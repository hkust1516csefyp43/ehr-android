package io.github.hkust1516csefyp43.ehr.pojo.server_response.v2;

import com.google.gson.annotations.SerializedName;

import io.github.hkust1516csefyp43.ehr.haveTitle;

/**
 * Created by Louis on 15/3/16.
 */
public class BloodType implements haveTitle {
    @SerializedName("blood_type_id")private String id;
    @SerializedName("blood_type") private String type;

    public BloodType(String id, String type) {
        this.id = id;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "BloodType{" + "id='" + id + '\'' + ", type='" + type + '\'' + '}';
    }

    @Override
    public String getTitle() {
        return getType();
    }
}
