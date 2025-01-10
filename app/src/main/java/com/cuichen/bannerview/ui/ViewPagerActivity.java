package com.cuichen.bannerview.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.cuichen.bannerview.R;
import com.cuichen.bannerview.adapter.ImageAdapter;
import com.cuichen.bannerview.adapter.RvAdapter;
import com.cuichen.bannerview.base.BaseData;
import com.cuichen.bannerview.view.MyViewPager2;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity extends AppCompatActivity {

    MyViewPager2 vp2;
    private String TAG = ViewPagerActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        vp2 = findViewById(R.id.vp2);
        initData();
        initView();
    }

    private void initView() {
        ImgAdapter mAdapter = new ImgAdapter();
        mAdapter.setDatas(BaseData.getImgs());
        vp2.mViewPager2.setAdapter(mAdapter);


    }

    private void initData() {

    }



    private class ImgAdapter extends RecyclerView.Adapter<ImgAdapter.Vh>{

        public List<Integer> datas = new ArrayList<>();
        public int Y = 0;

        public void setDatas(List<Integer> datas){
            if(datas == null){
                datas = new ArrayList<>();
            }
            this.datas = datas;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public Vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new Vh(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_banner , parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull Vh holder, int position) {
            holder.iv.setImageResource(datas.get(position));
        }

        @Override
        public int getItemCount() {
            return datas.size();
        }

        class Vh extends RecyclerView.ViewHolder{
            ImageView iv;
            public Vh(@NonNull View itemView) {
                super(itemView);
                iv = itemView.findViewById(R.id.iv);
            }
        }
    }




}