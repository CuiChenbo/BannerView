package com.cuichen.bannerview.ui;

import android.os.Bundle;

import androidx.activity.ComponentActivity;

import com.cuichen.banner_view.transformer.common.ScaleInTransformer;
import com.cuichen.banner_view.utils.BannerUtils;
import com.cuichen.banner_view.BannerViewPager;
import com.cuichen.bannerview.R;
import com.cuichen.bannerview.adapter.BackGroundAdapter;
import com.cuichen.bannerview.adapter.ImageAdapter;
import com.cuichen.bannerview.base.BaseData;


public class BannerMzActivity extends ComponentActivity {

    private final String TAG = BannerMzActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_mz);
        initLoopViewPager();
        initLoopViewPager2();
    }

    private void initLoopViewPager() {

        BannerViewPager banner = findViewById(R.id.lvp2);
        banner.setAdapter(new BackGroundAdapter(BaseData.getColors()));
        banner.setPageTransformer(new ScaleInTransformer());
        banner.setRecyclerViewPadding(BannerUtils.dp2px(36));
        banner.startLoop();

    }

    private void initLoopViewPager2() {
        BannerViewPager banner = findViewById(R.id.lvp22);
        banner.setAdapter(new ImageAdapter(BaseData.getImgs()));
        banner.setPageTransformer(new ScaleInTransformer());
        banner.setRecyclerViewPadding(BannerUtils.dp2px(36));
        banner.startLoop();

    }

}