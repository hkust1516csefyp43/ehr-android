package io.github.hkust1516csefyp43.ehr;

import android.app.Application;

import com.bumptech.glide.Glide;
import com.bumptech.glide.integration.okhttp.OkHttpUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.crashlytics.android.Crashlytics;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.okhttp.OkHttpClient;

import java.io.InputStream;

import io.fabric.sdk.android.Fabric;

/**
 * Created by Louis on 1/9/15.
 */
public class application extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
        Fabric.with(this, new Crashlytics());
        Glide.get(this).register(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(new OkHttpClient()));
    }
}
