package com.example.golf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
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

public class login extends AppCompatActivity {
     String userid ;
     String password ;
    public static Context context_main;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        ImageButton backBtn;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context_main = this;
        backBtn =(ImageButton) findViewById(R.id.backBtn);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(login.this,submain.class);
               startActivity(intent);
               finish();
            }
        });
    }

    public void submit(View v) {
        EditText useridView = findViewById(R.id.userId);
        EditText passwordView = findViewById(R.id.userPwd);
        userid = useridView.getText().toString().trim();
        password = passwordView.getText().toString().trim();

        if (userid.length() == 0 || password.length() == 0) {
            Toast.makeText(getApplicationContext(), "아이디와 패스워드를 입력하세요.", Toast.LENGTH_LONG).show();
            return;
        }
        else {
            JSONObject loginForm = new JSONObject();
            try {
                loginForm.put("subject", "login");
                loginForm.put("userid", userid);
                loginForm.put("userpwd", password);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), loginForm.toString());

            postRequest(submain.postUrl, body);
        }
    }

    public void postRequest(String postUrl, RequestBody postBody) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(postUrl)
                .post(postBody)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // Cancel the post on failure.
                call.cancel();
                Log.d("FAIL", e.getMessage());

                // In order to access the TextView inside the UI thread, the code is executed inside runOnUiThread()
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(login.this,"인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                // In order to access the TextView inside the UI thread, the code is executed inside runOnUiThread()
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String loginResponseString = response.body().string().trim();
                            Log.d("LOGIN", "Response from the server : " + loginResponseString);
                            if (loginResponseString.equals("success")) {
                                Log.d("LOGIN", "Successful Login");
                                Intent intent = new Intent(login.this,MainMenu.class);
                                startActivity(intent);
                                finish();
                            } else if (loginResponseString.equals("failure")) {
                                Toast.makeText(login.this,"아이디,비밀번호를 확인하세요. ", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }
}