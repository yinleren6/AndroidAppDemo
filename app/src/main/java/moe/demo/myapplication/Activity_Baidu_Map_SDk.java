package moe.demo.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Activity_Baidu_Map_SDk extends BaseActivity {
    private static final String TAG = "TAG_Activity_Baidu_Map_SKD";
    private static TextView textView;
    public LocationClient mLocationClient;
    RadioGroup mGroup;
    int method = 0;
    LocationClientOption option;
    TextView mode;
    Switch mSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baidu_map_skd);

        mode = findViewById(R.id.mode);
        textView = findViewById(R.id.location);
        findViewById(R.id.button55).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //开始定位
                requestLocation();
            }
        });
        findViewById(R.id.button56).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Baidu_Map_SDk.this, Activity_BaiduMapView.class);
                startActivity(intent);
            }
        });

        mGroup = findViewById(R.id.radioGroup2);
        mGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == R.id.radioButton) {
                    method = 0;
                }
                else if (checkedId == R.id.radioButton5) {
                    method = 1;
                }
                else if (checkedId == R.id.radioButton7) {
                    method = 2;
                }
                sermode();
            }
        });
        initPermission();
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(new MyLocationListerner());
        option = new LocationClientOption();
        mSwitch = findViewById(R.id.switch1);
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    option.setScanSpan(5000);
                }
                else {
                    option.setScanSpan(0);
                }
            }
        });

        sermode();
        requestLocation();
        if (option.getScanSpan() > 0) {
            mSwitch.setChecked(true);
        }
        else {
            mSwitch.setChecked(false);
        }

    }

    private void initPermission() {
        List<String> permissionList = new ArrayList<>();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
            Log.i(TAG, "没有ACCESS_FINE_LOCATION");
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.ACCESS_COARSE_LOCATION);
            Log.i(TAG, "没有ACCESS_COARSE_LOCATION");
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
            Log.i(TAG, "没有READ_PHONE_STATE");
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            Log.i(TAG, "没有WRITE_EXTERNAL_STORAGE");
        }


        if (!permissionList.isEmpty()) {
            String[] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(Activity_Baidu_Map_SDk.this, permissions, 1);
            Log.i(TAG, " 请求权限");
        }
        LocationManager locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!locManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Log.i(TAG, "没有GPS权限");
        }


    }

    void sermode() {
        if (method == 0) {
            //高精度模式
            option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
            mode.setText("当前模式：高精度模式");
            Log.i(TAG, "当前模式" + "Hight_Accuracy");
        }
        if (method == 1) {
            //仅限设备 GPS模式
            option.setLocationMode(LocationClientOption.LocationMode.Device_Sensors);
            mode.setText("当前模式：仅限设备");
            Log.i(TAG, "当前模式" + "Device_Sensors");
        }
        if (method == 2) {
            //节能模式
            option.setLocationMode(LocationClientOption.LocationMode.Battery_Saving);
            mode.setText("当前模式：节能模式");
            Log.i(TAG, "当前模式" + "Battery_Saving");
        }

    }

    private void requestLocation() {
        //设置刷新位置间隔 一般用于实时导航


        mLocationClient.setLocOption(option);
        //是否需要具体地址 xx省xx市xx区
        option.setIsNeedAddress(true);
        mLocationClient.start();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    for (int ressult : grantResults) {
                        if (ressult != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(this, "需要权限", Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }

                    requestLocation();
                }
                else {
                    Toast.makeText(this, "没有权限", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();
    }

    public static class MyLocationListerner extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            StringBuilder pos = new StringBuilder();
            pos.append("维度：").append(bdLocation.getLatitude()).append("\n");
            pos.append("经线：").append(bdLocation.getLongitude()).append("\n");
            pos.append("国家：").append(bdLocation.getCountry()).append("\n");
            pos.append("省：").append(bdLocation.getProvince()).append("\n");
            pos.append("市：").append(bdLocation.getCity()).append("\n");
            pos.append("区：").append(bdLocation.getDistrict()).append("\n");
            pos.append("街道：").append(bdLocation.getStreet()).append("\n");


            pos.append("定位方式：");
            if (bdLocation.getLocType() == BDLocation.TypeGpsLocation) {
                pos.append("GPS");
            }
            else if (bdLocation.getLocType() == BDLocation.TypeNetWorkLocation) {
                pos.append("网络");
            }
            else if (bdLocation.getLocType() == BDLocation.TypeServerCheckKeyError) {
                pos.append("AK不存在或者非法");
            }
            else {
                pos.append("Null");
            }
            pos.append("\n状态码：" + bdLocation.getLocType());
            textView.setText(pos);
        }
    }
}