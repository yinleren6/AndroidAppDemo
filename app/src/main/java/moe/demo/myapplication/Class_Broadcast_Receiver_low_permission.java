package moe.demo.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class Class_Broadcast_Receiver_low_permission extends BroadcastReceiver {

    final String TAG = "TAG_Broadcast_Receiver_低权限";

    @Override
    public void onReceive(Context context, Intent intent) {

        //获取广播的数据
        String data = getResultData();
        String action = intent.getAction();
        Log.i(TAG, "低权限 收到广播: " + "action: " + action + " data: " + data);
        Toast.makeText(context, "低权限 收到广播: " + "action: " + action + " data: " + data, Toast.LENGTH_LONG).show();

    }

}
