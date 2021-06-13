package moe.demo.myapplication;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

public class Activity_Service extends BaseActivity implements View.OnClickListener {

    final String TAG = "TAG_Service_Activitty";
    private Class_Service.DownloadBinder mBinder;
    private boolean isBind;

    private final ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBinder = (Class_Service.DownloadBinder) service;
            mBinder.startDownload();
            mBinder.getProgress();
            Log.i(TAG, "回调：绑定成功  isBind =  " + isBind);
            isBind = true;
            //TODO 不会回调
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(TAG, "回调：绑定断开  isBind =  " + isBind);
            isBind = false;
            //TODO 不会回调
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        this.setTitle("服务 Service");
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button7).setOnClickListener(this);
        findViewById(R.id.button8).setOnClickListener(this);
        findViewById(R.id.button51).setOnClickListener(this);
        findViewById(R.id.button52).setOnClickListener(this);
        findViewById(R.id.button49).setOnClickListener(this);

    }


    public void finish(View view) {
        this.finish();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button1) {
            Log.i(TAG, "按钮：启动服务");
            startService(new Intent(getBaseContext(), Class_Service.class));
        }
        else if (v.getId() == R.id.button2) {
            Log.i(TAG, "按钮：停止服务");
            stopService(new Intent(getBaseContext(), Class_Service.class));

        }
        else if (v.getId() == R.id.button7) {
            Log.i(TAG, "按钮：开始绑定");
            Intent intent = new Intent(this, Class_Service.class);
            // 这个绑定的步骤是异步的，绑定成功后会回调onServiceConnected方法
            bindService(intent, conn, Context.BIND_AUTO_CREATE);
        }
        else if (v.getId() == R.id.button8) {
            Log.i(TAG, "按钮：解除绑定");
            if (isBind) {
                unbindService(conn);
            }
        }
        else if (v.getId() == R.id.button51) {
            Log.i(TAG, "按钮：启动服务");
            startService(new Intent(this, ForegroundService.class));
        }
        else if (v.getId() == R.id.button52) {
            Log.i(TAG, "按钮：停止服务");
            stopService(new Intent(getBaseContext(), ForegroundService.class));

        }
        else if (v.getId() == R.id.button49) {
            Log.i(TAG, "按钮：Intent Service");
            Log.i(TAG, "主线程  id:" + Thread.currentThread().getId());
            Log.i(TAG, "主线程  name:" + Thread.currentThread().getName());
            Intent intents = new Intent(this, Intent_Service.class);
            startService(intents);

        }
    }
}
