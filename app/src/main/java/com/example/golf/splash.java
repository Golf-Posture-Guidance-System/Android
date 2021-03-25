package com.example.golf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;

public class splash extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Handler mHandler = new Handler();
        setContentView(R.layout.activity_splash);
        mHandler.postDelayed(new Runnable() {
            public void run() {
                Intent intent = new Intent(splash.this,submain.class);
                startActivity(intent);
                finish();

            }
        },1000);


    }
}