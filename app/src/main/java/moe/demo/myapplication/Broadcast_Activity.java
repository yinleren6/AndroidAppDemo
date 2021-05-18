package moe.demo.myapplication;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Broadcast_Activity extends AppCompatActivity {
    final String TAG = "TAG_Broadcast_Activity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);
        this.setTitle("Service");

    }

    public void sendBroadcastUnorder(View view) {

        Intent i = new Intent();
        i.setAction("moe.demo.broadcast");
        i.putExtra("key", "额外的数据");
        sendBroadcast(i);
    }

    public void sendOrdered1(View view) {
        Intent i = new Intent();
        i.setAction("modify");
        sendOrderedBroadcast(i,
                null,
                new FinalReceiver_Broadcast(),
                null,
                0,
                "这是有序广播内容",
                null);
    }

    public void sendOrdered2(View view) {
        Intent i = new Intent();
        i.setAction("abort");
        sendOrderedBroadcast(i,
                null,
                new FinalReceiver_Broadcast(),
                null,
                0,
                "这是有序广播内容",
                null);
    }

    Broadcast_Receiver receiver;

    //动态注册广播
    public void register(View view) {
        receiver = new Broadcast_Receiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        filter.addAction(Intent.ACTION_INSTALL_PACKAGE);
        registerReceiver(receiver, filter);
        Log.i(TAG, "注册了动态广播");
    }

    public void unregister(View view) {
        try {
            if (receiver != null) {
                unregisterReceiver(receiver);
                Log.i(TAG, "取消了动态广播");
            }
        } catch (IllegalArgumentException e) {
            Log.w(TAG, "没有已注册的广播接收器" + e.toString());
        }
    }

    public void finish(View view) {
        this.finish();
    }
}
