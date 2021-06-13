package moe.demo.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Activity_Launch_Mode extends BaseActivity {
    private static final String TAG = "TAG_Activity_Launch_Mode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_mode);
        this.setTitle("启动模式");
        Log.i(TAG, "TaskID:" + getTaskId());
    }


    public void b1(View view) {
        Intent i = new Intent(this, Activity_FeedBack.class);
        i.setClass(this, Activity_Launch_Mode1.class);
        startActivity(i);
    }

    public void b2(View view) {
        Intent i = new Intent(this, Activity_FeedBack.class);
        i.setClass(this, Activity_Launch_Mode2.class);
        startActivity(i);
    }

    public void b3(View view) {
        Intent i = new Intent(this, Activity_FeedBack.class);
        i.setClass(this, Activity_Launch_Mode3.class);
        startActivity(i);
    }

    public void b4(View view) {
        Intent i = new Intent(this, Activity_FeedBack.class);
        i.setClass(this, Activity_Launch_Mode4.class);
        startActivity(i);
    }

    public void finish(View view) {
        this.finish();
    }
}