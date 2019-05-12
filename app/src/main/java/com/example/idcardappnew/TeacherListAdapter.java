package com.example.idcardappnew;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;

public class TeacherListAdapter extends ArrayAdapter {

    ArrayList<TeacherBean>list;
    public static ArrayList<TeacherBean>templist;

    public TeacherListAdapter(Context context,  ArrayList<TeacherBean> list) {
        super(context, R.layout.activity_teacherlistadapter,list);
        this.list=list;
        templist=new ArrayList<>();
        templist.addAll(list);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= LayoutInflater.from(getContext());
        View customView=inflater.inflate(R.layout.activity_teacherlistadapter,parent,false);
        CircularImageView image=(CircularImageView) customView.findViewById(R.id.TeacherListImage);
        TextView name=(TextView)customView.findViewById(R.id.TeacherListName);
        TeacherBean tb=new TeacherBean();
        tb=list.get(position);
        byte[] teacherimage=tb.getTeacherimage();
        Bitmap bitmap= BitmapFactory.decodeByteArray(teacherimage,0,teacherimage.length);
        image.setImageBitmap(bitmap);
        name.setText(tb.getTeachername());

        return customView;
    }
    public void filter(String str){
        list.clear();
        if(str.length()==0){
            list.addAll(templist);
        }else{
            for(TeacherBean tb:templist){
                if(tb.getTeachername().toString().trim().startsWith(str)){
                    list.add(tb);
                }
            }
        }
        notifyDataSetChanged();
    }

}
