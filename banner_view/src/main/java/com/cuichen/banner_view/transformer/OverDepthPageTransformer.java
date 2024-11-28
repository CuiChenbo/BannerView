//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.cuichen.banner_view.transformer;

import android.view.View;

import com.cuichen.banner_view.transformer.common.BasePageTransformer;


public class OverDepthPageTransformer extends BasePageTransformer {
    private static final float DEFAULT_MIN_SCALE = 0.75F;
    private float mMinScale = 0.8F; //View居中缩放
    private float translationX = 300F; //X轴偏移差
    private float translationY = 0F; //Y轴偏移距离

    public OverDepthPageTransformer() {
    }

    public OverDepthPageTransformer(float minScale , float translationX , float translationY ) {
        this.mMinScale = minScale;
        this.translationX = translationX;
        this.translationY = translationY;
    }



    public void transformPage(View view, float position) {

        int pageWidth = view.getWidth();
//        float reduceX = (1.0f - mMinScale) * pageWidth / 2.0f;
        float reduceX = (pageWidth-translationX) / Math.abs(position);
        if (position < -1.0F) {
            //[-∞ , -1 ]
            view.setTranslationX(reduceX);
            view.setTranslationY(translationY*1); //Y轴向下偏移
            view.setScaleX(mMinScale);
            view.setScaleY(mMinScale);
            view.setElevation(position);
        } else if (position <= 0.0F) {
            //[-1 , 0 ]
            view.setTranslationX((float)(pageWidth-translationX) * -position);//X轴偏移到显示View的后方
            view.setTranslationY(translationY * -position); //Y轴向下偏移
            float scaleFactor = this.mMinScale + (1.0F - this.mMinScale) * (1.0F - Math.abs(position));
            view.setScaleX(scaleFactor); //X轴缩放
            view.setScaleY(scaleFactor);
            view.setElevation(scaleFactor); //布局层级
        } else if (position <= 1.0F) {
            //[0 , 1]
            view.setTranslationX((float)(pageWidth-translationX) * -position);//X轴偏移到显示View的后方
            view.setTranslationY(translationY * position);  //Y轴向下偏移
            float scaleFactor = this.mMinScale + (1.0F - this.mMinScale) * (1.0F - Math.abs(position));
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);
            view.setElevation(scaleFactor);
        } else {
            //[1 , +∞]
            view.setTranslationX(-reduceX);
            view.setTranslationY(translationY * 1); //Y轴向下偏移
            view.setScaleX(mMinScale);
            view.setScaleY(mMinScale);
            view.setElevation(-position);
        }

    }
}
