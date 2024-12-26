package com.cuichen.bannerview.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.webkit.WebView;


/**
 * BaseWebView class
 *
 */
public class BaseWebView extends WebView {
    public static final String TAG = "BaseWebView";

    /**
     * @param context 上下文
     */
    public BaseWebView(Context context) {
        super(context);
        init();
    }

    /**
     * @param context 上下文
     * @param attrs   attrs
     */
    public BaseWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     * @param context      上下文
     * @param attrs        attrs
     * @param defStyleAttr defStyleAttr
     */
    public BaseWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    /**
     * 初始化
     */
    public void init() {

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(isIntercept){
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mStartX = event.getX();
                    mStartY = event.getY();
                    requestDisallowInterceptTouchEvent(true);
                    break;
                case MotionEvent.ACTION_MOVE:
                    float endX = event.getX();
                    float endY = event.getY();
                    float distanceX = Math.abs(endX - mStartX);
                    float distanceY = Math.abs(endY - mStartY);
                    boolean goIntercept = distanceY > mTouchSlop && distanceY > distanceX;
                    Log.i(TAG, "requestDisallowInterceptTouchEvent: "+goIntercept);
                    requestDisallowInterceptTouchEvent(goIntercept);
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    requestDisallowInterceptTouchEvent(false);
                    break;
            }
        }

        return super.onTouchEvent(event);
    }


    private int mTouchSlop = 20;
    private float mStartX = 0;
    private float mStartY = 0;
    private boolean isIntercept = true;  //是否拦截触摸事件（上下滑动时，拦截事件，避免左右滑动的ViewPager抢走事件）

}
