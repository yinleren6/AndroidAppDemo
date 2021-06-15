package moe.demo.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class Class_Service extends Service {
    final String TAG = "TAG_Service_Class";
    private final DownloadBinder mBinder = new DownloadBinder();

    @Override
    public void onCreate() {
        Toast.makeText(this, "服务被创建onCreate", Toast.LENGTH_LONG).show();
        Log.i(TAG, "服务被创建onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(getBaseContext(), "服务被启动onStartCommand", Toast.LENGTH_LONG).show();
        new Thread(new Runnable() {
            @Override
            public void run() {
//
                Log.i(TAG, "服务被启动onStartCommand");
//                stopSelf();
            }
        }).start();


        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(this, "服务被绑定onBind", Toast.LENGTH_LONG).show();
        Log.i(TAG, "服务被绑定onBind");
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Toast.makeText(this, "服务被接触绑定onUnbind", Toast.LENGTH_LONG).show();
        Log.i(TAG, "服务被接触绑定onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Toast.makeText(this, "服务被重新绑定onRebind", Toast.LENGTH_LONG).show();
        Log.i(TAG, "服务被重新绑定onRebind");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "服务被销毁onDestroy", Toast.LENGTH_LONG).show();
        Log.i(TAG, "服务被销毁onDestroy");
    }

    static class DownloadBinder extends Binder {
        private static final String TAG = "TAG_DownloadBinder";

        public void startDownload() {
            Log.i(TAG, "开始下载");
        }

        public int getProgress() {
            Log.i(TAG, "下载进度 -> " + 0);
            return 0;
        }
    }
}
