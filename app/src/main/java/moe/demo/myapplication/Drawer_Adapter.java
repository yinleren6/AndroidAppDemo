package moe.demo.myapplication;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class Drawer_Adapter extends RecyclerView.Adapter<Drawer_Adapter.ViewHolder> {

    private static final String TAG = "TAG_Drawer_Adapter";
    private final List<ListItem1> datas;

    public Drawer_Adapter(List<ListItem1> datas) {
        this.datas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_adapter1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Drawer_Adapter.ViewHolder holder, int position) {
        ListItem1 items = datas.get(position);

        holder.imageView.setImageResource(items.getImageId());
        holder.textView.setText(items.getName());

        //项目点击监听
        holder.itemView.setOnClickListener(v -> {
            Toast.makeText(v.getContext(), "点击了侧边第 " + holder.getAdapterPosition(), Toast.LENGTH_LONG).show();
            Intent i = new Intent();
            switch (position) {
                case 0:
                    i.setClass(v.getContext(), Activity_Activity.class);
                    v.getContext().startActivity(i);
                    break;
                case 1:
                    i.setClass(v.getContext(), Activity_Service.class);
                    v.getContext().startActivity(i);
                    break;
                case 2:
                    i.setClass(v.getContext(), Activity_Broadcast.class);
                    v.getContext().startActivity(i);
                    break;
                case 3:
                    //采用广播的机制退出APP

                    Intent intent = new Intent();
                    intent.setAction("exit_app");
                    v.getContext().sendBroadcast(intent);
                    break;
            }

        });
//        //文本点击监听
//        holder.textView.setOnClickListener(v -> {
//            Log.i(TAG, "点击文本" + items.getName());
//            Toast.makeText(v.getContext(), "点击了文本 " + holder.getAdapterPosition(), Toast.LENGTH_LONG).show();
//        });
//
//
//        //图片点击监听
//
//        holder.imageView.setOnClickListener(v -> {
//            Log.i(TAG, "点击图" + items.getName());
//            Toast.makeText(v.getContext(), "点击了图 " + holder.getAdapterPosition(), Toast.LENGTH_LONG).show();
//        });

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageView;
        private final TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.selfimage);
            textView = itemView.findViewById(R.id.selftextView01);
        }
    }

}