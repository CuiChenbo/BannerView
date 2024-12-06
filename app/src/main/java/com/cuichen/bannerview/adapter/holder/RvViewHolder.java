package com.cuichen.bannerview.adapter.holder;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cuichen.bannerview.R;
import com.cuichen.bannerview.adapter.RvAdapter;
import com.cuichen.bannerview.base.BaseData;

import java.util.Arrays;


public class RvViewHolder extends RecyclerView.ViewHolder {
        public RecyclerView rv;

        public RvViewHolder(ViewGroup parent){
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_banner_rv , parent,false));
            this.rv = itemView.findViewById(R.id.rv);
        }

    RvAdapter rvAdapter;
        public void setData(Integer data){
            if(rv.getAdapter() == null) {
                rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));
                rvAdapter = new RvAdapter();
                rv.setAdapter(rvAdapter);
                rvAdapter.datas.addAll(Arrays.asList("", "", "", "", "", "", "", "", "", "", "", "", "", "", ""));
                rvAdapter.notifyDataSetChanged();
            }else {
                Log.i("RvViewHolder", "RvViewHolder被复用");
            }
        }
    }

