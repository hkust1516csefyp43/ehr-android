package io.github.hkust1516csefyp43.ehr.pojo.server_response.v2;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

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
    @SerializedName("create_timestamp")
    private Date create_timestamp;

    public Clinic(String clinicId, String englishName, String nativeName, String countryId, String suitcaseId, Boolean isActive, Boolean isGlobal, Double latitude, Double longitude, String remark, Date create_timestamp) {
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
        this.create_timestamp = create_timestamp;
    }

    @Override
    public String toString() {
        return getEnglishName();
    }

    public String getClinicId() {
        return clinicId;
    }

    public String getEnglishName() {
        return englishName;
    }

    @Override
    public String getTitle() {
        return englishName;
    }

}
