package io.github.hkust1516csefyp43.easymed.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Louis on 20/4/16.
 */
public class Attachment implements Serializable{

  @SerializedName("attachment_id")
  private String id;
  @SerializedName("cloudinary_url")
  private String url;
  @SerializedName("file_name")
  private String path;
  @SerializedName("user_id")
  private String userId;
  @SerializedName("create_timestamp")
  private Date createTimestamp;

  public Attachment(String id, String url, String path, String userId, Date createTimestamp) {
    this.id = id;
    this.url = url;
    this.path = path;
    this.userId = userId;
    this.createTimestamp = createTimestamp;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public Date getCreateTimestamp() {
    return createTimestamp;
  }

  public void setCreateTimestamp(Date createTimestamp) {
    this.createTimestamp = createTimestamp;
  }

  @Override
  public String toString() {
    return "Attachment{" +
        "id='" + id + '\'' +
        ", url='" + url + '\'' +
        ", path='" + path + '\'' +
        ", userId='" + userId + '\'' +
        ", createTimestamp=" + createTimestamp +
        '}';
  }

}
