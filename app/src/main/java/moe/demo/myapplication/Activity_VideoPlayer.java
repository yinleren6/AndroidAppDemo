package moe.demo.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.MediaController;
import android.widget.Spinner;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class Activity_VideoPlayer extends AppCompatActivity {
    MediaController mController;
    VideoView mVideoView;
    int[] uri = {R.raw.trump, R.raw.ah};
    Spinner spinner;

    String u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        setTitle("视频播放");
        init();
    }

    private void init() {
        u = "android.resource://" + getPackageName() + "/";
        mVideoView = findViewById(R.id.videoView);
        //res 文件夹路径
        mVideoView.setVideoPath(u + uri[0]);
        {//sd 卡路径
            //            File file = new File(Environment.getExternalStorageDirectory(), "文件路径");
            //            mVideoView.setVideoPath(file.getPath());
        }
        //TODO 从文件手动选择 参考选择照片
        mController = new MediaController(this);
        mVideoView.setMediaController(mController);
        mController.setMediaPlayer(mVideoView);
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                              @Override
                                              public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                  mVideoView.setVideoPath(u + uri[position]);
                                              }

                                              @Override
                                              public void onNothingSelected(AdapterView<?> parent) {

                                              }
                                          }

        );
        {
            //            mVideoView.start();
            //            mVideoView.pause();
            //              mVideoView.resume(); //重新播放
            //            mVideoView.suspend();  //释放资源 一般在OnDestory中使用
        }


    }

}