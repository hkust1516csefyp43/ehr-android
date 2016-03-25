package io.github.hkust1516csefyp43.ehr.pojo.server_response.v2;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Louis on 25/3/16.
 */
public class EmergencyContact implements Serializable {
    @SerializedName("emergency_contact_id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("user_id")
    private String userId;
    @SerializedName("email_1")
    private String email1;
    @SerializedName("email_2")
    private String email2;
    @SerializedName("email_3")
    private String email3;
    @SerializedName("phone_number_1")
    private String phone1;
    @SerializedName("phone_number_2")
    private String phone2;
    @SerializedName("phone_number_3")
    private String phone3;
    @SerializedName("relationship_description")
    private String relationship;
    @SerializedName("remark")
    private String remark;
    @SerializedName("create_timestamp")
    private Date createTimestamp;

    public EmergencyContact(String id, String name, String userId, String email1, String email2, String email3, String phone1, String phone2, String phone3, String relationship, String remark, Date createTimestamp) {
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.email1 = email1;
        this.email2 = email2;
        this.email3 = email3;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.phone3 = phone3;
        this.relationship = relationship;
        this.remark = remark;
        this.createTimestamp = createTimestamp;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail1() {
        return email1;
    }

    public void setEmail1(String email1) {
        this.email1 = email1;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public String getEmail3() {
        return email3;
    }

    public void setEmail3(String email3) {
        this.email3 = email3;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getPhone3() {
        return phone3;
    }

    public void setPhone3(String phone3) {
        this.phone3 = phone3;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Date createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    @Override
    public String toString() {
        return "EmergencyContact{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", userId='" + userId + '\'' +
                ", email1='" + email1 + '\'' +
                ", email2='" + email2 + '\'' +
                ", email3='" + email3 + '\'' +
                ", phone1='" + phone1 + '\'' +
                ", phone2='" + phone2 + '\'' +
                ", phone3='" + phone3 + '\'' +
                ", relationship='" + relationship + '\'' +
                ", remark='" + remark + '\'' +
                ", createTimestamp=" + createTimestamp +
                '}';
    }
}
