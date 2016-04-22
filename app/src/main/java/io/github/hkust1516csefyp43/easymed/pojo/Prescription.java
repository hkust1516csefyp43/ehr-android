package io.github.hkust1516csefyp43.easymed.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Louis on 8/4/16.
 */
public class Prescription implements Serializable {
  @SerializedName("prescription_id")
  String id;
  @SerializedName("consultation_id")
  String consultationId;
  @SerializedName("medication_id")
  String medicationId;
  @SerializedName("prescription_detail")
  String detail;
  @SerializedName("prescribed")
  Boolean prescribed;
  String medicationName;


  public Prescription() {
    //empty constructor
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getConsultationId() {
    return consultationId;
  }

  public void setConsultationId(String consultationId) {
    this.consultationId = consultationId;
  }

  public String getMedicationId() {
    return medicationId;
  }

  public void setMedicationId(String medicationId) {
    this.medicationId = medicationId;
  }

  public String getDetail() {
    return detail;
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }

  public Boolean getPrescribed() {
    return prescribed;
  }

  public void setPrescribed(Boolean prescribed) {
    this.prescribed = prescribed;
  }

  public String getMedicationName() {
    return medicationName;
  }

  public void setMedicationName(String medicationName) {
    this.medicationName = medicationName;
  }

  @Override
  public String toString() {
    return "Prescription{" +
        "id='" + id + '\'' +
        ", consultationId='" + consultationId + '\'' +
        ", medicationId='" + medicationId + '\'' +
        ", detail='" + detail + '\'' +
        ", prescribed=" + prescribed +
        ", medicationName='" + medicationName + '\'' +
        '}';
  }
}