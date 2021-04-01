package com.example.golf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class submain extends AppCompatActivity {
    private Button loginBtn, joinBtn,findPwd;
    public static Context mainActivityContext;
    static String postUrl = "http://54.180.114.13:5000/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submain);
        loginBtn = (Button)findViewById(R.id.loginBtn);
        joinBtn = (Button)findViewById(R.id.joinBtn);
        findPwd =(Button)findViewById(R.id.findPwd);

        ActivityCompat.requestPermissions(submain.this, new String[]{Manifest.permission.INTERNET}, 0);
        mainActivityContext = this;
        loginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(submain.this,login.class);
                startActivity(intent);
                finish();
            }

        });

        joinBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(submain.this,join.class);
                startActivity(intent);
                finish();
            }
        });

        findPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(submain.this,analysis.class);
                startActivity(intent);
                finish();
            }
        });

    }
}