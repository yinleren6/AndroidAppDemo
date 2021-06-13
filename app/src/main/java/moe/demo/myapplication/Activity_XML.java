package moe.demo.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Activity_XML extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "TAG_Activity_XML";

    private TextView mTvResponseText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml);
        setTitle("解析XML和json");
        mTvResponseText = findViewById(R.id.textView15);
        findViewById(R.id.button44).setOnClickListener(this);
        findViewById(R.id.button45).setOnClickListener(this);
        findViewById(R.id.button46).setOnClickListener(this);
        findViewById(R.id.button47).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button44) {
            pull();
        }
        if (v.getId() == R.id.button45) {
            sax();
        }
        if (v.getId() == R.id.button46) {
            JSONObject();
        }
        if (v.getId() == R.id.button47) {
            GSON();
        }

    }

    private void pull() {
        //开启新线程做耗时操作
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();//创建一个OkHttpClient实例
                    Request request = new Request.Builder().url("http://192.168.123.10:8080/data.xml")//设置目标网络地址
                            .build();//创建一个Request对象
                    //同步方法
                    Response response = client.newCall(request).execute();//发送请求获取服务器返回对象
                    String responseData = response.body().string();

                    //异步方法
                    //                Call call = client.newCall(request);
                    //                call.enqueue(new Callback() {
                    //                    @Override
                    //                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    //
                    //                    }
                    //
                    //                    @Override
                    //                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    //                        String respone = response.body().string();
                    //                        try {
                    //                            parseXMLWithPull(respone);
                    //                            Log.i(TAG, "parseXMLWithPull");
                    //                        } catch (XmlPullParserException e) {
                    //                            e.printStackTrace();
                    //                        }
                    //                    }
                    //                });

                    //解析XML
                    parseXMLWithPull(responseData);
                } catch (IOException | XmlPullParserException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parseXMLWithPull(String xmldata) throws XmlPullParserException, IOException {
        StringBuilder builder = new StringBuilder();

        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        XmlPullParser parser = factory.newPullParser();

        Log.i(TAG, "传入\n" + xmldata);

        parser.setInput(new StringReader(xmldata));

        int eventType = parser.getEventType();
        String id = "";
        String name = "";
        String version = "";
        Log.i(TAG, "evenType0: " + eventType);

        while (eventType != XmlPullParser.END_DOCUMENT) {

            String nodeName = parser.getName();

            Log.i(TAG, "evenType1: " + eventType);

            Log.i(TAG, "nodeName: " + parser.getName());


            switch (eventType) {
                case XmlPullParser.START_TAG: { // 2
                    if ("id".equals(nodeName)) {
                        id = parser.nextText();
                    }
                    else if ("name".equals(nodeName)) {
                        name = parser.nextText();
                    }
                    else if ("version".equals(nodeName)) {
                        version = parser.nextText();
                    }
                    break;
                }
                case XmlPullParser.END_TAG: { // 3
                    if ("app".equals(nodeName)) {
                        builder.append("id:" + id + "\n").append("name:" + name + "\n").append("version:" + version + "\n").append("-----------------------------\n");
                        Log.i(TAG, "id " + id);
                        Log.i(TAG, "name " + name);
                        Log.i(TAG, "version " + version);
                    }
                    break;
                }

                default:
                    break;
            }
            Log.i(TAG, "evenType2: " + eventType);
            eventType = parser.next();
            Log.i(TAG, "evenType3: " + eventType);
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Log.i(TAG, "builder.toString(): <<\n" + builder.toString() + ">>");
                mTvResponseText.setText(builder.toString());
            }
        });
    }

    private void sax() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();//创建一个OkHttpClient实例
                    Request request = new Request.Builder().url("http://192.168.123.10:8080/data.xml")//设置目标网络地址
                            .build();//创建一个Request对象
                    //同步方法
                    Response response = client.newCall(request).execute();//发送请求获取服务器返回对象
                    String responseData = response.body().string();

                    parseXMLWithSax(responseData);
                } catch (Exception e) {
                }
            }
        }).start();
    }

    private void parseXMLWithSax(String xmldata) throws ParserConfigurationException, SAXException {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            XMLReader reader = factory.newSAXParser().getXMLReader();
            SAXHandler handler = new SAXHandler();
            reader.setContentHandler(handler);
            reader.parse(new InputSource(new StringReader(xmldata)));

        } catch (Exception e) {
        }

    }

    private void JSONObject() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();//创建一个OkHttpClient实例
                    Request request = new Request.Builder().url("http://192.168.123.10:8080/data2.json")//设置目标网络地址
                            .build();//创建一个Request对象
                    //同步方法
                    Response response = client.newCall(request).execute();//发送请求获取服务器返回对象
                    String responseData = response.body().string();

                    parseJson(responseData);
                } catch (Exception e) {
                }
            }
        }).start();
    }

    private void parseJson(String jsonData) {
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String name = jsonObject.getString("name");
                String version = jsonObject.getString("version");
                Log.i(TAG, id);
                Log.i(TAG, name);
                Log.i(TAG, version);
            }


        } catch (JSONException e) {
        }

    }

    private void GSON() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();//创建一个OkHttpClient实例
                    Request request = new Request.Builder().url("http://192.168.123.10:8080/data2.json")//设置目标网络地址
                            .build();//创建一个Request对象
                    //同步方法
                    Response response = client.newCall(request).execute();//发送请求获取服务器返回对象
                    String responseData = response.body().string();

                    parseGson(responseData);
                } catch (Exception e) {
                }
            }
        }).start();
    }

    private void parseGson(String jsonData) {
        Gson gson = new Gson();
        List<GSONAppClass> appList = gson.fromJson(jsonData, new TypeToken<List<GSONAppClass>>() {}.getType());
        for (GSONAppClass app : appList) {
            Log.i(TAG, app.getId());
            Log.i(TAG, app.getName());
            Log.i(TAG, app.getVersion());
        }

    }
}