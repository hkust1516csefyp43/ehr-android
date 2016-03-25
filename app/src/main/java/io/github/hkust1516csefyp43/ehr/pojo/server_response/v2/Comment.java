package io.github.hkust1516csefyp43.ehr.pojo.server_response.v2;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Louis on 25/3/16.
 */
public class Comment implements Serializable {
    @SerializedName("comment_id")
    private String id;
    @SerializedName("comment")
    private String comment;
    @SerializedName("user_id")
    private String userId;
    @SerializedName("visit_id")
    private String visitId;
    @SerializedName("create_timestamp")
    private Date createTimestamp;

    public Comment(String id, String comment, String userId, String visitId, Date createTimestamp) {
        this.id = id;
        this.comment = comment;
        this.userId = userId;
        this.visitId = visitId;
        this.createTimestamp = createTimestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getVisitId() {
        return visitId;
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

    public Date getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Date createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", comment='" + comment + '\'' +
                ", userId='" + userId + '\'' +
                ", visitId='" + visitId + '\'' +
                ", createTimestamp=" + createTimestamp +
                '}';
    }
}
