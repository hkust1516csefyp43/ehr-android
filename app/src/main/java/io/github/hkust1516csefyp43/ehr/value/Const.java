package io.github.hkust1516csefyp43.ehr.value;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mikepenz.aboutlibraries.Libs;
import com.mikepenz.aboutlibraries.LibsBuilder;

import org.json.JSONArray;
import org.json.JSONException;

import io.github.hkust1516csefyp43.ehr.BuildConfig;
import io.github.hkust1516csefyp43.ehr.R;

/**
 * Created by Louis on 17/9/15.
 */
public class Const {
    public final static int SPLASH_DISPLAY_LENGTH = 4000;

    public final static Gson gson1 = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
    public final static Gson gson2 = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").create();

    public final static int ID_TRIAGE = 1;
    public final static int ID_CONSULTATION = 2;
    public final static int ID_PHARMACY = 3;
    public final static int ID_INVENTORY = 4;
    public final static int ID_ADMIN = 6;
    public final static int ID_SETTINGS = 7;
    public final static int ID_ABOUT = 8;
    public final static int ID_LOGOUT = 9;

    public final static String KEY_SHARE_PREFERENCES = "EHR" + BuildConfig.FLAVOR;
    public final static String KEY_POST_TRIAGE_PATIENTS = "postTriagePatients";
    public final static String KEY_POST_CONSULTATION_PATIENTS = "postConsultationPatients";
    public final static String KEY_POST_PHARMACY_PATIENTS = "postPharmacyPatients";
    public final static String KEY_LOCAL_SETTINGS = "localSettings";
    public final static String KEY_CURRENT_USER = "currentUser";

    //------------------------------<current patient>------------------------------
    public static final String KEY_CURRENT_PATIENT_ = "currentPatient";

    public static final String KEY_PERSONAL_DATA = "personal_data";
    public static final String KEY_CURRENT_PATIENT_FIRST_NAME = "currentPatientFirstName";
    public static final String KEY_CURRENT_PATIENT_MIDDLE_NAME = "currentPatientMiddleName";
    public static final String KEY_CURRENT_PATIENT_LAST_NAME = "currentPatientLastName";
    public static final String KEY_CURRENT_PATIENT_BIRTH_YEAR = "currentPatientBirthYear";
    public static final String KEY_CURRENT_PATIENT_BIRTH_MONTH = "currentPatientBirthMonth";
    public static final String KEY_CURRENT_PATIENT_BIRTH_DAY = "currentPatientBirthDay";

    public static final String KEY_CURRENT_PATIENT_GENDER_ID = "currentPatientGenderId";
    public static final String KEY_CURRENT_PATIENT_STATUS = "currentPatientStatus";
    public static final String KEY_CURRENT_PATIENT_TELEPHONE = "currentPatientTelephone";
    public static final String KEY_CURRENT_PATIENT_ADDRESS = "currentPatientAddress";
    public static final String KEY_CURRENT_PATIENT_SYSTOLIC = "currentPatientSystolic";
    public static final String KEY_CURRENT_PATIENT_DIASTOLIC = "currentPatientDiastolic";
    public static final String KEY_CURRENT_PATIENT_PULSE_RATE = "currentPatientPulseRate";
    public static final String KEY_CURRENT_PATIENT_RESPIRATORY_RATE = "currentPatientRespiratoryRate";
    public static final String KEY_CURRENT_PATIENT_TEMPERATURE = "currentPatientTemperature";
    public static final String KEY_CURRENT_PATIENT_SPO2 = "currentPatientSpo2";
    public static final String KEY_CURRENT_PATIENT_WEIGHT = "currentPatientWeight";
    public static final String KEY_CURRENT_PATIENT_HEIGHT = "currentPatientHeight";
    public static final String KEY_CURRENT_PATIENT_LAST_DEWORMING_MONTH = "currentPatientLastDewormingMonth";
    public static final String KEY_CURRENT_PATIENT_LAST_DEWORMING_YEAR = "currentPatientLastDewormingYear";
    //------------------------------</current patient>------------------------------

    public static final String KEY_SNACKBAR_TEXT = "snackBarText";
    public final static String KEY_VIRGIN = "touch_for_the_very_first_time~";

    public static final String FLAVOR_ONE_2_ONE_CAMBODIA = "one2onecambodia";
    public static final String FLAVOR_FREE = "free";

    public static final String CONFIG_CLOUD_HOST = "cloud_api_host";
    public static final String CONFIG_LOCAL_HOST = "local_api_host";
    public static final String CONFIG_SSID_LIST = "ssid_list";

    public static final int ACTION_TAKE_PICTURE = 0;
    public static final int ACTION_SELECT_PICTURE = 1;
    public static final int ACTION_REMOVE_PICTURE = 2;

    public static final LibsBuilder ABOUT = new LibsBuilder()
            .withActivityStyle(Libs.ActivityStyle.LIGHT_DARK_TOOLBAR)
            .withAboutVersionShown(true)
            .withAboutIconShown(true)
            .withVersionShown(true)
            .withActivityTitle("About")
            .withFields(R.string.class.getFields())
            .withAboutAppName("EHR")        //TODO how to get app name on the fly
            .withAboutDescription("EHR 2015-2016 from SIGHT@HKUST x CSE@HKUST")
            .withSortEnabled(true);
    //------------------------------<One-2-One CAMBODIA specific data>----------------------------------
    public static final String API_ONE2ONE_HEROKU = "https://ehr-api.herokuapp.com:443/v1";
    public static final String API_ONE2ONE_RPi = "http://192.168.0.123:3000/v1";
    public static JSONArray LIST_ONE2ONE_SSID;
    public static JSONArray LIST_SSID;
    public static String API_CLOUD;
    //------------------------------</One-2-One CAMBODIA specific data>----------------------------------
    public static String API_LOCAL;

    static {
        try {
            LIST_ONE2ONE_SSID = new JSONArray("['sight_network', 'sight_network2', 'One-2-One Cambodia GroundFlor']");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void setCloudAPI(String api) {
        API_CLOUD = api;
    }
    public static void setLocalAPI(String api) {
        API_LOCAL = api;
    }
    public static void setListSSID(JSONArray ssid) {
        LIST_SSID = ssid;
    }
}
