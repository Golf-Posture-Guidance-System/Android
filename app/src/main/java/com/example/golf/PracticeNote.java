package com.example.golf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PracticeNote extends AppCompatActivity {
    private ImageButton addBtn;
    File f;
    ImageButton backBtn;
    TextView swingData1,swingData2,swingData3,swingData4,swingData5;
    CardView list1,list2,list3,list4,list5;
    String getTime;
    File savefile1,savefile2,savefile3,savefile4,savefile5;
    String imagename, score, userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_note);
        swingData1 = (TextView) findViewById(R.id.swingData1);
        swingData2 = (TextView) findViewById(R.id.swingData2);
        swingData3 = (TextView) findViewById(R.id.swingData3);
        swingData4 = (TextView) findViewById(R.id.swingData4);
        swingData5 = (TextView) findViewById(R.id.swingData5);
        list1 = (CardView)findViewById(R.id.list1);
        list2 = (CardView)findViewById(R.id.list2);
        list3 = (CardView)findViewById(R.id.list3);
        list4 = (CardView)findViewById(R.id.list4);
        list5 = (CardView)findViewById(R.id.list5);
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy년-MM월-dd일 hh시-mm분 스윙");
        getTime = sdf.format(date);
        Intent intent = getIntent() ;
        imagename =  intent.getStringExtra("imagename");
        score =  intent.getStringExtra("score");
        add();
        backBtn =(ImageButton)findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PracticeNote.this,MainMenu.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void submit(View v){

    }
    protected void add() {
        userid = ((login)login.context_main).userid;
        if(!new File("/sdcard/" + userid + "/swingData1").exists()&& score !=null)
        {
            new File("/sdcard/" + userid + "/swingData1").mkdir();
            writefile("1");

        }
        else if(!new File("/sdcard/" + userid + "/swingData2").exists()&& score !=null)
        {
            new File("/sdcard/" + userid + "/swingData2").mkdir();
            writefile("2");

        }
        else if(!new File("/sdcard/" + userid + "/swingData3").exists()&& score !=null)
        {
            new File("/sdcard/" + userid + "/swingData3").mkdir();
            writefile("3");
        }
        else if(!new File("/sdcard/" + userid + "/swingData4").exists()&& score !=null)
        {
            new File("/sdcard/" + userid + "/swingData4").mkdir();
            writefile("4");
        }
        else if(!new File("/sdcard/" + userid + "/swingData5").exists()&& score !=null)
        {

            new File("/sdcard/" + userid + "/swingData5").mkdir();
            writefile("5");
        }
        if(new File("/sdcard/" + userid + "/swingData1").exists()){

            readfile("1");
            swingData1.setText(getTime);
            list1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    readfile("1");
                    Intent intent = new Intent(PracticeNote.this, analysis.class);
                    intent.putExtra("imagename",imagename);
                    intent.putExtra("score",score);
                    startActivity(intent);
                    finish();
                }
            });

        }
        if(new File("/sdcard/" + userid + "/swingData2").exists()){
            readfile("2");
            swingData2.setText(getTime);
            list2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    readfile("2");
                    Intent intent = new Intent(PracticeNote.this, analysis.class);
                    intent.putExtra("imagename", imagename);
                    intent.putExtra("score", score);
                    startActivity(intent);
                    finish();
                }
            });
        }
        if(new File("/sdcard/" + userid + "/swingData3").exists()){
            readfile("3");
            swingData3.setText(getTime);
            list3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    readfile("3");
                    Intent intent = new Intent(PracticeNote.this, analysis.class);
                    intent.putExtra("imagename", imagename);
                    intent.putExtra("score", score);
                    startActivity(intent);
                    finish();
                }
            });
        }
        if(new File("/sdcard/" + userid + "/swingData4").exists()){
            readfile("4");
            swingData4.setText(getTime);
            list4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    readfile("4");
                    Intent intent = new Intent(PracticeNote.this, analysis.class);
                    intent.putExtra("imagename", imagename);
                    intent.putExtra("score", score);
                    startActivity(intent);
                    finish();
                }
            });
        }
        if(new File("/sdcard/" + userid + "/swingData5").exists()){
            readfile("5");
            swingData5.setText(getTime);
            list5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    readfile("4");
                    Intent intent = new Intent(PracticeNote.this, analysis.class);
                    intent.putExtra("imagename", imagename);
                    intent.putExtra("score", score);
                    startActivity(intent);
                    finish();
                }
            });
        }
    }
    protected void writefile(String num) {
        try{
            //파일 output stream 생성
            FileOutputStream fos = new FileOutputStream("/sdcard/" + userid + "/swingData"+ num + "/feedback.txt", true);
            //파일쓰기
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
            writer.write(getTime);
            writer.newLine();
            writer.write(imagename);
            writer.newLine();
            writer.write(score);
            writer.newLine();
            writer.flush();
            writer.close();
            fos.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    protected void readfile(String num) {

        try {
            BufferedReader buf = new BufferedReader(new FileReader("/sdcard/" + userid + "/swingData"+ num + "/feedback.txt"));
            getTime=buf.readLine();
            imagename=buf.readLine();
            score=buf.readLine();
            buf.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}