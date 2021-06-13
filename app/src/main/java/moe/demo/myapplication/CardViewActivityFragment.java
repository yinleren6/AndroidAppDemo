package moe.demo.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class CardViewActivityFragment extends Fragment {

    RecyclerView recyclerView;
    List<ListItem1> list = new ArrayList<>();
    ListItem1[] datas = {new ListItem1(R.drawable.icon, "1"), new ListItem1(R.drawable.icon, "2"), new ListItem1(R.drawable.icon, "3"), new ListItem1(R.drawable.icon, "4"), new ListItem1(R.drawable.icon, "5"), new ListItem1(R.drawable.icon, "6"), new ListItem1(R.drawable.icon, "7"), new ListItem1(R.drawable.icon, "8"), new ListItem1(R.drawable.icon, "9"), new ListItem1(R.drawable.icon, "10")};
    CardView_Adapter adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_card_view, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView = getActivity().findViewById(R.id.recyclerview3);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        adapter = new CardView_Adapter(list);
        recyclerView.setAdapter(adapter);


        list.clear();
        for (int i = 0; i < 50; i++) {
            Random random = new Random();
            int index = random.nextInt(datas.length);
            list.add(datas[index]);

        }
    }
}
