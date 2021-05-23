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
    final String TAG = "TAG_Main_Activity";
    Intent i;
    ListView listView;
    List<Class_ListView_Item> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        i = new Intent();
        list = new ArrayList<>();
        list.add(new Class_ListView_Item("Activity", "活动测试", R.drawable.icon));
        list.add(new Class_ListView_Item("Service", "服务测试", R.drawable.icon));
        list.add(new Class_ListView_Item("Broadcast", "广播接收器测试", R.drawable.icon));
        list.add(new Class_ListView_Item(" ", " ", R.drawable.icon));
        list.add(new Class_ListView_Item("okhttp", "网络测试", R.drawable.icon));
        list.add(new Class_ListView_Item(" ", " ", R.drawable.icon));
        list.add(new Class_ListView_Item("Notification", "通知测试", R.drawable.icon));
        list.add(new Class_ListView_Item(" ", " ", R.drawable.icon));


        listView = findViewById(R.id.listView);
        Adapter_ListView adapter = new Adapter_ListView(this, R.layout.listview_adapter, list);
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
//                        i.setClass(MainActivity.this, Activity_Service.class);
//                        startActivity(i);
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