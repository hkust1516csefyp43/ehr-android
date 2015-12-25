package io.github.hkust1516csefyp43.ehr.value;

/**
 * Created by Louis on 17/9/15.
 */
public class Const {
    public final static int SPLASH_DISPLAY_LENGTH = 4000;

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
    public final static String API_HEROKU = "https://ehr-api.herokuapp.com:443/v1";

    //TODO change Heroku to One2One, Webstorm to Public
    public final static String PACKAGE_ONE2ONE = "io.github.hkust1516csefyp43.ehr.one2one";
    public final static String PACKAGE_PUBLIC = "io.github.hkust1516csefyp43.ehr.public";
    public final static String API_ONE2ONE_HEROKU = "https://ehr-api.herokuapp.com:443/v1";
    public final static String API_ONE2ONE_RPi = "http://192.168.0.123:3000/v1";

    public final static String KEY_SHARE_PREFERENCES = "EHR";

    public final static String KEY_POST_TRIAGE_PATIENTS = "postTriagePatients";
    public final static String KEY_POST_CONSULTATION_PATIENTS = "postConsultationPatients";
    public final static String KEY_POST_PHARMACY_PATIENTS = "postPharmacyPatients";

    public final static String KEY_LOCAL_SETTINGS = "localSettings";
    public final static String KEY_CURRENT_USER = "currentUser";

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
    //public static final String KEY_CURRENT_PATIENT_ = "currentPatient";
}
