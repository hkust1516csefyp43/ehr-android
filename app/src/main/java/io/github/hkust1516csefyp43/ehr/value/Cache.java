package io.github.hkust1516csefyp43.ehr.value;

import java.util.List;

import io.github.hkust1516csefyp43.ehr.pojo.Patient;
import io.github.hkust1516csefyp43.ehr.pojo.Slum;

/**
 * Created by Louis on 5/11/15.
 */

public class Cache {
    private static List<Patient> postTriagePatients;
    private static List<Patient> postPharmacyPatients;
    private static List<Slum> slums;

    public static List<Patient> getPostTriagePatients() {
        return postTriagePatients;
    }

    public static void setPostTriagePatients(List<Patient> postTriagePatients) {
        Cache.postTriagePatients = postTriagePatients;
    }

    public static void clearPostTriagePatients() {
        postTriagePatients = null;
    }

    public static List<Patient> getPostPharmacyPatients() {
        return postPharmacyPatients;
    }

    public static void setPostPharmacyPatients(List<Patient> postPharmacyPatients) {
        Cache.postPharmacyPatients = postPharmacyPatients;
    }

    public static void clearPostPharmacyPatients() {
        postPharmacyPatients = null;
    }
}
