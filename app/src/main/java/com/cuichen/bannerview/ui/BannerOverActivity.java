package com.cuichen.bannerview.ui;

import android.os.Bundle;

import androidx.activity.ComponentActivity;

import com.cuichen.banner_view.transformer.OverDepthPageTransformer;
import com.cuichen.banner_view.utils.BannerUtils;
import com.cuichen.banner_view.BannerViewPager;
import com.cuichen.bannerview.R;
import com.cuichen.bannerview.adapter.ImageAdapter;
import com.cuichen.bannerview.base.BaseData;


public class BannerOverActivity extends ComponentActivity {

    private final String TAG = BannerOverActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_over);
        initLoopViewPager();
        initLoopViewPager2();
    }

    private void initLoopViewPager() {

        BannerViewPager banner = findViewById(R.id.banner);
        banner.setAdapter(new ImageAdapter(BaseData.getImgs()));
        banner.setPageTransformer(new OverDepthPageTransformer(0.8f , BannerUtils.dp2px(50) , 0));
        banner.setRecyclerViewPadding(BannerUtils.dp2px(50));
        banner.startLoop();

    }

    private void initLoopViewPager2() {

        BannerViewPager banner = findViewById(R.id.banner2);
        banner.setAdapter(new ImageAdapter(BaseData.getImgs()));
        banner.setPageTransformer(new OverDepthPageTransformer(0.8f , BannerUtils.dp2px(50) , BannerUtils.dp2px(20)));
        banner.setRecyclerViewPadding(BannerUtils.dp2px(50));
        banner.startLoop();

    }

}