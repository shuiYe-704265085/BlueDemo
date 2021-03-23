package com.example.carl.bluedemo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class BlueAdaptor extends RecyclerView.Adapter<BlueAdaptor.ViewHolder>{
    private List<Blue> mBlueList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView blueImage;
        TextView blueName;
        TextView blueMac;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            blueImage = (ImageView) itemView.findViewById(R.id.blue_icon);
            blueName = (TextView) itemView.findViewById(R.id.blue_name);
            blueImage = (ImageView) itemView.findViewById(R.id.blue_icon);
        }
    }

    public BlueAdaptor(List<Blue> blueList) {
        mBlueList = blueList;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.blue_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Blue blue = mBlueList.get(i);
        viewHolder.blueName.setText(blue.getBlueName());
        viewHolder.blueMac.setText(blue.getMac());
        viewHolder.blueImage.setImageResource(blue.getImageId());
    }

    @Override
    public int getItemCount() {
        return mBlueList.size();
    }
}
