package moe.demo.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Activity_Launch_Mode2 extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_mode1234);
        this.setTitle("singleTop");
        textView = findViewById(R.id.textView11);
        String t = "栈顶复用：当要创建的活动处于栈顶时，会复用此活动（此时不会调用onCreate、onStart方法，而是onNewIntent会被回调）；当要创建的活动不在栈顶时，会同standard模式一样创建新的实例";
        textView.setText(t);
    }

    public void b1(View view) {
        Intent i = new Intent(this, Activity_FeedBack_Activity.class);
        i.setClass(this, Activity_Launch_Mode2.class);
        startActivity(i);
    }


    public void finish(View view) {
        this.finish();
    }
}