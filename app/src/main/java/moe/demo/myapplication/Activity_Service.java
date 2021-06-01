package moe.demo.myapplication;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Activity_Service extends AppCompatActivity {

    final String TAG = "TAG_Service_Activitty";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        this.setTitle("服务");

    }

    private boolean isBind;
    private final ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
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


    public void start(View view) {
        Log.i(TAG, "按钮：启动服务");
        startService(new Intent(getBaseContext(), Class_Service.class));
    }

    public void stop(View view) {
        Log.i(TAG, "按钮：停止服务");
        stopService(new Intent(getBaseContext(), Class_Service.class));
    }

    public void bindClick(View view) {
        Log.i(TAG, "按钮：开始绑定");
        Intent intent = new Intent(this, Class_Service.class);
        // 这个绑定的步骤是异步的，绑定成功后会回调onServiceConnected方法
        bindService(intent, conn, Context.BIND_AUTO_CREATE);

    }

    public void unbindClick(View view) {

        Log.i(TAG, "按钮：解除绑定");
        if (isBind) {
            unbindService(conn);
        }
    }

    public void finish(View view) {
        this.finish();
    }
}
