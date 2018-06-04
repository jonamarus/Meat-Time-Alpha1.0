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
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MainViewHolder>{

Context mContext ;
List<PlanningItemFragment> mData;
int mExpandedPosition = -1;

    public RecyclerViewAdapter(Context mContext, List<PlanningItemFragment> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v ;
        v = LayoutInflater.from(mContext).inflate(R.layout.item_planning,parent,false);
        MainViewHolder vHolder = new MainViewHolder(v);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, final int position) {

        holder.tv_name.setText(mData.get(position).getName());
        holder.tv_keywords.setText(mData.get(position).getKeywords());
        holder.img_photo.setImageResource(mData.get(position).getPhoto());

        final boolean isExpanded = position==mExpandedPosition;
        holder.expandedView.setVisibility(isExpanded?View.VISIBLE:View.GONE);
        holder.itemView.setActivated(isExpanded);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExpandedPosition = isExpanded ? -1:position;
                notifyItemChanged(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void notifydatasetchanged() {
    }

    public static class MainViewHolder extends GroupViewHolder {

        private TextView tv_name;
        private TextView tv_keywords;
        private ImageView img_photo;
        private View expandedView;

        public MainViewHolder(View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.item_name);
            tv_keywords =  itemView.findViewById(R.id.keywords);
            img_photo = itemView.findViewById(R.id.img_meal);
            expandedView = itemView.findViewById(R.id.expandedView);

        }
    }

    //public class DetailViewHolder extends ChildViewHolder {

//        private TextView artistName;

//        public DetailViewHolder(View itemView) {
//            super(itemView);
//            artistName = itemView.findViewById(R.id.artist_name);
//        }

//        public void onBind(Artist artist) {
//            artistName.setText(artist.getTitle());
        }
//    }



//}
