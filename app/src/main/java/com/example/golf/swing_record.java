package com.example.golf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class swing_record extends AppCompatActivity implements SurfaceHolder.Callback {
    CountDownTimer swingTimer;
    TextView time_text;
    ProgressBar progressBar;
    int i = 0;
    final int TOTALTIME = 30000;
    final int COUNT_DOWN_INTERVAL = 1000;

    private Camera mcam;
    private MediaRecorder mediaRecorder;
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;

    boolean recording = false; //초기는 녹화중이 아님

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        if (mcam == null) {
            try {

                mcam.setPreviewDisplay(surfaceHolder);
                mcam.startPreview();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
        if (surfaceHolder.getSurface() == null) {
            // preview surface does not exist
            return;
        }
        // stop preview before making changes
        try {
            mcam.stopPreview();
        } catch (Exception e) {
            // ignore: tried to stop a non-existent preview
        }
        // set preview size and make any resize, rotate or
        // reformatting changes here
        // start preview with new settings
        try {
            mcam.setPreviewDisplay(surfaceHolder);
            mcam.startPreview();
        } catch (Exception e) {
        }
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) { }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swing_record);

        TedPermission.with(this)
                .setPermissionListener(permission)
                .setRationaleMessage("녹화를 위하여 권한을 허용해주세요.")
                .setDeniedMessage("권한이 거부되었습니다. 설정 > 권한에서 허용해주세요.")
                .setPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO)
                .check();

        setting();

        final ToggleButton tbtn = (ToggleButton) this.findViewById(R.id.recordBtn);

        tbtn.setOnClickListener(v -> {
            if (recording) { //녹화 중일때 버튼 클릭시 녹화 종료
                mediaRecorder.stop();
                mediaRecorder.release();//stop release세트임
                mediaRecorder = null;
                mcam.lock();
                recording = false;
            }
            else { //화면 녹화 시작
                swingTimer();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tbtn.setBackgroundDrawable( //버튼바꾸기
                                getResources().getDrawable(R.drawable.ic_stop)
                        );
                        long now = System.currentTimeMillis();
                        Date mDate = new Date(now);
                        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyyMMdd_hhmmss");
                        String getTime = simpleDate.format(mDate);
                        Toast.makeText(swing_record.this, "녹화가 시작되었습니다.", Toast.LENGTH_SHORT).show();
                        try {
                            mediaRecorder = new MediaRecorder();
                            mcam.unlock();
                            mediaRecorder.setCamera(mcam);
                            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                            mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
                            mediaRecorder.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_720P));
                            mediaRecorder.setOrientationHint(90);
                            mediaRecorder.setOutputFile("sdcard/" + getTime + ".mp4");
                            mediaRecorder.setPreviewDisplay(surfaceHolder.getSurface());
                            mediaRecorder.prepare();
                            mediaRecorder.start();
                        } catch (Exception e) {
                            e.printStackTrace();
                            mediaRecorder.release();
                        }
                        recording = true;
                    }
                });
            }
        });
    }

    private  void setting(){
        mcam = Camera.open();

        Camera.Parameters params = mcam.getParameters();
        params.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
        mcam.setParameters(params);
        
        mcam.setDisplayOrientation(90);
        surfaceView = (SurfaceView)findViewById(R.id.surfaceView);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    PermissionListener permission = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
            Toast.makeText(swing_record.this, "권한 허가", Toast.LENGTH_SHORT).show();
/*
            mcam = android.hardware.Camera.open();
            mcam.setDisplayOrientation(90);
            surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
            surfaceHolder = surfaceView.getHolder();
            surfaceHolder.addCallback(swing_record.this);
            surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
            */

        }

        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
            Toast.makeText(swing_record.this, "권한 거부", Toast.LENGTH_SHORT).show();
        }
    };


    public void swingTimer() {
            time_text = (TextView) findViewById(R.id.countDown);
            progressBar = (ProgressBar) findViewById(R.id.progress);
            progressBar.setProgress(i);
            swingTimer = new CountDownTimer(TOTALTIME, COUNT_DOWN_INTERVAL) {
                @Override
                public void onTick(long millisUntilFinished) {
                    i++;
                    time_text.setText(millisUntilFinished / 1000 + "초");
                    progressBar.setProgress((int) i * 100 / (TOTALTIME / COUNT_DOWN_INTERVAL));
                }

                @Override
                public void onFinish() { //시간이 다 되면 자동 녹화종료 -> 화면 넘어감
                    i++;
                    progressBar.setProgress(100);

                }
            }.start();
        }
    private void setCamera(android.hardware.Camera cam) {
        mcam = cam;
    }
}
