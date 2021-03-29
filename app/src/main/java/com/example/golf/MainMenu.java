package com.example.golf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.google.android.material.navigation.NavigationView;

public class MainMenu extends AppCompatActivity {
    public static Context mcontext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        CardView cameraview,galleryview;
        cameraview = (CardView) findViewById(R.id.cameraSwing);
        galleryview = (CardView) findViewById(R.id.gallery);
        mcontext = this;
        cameraview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((splash)MainMenu.mcontext).cameraIntent();
                finish();
            }
        });
        galleryview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((splash)MainMenu.mcontext).galleryIntent();
                finish();
            }
        });
        final DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);

        findViewById(R.id.navBtn).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        NavigationView navigationView = findViewById(R.id.navView);
        navigationView.setItemIconTintList(null);

    }
}