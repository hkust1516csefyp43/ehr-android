package io.github.hkust1516csefyp43.ehr.pojo;

import java.io.Serializable;

/**
 * TODO user for disk cache
 * Created by Louis on 6/10/15.
 */
public class User implements Serializable{
    private String email;
    private String accessToken;
    private String refreshToken;
    private String firstName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
