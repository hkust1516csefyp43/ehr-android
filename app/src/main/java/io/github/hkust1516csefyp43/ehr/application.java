package io.github.hkust1516csefyp43.ehr;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

/**
 * Created by Louis on 1/9/15.
 */
public class application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        //TODO setup a Parse account with hkust1516csefyp43@gmail.com
//        ParseCrashReporting.enable(this);
//        Parse.initialize(this, "LavYuwYH6JGN7MdH26XC87atMcAGgmEeSjcBdiy5", "1C9v0QxgVdwvTwOH3DlC34P9TFcLzzOiYYmM6Ix2");
    }
}
