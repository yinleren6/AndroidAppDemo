package moe.demo.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Activity_Launch_Mode2 extends BaseActivity implements View.OnLongClickListener {
    private static final String TAG = "TAG_Activity_Launch_Mode2";
    TextView textView;
    RadioGroup radioGroup;
    Intent intent = new Intent();
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_mode1234);
        this.setTitle("启动模式: singleTop");
        Class_Activity_Manger.addActivity(this);
        button = findViewById(R.id.button4);
        button.setOnLongClickListener(this);

        textView = findViewById(R.id.textView11);
        String t = "栈顶复用：当要创建的活动处于栈顶时，会复用此活动（此时不会调用onCreate、onStart方法，而是onNewIntent会被回调）；当要创建的活动不在栈顶时，会同standard模式一样创建新的实例";
        textView.setText(t + "\nTaskID：" + getTaskId());
        radioGroup = findViewById(R.id.radioGroup);

        Intent i1 = new Intent(this, Activity_Launch_Mode1.class);
        Intent i2 = new Intent(this, Activity_Launch_Mode2.class);
        Intent i3 = new Intent(this, Activity_Launch_Mode3.class);
        Intent i4 = new Intent(this, Activity_Launch_Mode4.class);
        intent = i1;
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {

            if (checkedId == R.id.radioButton1) {
                intent = i1;
                Log.i(TAG, "选中：选项1  intent = i1");

            } else if (checkedId == R.id.radioButton2) {
                intent = i2;
                Log.i(TAG, "选中：选项2  intent = i2");

            } else if (checkedId == R.id.radioButton3) {
                intent = i3;
                Log.i(TAG, "选中：选项3  intent = i3");

            } else if (checkedId == R.id.radioButton4) {
                intent = i4;
                Log.i(TAG, "选中：选项4  intent = i4");
            }
            Log.i(TAG, "----------" + checkedId);
        });
    }

    public void b1(View view) {
        if (intent != null) {
            startActivity(intent);
        } else {
            Log.e(TAG, "intent 是空的");
        }
    }

    public void finish(View view) {
        this.finish();
    }

    @Override
    public boolean onLongClick(View v) {
        Log.i(TAG, "长按");
        Class_Activity_Manger.finishAll();
        return false;
    }
}