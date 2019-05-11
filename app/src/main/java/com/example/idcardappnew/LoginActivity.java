package com.example.idcardappnew;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText editUsername,editPassword;
    Button login;

    SharedPreferences sharedPrefs;
    SharedPreferences.Editor editor;
  //  public static final String PREF_NAME="LoginInfo";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        saveInfo();
        editUsername=(EditText)findViewById(R.id.editUsername);
        editPassword=(EditText)findViewById(R.id.editPassword);
        login=(Button)findViewById(R.id.ButtonLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=sharedPrefs.getString("Username","");
                String password=sharedPrefs.getString("Password","");
                if(editUsername.getText().toString().equals(name) && editPassword.getText().toString().equals(password)){
                    Intent i=new Intent(LoginActivity.this,MainListActivity.class);
                    startActivity(i);
                    finish();

                }else {
                    editUsername.setText("");
                    editPassword.setText("");
                    Toast.makeText(LoginActivity.this,"Login Details are not Correct",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void saveInfo(){
        sharedPrefs=getSharedPreferences(CheckActivity.PREF_NAME,MODE_PRIVATE);

    }
}
