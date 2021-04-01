package com.example.golf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.amazonaws.Response;

import org.json.JSONObject;

public class submain extends AppCompatActivity {
    Button loginBtn, joinBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submain);
        loginBtn = (Button)findViewById(R.id.loginBtn);
        joinBtn = (Button)findViewById(R.id.joinBtn);

        loginBtn.setOnClickListener(new View.OnClickListener(){
          @Override
          public void onClick(View v){
              Intent intent = new Intent(submain.this,login.class);
              startActivity(intent);
              finish();
          }
        });

        joinBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(submain.this,join.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");

                    if (success) {//로그인 성공시

                        String userID = jsonObject.getString("userID");
                        String userPass = jsonObject.getString("userPassword");
                        String userName = jsonObject.getString("userName");
                        String userAge = jsonObject.getString("userAge");

                        Toast.makeText(getApplicationContext(), "로그인 성공", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                        intent.putExtra("userID", userID);
                        intent.putExtra("userPass", userPass);
                        intent.putExtra("userName", userName);
                        intent.putExtra("userAge", userAge);

                        startActivity(intent);

                    } else {//로그인 실패시
                        Toast.makeText(getApplicationContext(), "로그인 실패", Toast.LENGTH_SHORT).show();
                        return;
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }