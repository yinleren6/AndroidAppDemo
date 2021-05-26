package moe.demo.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "TAG_Main_Activity";
    ListView listView;
    List<ListItem2> list;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        i = new Intent();

        list = new ArrayList<>();
        list.add(new ListItem2("Activity", "活动测试", R.drawable.icon));
        list.add(new ListItem2("Service", "服务测试", R.drawable.icon));
        list.add(new ListItem2("Broadcast", "广播接收器测试", R.drawable.icon));
        list.add(new ListItem2("RecyclerAdapter", "更强大的 ListView", R.drawable.icon));
        list.add(new ListItem2("okhttp", "网络测试", R.drawable.icon));
        list.add(new ListItem2(" ", " ", R.drawable.icon));
        list.add(new ListItem2("Notification", "通知测试", R.drawable.icon));
        list.add(new ListItem2(" ", " ", R.drawable.icon));


        listView = findViewById(R.id.listView);
        ListAdapter2 adapter = new ListAdapter2(this, R.layout.listview_adapter2, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        i.setClass(MainActivity.this, Activity_Activity.class);
                        startActivity(i);
                        break;
                    case 1:
                        i.setClass(MainActivity.this, Activity_Service.class);
                        startActivity(i);
                        break;
                    case 2:
                        i.setClass(MainActivity.this, Activity_Broadcast.class);
                        startActivity(i);
                        break;
                    case 3:
                        i.setClass(MainActivity.this, Activity_Recycler_List.class);
                        startActivity(i);
                        break;
                    case 4:
                        i.setClass(MainActivity.this, Activity_okhttp.class);
                        startActivity(i);
                        break;
                    case 5:
//                        i.setClass(MainActivity.this, Activity_okhttp.class);
//                        startActivity(i);
                        break;
                    case 6:
                        i.setClass(MainActivity.this, Activity_Notification.class);
                        startActivity(i);
                        break;
                    case 7:
//                        i.setClass(MainActivity.this, Activity_Notification.class);
//                        startActivity(i);
                        break;
                }
            }
        });
    }


    public void finish(View view) {
        this.finish();
    }
}