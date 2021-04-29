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

    private TextView commentText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis_detail);

        pracBtn = (Button)findViewById(R.id.pracNoteBtn);
        mainBtn = (Button)findViewById(R.id.backToMainBtn);
        for ( int i=0;i<7;i++) {
            poseBtn[i] = (Button) findViewById(RidBtn[i]);
            poseBtn[i].setOnClickListener(this);
        }
        commentText = (TextView)findViewById(R.id.commentText);
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
                //이미지 뷰를 어드래스 이미지로 표기
                //텍스트를 해당 버튼에 맞게 수정.
                commentText.setText("지피지기팁:어드래스삼각형어쩌구.");
                break;
            case R.id.Btn2:
                //이미지 뷰를 어드래스 이미지로 표기
                commentText.setText("지피지기팁:좋은 백스윙은 채가 일자가 되어야합니다.");
                break;
            case R.id.Btn3:
                break;
        }
    }

}