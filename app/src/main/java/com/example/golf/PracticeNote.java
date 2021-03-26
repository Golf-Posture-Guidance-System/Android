package com.example.golf;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class PracticeNote extends AppCompatActivity {
    private ImageButton addBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_note);
        addBtn = (ImageButton) findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //여기에 버튼 클릭시 숨겨진 버튼 두개 보이게(엘범 불러오기 촬영하기) 코딩
            }
        });
    }
}