package com.example.golf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.navigation.NavigationView;

public class MainMenu extends AppCompatActivity {
    final DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        CardView cameraview,galleryview;
        cameraview = (CardView) findViewById(R.id.cameraSwing);
        galleryview = (CardView) findViewById(R.id.gallery);
        cameraview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, videoPreview.class);
                intent.putExtra("REQUEST_CAMERA",2);
                startActivity(intent);
                finish();
            }
        });
        galleryview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, videoPreview.class);
                intent.putExtra("PICTURE_CHOICE",3);
                startActivity(intent);
                finish();
            }
        });
        NavigationView navigationView = findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                drawerLayout.closeDrawers();

                int id = menuItem.getItemId();

                if(id == R.id.Logout){
                    Intent intent = new Intent(MainMenu.this,splash.class);
                    intent.putExtra("REQUEST_CAMERA",2);
                    startActivity(intent);
                    finish();
                }

                return true;
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.navBtn:{ // 왼쪽 상단 버튼 눌렀을 때
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}