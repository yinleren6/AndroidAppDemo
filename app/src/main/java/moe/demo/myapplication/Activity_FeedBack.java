package moe.demo.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_FeedBack extends BaseActivity {

    EditText nameInput;
    EditText pwdInput;
    Bundle bundle;
    TextView textView;
    private static final String TAG = "TAG_Activity_FeedBack_Activ";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        this.setTitle("Activity_FeedBack");


        nameInput = findViewById(R.id.editTextTextPersonName);
        pwdInput = findViewById(R.id.editTextTextPassword);
        textView = findViewById(R.id.textView8);
        Intent intent = getIntent();
        String data1 = intent.getStringExtra("key1");
        String data2 = intent.getStringExtra("key2");
        boolean a = data1 != null;
        Log.i(TAG, "data != nul:" + a);
        if (data1 != null | data2 != null) {
            textView.setText(data1 + "  " + data2);
        }
    }


    public static void activityStart(Context context, String key1, String data1, String key2, String data2) {
        Intent intent = new Intent(context, Activity_FeedBack.class);
        intent.putExtra(key1, data1);
        intent.putExtra(key2, data2);
        context.startActivity(intent);
    }


    public void b1(View view) {
        String name = nameInput.getText().toString();
        String pwd = pwdInput.getText().toString();

        Intent i = new Intent(this, Activity_FeedBack.class);
        i.putExtra("name", name).putExtra("pwd", pwd);
        setResult(1, i);
        finish();
    }

    public void b2(View view) {
        String name = nameInput.getText().toString();
        String pwd = pwdInput.getText().toString();
        bundle = new Bundle();
        bundle.putString("name", name);
        bundle.putString("pwd", pwd);
        Intent i = new Intent(this, Activity_FeedBack.class);
        i.putExtras(bundle);
        setResult(1, i);
        finish();
    }


    @Override
    public void onBackPressed() {
        //当不是使用按钮而是使用按下Back键时 也能传回数据
        Toast.makeText(this, "按下了Back键", Toast.LENGTH_LONG).show();
        //...
        super.onBackPressed();
    }

    public void finish(View view) {
        this.finish();
    }
}
