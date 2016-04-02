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

    public final static Gson GsonParserThatWorksWithPGTimestamp
            = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
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

    public static final String KEY_HPI = "currentPatientHPI";
    public static final String KEY_FAMILY_HISTORY = "currentPatientFamilyHistory";
    public static final String KEY_SOCIAL_HISTORY = "currentPatientSocialHistory";
    public static final String KEY_IS_TRIAGE = "istriage";
    public static final String KEY_WHICH_STATION = "keywhichstation";
    //------------------------------<current patient>------------------------------
    public static final String KEY_SNACKBAR_TEXT = "snackBarText";
    public final static String KEY_VIRGIN = "touch_for_the_very_first_time~";
    public final static String KEY_EMERGENCY_FIX = "\uD83D\uDE13";
    public final static int NUMBER_EMERGENCY_FIX = 1;
    public static final String FLAVOR_ONE_2_ONE_CAMBODIA = "one2onecambodia";
    public static final String FLAVOR_FREE = "free";
    //------------------------------<server config>------------------------------
    public static final String CONFIG_CLOUD_HOST = "cloud_api_host";
    public static final String CONFIG_LOCAL_HOST = "local_api_host";
    public static final String CONFIG_SSID_LIST = "ssid_list";
    //<One-2-One CAMBODIA specific data>
    public static final String API_ONE2ONE_HEROKU = "https://ehr-api.herokuapp.com:443/v1";
    public static final String API_ONE2ONE_RPi = "http://192.168.0.123:3000/v1";
    //------------------------------</server config>------------------------------
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
    //----------------------------<Patients list>------------------------------------
    public static final int PATIENT_LIST_POST_TRIAGE = 1;
    public static final int PATIENT_LIST_ALL_PATIENTS = 2;
    public static final int PATIENT_LIST_PRE_CONSULTATION = 1;      //Yup, it is the same as post triage :joy:
    public static final int PATIENT_LIST_POST_CONSULTATION = 3;
    public static final int PATIENT_LIST_PRE_PHARMACY = 4;          //It is not necessary same as Post Consultation because some patients need no medication
    public static final int PATIENT_LIST_ALL_TODAYS_PATIENT = 5;
    public static final String KEY_TITLE = "title";
    public static final String KEY_PRE_FILL_ITEMS = "pre_fill_items";
    public static final String[] DEFAULT_PHYSICAL_EXAMINATION = {"General Appearance", "Respiratory", "Cardiovascular", "Gastrointestinal", "Genital/Urinary", "ENT", "Skin", "Other"};
    public static final String[] DEFAULT_REVICE_OF_SYSTEM = {"EENT", "Respiratory", "Cardiovascular", "Gastrointestinal", "Genital/Urinary", "ENT", "Skin", "Locomotor", "Neurology"};
    //------------------------------<Static activity data>----------------------------------
    public static final int STATIC_CLINIC = 0;
    public static final int STATIC_KEYWORD = 1;
    public static final int STATIC_SUITCASE = 2;
    public static final int STATIC_BLOOD_TYPE = 3;
    public static final int STATIC_COUNTRIES = 4;
    public static final int STATIC_GENDER = 5;
    public static final int STATIC_USERS = 6;
    //------------------------------</Static activity data>----------------------------------
    public static final String KEY_WHICH_STATIC = "key_which_static";
    public static final String KEY_CLINICS = "list_of_clinics";
    public static final String EXTRA_CACHE_KEY = "cache_key";
    public static final String EXTRA_WHICH_ONE = "which_list_of_patients";
    public static final String KEY_BLOOD_TYPES = "kwy_blood_type";
    public static final String KEY_KEYWORDS = "just_keywords";
    public static final String KEY_CURRENT_CLINIC = "currentclinic";
    public static final String KEY_PATIENT = "thePatientIsBeingPassedToPVA";
    public static final String KEY_TRIAGE = "theTriageIsBeingPassedToPVA";
    public static final String KEY_CONSULTATION = "theConsultationIsBeingPassedToPVA";
    public static String KEY_LIST_OF_CARD_FRAGMENT = "locf";
    public static JSONArray SSID_LIST_ONE2ONE;
    public static String API_LOCAL;
    public static String API_CLOUD;
    public static JSONArray LIST_SSID;
    /**
     * BOY
     * http://www.who.int/childgrowth/standards/WFA_boys_0_5_zscores.pdf?ua=1
     * -3, -2, Median, +2, +3 (+-1 does not matter)
     */
    public static double[][] weightForAgeBoyUnder5 = new double[][]{
            {2.1, 2.9, 3.8, 4.4, 4.9, 5.3, 5.7, 5.9, 6.2, 6.4, 6.6, 6.8, 6.9, 7.1, 7.2, 7.4, 7.5, 7.7, 7.8, 8.0, 8.1, 8.2, 8.4, 8.5, 8.6, 8.8, 8.9, 9.0, 9.1, 9.2, 9.4, 9.5, 9.6, 9.7, 9.8, 9.9, 10.0, 10.1, 10.2, 10.3, 10.4, 10.5, 10.6, 10.7, 10.8, 10.9, 11.0, 11.1, 11.2, 11.3, 11.4, 11.5, 11.6, 11.7, 11.8, 11.9, 12.0, 12.1, 12.2, 12.3, 12.4},
            {2.5, 3.4, 4.3, 5.0, 5.6, 6.0, 6.4, 6.7, 6.9, 7.1, 7.4, 7.6, 7.7, 7.9, 8.1, 8.3, 8.4, 8.6, 8.8, 8.9, 9.1, 9.2, 9.4, 9.5, 9.7, 9.8, 10.0, 10.1, 10.2, 10.4, 10.5, 10.7, 10.8, 10.9, 11.0, 11.2, 11.3, 11.4, 11.5, 11.6, 11.8, 11.9, 12.0, 12.1, 12.2, 12.4, 12.5, 12.6, 12.7, 12, 8, 12.9, 13.1, 13.2, 13.3, 13.4, 13.5, 13.6, 13.7, 13.8, 14.0, 14.1},
            {3.3, 4.5, 5.6, 6.4, 7.0, 7.5, 7.9, 8.3, 8.6, 8.9, 9.2, 9.4, 9.6, 9.9, 10.1, 10.3, 10.5, 10.7, 10.9, 11.1, 11.3, 11.5, 11.8, 12.0, 12.2, 12.4, 12.5, 12.7, 12.9, 13.1, 13.3, 13.5, 13.7, 13.8, 14.0, 14.2, 14.3, 14.5, 14.7, 14.8, 15.0, 15.2, 15.3, 15.5, 15.7, 15.8, 16.0, 16.2, 16.3, 16.5, 16.7, 16.8, 17.0, 17.2, 17.3, 17.5, 17.7, 17.8, 18.0, 18.2, 18.3},
            {4.4, 5.8, 7.1, 8.0, 8.7, 9.3, 9.8, 10.3, 10.7, 11.0, 11.4, 11.7, 12.0, 12.3, 12.6, 12.8, 13.1, 13.4, 13.7, 13.9, 14.2, 14.5, 14.7, 15.0, 15.3, 15.5, 15.8, 16.1, 16.3, 16.6, 16.9, 17.1, 17.4, 17.6, 17.8, 18.1, 18.3, 18.6, 18.8, 19.0, 19.3, 19.5, 19.7, 20.0, 20.2, 20.5, 20.7, 20.9, 21.2, 21.4, 21.7, 21.9, 22.2, 22.4, 22.7, 22.9, 23.2, 23.4, 23.7, 23.9, 24.2},
            {5.0, 6.6, 8.0, 9.0, 9.7, 10.4, 10.9, 11.4, 11.9, 12.3, 12.7, 13.0, 13.3, 13.7, 14.0, 14.3, 14.6, 14.9, 15.3, 15.6, 15.9, 16.2, 16.5, 16.8, 17.1, 17.5, 17.8, 18.1, 18.4, 18.7, 19.0, 19.3, 19.6, 19.9, 20.2, 20.4, 20.7, 21.0, 21.3, 21.6, 21.9, 22.1, 22.4, 22.7, 23.0, 23.3, 23.6, 23.9, 24.2, 24.5, 24.8, 25.1, 25.4, 25.7, 26.0, 26.3, 26.6, 26.9, 27.2, 27.6, 27.9}
    };
    /**
     * GIRL
     * http://www.who.int/childgrowth/standards/WFA_girls_0_5_zscores.pdf?ua=1
     */
    public static double[][] weightForAgeGirlUnder5 = new double[][]{
            {2.0, 2.7, 3.4, 4.0, 4.4, 4.8, 5.1, 5.3, 5.6, 5.8, 5.9, 6.1, 6.3, 6.4, 6.6, 6.7, 6.9, 7.0, 7.2, 7.3, 7.5, 7.6, 7.8, 7.9, 8.1, 8.2, 8.4, 8.5, 8.6, 8.8, 8.9, 9.0, 9.1, 9.3, 9.4, 9.5, 9.6, 9.7, 9.8, 9.9, 10.1, 10.2, 10.3, 10.4, 10.5, 10.6, 10.7, 10.8, 10.9, 11.0, 11.1, 11.2, 11.3, 11.4, 11.5, 11.6, 11.7, 11.8, 11.9, 12.0, 12.1},
            {2.4, 3.2, 3.9, 4.5, 5.0, 5.4, 5.7, 6.0, 6.3, 6.5, 6.7, 6.9, 7.0, 7.2, 7.4, 7.6, 7.7, 7.9, 8.1, 8.2, 8.4, 8.6, 8.7, 8.9, 9.0, 9.2, 9.4, 9.5, 9.7, 9.8, 10.0, 10.1, 10.3, 10.4, 10.5, 10.7, 10.8, 10.9, 11.1, 11.2, 11.3, 11.5, 11.6, 11.7, 11.8, 12.0, 12.1, 12.2, 12.3, 12.4, 12.6, 12.7, 12.8, 12.9, 13.0, 13.2, 13.3, 13.4, 13.5, 13.6, 13.7},
            {3.2, 4.2, 5.1, 5.8, 6.4, 6.9, 7.3, 7.6, 7.9, 8.2, 8.5, 8.7, 8.9, 9.2, 9.4, 9.6, 9.8, 10.0, 10.2, 10.4, 10.6, 10.9, 11.1, 11.3, 11.5, 11.7, 11.9, 12.1, 12.3, 12.5, 12.7, 12.8, 13.1, 13.3, 13.5, 13.7, 13.9, 14.0, 14.2, 14.4, 14.6, 14.8, 15.0, 15.2, 15.3, 15.5, 15.7, 15.9, 16.1, 16.3, 16.4, 16.6, 16.8, 17.0, 17.2, 17.3, 17.5, 17.7, 17.9, 18.0, 18.2},
            {4.2, 5.5, 6.6, 7.5, 8.2, 8.8, 9.3, 9.8, 10.2, 10.5, 10.9, 11.2, 11.5, 11.8, 12.1, 12.4, 12.6, 12.9, 13.2, 13.5, 13.7, 14.0, 14.3, 14.6, 14.8, 15.1, 15.4, 15.7, 16.0, 16.2, 16.5, 16.8, 17.1, 17.3, 17.6, 17.9, 18.1, 18.4, 18.7, 19.0, 19.2, 19.5, 19.8, 20.1, 20.4, 20.7, 20.9, 21.2, 21.5, 21.8, 22.1, 22.4, 22.6, 22.9, 23.2, 23.5, 23.8, 24.1, 24.4, 24.6, 24.9},
            {4.8, 6.2, 7.5, 8.5, 9.3, 10.0, 10.6, 11.1, 11.6, 12.0, 12.4, 12.8, 13.1, 13.5, 13.8, 14.1, 14.5, 14.8, 15.1, 15.4, 15.7, 16.0, 16.4, 16.7, 17.0, 17.3, 17.7, 18.0, 18.3, 18.7, 19.0, 19.3, 19.6, 20.0, 20.3, 20.6, 20.9, 21.3, 21.6, 22.0, 22.3, 22.7, 23.0, 23.4, 23.7, 24.1, 24.5, 24.8, 25.2, 25.5, 25.9, 26.3, 26.6, 27.0, 27.4, 27.7, 28.1, 28.5, 28.8, 29.2, 20.5}
    };

    static {
        try {
            SSID_LIST_ONE2ONE = new JSONArray("['sight_network', 'sight_network2', 'One-2-One Cambodia GroundFlor']");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public enum WeightForAgeStatus {
        OVERWEIGHT, NORMAL, UNDERWEIGHT, TOO_UNDERWEIGHT
    }
}
