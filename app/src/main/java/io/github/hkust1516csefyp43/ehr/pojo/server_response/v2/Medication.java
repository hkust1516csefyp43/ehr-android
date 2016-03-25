package io.github.hkust1516csefyp43.ehr.pojo.server_response.v2;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Louis on 25/3/16.
 */
public class Medication implements Serializable {

    @SerializedName("create_timestamp")
    private String createTimestamp;
    @SerializedName("medication")
    private String medication;
    @SerializedName("medication_id")
    private String medicationId;
    @SerializedName("user_id")
    private String userId;

    public Medication(String createTimestamp, String medication, String medicationId, String userId) {
        this.createTimestamp = createTimestamp;
        this.medication = medication;
        this.medicationId = medicationId;
        this.userId = userId;
    }

    public String getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(String createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public String getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(String medicationId) {
        this.medicationId = medicationId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Medication{" +
                "createTimestamp='" + createTimestamp + '\'' +
                ", medication='" + medication + '\'' +
                ", medicationId='" + medicationId + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
