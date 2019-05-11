package com.example.idcardappnew;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ChooseOptionTeacherActivity extends AppCompatActivity {
    String key;
    RadioGroup optiongroup;
    RadioButton optionview,optionmodify,optiondelete;
    StudentSqliteDBHelper  dbhandler ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_choose_option);

        dbhandler=new StudentSqliteDBHelper(this,null,null,1);

        optiongroup=(RadioGroup)findViewById(R.id.optiongroup);
        optionview=(RadioButton)findViewById(R.id.TeacherOptionView);
        optionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2=new Intent(ChooseOptionTeacherActivity.this, TeacherViewActivity.class);
                startActivity(i2);
                optiongroup.clearCheck();
                finish();

            }
        });

        optionmodify=(RadioButton)findViewById(R.id.TeacherOptionModify);
        optionmodify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.flag1 = true;
                Intent i1 = new Intent(ChooseOptionTeacherActivity.this, TeacherActivity.class);
                startActivity(i1);
                optiongroup.clearCheck();
            }
        });

        optiondelete=(RadioButton)findViewById(R.id.TeacherOptionDelete);
        optiondelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbhandler.deleteTeacher();
                Toast.makeText(ChooseOptionTeacherActivity.this,"Information Deleted Succcessfully",Toast.LENGTH_LONG).show();
                optiongroup.clearCheck();
            }
        });



    }


}
