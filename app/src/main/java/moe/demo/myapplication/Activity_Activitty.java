package moe.demo.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Activity_Activitty extends AppCompatActivity {
    Intent i;
    TextView textView2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity);
        this.setTitle("Activity_Activity");
        i = new Intent();
        textView2 = findViewById(R.id.textView2);
    }

    public void b1(View view) {

        i.setClass(this, Activity_FeedBack_Activity.class);
        startActivity(i);
    }

    public void b2(View view) {
        i.setClass(this, Activity_FeedBack_Activity.class);
        startActivityForResult(i, 1);
    }

    public void b3(View view) {
        Intent intent = new Intent("com.demo.myapplication.Active_Start");
        //可选
        intent.addCategory("AAAAA");
        startActivity(intent);
    }

    //隐式启动浏览器
    public void b4(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://www.coolapk.com"));


        startActivity(intent);
    }  //隐式启动浏览器

    public void b5(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://misaka.net"));


        startActivity(intent);
    }

    public void finish(View view) {
        this.finish();
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
}
