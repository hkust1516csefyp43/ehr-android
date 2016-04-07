package io.github.hkust1516csefyp43.ehr.pojo.patient_visit;

import java.io.Serializable;

/**
 * Created by Louis on 6/4/16.
 */
public class VitalSigns implements Serializable {
    private Integer systolic;
    private Integer diastolic;
    private Integer pulseRate;
    private Integer respiratoryRate;
    private Float temperature;          //Celcius
    private Integer spo2;
    private Float weight;               //kg
    private Float height;               //m
    private Integer bloodSugar;         //mg/dL

    public VitalSigns(Integer systolic, Integer diastolic, Integer pulseRate, Integer respiratoryRate, Float temperature, Integer spo2, Float weight, Float height, Integer bloodSugar) {
        this.systolic = systolic;
        this.diastolic = diastolic;
        this.pulseRate = pulseRate;
        this.respiratoryRate = respiratoryRate;
        this.temperature = temperature;
        this.spo2 = spo2;
        this.weight = weight;
        this.height = height;
        this.bloodSugar = bloodSugar;
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

    public Float getTemperature() {
        return temperature;
    }

    public void setTemperature(Float temperature) {
        this.temperature = temperature;
    }

    public Integer getSpo2() {
        return spo2;
    }

    public void setSpo2(Integer spo2) {
        this.spo2 = spo2;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
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
