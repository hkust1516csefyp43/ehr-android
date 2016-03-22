package io.github.hkust1516csefyp43.ehr.pojo.server_response.v2;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Louis on 22/3/16.
 */
public class Clinic {
    @SerializedName("clinic_id")
    private String clinicId;
    @SerializedName("english_name")
    private String englishName;

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
}
