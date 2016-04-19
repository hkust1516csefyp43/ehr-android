package io.github.hkust1516csefyp43.easymed;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import io.github.hkust1516csefyp43.easymed.POJO.Clinic;
import io.github.hkust1516csefyp43.easymed.POJO.Notification;

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

  public static void deleteSomething(Context context, String key) {
    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
    prefs.edit().remove(key).apply();
  }

  public static class CurrentUser {
    //user info


    //tokens

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
      removeClinic(context);
      clearNotifications(context);
    }
  }
}
