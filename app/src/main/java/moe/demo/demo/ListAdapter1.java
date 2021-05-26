package moe.demo.demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ListAdapter1 extends ArrayAdapter {

    private final int id;

    public ListAdapter1(Context context, int resource, List<ListItem1> objects) {
        super(context, resource, objects);

        this.id = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListItem1 item = (ListItem1) getItem(position);

        View view = LayoutInflater.from(getContext()).inflate(id, null);

        ImageView image = view.findViewById(R.id.selfimage);
        TextView t1 = view.findViewById(R.id.selftextView01);

        image.setImageResource(item.getImageId());
        t1.setText(item.getName());
        return view;
    }
}
