package com.cuichen.banner_view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.cuichen.banner_view.utils.ScrollFragmentSpeedManger;

import java.lang.ref.WeakReference;


public class BannerFragmentView extends FrameLayout {

  private Context context;

    public BannerFragmentView(@NonNull Context context) {
        this(context , null);
    }

    public BannerFragmentView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs , 0);
    }

    public BannerFragmentView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    public ViewPager2 mViewPager2;
    private void init() {
        this.mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop() / 2;
        this.mViewPager2 = new ViewPager2(context);
        this.mViewPager2.setLayoutParams(new LayoutParams(-1, -1));
        this.mViewPager2.setOffscreenPageLimit(2);
        this.addView(this.mViewPager2);
        this.mLoopTask = new AutoLoopTask(this);
        ScrollFragmentSpeedManger.reflectLayoutManager(this);
    }

    public int getScrollTime(){
        return 600;
    }


    public BannerFragmentView setPageTransformer(@Nullable ViewPager2.PageTransformer transformer) {
        this.getViewPager2().setPageTransformer(transformer);
        return this;
    }
    public BannerFragmentView setRecyclerViewPadding(int itemPadding) {
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

    public BannerFragmentView setAdapter(@Nullable FragmentStateAdapter adapter) {
        setAdapter(adapter , 0);
        return this;
    }

    public FragmentStateAdapter mAdapter;
    public BannerFragmentView setAdapter(@Nullable FragmentStateAdapter adapter , int startPosition) {
        mAdapter = adapter;
        this.getViewPager2().setAdapter(mAdapter);
        this.getViewPager2().setCurrentItem(startPosition,false);
        return this;
    }


    public int getRealCount(){
        if (mAdapter == null){
            return 0;
        }
        if(mAdapter.getItemCount() < 2){
            return 0;
        }
        return mAdapter.getItemCount()-2;
    }

    private BannerFragmentView setCurrentItem(int position, boolean smoothScroll) {
        this.getViewPager2().setCurrentItem(position, smoothScroll);
        return this;
    }


    public ViewPager2 getViewPager2() {
        return this.mViewPager2;
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

    public void setIsAutoLoop(boolean mIsAutoLoop){
      this.mIsAutoLoop = mIsAutoLoop;
      startLoop();
    }

    public void destroy(){
        stopLoop();
    }

    boolean mIsAutoLoop = false;
    int mLoopTime = 5000;
    AutoLoopTask mLoopTask;
    ///设置自动轮播效果
    static class AutoLoopTask implements Runnable {
        private final WeakReference<BannerFragmentView> reference;

        AutoLoopTask(BannerFragmentView loopVp) {
            this.reference = new WeakReference(loopVp);
        }

        public void run() {
            BannerFragmentView loopVp = (BannerFragmentView)this.reference.get();
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
    
}
