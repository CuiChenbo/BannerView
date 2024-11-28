package com.cuichen.bannerview.ui;

import android.os.Bundle;

import androidx.activity.ComponentActivity;

import com.cuichen.banner_view.transformer.OverlapDeepPageTransformer;
import com.cuichen.banner_view.transformer.common.RotateYTransformer;
import com.cuichen.banner_view.utils.BannerUtils;
import com.cuichen.banner_view.BannerViewPager;
import com.cuichen.bannerview.R;
import com.cuichen.bannerview.adapter.ImageAdapter;
import com.cuichen.bannerview.base.BaseData;


public class BannerOverSeepActivity extends ComponentActivity {

    private final String TAG = BannerOverSeepActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_over_seep);
        initLoopViewPager();
        initLoopViewPager2();
    }

    private void initLoopViewPager() {

        BannerViewPager banner = findViewById(R.id.banner);
        banner.setAdapter(new ImageAdapter(BaseData.getImgs()))
        .setPageTransformer(new OverlapDeepPageTransformer(0.8f , BannerUtils.dp2px(40) , BannerUtils.dp2px(6)))
        .setRecyclerViewPadding(BannerUtils.dp2px(60));
        banner.startLoop();

    }

    private void initLoopViewPager2() {

        BannerViewPager banner = findViewById(R.id.banner2);
        banner.setAdapter(new ImageAdapter(BaseData.getImgs()))
                .setPageTransformer(new RotateYTransformer())
                .setRecyclerViewPadding(BannerUtils.dp2px(60));
        banner.startLoop();

    }

}