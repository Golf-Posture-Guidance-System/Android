package com.example.golf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class join extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
    }

    public void register(View v) {
        EditText usernameView = findViewById(R.id.userName);
        EditText useridView = findViewById(R.id.userId);
        EditText useremailView = findViewById(R.id.userEmail);
        EditText userpwdView = findViewById(R.id.userPwd);

        String username = usernameView.getText().toString().trim();
        String userid = useridView.getText().toString().trim();
        String useremail = useremailView.getText().toString().trim();
        String userpwd = userpwdView.getText().toString().trim();

        if (usernameView.length() == 0 || userid.length() == 0 || useremail.length() == 0 || userpwd.length() == 0) {
            Toast.makeText(getApplicationContext(), "모두 입력해주세요.", Toast.LENGTH_LONG).show();
        } else {
            JSONObject registrationForm = new JSONObject();
            try {
                registrationForm.put("subject", "register");
                registrationForm.put("username", username);
                registrationForm.put("userid", userid);
                registrationForm.put("useremail", useremail);
                registrationForm.put("userpwd", userpwd);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), registrationForm.toString());

            postRequest(submain.postUrl, body);
        }
    }

    public void postRequest(String postUrl, RequestBody postBody) {
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(postUrl)
                .post(postBody)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
                Log.d("FAIL", e.getMessage());

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(join.this,"인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) {
                try {
                    final String responseString = response.body().string().trim();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (responseString.equals("success")) {
                                Toast.makeText(join.this,"회원가입 되었습니다.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(join.this,login.class);
                                startActivity(intent);
                                finish();

                            } else if (responseString.equals("username")) {
                                Toast.makeText(join.this,"이미 존재하는 아이디입니다.", Toast.LENGTH_SHORT).show();
                            } else {

                            }
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}