package io.github.hkust1516csefyp43.ehr;

import android.content.Context;

import java.util.Locale;

import io.github.hkust1516csefyp43.ehr.pojo.Person;

/**
 * Created by Louis on 5/11/15.
 */
public class Utils {
    private String packageName;

    public static Locale getDefaultLocale() {
        return Locale.ENGLISH;
    }

    public String getPackageName(Context context) {
        if (packageName == null) {
            packageName = context.getPackageName();
        }
        return packageName;
    }

    public void setPackageName(String pn) {
        packageName = pn;
    }

    public String getTextDrawableText(Person p) {
        if (p != null) {
            if (p.last_name == null) {
                //just firstname
                if (p.first_name == null) {
                    //no name
                    return "?";
                }
                return "f";
            } else if (p.first_name == null) {
                //just lastname
                return "l";
            } else {
                //normal
                return "fl";
            }
        } else
            return "?";
    }
}
