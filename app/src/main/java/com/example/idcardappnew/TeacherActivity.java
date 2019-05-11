package com.example.idcardappnew;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;

public class TeacherActivity extends AppCompatActivity {

    EditText teachername,teachercontact;
    Spinner teacherqualification,teacherdepart,teacherdojdd,teacherdojmm,teacherdojyy,teacherworkexp,teachercollege,teacherposition;
    RadioGroup group;
    RadioButton teachermale,teacherfemale;
    Button teachersubmit;
    CircularImageView teacherimage;
    TeacherBean bean;

    StudentSqliteDBHelper dbhandler;
    static final int REQUEST_IMAGE_CAPTURE=1;
    ArrayList<String> qualification,department,college,position,dojdd,dojmm,dojyy,workexp;
    ArrayAdapter<String>qualificationadapter,departmentadapter,collegeadapter,positionadapter,dojadapterdd,dojadaptermm,dojadapteryy,workexpadapter;
    String str="",str2="",str3="";
    String date[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        bean=new TeacherBean();
        dbhandler=new StudentSqliteDBHelper(this,null,null,1);
        initViews();
    }
    public void initViews(){
        qualification=new ArrayList<>();
        qualification.add("Select Qualification");
        qualification.add("MSocSci");
        qualification.add("MBA");
        qualification.add("BCS AIT");
        qualification.add("BArch (Hons) ");
        qualification.add("BHortSc");
        qualification.add("CMHRINZ ");
        qualificationadapter=new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item,qualification);
        qualificationadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        teacherqualification=(Spinner)findViewById(R.id.TeacherQualification);
        teacherqualification.setAdapter(qualificationadapter);
        teacherqualification.setOnItemSelectedListener(spinlist);
        department=new ArrayList<>();
        department.add("Select Department");
        department.add("Business Administration");
        department.add("Agribusiness");
        department.add("Arboriculture");
        department.add("Architectural Technology");
        department.add("Art and Design");
        department.add("Beauty Therapy");
        department.add("Business Management");
        department.add("Communication");
        department.add("Counselling");
        departmentadapter=new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item,department);
        departmentadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        teacherdepart=(Spinner)findViewById(R.id.TeacherDepartment);
        teacherdepart.setAdapter(departmentadapter);
        teacherdepart.setOnItemSelectedListener(spinlist);
        college=new ArrayList<>();
        college.add("Select College");
        college.add("Wintec");
        collegeadapter=new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item,college);
        collegeadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        teachercollege=(Spinner)findViewById(R.id.TeacherCollege);
        teachercollege.setAdapter(collegeadapter);
        teachercollege.setOnItemSelectedListener(spinlist);
        position=new ArrayList<>();
        position.add("Select Position");
        position.add("Senior Lecturer");
        position.add("Junior Lecturer");
        position.add("Fresher");
        positionadapter=new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item,position);
        positionadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        teacherposition=(Spinner)findViewById(R.id.TeacherPosition);
        teacherposition.setAdapter(positionadapter);
        teacherposition.setOnItemSelectedListener(spinlist);
        dojdd=new ArrayList<>();
        dojdd.add("DD");
        for(int i=1;i<=31;i++){
            dojdd.add(String.valueOf(i));
        }
        dojadapterdd=new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item,dojdd);
        dojadapterdd.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        teacherdojdd=(Spinner)findViewById(R.id.TeacherDOJdd);
        teacherdojdd.setAdapter(dojadapterdd);
        teacherdojdd.setOnItemSelectedListener(spinlist);
        dojmm=new ArrayList<>();
        dojmm.add("MM");
        dojmm.add("Jan");
        dojmm.add("Feb");
        dojmm.add("Mar");
        dojmm.add("April");
        dojmm.add("May");
        dojmm.add("June");
        dojmm.add("July");
        dojmm.add("Aug");
        dojmm.add("Sept");
        dojmm.add("Oct");
        dojmm.add("Nov");
        dojmm.add("Dec");
        dojadaptermm=new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item,dojmm);
        dojadaptermm.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        teacherdojmm=(Spinner)findViewById(R.id.TeacherDOJmm);
        teacherdojmm.setAdapter(dojadaptermm);
        teacherdojmm.setOnItemSelectedListener(spinlist);
        dojyy=new ArrayList<>();
        dojyy.add("YY");
        for(int i=2010;i<=2019;i++){
            dojyy.add(String.valueOf(i));
        }
        dojadapteryy=new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item,dojyy);
        dojadapteryy.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        teacherdojyy=(Spinner)findViewById(R.id.TeacherDOJyy);
        teacherdojyy.setAdapter(dojadapteryy);
        teacherdojyy.setOnItemSelectedListener(spinlist);

        workexp=new ArrayList<>();
        workexp.add("Select Work Experience");
        workexp.add("1");
        workexp.add("2");
        workexp.add("3");
        workexp.add("4");
        workexp.add("5");
        workexp.add("6");
        workexp.add("7");
        workexp.add("8");
        workexp.add("9");
        workexp.add("10");
        workexp.add("Above");
        workexpadapter=new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,workexp);
        workexpadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        teacherworkexp=(Spinner)findViewById(R.id.TeacherWorkExp);
        teacherworkexp.setAdapter(workexpadapter);
        teacherworkexp.setOnItemSelectedListener(spinlist);

        teacherimage=(CircularImageView) findViewById(R.id.TeacherImage);
        teacherimage.setOnClickListener(takephoto);
        teachername=(EditText)findViewById(R.id.TeacherName);
        group=(RadioGroup)findViewById(R.id.tgroup);
        teachermale=(RadioButton)findViewById(R.id.TeacherMale);
        teacherfemale=(RadioButton)findViewById(R.id.TeacherFemale);
        teachercontact=(EditText)findViewById(R.id.TeacherContact);
        teachersubmit=(Button)findViewById(R.id.TeacherSubmit);
        teachersubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (teachername.getText().toString().isEmpty()) {
                    teachername.setError("Cant be blank");
                }else if (teachername.getText().toString().matches("[@#%$&*^()?<>]+")) {
                    teachername.setError("Not Correct");
                }else if (teachername.getText().toString().matches("[0-9]+")) {
                    teachername.setError("contain numbers");
                }else if (teacherqualification.getSelectedItem().equals("Select Qualification")) {
                    BufferMessage("Select Qualification");
                }else if(!(teachermale.isChecked()||teacherfemale.isChecked())){
                    BufferMessage("Select Your Gender");
                }else if (teacherdepart.getSelectedItem().equals("Select Department")) {
                    BufferMessage("Select Department");
                }else if (teachercollege.getSelectedItem().equals("Select College")) {
                    BufferMessage("Select College");
                }else if (teacherposition.getSelectedItem().equals("Select Position")) {
                    BufferMessage("Select Position");
                }else if (teacherdojdd.getSelectedItem().equals("DD")) {
                    BufferMessage("Select Day");
                }else if (teacherdojmm.getSelectedItem().equals("MM")) {
                    BufferMessage("Select Month");
                }else if (teacherdojyy.getSelectedItem().equals("YY")) {
                    BufferMessage("Select Year");
                }else if (teacherworkexp.getSelectedItem().equals("Select Work Experience")) {
                    BufferMessage("Select Work Experience");
                }else if (teachercontact.getText().toString().isEmpty()) {
                    teachercontact.setError("Cant be blank");
                }else if(teachercontact.getText().toString().length()!=10) {
                    teachercontact.setError("Length is not correct");
                }else if(teacherimage.getTag().equals("Take Photo")) {
                    BufferMessage("Capture Your Image");
                }
                else {
                    intiObject();
                    dbhandler.addTeacher(bean);
                    clearViews();
                    if(Utils.flag1==true){
                        Toast.makeText(TeacherActivity.this,"Information Updated Succcessfully",Toast.LENGTH_LONG).show();
                        Utils.flag1 = false;
                    }else{
                        Toast.makeText(TeacherActivity.this,"Information Inserted Succcessfully",Toast.LENGTH_LONG).show();
                    }
                }

              //  Toast.makeText(TeacherActivity.this,Utils.tb.getTeacherposition(),Toast.LENGTH_LONG).show();




            }
        });


        if(Utils.flag1==true) {
            // Toast.makeText(StudentActivity.this,Utils.sb.getStudentname(),Toast.LENGTH_LONG).show();
            teachername.setText(Utils.tb.getTeachername());
            for(int i=0;i<qualification.size();i++){
                if(qualification.get(i).equals(Utils.tb.getTeacherqualification())){
                    teacherqualification.setSelection(i);
                }
            }

            for(int i=0;i<department.size();i++){
                if(department.get(i).equals(Utils.tb.getTeacherdepart())){
                    teacherdepart.setSelection(i);
                }
            }
            for(int i=0;i<college.size();i++){
                if(college.get(i).equals(Utils.tb.getTeachercollege())){
                    teachercollege.setSelection(i);
                }
            }
            for(int i=0;i<position.size();i++){
                if(position.get(i).equals(Utils.tb.getTeacherposition())){
                    teacherposition.setSelection(i);
                }
            }
            date= Utils.tb.getTeacherdoj().split("/");
            for(int i=0;i<dojdd.size();i++){
                if(dojdd.get(i).equals(date[0])){
                    teacherdojdd.setSelection(i);
                }
            }

            for(int i=1;i<dojmm.size();i++){
                if(dojmm.get(i).equals(date[1])){
                    teacherdojmm.setSelection(i);
                }
            }
            for(int i=0;i<dojyy.size();i++){
                if(dojyy.get(i).equals(date[2])){
                    teacherdojyy.setSelection(i);
                }
            }

            for(int i=0;i<workexp.size();i++){
                if(workexp.get(i).equals(Utils.tb.getTeacherworkexp())){
                    teacherworkexp.setSelection(i);
                }
            }
            //studentmname.setText(Utils.sb.getStudentmname());
            //studentaddress.setText(Utils.sb.getStudentaddress());
            if(Utils.tb.getTeachergender().equals("M"))
                group.check(R.id.TeacherMale);
            else
                group.check(R.id.TeacherFemale);

            teachercontact.setText(String.valueOf(Utils.tb.getTeachercontact()));
            teachersubmit.setText("Update");
            byte[] teacherimage1= Utils.tb.getTeacherimage();
            Bitmap bitmap= BitmapFactory.decodeByteArray(teacherimage1,0,teacherimage1.length);
            teacherimage.setImageBitmap(bitmap);
            teacherimage.setTag("Photo Captured");
            //Utils.flag1=false;
        }

    }


    public void intiObject(){
        byte[]image= StudentActivity.imageViewToByte(teacherimage);
        bean.setTeacherimage(image);
        bean.setTeachername(teachername.getText().toString());
        if(teachermale.isChecked())
            bean.setTeachergender(teachermale.getText().toString());
        else
            bean.setTeachergender(teacherfemale.getText().toString());
        bean.setTeachercontact(Integer.parseInt(teachercontact.getText().toString()));
        bean.setTeacherqualification(teacherqualification.getSelectedItem().toString());
        bean.setTeacherdepart(teacherdepart.getSelectedItem().toString());
        bean.setTeachercollege(teachercollege.getSelectedItem().toString());
        bean.setTeacherposition(teacherposition.getSelectedItem().toString());
        str=teacherdojdd.getSelectedItem().toString();
        str2=teacherdojmm.getSelectedItem().toString();
        str3=teacherdojyy.getSelectedItem().toString();
        bean.setTeacherdoj(str+"/"+str2+"/"+str3);
        bean.setTeacherworkexp(teacherworkexp.getSelectedItem().toString());

    }

    public void clearViews(){
        //Empty all fields
        teacherimage.setImageResource(R.drawable.takephoto);
        teachername.setText("");
        teacherqualification.setSelection(0);
        group.clearCheck();
        teacherdepart.setSelection(0);
        teachercollege.setSelection(0);
        teacherposition.setSelection(0);
        teacherdojdd.setSelection(0);
        teacherdojmm.setSelection(0);
        teacherdojyy.setSelection(0);
        teacherworkexp.setSelection(0);
        teachercontact.setText("");

    }

    View.OnClickListener takephoto=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!(getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY))){
                Toast.makeText(TeacherActivity.this,"Your device has no Camera Service",Toast.LENGTH_LONG).show();
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
            teacherimage.setImageBitmap(photo);   //Set the image to imageview
            teacherimage.setTag("Photo Captured");
        }
    }


    Spinner.OnItemSelectedListener spinlist=new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            int vid=view.getId();
            switch (vid){
                case R.id.TeacherQualification:
                  //  bean.setTeacherqualification(teacherqualification.getSelectedItem().toString());
                    break;
                case R.id.TeacherDepartment:
                //    bean.setTeacherdepart(teacherdepart.getSelectedItem().toString());
                    break;
                case R.id.TeacherCollege:
                 //   bean.setTeachercollege(teachercollege.getSelectedItem().toString());
                    break;
                case R.id.TeacherPosition:
                 //   bean.setTeacherposition(teacherposition.getSelectedItem().toString());
                    break;
                case R.id.TeacherDOJdd:
                 //   str=teacherdojdd.getSelectedItem().toString();
                    break;
                case R.id.TeacherDOJmm:
                 //   str2=teacherdojmm.getSelectedItem().toString();
                    break;

                case R.id.TeacherDOJyy:
                //    str3=teacherdojyy.getSelectedItem().toString();
                    break;
                case R.id.TeacherWorkExp:
                  //  bean.setTeacherworkexp(teacherworkexp.getSelectedItem().toString());
                    break;

            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

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
        //MenuInflater inflater=getMenuInflater();
       // inflater.inflate(R.menu.menu,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //int id=item.getItemId();
       // if(id==R.id.teacher_list){


       // }
        return super.onOptionsItemSelected(item);
    }
}
