package com.example.idcardappnew;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.mikhaellopez.circularimageview.CircularImageView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class StudentActivity extends AppCompatActivity {
    EditText studentname,studentfname,studentmname,studentaddress,studentcontact,studentid;
    Button studentsubmit;
    RadioButton studentmale,studentfemale;
    RadioGroup group;

   CircularImageView studentimage;
    StudentBean sb;
  StudentSqliteDBHelper dbhandler ;
  Spinner studentcourse,studentbatch;
    ArrayList<String>course,batch;
    ArrayAdapter<String>courseadapter,batchadapter;
  //  StudentSqliteDBHelper dbhandler;
    static final int REQUEST_IMAGE_CAPTURE=1;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        initViews();

       // initObject();


    }
    public void initViews(){
        //else{
        studentimage = (CircularImageView) findViewById(R.id.StudentImage);
        studentimage.setOnClickListener(takephoto);
        studentname = (EditText) findViewById(R.id.StudentName);
        studentfname = (EditText) findViewById(R.id.StudentFName);
        studentmname = (EditText) findViewById(R.id.StudentMName);
        studentaddress = (EditText) findViewById(R.id.StudentAddress);
        group = (RadioGroup) findViewById(R.id.group);
        studentmale = (RadioButton) findViewById(R.id.Male);
        studentfemale = (RadioButton) findViewById(R.id.Female);
        studentid = (EditText) findViewById(R.id.StudentID);
        course = new ArrayList<>();
        course.add("Select Course");
        course.add("BCA");
        course.add("MCA");
        course.add("Administration");
        course.add("Art and Design");
        course.add("Beauty Therapy");
        course.add("Business Management");
        course.add("Communication");
        course.add("Counselling");
        courseadapter=new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item,course);
       courseadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        studentcourse = (Spinner) findViewById(R.id.StudentCourse);
        studentcourse.setAdapter(courseadapter);
        studentcourse.setOnItemSelectedListener(spinlist);
        batch=new ArrayList<>();
        batch.add("Select Batch");
        batch.add("2010-2013");
        batch.add("2013-2016");
        batch.add("2016-2019");
        batchadapter=new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item,batch);
        batchadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        studentbatch = (Spinner) findViewById(R.id.StudentBatch);
        studentbatch.setAdapter(batchadapter);
        studentbatch.setOnItemSelectedListener(spinlist);
        //batchend=(Spinner)findViewById(R.id.StudentBatchEnd);
        studentcontact = (EditText) findViewById(R.id.StudentContact);
        studentsubmit = (Button) findViewById(R.id.StudentSubmit);
        studentsubmit.setOnClickListener(submit);
        sb = new StudentBean();
        dbhandler = new StudentSqliteDBHelper(this, null, null, 1);

        if(Utils.flag==true) {
           // Toast.makeText(StudentActivity.this,Utils.sb.getStudentname(),Toast.LENGTH_LONG).show();
            studentname.setText(Utils.sb.getStudentname());
            studentfname.setText(Utils.sb.getStudentfname());
            studentmname.setText(Utils.sb.getStudentmname());
            studentaddress.setText(Utils.sb.getStudentaddress());
            if(sb.getStudentgender().equals("M"))
                group.check(R.id.Male);
            else
                group.check(R.id.Female);

            studentid.setText(Utils.sb.getStudentid());
           // course.setSelected();
            for(int i=0;i<course.size();i++){
                if(course.get(i).equals(Utils.sb.getStudentcourse())){
                    studentcourse.setSelection(i);
                }
            }

            for(int i=0;i<batch.size();i++){
                if(batch.get(i).equals(Utils.sb.getStudentbatch())){
                    studentbatch.setSelection(i);
                }
            }
            studentcontact.setText(Utils.sb.getStudentcontact().toString());
            studentsubmit.setText("Update");
            byte[] studentimage1= Utils.sb.getStudentimage();
            Bitmap bitmap= BitmapFactory.decodeByteArray(studentimage1,0,studentimage1.length);
            studentimage.setImageBitmap(bitmap);
            studentimage.setTag("Image Captured");

        }
        //}


    }
    public void initObject(){

            byte[] image = imageViewToByte(studentimage);
            sb.setStudentimage(image);
            sb.setStudentname(studentname.getText().toString());
            sb.setStudentfname(studentfname.getText().toString());
            sb.setStudentmname(studentmname.getText().toString());
            sb.setStudentaddress(studentaddress.getText().toString());
            sb.setStudentcontact((Integer.parseInt(studentcontact.getText().toString())));
            if (studentmale.isChecked())
                sb.setStudentgender(studentmale.getText().toString());
            else
                sb.setStudentgender(studentfemale.getText().toString());
            sb.setStudentid(studentid.getText().toString().trim());
            sb.setStudentcourse(studentcourse.getSelectedItem().toString());
            sb.setStudentbatch(studentbatch.getSelectedItem().toString());

    }
    public void clearViews(){
        studentimage.setImageResource(R.drawable.takephoto1);
        studentname.setText("");
        studentfname.setText("");
        studentmname.setText("");
        studentaddress.setText("");
        studentcontact.setText("");
        group.clearCheck();
        studentid.setText("");
        studentbatch.setSelection(0);
        studentcourse.setSelection(0);

    }
    public static byte[] imageViewToByte(ImageView image){
        Bitmap bitmap=((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[]byteArray=stream.toByteArray();
        return byteArray;

    }


    View.OnClickListener takephoto=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!(getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY))){
                Toast.makeText(StudentActivity.this,"Your device has no Camera Service",Toast.LENGTH_LONG).show();
            }else{
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //Take a picture and pass along it to onActivityResult
                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
            }

        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        if(requestCode==REQUEST_IMAGE_CAPTURE && resultCode==RESULT_OK){
            //Get the photo
            Bundle extras=data.getExtras();
            Bitmap photo=(Bitmap)extras.get("data");//give the information about photo
            studentimage.setImageBitmap(photo);   //Set the image to imageview
            studentimage.setTag("Image Captured");
        }
    }
    Spinner.OnItemSelectedListener spinlist=new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            int vid=view.getId();
            switch (vid){
                case R.id.StudentCourse:
                    //  bean.setTeacherqualification(teacherqualification.getSelectedItem().toString());
                    break;
                case R.id.StudentBatch:
                    //    bean.setTeacherdepart(teacherdepart.getSelectedItem().toString());
                    break;

            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };


    View.OnClickListener submit=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //if(Utils.sb.getId()==0)
            if (studentname.getText().toString().isEmpty()) {
                studentname.setError("Cant be blank");
            }else if (studentname.getText().toString().matches("[@#%$&*^()?<>]+")) {
                studentname.setError("Not Correct");
            }else if (studentname.getText().toString().matches("[0-9]+")) {
                studentname.setError("contain numbers");
            }
            else if (studentfname.getText().toString().isEmpty()) {
                studentfname.setError("Cant be blank");

            }else if (studentfname.getText().toString().matches("[@#%$&*^()?<>]+")) {
                studentfname.setError("Not Correct");
            }else if (studentfname.getText().toString().matches("[0-9]+")) {
                studentfname.setError(" contain numbers");
            }
            else if (studentmname.getText().toString().isEmpty()) {
                studentmname.setError("Cant be blank");

            }else if (studentmname.getText().toString().matches("[@#%$&*^()?<>]+")) {
                studentmname.setError("Not Correct");
            }else if (studentmname.getText().toString().matches("[0-9]+")) {
                studentmname.setError("contain numbers");
            }
            else  if (studentaddress.getText().toString().isEmpty()) {
                studentaddress.setError("Cant be blank");

            }else if (studentaddress.getText().toString().matches("[@#%$&*^()?<>]+")) {
                studentaddress.setError("Not Correct");
            }else if(!(studentmale.isChecked()||studentfemale.isChecked())){
                BufferMessage("Select Your Gender");
            }else if (studentid.getText().toString().isEmpty()) {
                studentid.setError("Cant be blank");
            }else if (studentid.getText().toString().matches("[@#%$&*^()?<>]+")) {
                studentid.setError("Not Correct");
            }else if (studentcontact.getText().toString().isEmpty()) {
                studentcontact.setError("Cant be blank");
            }else if(studentcontact.getText().toString().length()!=10){
                studentcontact.setError("Length is not correct");
            }else if(studentcourse.getSelectedItem().equals("Select Course")){
                BufferMessage("Select Your Course");
            }else if(studentbatch.getSelectedItem().equals("Select Batch")) {
                BufferMessage("Select Your Batch");
            }else if(studentimage.getTag().equals("Capture Your Image")) {
                BufferMessage("Capture Your Image");
            }
            else {
                initObject();
                dbhandler.addStudent(sb);
                clearViews();
                if(Utils.flag==true){
                    Toast.makeText(StudentActivity.this,"Information Updated Succcessfully",Toast.LENGTH_LONG).show();
                    Utils.flag=false;
                }else{
                    Toast.makeText(StudentActivity.this,"Information Inserted Succcessfully",Toast.LENGTH_LONG).show();
                }
            }




            //Toast.makeText(StudentActivity.this,sb.getStudentname()+sb.getStudentfname()+sb.getStudentmname()+sb.getStudentgender()+sb.getStudentaddress()+sb.getStudentcontact(),Toast.LENGTH_LONG).show();

        }
    };
    void BufferMessage(String str) {
        AlertDialog dialog;
        final AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);
        builder.setMessage(str);
        builder.setPositiveButton("OK",null);
        dialog = builder.create();
        dialog.show();
        str="";

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
      //  MenuInflater inflater=getMenuInflater();
        //inflater.inflate(R.menu.menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //int id=item.getItemId();
        //if(id==R.id.student_list){

         //   return true;

      //  }
        return super.onOptionsItemSelected(item);
    }
}
