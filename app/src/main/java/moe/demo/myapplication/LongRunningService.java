package moe.demo.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

/**
 * 定时任务服务
 */
public class LongRunningService extends Service {
    public LongRunningService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 做一些事情
            }
        }).start();
        //获取 AlarmManager 实例
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        // 一小时的毫秒数
        int anHour = 60 * 60 * 1000;
        //定义一个触发时间
        long trigger = SystemClock.elapsedRealtime() + anHour;
        //定义一个启动此服务的 Intent
        Intent i = new Intent(this, LongRunningService.class);
        //定义一个意图，用于再次开启此服务
        PendingIntent pi = PendingIntent.getService(this, 0, i, 0);
        //调用 set 方法设置一个定时任务 传入参数：工作类型， 任务触发时间 和 意图
        /** 工作类型：有四种
         * RTC_WAKEUP   从 1970 年 1 月 1 日 0 点开始算起，会唤醒CPU
         * RTC          从 1970 年 1 月 1 日 0 点开始算起，不会唤醒CPU
         * ELAPSED_REALTIME_WAKEUP  触发时间从系统开机开始计算，会唤醒CPU
         * ELAPSED_REALTIME         触发时间从系统开机开始计算，不会唤醒CPU
         *
         */
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, trigger, pi);
        /**关于待机：
         // Android 4.4 之后优化了待机耗电，会使多个任务集中执行，减少不必要的唤醒节省电量，会使一些任务延迟，使用setExact 来保证任务准时进行
         // manager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP, trigger, pi);
         //Android 6.0 新增 Doze 模式，进一步优化了待机唤醒耗电，Alarm任务将在下次退出Doze模式时执行，这样任务将变得不准时，使用以下方法保证任务准时进行
         // manager.setAndAllowWhileIdle(AlarmManager.ELAPSED_REALTIME_WAKEUP, trigger, pi);
         // manager.setExactAndAllowWhileIdle(AlarmManager.ELAPSED_REALTIME_WAKEUP, trigger, pi);
         */
        //这样启动此服务之后会开启一个一小时的定时任务，时间到了之后会再启动此服务，达到一小时 启动一次 onStartCommand 的循环目的


        Log.i("当前时间戳: ", System.currentTimeMillis() + "");
        Log.i("系统开机时间: ", SystemClock.elapsedRealtime() + "");

        return super.onStartCommand(intent, flags, startId);
    }
}