//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.cuichen.banner_view.transformer;

import android.view.View;

import com.cuichen.banner_view.transformer.common.BasePageTransformer;

/**
 * 左右压边重叠效果+深景效果
 */
public class OverlapDeepPageTransformer extends BasePageTransformer {
    private float mMinScale = 0.82F;  //View居中缩放
    private float translationX = 300F;  //X轴（横向）偏移差
    private float rotationY = 10F; //Y轴内侧旋转距离

    public OverlapDeepPageTransformer() {
    }

    public OverlapDeepPageTransformer(float minScale , float translationX , int rotationY) {
        this.mMinScale = minScale;
        this.translationX = translationX;
        this.rotationY = rotationY;
    }

    public void transformPage(View view, float position) {

        int pageWidth = view.getWidth();
        int pageHeight = view.getHeight();
        if (position < -1.0F) {
            int rp = (int)position;
            float pp = position - rp;
            float scaleFactor = this.mMinScale + (1.0F - this.mMinScale) * (1.0F - Math.abs(pp));
            scaleFactor = Math.min(scaleFactor , mMinScale);

            view.setTranslationX((float)(pageWidth-translationX));//X轴偏移到显示View的后方
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);
            view.setElevation(scaleFactor);

            view.setPivotX(0);
            view.setPivotY(pageHeight/2f);
            view.setRotationY(rotationY*Math.abs(1));

        } else if (position <= 0.0F) {
            view.setTranslationX((float)(pageWidth-translationX) * -position);//X轴偏移到显示View的后方
            float scaleFactor = this.mMinScale + (1.0F - this.mMinScale) * (1.0F - Math.abs(position));
            view.setScaleX(scaleFactor); //X轴缩放
            view.setScaleY(scaleFactor);
            view.setElevation(scaleFactor); //布局层级

            view.setPivotX(0);
            view.setPivotY(pageHeight/2f);
            view.setRotationY(rotationY*Math.abs(position));

        } else if (position <= 1.0F) {
            view.setTranslationX((float)(pageWidth-translationX) * -position);//X轴偏移到显示View的后方
            float scaleFactor = this.mMinScale + (1.0F - this.mMinScale) * (1.0F - Math.abs(position));
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);
            view.setElevation(scaleFactor);

            view.setPivotX(pageWidth);
            view.setPivotY(pageHeight/2f);
            view.setRotationY(-rotationY*Math.abs(position));

        } else {
            int rp = (int)position;
            float pp = position - rp;
            float scaleFactor = this.mMinScale + (1.0F - this.mMinScale) * (1.0F - Math.abs(pp));
            scaleFactor = Math.min(scaleFactor , mMinScale);

            view.setTranslationX((float)(pageWidth-translationX) * -1);//X轴偏移到显示View的后方
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);
            view.setElevation(scaleFactor);

            view.setPivotX(pageWidth);
            view.setPivotY(pageHeight/2f);
            view.setRotationY(-rotationY*Math.abs(1));
        }

    }

}
