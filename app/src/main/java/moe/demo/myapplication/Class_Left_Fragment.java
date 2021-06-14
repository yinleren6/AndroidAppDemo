package moe.demo.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Class_Left_Fragment extends Fragment implements View.OnClickListener {

    boolean isaddToBackStack = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.left, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        getActivity().findViewById(R.id.button13).setOnClickListener(this);
        getActivity().findViewById(R.id.button9).setOnClickListener(this);


        Switch s = getActivity().findViewById(R.id.switch2);
        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    isaddToBackStack = true;
                }
                else {
                    isaddToBackStack = false;
                }
            }
        });
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onClick(View v) {

        //碎片管理器
        FragmentManager manager = getActivity().getSupportFragmentManager();
        //事物管理器
        FragmentTransaction transaction = manager.beginTransaction();

        if (v.getId() == R.id.button13) {
            //使用事物管理器 替换当前帧布局的碎片 // remove&add
            transaction.replace(R.id.framelayout, new Class_Right_Fragment());
        }
        else if (v.getId() == R.id.button9) {
            transaction.replace(R.id.framelayout, new Class_Right_Fragment2());
        }
        if (isaddToBackStack) {//把当前碎片放入栈中
            transaction.addToBackStack(null);
        }
        //提交
        transaction.commit();
    }
}
