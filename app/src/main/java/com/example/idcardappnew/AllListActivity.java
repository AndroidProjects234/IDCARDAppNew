package com.example.idcardappnew;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class AllListActivity extends AppCompatActivity {
TextView alllisttext;
RadioGroup group;
RadioButton allliststudent,alllistteacher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        alllisttext=(TextView)findViewById(R.id.AllListText);
        group=(RadioGroup)findViewById(R.id.AllListGroup);
        allliststudent=(RadioButton)findViewById(R.id.AllListStudent);
        allliststudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(AllListActivity.this, StudentList.class);
                startActivity(i);
                group.clearCheck();
            }
        });
        alllistteacher=(RadioButton)findViewById(R.id.AllListTeacher);
        alllistteacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(AllListActivity.this,TeacherList.class);
                startActivity(i);
                group.clearCheck();

            }
        });
    }
}
