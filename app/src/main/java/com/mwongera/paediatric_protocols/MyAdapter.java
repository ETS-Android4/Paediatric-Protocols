package com.mwongera.paediatric_protocols;

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by mwongera on 2/10/17.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<item> itemList;
    private ClickListener clickListener = null;
    public MyAdapter (List<item> itemList) {
        this.itemList = itemList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView icon;
        private LinearLayout main;

        public MyViewHolder(final View parent) {
            super(parent);

            title = (TextView) parent.findViewById(R.id.title);
            icon = (ImageView) parent.findViewById(R.id.icon);
            main = (LinearLayout) parent.findViewById(R.id.main);
            main.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if(clickListener != null){
                        clickListener.itemClicked(v,getAdapterPosition());
                    }
                }
            });
        }
    }

    public void setClickListener(ClickListener clickListener){
        this.clickListener = clickListener;
    }
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);

        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        item row = itemList.get(position);
        holder.title.setText(row.getTitle());
        holder.icon.setImageResource(row.getImageId());
    }
    @Override
    public int getItemCount() {
        return itemList.size();
    }
}

