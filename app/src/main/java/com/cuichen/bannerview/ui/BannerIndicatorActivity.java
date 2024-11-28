package com.cuichen.bannerview.ui;

import android.os.Bundle;

import androidx.activity.ComponentActivity;

import com.cuichen.banner_view.view.BannerIndicator;
import com.cuichen.banner_view.BannerViewPager;
import com.cuichen.banner_view.adapter.OnPageChangeListener;
import com.cuichen.bannerview.R;
import com.cuichen.bannerview.adapter.ImageAdapter;
import com.cuichen.bannerview.base.BaseData;


public class BannerIndicatorActivity extends ComponentActivity {

    private final String TAG = BannerIndicatorActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_indicator);
        initLoopViewPager();
    }

    private void initLoopViewPager() {

        BannerViewPager banner = findViewById(R.id.banner);
        banner.setAdapter(new ImageAdapter(BaseData.getImgs()));
        banner.startLoop();

        BannerIndicator indicator = findViewById(R.id.indicator);
        indicator.setView(banner.getRealCount() , 0);
        banner.addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                indicator.setView(banner.getRealCount() , position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

}