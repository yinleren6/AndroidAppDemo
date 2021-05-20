package moe.demo.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Activity_Launch_Mode3 extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_mode1234);
        this.setTitle("singleTask");
        textView = findViewById(R.id.textView11);
        String t = "栈内复用模式：当要创建的活动已处于栈中时，不会创建新的实例，而是会销毁它之上的所有活动，使其成为栈顶，仅会回调onNewIntent方法";
        textView.setText(t);
    }

    public void b1(View view) {
        Intent i = new Intent(this, Activity_FeedBack_Activity.class);
        i.setClass(this, Activity_Launch_Mode3.class);
        startActivity(i);
    }


    public void finish(View view) {
        this.finish();
    }
}