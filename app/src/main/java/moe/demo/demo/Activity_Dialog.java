package moe.demo.demo;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Activity_Dialog extends AppCompatActivity {
    private static final String TAG = "TAG_Dialog_Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        this.setTitle("Dialog");
        //在AndroidManifest.xml中设置 android:theme="@style/Theme.AppCompat.Dialog"
    }

    public void finish(View view) {
        this.finish();
    }
}