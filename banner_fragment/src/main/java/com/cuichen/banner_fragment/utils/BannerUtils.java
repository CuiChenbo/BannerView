package com.cuichen.banner_fragment.utils;

import static android.util.TypedValue.COMPLEX_UNIT_DIP;

import android.content.res.Resources;
import android.graphics.Outline;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

public class BannerUtils {

    public static int getRealPosition(boolean isIncrease, int position, int realCount) {
        if (!isIncrease) {
            return position;
        } else {
            int realPosition;
            if (position == 0) {
                realPosition = realCount - 1;
            } else if (position == realCount + 1) {
                realPosition = 0;
            } else {
                realPosition = position - 1;
            }

            return realPosition;
        }
    }

    public static int dp2px(float dp) {
        return (int)TypedValue.applyDimension(COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }

}
