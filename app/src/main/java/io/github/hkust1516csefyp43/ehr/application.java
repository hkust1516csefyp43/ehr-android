package io.github.hkust1516csefyp43.ehr;

import android.content.Context;
import android.util.Log;

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
public class application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
        Fabric.with(this, new Crashlytics());
        Glide.get(this).register(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(new OkHttpClient()));


        //TODO remove the following code when deploy
        if (io.github.hkust1516csefyp43.ehr.BuildConfig.DEBUG) {
            Log.d("qqq24", "clear glide cache");
            final Context context = this;
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    Glide.get(context).clearDiskCache();
                    Glide.get(context).clearMemory();
                }
            });
            t.start();
        }
    }
}
