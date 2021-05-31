package moe.demo.demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class Class_Broadcast_Receiver extends BroadcastReceiver {
    final String TAG = "TAG_Broadcast_Receiver_普通广播接收器：";

    @Override
    public void onReceive(Context context, Intent intent) {
        String data = getResultData();
        String action = intent.getAction();

        if ("android.intent.action.PACKAGE_INSTALL".equals(intent.getAction())) {
            Log.i(TAG, "收到动态广播 ：安装了新包");
        } else if ("android.intent.action.ACTION_POWER_CONNECTED".equals(intent.getAction())) {
            Toast.makeText(context, "连接电源000", Toast.LENGTH_LONG).show();
            Log.i(TAG, "收到动态广播 ：连接电源");
        } else if ("android.intent.action.ACTION_POWER_DISCONNECTED".equals(intent.getAction())) {
            Toast.makeText(context, "断开电源", Toast.LENGTH_LONG).show();
            Log.i(TAG, "收到动态广播 ：断开电源");
        } else {
            data = intent.getStringExtra("key");
            Log.i(TAG, "：" + intent.getAction() + "额外数据：" + data);
        }
        Toast.makeText(context, " 普通广播接收器 收到广播: " + "action: " + action + " data: " + data, Toast.LENGTH_LONG).show();

    }
}
