package com.example.golf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class PracticeNote extends AppCompatActivity {
    private Button addBtn;
    private ImageButton backBtn;
    private CardView cardView[] = new CardView[5];
    private TextView prac_text[] = new TextView[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_note);


        addBtn = (Button) findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PracticeNote.this,MainMenu.class);
                startActivity(intent);
                finish();
            }
        });
        backBtn =(ImageButton) findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PracticeNote.this,MainMenu.class);
                startActivity(intent);
                finish();
            }
        });

        String str_cardview_id = "list";
        String str_text_id = "swingData";
        for (int i =0 ;i<5;i++){
            String num = Integer.toString(i+1);
            str_cardview_id = str_cardview_id + num;
            str_text_id =  str_text_id + num;

            int card_id = getResources().getIdentifier(str_cardview_id,"id",getPackageName());
            int text_id = getResources().getIdentifier(str_text_id,"id",getPackageName());

            cardView[i] = (CardView) findViewById(card_id);
            prac_text[i] = (TextView) findViewById(text_id);
        }
        prac_text[0].setText("5월 6일 11시 스윙");
    }
}