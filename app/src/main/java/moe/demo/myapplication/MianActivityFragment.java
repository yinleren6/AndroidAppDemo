package moe.demo.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;


public class MianActivityFragment extends Fragment {
    private static final String TAG = "TAG_MianActivityFragment";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_mian_activity, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        List<ListItem2> list;
        list = new ArrayList<>();
        list.add(new ListItem2("Activity", "活动测试", R.drawable.icon));
        list.add(new ListItem2("Service", "服务测试", R.drawable.icon));
        list.add(new ListItem2("Broadcast", "广播接收器测试", R.drawable.icon));
        list.add(new ListItem2("RecyclerView", "一种更强大的 ListView", R.drawable.icon));
        list.add(new ListItem2("okhttp", "网络测试", R.drawable.icon));
        list.add(new ListItem2("Fragment", "一种碎片化布局", R.drawable.icon));
        list.add(new ListItem2("Notification", "通知测试", R.drawable.icon));
        list.add(new ListItem2("Data Storage", "三种数据持久化技术", R.drawable.icon));
        list.add(new ListItem2("Runtime Permission", "运行时权限", R.drawable.icon));
        list.add(new ListItem2("ContentProvider", "内容提供器", R.drawable.icon));
        list.add(new ListItem2("Camera&Album", "照片相机", R.drawable.icon));
        list.add(new ListItem2("Music&Video", "音乐视频", R.drawable.icon));
        list.add(new ListItem2("WebView", "浏览网页", R.drawable.icon));
        list.add(new ListItem2("XML/JSON", "数据解析", R.drawable.icon));
        list.add(new ListItem2("Service", "服务demo", R.drawable.icon));
        list.add(new ListItem2("Service", "百度地图SDK", R.drawable.icon));
        list.add(new ListItem2("CardViewActivity", " ", R.drawable.icon));


        ListView listView = getActivity().findViewById(R.id.listView);
        Intent i = new Intent();
        ListAdapter2 adapter = new ListAdapter2(getActivity(), R.layout.listview_adapter2, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Log.i(TAG, "listview 点击事件");
            switch (position) {
                case 0:
                    i.setClass(getActivity(), Activity_Activity.class);
                    break;
                case 1:
                    i.setClass(getActivity(), Activity_Service.class);
                    break;
                case 2:
                    i.setClass(getActivity(), Activity_Broadcast.class);
                    break;
                case 3:
                    i.setClass(getActivity(), Activity_RecyclerView.class);
                    break;
                case 4:
                    i.setClass(getActivity(), Activity_okhttp.class);
                    break;
                case 5:
                    i.setClass(getActivity(), Activity_Fragment.class);
                    break;
                case 6:
                    i.setClass(getActivity(), Activity_Notification.class);
                    break;
                case 7:
                    i.setClass(getActivity(), Activity_Data_Storage.class);
                    break;
                case 8:
                    i.setClass(getActivity(), RuntimePermission.class);
                    break;
                case 9:
                    i.setClass(getActivity(), Activity_ContentProvider_read.class);
                    break;
                case 10:
                    i.setClass(getActivity(), Activity_CameraAlbum.class);
                    break;
                case 11:
                    i.setClass(getActivity(), Activity_MediaPlayer.class);
                    break;
                case 12:
                    i.setClass(getActivity(), Activity_webView.class);
                    break;
                case 13:
                    i.setClass(getActivity(), Activity_XML.class);
                    break;
                case 14:
                    i.setClass(getActivity(), Download_Demo.class);
                    break;
                case 15:
                    i.setClass(getActivity(), Activity_Baidu_Map_SDk.class);
                    break;
                case 16:
                    i.setClass(getActivity(), CardViewActivity.class);
                    break;
            }

            startActivity(i);


        });


    }


}
