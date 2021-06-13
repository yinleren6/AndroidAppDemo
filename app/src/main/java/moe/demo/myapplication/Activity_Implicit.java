package moe.demo.myapplication;

import android.os.Bundle;
import android.view.View;

public class Activity_Implicit extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finish);
        setTitle("隐式启动");
    }

    public void finish(View view) {
        finish();
    }

}
