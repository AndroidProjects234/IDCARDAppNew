package com.example.idcardappnew;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainListActivity extends AppCompatActivity {
    RadioButton viewlist,addstudent,addteacher;
    RadioGroup mainlistgroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mainlistgroup=(RadioGroup)findViewById(R.id.main_list_group);
        viewlist=(RadioButton) findViewById(R.id.ViewList);
        viewlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainListActivity.this, AllListActivity.class);
                startActivity(i);
                mainlistgroup.clearCheck();

            }
        });
        addstudent=(RadioButton) findViewById(R.id.AddStudent);
        addstudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainListActivity.this, StudentActivity.class);
                startActivity(i);
                mainlistgroup.clearCheck();



            }
        });
        addteacher=(RadioButton) findViewById(R.id.AddTeacher);
        addteacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(MainListActivity.this,TeacherActivity.class);
                startActivity(i1);
                mainlistgroup.clearCheck();

            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
          MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id== R.id.logout){
            Intent i=new Intent(this, LoginActivity.class);
            startActivity(i);
            finish();
          return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
