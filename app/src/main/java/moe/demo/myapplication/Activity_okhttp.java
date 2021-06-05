package moe.demo.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Activity_okhttp extends AppCompatActivity implements View.OnClickListener {
    private static final MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
    private final String TAG = "TAG_OkHttp_测试";
    //1 创建 OkHttpClient 实例
    private final OkHttpClient client = new OkHttpClient();
    TextView textView;

    EditText editText;
    String editLink;
    URL url;
    ;


    public Activity_okhttp() throws MalformedURLException {
    }
    //===========post

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
        setTitle("okHttp");
        textView = findViewById(R.id.textView7);
        editText = findViewById(R.id.editTextTextPersonName2);


        findViewById(R.id.button43).setOnClickListener(this);
        findViewById(R.id.button18).setOnClickListener(this);

        try {
            url = new URL(editLink = editText.getText().toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    //============get
    public void get(View view) {
        editLink = editText.getText().toString();
        //2 创建 request 对象
        final Request request = new Request.Builder().url(editLink).get().build();
        //3 创建 Call 对象 获取 callback响应并把响应体返回
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String a = Objects.requireNonNull(response.body()).string();
                Log.i(TAG, "onResponse: " + a);

                Activity_okhttp.this.runOnUiThread(() -> {

                    textView.setText(a);
                });
            }
        });
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

    //POST方式提交String
    public void post1(View view) {
        editLink = editText.getText().toString();
        String re = "xxx";
        RequestBody requestBody = RequestBody.Companion.create(re, mediaType);
        Request request = new Request.Builder().url(url).post(requestBody).build();

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
                String onResponse = Objects.requireNonNull(response.body()).string();
                Log.d(TAG, "onResponse: " + onResponse);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(onResponse);
                    }
                });
            }
        });
    }


    //POST方式提交表单
    void post4() throws NullPointerException {

        RequestBody requestBody = new FormBody.Builder().add("key", "value").add("key2", "value2").build();
        Request request = new Request.Builder().url(url).post(requestBody).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, response.protocol() + " " + response.code() + " " + response.message());
                Headers headers = response.headers();
                for (int i = 0; i < headers.size(); i++) {
                    Log.d(TAG, headers.name(i) + ":" + headers.value(i));
                }
                Log.d(TAG, "onResponse: " + response.body().string());
            }
        });
    }


    public void finish(View view) {
        this.finish();
    }
    //以下是android 传统的http

    void http() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;


                try {


                    connection = (HttpURLConnection) url.openConnection();
                    //get方法
                    connection.setRequestMethod("GET");
                    //POST 方法
                    //                    connection.setRequestMethod("POST");
                    //                    DataOutputStream out = new DataOutputStream(connection.getOutputStream());
                    //提交数据
                    //                    out.writeBytes("key=value&key2=value2");


                    connection.setConnectTimeout(8000);

                    connection.setReadTimeout(8000);
                    Log.i(TAG, "15");

                    InputStream in = connection.getInputStream();
                    Log.i(TAG, "16");
                    //下面对获取到的输入流进行读取
                    reader = new BufferedReader(new InputStreamReader(in));
                    Log.i(TAG, "17");
                    StringBuilder response = new StringBuilder();
                    Log.i(TAG, "18");
                    String line;
                    Log.i(TAG, "2");
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                        Log.i(TAG, line);
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.i(TAG, "3");
                            textView.setText(response.toString());
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button18) {
            post4();
        }
        else if (v.getId() == R.id.button43) {
            http();
        }
    }
}
