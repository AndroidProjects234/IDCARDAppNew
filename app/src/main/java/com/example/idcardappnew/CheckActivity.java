package com.example.idcardappnew;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class CheckActivity extends AppCompatActivity {
    public static final String PREF_NAME="LoginInfo";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences settings=getSharedPreferences(PREF_NAME,0);
        boolean firstRun=settings.getBoolean("firstRun",false);
        if(firstRun==false)//if running for first time
        //Splash will load for first time
        {
            SharedPreferences.Editor editor=settings.edit();
            editor.putBoolean("firstRun",true);
            editor.putString("Username","admin");
            editor.putString("Password","admin");
            editor.commit();
            Intent i=new Intent(CheckActivity.this,SplashActivity.class);
            startActivity(i);
            finish();
        }
        else
        {

            Intent a=new Intent(CheckActivity.this, LoginActivity.class);
            startActivity(a);
            finish();
        }

    }
}
