package com.cuichen.banner_view.view;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.cuichen.banner_view.R;
import com.cuichen.banner_view.utils.BannerUtils;


public class BannerIndicator extends LinearLayout {
    public BannerIndicator(Context context) {
        super(context);
        init();
    }

    public BannerIndicator(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BannerIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

   void init(){

    }

    LayoutParams getLp(LayoutParams lp , int width){
        lp.height = BannerUtils.dp2px(3);
        lp.width = BannerUtils.dp2px(width);
        lp.setMargins(BannerUtils.dp2px(3),0, BannerUtils.dp2px(3),0);
        return lp;
    }

    public void setView(int size , int index){
        this.removeAllViews();
        for (int i = 0; i < size; i++) {
            View v = new View(this.getContext());
            GradientDrawable gd = new GradientDrawable();
            gd.setColor(getResources().getColor(index == i ? R.color.red : R.color.aaa , null));
            gd.setCornerRadius(BannerUtils.dp2px(2));
            v.setBackground(gd);
            addView(v);

            if(i == index){
                v.setLayoutParams(getLp((LayoutParams) v.getLayoutParams() , 10));
            }else {
                v.setLayoutParams(getLp((LayoutParams) v.getLayoutParams() , 5));
            }
        }
    }
}
