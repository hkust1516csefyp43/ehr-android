package io.github.hkust1516csefyp43.ehr.pojo.server_response.v2;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Louis on 22/3/16.
 */
public class Suitcase {
    @SerializedName("suitcase_id")      private String id;
    @SerializedName("suitcase_name")    private String name;

    public Suitcase(String id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "Suitcase{" + "id='" + id + '\'' + ", name='" + name + '\'' + '}';
    }
}
