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

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class Activity_Notification extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "TAG_通知测试";
    Intent intent;
    Intent intent2;
    PendingIntent pendingIntent;
    PendingIntent pendingIntent2;
    NotificationManager manager;
    NotificationChannel mChannel;
    Notification notification;
    int progress;
    int s = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        this.setTitle("通知");
        //初始化 NotificationManager
        if (manager == null) {
            manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        }

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
        //        findViewById(R.id.b9).setOnClickListener(this);
        findViewById(R.id.b_cancel).setOnClickListener(this);


        init();
    }

    public void init() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //创建通知渠道ID
            String channelId = "default";
            //创建通知渠道名称
            String channelName = "默认渠道1";
            //创建通知渠道重要性
            int importance = NotificationManager.IMPORTANCE_HIGH;
            mChannel = new NotificationChannel(channelId, channelName, importance);
            manager.createNotificationChannel(mChannel);
        }
        //        notification = new NotificationCompat.Builder(this, "default").setContentTitle("标题")                      //设置标题
        //
        //                .setWhen(System.currentTimeMillis())        //设置通知发送时间
        //
        //
        //                .setDeleteIntent(pendingIntent2)                        //滑动消失的行为
        //                .setPriority(NotificationCompat.PRIORITY_MAX)//通知优先级 从低到高 -2 ~ 2
        //                .
        //

        //.setNumber(999) //桌面角标通知数量
        //.setVibrate(new long[]{0, 500, 500, 500})   //设置通知震动

        // .setSound(Uri.parse("andrpid.resource://"+getPackageName()+"/"+R.raw.))    //通知铃声

        //    .build();


    }

    @Override
    public void onClick(View v) {
        String[] a = {"1", "2", "3", "4", "5", "6"};

        //1普通通知
        if (v.getId() == R.id.b1) {

            s++;
            notification = new NotificationCompat.Builder(this, "default")
                    //设置标题
                    .setContentTitle("1 默认通知")
                    //设置通知内容
                    .setContentText("s-" + s + "-" + a[s])
                    //是否显示发送的时间 (现在 5分钟前)（默认）
                    .setShowWhen(true)
                    //设置通知发送时间（默认）
                    .setWhen(System.currentTimeMillis())
                    //小图标 需要alpha图层 也就是透明图标
                    .setSmallIcon(R.mipmap.icon)
                    //设置小图的背景颜色
                    .setColor(Color.GREEN)
                    //大图标
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.icon))
                    //点击动作
                    .setContentIntent(pendingIntent)
                    //删除通知的行为
                    .setDeleteIntent(pendingIntent2)
                    //通知优先级 从低到高 -2 ~ 2 （默认0）
                    .setPriority(NotificationCompat.PRIORITY_MAX)
                    //设置点击之后是否自动消失（默认falsse）
                    .setAutoCancel(true)
                    //全部默认
                    .setDefaults(NotificationCompat.DEFAULT_ALL)

                    .build();
            manager.notify(1, notification);
        }


        //2style的富文本通知
        else if (v.getId() == R.id.b2) {
            notification = new NotificationCompat.Builder(this, "default")
                    //设置标题
                    .setContentTitle("2 富文本通知")
                    //设置通知内容
                    .setContentText("阿巴啊巴巴巴巴")
                    //设置通知发送时间
                    .setWhen(System.currentTimeMillis())
                    //小图标
                    .setSmallIcon(R.mipmap.icon)
                    //点击动作
                    .setContentIntent(pendingIntent)
                    //  超长文本 208字符
                    .setStyle(new NotificationCompat.BigTextStyle().bigText("长内容文本超长内容文本超长内容文本超长内容文本超长内容文本超长内容文本超长内容文本超长内容文本超长内容文本超长超长内容文本超长内容文本超长内容文本超长内容文本超长内容文本超长内容文本文本超文本超长内容文本超长内容文本容文本超长内容文本超长内容文本内容文本超长内容文本超长内容文本超长内容文本超长内容文本超长内容文本超长内容文本超长内容文本超长内容文本超长内容文本超长内长内容文本超长内容文本超长长内容文本多少汉字字啊"))
                    //设置点击之后是否自动消失
                    .setAutoCancel(true).build();
            manager.notify(1, notification);
        }
        //3style的设置图片
        else if (v.getId() == R.id.b3) {
            notification = new NotificationCompat.Builder(this, "default")
                    //设置标题
                    .setContentTitle("3 带图片通知")
                    //设置通知内容
                    .setContentText("有图片！！")
                    //设置通知发送时间
                    .setWhen(System.currentTimeMillis())
                    //小图标
                    .setSmallIcon(R.mipmap.icon)
                    //点击动作
                    .setContentIntent(pendingIntent)
                    //设置点击之后是否自动消失
                    .setAutoCancel(true)
                    //设置图片
                    .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.a0gtav))).build();
            manager.notify(1, notification);
        }
        //TODO
        //4进度条
        else if (v.getId() == R.id.b4) {
            notification = new NotificationCompat.Builder(this, "default")
                    //设置标题
                    .setContentTitle("4 Downloading...")
                    //小图标
                    .setSmallIcon(R.mipmap.icon)
                    //通知内容
                    .setContentText(String.valueOf(progress)).setContentIntent(pendingIntent)
                    //进度条
                    .setProgress(100, progress, false)
                    //通知不可删除
                    .setOngoing(true).setAutoCancel(true)

                    .setWhen(System.currentTimeMillis())

                    .build();
            manager.notify(1, notification);
        } //5带按钮
        else if (v.getId() == R.id.b5) {
            notification = new NotificationCompat.Builder(this, "default")

                    .setContentTitle("5 请选择") //设置标题

                    .setContentText("地上有一块肥皂，是否捡起？")

                    .setSmallIcon(R.mipmap.icon)                //小图标

                    .addAction(R.drawable.icon, "捡它！", pendingIntent).addAction(R.drawable.icon, "确定", pendingIntent)

                    .setContentIntent(pendingIntent)            //点击动作

                    .setAutoCancel(true)//设置点击之后是否自动消失

                    .build();
            manager.notify(1, notification);
        }
        //TODO 6多条通知
        else if (v.getId() == R.id.b6) {
            notification = new NotificationCompat.Builder(this, "default")

                    .setContentTitle("6 多条通知") //设置标题

                    .setContentText("地上有一块肥皂，是否捡起？")                     //设置通知内容

                    .setWhen(System.currentTimeMillis())        //设置通知发送时间

                    .setSmallIcon(R.mipmap.icon)                //小图标

                    .setStyle(new NotificationCompat.MessagingStyle(""))

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
        //
        //            notification = new NotificationCompat.Builder(this, "default")
        //
        //                    .setContentTitle("多条通知") //设置标题
        //
        //                    .setContentText("地上有一块肥皂，是否捡起？")                     //设置通知内容
        //
        //                    .setWhen(System.currentTimeMillis())        //设置通知发送时间
        //
        //                    .setSmallIcon(R.mipmap.icon)                //小图标
        //
        //                    .addAction(action)
        //
        //                    .setAutoCancel(true)//设置点击之后是否自动消失
        //                    //悬浮必须设置？
        //                    .setDefaults(Notification.DEFAULT_ALL)
        //
        //                    .setCategory(Notification.CATEGORY_MESSAGE)
        //
        //                    .build();
        //            manager.notify(1, notification);

        //        https://www.jianshu.com/p/a4ee45995fcd
        //        }
        else if (v.getId() == R.id.b8) {
            notification = new NotificationCompat.Builder(this, "default")

                    .setContentTitle("多条通知") //设置标题

                    .setContentText("地上有一块肥皂，是否捡起？")                     //设置通知内容

                    .setWhen(System.currentTimeMillis())        //设置通知发送时间

                    .setSmallIcon(R.mipmap.icon)                //小图标

                    .setStyle(new NotificationCompat.MessagingStyle(""))

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
