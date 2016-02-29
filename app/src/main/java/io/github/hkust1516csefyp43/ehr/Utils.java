package io.github.hkust1516csefyp43.ehr;

import android.content.Context;
import android.graphics.Color;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Random;

import io.github.hkust1516csefyp43.ehr.pojo.Patient;

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

    /**
     * If you guys are still using this app in 2100 it's your problem not mine
     *
     * @param year  of birthday
     * @param month of birthday
     * @param day   of birthday
     * @return a string of age description:
     * < 1 week:        X day(s) old
     * < 3 month:       XX week(s) old
     * < 2 year old:    XX month(s) old
     * else:            XX year(s) old
     */
    public static String birthdayToAgeString(int year, int month, int day) {
        if (year > 1900 && year < 2100) {
            //TODO babies >> XX months
            GregorianCalendar gc = new GregorianCalendar();
            int age = year - gc.get(Calendar.YEAR);
            int monthDiff = month - gc.get(Calendar.MONTH);
            int dayDiff = day - gc.get(Calendar.DAY_OF_MONTH);

            if (age == 0) {
                if (monthDiff == 0) {
                    //TODO XX days/weeks old
                } else {
                    //TODO XX months old
                }
            } else {
                if (monthDiff < 0) {
                    age--;
                }
                if (monthDiff == 0 && dayDiff < 0) {
                    age--;
                }
            }

           return null;
        } else {
            //Invalid birth year >> no age
           return null;
       }
    }

    public static String getTextDrawableText(Patient p) {
        String op;
        if (p != null) {
            if (p.getFirstName() != null) {
                if (p.getLastName() != null) {
                    op = p.getFirstName().substring(0, 1) + p.getLastName().substring(0, 1);
                } else {
                    op = p.getFirstName().substring(0, 1);
                }
            } else {
                if (p.getLastName() != null) {
                    op = p.getLastName().substring(0, 1);
                } else {
                    op = "?";
                }
            }
        } else {
            op = "?";
        }
        return op;
    }

    public static int getRandomColor() {
        Random rand = new Random();
        int r, g, b;
        do {
            r = rand.nextInt(255);
        } while (r < 50);
        do {
            g = rand.nextInt(255);
        } while (g < 50);
        do {
            b = rand.nextInt(255);
        } while (b < 50);
        return Color.rgb(r, g, b);
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

}
