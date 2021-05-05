package com.example.golf;
//포즈 분석 결과 클래스
import android.app.AppComponentFactory;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import androidx.appcompat.app.AppCompatActivity;

import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;

import java.io.File;
import java.io.FileOutputStream;

public class analysis extends AppCompatActivity {
    Button pracBtn, detailBtn;
    ImageButton backBtn;
    TransferUtility transferUtility;
    File f;
    String imagename;
    FileOutputStream fos =null;
    ImageView imageView;
    int num = 0;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);
        imageView = (ImageView)findViewById(R.id.image);
        pracBtn = (Button)findViewById(R.id.pracNoteBtn);
        detailBtn = (Button)findViewById(R.id.detailBtn);
        backBtn =(ImageButton) findViewById(R.id.backBtn);

        pracBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(analysis.this,PracticeNote.class);
                startActivityForResult(intent, 2);
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(analysis.this,videoPreview.class);
                startActivity(intent);
                finish();
            }
        });
        detailBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(analysis.this,analysis_detail.class);
                intent.putExtra("imagename", imagename) ;
                startActivityForResult(intent, 2);
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(analysis.this,MainMenu.class);
                startActivityForResult(intent, 2);
            }
        });
        analysisImage();
    }
    public void analysisImage()
    {
        Intent intent = getIntent() ;
        String userid = ((login)login.context_main).userid;
        imagename =  intent.getStringExtra("imagename") ;
        String Strnum = Integer.toString(num);
        f = new File("/sdcard/" + userid, imagename + Strnum + ".jpg");
        Bitmap myBitmap = BitmapFactory.decodeFile(f.getAbsolutePath());
        imageView.setImageBitmap(myBitmap);

    }
}
