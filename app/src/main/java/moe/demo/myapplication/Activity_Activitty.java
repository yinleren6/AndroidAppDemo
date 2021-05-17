package moe.demo.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
        this.setTitle("A_Activity");
        i = new Intent();
        textView2 = findViewById(R.id.textView2);
    }

    public void b1(View view) {
        Log.i("aaaa", "按了按钮2");
        i.setClass(this, Activity_FeedBack_Activitty.class);
        startActivity(i);
    }

    public void b2(View view) {
        i.setClass(this, Activity_FeedBack_Activitty.class);
        startActivityForResult(i, 1);
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
