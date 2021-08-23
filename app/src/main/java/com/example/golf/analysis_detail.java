package com.example.golf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

public class analysis_detail extends AppCompatActivity implements View.OnClickListener{

    private Button backBtn, mainBtn;
    private Button poseBtn[] = new Button[7];
    private Integer [] RidBtn = {
            R.id.Btn1,R.id.Btn2,R.id.Btn3,R.id.Btn4,
            R.id.Btn5,R.id.Btn6,R.id.Btn7};
    private ImageView pose_img;
    Intent intent;
    String userid,imagename,score, add_advice1, add_advice2, add_advice3, body_sway, taway_advice, finish_advice, top_advice1, top_advice2, top_advice3, down_advice, imp_advice1,
            imp_advice2,imp_advice3 , slice_advice, thu_advice1, thu_advice2, thu_advice3, chiken_wing, top_advice4 , down_advice2,worst, adressscore, takebackscore, topascore,
            iascore, truascore, fscore, dscore ;
    File f;
    Bitmap myBitmap;
    ImageView imageView;
    private TextView address, takeAway, top, down, impact, followThru, finish, detailscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis_detail);
        imageView = (ImageView)findViewById(R.id.poseimg);
        detailscore = (TextView)findViewById(R.id.detailscore);
        address = (TextView)findViewById(R.id.commentText);
        takeAway = (TextView)findViewById(R.id.commentText);
        top = (TextView)findViewById(R.id.commentText);
        down = (TextView)findViewById(R.id.commentText);
        impact = (TextView)findViewById(R.id.commentText);
        followThru = (TextView)findViewById(R.id.commentText);
        finish = (TextView)findViewById(R.id.commentText);
        backBtn = (Button)findViewById(R.id.backBtn);
        mainBtn = (Button)findViewById(R.id.backToMainBtn);
        intent = getIntent() ;
        userid = ((login)login.context_main).userid;
        adressscore =  intent.getStringExtra("adressscore");
        takebackscore =  intent.getStringExtra("takebackscore");
        topascore = intent.getStringExtra("topascore");
        dscore = intent.getStringExtra("dscore");
        iascore = intent.getStringExtra("iascore");
        truascore = intent.getStringExtra("truascore");
        fscore = intent.getStringExtra("fscore");
        imagename =  intent.getStringExtra("imagename") ;
        score =  intent.getStringExtra("score");
        chiken_wing = intent.getStringExtra("chiken_wing");
        body_sway = intent.getStringExtra("body_sway");
        finish_advice = intent.getStringExtra("finish_advice");
        add_advice1 = intent.getStringExtra("add_advice1");
        add_advice2 = intent.getStringExtra("add_advice2");
        add_advice3 = intent.getStringExtra("add_advice3");
        taway_advice = intent.getStringExtra("taway_advice");
        top_advice1 = intent.getStringExtra("top_advice1");
        top_advice2 = intent.getStringExtra("top_advice2");
        top_advice3 = intent.getStringExtra("top_advice3");
        top_advice4 = intent.getStringExtra("top_advice4");
        down_advice = intent.getStringExtra("down_advice");
        down_advice2 = intent.getStringExtra("down_advice2");
        imp_advice1 = intent.getStringExtra("imp_advice1");
        imp_advice2 = intent.getStringExtra("imp_advice2");
        imp_advice3 = intent.getStringExtra("imp_advice3");
        slice_advice = intent.getStringExtra("slice_advice");
        thu_advice1 = intent.getStringExtra("thu_advice1");
        thu_advice2 = intent.getStringExtra("thu_advice2");
        thu_advice3 = intent.getStringExtra("thu_advice3");
        worst = intent.getStringExtra("worst");

        for ( int i=0;i<7;i++) {
            poseBtn[i] = (Button) findViewById(RidBtn[i]);
            poseBtn[i].setOnClickListener(this);
        }
        f = new File("/sdcard/" + userid + "/image/" + imagename + '0' + ".jpg");
        myBitmap = BitmapFactory.decodeFile(f.getAbsolutePath());
        imageView.setImageBitmap(myBitmap);
        address.setText(R.string.address);
        detailscore.setText(adressscore);
        address.append("\n\n");
        if(!add_advice1.equals(""))
            address.append(add_advice1 + "\n\n");
        if(!add_advice2.equals(""))
            address.append(add_advice2 + "\n\n");
        if(!add_advice3.equals(""))
            address.append(add_advice3);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(analysis_detail.this,analysis.class);
                intent.putExtra("adressscore", adressscore);
                intent.putExtra("takebackscore", takebackscore);
                intent.putExtra("topascore", topascore);
                intent.putExtra("dscore", dscore);
                intent.putExtra("iascore", iascore);
                intent.putExtra("truascore", truascore);
                intent.putExtra("fscore", fscore);
                intent.putExtra("imagename", imagename);
                intent.putExtra("imagename", imagename);
                intent.putExtra("score", score);
                intent.putExtra("add_advice1", add_advice1);
                intent.putExtra("add_advice2", add_advice2);
                intent.putExtra("add_advice3", add_advice3);
                intent.putExtra("chiken_wing", chiken_wing);
                intent.putExtra("body_sway", body_sway);
                intent.putExtra("finish_advice", finish_advice);
                intent.putExtra("taway_advice", taway_advice);
                intent.putExtra("top_advice1", top_advice1);
                intent.putExtra("top_advice2", top_advice2);
                intent.putExtra("top_advice3", top_advice3);
                intent.putExtra("top_advice4", top_advice4);
                intent.putExtra("down_advice", down_advice);
                intent.putExtra("down_advice2", down_advice2);
                intent.putExtra("imp_advice1", imp_advice1);
                intent.putExtra("imp_advice2", imp_advice2);
                intent.putExtra("imp_advice3", imp_advice3);
                intent.putExtra("slice_advice", slice_advice);
                intent.putExtra("thu_advice1", thu_advice1);
                intent.putExtra("thu_advice2", thu_advice2);
                intent.putExtra("thu_advice3", thu_advice3);
                intent.putExtra("worst", worst);
                startActivity(intent);
                finish();
            }
        });
        mainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(analysis_detail.this,MainMenu.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.Btn1:
                f = new File("/sdcard/" + userid + "/image/" + imagename + '0' + ".jpg");
                myBitmap = BitmapFactory.decodeFile(f.getAbsolutePath());
                imageView.setImageBitmap(myBitmap);
                address.setText(R.string.address);
                detailscore.setText(adressscore);
                address.append("\n\n");
                if(!add_advice1.equals(""))
                    address.append(add_advice1 + "\n\n");
                if(!add_advice2.equals(""))
                    address.append(add_advice2 + "\n\n");
                if(!add_advice3.equals(""))
                    address.append(add_advice3);
                break;
            case R.id.Btn2:
                f = new File("/sdcard/" + userid + "/image/" + imagename + '1' + ".jpg");
                myBitmap = BitmapFactory.decodeFile(f.getAbsolutePath());
                imageView.setImageBitmap(myBitmap);
                takeAway.setText(R.string.takeAway);
                detailscore.setText(takebackscore);
                takeAway.append("\n\n");
                if(!taway_advice.equals(""))
                    takeAway.append(taway_advice);
                break;
            case R.id.Btn3:
                f = new File("/sdcard/" + userid + "/image/" +imagename + '2' + ".jpg");
                myBitmap = BitmapFactory.decodeFile(f.getAbsolutePath());
                imageView.setImageBitmap(myBitmap);
                top.setText(R.string.top);
                detailscore.setText(topascore);
                top.append("\n\n");
                if(!top_advice1.equals(""))
                    top.append(top_advice1 + "\n\n");
                if(!top_advice2.equals(""))
                    top.append(top_advice2 + "\n\n");
                if(!top_advice3.equals(""))
                    top.append(top_advice3 + "\n\n");
                if(!top_advice4.equals(""))
                    top.append(top_advice4 + "\n\n");
                if(!slice_advice.equals(""))
                    top.append(slice_advice);
                break;
            case R.id.Btn4:
                f = new File("/sdcard/" + userid + "/image/" +imagename + '3' + ".jpg");
                myBitmap = BitmapFactory.decodeFile(f.getAbsolutePath());
                imageView.setImageBitmap(myBitmap);
                down.setText(R.string.down );
                detailscore.setText(dscore);
                down.append("\n\n");
                if(!down_advice.equals(""))
                    down.append(down_advice + "\n\n");
                if(!down_advice2.equals(""))
                    down.append(down_advice2);
                break;
            case R.id.Btn5:
                f = new File("/sdcard/" + userid + "/image/" + imagename + '4' + ".jpg");
                myBitmap = BitmapFactory.decodeFile(f.getAbsolutePath());
                imageView.setImageBitmap(myBitmap);
                impact.setText(R.string.impact);
                detailscore.setText(iascore);
                impact.append("\n\n");
                if(!imp_advice1.equals(""))
                    impact.append(imp_advice1 + "\n\n");
                if(!imp_advice2.equals(""))
                    impact.append(imp_advice2 + "\n\n");
                if(!imp_advice3.equals(""))
                    impact.append(imp_advice3);
                break;
            case R.id.Btn6:
                f = new File("/sdcard/" + userid + "/image/" + imagename + '5' + ".jpg");
                myBitmap = BitmapFactory.decodeFile(f.getAbsolutePath());
                imageView.setImageBitmap(myBitmap);
                followThru.setText(R.string.followThru);
                detailscore.setText(truascore);
                followThru.append("\n\n");
                Log.d("asd",chiken_wing);
                if(!thu_advice1.equals(""))
                    followThru.append(thu_advice1 + "\n\n");
                if(!thu_advice2.equals(""))
                    followThru.append(thu_advice2 + "\n\n");
                if(!thu_advice3.equals(""))
                    followThru.append(thu_advice3 + "\n\n");
                if(!chiken_wing.equals(""))
                    followThru.append(chiken_wing + "\n\n");
                if(!body_sway.equals(""))
                    followThru.append(body_sway);
                break;
            case R.id.Btn7:
                f = new File("/sdcard/" + userid + "/image/" + imagename + '6' + ".jpg");
                myBitmap = BitmapFactory.decodeFile(f.getAbsolutePath());
                imageView.setImageBitmap(myBitmap);
                finish.setText(R.string.finish);
                detailscore.setText(fscore);
                finish.append("\n\n");
                if(!finish_advice.equals(""))
                    finish.append(finish_advice);
                break;
        }
    }

}