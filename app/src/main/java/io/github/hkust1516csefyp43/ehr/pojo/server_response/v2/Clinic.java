package io.github.hkust1516csefyp43.ehr.pojo.server_response.v2;

import com.google.gson.annotations.SerializedName;

import io.github.hkust1516csefyp43.ehr.haveTitle;

/**
 * Created by Louis on 22/3/16.
 */
public class Clinic implements haveTitle {
    @SerializedName("clinic_id")
    private String clinicId;
    @SerializedName("english_name")
    private String englishName;
    @SerializedName("native_name")
    private String nativeName;
    @SerializedName("country_id")
    private String countryId;
    @SerializedName("suitcase_id")
    private String suitcaseId;
    @SerializedName("is_active")
    private Boolean isActive;
    @SerializedName("is_global")
    private Boolean isGlobal;
    @SerializedName("latitude")
    private Double latitude;
    @SerializedName("longitude")
    private Double longitude;
    @SerializedName("remark")
    private String remark;
    //TODO create_timestamp


    public Clinic(String clinicId, String englishName, String nativeName, String countryId, String suitcaseId, Boolean isActive, Boolean isGlobal, Double latitude, Double longitude, String remark) {
        this.clinicId = clinicId;
        this.englishName = englishName;
        this.nativeName = nativeName;
        this.countryId = countryId;
        this.suitcaseId = suitcaseId;
        this.isActive = isActive;
        this.isGlobal = isGlobal;
        this.latitude = latitude;
        this.longitude = longitude;
        this.remark = remark;
    }

    /**
     * print just the simple clinic format
     *
     * @return
     */
    @Override
    public String toString() {
        return clinicId + ": " + englishName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getClinicId() {
        return clinicId;
    }

    public void setClinicId(String clinicId) {
        this.clinicId = clinicId;
    }

    @Override
    public String getTitle() {
        return englishName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Boolean getGlobal() {
        return isGlobal;
    }

    public void setGlobal(Boolean global) {
        isGlobal = global;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getSuitcaseId() {
        return suitcaseId;
    }

    public void setSuitcaseId(String suitcaseId) {
        this.suitcaseId = suitcaseId;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }
}
