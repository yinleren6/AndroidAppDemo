package moe.demo.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class Activity_Collapsing_Toobar extends AppCompatActivity {
    private static final String TAG = "TAG_Activity_Collapsing_Too";

FloatingActionButton mFloatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing_toobar);

mFloatingActionButton=findViewById(R.id.floatingActionButton3);

mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Snackbar.make(v, "按下了浮动按钮", Snackbar.LENGTH_SHORT).setAction("好的", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "按下了 好的", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "点击Snackbar交互按钮监听");
            }
        }).setCallback(new Snackbar.Callback() {
            @Override
            public void onShown(Snackbar sb) {
                super.onShown(sb);
                Log.i(TAG, "Snackbar显示时的回调");
            }

            @Override
            public void onDismissed(Snackbar transientBottomBar, int event) {
                super.onDismissed(transientBottomBar, event);
                Log.i(TAG, "Snackbar消失时的回调");
            }
        }).show();
    }
});
        Intent intent = getIntent();

        String name = intent.getStringExtra("name");
        int id = intent.getIntExtra("id", 0);

        Toolbar toolbar = findViewById(R.id.toolbar3);
        CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapsingtoolbar);


        ImageView mImage = findViewById(R.id.img);

        TextView textView = findViewById(R.id.textView23);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);

            //            actionBar.setHomeAsUpIndicator(R.drawable.abc_vector_test);
        }

        collapsingToolbar.setTitle(name);

        Glide.with(this).load(id).into(mImage);
        String mContent = generateContent(name);
        textView.setText(mContent);



            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Activity_Collapsing_Toobar.this.finish();

                }
            });

    }

    String generateContent(String name) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 500; i++) {
            stringBuilder.append(name);

        }
        return stringBuilder.toString();
    }


}