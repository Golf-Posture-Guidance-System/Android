package com.example.golf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
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
    String userid,imagename,score;
    File f;
    Bitmap myBitmap;
    ImageView imageView;
    private TextView address, takeAway, top, down, impact, followThru, finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis_detail);
        imageView = (ImageView)findViewById(R.id.poseimg);
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
        imagename =  intent.getStringExtra("imagename") ;
        score =  intent.getStringExtra("score");
        for ( int i=0;i<7;i++) {
            poseBtn[i] = (Button) findViewById(RidBtn[i]);
            poseBtn[i].setOnClickListener(this);
        }

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(analysis_detail.this,analysis.class);
                intent.putExtra("imagename", imagename);
                intent.putExtra("score", score);
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
                break;
            case R.id.Btn2:
                f = new File("/sdcard/" + userid + "/image/" + imagename + '1' + ".jpg");
                myBitmap = BitmapFactory.decodeFile(f.getAbsolutePath());
                imageView.setImageBitmap(myBitmap);
                takeAway.setText(R.string.takeAway);
                break;
            case R.id.Btn3:
                f = new File("/sdcard/" + userid + "/image/" +imagename + '2' + ".jpg");
                myBitmap = BitmapFactory.decodeFile(f.getAbsolutePath());
                imageView.setImageBitmap(myBitmap);
                top.setText(R.string.top);
                break;
            case R.id.Btn4:
                f = new File("/sdcard/" + userid + "/image/" +imagename + '3' + ".jpg");
                myBitmap = BitmapFactory.decodeFile(f.getAbsolutePath());
                imageView.setImageBitmap(myBitmap);
                down.setText(R.string.down);
                break;
            case R.id.Btn5:
                f = new File("/sdcard/" + userid + "/image/" + imagename + '4' + ".jpg");
                myBitmap = BitmapFactory.decodeFile(f.getAbsolutePath());
                imageView.setImageBitmap(myBitmap);
                impact.setText(R.string.impact);
                break;
            case R.id.Btn6:
                f = new File("/sdcard/" + userid + "/image/" + imagename + '5' + ".jpg");
                myBitmap = BitmapFactory.decodeFile(f.getAbsolutePath());
                imageView.setImageBitmap(myBitmap);
                followThru.setText(R.string.followThru);
                break;
            case R.id.Btn7:
                f = new File("/sdcard/" + userid + "/image/" + imagename + '6' + ".jpg");
                myBitmap = BitmapFactory.decodeFile(f.getAbsolutePath());
                imageView.setImageBitmap(myBitmap);
                finish.setText(R.string.finish);
                break;
        }
    }

}