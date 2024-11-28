package com.cuichen.banner_loop.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.cuichen.banner_loop.LoopUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class LoopAdapter<T,VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> implements LoopViewHolder<T,VH> {

    protected List<T> mDatas = new ArrayList<>();

    public LoopAdapter(List<T> datas) {
        setDatas(datas);
    }

    /**
     * 设置实体集合（可以在自己的adapter自定义，不一定非要使用）
     *
     * @param datas
     */
    public void setDatas(List<T> datas) {
        if (datas == null) {
            datas = new ArrayList<>();
        }
        mDatas.clear();
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return onCreateHolder(parent , viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
          onBindHolder(holder, mDatas , getRealPosition(position));
    }


    @Override
    public int getItemCount() {
        return getRealCount() > 1 ? getRealCount() * 1000 : getRealCount();
    }

    public int getRealCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    public int getRealPosition(int position) {
        return LoopUtils.getRealPosition( position, getRealCount());
    }

}
