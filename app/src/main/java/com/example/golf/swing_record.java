package com.example.golf;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ToggleButton;

public class swing_record extends AppCompatActivity {
    CountDownTimer swingTimer;
    TextView time_text;
    ProgressBar progressBar;
    int i = 0;
    final int TOTALTIME = 30000;
    final int COUNT_DOWN_INTERVAL = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swing_record);

        final ToggleButton tbtn = (ToggleButton) this.findViewById(R.id.recordBtn);

        tbtn.setOnClickListener(v -> {
            if(tbtn.isChecked()){ //화면 녹화 시작
                tbtn.setBackgroundDrawable(
                        getResources().getDrawable(R.drawable.ic_stop)
                );
                swingTimer();
            }else{ //화면 녹화 중지
                tbtn.setBackgroundDrawable(
                        getResources().getDrawable(R.drawable.ic_start)

                );
                swingTimer.cancel();
            }
        });
    }

    public void swingTimer(){

        time_text = (TextView) findViewById(R.id.countDown);
        progressBar = (ProgressBar)findViewById(R.id.progress);
        progressBar.setProgress(i);
        swingTimer = new CountDownTimer(TOTALTIME, COUNT_DOWN_INTERVAL) {
            @Override
            public void onTick(long millisUntilFinished) {
                i++;
                time_text.setText(millisUntilFinished / 1000+"초");
                progressBar.setProgress((int)i*100/(TOTALTIME/COUNT_DOWN_INTERVAL));
            }
            @Override
            public void onFinish() { //시간이 다 되면 자동 녹화종료 -> 화면 넘어감
                i++;
                progressBar.setProgress(100);

            }
        }.start();
    }





}