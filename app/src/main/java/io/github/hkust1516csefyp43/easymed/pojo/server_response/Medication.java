package io.github.hkust1516csefyp43.easymed.pojo.server_response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Louis on 21/4/16.
 */
public class Medication implements Serializable {
  @SerializedName("create_timestamp")     Date createTimestamp;
  @SerializedName("medication")			      String medication;
  @SerializedName("medication_id")			  String medicationId;
  @SerializedName("user_id")			        String userId;

  public Medication() {
  }

  public Date getCreateTimestamp() {
    return createTimestamp;
  }

  public void setCreateTimestamp(Date createTimestamp) {
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
        "createTimestamp=" + createTimestamp +
        ", medication='" + medication + '\'' +
        ", medicationId='" + medicationId + '\'' +
        ", userId='" + userId + '\'' +
        '}';
  }
}
