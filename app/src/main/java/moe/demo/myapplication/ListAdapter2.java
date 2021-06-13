package moe.demo.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ListAdapter2 extends ArrayAdapter {

    private final int id;

    public ListAdapter2(Context context, int resource, List<ListItem2> objects) {
        super(context, resource, objects);

        this.id = resource;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListItem2 item = (ListItem2) getItem(position);

        View view = LayoutInflater.from(getContext()).inflate(id, null);

        ImageView image = view.findViewById(R.id.selfimage);
        TextView t1 = view.findViewById(R.id.selftext);
        TextView t2 = view.findViewById(R.id.selftextView02);

        image.setImageResource(item.getImageId());
        t1.setText(item.getTitle());
        t2.setText(item.getText());

        return view;
    }
}
