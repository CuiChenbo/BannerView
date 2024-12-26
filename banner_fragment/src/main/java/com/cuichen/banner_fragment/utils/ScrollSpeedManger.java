//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.cuichen.banner_fragment.utils;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.cuichen.banner_fragment.BannerFragmentView;

import java.lang.reflect.Field;

public class ScrollSpeedManger extends LinearLayoutManager {
    private BannerFragmentView banner;

    public ScrollSpeedManger(BannerFragmentView banner, LinearLayoutManager linearLayoutManager) {
        super(banner.getContext(), linearLayoutManager.getOrientation(), false);
        this.banner = banner;
    }

    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
        LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(recyclerView.getContext()) {
            protected int calculateTimeForDeceleration(int dx) {
                return ScrollSpeedManger.this.banner.getScrollTime();
            }
        };
        linearSmoothScroller.setTargetPosition(position);
        this.startSmoothScroll(linearSmoothScroller);
    }

    public static void reflectLayoutManager(BannerFragmentView banner) {
        if (banner.getScrollTime() >= 100) {
            try {
                ViewPager2 viewPager2 = banner.getViewPager2();
                RecyclerView recyclerView = (RecyclerView)viewPager2.getChildAt(0);
                recyclerView.setOverScrollMode(2);
                ScrollSpeedManger speedManger = new ScrollSpeedManger(banner, (LinearLayoutManager)recyclerView.getLayoutManager());
                recyclerView.setLayoutManager(speedManger);
                Field LayoutMangerField = ViewPager2.class.getDeclaredField("mLayoutManager");
                LayoutMangerField.setAccessible(true);
                LayoutMangerField.set(viewPager2, speedManger);
                Field pageTransformerAdapterField = ViewPager2.class.getDeclaredField("mPageTransformerAdapter");
                pageTransformerAdapterField.setAccessible(true);
                Object mPageTransformerAdapter = pageTransformerAdapterField.get(viewPager2);
                if (mPageTransformerAdapter != null) {
                    Class<?> aClass = mPageTransformerAdapter.getClass();
                    Field layoutManager = aClass.getDeclaredField("mLayoutManager");
                    layoutManager.setAccessible(true);
                    layoutManager.set(mPageTransformerAdapter, speedManger);
                }

                Field scrollEventAdapterField = ViewPager2.class.getDeclaredField("mScrollEventAdapter");
                scrollEventAdapterField.setAccessible(true);
                Object mScrollEventAdapter = scrollEventAdapterField.get(viewPager2);
                if (mScrollEventAdapter != null) {
                    Class<?> aClass = mScrollEventAdapter.getClass();
                    Field layoutManager = aClass.getDeclaredField("mLayoutManager");
                    layoutManager.setAccessible(true);
                    layoutManager.set(mScrollEventAdapter, speedManger);
                }
            } catch (Exception var11) {
                var11.printStackTrace();
            }

        }
    }
}
