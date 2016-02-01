package io.github.hkust1516csefyp43.ehr.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Louis on 23/11/15.
 */
public class ChiefComplain implements Serializable {
    @SerializedName("chief_complain_id")
    public String id;
    @SerializedName("name")
    public String name;

    public ChiefComplain(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id: ");
        sb.append(id);
        sb.append("; ");
        sb.append("name: ");
        sb.append(name);
        return sb.toString();
    }
}
