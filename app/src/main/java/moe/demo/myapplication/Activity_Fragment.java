package moe.demo.myapplication;

import android.os.Bundle;

public class Activity_Fragment extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        setTitle("碎片 Fragment");
    }

    //TODO 用动态碎片实现兼容平板和手机的界面 新闻APP
    //TODO 或者横竖屏切换布局
}