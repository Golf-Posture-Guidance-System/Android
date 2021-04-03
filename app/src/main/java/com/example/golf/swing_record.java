package com.example.golf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Button;
import android.widget.MediaController;
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
    private Camera camera;
    private MediaRecorder mediaRecorder;
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swing_record);
        final MediaController mediaController =
                new MediaController(this);
        TedPermission.with(this)
                .setPermissionListener(permission)
                .setRationaleMessage("녹화를 위하여 권한을 허용해주세요.")
                .setDeniedMessage("권한이 거부되었습니다. 설정 > 권한에서 허용해주세요.")
                .setPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO)
                .check();
        final ToggleButton tbtn = (ToggleButton) this.findViewById(R.id.recordBtn);

        tbtn.setOnClickListener(v -> {
            if (tbtn.isChecked()) { //화면 녹화 시작
                swingTimer();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tbtn.setBackgroundDrawable(
                                getResources().getDrawable(R.drawable.ic_stop)
                        );
                        long now = System.currentTimeMillis();
                        Date mDate = new Date(now);
                        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyyMMdd_hhmmss");
                        String getTime = simpleDate.format(mDate);
                        Toast.makeText(swing_record.this, "녹화가 시작되었습니다.", Toast.LENGTH_SHORT).show();
                        try {
                            mediaRecorder = new MediaRecorder();
                            camera.unlock();
                            mediaRecorder.setCamera(camera);
                            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
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
                    }
                });

            } else { //화면 녹화 중지
                setResult(RESULT_OK);
                finish();
            }
        });
    }

    PermissionListener permission = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
            Toast.makeText(swing_record.this, "권한 허가", Toast.LENGTH_SHORT).show();

            camera = android.hardware.Camera.open();
            camera.setDisplayOrientation(90);
            surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
            surfaceHolder = surfaceView.getHolder();
            surfaceHolder.addCallback(swing_record.this);
            surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

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
    private void refreshCamera(android.hardware.Camera camera) {
        if (surfaceHolder.getSurface() == null) {
            return;
        }

        try {
            camera.stopPreview();
        } catch (Exception e) {
            e.printStackTrace();
        }

        setCamera(camera);
    }
    private void setCamera(android.hardware.Camera cam) {

        camera = cam;

    }
    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
        refreshCamera(camera);
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }
}