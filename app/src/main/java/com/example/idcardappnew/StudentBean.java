package com.example.idcardappnew;

public class StudentBean {
    private String studentname,studentfname,studentmname,studentaddress,studentgender,studentid,studentcourse,studentbatch;
    private int studentcontact,id;
    public byte[]studentimage;



    public StudentBean() {
        studentname="";
        studentfname="";
        studentmname="";
        studentaddress="";
        studentgender="";
        studentid="";
        studentcourse="";
        studentbatch="";
        studentcontact=0;
        id=0;

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public byte[] getStudentimage() {
        return studentimage;
    }

    public void setStudentimage(byte[] studentimage) {
        this.studentimage = studentimage;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public String getStudentfname() {
        return studentfname;
    }

    public void setStudentfname(String studentfname) {
        this.studentfname = studentfname;
    }

    public String getStudentmname() {
        return studentmname;
    }

    public void setStudentmname(String studentmname) {
        this.studentmname = studentmname;
    }

    public String getStudentaddress() {
        return studentaddress;
    }

    public void setStudentaddress(String studentaddress) {
        this.studentaddress = studentaddress;
    }

    public String getStudentgender() {
        return studentgender;
    }

    public void setStudentgender(String studentgender) {
        this.studentgender = studentgender;
    }
    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getStudentcourse() {
        return studentcourse;
    }

    public void setStudentcourse(String studentcourse) {
        this.studentcourse = studentcourse;
    }

    public String getStudentbatch() {
        return studentbatch;
    }

    public void setStudentbatch(String studentbatch) {
        this.studentbatch = studentbatch;
    }


    public Integer getStudentcontact() {
        return studentcontact;
    }

    public void setStudentcontact(Integer studentcontact) {
        this.studentcontact = studentcontact;
    }
}
