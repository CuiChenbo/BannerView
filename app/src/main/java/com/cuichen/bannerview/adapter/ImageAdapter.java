package com.cuichen.bannerview.adapter;

import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cuichen.banner_view.adapter.BannerAdapter;
import com.cuichen.bannerview.adapter.holder.ImgViewHolder;
import com.cuichen.bannerview.adapter.holder.TextViewHolder;

import java.util.List;

public class ImageAdapter extends BannerAdapter<Integer, RecyclerView.ViewHolder> {

    public ImageAdapter(List<Integer> mDatas) {
        //设置数据，也可以调用banner提供的方法,或者自己在adapter中实现
        super(mDatas);
    }

    @Override
    public int getItemViewType(int position) {
        return getRealPosition(position) % 2;
    }

    //创建ViewHolder，可以用viewType这个字段来区分不同的ViewHolder
    @Override
    public RecyclerView.ViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        if(viewType == 0){
            return new ImgViewHolder(parent);
        }else {
            return new TextViewHolder(parent);
        }

    }

    @Override
    public void onBindHolder(@NonNull RecyclerView.ViewHolder holder, List<Integer> datas, int position) {
        if(holder.getItemViewType() == 0){
            ((ImgViewHolder)holder).setData(datas.get(position));
        }else {
            ((TextViewHolder)holder).setData(datas.get(position) , position);
        }
    }


}