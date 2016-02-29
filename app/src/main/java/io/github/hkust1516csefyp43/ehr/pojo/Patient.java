package io.github.hkust1516csefyp43.ehr.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

/**
 * TODO this need to look exactly like the JSON Object
 * Created by Louis on 6/10/15.
 */
public class Patient implements Serializable{
    @SerializedName("patient_id")
    private String patient_id;
    @SerializedName("honorific")
    private String honorific;
    @SerializedName("first_name")
    private String firstName;
    @SerializedName("middle_name")
    private String middleName;
    @SerializedName("last_name")
    private String lastName;
    @SerializedName("phone_country_id")
    private int phoneCountryId;
    @SerializedName("phone_number")
    private String phoneNumber;
    @SerializedName("address")
    private String address;
    @SerializedName("date_of_birth")
    private Object dateOfBirth;
    @SerializedName("gender")
    private String gender;
    @SerializedName("photo")
    private Object photo;
    @SerializedName("slum_id")
    private String SlumId;
    @SerializedName("blood_type")
    private String bloodType;
    @SerializedName("create_timestamp")
    private Object createTimestamp;
    @SerializedName("last_seen")
    private Date lastSeen;
    @SerializedName("next_station")
    private int nextStation;
    @SerializedName("email")
    private String email;
    @SerializedName("photo_url")
    private String profilePictureUrl;

    public Patient(String patient_id, String honorific, String firstName, String middleName, String lastName, int phoneCountryId, String phoneNumber, String address, Object dateOfBirth, String gender, Object photo, String slumId, String bloodType, Object createTimestamp, Date lastSeen, int nextStation, String email, String profilePictureUrl) {
        this.patient_id = patient_id;
        this.honorific = honorific;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.phoneCountryId = phoneCountryId;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.photo = photo;
        SlumId = slumId;
        this.bloodType = bloodType;
        this.createTimestamp = createTimestamp;
        this.lastSeen = lastSeen;
        this.nextStation = nextStation;
        this.email = email;
        this.profilePictureUrl = profilePictureUrl;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public String getPatientId() {
        return patient_id;
    }

    public void setId(String id) {
        this.patient_id = id;
    }

    public String getHonorific() {
        return honorific;
    }

    public void setHonorific(String honorific) {
        this.honorific = honorific;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getPhoneCountryId() {
        return phoneCountryId;
    }

    public void setPhoneCountryId(int phoneCountryId) {
        this.phoneCountryId = phoneCountryId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Object getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Object dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Object getPhoto() {
        return photo;
    }

    public void setPhoto(Object photo) {
        this.photo = photo;
    }

    public String getSlumId() {
        return SlumId;
    }

    public void setSlumId(String slumId) {
        SlumId = slumId;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public Object getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Object createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public Date getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(Date lastSeen) {
        this.lastSeen = lastSeen;
    }

    public int getNextStation() {
        return nextStation;
    }

    public void setNextStation(int nextStation) {
        this.nextStation = nextStation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patient_id='" + patient_id + '\'' +
                ", honorific='" + honorific + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneCountryId=" + phoneCountryId +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender='" + gender + '\'' +
                ", photo=" + photo +
                ", SlumId='" + SlumId + '\'' +
                ", bloodType='" + bloodType + '\'' +
                ", createTimestamp=" + createTimestamp +
                ", lastSeen=" + lastSeen +
                ", nextStation=" + nextStation +
                ", email='" + email + '\'' +
                ", profilePictureUrl='" + profilePictureUrl + '\'' +
                '}';
    }
}
