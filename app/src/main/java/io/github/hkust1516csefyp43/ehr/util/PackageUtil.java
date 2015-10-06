package io.github.hkust1516csefyp43.ehr.util;

import android.content.Context;

/**
 * A Util for getting and setting packages
 * Created by Louis on 6/10/15.
 */
public class PackageUtil {

    private String packageName;
    public String getPackageName(Context context) {
        if (packageName == null) {
            packageName = context.getPackageName();
        }
        return packageName;
    }
    public void setPackageName(String pn) {
        packageName = pn;
    }
}
