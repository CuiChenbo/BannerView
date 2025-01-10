package com.cuichen.bannerview.view;


import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager2.widget.ViewPager2;

import com.cuichen.banner_view.BannerViewPager;
import com.cuichen.banner_view.utils.ScrollSpeedManger;

public class MyViewPager2 extends FrameLayout {
    public MyViewPager2(@NonNull Context context) {
        super(context);
        init();
    }

    public MyViewPager2(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyViewPager2(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public MyViewPager2(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public ViewPager2 mViewPager2;
    void init(){
        this.mViewPager2 = new ViewPager2(getContext());
        this.mViewPager2.setLayoutParams(new LayoutParams(-1, -1));
        this.mViewPager2.setOffscreenPageLimit(2);
        this.addView(this.mViewPager2);
    }


    private float mStartX;
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mStartX = event.getX();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                break;
            case MotionEvent.ACTION_MOVE:
                float endX = event.getX();
                float distanceX = (endX - mStartX);
                if( mViewPager2.getCurrentItem() == 0){
                    if(distanceX > 100){
                        Log.i("TAG", "onTouch: 已经是第一张了"+distanceX);
                    }
                }else if(mViewPager2.getCurrentItem() == mViewPager2.getAdapter().getItemCount()-1){
                    if(distanceX < -100){
                        Log.i("TAG", "onTouch: 已经是最后一张了"+distanceX);
                    }

                }
        }
        return super.dispatchTouchEvent(event);
    }
}
