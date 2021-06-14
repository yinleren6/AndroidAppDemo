package moe.demo.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "TAG_Main_Activity";


    DrawerLayout mDrawerLayout;
    RecyclerView recyclerView1;
    boolean isOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolBar();

        mDrawerLayout = findViewById(R.id.drawerlayout1);
        //透明背景
        //        mDrawerLayout.setScrimColor(Color.TRANSPARENT);
        //DrawLayout 事件回调
        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View view, float v) {
                Log.i("---", "滑动中");
            }

            @Override
            public void onDrawerOpened(@NonNull View view) {
                Log.i("---", "打开");
                isOpen = true;
            }

            @Override
            public void onDrawerClosed(@NonNull View view) {
                Log.i("---", "关闭");
                isOpen = false;
            }

            @Override
            public void onDrawerStateChanged(int i) {
                Log.i("---", "状态改变");
            }
        });


        findViewById(R.id.floatingActionButton2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "按下了浮动按钮", Toast.LENGTH_SHORT).show();
            }
        });
        initDrawerlayout();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在主线程里关闭DrawerLayout然后打开新的Activity会感觉卡顿？
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();

                }
                mDrawerLayout.closeDrawer(GravityCompat.START);
            }
        }).start();

    }

    void initDrawerlayout() {
        recyclerView1 = findViewById(R.id.recyclerView2);
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView1.setLayoutManager(linearLayoutManager);
        //实例化一个列表项目类，并添加要显示的数据
        List<ListItem1> datas = new ArrayList<>();


        datas.add(new ListItem1(R.drawable.icon, "活动测试"));
        datas.add(new ListItem1(R.drawable.icon, "服务测试"));
        datas.add(new ListItem1(R.drawable.icon, "广播接收器测试"));
        datas.add(new ListItem1(R.drawable.icon, "发送exit_app广播"));


        //设置Adapter，同时传入需要展示的数据datas
        recyclerView1.setAdapter(new Drawer_Adapter(datas));


        //设置滑出宽度
        ViewGroup.LayoutParams mLayoutParams = recyclerView1.getLayoutParams();
        int width = getResources().getDisplayMetrics().widthPixels;
        mLayoutParams.width = width * 2 / 3;
        recyclerView1.setLayoutParams(mLayoutParams);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //设置toolbar右边的菜单项
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(MainActivity.this, "点击了菜单", Toast.LENGTH_SHORT).show();
        //第一个参数 ：任意View Snackbar 会寻找自动最外层的布局
        Snackbar.make(findViewById(R.id.coordinatorlayout), "已删除一个会话", Snackbar.LENGTH_SHORT).setAction("撤销", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "撤销了删除", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "点击Snackbar交互按钮监听");
            }
        }).setCallback(new Snackbar.Callback() {
            @Override
            public void onShown(Snackbar sb) {
                super.onShown(sb);
                Log.i(TAG, "Snackbar显示时的回调");
            }

            @Override
            public void onDismissed(Snackbar transientBottomBar, int event) {
                super.onDismissed(transientBottomBar, event);
                Log.i(TAG, "Snackbar消失时的回调");
            }
        }).show();

        switch (item.getItemId()) {


            default:
                break;
        }
        return true;

    }

    private void initToolBar() {


        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {

            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.abc_vector_test);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        //也可以在布局中设置这些属性,具体见布局文件
        toolbar.setNavigationIcon(R.drawable.abc_vector_test);//设置最左侧图标
        toolbar.setLogo(R.mipmap.ic_launcher);//设置程序logo图标
        toolbar.setTitle("toolbar标题");//需要在替换actionbar之前设置,否则显示不了

        toolbar.setTitleTextColor(Color.parseColor("#ff0000"));//设置标题的字体颜色
        toolbar.setSubtitle("子标题");
        toolbar.setSubtitleTextColor(Color.parseColor("#00ff00"));//设置子标题的字体颜色

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "点击了最左边导航按钮", Toast.LENGTH_SHORT).show();
                mDrawerLayout.openDrawer(GravityCompat.START);

            }
        });
        setSupportActionBar(toolbar);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "按了返回键", Toast.LENGTH_SHORT).show();
        if (isOpen) {
            Log.i(TAG, "关闭DrawerLayout");
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }


    }
}