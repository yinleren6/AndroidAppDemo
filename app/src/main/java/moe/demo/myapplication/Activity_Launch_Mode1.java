package moe.demo.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Activity_Launch_Mode1 extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_mode1234);
        this.setTitle("standard");
        textView = findViewById(R.id.textView11);
        String t = "标准(默认)启动模式：每次都会创建一个新实例，并压入栈中（前台任务栈）当按HOME键回到桌面时，此活动就会移到后台任务栈";
        textView.setText(t);
    }

    public void b1(View view) {
        Intent i = new Intent(this, Activity_FeedBack_Activity.class);
        i.setClass(this, Activity_Launch_Mode1.class);
        startActivity(i);
    }


    public void finish(View view) {
        this.finish();
    }
}