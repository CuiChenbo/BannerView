package com.cuichen.banner_view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.cuichen.banner_view.adapter.OnPageChangeListener;
import com.cuichen.banner_view.utils.BannerLifecycleObserver;
import com.cuichen.banner_view.utils.BannerLifecycleObserverAdapter;
import com.cuichen.banner_view.utils.BannerUtils;
import com.cuichen.banner_view.utils.ScrollSpeedManger;

import java.lang.ref.WeakReference;


public class BannerViewPager extends FrameLayout implements BannerLifecycleObserver {

  private Context context;

    public BannerViewPager(@NonNull Context context) {
        this(context , null);
    }

    public BannerViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs , 0);
    }

    public BannerViewPager(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    public ViewPager2 mViewPager2;
    private final int mIncreaseCount = 2;
    private OnBannerPageChangeCallback mPageChangeCallback;
    private OnPageChangeListener mOnPageChangeListener;
    private void init() {
        this.mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop() / 2;
        this.mPageChangeCallback = new OnBannerPageChangeCallback();
        this.mViewPager2 = new ViewPager2(context);
        this.mViewPager2.setLayoutParams(new LayoutParams(-1, -1));
        this.mViewPager2.setOffscreenPageLimit(2);
        this.mViewPager2.registerOnPageChangeCallback(this.mPageChangeCallback);
        this.addView(this.mViewPager2);
        this.mLoopTask = new AutoLoopTask(this);
        ScrollSpeedManger.reflectLayoutManager(this);
    }

    public int getScrollTime(){
        return 600;
    }


    public BannerViewPager setPageTransformer(@Nullable ViewPager2.PageTransformer transformer) {
        this.getViewPager2().setPageTransformer(transformer);
        return this;
    }
    public BannerViewPager setRecyclerViewPadding(int itemPadding) {
        this.setRecyclerViewPadding(itemPadding, itemPadding);
        return this;
    }

    private void setRecyclerViewPadding(int leftItemPadding, int rightItemPadding) {
        RecyclerView recyclerView = (RecyclerView)this.getViewPager2().getChildAt(0);
        if (this.getViewPager2().getOrientation() == ViewPager2.ORIENTATION_VERTICAL) {
            recyclerView.setPadding(this.mViewPager2.getPaddingLeft(), leftItemPadding, this.mViewPager2.getPaddingRight(), rightItemPadding);
        } else {
            recyclerView.setPadding(leftItemPadding, this.mViewPager2.getPaddingTop(), rightItemPadding, this.mViewPager2.getPaddingBottom());
        }

        recyclerView.setClipToPadding(false);
    }

    public BannerViewPager addOnPageChangeListener(OnPageChangeListener pageListener) {
        this.mOnPageChangeListener = pageListener;
        return this;
    }

    private boolean mIsInfiniteLoop = true;
    public boolean isInfiniteLoop() {
        return this.mIsInfiniteLoop;
    }

    public RecyclerView.Adapter getAdapter() {
        return this.getViewPager2().getAdapter();
    }

    public BannerViewPager setAdapter(@Nullable RecyclerView.Adapter adapter) {
        this.getViewPager2().setAdapter(adapter);
        this.getViewPager2().setCurrentItem(1,false);
        return this;
    }

    public int getItemCount() {
        return this.getAdapter() != null ? this.getAdapter().getItemCount() : 0;
    }

    public int getRealCount() {
        return this.getAdapter() != null ? this.getAdapter().getItemCount()-mIncreaseCount : 0;
    }

    public BannerViewPager setCurrentItem(int position) {
        return this.setCurrentItem(position, true);
    }

    public BannerViewPager setCurrentItem(int position, boolean smoothScroll) {
        this.getViewPager2().setCurrentItem(position, smoothScroll);
        return this;
    }

    public int getCurrentItem() {
        return this.getViewPager2().getCurrentItem();
    }


    public ViewPager2 getViewPager2() {
        return this.mViewPager2;
    }

    ///设置无限轮播效果
    class OnBannerPageChangeCallback extends ViewPager2.OnPageChangeCallback {
        private int mTempPosition = -1;
        private boolean isScrolled;

        OnBannerPageChangeCallback() {
        }

        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            int realPosition = BannerUtils.getRealPosition(BannerViewPager.this.isInfiniteLoop(), position, BannerViewPager.this.getRealCount());
            if (BannerViewPager.this.mOnPageChangeListener != null && realPosition == BannerViewPager.this.getCurrentItem() - 1) {
                BannerViewPager.this.mOnPageChangeListener.onPageScrolled(realPosition, positionOffset, positionOffsetPixels);
            }


        }

        public void onPageSelected(int position) {
            if (this.isScrolled) {
                this.mTempPosition = position;
                int realPosition = BannerUtils.getRealPosition(BannerViewPager.this.isInfiniteLoop(), position, BannerViewPager.this.getRealCount());
                if (BannerViewPager.this.mOnPageChangeListener != null) {
                    BannerViewPager.this.mOnPageChangeListener.onPageSelected(realPosition);
                }

            }

        }

        public void onPageScrollStateChanged(int state) {
            if (state != 1 && state != 2) {
                if (state == 0) {
                    this.isScrolled = false;
                    if (this.mTempPosition != -1 && BannerViewPager.this.mIsInfiniteLoop) {
                        if (this.mTempPosition == 0) {
                            BannerViewPager.this.setCurrentItem(BannerViewPager.this.getRealCount(), false);
                        } else if (this.mTempPosition == BannerViewPager.this.getItemCount() - 1) {
                            BannerViewPager.this.setCurrentItem(1, false);
                        }
                    }
                }
            } else {
                this.isScrolled = true;
            }

            if (BannerViewPager.this.mOnPageChangeListener != null) {
                BannerViewPager.this.mOnPageChangeListener.onPageScrollStateChanged(state);
            }


        }
    }

   ///设置触摸暂停轮播
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (!this.getViewPager2().isUserInputEnabled()) {
            return super.dispatchTouchEvent(ev);
        } else {
            int action = ev.getActionMasked();
            if (action != MotionEvent.ACTION_UP && action != MotionEvent.ACTION_CANCEL && action != MotionEvent.ACTION_OUTSIDE) {
                if (action == MotionEvent.ACTION_DOWN) {
                    this.stopLoop();
                }
            } else {
                this.startLoop();
            }

            return super.dispatchTouchEvent(ev);
        }
    }

    private float mStartX;
    private float mStartY;
    private boolean mIsViewPager2Drag;
    private boolean isIntercept = true;
    private int mTouchSlop;
    ///设置滑动冲突抵消
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (this.getViewPager2().isUserInputEnabled() && this.isIntercept) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    this.mStartX = event.getX();
                    this.mStartY = event.getY();
                    this.getParent().requestDisallowInterceptTouchEvent(true);
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    this.getParent().requestDisallowInterceptTouchEvent(false);
                    break;
                case MotionEvent.ACTION_MOVE:
                    float endX = event.getX();
                    float endY = event.getY();
                    float distanceX = Math.abs(endX - this.mStartX);
                    float distanceY = Math.abs(endY - this.mStartY);
                    if (this.getViewPager2().getOrientation() == ViewPager2.ORIENTATION_HORIZONTAL) {
                        this.mIsViewPager2Drag = distanceX > (float)this.mTouchSlop && distanceX > distanceY;
                    } else {
                        this.mIsViewPager2Drag = distanceY > (float)this.mTouchSlop && distanceY > distanceX;
                    }

                    this.getParent().requestDisallowInterceptTouchEvent(this.mIsViewPager2Drag);
            }

            return super.onInterceptTouchEvent(event);
        } else {
            return super.onInterceptTouchEvent(event);
        }
    }


    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.startLoop();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.stopLoop();
    }

   public void startLoop(){
        if(mIsAutoLoop) {
            stopLoop();
            getViewPager2().postDelayed(mLoopTask, mLoopTime);
        }
    }

    public void stopLoop(){
        if(mIsAutoLoop) {
            getViewPager2().removeCallbacks(mLoopTask);
        }
    }

    public void destroy() {
        if (getViewPager2() != null && mPageChangeCallback != null) {
            getViewPager2().unregisterOnPageChangeCallback(mPageChangeCallback);
            mPageChangeCallback = null;
        }
        stopLoop();
    }

    boolean mIsAutoLoop = true;
    int mLoopTime = 5000;
    AutoLoopTask mLoopTask;
    ///设置自动轮播效果
    static class AutoLoopTask implements Runnable {
        private final WeakReference<BannerViewPager> reference;

        AutoLoopTask(BannerViewPager loopVp) {
            this.reference = new WeakReference(loopVp);
        }

        public void run() {
            BannerViewPager loopVp = (BannerViewPager)this.reference.get();
            if (loopVp != null && loopVp.mIsAutoLoop && loopVp.getViewPager2().getAdapter() != null) {
                int count = loopVp.getViewPager2().getAdapter().getItemCount();
                if (count == 0) {
                    return;
                }

                int next = (loopVp.getViewPager2().getCurrentItem() + 1) % count;
                loopVp.getViewPager2().setCurrentItem(next);
                loopVp.getViewPager2().postDelayed(loopVp.mLoopTask, loopVp.mLoopTime);
            }

        }
    }

    public BannerViewPager addBannerLifecycleObserver(LifecycleOwner owner) {
        if (owner != null) {
            owner.getLifecycle().addObserver(new BannerLifecycleObserverAdapter(owner, this));
        }
        return this;
    }

    @Override
    public void onStart(LifecycleOwner owner) {
        startLoop();
    }

    @Override
    public void onStop(LifecycleOwner owner) {
        stopLoop();
    }

    @Override
    public void onDestroy(LifecycleOwner owner) {
        destroy();
    }
    
}
