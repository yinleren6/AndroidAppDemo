package moe.demo.myapplication;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Activity_RecyclerView extends BaseActivity {
    RecyclerView mRecyclerView;
    List<ListItem1> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        setTitle("回收器视图  RecyclerView");

        mRecyclerView = findViewById(R.id.recyclerView1);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mRecyclerView.setAdapter(new Recycler_Adapter(datas));

        initView1();
    }

    private void initView1() {

        datas.add(new ListItem1(R.drawable.pic11, "Service1"));
        datas.add(new ListItem1(R.drawable.pic12, "Service2"));
        datas.add(new ListItem1(R.drawable.pic13, "Service3"));
        datas.add(new ListItem1(R.drawable.pic14, "Service4"));
        datas.add(new ListItem1(R.drawable.pic15, "Service5"));
        datas.add(new ListItem1(R.drawable.pic16, "Service6"));
        datas.add(new ListItem1(R.drawable.pic17, "Service7"));
        datas.add(new ListItem1(R.drawable.pic18, "Service8"));
        datas.add(new ListItem1(R.drawable.pic19, "Service9"));
        datas.add(new ListItem1(R.drawable.pic20, "Service10"));
        datas.add(new ListItem1(R.drawable.pic21, "Service11"));
        datas.add(new ListItem1(R.drawable.pic22, "Service12"));
        datas.add(new ListItem1(R.drawable.pic23, "Service13"));
        datas.add(new ListItem1(R.drawable.pic24, "Service14"));
        datas.add(new ListItem1(R.drawable.pic25, "Service15"));
        datas.add(new ListItem1(R.drawable.pic26, "Service16"));
        datas.add(new ListItem1(R.drawable.pic27, "Service17"));
        datas.add(new ListItem1(R.drawable.pic28, "Service18"));
        datas.add(new ListItem1(R.drawable.pic29, "Service19"));
        datas.add(new ListItem1(R.drawable.pic30, "Service20"));

    }

}