package io.github.hkust1516csefyp43.ehr.value;

/**
 * Created by Louis on 17/9/15.
 */
public class Const {
    public final static int ID_TRIAGE = 1;
    public final static int ID_CONSULTATION = 2;
    public final static int ID_PHARMACY = 3;
    public final static int ID_INVENTORY = 4;
    public final static int ID_ADMIN = 6;
    public final static int ID_SETTINGS = 7;
    public final static int ID_ABOUT = 8;
    public final static int ID_LOGOUT = 9;

    public final static String PACKAGE_HEROKU = "io.github.hkust1516csefyp43.ehr.heroku";
    public final static String PACKAGE_WEBSTORM = "io.github.hkust1516csefyp43.ehr.webstorm";

    public final static String PACKAGE_ONE2ONE_HEROKU = "";
    public final static String PACKAGE_ONE2ONE_RPI = "";

    public final static String API_HEROKU = "https://ehr-api.herokuapp.com:443/v1";
    public final static String API_WEBSTORM = "http://localhost:3000/v1";

    public final static int SPLASH_DISPLAY_LENGTH = 4000;

    public final static String KEY_SHARE_PREFERENCES = "EHR";
    public final static String KEY_POST_TRIAGE_PATIENTS = "postTriagePatients";
    public final static String KEY_POST_CONSULTATION_PATIENTS = "postConsultationPatients";
    public final static String KEY_POST_PHARMACY_PATIENTS = "postPharmacyPatients";
    public final static String KEY_LOCAL_SETTINGS = "localSettings";
    public final static String KEY_CURRENT_USER = "currentUser";
}
