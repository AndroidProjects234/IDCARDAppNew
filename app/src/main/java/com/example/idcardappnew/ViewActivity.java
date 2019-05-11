package com.example.idcardappnew;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;

public class ViewActivity extends AppCompatActivity {

    TextView name,fname,mname,address,gender,contact,studentid,course,batch;
    CircularImageView image;
    Button ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        initViews();
        initObject();

    }
    public void initViews(){
        image=(CircularImageView) findViewById(R.id.ViewStudentImage);
        name=(TextView)findViewById(R.id.ViewStudentName);
        fname=(TextView)findViewById(R.id.ViewStudentFName);
        mname=(TextView)findViewById(R.id.ViewStudentMName);
        address=(TextView)findViewById(R.id.ViewStudentAddress);
        gender=(TextView)findViewById(R.id.ViewGender);
        studentid=(TextView)findViewById(R.id.ViewStudentID);
        course=(TextView)findViewById(R.id.ViewStudentCourse);
        batch=(TextView)findViewById(R.id.ViewStudentBatch);
        contact=(TextView)findViewById(R.id.ViewStudentContact);
        ok=(Button)findViewById(R.id.StudentOK);

    }
    public void initObject(){
        name.setText(Utils.sb.getStudentname());
        fname.setText(Utils.sb.getStudentfname());
        mname.setText(Utils.sb.getStudentmname());
        address.setText(Utils.sb.getStudentaddress());
        gender.setText(Utils.sb.getStudentgender());
        studentid.setText(Utils.sb.getStudentid());
        course.setText(Utils.sb.getStudentcourse());
        batch.setText(Utils.sb.getStudentbatch());
        contact.setText(Utils.sb.getStudentcontact().toString());
        byte[] studentimage= Utils.sb.getStudentimage();
        Bitmap bitmap= BitmapFactory.decodeByteArray(studentimage,0,studentimage.length);
        image.setImageBitmap(bitmap);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(ViewActivity.this, ChooseOptionActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
