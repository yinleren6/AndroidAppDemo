package moe.demo.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class Broadcast_Receiver extends BroadcastReceiver {
    final String TAG = "TAG_Broadcast_Receiver_无序";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        String data = intent.getStringExtra("key");

        if ("android.intent.action.AIRPLANE_MODE".equals(action)) {
            Log.i(TAG, "收到动态广播 ：飞行模式");
        } else if ("android.intent.action_INSTALL_PACKAGE".equals(action)) {
            Log.i(TAG, "收到动态广播 ：安装了新包");
        } else {
            Log.i(TAG, "：" + action + "额外数据：" + data);
        }
    }
}
