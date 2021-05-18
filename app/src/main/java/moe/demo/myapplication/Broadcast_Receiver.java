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

        Log.i(TAG, "收到广播：" + action + "额外数据：" + data);


    }
}
