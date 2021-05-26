package moe.demo.demo;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Activity_open_noti extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_noti);
        this.setTitle("通知打开的活动");
    }

    public void finish(View view) {
        finish();
    }
}