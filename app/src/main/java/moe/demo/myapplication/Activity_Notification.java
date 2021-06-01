package moe.demo.myapplication;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class Activity_Notification extends AppCompatActivity {
    Intent intent;
    Intent intent2;
    PendingIntent pendingIntent;
    PendingIntent pendingIntent2;
    NotificationManager manager;
    NotificationChannel mChannel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        this.setTitle("通知");
        //初始化 NotificationManager
        if (manager == null) {
            manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        }


        intent = new Intent(this, Activity_open_noti.class);
        intent2 = new Intent(this, Activity_Service.class);
        pendingIntent = PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        pendingIntent2 = PendingIntent.getActivity(this, 2, intent2, PendingIntent.FLAG_CANCEL_CURRENT);

    }

    public void n1(View view) {


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
                .setContentTitle("标题")                      //设置标题
                .setContentText("内容文本")                     //设置通知内容
                .setWhen(System.currentTimeMillis())        //设置通知发送时间
                .setSmallIcon(R.mipmap.icon)                //小图标
                .setContentIntent(pendingIntent)            //点击动作
                .setDeleteIntent(pendingIntent2)                        //滑动消失的行为
                .setAutoCancel(true)
                .setNumber(999)//设置点击之后是否自动消失
                .setVibrate(new long[]{0, 500, 500, 500})   //设置通知震动
//                .setLights(Color.YELLOW, 1000, 1000).setSound(Uri.parse("andrpid.resource://"+getPackageName()+"/"+R.raw.))    //通知铃声
                //.setStyle(new NotificationCompat.BigTextStyle().bigText("str"))   //  超长文本
                //.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(  getResources(), R.mipmap.p1)))
                .setPriority(NotificationCompat.PRIORITY_MAX)//通知优先级 从低到高 -2 ~ 2
                .build();

        manager.notify(1, notification);
    }

    public void u1(View view) {

    }

    public void c1(View view) {
        manager.cancel(1);
    }
}
