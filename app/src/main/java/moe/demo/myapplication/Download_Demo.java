package moe.demo.myapplication;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import org.jetbrains.annotations.NotNull;

public class Download_Demo extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "TAG_Download_Demo";
    ProgressBar progressBar1;
    ProgressBar progressBar2;
    SeekBar seekBar;
    private DownloadService.DownloadBinder downloadBinder;
    private final ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (DownloadService.DownloadBinder) service;
            Log.i(TAG, "回调：绑定成功 ");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };

    //TODO 除了显示在通知里  还可以使用广播动态更新下载进度显示在界面上
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("下载器Demo");
        setContentView(R.layout.activity_download_demo);
        findViewById(R.id.button50).setOnClickListener(this);
        findViewById(R.id.button53).setOnClickListener(this);
        findViewById(R.id.button54).setOnClickListener(this);
        progressBar1 = findViewById(R.id.progressBar);
        progressBar2 = findViewById(R.id.progressBar2);
        seekBar = findViewById(R.id.seekBar2);


        Intent intent = new Intent(this, DownloadService.class);
        startService(intent);//启动服务
        bindService(intent, conn, BIND_AUTO_CREATE);//绑定服务
        if (ContextCompat.checkSelfPermission(Download_Demo.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Download_Demo.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 11);
        }

    }

    @Override
    public void onClick(View v) {
        if (downloadBinder == null) {
            return;
        }

        if (v.getId() == R.id.button50) {
            Log.i(TAG, "按钮：启动下载");
            downloadBinder.startDownload("http://192.168.123.10:8080/test.mp4");
        }
        else if (v.getId() == R.id.button53) {
            Log.i(TAG, "按钮：暂停下载");
            downloadBinder.pauseDownload();
        }
        else if (v.getId() == R.id.button54) {
            Log.i(TAG, "按钮：取消下载");
            downloadBinder.cancelDownload();

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        if (requestCode == 11) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            }
            else {
                Toast.makeText(this, "授权失败", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);
    }
}