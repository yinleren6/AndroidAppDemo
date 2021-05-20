package moe.demo.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Activity_Launch_Mode4 extends AppCompatActivity {
    TextView textView;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_mode1234);
        this.setTitle("singleInstance");
        textView = findViewById(R.id.textView11);
        editText = findViewById(R.id.editTextTextPersonName3);
        String t = "单实例模式：全局单实例，是一种加强的SngleTask模式，此模式的Activity仅能单独位于一个任务栈中，系统会为它创建一个单独到任务栈";
        textView.setText(t);
    }


    public void b1(View view) {
        Intent i = new Intent(this, Activity_FeedBack_Activity.class);
        i.setClass(this, Activity_Launch_Mode4.class);
        startActivity(i);
    }

    public void b2(View view) {
        String a = editText.getText() + ".class";
        Intent i = new Intent(this, Activity_FeedBack_Activity.class);
        i.setClass(this, Activity_Launch_Mode2.class);
//        i.setClass(this, Activity_Launch_Mode2.class);
        startActivity(i);
    }


    public void finish(View view) {
        this.finish();
    }
}