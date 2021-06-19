package com.example.golf;
//포즈 분석 결과 클래스
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AppComponentFactory;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import androidx.appcompat.app.AppCompatActivity;

import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class analysis extends AppCompatActivity {
    Button pracBtn, detailBtn;
    ImageButton backBtn;
    TransferUtility transferUtility;
    File f;
    String imagename, Sscore,userid;
    FileOutputStream fos =null;
    ImageView imageView;
    TextView score;
    int num = 0;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);
        imageView = (ImageView)findViewById(R.id.image);
        pracBtn = (Button)findViewById(R.id.pracNoteBtn);
        detailBtn = (Button)findViewById(R.id.detailBtn);
        backBtn =(ImageButton) findViewById(R.id.backBtn);
        score =(TextView) findViewById(R.id.score);
        Intent intent = getIntent();
        userid = ((login)login.context_main).userid;
        imagename =  intent.getStringExtra("imagename");
        Sscore =  intent.getStringExtra("score");
        pracBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                pracNote();
            }
        });
        detailBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(analysis.this,analysis_detail.class);
                intent.putExtra("imagename", imagename);
                intent.putExtra("score", Sscore);
                startActivity(intent);
                finish();
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(analysis.this,MainMenu.class);
                startActivity(intent);
                finish();
            }
        });
        analysisImage();
    }
    public void analysisImage()
    {
        score.setText(Sscore);
        String Strnum = Integer.toString(num);
        f = new File("/sdcard/" + userid +"/image/" + imagename + Strnum + ".jpg");
        Bitmap myBitmap = BitmapFactory.decodeFile(f.getAbsolutePath());
        imageView.setImageBitmap(myBitmap);
    }
    public void pracNote() {
        final CharSequence[] items = {"현재스윙 기록하기", "이전 기록내용 보기",
                "취소"};
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setTitle("연습노트");
        builder1.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result = Utility.checkPermission(getApplicationContext());

                if (items[item].equals("현재스윙 기록하기")) {
                    if (result) {
                        Intent intent = new Intent(analysis.this,PracticeNote.class);
                        intent.putExtra("imagename", imagename);
                        intent.putExtra("score", Sscore);
                        startActivity(intent);
                        finish();
                    }

                } else if (items[item].equals("이전 기록내용 보기")) {
                    if (result) {
                        Intent intent = new Intent(analysis.this,PracticeNote.class);
                        startActivity(intent);
                        finish();
                    }

                } else if (items[item].equals("취소")) {
                    dialog.dismiss();
                }
            }
        });
        AlertDialog alertDialog2 = builder1.create();
        builder1.show();
    }

}