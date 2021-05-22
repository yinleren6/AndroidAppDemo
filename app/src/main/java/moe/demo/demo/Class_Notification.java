package moe.demo.demo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;

import androidx.core.app.NotificationCompat;

public class Class_Notification extends Notification {


    public int send(NotificationManager notificationManager, PendingIntent pendingIntent, Context context, String title, String text) {
         Notification notification = new NotificationCompat.Builder(context, "default")
                .setContentTitle(title)
                .setContentText(text)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.icon)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();
        int id = 1;
        notificationManager.notify(id, notification);

        return id;
    }
}