package com.example.golf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class analysis_detail extends AppCompatActivity implements View.OnClickListener{
    private Button pracBtn, mainBtn;
    private Button poseBtn[] = new Button[7];
    private Integer [] RidBtn = {
            R.id.Btn1,R.id.Btn2,R.id.Btn3,R.id.Btn4,
            R.id.Btn5,R.id.Btn6,R.id.Btn7};

    private TextView address, takeAway, top, down, impact, followThru, finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis_detail);

        address = (TextView)findViewById(R.id.commentText);
        takeAway = (TextView)findViewById(R.id.commentText);
        top = (TextView)findViewById(R.id.commentText);
        down = (TextView)findViewById(R.id.commentText);
        impact = (TextView)findViewById(R.id.commentText);
        followThru = (TextView)findViewById(R.id.commentText);
        finish = (TextView)findViewById(R.id.commentText);

        pracBtn = (Button)findViewById(R.id.pracNoteBtn);
        mainBtn = (Button)findViewById(R.id.backToMainBtn);

        for ( int i=0;i<7;i++) {
            poseBtn[i] = (Button) findViewById(RidBtn[i]);
            poseBtn[i].setOnClickListener(this);
        }

        pracBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(analysis_detail.this,PracticeNote.class);
                startActivityForResult(intent, 2);
            }
        });
        mainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(analysis_detail.this,MainMenu.class);
                startActivityForResult(intent, 2);
            }
        });
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.Btn1:
                address.setText(R.string.address);
                break;
            case R.id.Btn2:
                takeAway.setText(R.string.takeAway);
                break;
            case R.id.Btn3:
                top.setText(R.string.top);
                break;
            case R.id.Btn4:
                down.setText(R.string.down);
                break;
            case R.id.Btn5:
                impact.setText(R.string.impact);
                break;
            case R.id.Btn6:
                followThru.setText(R.string.followThru);
                break;
            case R.id.Btn7:
                finish.setText(R.string.finish);
                break;
        }
    }

}