package moe.demo.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

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
    ListItem1[] datas = {
            new ListItem1(R.drawable.pic1, "111111111111111"),
            new ListItem1(R.drawable.pic2, "2222222222"),
            new ListItem1(R.drawable.pic3, "33333333333"),
            new ListItem1(R.drawable.pic4, "4444444444"),
            new ListItem1(R.drawable.pic5, "55555555555"),
            new ListItem1(R.drawable.pic6, "6666666666666"),
            new ListItem1(R.drawable.pic7, "777777777"),
            new ListItem1(R.drawable.pic8, "888888888888"),
            new ListItem1(R.drawable.pic9, "99999999999999"),
            new ListItem1(R.drawable.pic10, "100000000000")
    };

    List<ListItem1> mListItem1s = new ArrayList<>();
    CardView_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);
        setTitle("卡片布局");
        recyclerView = findViewById(R.id.recyclerview3);
        //此处设置卡片布局的列数
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(gridLayoutManager);

        adapter = new CardView_Adapter(mListItem1s);
        recyclerView.setAdapter(adapter);


        reload();
        initToolBar();
        initSwipeRefresh();
    }

    private void reload() {
        mListItem1s.clear();
        for (int i = 0; i <= 10; i++) {
            Random random = new Random();
            int index = random.nextInt(datas.length);
            mListItem1s.add(datas[index]);

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
                            Thread.sleep(1000);
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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
//            actionBar.setHomeAsUpIndicator(R.drawable.abc_vector_test);
        }


        //也可以在布局中设置这些属性,具体见布局文件
        toolbar.setNavigationIcon(R.drawable.abc_vector_test);//设置最左侧图标
        toolbar.setLogo(R.mipmap.ic_launcher);//设置程序logo图标
        toolbar.setTitle("卡片布局");

        toolbar.setTitleTextColor(Color.parseColor("#ff0000"));//设置标题的字体颜色
        toolbar.setSubtitle("子标题zzzz");
        toolbar.setSubtitleTextColor(Color.parseColor("#00ff00"));//设置子标题的字体颜色

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CardViewActivity.this.finish();

            }
        });
    }

}