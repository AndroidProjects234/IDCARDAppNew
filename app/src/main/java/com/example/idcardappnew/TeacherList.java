package com.example.idcardappnew;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class TeacherList extends AppCompatActivity {

    EditText search;
    ListView teacherlist;
  public static   ArrayList<TeacherBean> list;
  public static TeacherListAdapter adapter;
    StudentSqliteDBHelper dbhandler;
    TeacherBean tb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacherlist);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dbhandler=new StudentSqliteDBHelper(this,null,null,1);
        search=(EditText)findViewById(R.id.TeacherFilter);
        teacherlist=(ListView)findViewById(R.id.TeacherList);
        list=new ArrayList<>();
        printDatabase();
        adapter=new TeacherListAdapter(this,list);
        teacherlist.setAdapter(adapter);
        teacherlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Utils.tb=list.get(position);
                Utils.pos1=position;
              //  Toast.makeText(TeacherList.this,Utils.tb.getTeachername(),Toast.LENGTH_LONG).show();
                Intent i=new Intent(TeacherList.this, ChooseOptionTeacherActivity.class);
                startActivity(i);
            }
        });
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.filter(search.getText().toString().toLowerCase().trim());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
    public void printDatabase(){
        list.clear();
        Cursor c= dbhandler.databaseToString1();
        c.moveToNext();
        while (!c.isAfterLast()) {
            tb=new TeacherBean();
            //Resultset having data
            tb.setId(c.getInt(c.getColumnIndex(StudentSqliteDBHelper.COLUMN_ID)));
            tb.setTeachername(c.getString(c.getColumnIndex(StudentSqliteDBHelper.COLUMN_TEACHERNAME)));
            tb.setTeacherqualification(c.getString(c.getColumnIndex(StudentSqliteDBHelper.COLUMN_TEACHERQUALIFICATION)));
            tb.setTeachergender(c.getString(c.getColumnIndex(StudentSqliteDBHelper.COLUMN_TEACHERGENDER)));
            tb.setTeacherdepart(c.getString(c.getColumnIndex(StudentSqliteDBHelper.COLUMN_TEACHERDEPART)));
            tb.setTeachercollege(c.getString(c.getColumnIndex(StudentSqliteDBHelper.COLUMN_TEACHERCOLLEGE)));
            tb.setTeacherposition(c.getString(c.getColumnIndex(StudentSqliteDBHelper.COLUMN_TEACHERPOSITION)));
            tb.setTeacherdoj(c.getString(c.getColumnIndex(StudentSqliteDBHelper.COLUMN_TEACHERDOJ)));
            tb.setTeacherworkexp(c.getString(c.getColumnIndex(StudentSqliteDBHelper.COLUMN_TEACHERWORKEXP)));
            tb.setTeachercontact(c.getInt(c.getColumnIndex(StudentSqliteDBHelper.COLUMN_TEACHERCONTACT)));
            tb.setTeacherimage(c.getBlob(c.getColumnIndex(StudentSqliteDBHelper.COLUMN_TEACHERIMAGE)));
            list.add(tb);
            c.moveToNext();
        }

    }


}

