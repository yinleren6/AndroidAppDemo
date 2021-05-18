package moe.demo.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class Broadcast_Receiver_high_permission2 extends BroadcastReceiver {

    final String TAG = "TAG_Broadcast_Receiver_高权限 2 ";

    @Override
    public void onReceive(Context context, Intent intent) {

        //获取广播的数据
        String data = getResultData();
        String action = intent.getAction();

        //修改
        if ("abort".equals(action)) {
            abortBroadcast();
            Log.i(TAG, " 终止了广播 " + data);
        }
        Log.i(TAG, " 收到了广播 " + action+ data);

    }

}
