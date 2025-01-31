package com.cuichen.banner_view.utils;

import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

public interface BannerLifecycleObserver extends LifecycleObserver {

    void onStop(LifecycleOwner owner);

    void onStart(LifecycleOwner owner);

    void onDestroy(LifecycleOwner owner);
}
