package moe.demo.myapplication;

import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Service_Activitty extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_activity);
        this.setTitle("Service");
        Service_Service service = new Service_Service();
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
}
