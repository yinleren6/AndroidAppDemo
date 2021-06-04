package moe.demo.myapplication;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Activity_webView extends AppCompatActivity {
    WebView mWebView;
    EditText mEditText;
    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        setTitle("WebViiew");

        mWebView = findViewById(R.id.webView);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.loadUrl("https://www.baidu.com");
        //android 9.0 (API 28) 之后，默认情况下禁用非加密明文http支持，网页会出现 net::ERR_CLEARTEXT_NOT_PERMITTED 无法加载url
        //https不受影响  方案
        // 1.使用 https
        // 2.调整targetSdkVersion降到27以下
        // 2. 在清单文件中配置 android:usesCleartextTraffic="true"

        mEditText = findViewById(R.id.uri);
        mButton = findViewById(R.id.button42);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebView.loadUrl(mEditText.getText().toString());

            }
        });
    }


}