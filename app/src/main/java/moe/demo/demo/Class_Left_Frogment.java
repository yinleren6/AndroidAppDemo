package moe.demo.demo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Class_Left_Frogment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.left, container, false);
    }

    boolean a = true;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        //获取按钮对象
        Button button = (Button) getActivity().findViewById(R.id.button13);
        //给按钮设置点击监听
        button.setOnClickListener(v -> {
            //碎片管理器
            FragmentManager manager = getActivity().getSupportFragmentManager();
            //事物管理器
            FragmentTransaction transaction = manager.beginTransaction();
            //使用事物管理器 替换当前帧布局的碎片 // remove&add
            if (a) {
                transaction.replace(R.id.framelayout, new Class_Right_Frogment2());
                a = false;
            } else {
                transaction.replace(R.id.framelayout, new Class_Right_Frogment());
                a = true;
            }


            //把当前碎片放入栈中
            transaction.addToBackStack(null);
            //提交
            transaction.commit();
        });
        super.onActivityCreated(savedInstanceState);
    }
}
