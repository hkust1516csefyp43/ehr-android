package io.github.hkust1516csefyp43.ehr;

import com.crashlytics.android.Crashlytics;
import com.vincentbrison.openlibraries.android.dualcache.lib.DualCacheContextUtils;
import com.vincentbrison.openlibraries.android.dualcache.lib.DualCacheLogUtils;

import io.fabric.sdk.android.Fabric;

/**
 * Created by Louis on 1/9/15.
 */
public class application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        DualCacheLogUtils.enableLog();
        DualCacheContextUtils.setContext(this);
    }
}
