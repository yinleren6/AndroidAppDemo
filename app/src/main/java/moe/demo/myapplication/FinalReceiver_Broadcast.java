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

        Log.i(TAG, "收到：" + data);
    }
}
