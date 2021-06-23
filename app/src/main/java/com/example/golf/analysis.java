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
    String imagename, Sscore,userid, add_advice1, add_advice2, add_advice3, body_sway, taway_advice, finish_advice, top_advice1, top_advice2, top_advice3, down_advice, imp_advice1,
            imp_advice2,imp_advice3 , slice_advice, thu_advice1, thu_advice2, thu_advice3, chiken_wing, top_advice4, down_advice2, worst;
    FileOutputStream fos =null;
    ImageView imageView;
    TextView score, feedback;
    int num = 0;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);
        imageView = (ImageView)findViewById(R.id.image);
        pracBtn = (Button)findViewById(R.id.pracNoteBtn);
        detailBtn = (Button)findViewById(R.id.detailBtn);
        backBtn =(ImageButton) findViewById(R.id.backBtn);
        score =(TextView) findViewById(R.id.score);
        feedback = (TextView) findViewById(R.id.comment);
        Intent intent = getIntent();
        userid = ((login)login.context_main).userid;
        imagename =  intent.getStringExtra("imagename");
        Sscore =  intent.getStringExtra("score");
        chiken_wing = intent.getStringExtra("chiken_wing");
        body_sway = intent.getStringExtra("body_sway");
        finish_advice = intent.getStringExtra("finish_advice");
        add_advice1 = intent.getStringExtra("add_advice1");
        add_advice2 = intent.getStringExtra("add_advice2");
        add_advice3 = intent.getStringExtra("add_advice3");
        taway_advice = intent.getStringExtra("taway_advice");
        top_advice1 = intent.getStringExtra("top_advice1");
        top_advice2 = intent.getStringExtra("top_advice2");
        top_advice3 = intent.getStringExtra("top_advice3");
        top_advice4 = intent.getStringExtra("top_advice4");
        down_advice = intent.getStringExtra("down_advice");
        down_advice2 = intent.getStringExtra("down_advice2");
        imp_advice1 = intent.getStringExtra("imp_advice1");
        imp_advice2 = intent.getStringExtra("imp_advice2");
        imp_advice3 = intent.getStringExtra("imp_advice3");
        slice_advice = intent.getStringExtra("slice_advice");
        thu_advice1 = intent.getStringExtra("thu_advice1");
        thu_advice2 = intent.getStringExtra("thu_advice2");
        thu_advice3 = intent.getStringExtra("thu_advice3");
        worst = intent.getStringExtra("worst");
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
                intent.putExtra("add_advice1", add_advice1);
                intent.putExtra("add_advice2", add_advice2);
                intent.putExtra("add_advice3", add_advice3);
                intent.putExtra("chiken_wing", chiken_wing);
                intent.putExtra("body_sway", body_sway);
                intent.putExtra("finish_advice", finish_advice);
                intent.putExtra("taway_advice", taway_advice);
                intent.putExtra("top_advice1", top_advice1);
                intent.putExtra("top_advice2", top_advice2);
                intent.putExtra("top_advice3", top_advice3);
                intent.putExtra("top_advice4", top_advice4);
                intent.putExtra("down_advice", down_advice);
                intent.putExtra("down_advice2", down_advice2);
                intent.putExtra("imp_advice1", imp_advice1);
                intent.putExtra("imp_advice2", imp_advice2);
                intent.putExtra("imp_advice3", imp_advice3);
                intent.putExtra("slice_advice", slice_advice);
                intent.putExtra("thu_advice1", thu_advice1);
                intent.putExtra("thu_advice2", thu_advice2);
                intent.putExtra("thu_advice3", thu_advice3);
                intent.putExtra("worst", worst);
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
        f = new File("/sdcard/" + userid +"/image/" + imagename + worst + ".jpg");
        Bitmap myBitmap = BitmapFactory.decodeFile(f.getAbsolutePath());
        imageView.setImageBitmap(myBitmap);
        if(worst.equals("0"))
        {
            feedback.setText(R.string.address);
            feedback.append("\n\n");
            if(!add_advice1.equals(""))
                feedback.append(add_advice1 + "\n\n");
            if(!add_advice2.equals(""))
                feedback.append(add_advice2 + "\n\n");
            if(!add_advice3.equals(""))
                feedback.append(add_advice3);
        }
        if(worst.equals("1"))
        {
            feedback.setText(R.string.takeAway);
            feedback.append("\n\n");
            if(!taway_advice.equals(""))
                feedback.append(taway_advice);
        }
        if(worst.equals("2"))
        {
            feedback.setText(R.string.top);
            feedback.append("\n\n");
            if(!top_advice1.equals(""))
                feedback.append(top_advice1 + "\n\n");
            if(!top_advice2.equals(""))
                feedback.append(top_advice2 + "\n\n");
            if(!top_advice3.equals(""))
                feedback.append(top_advice3 + "\n\n");
            if(!top_advice4.equals(""))
                feedback.append(top_advice4 + "\n\n");
            if(!slice_advice.equals(""))
                feedback.append(slice_advice);
        }
        if(worst.equals("3"))
        {
            feedback.setText(R.string.down );
            feedback.append("\n\n");
            if(!down_advice.equals(""))
                feedback.append(down_advice + "\n\n");
            if(!down_advice2.equals(""))
                feedback.append(down_advice2);
        }
        if(worst.equals("4"))
        {
            feedback.setText(R.string.impact);
            feedback.append("\n\n");
            if(!imp_advice1.equals(""))
                feedback.append(imp_advice1 + "\n\n");
            if(!imp_advice2.equals(""))
                feedback.append(imp_advice2 + "\n\n");
            if(!imp_advice3.equals(""))
                feedback.append(imp_advice3);
        }
        if(worst.equals("5"))
        {
            feedback.setText(R.string.followThru);
            feedback.append("\n\n");
            Log.d("asd",chiken_wing);
            if(!top_advice1.equals(""))
                feedback.append(thu_advice1 + "\n\n");
            if(!top_advice2.equals(""))
                feedback.append(thu_advice2 + "\n\n");
            if(!top_advice3.equals(""))
                feedback.append(thu_advice3 + "\n\n");
            if(!chiken_wing.equals(""))
                feedback.append(chiken_wing + "\n\n");
            if(!body_sway.equals(""))
                feedback.append(body_sway);
        }
        if(worst.equals("6"))
        {
            feedback.setText(R.string.finish);
            feedback.append("\n\n");
            if(!finish_advice.equals(""))
                feedback.append(finish_advice);
        }
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
                        intent.putExtra("add_advice1", add_advice1);
                        intent.putExtra("add_advice2", add_advice2);
                        intent.putExtra("add_advice3", add_advice3);
                        intent.putExtra("chiken_wing", chiken_wing);
                        intent.putExtra("body_sway", body_sway);
                        intent.putExtra("finish_advice", finish_advice);
                        intent.putExtra("taway_advice", taway_advice);
                        intent.putExtra("top_advice1", top_advice1);
                        intent.putExtra("top_advice2", top_advice2);
                        intent.putExtra("top_advice3", top_advice3);
                        intent.putExtra("top_advice4", top_advice4);
                        intent.putExtra("down_advice", down_advice);
                        intent.putExtra("down_advice2", down_advice2);
                        intent.putExtra("imp_advice1", imp_advice1);
                        intent.putExtra("imp_advice2", imp_advice2);
                        intent.putExtra("imp_advice3", imp_advice3);
                        intent.putExtra("slice_advice", slice_advice);
                        intent.putExtra("thu_advice1", thu_advice1);
                        intent.putExtra("thu_advice2", thu_advice2);
                        intent.putExtra("thu_advice3", thu_advice3);
                        intent.putExtra("worst", worst);
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