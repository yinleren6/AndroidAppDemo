package moe.demo.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        i = new Intent();
    }

    public void c1(View view) {
        i.setClass(this, Activity_Activitty.class);
        startActivity(i);
    }

    public void c2(View view) {
        i.setClass(this, Service_Activitty.class);
        startActivity(i);
    }

    public void c3(View view) {

    }

    public void c4(View view) {

    }

    public void finish(View view) {
        this.finish();
    }
}