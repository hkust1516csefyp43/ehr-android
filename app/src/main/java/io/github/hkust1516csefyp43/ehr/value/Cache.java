package io.github.hkust1516csefyp43.ehr.value;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.util.List;

import io.github.hkust1516csefyp43.ehr.pojo.Chief_complain;
import io.github.hkust1516csefyp43.ehr.pojo.Patient;
import io.github.hkust1516csefyp43.ehr.pojo.Perscription;
import io.github.hkust1516csefyp43.ehr.pojo.User;

/**
 * Created by Louis on 5/11/15.
 */

public class Cache {

    //Current user

    public static void setUser(Context context, User user) {
        Gson gson = new GsonBuilder().create();
        String jsonString = gson.toJson(user);
        SharedPreferences prefs = context.getSharedPreferences(Const.KEY_SHARE_PREFERENCES, Context.MODE_PRIVATE);
        prefs.edit().putString(Const.KEY_CURRENT_USER, jsonString).apply();
    }

    public static User getUser(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(Const.KEY_SHARE_PREFERENCES, Context.MODE_PRIVATE);
        String value = prefs.getString(Const.KEY_CURRENT_USER, null);
        if (value != null) {
            return new Gson().fromJson(value, User.class);
        } else {
            return null;
        }
    }

    //The basic functions: get, set and delete

    /**
     * @param context can be fragment, activity, application, etc
     * @param key     is the string on how & where to find
     * @return
     */
    private static List<Patient> getPatients(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences(Const.KEY_SHARE_PREFERENCES, Context.MODE_PRIVATE);
        String value = prefs.getString(key, null);
        return new Gson().fromJson(value, new TypeToken<List<Patient>>() {
        }.getType());
    }

    private static void setPatients(Context context, String key, List<Patient> patients) {
        Gson gson = new GsonBuilder().create();
        String jsonString = gson.toJson(patients);
        SharedPreferences prefs = context.getSharedPreferences(Const.KEY_SHARE_PREFERENCES, Context.MODE_PRIVATE);
        prefs.edit().putString(key, jsonString).apply();
    }

    private static void clearPatients(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences(Const.KEY_SHARE_PREFERENCES, Context.MODE_PRIVATE);
        prefs.edit().remove(key).apply();
    }

    //------------------------- separation line ----------------------------------

    /**
     * @param context
     * @return
     */
    public static List<Patient> getPostTriagePatients(Context context) {
        return getPatients(context, Const.KEY_POST_TRIAGE_PATIENTS);
    }

    public static void setPostTriagePatients(Context context, List<Patient> postTriagePatients) {
        setPatients(context, Const.KEY_POST_TRIAGE_PATIENTS, postTriagePatients);
    }

    public static void clearPostTriagePatients(Context context) {
        clearPatients(context, Const.KEY_POST_TRIAGE_PATIENTS);
    }

    public static List<Patient> getPostPharmacyPatients(Context context) {
        return getPatients(context, Const.KEY_POST_PHARMACY_PATIENTS);
    }

    public static void setPostPharmacyPatients(Context context, List<Patient> postPharmacyPatients) {
        setPatients(context, Const.KEY_POST_PHARMACY_PATIENTS, postPharmacyPatients);
    }

    public static void clearPostPharmacyPatients(Context context) {
        clearPatients(context, Const.KEY_POST_PHARMACY_PATIENTS);
    }

    //---------------- non patient related stuff --------------------------

    public static class currentPatientVisit implements Serializable {
        //TODO save data to SharePreferences of every single fucking input fields every single fucking time

        //The basic functions

        private static String getString(Context context, String key) {
            return context.getSharedPreferences(Const.KEY_SHARE_PREFERENCES, Context.MODE_PRIVATE).getString(key, null);
        }

        private static void setString(Context context, String key, String value) {
            context.getSharedPreferences(Const.KEY_SHARE_PREFERENCES, Context.MODE_PRIVATE).edit().putString(key, value).apply();
        }

        private static int getInt(Context context, String key) {
            return context.getSharedPreferences(Const.KEY_SHARE_PREFERENCES, Context.MODE_PRIVATE).getInt(key, 0);
        }

        private static void setInt(Context context, String key, int value) {
            context.getSharedPreferences(Const.KEY_SHARE_PREFERENCES, Context.MODE_PRIVATE).edit().putInt(key, value).apply();
        }

        private static boolean getBoolean(Context context, String key) {
            return context.getSharedPreferences(Const.KEY_SHARE_PREFERENCES, Context.MODE_PRIVATE).getBoolean(key, false);
        }

        private static float getFloat(Context context, String key) {
            return context.getSharedPreferences(Const.KEY_SHARE_PREFERENCES, Context.MODE_PRIVATE).getFloat(key, 0f);
        }

        private static long getLong(Context context, String key) {
            return context.getSharedPreferences(Const.KEY_SHARE_PREFERENCES, Context.MODE_PRIVATE).getLong(key, 0L);
        }

        private static List<String> getStrings(Context context, String key) {
            return new Gson().fromJson(getString(context, key), new TypeToken<List<String>>() {
            }.getType());
        }

        private static List<Chief_complain> getChiefComplains(Context context, String key) {
            return new Gson().fromJson(getString(context, key), new TypeToken<List<Chief_complain>>() {
            }.getType());
        }

        private static List<Perscription> getPerscriptions(Context context, String key) {
            return new Gson().fromJson(getString(context, key), new TypeToken<List<Perscription>>() {
            }.getType());
        }

        private static void clearAllCurrentPatientVisitData(Context context) {
            //TODO clear everything
        }

        //------------------------- separation line ----------------------------------

        public static String getFirstName(Context context) {
            return getString(context, Const.KEY_CURRENT_PATIENT_FIRST_NAME);
        }

        public static void setFirstName(Context context, String firstName) {
            setString(context, Const.KEY_CURRENT_PATIENT_FIRST_NAME, firstName);
        }

        public static String getMiddleName(Context context) {
            return getString(context, Const.KEY_CURRENT_PATIENT_MIDDLE_NAME);
        }

        public static void setMiddleName(Context context, String middleName) {
            setString(context, Const.KEY_CURRENT_PATIENT_MIDDLE_NAME, middleName);
        }

        public static String getLastName(Context context) {
            return getString(context, Const.KEY_CURRENT_PATIENT_LAST_NAME);
        }

        public static void setLastName(Context context, String lastName) {
            setString(context, Const.KEY_CURRENT_PATIENT_LAST_NAME, lastName);
        }

        public static int getBirthYear(Context context) {
            return getInt(context, Const.KEY_CURRENT_PATIENT_BIRTH_YEAR);
        }

        public static void setBirthYear(Context context, int birthYear) {
            setInt(context, Const.KEY_CURRENT_PATIENT_BIRTH_YEAR, birthYear);
        }

        //previous medical history
        //screening
        //drug history
        //allergy
        //lmp year (int)
        //lmp month (int)
        //lmp day (int)
        //currently pregnant how many (int)
        //gestation (int)
        //currently breast feeding (boolean)
        //contraceptive use
        //(TBC)
    }
}
