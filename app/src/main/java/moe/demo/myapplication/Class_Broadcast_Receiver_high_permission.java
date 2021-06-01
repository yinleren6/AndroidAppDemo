package moe.demo.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class Class_Broadcast_Receiver_high_permission extends BroadcastReceiver {

    final String TAG = "TAG_Broadcast_Receiver_高权限";

    @Override
    public void onReceive(Context context, Intent intent) {

        //获取广播的数据
        String data = getResultData();
        String action = intent.getAction();

        //修改
        if ("modify".equals(action)) {
            setResultData("广播被高权限修改");
            Toast.makeText(context, "2高权限 收到广播: " + "action: " + action + " data: " + data, Toast.LENGTH_LONG).show();
            Log.i(TAG, "高权限 收到广播: " + "action: " + action + " data: " + data);
        }

    }

}
