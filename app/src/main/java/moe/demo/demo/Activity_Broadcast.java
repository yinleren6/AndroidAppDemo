package moe.demo.demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Activity_Broadcast extends AppCompatActivity {
    final String TAG = "TAG_Broadcast_Activity";
    Toast toast;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);
        this.setTitle("Broadcast");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        toast = Toast.makeText(Activity_Broadcast.this, "回调： 网络动态广播接收器 已取消", Toast.LENGTH_LONG);
        toast.show();
        try {
            if (receiver != null) {
                unregisterReceiver(netWorkChangeReceiver);
            }
        } catch (Exception e) {
            Log.e(TAG, "没有已注册的广播接收器" + e.toString());
        }
    }

    //标准广播 异步执行 所有的广播接收器几乎都会在同一时间收到广播  效率高 无法被截断
    public void sendBroadcast(View view) {
        Intent i = new Intent();
        i.setAction("common");
        i.putExtra("key", "这是一条标准广播的额外的数据");
        sendBroadcast(i);
    }

    //有序广播 是一种同步执行的广播 同一时刻只会有一个广播接收器接受广播，当此接收器的逻辑执行完了之后广播才会继续传递，广播接收器的优先级越高 就越先收到广播
    //使用高权限接收器修改内容
    public void sendOrderedBroadcast(View view) {
        Intent i = new Intent();
        i.setAction("modify");
        sendOrderedBroadcast(i,
                null,
                new Class_FinalReceiver_Broadcast(),
                null,
                0,
                "这是有序广播内容",
                null);
    }

    //使用高权限接收器终止广播
    public void sendOrderedBroadcast2(View view) {
        Intent i = new Intent();
        i.setAction("abort");
        sendOrderedBroadcast(i,
                null,
                new Class_FinalReceiver_Broadcast(),
                null,
                0,
                "这是有序广播内容",
                null);
    }

    private Class_Broadcast_Receiver receiver;
    private NetWorkChangeReceiver netWorkChangeReceiver;

    //动态注册广播接收器
    public void register(View view) {
        receiver = new Class_Broadcast_Receiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        intentFilter.addAction("android.intent.action.ACTION_POWER_CONNECTED");
        intentFilter.addAction("android.intent.action.ACTION_POWER_DISCONNECTED");
        intentFilter.addAction("offline");

        netWorkChangeReceiver = new NetWorkChangeReceiver();
        registerReceiver(netWorkChangeReceiver, intentFilter);

        toast = Toast.makeText(getBaseContext(), "注册了动态广播", Toast.LENGTH_LONG);
        toast.show();
        Log.i(TAG, "注册了动态广播");
    }

    class NetWorkChangeReceiver extends BroadcastReceiver {
        private static final String TAG = "TAG_NetWorkChangeReceiver";

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "跨应用收到 动态广播 " + intent.getAction());

            ConnectivityManager connectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);


            if (toast != null) {
                toast.cancel();
            }
            if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {

            //TODO 抄来的
                if (connectivity != null) {
                    Network networks = connectivity.getActiveNetwork();
                    NetworkCapabilities networkCapabilities = connectivity.getNetworkCapabilities(networks);
                    if (networkCapabilities != null) {
                        if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                            Log.e("-----------wifi", "wifi");
                            toast = Toast.makeText(Activity_Broadcast.this, "网络可用", Toast.LENGTH_LONG);
                            toast.show();
                        } else if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                            Log.e("-----------流量", "手机流量");
                            toast = Toast.makeText(Activity_Broadcast.this, "网络可用", Toast.LENGTH_LONG);
                            toast.show();
                        }
                    } else {
                        Log.e("------------没有网络", "没有网络");
                        toast = Toast.makeText(Activity_Broadcast.this, "网络已断开", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }


            }

            if (intent.getAction().equals("android.intent.action.ACTION_POWER_CONNECTED")) {
                toast = Toast.makeText(Activity_Broadcast.this, "电源已连接", Toast.LENGTH_LONG);
                toast.show();

            } else if (intent.getAction().equals("android.intent.action.ACTION_POWER_DISCONNECTED")) {
                toast = Toast.makeText(Activity_Broadcast.this, "电源已断开", Toast.LENGTH_LONG);
                toast.show();
            }
        }
    }


    //按钮事件
    public void unregisterMyReceiver(View view) {
        if (toast != null) {
            toast.cancel();
        }

        try {
            if (receiver != null) {
                toast = Toast.makeText(Activity_Broadcast.this, "取消了动态广播", Toast.LENGTH_LONG);
                toast.show();
                unregisterReceiver(netWorkChangeReceiver);
                Log.i(TAG, "取消了动态广播");
            } else {
                toast = Toast.makeText(Activity_Broadcast.this, "没有已注册的广播接收器", Toast.LENGTH_LONG);
                toast.show();
                Log.i(TAG, "没有已注册的广播接收器");
            }
        } catch (Exception e) {
            if (toast != null) {
                toast.cancel();
            }

            toast = Toast.makeText(Activity_Broadcast.this, "没有已注册的广播接收器" + e.toString(), Toast.LENGTH_LONG);
            toast.show();
            Log.e(TAG, "没有已注册的广播接收器" + e.toString());
        }
    }

    public void finish(View view) {
        this.finish();
    }


}
