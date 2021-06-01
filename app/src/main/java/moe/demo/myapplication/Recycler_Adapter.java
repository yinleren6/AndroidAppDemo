package moe.demo.myapplication;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class Recycler_Adapter extends RecyclerView.Adapter<Recycler_Adapter.ViewHolder> {

    private static final String TAG = "TAG_Recycler_Adapter";
    private final List<ListItem1> datas;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageView;
        private final TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.selfimage);
            textView = itemView.findViewById(R.id.selftextView01);
        }
    }

    public Recycler_Adapter(List<ListItem1> datas) {
        this.datas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_adapter1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Recycler_Adapter.ViewHolder holder, int position) {
        ListItem1 items = datas.get(position);

        holder.imageView.setImageResource(items.getImageId());
        holder.textView.setText(items.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "点击了第 " + holder.getAdapterPosition(), Toast.LENGTH_LONG).show();
            }
        }); holder.imageView.setOnClickListener(v -> {
            Log.i(TAG, "点击 图");

            Toast.makeText(v.getContext(), "你点击了 图 " + holder.getAdapterPosition() + "--" + items.getName(), Toast.LENGTH_LONG).show();
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
}