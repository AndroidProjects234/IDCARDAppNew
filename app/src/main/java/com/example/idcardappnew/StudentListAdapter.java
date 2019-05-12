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

public class StudentListAdapter extends ArrayAdapter {
    ArrayList<StudentBean>list;
    public static ArrayList<StudentBean>templist;

    public StudentListAdapter(Context context,  ArrayList<StudentBean> list) {
        super(context, R.layout.activity_studentlistadapter,list);
        this.list=list;
        templist=new ArrayList<>();
        templist.addAll(list);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= LayoutInflater.from(getContext());
        View customView=inflater.inflate(R.layout.activity_studentlistadapter,parent,false);
        CircularImageView image=(CircularImageView)customView.findViewById(R.id.StudentListImage);
        TextView name=(TextView)customView.findViewById(R.id.StudentListName);
        TextView id=(TextView)customView.findViewById(R.id.StudentListId);
        StudentBean sb=new StudentBean();
        sb=list.get(position);
        byte[] studentimage=sb.getStudentimage();
        Bitmap bitmap= BitmapFactory.decodeByteArray(studentimage,0,studentimage.length);
        image.setImageBitmap(bitmap);
        name.setText(sb.getStudentname());
        id.setText(sb.getStudentid());
        return customView;
    }
    public void filter(String str){
        list.clear();
        if(str.length()==0){
            list.addAll(templist);
        }else{
            for(StudentBean sb:templist){
                if(sb.getStudentname().toString().trim().startsWith(str)||sb.getStudentid().startsWith(str)){
                    list.add(sb);
                }
            }
        }
        notifyDataSetChanged();
    }

}
