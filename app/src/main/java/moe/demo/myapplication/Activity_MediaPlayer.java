package moe.demo.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Activity_MediaPlayer extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player);
        findViewById(R.id.button36).setOnClickListener(this);
        findViewById(R.id.button37).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        if (v.getId() == R.id.button36) {
            intent = new Intent(this, Activity_MusicPlayer.class);
        }
        else if (v.getId() == R.id.button37) {
            intent = new Intent(this, Activity_VideoPlayer.class);
        }
        startActivity(intent);
        finish();
    }
}