package com.example.golf;
import android.Manifest;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
public class camera extends AppCompatActivity implements SurfaceHolder.Callback{
    private Camera camera;
    private Button btn_record;
    private Button btn_stop;
    private MediaRecorder mediaRecorder;
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;
    private boolean recording = false;
   @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_swing_record);
       final MediaController mediaController =
               new MediaController(this);
       setContentView(R.layout.camera);
       TedPermission.with(this)
               .setPermissionListener(permission)
               .setRationaleMessage("녹화를 위하여 권한을 허용해주세요.")
               .setDeniedMessage("권한이 거부되었습니다. 설정 > 권한에서 허용해주세요.")
               .setPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO)
               .check();
       btn_record = (Button)findViewById(R.id.btn_record);
       btn_stop = (Button)findViewById(R.id.btn_stop);
       btn_record.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (recording) {
                   mediaRecorder.stop();
                   mediaRecorder.release();
                   camera.lock();
                   recording = false;

               } else {
                   runOnUiThread(new Runnable() {
                       @Override
                       public void run() {
                           long now = System.currentTimeMillis();
                           Date mDate = new Date(now);
                           SimpleDateFormat simpleDate = new SimpleDateFormat("yyyyMMdd_hhmmss");
                           String getTime = simpleDate.format(mDate);
                           Toast.makeText(camera.this, "녹화가 시작되었습니다.", Toast.LENGTH_SHORT).show();
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
                               recording = true;
                           } catch (Exception e) {
                               e.printStackTrace();
                               mediaRecorder.release();
                           }
                       }
                   });
               }
           }
       });
       btn_stop.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               setResult(RESULT_OK);
               finish();
           }
       });
   }
    PermissionListener permission = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
            Toast.makeText(camera.this, "권한 허가", Toast.LENGTH_SHORT).show();

            camera = android.hardware.Camera.open();
            camera.setDisplayOrientation(90);
            surfaceView = (SurfaceView)findViewById(R.id.surfaceView);
            surfaceHolder = surfaceView.getHolder();
            surfaceHolder.addCallback(camera.this);
            surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        }

        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
            Toast.makeText(camera.this, "권한 거부", Toast.LENGTH_SHORT).show();
        }
    };



    @Override
    public void surfaceCreated(SurfaceHolder holder) {

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
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        refreshCamera(camera);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
