package io.github.hkust1516csefyp43.ehr;

import android.content.Context;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import io.github.hkust1516csefyp43.ehr.pojo.Person;

/**
 * Created by Louis on 5/11/15.
 */
public class Utils {
    public final static int HOW_MANY_MS_IN_S = 1000;
    public final static int HOW_MANY_S_IN_MIN = 60;
    public final static int HOW_MANY_MIN_IN_HOUR = 60;
    public final static int HOW_MANY_HOUR_IN_DAY = 24;
    public final static int HOW_MANY_DAY_IN_WEEK = 7;
    public final static long MILLISECOND = 1;
    public final static long SECOND = HOW_MANY_MS_IN_S * MILLISECOND;
    public final static long MINUTE = HOW_MANY_S_IN_MIN * SECOND;
    public final static long HOUR = HOW_MANY_MIN_IN_HOUR * MINUTE;
    public final static long DAY = HOW_MANY_HOUR_IN_DAY * HOUR;
    public final static long WEEK = HOW_MANY_DAY_IN_WEEK * DAY;
    public final static long MONTH = (long) (30.4375 * DAY);
    public final static long YEAR = (long) (365.25 * DAY);

    private String packageName;

    public static Locale getDefaultLocale() {
        return Locale.ENGLISH;
    }

    public static String lastSeenToString(Date d) {
        Calendar now = Calendar.getInstance();
        long diff = now.getTimeInMillis() - d.getTime();
        return toTimeAgo(diff);
    }

    public static String toTimeAgo(long dateMsDiff) {
        if (dateMsDiff < MINUTE)
            return "Just now";
        else if (dateMsDiff < 2 * MINUTE)
            return "A minute ago";
        else if (dateMsDiff < 50 * MINUTE)
            return (dateMsDiff / MINUTE + " minutes ago");
        else if (dateMsDiff < 80 * MINUTE)
            return "An hour ago";
        else if (dateMsDiff < 24 * HOUR)
            return (dateMsDiff / HOUR + " hours ago");
        else if (dateMsDiff < 36 * HOUR)
            return ("A day ago");
        else if (dateMsDiff < WEEK)
            return (dateMsDiff / DAY + " days ago");
        else if (dateMsDiff < 2 * WEEK)
            return "A week ago";
        else if (dateMsDiff < MONTH)
            return (dateMsDiff / WEEK + " weeks ago");
        else if (dateMsDiff < 2 * MONTH)
            return "A month ago";
        else if (dateMsDiff < YEAR)
            return (dateMsDiff / MONTH + " months ago");
        else if (dateMsDiff < 2 * YEAR)
            return "A year ago";
        else return (dateMsDiff / YEAR + " years ago");
    }

    public static String birthdayToAge(Integer year, Integer month, Integer date) {
       if (year != null) {
           //TODO
           return null;
       } else {
           return null;
       }
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
