package com.example.golf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

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

public class MainMenu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    String username;
    TextView user_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        CardView cameraview,galleryview,prac_view;
        username = ((login)login.context_main).username;
        user_name = (TextView)findViewById(R.id.userId);
        user_name.setText(username);
        cameraview = (CardView) findViewById(R.id.cameraSwing);

        cameraview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, videoPreview.class);
                intent.putExtra("REQUEST_CAMERA",2);
                startActivity(intent);
                finish();
            }
        });
        galleryview = (CardView) findViewById(R.id.gallery);
        galleryview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, videoPreview.class);
                intent.putExtra("PICTURE_CHOICE",3);
                startActivity(intent);
                finish();
            }
        });
        prac_view = (CardView)findViewById(R.id.pracNoteBtn);
        prac_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this,PracticeNote.class);
                startActivity(intent);
                finish();
            }
        });

        navigationView = findViewById(R.id.navView);

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(MainMenu.this);

        findViewById(R.id.navBtn).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        //아이디 변경

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.Logout) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            final CharSequence[] items = {"예", "취소",
            };
            builder1.setTitle("로그아웃 하시겠습니까?");
            builder1.setItems(items, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int item) {
                    if (items[item].equals("예")) {
                        Intent intent = new Intent(MainMenu.this, submain.class);
                        startActivity(intent);
                        finish();
                    } else if (items[item].equals("취소")) {
                        dialog.dismiss();
                    }
                }
            });
            AlertDialog alertDialog2 = builder1.create();
            builder1.show();
        }

        //Drawer를 닫기...
        drawerLayout.closeDrawer(navigationView);

        return true;
    }

}