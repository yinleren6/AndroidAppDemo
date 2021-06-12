package moe.demo.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Activity_Recycler_List extends AppCompatActivity {
    RecyclerView recyclerView1;

    List<ListItem1> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        recyclerView1 = findViewById(R.id.recyclerView1);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView1.setLayoutManager(linearLayoutManager);

        recyclerView1.setAdapter(new Recycler_Adapter(datas));

        initView1();
    }

    private void initView1() {

        datas.add(new ListItem1(R.drawable.icon,"Service1"  ));
        datas.add(new ListItem1(R.drawable.icon,"Service2"  ));
        datas.add(new ListItem1(R.drawable.icon,"Service3"  ));
        datas.add(new ListItem1(R.drawable.icon,"Service4"  ));
        datas.add(new ListItem1(R.drawable.icon,"Service5"  ));
        datas.add(new ListItem1(R.drawable.icon,"Service6"  ));
        datas.add(new ListItem1(R.drawable.icon,"Service7"  ));
        datas.add(new ListItem1(R.drawable.icon,"Service8"  ));
        datas.add(new ListItem1(R.drawable.icon,"Service9"  ));
        datas.add(new ListItem1(R.drawable.icon,"Service10"  ));
        datas.add(new ListItem1(R.drawable.icon,"Service11"  ));
        datas.add(new ListItem1(R.drawable.icon,"Service12"  ));
        datas.add(new ListItem1(R.drawable.icon,"Service13"  ));
        datas.add(new ListItem1(R.drawable.icon,"Service14"  ));
        datas.add(new ListItem1(R.drawable.icon,"Service15"  ));
        datas.add(new ListItem1(R.drawable.icon,"Service16"  ));
        datas.add(new ListItem1(R.drawable.icon,"Service17"  ));
        datas.add(new ListItem1(R.drawable.icon,"Service18"  ));
        datas.add(new ListItem1(R.drawable.icon,"Service19"  ));
        datas.add(new ListItem1(R.drawable.icon,"Service20"  ));

    }

}