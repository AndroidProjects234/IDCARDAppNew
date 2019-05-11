package com.example.idcardappnew;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ChooseOptionActivity extends AppCompatActivity {
    String key;
    RadioGroup optiongroup;
    RadioButton optionview,optionmodify,optiondelete;
    StudentSqliteDBHelper  dbhandler ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_option);
        dbhandler=new StudentSqliteDBHelper(this,null,null,1);
        optiongroup=(RadioGroup)findViewById(R.id.optiongroup);
        optionview=(RadioButton)findViewById(R.id.OptionView);
        optionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2=new Intent(ChooseOptionActivity.this,ViewActivity.class);
                startActivity(i2);
                optiongroup.clearCheck();
                finish();
            }
        });

        optionmodify=(RadioButton)findViewById(R.id.OptionModify);
        optionmodify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.flag = true;
                Intent i1 = new Intent(ChooseOptionActivity.this, StudentActivity.class);
                startActivity(i1);
                optiongroup.clearCheck();
            }
        });

        optiondelete=(RadioButton)findViewById(R.id.OptionDelete);

        optiondelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbhandler.deleteStudent();
                Toast.makeText(ChooseOptionActivity.this,"Information Deleted Succcessfully",Toast.LENGTH_LONG).show();
                optiongroup.clearCheck();
            }
        });


    }



}
