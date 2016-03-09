package io.github.hkust1516csefyp43.ehr.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by kalongip on 7/3/16.
 */
public class PreviousMedicalHistory implements Serializable {
    @SerializedName("medicineName")
    private String medicineName;
    @SerializedName("medicalDescription")
    private String medicalDescription;

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

    public PreviousMedicalHistory(String medicineName, String medicalDescription) {

        this.medicineName = medicineName;
        this.medicalDescription = medicalDescription;
    }
}
