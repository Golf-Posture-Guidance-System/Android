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
    String imagename, score, userid,  add_advice1, add_advice2, add_advice3, body_sway, taway_advice, finish_advice, top_advice1, top_advice2, top_advice3, down_advice, imp_advice1,
            imp_advice2, imp_advice3 , slice_advice, thu_advice1, thu_advice2, thu_advice3, chiken_wing, top_advice4, down_advice2 ,worst, adressscore, takebackscore, topascore,
            iascore, truascore, fscore, dscore ;
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
        adressscore =  intent.getStringExtra("adressscore");
        takebackscore =  intent.getStringExtra("takebackscore");
        topascore = intent.getStringExtra("topascore");
        dscore = intent.getStringExtra("dscore");
        iascore = intent.getStringExtra("iascore");
        truascore = intent.getStringExtra("truascore");
        fscore = intent.getStringExtra("fscore");
        imagename =  intent.getStringExtra("imagename");
        score =  intent.getStringExtra("score");
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
                    intent.putExtra("adressscore", adressscore);
                    intent.putExtra("takebackscore", takebackscore);
                    intent.putExtra("topascore", topascore);
                    intent.putExtra("dscore", dscore);
                    intent.putExtra("iascore", iascore);
                    intent.putExtra("truascore", truascore);
                    intent.putExtra("fscore", fscore);
                    intent.putExtra("imagename",imagename);
                    intent.putExtra("score",score);
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

        }
        if(new File("/sdcard/" + userid + "/swingData2").exists()){
            readfile("2");
            swingData2.setText(getTime);
            list2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    readfile("2");
                    Intent intent = new Intent(PracticeNote.this, analysis.class);
                    intent.putExtra("adressscore", adressscore);
                    intent.putExtra("takebackscore", takebackscore);
                    intent.putExtra("topascore", topascore);
                    intent.putExtra("dscore", dscore);
                    intent.putExtra("iascore", iascore);
                    intent.putExtra("truascore", truascore);
                    intent.putExtra("fscore", fscore);
                    intent.putExtra("imagename", imagename);
                    intent.putExtra("score", score);
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
        }
        if(new File("/sdcard/" + userid + "/swingData3").exists()){
            readfile("3");
            swingData3.setText(getTime);
            list3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    readfile("3");
                    Intent intent = new Intent(PracticeNote.this, analysis.class);
                    intent.putExtra("adressscore", adressscore);
                    intent.putExtra("takebackscore", takebackscore);
                    intent.putExtra("topascore", topascore);
                    intent.putExtra("dscore", dscore);
                    intent.putExtra("iascore", iascore);
                    intent.putExtra("truascore", truascore);
                    intent.putExtra("fscore", fscore);
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
        }
        if(new File("/sdcard/" + userid + "/swingData4").exists()){
            readfile("4");
            swingData4.setText(getTime);
            list4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    readfile("4");
                    Intent intent = new Intent(PracticeNote.this, analysis.class);
                    intent.putExtra("adressscore", adressscore);
                    intent.putExtra("takebackscore", takebackscore);
                    intent.putExtra("topascore", topascore);
                    intent.putExtra("dscore", dscore);
                    intent.putExtra("iascore", iascore);
                    intent.putExtra("truascore", truascore);
                    intent.putExtra("fscore", fscore);
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
        }
        if(new File("/sdcard/" + userid + "/swingData5").exists()){
            readfile("5");
            swingData5.setText(getTime);
            list5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    readfile("5");
                    Intent intent = new Intent(PracticeNote.this, analysis.class);
                    intent.putExtra("adressscore", adressscore);
                    intent.putExtra("takebackscore", takebackscore);
                    intent.putExtra("topascore", topascore);
                    intent.putExtra("dscore", dscore);
                    intent.putExtra("iascore", iascore);
                    intent.putExtra("truascore", truascore);
                    intent.putExtra("fscore", fscore);
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
            writer.write(worst);
            writer.newLine();
            writer.flush();
            writer.close();
            fos.close();
            FileOutputStream fos1 = new FileOutputStream("/sdcard/" + userid + "/swingData"+ num + "/adressfeedback.txt", true);
            //파일쓰기
            BufferedWriter writer1 = new BufferedWriter(new OutputStreamWriter(fos1));
            writer1.write(add_advice1);
            writer1.newLine();
            writer1.write(add_advice2);
            writer1.newLine();
            writer1.write(add_advice3);
            writer1.newLine();
            writer1.write(adressscore);
            writer1.newLine();
            writer1.flush();
            writer1.close();
            fos1.close();
            FileOutputStream fos2 = new FileOutputStream("/sdcard/" + userid + "/swingData"+ num + "/takebackfeedback.txt", true);
            //파일쓰기
            BufferedWriter writer2 = new BufferedWriter(new OutputStreamWriter(fos2));
            writer2.write(taway_advice);
            writer2.newLine();
            writer2.write(takebackscore);
            writer2.newLine();
            writer2.flush();
            writer2.close();
            fos2.close();
            FileOutputStream fos3 = new FileOutputStream("/sdcard/" + userid + "/swingData"+ num + "/topfeedback.txt", true);
            //파일쓰기
            BufferedWriter writer3 = new BufferedWriter(new OutputStreamWriter(fos3));
            writer3.write(top_advice1);
            writer3.newLine();
            writer3.write(top_advice2);
            writer3.newLine();
            writer3.write(top_advice3);
            writer3.newLine();
            writer3.write(top_advice4);
            writer3.newLine();
            writer3.write(slice_advice);
            writer3.newLine();
            writer3.write(topascore);
            writer3.newLine();
            writer3.flush();
            writer3.close();
            fos3.close();
            FileOutputStream fos4 = new FileOutputStream("/sdcard/" + userid + "/swingData"+ num + "/downfeedback.txt", true);
            //파일쓰기
            BufferedWriter writer4 = new BufferedWriter(new OutputStreamWriter(fos4));
            writer4.write(down_advice);
            writer4.newLine();
            writer4.write(down_advice2);
            writer4.newLine();
            writer4.write(dscore);
            writer4.newLine();
            writer4.flush();
            writer4.close();
            fos4.close();
            FileOutputStream fos5 = new FileOutputStream("/sdcard/" + userid + "/swingData"+ num + "/impactback.txt", true);
            //파일쓰기
            BufferedWriter writer5 = new BufferedWriter(new OutputStreamWriter(fos5));
            writer5.write(imp_advice1);
            writer5.newLine();
            writer5.write(imp_advice2);
            writer5.newLine();
            writer5.write(imp_advice3);
            writer5.newLine();
            writer5.write(iascore);
            writer5.newLine();
            writer5.flush();
            writer5.close();
            fos5.close();
            FileOutputStream fos6 = new FileOutputStream("/sdcard/" + userid + "/swingData"+ num + "/followfeedback.txt", true);
            //파일쓰기
            BufferedWriter writer6 = new BufferedWriter(new OutputStreamWriter(fos6));
            writer6.write(thu_advice1);
            writer6.newLine();
            writer6.write(thu_advice2);
            writer6.newLine();
            writer6.write(thu_advice3);
            writer6.newLine();
            writer6.write(body_sway);
            writer6.newLine();
            writer6.write(chiken_wing);
            writer6.newLine();
            writer6.write(truascore);
            writer6.newLine();
            writer6.flush();
            writer6.close();
            fos6.close();
            FileOutputStream fos7 = new FileOutputStream("/sdcard/" + userid + "/swingData"+ num + "/finishfeedback.txt", true);
            //파일쓰기
            BufferedWriter writer7 = new BufferedWriter(new OutputStreamWriter(fos7));
            writer7.write(finish_advice);
            writer7.newLine();
            writer7.write(fscore);
            writer7.newLine();
            writer7.flush();
            writer7.close();
            fos7.close();
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
            worst=buf.readLine();
            buf.close();

            BufferedReader buf1 = new BufferedReader(new FileReader("/sdcard/" + userid + "/swingData"+ num + "/adressfeedback.txt"));
            add_advice1=buf1.readLine();
            add_advice2=buf1.readLine();
            add_advice3=buf1.readLine();
            adressscore=buf1.readLine();
            buf1.close();
            BufferedReader buf2 = new BufferedReader(new FileReader("/sdcard/" + userid + "/swingData"+ num + "/takebackfeedback.txt"));
            taway_advice=buf2.readLine();
            takebackscore=buf2.readLine();
            buf2.close();

            BufferedReader buf3 = new BufferedReader(new FileReader("/sdcard/" + userid + "/swingData"+ num + "/topfeedback.txt"));
            top_advice1=buf3.readLine();
            top_advice2=buf3.readLine();
            top_advice3=buf3.readLine();
            top_advice4=buf3.readLine();
            slice_advice=buf3.readLine();
            topascore=buf3.readLine();
            buf3.close();

            BufferedReader buf4 = new BufferedReader(new FileReader("/sdcard/" + userid + "/swingData"+ num + "/downfeedback.txt"));
            down_advice=buf4.readLine();
            down_advice2=buf4.readLine();
            dscore=buf4.readLine();
            buf4.close();

            BufferedReader buf5 = new BufferedReader(new FileReader("/sdcard/" + userid + "/swingData"+ num + "/impactback.txt"));
            imp_advice1=buf5.readLine();
            imp_advice2=buf5.readLine();
            imp_advice3=buf5.readLine();
            iascore=buf5.readLine();
            buf5.close();

            BufferedReader buf6 = new BufferedReader(new FileReader("/sdcard/" + userid + "/swingData"+ num + "/followfeedback.txt"));
            thu_advice1=buf6.readLine();
            thu_advice2=buf6.readLine();
            thu_advice3=buf6.readLine();
            body_sway=buf6.readLine();
            chiken_wing=buf6.readLine();
            truascore=buf6.readLine();
            buf6.close();

            BufferedReader buf7 = new BufferedReader(new FileReader("/sdcard/" + userid + "/swingData"+ num + "/finishfeedback.txt"));
            finish_advice=buf7.readLine();
            fscore=buf7.readLine();
            buf7.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}