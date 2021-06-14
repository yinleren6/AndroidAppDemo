package moe.demo.myapplication;

import android.os.Bundle;
import android.view.View;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

public class Activity_BaiduMapView extends BaseActivity {
    static BaiduMap baiduMap;
    static boolean isFirstLocate = true;
    public LocationClient mLocationClient;
    MapView mapView;

    private static void navigateTo(BDLocation location) {
        if (isFirstLocate) {
            LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
            MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(ll);
            baiduMap.animateMapStatus(update);
            update = MapStatusUpdateFactory.zoomTo(12.5f);
            baiduMap.animateMapStatus(update);
            isFirstLocate = false;

        }

        MyLocationData.Builder builder = new MyLocationData.Builder();
        builder.latitude(location.getLatitude());
        builder.longitude(location.getLongitude());
        MyLocationData data = builder.build();
        baiduMap.setMyLocationData(data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SDKInitializer.initialize(getApplication());
        //自定义页面主题 Theme.MyApplication.NoActionBar
        //设置活动全屏模式 状态栏透明
        //实现沉浸式效果
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        setContentView(R.layout.activity_baidu_map_view);
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(new MyLocationListerner());
        mapView = findViewById(R.id.bmapView);
        baiduMap = mapView.getMap();
        baiduMap.setMyLocationEnabled(true);
        mLocationClient.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();
        mapView.onDestroy();
        baiduMap.setMyLocationEnabled(false);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    public static class MyLocationListerner extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {


            if (bdLocation.getLocType() == BDLocation.TypeNetWorkLocation || bdLocation.getLocType() == BDLocation.TypeGpsLocation) {

                navigateTo(bdLocation);
            }


        }
    }
}