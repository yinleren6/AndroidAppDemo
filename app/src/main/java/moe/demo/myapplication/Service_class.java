package moe.demo.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class Service_class extends Service {
    Binder binder = new Binder();
    final String TAG = "TAG_Service_Class";

    @Override
    public void onCreate() {
        Toast.makeText(this, "onCreate", Toast.LENGTH_LONG).show();
        Log.i(TAG, "onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "onStartCommand", Toast.LENGTH_LONG).show();
        Log.i(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(this, "onBind", Toast.LENGTH_LONG).show();
        Log.i(TAG, "onBind");
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Toast.makeText(this, "onUnbind", Toast.LENGTH_LONG).show();
        Log.i(TAG, "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        Toast.makeText(this, "onRebind", Toast.LENGTH_LONG).show();
        Log.i(TAG, "onRebind");
        super.onRebind(intent);
    }


    @Override
    public void onDestroy() {
        Toast.makeText(this, "onDestroy", Toast.LENGTH_LONG).show();
        Log.i(TAG, "onDestroy");
        super.onDestroy();
    }


}
