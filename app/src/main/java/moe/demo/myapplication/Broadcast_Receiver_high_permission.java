package moe.demo.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class Broadcast_Receiver_high_permission extends BroadcastReceiver {

    final String TAG = "TAG_Broadcast_Receiver_高权限";

    @Override
    public void onReceive(Context context, Intent intent) {

        //获取广播的数据
        String data = getResultData();
        String action = intent.getAction();

        //修改
        if ("modfy".equals(action)) {
            setResultData("这个广播被修改");
        }
        Log.i(TAG, " 收到:" + data);

    }

}
