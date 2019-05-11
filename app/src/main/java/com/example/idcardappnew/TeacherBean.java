package com.example.idcardappnew;

public class TeacherBean {
    private String teachername,teacherqualification,teachergender,teacherdepart,teachercollege,teacherposition,
            teacherdoj,teacherworkexp;
    private byte[] teacherimage;
    int id,teachercontact;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getTeacherimage() {
        return teacherimage;
    }

    public void setTeacherimage(byte[] teacherimage) {
        this.teacherimage = teacherimage;
    }

    public TeacherBean() {
        teachername="";
        teacherqualification="";
        teachergender="";
        teacherdepart="";
        teachercollege="";
        teacherposition="";
        teacherdoj="";
        teacherworkexp="";
        teachercontact=0;
        id=0;
    }

    public String getTeachername() {
        return teachername;
    }

    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }

    public String getTeacherqualification() {
        return teacherqualification;
    }

    public void setTeacherqualification(String teacherqualification) {
        this.teacherqualification = teacherqualification;
    }

    public String getTeachergender() {
        return teachergender;
    }

    public void setTeachergender(String teachergender) {
        this.teachergender = teachergender;
    }

    public String getTeacherdepart() {
        return teacherdepart;
    }

    public void setTeacherdepart(String teacherdepart) {
        this.teacherdepart = teacherdepart;
    }

    public String getTeachercollege() {
        return teachercollege;
    }

    public void setTeachercollege(String teachercollege) {
        this.teachercollege = teachercollege;
    }

    public String getTeacherposition() {
        return teacherposition;
    }

    public void setTeacherposition(String teacherposition) {
        this.teacherposition = teacherposition;
    }

    public String getTeacherdoj() {
        return teacherdoj;
    }

    public void setTeacherdoj(String teacherdoj) {
        this.teacherdoj = teacherdoj;
    }

    public String getTeacherworkexp() {
        return teacherworkexp;
    }

    public void setTeacherworkexp(String teacherworkexp) {
        this.teacherworkexp = teacherworkexp;
    }

    public int getTeachercontact() {
        return teachercontact;
    }

    public void setTeachercontact(int teachercontact) {
        this.teachercontact = teachercontact;
    }
}
