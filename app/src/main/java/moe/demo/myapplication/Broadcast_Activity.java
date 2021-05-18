package moe.demo.myapplication;

import android.content.Intent;
import android.os.Bundle;
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

    public void sendBroadcast(View view) {

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
        i.setAction("modify");
        sendOrderedBroadcast(i,
                null,
                new FinalReceiver_Broadcast(),
                null,
                0,
                "这是有序广播内容",
                null);
    }


    public void finish(View view) {
        this.finish();
    }
}
