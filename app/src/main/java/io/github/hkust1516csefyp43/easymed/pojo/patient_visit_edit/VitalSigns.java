package io.github.hkust1516csefyp43.easymed.pojo.patient_visit_edit;


import java.io.Serializable;

/**
 * Created by Louis on 6/4/16.
 */
public class VitalSigns implements Serializable {
  private Integer systolic;
  private Integer diastolic;
  private Integer pulseRate;
  private Integer respiratoryRate;
  private Double temperature;          //Celsius
  private Integer spo2;               //%
  private Double weight;              //kg
  private Double height;              //m
  private Integer bloodSugar;         //mg/dL

  public VitalSigns() {
    //default empty constructor
  }

  public Integer getSystolic() {
    return systolic;
  }

  public void setSystolic(Integer systolic) {
    this.systolic = systolic;
  }

  public Integer getDiastolic() {
    return diastolic;
  }

  public void setDiastolic(Integer diastolic) {
    this.diastolic = diastolic;
  }

  public Integer getPulseRate() {
    return pulseRate;
  }

  public void setPulseRate(Integer pulseRate) {
    this.pulseRate = pulseRate;
  }

  public Integer getRespiratoryRate() {
    return respiratoryRate;
  }

  public void setRespiratoryRate(Integer respiratoryRate) {
    this.respiratoryRate = respiratoryRate;
  }

  public Double getTemperature() {
    return temperature;
  }

  public void setTemperature(Double temperature) {
    this.temperature = temperature;
  }

  public Integer getSpo2() {
    return spo2;
  }

  public void setSpo2(Integer spo2) {
    this.spo2 = spo2;
  }

  public Double getWeight() {
    return weight;
  }

  public void setWeight(Double weight) {
    this.weight = weight;
  }

  public Double getHeight() {
    return height;
  }

  public void setHeight(Double height) {
    this.height = height;
  }

  public Integer getBloodSugar() {
    return bloodSugar;
  }

  public void setBloodSugar(Integer bloodSugar) {
    this.bloodSugar = bloodSugar;
  }

  @Override
  public String toString() {
    return "VitalSigns{" +
        "systolic=" + systolic +
        ", diastolic=" + diastolic +
        ", pulseRate=" + pulseRate +
        ", respiratoryRate=" + respiratoryRate +
        ", temperature=" + temperature +
        ", spo2=" + spo2 +
        ", weight=" + weight +
        ", height=" + height +
        ", bloodSugar=" + bloodSugar +
        '}';
  }
}
