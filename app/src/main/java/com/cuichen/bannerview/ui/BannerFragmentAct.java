package com.cuichen.bannerview.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.cuichen.banner_view.BannerFragmentView;
import com.cuichen.banner_view.BannerViewPager;
import com.cuichen.banner_view.transformer.OverlapDeepPageTransformer;
import com.cuichen.banner_view.transformer.common.MZScaleInTransformer;
import com.cuichen.banner_view.utils.BannerUtils;
import com.cuichen.bannerview.R;
import com.cuichen.bannerview.ui.fragment.ImgFragment;
import com.cuichen.bannerview.ui.fragment.RvFragment;
import com.cuichen.bannerview.ui.fragment.WebFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;

/**
 * 仅供参考 - Banner中的Item使用Fragment
 * ViewPager2+Fragment实现
 */
public class BannerFragmentAct extends AppCompatActivity {

    BannerFragmentView banner_fragment;


    List<Fragment> fragmentList;
    private void initData() {

        fragmentList = new ArrayList<>();
        fragmentList.add(ImgFragment.newInstance(R.mipmap.banner12));
        fragmentList.add(WebFragment.newInstance());
        fragmentList.add(ImgFragment.newInstance(R.mipmap.banner5));
        fragmentList.add(WebFragment.newInstance());
        fragmentList.add(ImgFragment.newInstance(R.mipmap.banner7));
        fragmentList.add(RvFragment.newInstance());
        fragmentList.add(ImgFragment.newInstance(R.mipmap.banner13));
        fragmentList.add(ImgFragment.newInstance(R.mipmap.banner14));
        fragmentList.add(WebFragment.newInstance());

        //Fragment不可重复添加，所以要在创建时添加首尾数据
        fragmentList.add(0,WebFragment.newInstance()); //最后一个数据添加到第顶部
        fragmentList.add(ImgFragment.newInstance(R.mipmap.banner12)); //第一个数据添加到尾部

    }

    TextView textViewIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_fragment);
        banner_fragment = findViewById(R.id.banner_fragment);
        textViewIndex = findViewById(R.id.tv_index);
        initData();
        initView();
    }

    MyFragmentStateAdapter mAdapter;
    private void initView() {
        mAdapter = new MyFragmentStateAdapter(this,fragmentList);
        banner_fragment.setAdapter(mAdapter,1);
        banner_fragment.setPageTransformer(new OverlapDeepPageTransformer(0.8f,100,10));
        banner_fragment.setRecyclerViewPadding(100);
        banner_fragment.getViewPager2().setOffscreenPageLimit(10);
        banner_fragment.getViewPager2().registerOnPageChangeCallback(onPageChangeCallback);

        textViewIndex.setText(BannerUtils.getRealPosition(true , 1 , banner_fragment.getRealCount()) +"/"+ (banner_fragment.getRealCount()-1));
    }

    public void onClick(View v){
        int viewId = v.getId();
        if(viewId == R.id.bt_add){
            mAdapter.addFragment(2 , ImgFragment.newInstance(R.mipmap.banner9));
            mAdapter.notifyDataSetChanged();
            ((RecyclerView)banner_fragment.getViewPager2().getChildAt(0)).smoothScrollBy(20,0);
        }else if(viewId == R.id.bt_remove){
            mAdapter.removeFragment(2);
            mAdapter.notifyDataSetChanged();
            ((RecyclerView)banner_fragment.getViewPager2().getChildAt(0)).smoothScrollBy(20,0);
        }
    }


    ViewPager2.OnPageChangeCallback onPageChangeCallback = new ViewPager2.OnPageChangeCallback() {
        private int mTempPosition = -1;
        @Override
        public void onPageSelected(int position) {
            super.onPageSelected(position);
            mTempPosition = position;

            textViewIndex.setText(BannerUtils.getRealPosition(true , position , banner_fragment.getRealCount()) +"/"+ (banner_fragment.getRealCount()-1));

        }

        @Override
        public void onPageScrollStateChanged(int state) {
            super.onPageScrollStateChanged(state);
            if(state == ViewPager2.SCROLL_STATE_IDLE){
                if (this.mTempPosition != -1) {
                    if (this.mTempPosition == 0) {
                        banner_fragment.getViewPager2().setCurrentItem(banner_fragment.getRealCount(), false);
                    } else if (this.mTempPosition == banner_fragment.mAdapter.getItemCount() - 1) {
                        banner_fragment.getViewPager2().setCurrentItem(1, false);
                    }
                }
            }
        }
    };

    private class MyFragmentStateAdapter extends FragmentStateAdapter {
        List<Fragment> fragments;


        private List<Long> mIds = new ArrayList<>();

        private AtomicLong mAtomicLong = new AtomicLong(0);
        private long getAtomicGeneratedId() {
            return mAtomicLong.incrementAndGet();
        }

        public MyFragmentStateAdapter(@NonNull FragmentActivity fragmentActivity , List<Fragment> fragmentList) {
            super(fragmentActivity);
            if(fragmentList == null || fragmentList.isEmpty()){
                fragments = new ArrayList<>();
                return;
            }
            this.fragments = fragmentList;
            mIds.clear();
            fragments.forEach(fragment -> mIds.add(getAtomicGeneratedId()));
        }

        /**
         * 添加
         */
        public void addFragment(int index, Fragment fragment) {
            if (fragment != null && index >= 0 && index <= fragments.size()) {
                fragments.add(index, fragment);
                mIds.add(index, getAtomicGeneratedId());
            }
        }


        /**
         * 删除
         */
        public void removeFragment(int index) {
            if (index >= 0 && index < fragments.size()) {
                fragments.remove(index);
                mIds.remove(index);
            }
        }


        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return fragments.get(position);
        }

        @Override
        public int getItemCount() {
            return fragments.size();
        }


        @Override
        public long getItemId(int position) {
            return mIds.get(position);
        }

        @Override
        public boolean containsItem(long itemId) {
            return mIds.contains(itemId);
        }
    };

}