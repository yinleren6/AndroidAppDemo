package moe.demo.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class A_back_Activitty extends AppCompatActivity {
    Intent i;
    EditText nameInput;
    EditText pwdInput;
    Bundle bundle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_fallback);
        this.setTitle("Activity跳转");
        i = new Intent();
        nameInput = findViewById(R.id.editTextTextPersonName);
        pwdInput = findViewById(R.id.editTextTextPassword);
    }

    public void b1(View view) {
        String name = nameInput.getText().toString();
        String pwd = pwdInput.getText().toString();

        i.setClass(this, A_back_Activitty.class);
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
        i.setClass(this, A_back_Activitty.class);
        i.putExtras(bundle);
        setResult(1, i);
        finish();
    }

    public void finish(View view) {
        this.finish();
    }
}
