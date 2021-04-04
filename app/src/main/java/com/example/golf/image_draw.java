package com.example.golf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.widgets.Rectangle;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.io.InputStream;
import java.nio.ByteBuffer;

public class image_draw extends AppCompatActivity {
    DrawView drawView;
    //RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_image_draw);

        //layout = (RelativeLayout)findViewById(R.id.drawLayout);
        drawView = new DrawView(this);
        setContentView(drawView);
        //layout.addView(drawView);




    }

    public class DrawView extends View {
        private Bitmap image01;
        private Context context;



        public DrawView(Context context){
            super(context);
            //this.context = context;

        }

        @Override
        protected void onDraw(Canvas canvas){
            super.onDraw(canvas);

            Paint paint = new Paint();
            Canvas c = new Canvas();
            canvas.drawColor(Color.WHITE);
            canvas.drawPoint(150,150,paint);
            paint.setColor(Color.RED);

            Resources r = getResources();
            BitmapDrawable bitmapDrawable = (BitmapDrawable)r.getDrawable(R.drawable.cutiegirl2);
            bitmapDrawable.setBounds(0,0,350,560);
            Bitmap bitmap = bitmapDrawable.getBitmap();

            //Rect rectangle = new Rect(20,30,100,100);
            canvas.drawBitmap(bitmap,200,500,null);

            paint.setStrokeWidth(3);
            canvas.drawLine(300,700,500,780,paint);




            //canvas.drawBitmap(image01,350,300,null);

            /*drawFigure.setColor(Color.RED);
            drawFigure.setStyle(Paint.Style.STROKE);
            drawFigure.setStrokeWidth(3);
            canvas.drawRect(10,10,100,100,drawFigure);*/


        }


    }
}