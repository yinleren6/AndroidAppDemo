package moe.demo.demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class Class_Broadcast_Receiver extends BroadcastReceiver {
    final String TAG = "TAG_Broadcast_Receiver_无序";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();


        if ("android.intent.action.AIRPLANE_MODE".equals(action)) {
            Log.i(TAG, "收到动态广播 ：飞行模式");
        } else if ("android.intent.action.PACKAGE_INSTALL".equals(action)) {
            Log.i(TAG, "收到动态广播 ：安装了新包");
        } else if ("android.intent.action.BOOT_COMPLETED".equals(action)) {
            Log.i(TAG, "收到动态广播 ：开机广播");
        } else if ("android.intent.action.ACTION_POWER_CONNECTED".equals(action)) {
            Log.i(TAG, "收到动态广播 ：连接电源");
        } else if ("android.intent.action.ACTION_POWER_DISCONNECTED".equals(action)) {
            Log.i(TAG, "收到动态广播 ：断开电源");
        } else {String data = intent.getStringExtra("key");
            Log.i(TAG, "：" + action + "额外数据：" + data);
        }
    }
}
