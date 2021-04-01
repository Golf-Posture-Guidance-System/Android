package com.example.golf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainMenu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    NavigationView navigationView = findViewById(R.id.navView);
    final DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        CardView cameraview,galleryview;
        cameraview = (CardView) findViewById(R.id.cameraSwing);
        galleryview = (CardView) findViewById(R.id.gallery);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(MainMenu.this); // 리스너 설정
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
        findViewById(R.id.navBtn).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        navigationView.setNavigationItemSelectedListener(MainMenu.this);

    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.Logout) {
            Toast.makeText(MainMenu.this,"인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
        }

        //Drawer를 닫기...
        drawerLayout.closeDrawer(navigationView);

        return true;
    }
}