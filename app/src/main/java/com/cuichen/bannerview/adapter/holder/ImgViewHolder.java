package com.cuichen.bannerview.adapter.holder;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.cuichen.bannerview.R;


public class ImgViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public ImgViewHolder(ViewGroup parent){
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_banner , parent,false));
            this.imageView = itemView.findViewById(R.id.iv);
        }

        public void setData(Integer data){
            imageView.setImageResource(data);
        }
    }