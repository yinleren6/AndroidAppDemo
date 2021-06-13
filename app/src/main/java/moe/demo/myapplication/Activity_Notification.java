package moe.demo.myapplication;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.core.app.NotificationCompat;

public class Activity_Notification extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "TAG_通知测试";
    Intent intent;
    Intent intent2;
    PendingIntent pendingIntent;
    PendingIntent pendingIntent2;

    NotificationChannel mChannel;
    Notification notification;
    int progress;
    int s = 0;
    ;
    NotificationCompat.Builder builder;
    NotificationManager manager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        setTitle("通知 Notification");


        //点击通知动作
        intent = new Intent(this, Activity_open_noti.class);
        pendingIntent = PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        //清除通知动作
        intent2 = new Intent(this, Activity_Service.class);
        pendingIntent2 = PendingIntent.getActivity(this, 2, intent2, PendingIntent.FLAG_CANCEL_CURRENT);

        findViewById(R.id.b1).setOnClickListener(this);
        findViewById(R.id.b2).setOnClickListener(this);
        findViewById(R.id.b3).setOnClickListener(this);
        findViewById(R.id.b4).setOnClickListener(this);
        findViewById(R.id.b5).setOnClickListener(this);
        findViewById(R.id.b6).setOnClickListener(this);
        findViewById(R.id.b7).setOnClickListener(this);
        findViewById(R.id.b8).setOnClickListener(this);
        findViewById(R.id.b_cancel).setOnClickListener(this);
        findViewById(R.id.finish12).setOnClickListener(this);
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        init();
    }

    public void init() {
        //初始化 NotificationManager


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "default";  //创建通知渠道ID
            String channelName = "默认渠道1";  //创建通知渠道名称
            int importance = NotificationManager.IMPORTANCE_HIGH; //创建通知渠道重要性

            mChannel = new NotificationChannel(channelId, channelName, importance);
            manager.createNotificationChannel(mChannel);
            builder = new NotificationCompat.Builder(Activity_Notification.this, channelId);
        }
        else {
            builder = new NotificationCompat.Builder(Activity_Notification.this);
        }
    }

    @Override
    public void onClick(View v) {
        String[] a = {"1", "2", "3", "4", "5", "6"};
        if (builder != null) {
            init();
        }
        //1普通通知
        if (v.getId() == R.id.b1) {
            s++;
            notification = builder.setContentTitle("1 默认通知") //设置标题
                    .setContentText("s-" + s + "-" + a[s]) //设置通知内容
                    .setShowWhen(true)//是否显示发送的时间 (现在 5分钟前)（默认）
                    .setWhen(System.currentTimeMillis())//设置通知发送时间（默认）
                    .setSmallIcon(R.mipmap.icon) //小图标 需要alpha图层 也就是透明图标
                    .setColor(Color.GREEN) //设置小图的背景颜色
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.icon))//大图标
                    .setContentIntent(pendingIntent)//点击动作
                    .setDeleteIntent(pendingIntent2) //删除通知的行为
                    .setPriority(NotificationCompat.PRIORITY_MAX)//通知优先级 从低到高 -2 ~ 2 （默认0）
                    .setAutoCancel(true)  //设置点击之后是否自动消失（默认falsse）
                    .setDefaults(NotificationCompat.DEFAULT_ALL) //全部默认
                    .build();

            manager.notify(1, notification);
        }


        //2style的富文本通知
        else if (v.getId() == R.id.b2) {
            notification = builder.setContentTitle("2 富文本通知") //设置标题
                    .setContentText("阿巴啊巴巴巴巴")//设置通知内容
                    .setWhen(System.currentTimeMillis())//设置通知发送时间
                    .setSmallIcon(R.mipmap.icon) //小图标
                    .setContentIntent(pendingIntent) //点击动作
                    //  超长文本 208字符
                    .setStyle(new NotificationCompat.BigTextStyle().bigText("长内容文本超长内容文本超长内容文本超长内容文本超长内容文本超长内容文本超长内容文本超长内容文本超长内容文本超长超长内容文本超长内容文本超长内容文本超长内容文本超长内容文本超长内容文本文本超文本超长内容文本超长内容文本容文本超长内容文本超长内容文本内容文本超长内容文本超长内容文本超长内容文本超长内容文本超长内容文本超长内容文本超长内容文本超长内容文本超长内容文本超长内长内容文本超长内容文本超长长内容文本多少汉字字啊")).setAutoCancel(true) //设置点击之后是否自动消失
                    .build();
            manager.notify(1, notification);
        }
        //3style的设置图片
        else if (v.getId() == R.id.b3) {
            notification = builder.setContentTitle("3 带图片通知")//设置标题
                    .setContentText("有图片！！")//设置通知内容
                    .setWhen(System.currentTimeMillis())  //设置通知发送时间
                    .setSmallIcon(R.mipmap.icon) //小图标
                    .setContentIntent(pendingIntent) //点击动作
                    .setAutoCancel(true)//设置点击之后是否自动消失
                    .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.a0gtav)))//设置图片
                    .build();
            manager.notify(1, notification);
        }
        //TODO
        //4进度条
        else if (v.getId() == R.id.b4) {

            notification = builder.setContentTitle("4 Downloading...")   //设置标题
                    .setSmallIcon(R.mipmap.icon)//小图标
                    .setContentText(String.valueOf(progress))  //通知内容
                    .setContentIntent(pendingIntent)//
                    .setProgress(100, progress, false) //进度条
                    .setOngoing(true) //通知不可删除
                    .setAutoCancel(true)  //
                    .setWhen(System.currentTimeMillis())//
                    .build();
            manager.notify(1, notification);
        }

        //5带按钮
        else if (v.getId() == R.id.b5) {
            notification = builder.setContentTitle("5 请选择") //设置标题
                    .setContentText("地上有一块肥皂，是否捡起？")//
                    .setSmallIcon(R.mipmap.icon)                //小图标
                    .addAction(R.drawable.icon, "捡它！", pendingIntent)//
                    .addAction(R.drawable.icon, "确定", pendingIntent)//
                    .setContentIntent(pendingIntent)            //点击动作
                    .setAutoCancel(true)//设置点击之后是否自动消失
                    .build();
            manager.notify(1, notification);
        }

        //TODO 6多条通知
        else if (v.getId() == R.id.b6) {
            //            notification = new NotificationCompat.Builder(this, "default")//
            notification = builder.setContentTitle("6 多条通知") //设置标题
                    .setContentText("地上有一块肥皂，是否捡起？")                     //设置通知内容
                    .setWhen(System.currentTimeMillis())        //设置通知发送时间
                    .setSmallIcon(R.mipmap.icon)                //小图标
                    //                    .setStyle(new NotificationCompat.MessagingStyle(""))
                    .setAutoCancel(true)//设置点击之后是否自动消失
                    .build();
            manager.notify(1, notification);
        }

        //TODO 7直接回复消息通知

        //        else if (v.getId() == R.id.b7) {
        //            RemoteInput remoteInput = new RemoteInput.Builder(RESULT_KEY).setLabel("回复通知").build();
        //            Intent i = new Intent(this, SendService.class);
        //            PendingIntent mPendingIntent = PendingIntent.getService(this, 1, i, PendingIntent.FLAG_CANCEL_CURRENT);
        //            NotificationCompat.Action action = new NotificationCompat.Action.Builder(R.drawable.icon, "回复", pendingIntent).addRemoteInput(remoteInput).build();
        //            notification = new NotificationCompat.Builder(this, "default")
        //                    .setContentTitle("多条通知") //设置标题
        //                    .setContentText("地上有一块肥皂，是否捡起？")                     //设置通知内容
        //                    .setWhen(System.currentTimeMillis())        //设置通知发送时间
        //                    .setSmallIcon(R.mipmap.icon)                //小图标
        //                    .addAction(action)
        //                    .setAutoCancel(true)//设置点击之后是否自动消失
        //                    //悬浮必须设置？
        //                    .setDefaults(Notification.DEFAULT_ALL)
        //                    .setCategory(Notification.CATEGORY_MESSAGE)
        //                    .build();
        //            manager.notify(1, notification);
        //        https://www.jianshu.com/p/a4ee45995fcd
        //        }
        else if (v.getId() == R.id.b8) {
            //            notification = new NotificationCompat.Builder(this, "default")//
            notification = builder.setContentTitle("多条通知") //设置标题
                    .setContentText("地上有一块肥皂，是否捡起？")                     //设置通知内容
                    .setWhen(System.currentTimeMillis())        //设置通知发送时间
                    .setSmallIcon(R.mipmap.icon)                //小图标
                    //.setStyle(new NotificationCompat.MessagingStyle(""))
                    .setAutoCancel(true)//设置点击之后是否自动消失
                    .build();
            manager.notify(1, notification);
        }
        else if (v.getId() == R.id.b_cancel) {
            manager.cancel(1);
        }
        else if (v.getId() == R.id.finish12) {
            finish();
        }
        //https://blog.csdn.net/lj19851227/article/details/81013605
    }
}
