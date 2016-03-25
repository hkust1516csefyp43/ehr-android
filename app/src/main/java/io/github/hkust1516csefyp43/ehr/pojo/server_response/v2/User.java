package io.github.hkust1516csefyp43.ehr.pojo.server_response.v2;

import java.io.Serializable;

/**
 * Created by Louis on 23/3/16.
 */
public class User implements Serializable {

    private String email;
    private String username;
    private String accessToken;
    private String refreshToken;
    private String firstName;

    public User(String email, String username, String accessToken, String refreshToken, String firstName) {
        this.email = email;
        this.username = username;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.firstName = firstName;
    }

    /**
     * @param email
     * @param username >> temp password storage
     */
    public User(String email, String username) {
        this.email = email;
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
