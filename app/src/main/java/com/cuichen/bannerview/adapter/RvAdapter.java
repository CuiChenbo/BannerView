package com.cuichen.bannerview.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cuichen.bannerview.R;

import java.util.ArrayList;
import java.util.List;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.Vh>{

       public List<String> datas = new ArrayList<>();
       public int Y = 0;

       public void setDatas(List<String> datas){
           if(datas == null){
               datas = new ArrayList<>();
           }
           this.datas = datas;
           notifyDataSetChanged();
       }

        @NonNull
        @Override
        public Vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new Vh(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_banner_rv_text , parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull Vh holder, int position) {
             holder.tv.setText(position+ " INDEX ");
        }

        @Override
        public int getItemCount() {
            return datas.size();
        }

        class Vh extends RecyclerView.ViewHolder{
            TextView tv , tv2;
            public Vh(@NonNull View itemView) {
                super(itemView);
                tv = itemView.findViewById(R.id.tv);
            }
        }
    }
