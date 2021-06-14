package moe.demo.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;

public class Class_Right_Fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.right, container, false);
    }

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);

    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        super.onCreate(savedInstanceState);
        getActivity().findViewById(R.id.button57).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "动态加载的fragment的按钮事件", Snackbar.LENGTH_SHORT).setAction("好的", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(new MyContext().getContext(), "按了 好的", Toast.LENGTH_SHORT).show();
                        Log.i("动态加载的fragment的按钮事件", "点击Snackbar交互按钮监听");
                    }
                }).show();
            }
        });

    }
}

