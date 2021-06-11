package moe.demo.myapplication;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

public class Intent_Service extends IntentService {
    private static final String TAG = "TAG_Intent_Service";

    public Intent_Service() {
        super("Intent_Service"); //调用父类有参构造函数
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        //打印当前线程ID

        Log.i(TAG, "当前是在新线程 id " + Thread.currentThread().getId());
        Log.i(TAG, "新线程 name:" + Thread.currentThread().getName());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy自动销毁");
    }
}
