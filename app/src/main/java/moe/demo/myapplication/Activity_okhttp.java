package moe.demo.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Activity_okhttp extends AppCompatActivity {
    private static final MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
    final String a = "0";
    private final String TAG = "TAG_OkHttp_测试";
    private final OkHttpClient client = new OkHttpClient();
    TextView textView;
    final Callback callback_get =
            new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e(TAG, "onFailure: " + e.getMessage());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String a = Objects.requireNonNull(response.body()).string();
                    Log.i(TAG, "onResponse: " + a);

                    Activity_okhttp.this.runOnUiThread(() -> {
                        Toast.makeText(Activity_okhttp.this, "T T T T T" + a, Toast.LENGTH_LONG).show();
                        textView.setText(a);
                    });
                }
            };
    EditText editText;
    String url;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
        setTitle("okHttp");
        textView = findViewById(R.id.textView7);
        editText = findViewById(R.id.editTextTextPersonName2);

        textView.setOnClickListener(v -> textView.setText(a));
        url = editText.getText().toString();
    }
    //===========post

    //============get
    public void get(View view) {
        url = editText.getText().toString();
        //String url = "https://push.xuthus.cc/send/098b8c2d0906da371c2470167afd468b?c=";
        final Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        //获取响应并把响应体返回
        Call call = client.newCall(request);
        call.enqueue(callback_get);
    }

    //POST方式提交String
    public void post(View view) {
        url = "https://baidu.com";
        post1(url);
    }


    //POST方式提交流
//    void post2(String url) throws NullPointerException {
//        RequestBody requestBody = new RequestBody() {
//            @Override
//            public MediaType contentType() {
//                return mediaType;
//            }
//
//            @Override
//            public void writeTo(BufferedSink sink) throws IOException {
//                sink.writeUtf8("I am Jdqm.");
//            }
//        };
//
//        Request request = new Request.Builder()
//                .url(url)
//                .post(requestBody)
//                .build();
//
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.d(TAG, "onFailure: " + e.getMessage());
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                Log.d(TAG, response.protocol() + " " + response.code() + " " + response.message());
//                Headers headers = response.headers();
//                for (int i = 0; i < headers.size(); i++) {
//                    Log.d(TAG, headers.name(i) + ":" + headers.value(i));
//                }
//                Log.d(TAG, "onResponse: " + response.body().string());
//            }
//        });
//    }

    //POST提交文件
//    void post3(String url) throws NullPointerException {
//        MediaType mediaType = MediaType.parse("text/x-markdown; charset=utf-8");
//
//        File file = new File("test.md");
//        Request request = new Request.Builder()
//                .url("https://api.github.com/markdown/raw")
//                .post(RequestBody.create(mediaType, file))
//                .build();
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.d(TAG, "onFailure: " + e.getMessage());
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                Log.d(TAG, response.protocol() + " " + response.code() + " " + response.message());
//                Headers headers = response.headers();
//                for (int i = 0; i < headers.size(); i++) {
//                    Log.d(TAG, headers.name(i) + ":" + headers.value(i));
//                }
//                Log.d(TAG, "onResponse: " + response.body().string());
//            }
//        });
//
//
//    }

    //POST方式提交表单
//    void post4(String url) throws NullPointerException {
//
//        RequestBody requestBody = new FormBody.Builder()
//                .add("search", "Jurassic Park")
//                .build();
//        Request request = new Request.Builder()
//                .url(url)
//                .post(requestBody)
//                .build();
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.d(TAG, "onFailure: " + e.getMessage());
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                Log.d(TAG, response.protocol() + " " + response.code() + " " + response.message());
//                Headers headers = response.headers();
//                for (int i = 0; i < headers.size(); i++) {
//                    Log.d(TAG, headers.name(i) + ":" + headers.value(i));
//                }
//                Log.d(TAG, "onResponse: " + response.body().string());
//            }
//        });
//    }

    //POST方式提交String
    void post1(String url) throws NullPointerException {
        String re = "xxxxxx";
        RequestBody requestBody = RequestBody.Companion.create(re, mediaType);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        Log.i(TAG, requestBody.toString());
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Headers headers = response.headers();
                for (int i = 0; i < headers.size(); i++) {
                    Log.d(TAG, headers.name(i) + ":" + headers.value(i));
                }
                Log.d(TAG, "onResponse: " + Objects.requireNonNull(response.body()).string());
            }
        });
    }

    public void finish(View view) {
        this.finish();
    }
}
