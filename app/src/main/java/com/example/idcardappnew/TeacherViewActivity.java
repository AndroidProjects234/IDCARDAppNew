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

public class TeacherViewActivity extends AppCompatActivity {
    CircularImageView image;
    Button teacherok;
    TextView name,qualification,gender,depart,college,position,doj,workexp,contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacherview);
        initViews();
        initObject();

    }
    public void initViews(){
        image=(CircularImageView)findViewById(R.id.ViewTeacherImage);
        name=(TextView)findViewById(R.id.ViewTeacherName);
        qualification=(TextView)findViewById(R.id.ViewTeacherQualification);
        gender=(TextView)findViewById(R.id.Viewgender);
        depart=(TextView)findViewById(R.id.ViewTeacherDepartment);
        college=(TextView)findViewById(R.id.ViewTeacherCollege);
        position=(TextView)findViewById(R.id.ViewTeacherPosition);
        doj=(TextView)findViewById(R.id.ViewTeacherDOJ);
        workexp=(TextView)findViewById(R.id.ViewTeacherWorkExp);
        contact=(TextView)findViewById(R.id.ViewTeacherContact);
        teacherok=(Button)findViewById(R.id.TeacherOK);
        teacherok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(TeacherViewActivity.this,ChooseOptionTeacherActivity.class);
                startActivity(i);
                finish();
            }
        });

    }
    public void initObject(){
        name.setText(Utils.tb.getTeachername());
        qualification.setText(Utils.tb.getTeacherqualification());
        gender.setText(Utils.tb.getTeachergender());
        depart.setText(Utils.tb.getTeacherdepart());
        college.setText(Utils.tb.getTeachercollege());
        position.setText(Utils.tb.getTeacherposition());
        doj.setText(Utils.tb.getTeacherdoj());
        workexp.setText(Utils.tb.getTeacherworkexp());
        contact.setText(String.valueOf(Utils.tb.getTeachercontact()));
        byte[] teacherimage= Utils.tb.getTeacherimage();
        Bitmap bitmap= BitmapFactory.decodeByteArray(teacherimage,0,teacherimage.length);
        image.setImageBitmap(bitmap);

    }
}
