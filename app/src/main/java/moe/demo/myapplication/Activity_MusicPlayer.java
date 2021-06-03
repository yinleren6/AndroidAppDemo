package moe.demo.myapplication;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class Activity_MusicPlayer extends AppCompatActivity {
    MediaPlayer loaclPlayer;
    MediaPlayer reslPlayer;
    MediaPlayer onLinelPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

        a();
        b();
        try {
            c();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void a() {
        loaclPlayer = new MediaPlayer();
        try {
            loaclPlayer.setDataSource(Environment.getExternalStorageDirectory().getPath() + "/test.mp3");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void b() {
        reslPlayer = MediaPlayer.create(this, R.raw.test);
    }

    void c() throws IOException {
        onLinelPlayer = new MediaPlayer();
        onLinelPlayer.setDataSource("http://192.168.123.10/D%3A/media/music/music/music/DJ%20Okawari%20-%20Perfect%20Blue.mp3");
    }
}