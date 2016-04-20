package io.github.hkust1516csefyp43.easymed.POJO;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Louis on 20/4/16.
 */
public class BloodType implements Serializable {
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
}