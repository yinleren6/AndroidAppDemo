package moe.demo.demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Adapter_ListView extends ArrayAdapter {

    private final int id;

    public Adapter_ListView(Context context, int resource, List<Class_ListView_Item> objects) {
        super(context, resource, objects);

        this.id = resource;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Class_ListView_Item item = (Class_ListView_Item) getItem(position);

        View view = LayoutInflater.from(getContext()).inflate(id, null);

        ImageView image = view.findViewById(R.id.selfimage);
        TextView t1 = view.findViewById(R.id.selftextView01);
        TextView t2 = view.findViewById(R.id.selftextView02);

        image.setImageResource(item.getImageId());
        t1.setText(item.getTitle());
        t2.setText(item.getText());

        return view;
    }
}
