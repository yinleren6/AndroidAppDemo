package moe.demo.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;


public class CardView_Adapter extends RecyclerView.Adapter<CardView_Adapter.ViewHolder> {
    private static final String TAG = "TAG_Recycler_Adapter";

    private Context mContext;
    private final List<ListItem1> mListItem1s;

    public CardView_Adapter(List<ListItem1> mListItem1s) {
        this.mListItem1s = mListItem1s;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (mContext == null) {
            mContext = parent.getContext();
        }

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_item_adapter, parent, false);

        final ViewHolder holder = new ViewHolder(view);

//        holder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int position = holder.getAdapterPosition();
//                ListItem1 item1 = mListItem1s.get(position);
//                Intent intent = new Intent(mContext, Activity_Collapsing_Toobar.class);
//                intent.putExtra("name", item1.getName());
//                intent.putExtra("id", item1.getImageId());
//                mContext.startActivity(intent);
//            }
//        });


        return holder;
    }

    @Override
    public void onBindViewHolder(CardView_Adapter.ViewHolder holder, int position) {
        ListItem1 items = mListItem1s.get(position);

        holder.image.setImageResource(items.getImageId());
        holder.name.setText(items.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.i(TAG, "点击  ");
//                Toast.makeText(v.getContext(), "点击了第 " + holder.getAdapterPosition(), Toast.LENGTH_LONG).show();
//                Intent i = new Intent();
//                i.setClass(v.getContext(), Activity_Collapsing_Toobar.class);
//                v.getContext().startActivity(i);



                int position = holder.getAdapterPosition();
                ListItem1 item1 = mListItem1s.get(position);
                Intent intent = new Intent(mContext, Activity_Collapsing_Toobar.class);
                intent.putExtra("name", item1.getName());
                intent.putExtra("id", item1.getImageId());
                mContext.startActivity(intent);



            }
        });
        Glide.with(mContext).load(items.getImageId()).into(holder.image);

        //        holder.imageView.setOnClickListener(v -> {
        //            Log.i(TAG, "点击 图");
        //
        //            Toast.makeText(v.getContext(), "你点击了 图 " + holder.getAdapterPosition() + "--" + items.getName(), Toast.LENGTH_LONG).show();
        //        });
        //        holder.textView.setOnClickListener(v -> {
        //            Log.i(TAG, "点击 文本");
        //
        //            Toast.makeText(v.getContext(), "你点击了  " + holder.getAdapterPosition() + "--" + items.getName(), Toast.LENGTH_LONG).show();
        //        });
    }

    @Override
    public int getItemCount() {
        return mListItem1s.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private final ImageView image;
        CardView cardView;


        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            image = itemView.findViewById(R.id.selfimage);
            name = itemView.findViewById(R.id.selftext);
        }
    }


}