package com.cuichen.bannerview.adapter.holder;


import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.cuichen.bannerview.R;


public class TextViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView tv;

        public TextViewHolder(ViewGroup parent){
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_banner , parent,false));
            this.imageView = itemView.findViewById(R.id.iv);
            this.tv = itemView.findViewById(R.id.tv);
        }

        public void setData(Integer data , int index){
            imageView.setImageResource((data));
            tv.setText("INDEX-"+index);
        }
    }