package com.cuichen.bannerview.ui;

import android.os.Bundle;
import android.view.ViewGroup;

import androidx.activity.ComponentActivity;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cuichen.banner_loop.LoopViewPager;
import com.cuichen.banner_loop.adapter.LoopAdapter;
import com.cuichen.banner_loop.callback.OnLoopPageChangeCallback;
import com.cuichen.banner_view.transformer.OverlapDeepPageTransformer;
import com.cuichen.banner_view.transformer.common.MZScaleInTransformer;
import com.cuichen.banner_view.transformer.common.ScaleInTransformer;
import com.cuichen.banner_view.utils.BannerUtils;
import com.cuichen.banner_view.view.BannerIndicator;
import com.cuichen.bannerview.R;
import com.cuichen.bannerview.adapter.holder.ImgViewHolder;
import com.cuichen.bannerview.base.BaseData;

import java.util.List;


public class LoopBannerActivity extends ComponentActivity {

    private final String TAG = LoopBannerActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_loop);
        initLoopViewPager();
    }

    LoopViewPager banner;
    private void initLoopViewPager() {
        banner = findViewById(R.id.loop_vp);
        banner.setAdapter(new ImageAdapter(BaseData.getImgs()) , 3);
        banner.setPageTransformer(new ScaleInTransformer(0.8f))
                .setRecyclerViewPadding(BannerUtils.dp2px(40));
        banner.startLoop();

        BannerIndicator indicator = findViewById(R.id.indicator);
        indicator.setView(banner.getAdapter().getRealCount() , 0);
        banner.setPageChangeCallback(new OnLoopPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                indicator.setView(banner.getAdapter().getRealCount() , position);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        banner.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();
        banner.startLoop();
    }

    @Override
    protected void onStop() {
        super.onStop();
        banner.stopLoop();
    }

    public class ImageAdapter extends LoopAdapter<Integer, RecyclerView.ViewHolder> {

        public ImageAdapter(List<Integer> mDatas) {
            //设置数据，也可以调用banner提供的方法,或者自己在adapter中实现
            super(mDatas);
        }


        @Override
        public RecyclerView.ViewHolder onCreateHolder(ViewGroup parent, int viewType) {
                return new ImgViewHolder(parent);
        }

        @Override
        public void onBindHolder(@NonNull RecyclerView.ViewHolder holder, List<Integer> datas, int position) {
                ((ImgViewHolder)holder).setData(datas.get(position));
        }


    }

}