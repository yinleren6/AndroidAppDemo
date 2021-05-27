package moe.demo.demo;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

public class BootReceiver extends BroadcastReceiver {
    NotificationChannel mChannel;
    NotificationManager manager;
    Intent intent1;
    PendingIntent pendingIntent;

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "收到开机完成时间的广播", Toast.LENGTH_LONG).show();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //创建通知渠道ID
            String channelId = "default";
            //创建通知渠道名称
            String channelName = "开机自启通知";
            //创建通知渠道重要性
            int importance = NotificationManager.IMPORTANCE_HIGH;
            mChannel = new NotificationChannel(channelId, channelName, importance);
            manager.createNotificationChannel(mChannel);
            intent1 = new Intent(context, MainActivity.class);
            pendingIntent = PendingIntent.getActivity(context, 1, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        }

        Notification notification = new NotificationCompat.Builder(context, "default")
                .setContentTitle("开机完成")                  //设置标题
                .setContentText("您的手机的开机速度打败了全国 1% 的用户")   //设置通知内容
                .setWhen(System.currentTimeMillis())          //设置通知发送时间
                .setSmallIcon(R.mipmap.icon)              //小图标
                .setContentIntent(pendingIntent)        //点击动作
                .setAutoCancel(true)                //设置点击之后是否自动消失
                .build();

        manager.notify(1, notification);
    }
}
