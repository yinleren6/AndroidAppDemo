package moe.demo.myapplication;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import java.io.File;

public class DownloadService extends Service {
    private static final String TAG = "TAG_DownloadService";
    private final DownloadBinder mBinder = new DownloadBinder();
    NotificationManager manager;
    NotificationCompat.Builder builder;
    Notification notification;
    NotificationChannel mChannel;
    private DownloadTask downloadTask;
    private final DownloadListener listener = new DownloadListener() {
        @Override
        public void onProgress(int progress) {
            getNotificationManager().notify(1, getNotification("在下载中...", progress));

        }

        @Override
        public void onSuccess() {
            downloadTask = null;
            stopForeground(true);
            getNotificationManager().notify(1, getNotification("下载成功啦", 100));
            Toast.makeText(DownloadService.this, "下载完成aa", Toast.LENGTH_SHORT).show();
            Log.i(TAG, "下载完成100");
        }

        @Override
        public void onFailed() {
            downloadTask = null;
            stopForeground(true);
            getNotificationManager().notify(1, getNotification("下载失败", -1));
            Toast.makeText(DownloadService.this, "下载失败", Toast.LENGTH_SHORT).show();
            Log.i(TAG, "下载失败");
        }

        @Override
        public void onPaused(int progress) {
            downloadTask = null;
            getNotificationManager().notify(1, getNotification("下载已暂停", progress));
            Toast.makeText(DownloadService.this, "下载暂停", Toast.LENGTH_SHORT).show();
            Log.i(TAG, "下载暂停");
        }

        @Override
        public void onCanceled() {
            getNotificationManager().notify(1, getNotification("下载已取消", 100));
            downloadTask = null;
            stopForeground(true);
            Toast.makeText(DownloadService.this, "下载取消", Toast.LENGTH_SHORT).show();
            Log.i(TAG, "下载取消");
        }


    };
    private String downloadUrl;

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private NotificationManager getNotificationManager() {
        return (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    void init() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "download";//创建通知渠道ID
            String channelName = "下载通知";//创建通知渠道名称
            int importance = NotificationManager.IMPORTANCE_DEFAULT;//创建通知渠道重要性
            mChannel = new NotificationChannel(channelId, channelName, importance);
            manager.createNotificationChannel(mChannel);
            builder = new NotificationCompat.Builder(getBaseContext(), channelId);
        }
        else {
            //noinspection deprecation
            builder = new NotificationCompat.Builder(getBaseContext());
        }
    }

    private Notification getNotification(String titel, int progress) {
        Intent intent = new Intent(this, Download_Demo.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);


        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        init();

        if (progress < 0) {
            Log.i(TAG, "还没开始");
            notification = builder.setContentTitle(titel)//
                    .setWhen(System.currentTimeMillis())//
                    .setSmallIcon(R.mipmap.icon)//
                    .setContentIntent(pi)//
                    .setProgress(100, progress, true)//
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.icon))//
                    .build();//
        }

        if (0 <= progress && progress != 100) {
            notification = builder.setContentTitle(titel + ":" + progress + "%")//
                    .setWhen(System.currentTimeMillis())//
                    .setSmallIcon(R.mipmap.icon)//
                    .setContentIntent(pi)//
                    .setSound(null)//
                    .setNotificationSilent()
                    .setProgress(100, progress, false)//
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.icon))//
                    .build();//
        }
        if (progress == 100) {
            Log.i(TAG, "下载完了");
            notification = builder.setContentTitle(titel)//
                    .setWhen(System.currentTimeMillis())//
                    .setSmallIcon(R.mipmap.icon)//
                    .setContentIntent(pi)//
                    .setContentText("操作完成")//
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.icon))//
                    .build();//

        }

        return notification;
    }

    class DownloadBinder extends Binder {
        private static final String TAG = "TAG_DownloadBinder";

        public void startDownload(String url) {
            if (downloadTask == null) {
                downloadUrl = url;
                downloadTask = new DownloadTask(listener);
                downloadTask.execute(downloadUrl);
                startForeground(1, getNotification("正在下载", 0));
                Toast.makeText(DownloadService.this, "正在下载", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "正在下载");
            }
        }

        public void pauseDownload() {
            if (downloadTask != null) {
                downloadTask.pause();
            }
        }

        public void cancelDownload() {
            if (downloadTask != null) {
                downloadTask.calcel();
            }
            else if (downloadUrl != null) {
                String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
                String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
                File file = new File(directory + fileName);
                if (file.exists()) {
                    file.delete();
                }
                getNotificationManager().cancel(1);
                stopForeground(true);
                Toast.makeText(DownloadService.this, "下载取消", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "下载取消");
            }
        }
    }
}