package moe.demo.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    final String TAG = "TAG_Main_Activity";
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        i = new Intent();
    }

    public void act(View view) {

        i.setClass(this, Activity_Activitty.class);
        startActivity(i);
    }

    public void ser(View view) {

        i.setClass(this, Service_Activitty.class);
        startActivity(i);
    }

    public void bro(View view) {

        i.setClass(this, Broadcast_Activity.class);
        startActivity(i);

    }

    public void okhttp(View view) {

        i.setClass(this, okhttp_Activity.class);
        startActivity(i);
    }

    public void finish(View view) {
        this.finish();
    }
}