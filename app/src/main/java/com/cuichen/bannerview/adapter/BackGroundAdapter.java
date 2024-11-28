package com.cuichen.bannerview.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;


import androidx.recyclerview.widget.RecyclerView;

import com.cuichen.banner_view.adapter.BannerAdapter;
import com.cuichen.bannerview.R;

import java.util.List;

public class BackGroundAdapter extends BannerAdapter<Integer, BackGroundAdapter.OnViewHolder> {

    public BackGroundAdapter(List<Integer> mDatas) {
        //设置数据，也可以调用banner提供的方法,或者自己在adapter中实现
        super(mDatas);
    }


    //创建ViewHolder，可以用viewType这个字段来区分不同的ViewHolder
    @Override
    public OnViewHolder onCreateHolder(ViewGroup parent, int viewType) {
            return new OnViewHolder(parent);

    }

    @Override
    public void onBindHolder(OnViewHolder holder, List<Integer> datas, int position) {
        holder.setData(datas.get(position));
    }


    class OnViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public OnViewHolder(ViewGroup parent){
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_banner , parent,false));
            this.imageView = itemView.findViewById(R.id.iv);
        }

        public void setData(Integer data){
            imageView.setBackgroundResource(data);
        }
    }

}

