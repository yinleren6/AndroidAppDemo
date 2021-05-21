package moe.demo.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Activity_Activitty extends AppCompatActivity {

    TextView textView2;
    private static final String TAG = "TAG_Activity_Activitty";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //重载此活动后可以调取之前存储的零食数据
        if (savedInstanceState != null) {
            String data = savedInstanceState.getString("data");
            Log.i(TAG, "重载保存的数据：" + data);
            Toast.makeText(this, "重载保存的数据：" + data, Toast.LENGTH_LONG).show();
        }
        Log.i(TAG, "onCreate");
        setContentView(R.layout.activity_activity);
        this.setTitle("Activity_Activity");

        textView2 = findViewById(R.id.textView2);
    }

    public void b1(View view) {

        //可选 传入参数

        Activity_FeedBack_Activity.activityStart(this, "key1", "这是参数一", "key2", "这是参数二");
    }

    public void b2(View view) {
        Intent i = new Intent(this, Activity_FeedBack_Activity.class);
        //传回参数
        startActivityForResult(i, 1);
    }

    //隐式启动活动
    public void b3(View view) {
        Intent intent = new Intent("com.demo.myapplication.Active_Start");
        //可选 自定义 类别
        intent.addCategory("AAAAA");

        startActivity(intent);
    }

    //隐式启动浏览器
    public void b4(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://www.coolapk.com"));
        startActivity(intent);
    }

    //隐式启动拨号
    public void b5(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:10086"));
        startActivity(intent);
    }

    //对话框主题的活动
    public void b6(View view) {
        Intent intent = new Intent(this, Dialog_Activity.class);
        startActivity(intent);
    }

    //Activity 的四种启动模式
    public void b7(View view) {
        Intent i = new Intent(this, Activity_FeedBack_Activity.class);
        i.setClass(this, Activity_Launch_Mode.class);
        startActivity(i);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 1) {
            String name = data.getStringExtra("name");
            String pwd = data.getStringExtra("pwd");

            if (name != "") {
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
        this.finish();
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
