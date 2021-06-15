package moe.demo.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CardViewActivity extends AppCompatActivity {
    private static final String TAG = "TAG_CardViewActivity";
    RecyclerView recyclerView;
    ListItem1[] datas = {//
            new ListItem1(R.drawable.pic1, "11111111111111111111111111111111111111"), //
            new ListItem1(R.drawable.pic2, "222222"), //
            new ListItem1(R.drawable.pic3, "33333333333"), //
            new ListItem1(R.drawable.pic4, "4444444444"), //
            new ListItem1(R.drawable.pic5, "55555555555"),//
            new ListItem1(R.drawable.pic6, "6666666666666"),//
            new ListItem1(R.drawable.pic7, "777777777"), //
            new ListItem1(R.drawable.pic8, "888888888888"), //
            new ListItem1(R.drawable.pic9, "99999999999999"),//
            new ListItem1(R.drawable.pic10, "100000000000")//
    };
    int layout;
    List<ListItem1> mListItem1s = new ArrayList<>();
    CardView_Adapter adapter;
    SwipeRefreshLayout refreshLayout;
    Toolbar toolbar;
    private StaggeredGridLayoutManager mLayoutManger2;
    private GridLayoutManager mLayoutManger1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);
        setTitle("卡片布局");
        refreshLayout = findViewById(R.id.refresh);
        recyclerView = findViewById(R.id.recyclerview3);
        //网格布局
        //此处设置卡片布局的列数
        mLayoutManger1 = new GridLayoutManager(this, 3);
        //瀑布流布局
        mLayoutManger2 = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManger1);
        layout = 1;
        if (savedInstanceState != null) {
            int saved = savedInstanceState.getInt("layout", 1);
            if (saved == 1) {
                recyclerView.setLayoutManager(mLayoutManger1);
                setTitle("卡片布局");
                layout = 1;
            }
            if (saved == 2) {
                recyclerView.setLayoutManager(mLayoutManger2);
                setTitle("瀑布流布局");
                layout = 2;
            }
            Toast.makeText(this, "活动已重新加载 reCreate" + saved, Toast.LENGTH_LONG).show();
            Toast.makeText(this, "上次选择的布局是：" + saved, Toast.LENGTH_LONG).show();
        }
        adapter = new CardView_Adapter(mListItem1s);
        recyclerView.setAdapter(adapter);
        initToolBar();
        refresh();
        initSwipeRefresh();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        int date = layout;
        outState.putInt("layout", date);
        Log.i(TAG, "即将重载  数据一保存：" + date);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //设置toolbar右边的菜单项
        getMenuInflater().inflate(R.menu.cardview_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_item1) {
            layout = 1;
            setTitle("卡片布局");
            recyclerView.setLayoutManager(mLayoutManger1);
        }
        if (item.getItemId() == R.id.action_item2) {
            layout = 2;
            setTitle("瀑布流布局");
            recyclerView.setLayoutManager(mLayoutManger2);
        }
        if (item.getItemId() == R.id.action_recreate) {
            this.recreate();
        }
        if (item.getItemId() == R.id.action_refresh) {
            refresh();
        }
        return true;
    }

    private void refresh() {
        refreshLayout.setRefreshing(true);
        new Thread(() -> {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            runOnUiThread(() -> {
                mListItem1s.clear();
                for (int i = 0; i <= 10; i++) {
                    Random random = new Random();
                    int index = random.nextInt(datas.length);
                    mListItem1s.add(datas[index]);
                }
                adapter.notifyDataSetChanged();
                refreshLayout.setRefreshing(false);
                //第一个参数 ：任意View Snackbar 会寻找自动最外层的布局
                Snackbar.make(findViewById(R.id.toolbar2), "已刷新", Snackbar.LENGTH_SHORT).setAction("好的", v -> Log.i(TAG, "snackbar按钮")).show();
            });
        }).start();
    }

    private void initSwipeRefresh() {
        refreshLayout.setColorSchemeResources(R.color.design_default_color_primary);
        refreshLayout.setOnRefreshListener(this::refresh);
    }

    private void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        toolbar.setNavigationIcon(R.drawable.abc_vector_test);//设置最左侧图标
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CardViewActivity.this.finish();
            }
        });
    }
}