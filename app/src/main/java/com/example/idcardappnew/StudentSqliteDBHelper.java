package com.example.idcardappnew;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StudentSqliteDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION=2;    //update structure of the database
    public static final String DATABASE_NAME="records.db";  //We save it into file
    public static final String TABLE_STUDENT="studentinfo";//name of the table
    public static final String TABLE_TEACHER="teacherinfo";
    public static final String COLUMN_ID="_id";                     //columns of table
    public static final String COLUMN_STUDENTNAME="name";
    public static final String COLUMN_STUDENTFNAME="fathername";
    public static final String COLUMN_STUDENTMNAME="mothername";
    public static final String COLUMN_STUDENTADDRESS="address";
    public static final String COLUMN_STUDENTGENDER="gender";
    public static final String COLUMN_STUDENTID="studentid";
    public static final String COLUMN_STUDENTCOURSE="course";
    public static final String COLUMN_STUDENTBATCH="batch";
    public static final String COLUMN_STUDENTCONTACT="contact";
    public static final String COLUMN_STUDENTIMAGE="image";

    public static final String COLUMN_TEACHERNAME="name";
    public static final String COLUMN_TEACHERQUALIFICATION="qualification";
    public static final String COLUMN_TEACHERGENDER="gender";
    public static final String COLUMN_TEACHERDEPART="department";
    public static final String COLUMN_TEACHERCOLLEGE="college";
    public static final String COLUMN_TEACHERPOSITION="position";
    public static final String COLUMN_TEACHERDOJ="doj";
    public static final String COLUMN_TEACHERWORKEXP="experience";
    public static final String COLUMN_TEACHERCONTACT="contact";
    public static final String COLUMN_TEACHERIMAGE="image";
//Student Table
  public static String query = "CREATE TABLE " + TABLE_STUDENT + "(" +         //Creating table for the first time
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
            COLUMN_STUDENTNAME + " TEXT ," +
            COLUMN_STUDENTFNAME + " TEXT ," +
            COLUMN_STUDENTMNAME + " TEXT ," +
            COLUMN_STUDENTADDRESS + " TEXT ," +
            COLUMN_STUDENTGENDER + " TEXT ," +
            COLUMN_STUDENTID + " TEXT ," +
            COLUMN_STUDENTCOURSE + " TEXT ," +
            COLUMN_STUDENTBATCH + " TEXT ," +
            COLUMN_STUDENTCONTACT + " NUMERIC ," +
            COLUMN_STUDENTIMAGE + " BLOB " +
            ");";
  //Teacher Table
    public static String query1 = "CREATE TABLE " + TABLE_TEACHER + "(" +         //Creating table for the first time
        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
        COLUMN_TEACHERNAME + " TEXT ," +
        COLUMN_TEACHERQUALIFICATION + " TEXT ," +
        COLUMN_TEACHERGENDER + " TEXT ," +
        COLUMN_TEACHERDEPART + " TEXT ," +
        COLUMN_TEACHERCOLLEGE + " TEXT ," +
        COLUMN_TEACHERPOSITION + " TEXT ," +
        COLUMN_TEACHERDOJ + " TEXT ," +
        COLUMN_TEACHERWORKEXP + " TEXT ," +
        COLUMN_TEACHERCONTACT + " NUMERIC ," +
        COLUMN_TEACHERIMAGE + " BLOB " +
        ");";


    public StudentSqliteDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(query);
        db.execSQL(query1);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);//Delete the table
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEACHER);//Delete the table
        onCreate(db);


    }

    public void addStudent(StudentBean studentBean){
        //StudentActivity sb=new StudentActivity();
        ContentValues values=new ContentValues();//Allows to set different values to different columns
        values.put(COLUMN_STUDENTNAME,studentBean.getStudentname());
        values.put(COLUMN_STUDENTFNAME,studentBean.getStudentfname());
        values.put(COLUMN_STUDENTMNAME,studentBean.getStudentmname());
        values.put(COLUMN_STUDENTADDRESS,studentBean.getStudentaddress());
        values.put(COLUMN_STUDENTGENDER,studentBean.getStudentgender());
        values.put(COLUMN_STUDENTID,studentBean.getStudentid());
        values.put(COLUMN_STUDENTCOURSE,studentBean.getStudentcourse());
        values.put(COLUMN_STUDENTBATCH,studentBean.getStudentbatch());
        values.put(COLUMN_STUDENTCONTACT,studentBean.getStudentcontact());
        values.put(COLUMN_STUDENTIMAGE,studentBean.getStudentimage());
        SQLiteDatabase db=getWritableDatabase();    //db is now equalto database we are going to write
        if(Utils.flag==false) {
            db.insert(TABLE_STUDENT, null, values);//Insert the product into a table
          //  sb.showToast("Information Inserted Successfully");
        } else {
            db.update(TABLE_STUDENT, values, COLUMN_ID + " = " + Utils.sb.getId(), null);
            StudentList.list.set(Utils.pos,studentBean);
            StudentListAdapter.templist.set(Utils.pos,studentBean);
            Utils.sb=studentBean;
            StudentList.adapter.notifyDataSetChanged();
           // sb.showToast("Information Updated Successfully");


        }
        db.close();//Close the database


    }
    public void deleteStudent(){
        //StudentActivity sb=new StudentActivity();
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_STUDENT + " WHERE " + COLUMN_ID + "=\"" + Utils.sb.getId() + "\";");
        StudentList.list.remove(Utils.sb);
        StudentListAdapter.templist.remove(Utils.sb);
        StudentList.adapter.notifyDataSetChanged();
      //  sb.showToast("Information Deleted Successfully");

    }

    public Cursor databaseToString(){
        SQLiteDatabase db=getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_STUDENT + " WHERE 1 ";    // * means all coloumns 1 means all rows
        //Cursor points to a location in your results
        Cursor c= db.rawQuery(query,null);  //pointer to a results
        return c;
    }
    public void addTeacher(TeacherBean teacherBean){
        ContentValues values=new ContentValues();//Allows to set different values to different columns
        values.put(COLUMN_TEACHERNAME,teacherBean.getTeachername());
          values.put(COLUMN_TEACHERQUALIFICATION,teacherBean.getTeacherqualification());
        values.put(COLUMN_TEACHERGENDER,teacherBean.getTeachergender());
         values.put(COLUMN_TEACHERDEPART,teacherBean.getTeacherdepart());
         values.put(COLUMN_TEACHERCOLLEGE,teacherBean.getTeachercollege());
          values.put(COLUMN_TEACHERPOSITION,teacherBean.getTeacherposition());
          values.put(COLUMN_TEACHERDOJ,teacherBean.getTeacherdoj());
         values.put(COLUMN_TEACHERWORKEXP,teacherBean.getTeacherworkexp());
        values.put(COLUMN_TEACHERCONTACT,teacherBean.getTeachercontact());
        values.put(COLUMN_TEACHERIMAGE,teacherBean.getTeacherimage());
        SQLiteDatabase db=getWritableDatabase();    //db is now equalto database we are going to write
        if(Utils.flag1==false)
            db.insert(TABLE_TEACHER,null,values);//Insert the product into a table

        else{
            db.update(TABLE_TEACHER,values,COLUMN_ID + " = " + Utils.tb.getId(),null);
            TeacherList.list.set(Utils.pos1,teacherBean);
            TeacherListAdapter.templist.set(Utils.pos1,teacherBean);
            Utils.tb=teacherBean;
           // TeacherListAdapter.templist.set(Utils.tb.getId(),teacherBean);
            TeacherList.adapter.notifyDataSetChanged();


        }
       // Toast.makeText(,""+teacherBean.getTeacherposition(),Toast.LENGTH_LONG).show();
        db.close();//Close the database


    }

    public void deleteTeacher(){
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_TEACHER + " WHERE " + COLUMN_ID + "=\"" + Utils.tb.getId() + "\";");
        TeacherList.list.remove(Utils.tb);
        TeacherListAdapter.templist.remove(Utils.tb);
        TeacherList.adapter.notifyDataSetChanged();
    }

    public Cursor databaseToString1(){
        SQLiteDatabase db=getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_TEACHER + " WHERE 1 ";    // * means all coloumns 1 means all rows
        //Cursor points to a location in your results
        Cursor c= db.rawQuery(query,null);  //pointer to a results
        return c;
    }
}
