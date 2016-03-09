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
    @SerializedName("startDate")
    private Object startDate;
    @SerializedName("endDate")
    private Object endDate;
    @SerializedName("underTreatment")
    private boolean underTreatment;

    public PreviousMedicalHistory(String medicineName, Object startDate, Object endDate, boolean underTreatment) {
        this.medicineName = medicineName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.underTreatment = underTreatment;
    }

    @Override
    public String toString() {
        return "Medication{" +
                "medicineName='" + medicineName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", underTreatment=" + underTreatment +
                '}';
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public Object getStartDate() {
        return startDate;
    }

    public void setStartDate(Object startDate) {
        this.startDate = startDate;
    }

    public Object getEndDate() {
        return endDate;
    }

    public void setEndDate(Object endDate) {
        this.endDate = endDate;
    }

    public boolean isUnderTreatment() {
        return underTreatment;
    }

    public void setUnderTreatment(boolean underTreatment) {
        this.underTreatment = underTreatment;
    }

}
