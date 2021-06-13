package moe.demo.myapplication;

import android.os.Bundle;
import android.view.View;

public class Activity_open_noti extends BaseActivity {

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