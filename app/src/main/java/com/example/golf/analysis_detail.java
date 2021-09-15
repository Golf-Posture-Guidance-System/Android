package com.example.golf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

public class analysis_detail extends AppCompatActivity implements View.OnClickListener{

    private Button backBtn, mainBtn;
    private Button poseBtn[] = new Button[7];
    private Integer [] RidBtn = {
            R.id.Btn1,R.id.Btn2,R.id.Btn3,R.id.Btn4,
            R.id.Btn5,R.id.Btn6,R.id.Btn7};
    private ImageView pose_img;
    Intent intent;
    String userid,imagename,score, add_advice1, add_advice2, add_advice3, taway_advice, finish_advice, top_advice1, top_advice2, top_advice3, top_advice5, down_advice, imp_advice1,
            imp_advice2,imp_advice3, thu_advice1, thu_advice2, thu_advice3, thu_advice4, thu_advice5,top_advice4 , down_advice2,worst, adressscore, takebackscore, topascore,
            iascore, truascore, fscore, dscore ,finish_advice1, finish_advice2, finish_advice3;
    File f;
    Bitmap myBitmap;
    ImageView imageView;
    private TextView address, takeAway, top, down, impact, followThru, finish, detailscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis_detail);
        imageView = (ImageView)findViewById(R.id.poseimg);
        detailscore = (TextView)findViewById(R.id.detailscore);
        address = (TextView)findViewById(R.id.commentText);
        takeAway = (TextView)findViewById(R.id.commentText);
        top = (TextView)findViewById(R.id.commentText);
        down = (TextView)findViewById(R.id.commentText);
        impact = (TextView)findViewById(R.id.commentText);
        followThru = (TextView)findViewById(R.id.commentText);
        finish = (TextView)findViewById(R.id.commentText);
        backBtn = (Button)findViewById(R.id.backBtn);
        mainBtn = (Button)findViewById(R.id.backToMainBtn);
        intent = getIntent() ;
        userid = ((login)login.context_main).userid;
        adressscore =  intent.getStringExtra("adressscore");
        takebackscore =  intent.getStringExtra("takebackscore");
        topascore = intent.getStringExtra("topascore");
        dscore = intent.getStringExtra("dscore");
        iascore = intent.getStringExtra("iascore");
        truascore = intent.getStringExtra("truascore");
        fscore = intent.getStringExtra("fscore");
        imagename =  intent.getStringExtra("imagename");
        score =  intent.getStringExtra("score");
        add_advice1 = intent.getStringExtra("add_advice1");
        add_advice2 = intent.getStringExtra("add_advice2");
        add_advice3 = intent.getStringExtra("add_advice3");
        taway_advice = intent.getStringExtra("taway_advice");
        top_advice1 = intent.getStringExtra("top_advice1");
        top_advice2 = intent.getStringExtra("top_advice2");
        top_advice3 = intent.getStringExtra("top_advice3");
        top_advice4 = intent.getStringExtra("top_advice4");
        top_advice5 = intent.getStringExtra("top_advice5");
        down_advice = intent.getStringExtra("down_advice");
        down_advice2 = intent.getStringExtra("down_advice2");
        imp_advice1 = intent.getStringExtra("imp_advice1");
        imp_advice2 = intent.getStringExtra("imp_advice2");
        imp_advice3 = intent.getStringExtra("imp_advice3");
        thu_advice1 = intent.getStringExtra("thu_advice1");
        thu_advice2 = intent.getStringExtra("thu_advice2");
        thu_advice3 = intent.getStringExtra("thu_advice3");
        thu_advice4 = intent.getStringExtra("thu_advice4");
        thu_advice5 = intent.getStringExtra("thu_advice5");
        finish_advice1 = intent.getStringExtra("finish_advice1");
        finish_advice2 = intent.getStringExtra("finish_advice2");
        finish_advice3 = intent.getStringExtra("finish_advice3");
        worst = intent.getStringExtra("worst");

        for ( int i=0;i<7;i++) {
            poseBtn[i] = (Button) findViewById(RidBtn[i]);
            poseBtn[i].setOnClickListener(this);
        }
        f = new File("/sdcard/" + userid + "/image/" + imagename + '0' + ".jpg");
        myBitmap = BitmapFactory.decodeFile(f.getAbsolutePath());
        imageView.setImageBitmap(myBitmap);
        address.setText(R.string.address);
        detailscore.setText(adressscore);
        address.append("\n\n");
        if(!add_advice1.equals(""))
            address.append(add_advice1 + "\n\n");
        if(!add_advice2.equals(""))
            address.append(add_advice2 + "\n\n");
        if(!add_advice3.equals(""))
            address.append(add_advice3);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(analysis_detail.this,analysis.class);
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
                intent.putExtra("taway_advice", taway_advice);
                intent.putExtra("top_advice1", top_advice1);
                intent.putExtra("top_advice2", top_advice2);
                intent.putExtra("top_advice3", top_advice3);
                intent.putExtra("top_advice4", top_advice4);
                intent.putExtra("top_advice5", top_advice5);
                intent.putExtra("down_advice", down_advice);
                intent.putExtra("down_advice2", down_advice2);
                intent.putExtra("imp_advice1", imp_advice1);
                intent.putExtra("imp_advice2", imp_advice2);
                intent.putExtra("imp_advice3", imp_advice3);
                intent.putExtra("thu_advice1", thu_advice1);
                intent.putExtra("thu_advice2", thu_advice2);
                intent.putExtra("thu_advice3", thu_advice3);
                intent.putExtra("thu_advice4", thu_advice4);
                intent.putExtra("thu_advice5", thu_advice5);
                intent.putExtra("finish_advice1", finish_advice1);
                intent.putExtra("finish_advice2", finish_advice2);
                intent.putExtra("finish_advice3", finish_advice3);
                intent.putExtra("worst", worst);
                startActivity(intent);
                finish();
            }
        });
        mainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(analysis_detail.this,MainMenu.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.Btn1:
                f = new File("/sdcard/" + userid + "/image/" + imagename + '0' + ".jpg");
                myBitmap = BitmapFactory.decodeFile(f.getAbsolutePath());
                imageView.setImageBitmap(myBitmap);
                address.setText(R.string.address);
                detailscore.setText(adressscore);
                address.append("\n\n");
                if(!add_advice1.equals(""))
                    address.append(add_advice1 + "\n\n");
                if(!add_advice2.equals(""))
                    address.append(add_advice2 + "\n\n");
                if(!add_advice3.equals(""))
                    address.append(add_advice3);
                break;
            case R.id.Btn2:
                f = new File("/sdcard/" + userid + "/image/" + imagename + '1' + ".jpg");
                myBitmap = BitmapFactory.decodeFile(f.getAbsolutePath());
                imageView.setImageBitmap(myBitmap);
                takeAway.setText(R.string.takeAway);
                detailscore.setText(takebackscore);
                takeAway.append("\n\n");
                if(!taway_advice.equals(""))
                    takeAway.append(taway_advice);
                break;
            case R.id.Btn3:
                f = new File("/sdcard/" + userid + "/image/" +imagename + '2' + ".jpg");
                myBitmap = BitmapFactory.decodeFile(f.getAbsolutePath());
                imageView.setImageBitmap(myBitmap);
                top.setText(R.string.top);
                detailscore.setText(topascore);
                top.append("\n\n");
                if(!top_advice1.equals(""))
                    top.append(top_advice1 + "\n\n");
                if(!top_advice2.equals(""))
                    top.append(top_advice2 + "\n\n");
                if(!top_advice3.equals(""))
                    top.append(top_advice3 + "\n\n");
                if(!top_advice4.equals(""))
                    top.append(top_advice4 + "\n\n");
                if(!top_advice5.equals(""))
                    top.append(top_advice5);
                break;
            case R.id.Btn4:
                f = new File("/sdcard/" + userid + "/image/" +imagename + '3' + ".jpg");
                myBitmap = BitmapFactory.decodeFile(f.getAbsolutePath());
                imageView.setImageBitmap(myBitmap);
                down.setText(R.string.down );
                detailscore.setText(dscore);
                down.append("\n\n");
                if(!down_advice.equals(""))
                    down.append(down_advice + "\n\n");
                if(!down_advice2.equals(""))
                    down.append(down_advice2);
                break;
            case R.id.Btn5:
                f = new File("/sdcard/" + userid + "/image/" + imagename + '4' + ".jpg");
                myBitmap = BitmapFactory.decodeFile(f.getAbsolutePath());
                imageView.setImageBitmap(myBitmap);
                impact.setText(R.string.impact);
                detailscore.setText(iascore);
                impact.append("\n\n");
                if(!imp_advice1.equals(""))
                    impact.append(imp_advice1 + "\n\n");
                if(!imp_advice2.equals(""))
                    impact.append(imp_advice2 + "\n\n");
                if(!imp_advice3.equals(""))
                    impact.append(imp_advice3);
                break;
            case R.id.Btn6:
                f = new File("/sdcard/" + userid + "/image/" + imagename + '5' + ".jpg");
                myBitmap = BitmapFactory.decodeFile(f.getAbsolutePath());
                imageView.setImageBitmap(myBitmap);
                followThru.setText(R.string.followThru);
                detailscore.setText(truascore);
                followThru.append("\n\n");
                if(!thu_advice1.equals(""))
                    followThru.append(thu_advice1 + "\n\n");
                if(!thu_advice2.equals(""))
                    followThru.append(thu_advice2 + "\n\n");
                if(!thu_advice3.equals(""))
                    followThru.append(thu_advice3 + "\n\n");
                if(!thu_advice4.equals(""))
                    followThru.append(thu_advice4 + "\n\n");
                if(!thu_advice5.equals(""))
                    followThru.append(thu_advice5);
                break;
            case R.id.Btn7:
                f = new File("/sdcard/" + userid + "/image/" + imagename + '6' + ".jpg");
                myBitmap = BitmapFactory.decodeFile(f.getAbsolutePath());
                imageView.setImageBitmap(myBitmap);
                finish.setText(R.string.finish);
                detailscore.setText(fscore);
                finish.append("\n\n");
                if(!finish_advice1.equals(""))
                    finish.append(finish_advice1 + "\n\n");
                if(!finish_advice2.equals(""))
                    finish.append(finish_advice2 + "\n\n");
                if(!finish_advice3.equals(""))
                    finish.append(finish_advice3);
                break;
        }
    }

}