package moe.demo.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class Activity_Activity extends BaseActivity {
    private static final String TAG = "TAG_Activity_Activitty";
    ListView listView;
    List<ListItem1> list;
    TextView textView2;
    EditText editTextNum;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity);
        setTitle("活动 Activity");
        Log.i(TAG, "onCreate");
        //重载此活动后可以调取之前存储的零食数据
        if (savedInstanceState != null) {
            String data = savedInstanceState.getString("data");
            Log.i(TAG, "重载保存的数据：" + data);
            Toast.makeText(this, "重载保存的数据：" + data, Toast.LENGTH_LONG).show();
        }
        textView2 = findViewById(R.id.textView2);
        editTextNum = findViewById(R.id.numIn);

        list = new ArrayList<>();
        list.add(new ListItem1(R.drawable.icon, "无返回结果带参数"));
        list.add(new ListItem1(R.drawable.icon, "有返回结果"));
        list.add(new ListItem1(R.drawable.icon, "隐式启动"));
        list.add(new ListItem1(R.drawable.icon, "隐式启动浏览器"));
        list.add(new ListItem1(R.drawable.icon, "拨号界面"));
        list.add(new ListItem1(R.drawable.icon, "对话框样式活动"));
        list.add(new ListItem1(R.drawable.icon, "活动的4种启动方式 -->"));


        listView = findViewById(R.id.listView);
        ListAdapter1 adapter = new ListAdapter1(this, R.layout.listview_adapter1, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id) -> {
            switch (position) {
                case 0:
                    Activity_FeedBack.activityStart(this, "key1", "这是参数一", "key2", "这是参数二");
                    break;
                case 1:
                    Intent intent1 = new Intent(this, Activity_FeedBack.class);
                    //传回参数
                    startActivityForResult(intent1, 1);
                    break;

                case 2:
                    //隐式启动活动
                    Intent intent2 = new Intent("com.demo.myapplication.Active_Start");
                    //可选 自定义 类别
                    intent2.addCategory("AAAAA");
                    startActivity(intent2);
                    break;
                case 3:
                    //隐式启动浏览器
                    Intent intent3 = new Intent(Intent.ACTION_VIEW);
                    intent3.setData(Uri.parse("http://www.coolapk.com"));
                    startActivity(intent3);
                    break;
                case 4:
                    //隐式启动拨号
                    Intent intent4 = new Intent(Intent.ACTION_DIAL);
                    intent4.setData(Uri.parse("tel:" + editTextNum.getText().toString()));
                    startActivity(intent4);
                    break;
                case 5:
                    //对话框主题的活动
                    Intent intent5 = new Intent(this, Activity_Dialog.class);
                    startActivity(intent5);
                    break;
                case 6:
                    //Activity 的四种启动模式
                    Intent intent6 = new Intent(this, Activity_FeedBack.class);
                    intent6.setClass(this, Activity_Launch_Mode.class);
                    startActivity(intent6);
                    break;

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 1) {
            String name = data.getStringExtra("name");
            String pwd = data.getStringExtra("pwd");

            if (!name.equals("")) {
                textView2.setText("姓名：" + name + "\t密码" + pwd);
            }
        }
    }

    //在被因内存不足或其他原因而被回收时 会回调此方法用于零食保存数据
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String data = "Something would be saved";
        outState.putString("data", data);
    }

    public void finish(View view) {
        //        this.finish();
        Intent intent = new Intent();
        intent.setAction("exit_app");
        view.getContext().sendBroadcast(intent);
    }
    //活动的7种生命周期

    //  onCreat()

    @Override
    protected void onStart() {
        Log.i(TAG, "onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.i(TAG, "onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.i(TAG, "onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i(TAG, "onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, "onDestroy");
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        Log.i(TAG, "onRestart");
        super.onRestart();
    }
}
