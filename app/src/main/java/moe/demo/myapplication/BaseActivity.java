package moe.demo.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    private static final String TAG = "TAG_BaseActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityCollector.addActivity(this);
        Log.d(TAG, "base 创建");
    }

    private ForceOffLineReceiver receiver;

    @Override
    protected void onResume() {
        Log.d(TAG, "base    onResume");
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("exit_app");
        receiver = new ForceOffLineReceiver();
        registerReceiver(receiver, intentFilter);
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "base   onPause");
        super.onPause();
        if (receiver != null) {
            unregisterReceiver(receiver);
            receiver = null;
        }
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "base   onDestroy");
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    static class ForceOffLineReceiver extends BroadcastReceiver {
        private static final String TAG = "TAG_接收器";

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "收到" + intent.getAction());

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("注意")
                    .setMessage("收到了下线广播")
                    .setCancelable(false)
                    .setPositiveButton("好", (dialog, which) -> {

                        Log.d(TAG, "收到了下线广播 ");

                        ActivityCollector.finishAll();
                        Intent i = new Intent(context, Activity_FeedBack.class);
                        context.startActivity(i);
                    });
            builder.show();
        }
    }
}