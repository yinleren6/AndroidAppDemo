package moe.demo.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class FinalReceiver_Broadcast extends BroadcastReceiver {
    final String TAG = "TAG_Broadcast_Receiver_最终";

    @Override
    public void onReceive(Context context, Intent intent) {
        String data = getResultData();
        String action = intent.getAction();
        Log.i(TAG, "收到有序广播：" + action + data);//TODO  被修改 无法获取原始数据
    }
}
