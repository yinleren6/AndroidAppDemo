package moe.demo.myapplication;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Service_Activitty extends AppCompatActivity {
    ServiceConnection conn;
    final String TAG = "TAG";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_activity);
        this.setTitle("Service");
        conn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
    }


    public void b1(View view) {
        startService(new Intent(getBaseContext(), Service_Service.class));
    }

    public void b2(View view) {
        stopService(new Intent(getBaseContext(), Service_Service.class));
    }

    public void b3(View view) {

    }

    public void b4(View view) {

        startService(new Intent(getBaseContext(), Service_Service.class));
    }

    public void finish(View view) {
        this.finish();
    }
}
