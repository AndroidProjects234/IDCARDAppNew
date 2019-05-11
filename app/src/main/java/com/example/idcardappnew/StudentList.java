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

public class StudentList extends AppCompatActivity {
    EditText search;
    ListView studentlist;
    public static StudentListAdapter adapter;
 public static ArrayList<StudentBean>list;
    StudentSqliteDBHelper dbhandler;
    StudentBean sb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentlist);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dbhandler=new StudentSqliteDBHelper(this,null,null,1);
        search=(EditText)findViewById(R.id.StudentFilter);
        studentlist=(ListView)findViewById(R.id.StudentList);
        list=new ArrayList<>();
        printDatabase();
        adapter=new StudentListAdapter(this,list);
        studentlist.setAdapter(adapter);
        studentlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              Utils.sb=list.get(position);
              Utils.pos=position;
                Intent i=new Intent(StudentList.this, ChooseOptionActivity.class);
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
        Cursor c= dbhandler.databaseToString();
        c.moveToNext();
        while (!c.isAfterLast()) {
            sb=new StudentBean();
            //Resultset having data
            sb.setId(c.getInt(c.getColumnIndex(StudentSqliteDBHelper.COLUMN_ID)));
            sb.setStudentname(c.getString(c.getColumnIndex(StudentSqliteDBHelper.COLUMN_STUDENTNAME)));
            sb.setStudentfname(c.getString(c.getColumnIndex(StudentSqliteDBHelper.COLUMN_STUDENTFNAME)));
            sb.setStudentmname(c.getString(c.getColumnIndex(StudentSqliteDBHelper.COLUMN_STUDENTMNAME)));
            sb.setStudentaddress(c.getString(c.getColumnIndex(StudentSqliteDBHelper.COLUMN_STUDENTADDRESS)));
            sb.setStudentgender(c.getString(c.getColumnIndex(StudentSqliteDBHelper.COLUMN_STUDENTGENDER)));
            sb.setStudentid(c.getString(c.getColumnIndex(StudentSqliteDBHelper.COLUMN_STUDENTID)));
            sb.setStudentcourse(c.getString(c.getColumnIndex(StudentSqliteDBHelper.COLUMN_STUDENTCOURSE)));
            sb.setStudentbatch(c.getString(c.getColumnIndex(StudentSqliteDBHelper.COLUMN_STUDENTBATCH)));
            sb.setStudentcontact(c.getInt(c.getColumnIndex(StudentSqliteDBHelper.COLUMN_STUDENTCONTACT)));
            sb.setStudentimage(c.getBlob(c.getColumnIndex(StudentSqliteDBHelper.COLUMN_STUDENTIMAGE)));
            list.add(sb);
            c.moveToNext();
        }

    }


}
