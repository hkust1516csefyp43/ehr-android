package io.github.hkust1516csefyp43.easymed.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Louis on 20/4/16.
 */
public class Consultation implements Serializable{
  @SerializedName("consultation_id")    String id;
  @SerializedName("user_id")            String userId;
  @SerializedName("visit_id")           String visitId;

  public String getId() {
    return id;
  }


}
