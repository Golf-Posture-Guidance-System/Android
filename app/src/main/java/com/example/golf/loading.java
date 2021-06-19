package com.example.golf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;

import java.io.File;

public class loading extends AppCompatActivity {
    TextView point;
    ImageView loading;
    AnimationDrawable animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        Intent intent = getIntent() ;
        loading = findViewById(R.id.loading);
        animation = new AnimationDrawable();
        animation.addFrame(getResources().getDrawable(R.drawable.ic_loading1),500);
        animation.addFrame(getResources().getDrawable(R.drawable.ic_loading2),500);
        loading.setImageDrawable(animation);
        animation.start();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setResult(RESULT_OK,intent);
                finish();
                }
        }, 34000); //딜레이 타임 조절
    }
}