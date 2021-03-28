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
                startActivityForResult(intent, 2);
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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) { // Login
            Toast.makeText(this.getApplicationContext(),"로그인 되었습니다.", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this.getApplicationContext(),"입력란을 다시 확인하세요.", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}