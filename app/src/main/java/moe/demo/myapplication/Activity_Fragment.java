package moe.demo.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Activity_Fragment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
    }//TODO 用动态碎片实现兼容平板和手机的界面 新闻APP
    //TODO 或者横竖屏切换布局
}