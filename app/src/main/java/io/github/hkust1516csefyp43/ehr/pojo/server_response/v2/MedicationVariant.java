package io.github.hkust1516csefyp43.ehr.pojo.server_response.v2;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Louis on 25/3/16.
 */
public class MedicationVariant implements Serializable {
    @SerializedName("medication_variant_id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("form")
    private String form;
    @SerializedName("medication_id")
    private String medicationId;
    @SerializedName("suitcase_id")
    private String suitcaseId;
    @SerializedName("user_id")
    private String userId;
    @SerializedName("remark")
    private String remark;
    @SerializedName("stock")
    private int stock;          //0 >> no, 1 >> very little, 2 >> (more than) enough
    @SerializedName("strength")
    private String strength;    //it is just a description

    public MedicationVariant(String id, String name, String form, String medicationId, String suitcaseId, String userId, String remark, int stock, String strength) {
        this.id = id;
        this.name = name;
        this.form = form;
        this.medicationId = medicationId;
        this.suitcaseId = suitcaseId;
        this.userId = userId;
        this.remark = remark;
        this.stock = stock;
        this.strength = strength;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(String medicationId) {
        this.medicationId = medicationId;
    }

    public String getSuitcaseId() {
        return suitcaseId;
    }

    public void setSuitcaseId(String suitcaseId) {
        this.suitcaseId = suitcaseId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    @Override
    public String toString() {
        return "MedicationVariant{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", form='" + form + '\'' +
                ", medicationId='" + medicationId + '\'' +
                ", suitcaseId='" + suitcaseId + '\'' +
                ", userId='" + userId + '\'' +
                ", remark='" + remark + '\'' +
                ", stock=" + stock +
                ", strength='" + strength + '\'' +
                '}';
    }
}
