package moe.demo.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
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
    ListView listView;
    List<ListItem2> list;
    Intent i;
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

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {

            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.abc_vector_test);
        }


        i = new Intent();
        list = new ArrayList<>();
        list.add(new ListItem2("Activity", "活动测试", R.drawable.icon));
        list.add(new ListItem2("Service", "服务测试", R.drawable.icon));
        list.add(new ListItem2("Broadcast", "广播接收器测试", R.drawable.icon));
        list.add(new ListItem2("RecyclerView", "一种更强大的 ListView", R.drawable.icon));
        list.add(new ListItem2("okhttp", "网络测试", R.drawable.icon));
        list.add(new ListItem2("Fragment", "一种碎片化布局", R.drawable.icon));
        list.add(new ListItem2("Notification", "通知测试", R.drawable.icon));
        list.add(new ListItem2("Data Storage", "三种数据持久化技术", R.drawable.icon));
        list.add(new ListItem2("Runtime Permission", "运行时权限", R.drawable.icon));
        list.add(new ListItem2("ContentProvider", "内容提供器", R.drawable.icon));
        list.add(new ListItem2("Camera&Album", "照片相机", R.drawable.icon));
        list.add(new ListItem2("Music&Video", "音乐视频", R.drawable.icon));
        list.add(new ListItem2("WebView", "浏览网页", R.drawable.icon));
        list.add(new ListItem2("XML/JSON", "数据解析", R.drawable.icon));
        list.add(new ListItem2("Service", "服务demo", R.drawable.icon));
        list.add(new ListItem2("Service", "百度地图SDK", R.drawable.icon));

        listView = findViewById(R.id.listView);
        ListAdapter2 adapter = new ListAdapter2(this, R.layout.listview_adapter2, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Log.i(TAG, "listview 点击事件");
            switch (position) {
                case 0:
                    i.setClass(MainActivity.this, Activity_Activity.class);
                    break;
                case 1:
                    i.setClass(MainActivity.this, Activity_Service.class);
                    break;
                case 2:
                    i.setClass(MainActivity.this, Activity_Broadcast.class);
                    break;
                case 3:
                    i.setClass(MainActivity.this, Activity_Recycler_List.class);
                    break;
                case 4:
                    i.setClass(MainActivity.this, Activity_okhttp.class);
                    break;
                case 5:
                    i.setClass(MainActivity.this, Activity_Fragment.class);
                    break;
                case 6:
                    i.setClass(MainActivity.this, Activity_Notification.class);
                    break;
                case 7:
                    i.setClass(MainActivity.this, Activity_Data_Storage.class);
                    break;
                case 8:
                    i.setClass(MainActivity.this, RuntimePermission.class);
                    break;
                case 9:
                    i.setClass(MainActivity.this, Activity_ContentProvider_read.class);
                    break;
                case 10:
                    i.setClass(MainActivity.this, Activity_CameraAlbum.class);
                    break;
                case 11:
                    i.setClass(MainActivity.this, Activity_MediaPlayer.class);
                    break;
                case 12:
                    i.setClass(MainActivity.this, Activity_webView.class);
                    break;
                case 13:
                    i.setClass(MainActivity.this, Activity_XML.class);
                    break;
                case 14:
                    i.setClass(MainActivity.this, Download_Demo.class);
                    break;
                case 15:
                    i.setClass(MainActivity.this, Activity_Baidu_Map_SKD.class);
                    break;
            }
            startActivity(i);
        });
        initDrawerlayout();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mDrawerLayout.closeDrawer(GravityCompat.START);
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
        datas.add(new ListItem1(R.drawable.icon, "发送广播 exit_app 关闭应用"));


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
        getMenuInflater().inflate(R.menu.main_menu, menu);//设置toolbar右边的菜单项
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
        })

               .setCallback(new Snackbar.Callback() {
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
        })

                .show();


        switch (item.getItemId()) {


            default:
                break;
        }
        return true;

    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
                Toast.makeText(MainActivity.this, "点击了最左边导航按钮", Toast.LENGTH_SHORT).show();
                mDrawerLayout.openDrawer(GravityCompat.START);

            }
        });
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