package com.cuichen.bannerview

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import com.cuichen.banner_view.BannerViewPager
import com.cuichen.bannerview.adapter.ImageAdapter
import com.cuichen.bannerview.base.BaseData
import com.cuichen.bannerview.ui.BannerIndicatorActivity
import com.cuichen.bannerview.ui.BannerMzActivity
import com.cuichen.bannerview.ui.BannerOverActivity
import com.cuichen.bannerview.ui.BannerOverSeepActivity
import com.cuichen.bannerview.ui.LoopBannerActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val context = this

        initBanner()

        findViewById<View>(R.id.bt_mz).setOnClickListener {
            startActivity(
                Intent(
                    context,
                    BannerMzActivity::class.java
                )
            )
        }
        findViewById<View>(R.id.bt_over).setOnClickListener {
            startActivity(
                Intent(
                    context,
                    BannerOverActivity::class.java
                )
            )
        }
        findViewById<View>(R.id.bt_over_sp).setOnClickListener {
            startActivity(
                Intent(
                    context,
                    BannerOverSeepActivity::class.java
                )
            )
        }

        findViewById<View>(R.id.bt_banner_indicator).setOnClickListener {
            startActivity(
                Intent(
                    context,
                    BannerIndicatorActivity::class.java
                )
            )
        }

        findViewById<View>(R.id.bt_banner_loop).setOnClickListener {
            startActivity(
                Intent(
                    context,
                    LoopBannerActivity::class.java
                )
            )
        }


    }

    private fun initBanner() {
        val banner = findViewById<BannerViewPager>(R.id.banner)
        banner.setAdapter(ImageAdapter(BaseData.getImgs()))
            .startLoop()
    }
}
