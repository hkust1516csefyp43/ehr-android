package io.github.hkust1516csefyp43.easymed.utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.util.GregorianCalendar;
import java.util.List;

import io.github.hkust1516csefyp43.easymed.pojo.server_response.Clinic;
import io.github.hkust1516csefyp43.easymed.pojo.server_response.Notification;
import io.github.hkust1516csefyp43.easymed.pojo.server_response.Query;
import io.github.hkust1516csefyp43.easymed.pojo.server_response.User;

/**
 * Created by Louis on 20/4/16.
 */
public class Cache {
  public static void setString(Context context, String objectString, String key) {
    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
    prefs.edit().putString(key, objectString).apply();
  }

  public static String getString(Context context, String key, @Nullable String ifNull) {
    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
    return prefs.getString(key, ifNull);
  }

  public static void setLong(Context context, long number, String key) {
    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
    prefs.edit().putLong(key, number).apply();
  }

  public static long getLong(Context context, String key, @Nullable long ifNull) {
    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
    return prefs.getLong(key, ifNull);
  }

  public static void deleteSomething(Context context, String key) {
    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
    prefs.edit().remove(key).apply();
  }

  public static class CurrentUser {
    //user info
    public static void setUser(Context context, User user) {
      Gson gson = new GsonBuilder().create();
      String jsonString = gson.toJson(user);
      setString(context, jsonString, Const.CacheKey.CURRENT_USER_INFO);
    }

    public static User getUser(Context context) {
      String value = getString(context, Const.CacheKey.CURRENT_USER_INFO, null);
      if (value != null) {
        return new Gson().fromJson(value, User.class);
      } else {
        return null;
      }
    }

    public static void removeUser(Context context) {
      deleteSomething(context, Const.CacheKey.CURRENT_USER_INFO);
    }

    //tokens
    public static void setAccessToken(Context context, String accessToken) {
      setString(context, accessToken, Const.CacheKey.CURRENT_ACCESS_TOKEN);
    }

    public static String getAccessToken(Context context) {
      return getString(context, Const.CacheKey.CURRENT_ACCESS_TOKEN, null);
    }

    public static void deleteAccessToken(Context context) {
      deleteSomething(context, Const.CacheKey.CURRENT_ACCESS_TOKEN);
    }

    public static void setRefreshToken(Context context, String refreshToken) {
      setString(context, refreshToken, Const.CacheKey.CURRENT_REFRESH_TOKEN);
    }

    public static String getRefreshToekn(Context context) {
      return getString(context, Const.CacheKey.CURRENT_REFRESH_TOKEN, null);
    }

    public static void deleteRefreshToken(Context context) {
      deleteSomething(context, Const.CacheKey.CURRENT_REFRESH_TOKEN);
    }

    //clinic
    public static void setClinic(Context context, Clinic clinic) {
      Gson gson = new GsonBuilder().create();
      String jsonString = gson.toJson(clinic);
      setString(context, jsonString, Const.CacheKey.CURRENT_CLINIC);
    }

    public static Clinic getClinic(Context context) {
      String value = getString(context, Const.CacheKey.CURRENT_CLINIC, null);
      if (value != null) {
        return new Gson().fromJson(value, Clinic.class);
      } else {
        return null;
      }
    }

    public static void removeClinic(Context context) {
      deleteSomething(context, Const.CacheKey.CURRENT_CLINIC);
    }

    //notifications
    public static void setNotifications(Context context, List<Notification> notifications) {
      Gson gson = new GsonBuilder().create();
      String jsonstring = gson.toJson(notifications);
      setString(context, jsonstring, Const.CacheKey.MY_NOTIFICATIONS);
    }

    public static List<Notification> getNotifications(Context context) {
      String value = getString(context, Const.CacheKey.MY_NOTIFICATIONS, null);
      if (value != null) {
        try {
          List<Notification> lp = new Gson().fromJson(value, new TypeToken<List<Notification>>() {
          }.getType());
          return lp;
        } catch (JsonSyntaxException e) {
          e.printStackTrace();
          //TODO for some reason it occasionally got incorrect syntax stuff -_-
        }
      }
      return null;
    }

    public static void clearNotifications (Context context) {
      deleteSomething(context, Const.CacheKey.MY_NOTIFICATIONS);
    }

    //logout (clear everything)
    public static void logout(Context context) {
      removeUser(context);
      removeClinic(context);
      clearNotifications(context);
    }
  }

  public static class Synchronisation {
    //last push
    public static void setLastPushToCloud(Context context, GregorianCalendar gregorianCalendar) {
      setLong(context, gregorianCalendar.getTimeInMillis(), Const.CacheKey.SYNC_LAST_PUSH_TO_CLOUD);
    }

    public static GregorianCalendar getLastPushToCloud(Context context) {
      GregorianCalendar gregorianCalendar = new GregorianCalendar();
      long mills = getLong(context, Const.CacheKey.SYNC_LAST_PUSH_TO_CLOUD, -1);
      if (mills > 0) {
        gregorianCalendar.setTimeInMillis(mills);
        return gregorianCalendar;
      }
      return null;
    }

    //last pull
    public static void setLastPullFromCloud(Context context, GregorianCalendar gregorianCalendar) {
      setLong(context, gregorianCalendar.getTimeInMillis(), Const.CacheKey.SYNC_LAST_PULL_FROM_CLOUD);
    }

    public static GregorianCalendar getLastPullFromCloud(Context context) {
      GregorianCalendar gregorianCalendar = new GregorianCalendar();
      long mills = getLong(context, Const.CacheKey.SYNC_LAST_PULL_FROM_CLOUD, -1);
      if (mills > 0) {
        gregorianCalendar.setTimeInMillis(mills);
        return gregorianCalendar;
      }
      return null;
    }


    //last push
    public static void setLastPushToLocal(Context context, GregorianCalendar gregorianCalendar) {
      setLong(context, gregorianCalendar.getTimeInMillis(), Const.CacheKey.SYNC_LAST_PUSH_TO_LOCAL);
    }

    public static GregorianCalendar getLastPushToLocal(Context context) {
      GregorianCalendar gregorianCalendar = new GregorianCalendar();
      long mills = getLong(context, Const.CacheKey.SYNC_LAST_PUSH_TO_LOCAL, -1);
      if (mills > 0) {
        gregorianCalendar.setTimeInMillis(mills);
        return gregorianCalendar;
      }
      return null;
    }

    //last pull
    public static void setLastPullFromLocal(Context context, GregorianCalendar gregorianCalendar) {
      setLong(context, gregorianCalendar.getTimeInMillis(), Const.CacheKey.SYNC_LAST_PULL_FROM_LOCAL);
    }

    public static GregorianCalendar getLastPullFromLocal(Context context) {
      GregorianCalendar gregorianCalendar = new GregorianCalendar();
      long mills = getLong(context, Const.CacheKey.SYNC_LAST_PULL_FROM_LOCAL, -1);
      if (mills > 0) {
        gregorianCalendar.setTimeInMillis(mills);
        return gregorianCalendar;
      }
      return null;
    }

    public static void setPullFromCloudData(Context context, List<Query> queries) {
      Gson gson = new GsonBuilder().create();
      String jsonstring = gson.toJson(queries);
      setString(context, jsonstring, Const.CacheKey.QUERIES_FROM_CLOUD);
    }

    public static List<Query> getPullFromCloudData(Context context) {
      String value = getString(context, Const.CacheKey.QUERIES_FROM_CLOUD, null);
      if (value != null) {
        try {
          List<Query> lp = new Gson().fromJson(value, new TypeToken<List<Query>>() {
          }.getType());
          return lp;
        } catch (JsonSyntaxException e) {
          e.printStackTrace();
          //TODO for some reason it occasionally got incorrect syntax stuff -_-
        }
      }
      return null;
    }

    public static void setPullFromLocalData(Context context, List<Query> queries) {
      Gson gson = new GsonBuilder().create();
      String jsonstring = gson.toJson(queries);
      setString(context, jsonstring, Const.CacheKey.QUERIES_FROM_LOCAL);
    }

    public static List<Query> getPullFromLocalData(Context context) {
      String value = getString(context, Const.CacheKey.QUERIES_FROM_LOCAL, null);
      if (value != null) {
        try {
          List<Query> lp = new Gson().fromJson(value, new TypeToken<List<Query>>() {
          }.getType());
          return lp;
        } catch (JsonSyntaxException e) {
          e.printStackTrace();
          //TODO for some reason it occasionally got incorrect syntax stuff -_-
        }
      }
      return null;
    }

  }
}
