package moe.demo.myapplication;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Activity_MusicPlayer extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "TAG_Activity_MusicPlayer";
    MediaPlayer mPlayer;

    SeekBar mSeekBar;

    TextView textView;
    Boolean isPlaying = false;
    private Button mLoop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);
        init();

        Log.i(TAG, "oncreat");


        try {
            initPlayer1();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Handler handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {


                textView.setText(format(msg.what) + "/" + format(mPlayer.getDuration()));
                mSeekBar.setProgress(msg.what);

            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                do {
                    while (isPlaying && mPlayer != null) {

                        handler.sendEmptyMessage(mPlayer.getCurrentPosition());
                        try {

                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Log.i(TAG, "run:" + mPlayer.getCurrentPosition());
                    }
                } while (true);
            }
        }).start();

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mPlayer.seekTo(seekBar.getProgress());

            }
        });
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mPlayer.seekTo(0);
                mSeekBar.setProgress(0);
                isPlaying = false;
                mPlayer.release();
            }
        });
    }

    private void init() {
        findViewById(R.id.button34).setOnClickListener(this);
        findViewById(R.id.button35).setOnClickListener(this);
        findViewById(R.id.button38).setOnClickListener(this);
        findViewById(R.id.button39).setOnClickListener(this);
        findViewById(R.id.button40).setOnClickListener(this);
        findViewById(R.id.button41).setOnClickListener(this);


        mLoop = findViewById(R.id.button35);
        textView = findViewById(R.id.textView14);
        mSeekBar = findViewById(R.id.seekBar);

    }

    String format(long time) {
        SimpleDateFormat var = new SimpleDateFormat("mm:ss");
        return var.format(new Date(time));

    }

    void initPlayer1() throws IOException {
        try {
            mPlayer = new MediaPlayer();

            mPlayer.setDataSource(Environment.getExternalStorageDirectory().getPath() + "/test.mp3");

            mPlayer.prepare();
            Log.i(TAG, "准备完成");
            mSeekBar.setMax(mPlayer.getDuration());
            mPlayer.setLooping(false);

        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }

    void initPlayer2() throws IOException {
        mPlayer = MediaPlayer.create(this, R.raw.thomas);
    }

    void initPlayer3() throws IOException {
        //TODO 加载不了网络资源
        mPlayer = new MediaPlayer();
        mPlayer.setDataSource("http://192.168.123.10/media/23.MP3");


    }

    @Override
    public void onClick(View v) {


        if (v.getId() == R.id.button35) {

            mPlayer.setLooping(!mPlayer.isLooping());
            if (mPlayer.isLooping()) {
                mLoop.setText("Loop On");
            }
            else {
                mLoop.setText("Loop Off");
            }
        }
        if (v.getId() == R.id.button34) {

            if (isPlaying) {
                mPlayer.pause();
                Log.i(TAG, "暂停");
            }
            else {
                mPlayer.start();
                Log.i(TAG, "播放");
            }
            isPlaying = !isPlaying;
        }


        if (v.getId() == R.id.button38) {
            Log.i(TAG, "停止");
            mPlayer.stop();
            isPlaying = false;
            try {
                mPlayer.prepare();
            } catch (Exception e) {
                Log.e(TAG, e.toString());
            }
        }

        if (v.getId() == R.id.button39) {
            try {
                mPlayer.release();
                isPlaying = false;
                initPlayer1();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (v.getId() == R.id.button40) {
            try {
                mPlayer.release();
                isPlaying = false;
                initPlayer2();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (v.getId() == R.id.button41) {
            try {
                mPlayer.release();
                isPlaying = false;
                initPlayer3();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}