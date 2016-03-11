package io.github.hkust1516csefyp43.ehr.pojo.patient_visit;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Louis on 1/2/16.
 */
public class PMHFrag implements Serializable {
    @SerializedName("medicineName")
    private String medicineName;
    @SerializedName("medicalDescription")
    private String medicalDescription;

    public PMHFrag(String medicineName, String medicalDescription) {

        this.medicineName = medicineName;
        this.medicalDescription = medicalDescription;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getMedicalDescription() {
        return medicalDescription;
    }

    public void setMedicalDescription(String medicalDescription) {
        this.medicalDescription = medicalDescription;
    }
}
