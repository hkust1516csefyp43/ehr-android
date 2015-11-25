package io.github.hkust1516csefyp43.ehr.value;

import java.util.List;

import io.github.hkust1516csefyp43.ehr.pojo.Patient;

/**
 * Created by Louis on 5/11/15.
 */
public class Cache {
    private static List<Patient> patients;

    public static List<Patient> getPatients() {
        if (patients != null) {
            return patients;
        } else {
            return null;
        }
    }

    public static void setPatients(List<Patient> patients) {
        Cache.patients = patients;
    }
}
