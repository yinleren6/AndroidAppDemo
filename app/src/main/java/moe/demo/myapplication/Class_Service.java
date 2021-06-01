package moe.demo.myapplication;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

public class Class_Service extends Service {
    Binder binder = new Binder();
    final String TAG = "TAG_Service_Class";

    @Override
    public void onCreate() {
        Toast.makeText(this, "onCreate", Toast.LENGTH_LONG).show();
        Log.i(TAG, "onCreate");
        super.onCreate();
    }

    NotificationManager manager;
    NotificationChannel mChannel;
    PendingIntent pendingIntent;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "onStartCommand", Toast.LENGTH_LONG).show();
        Log.i(TAG, "onStartCommand");
        //尝试用服务发送通知
        intent = new Intent(this, Activity_open_noti.class);
        pendingIntent = PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        if (manager == null) {
            manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            //创建通知渠道ID
            String channelId = "default";
//            //创建通知渠道名称
            String channelName = "默认渠道1";
//            //创建通知渠道重要性
            int importance = NotificationManager.IMPORTANCE_HIGH;
            mChannel = new NotificationChannel(channelId, channelName, importance);
            manager.createNotificationChannel(mChannel);
        }
        Notification notification = new NotificationCompat.Builder(this, "default")
                .setContentTitle("服务通知")                      //设置标题
                .setContentText("服务正在运行")                     //设置通知内容
                .setWhen(System.currentTimeMillis())        //设置通知发送时间
                .setSmallIcon(R.mipmap.icon)                //小图标
                .setContentIntent(pendingIntent)            //点击动作
//                .setDeleteIntent(pendingIntent2)                        //滑动消失的行为
                .setAutoCancel(true)
                .setNumber(999)//设置点击之后是否自动消失
                .setVibrate(new long[]{0, 500, 500, 500})   //设置通知震动
//                .setLights(Color.YELLOW, 1000, 1000).setSound(Uri.parse("andrpid.resource://"+getPackageName()+"/"+R.raw.))    //通知铃声
                //.setStyle(new NotificationCompat.BigTextStyle().bigText("str"))   //  超长文本
                //.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(  getResources(), R.mipmap.p1)))
                .setPriority(NotificationCompat.PRIORITY_MAX)//通知优先级 从低到高 -2 ~ 2
                .build();

        manager.notify(1, notification);
//


        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(this, "onBind", Toast.LENGTH_LONG).show();
        Log.i(TAG, "onBind");
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Toast.makeText(this, "onUnbind", Toast.LENGTH_LONG).show();
        Log.i(TAG, "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        Toast.makeText(this, "onRebind", Toast.LENGTH_LONG).show();
        Log.i(TAG, "onRebind");
        super.onRebind(intent);
    }


    @Override
    public void onDestroy() {
        Toast.makeText(this, "onDestroy", Toast.LENGTH_LONG).show();
        Log.i(TAG, "onDestroy");
        super.onDestroy();
    }


}
