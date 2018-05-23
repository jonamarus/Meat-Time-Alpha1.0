package com.example.android.meat_timealpha10.Fragments;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.meat_timealpha10.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

Context mContext ;
List<PlanningItemFragment> mData;

    public RecyclerViewAdapter(Context mContext, List<PlanningItemFragment> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v ;
        v = LayoutInflater.from(mContext).inflate(R.layout.item_planning,parent,false);
        ViewHolder vHolder = new ViewHolder(v);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.tv_name.setText(mData.get(position).getName());
        holder.tv_keywords.setText(mData.get(position).getKeywords());
        holder.img_photo.setImageResource(mData.get(position).getPhoto());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void notifydatasetchanged() {
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_name;
        private TextView tv_keywords;
        private ImageView img_photo;

        public ViewHolder(View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.item_name);
            tv_keywords =  itemView.findViewById(R.id.keywords);
            img_photo = itemView.findViewById(R.id.img_meal);

        }
    }



}
