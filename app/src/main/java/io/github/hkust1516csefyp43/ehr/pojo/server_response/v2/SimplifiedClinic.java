package io.github.hkust1516csefyp43.ehr.pojo.server_response.v2;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Louis on 16/3/16.
 */
public class SimplifiedClinic {
    @SerializedName("clinic_id")
    String clinicID;
    @SerializedName("english_name")
    String englishName;
}
