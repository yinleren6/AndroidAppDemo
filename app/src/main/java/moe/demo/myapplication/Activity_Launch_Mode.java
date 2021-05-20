package moe.demo.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Activity_Launch_Mode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_mode);
        this.setTitle("Launche Mode");
    }


    public void b1(View view) {
        Intent i = new Intent(this, Activity_FeedBack_Activity.class);
        i.setClass(this, Activity_Launch_Mode1.class);
        startActivity(i);
    }

    public void b2(View view) {
        Intent i = new Intent(this, Activity_FeedBack_Activity.class);
        i.setClass(this, Activity_Launch_Mode2.class);
        startActivity(i);
    }

    public void b3(View view) {
        Intent i = new Intent(this, Activity_FeedBack_Activity.class);
        i.setClass(this, Activity_Launch_Mode3.class);
        startActivity(i);
    }

    public void b4(View view) {
        Intent i = new Intent(this, Activity_FeedBack_Activity.class);
        i.setClass(this, Activity_Launch_Mode4.class);
        startActivity(i);
    }

    public void finish(View view) {
        this.finish();
    }
}