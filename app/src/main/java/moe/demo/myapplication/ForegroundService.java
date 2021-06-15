package moe.demo.myapplication;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;

public class ForegroundService extends Service {
    private static final String TAG = "TAG_ForegroundService";


    @Override
    public void onCreate() {
        super.onCreate();
        sendNoti();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Code
                // stopSelf();
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    //启动前台服
    void sendNoti() {
        Log.i(TAG, "启动前台服务 Oncreate");
        Intent intent = new Intent(this, Activity_open_noti.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        Notification notification = new NotificationCompat.Builder(this, "default")//
              //  .setChannelId("default")
                .setContentTitle("前台服务通知")//
                .setContentText("前台服务正在运行")//
                .setWhen(System.currentTimeMillis())//
                .setSmallIcon(R.mipmap.icon)//
                .setContentIntent(pendingIntent)//
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.icon))//
                .build();//

        startForeground(1, notification);
    }


}