package moe.demo.demo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Activity_Launch_Mode3 extends AppCompatActivity implements View.OnLongClickListener {
    private static final String TAG = "TAG_Activity_Launch_Mode3";
    TextView textView;
    RadioGroup radioGroup;
    Intent intent = new Intent();
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_mode1234);
        this.setTitle("singleTask");
        Class_Activity_Manger.addActivity(this);
        button = findViewById(R.id.button4);
        button.setOnLongClickListener(this);

        textView = findViewById(R.id.textView11);
        String t = "栈内复用模式：当要创建的活动已处于栈中时，不会创建新的实例，而是会销毁它之上的所有活动，使其成为栈顶，仅会回调onNewIntent方法";
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