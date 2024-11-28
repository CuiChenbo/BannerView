package com.cuichen.banner_loop.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public interface LoopViewHolder<T,VH extends RecyclerView.ViewHolder> {

    VH onCreateHolder(@NonNull ViewGroup parent, int viewType);
    void onBindHolder(@NonNull VH holder, List<T> datas , int position);
}
