package moe.demo.myapplication;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CardViewActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    List<ListItem1> list = new ArrayList<>();
    ListItem1[] datas = {new ListItem1(R.drawable.icon, "1"), new ListItem1(R.drawable.icon, "2"), new ListItem1(R.drawable.icon, "3"), new ListItem1(R.drawable.icon, "4"), new ListItem1(R.drawable.icon, "5"), new ListItem1(R.drawable.icon, "6"), new ListItem1(R.drawable.icon, "7"), new ListItem1(R.drawable.icon, "8"), new ListItem1(R.drawable.icon, "9"), new ListItem1(R.drawable.icon, "10")};
    CardView_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);
        setTitle("卡片布局");
        recyclerView = findViewById(R.id.recyclerview3);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        adapter = new CardView_Adapter(list);
        recyclerView.setAdapter(adapter);


        reload();
        initToolBar();
        initSwipeRefresh();
    }

    private void reload() {
        list.clear();
        for (int i = 0; i < 50; i++) {
            Random random = new Random();
            int index = random.nextInt(datas.length);
            list.add(datas[index]);

        }
    }

    private void initSwipeRefresh() {
        SwipeRefreshLayout refreshLayout = findViewById(R.id.refresh);
        refreshLayout.setColorSchemeResources(R.color.design_default_color_primary);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //TODO
                                reload();
                                adapter.notifyDataSetChanged();
                                refreshLayout.setRefreshing(false);
                            }
                        });
                    }
                }).start();
            }
        });


    }

    private void initToolBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {

            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.abc_vector_test);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        //也可以在布局中设置这些属性,具体见布局文件
        toolbar.setNavigationIcon(R.drawable.abc_vector_test);//设置最左侧图标
        toolbar.setLogo(R.mipmap.ic_launcher);//设置程序logo图标
        toolbar.setTitle("卡片布局");//需要在替换actionbar之前设置,否则显示不了

        toolbar.setTitleTextColor(Color.parseColor("#ff0000"));//设置标题的字体颜色
        toolbar.setSubtitle("子标题");
        toolbar.setSubtitleTextColor(Color.parseColor("#00ff00"));//设置子标题的字体颜色


    }

}