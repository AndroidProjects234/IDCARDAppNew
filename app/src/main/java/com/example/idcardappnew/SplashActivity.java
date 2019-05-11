package com.example.idcardappnew;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        mainhandler.sendEmptyMessageDelayed(Utils.MSG_START, Utils.MSG_TIME);

    }
    Handler mainhandler=new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==(Utils.MSG_START))
            {
                Intent i= new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        }
    };

}
