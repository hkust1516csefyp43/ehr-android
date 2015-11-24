package io.github.hkust1516csefyp43.ehr.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * TODO this need to look exactly like the JSON Object
 * Created by Louis on 6/10/15.
 */
public class Patient {
    @SerializedName("patient_id")
    private String id;
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
    private Object dateOfBirth;     //TODO Check how to format date http://stackoverflow.com/questions/18473011/retrofit-gson-serialize-date-from-json-string-into-java-util-date
    @SerializedName("gender")
    private String gender;
    @SerializedName("photo")
    private Object photo;           //TODO photo
    @SerializedName("slum_id")
    private String SlumId;
    @SerializedName("blood_type")
    private String bloodType;
    @SerializedName("create_timestamp")
    private Object createTimestamp;
    @SerializedName("last_seen")
    private Object lastSeen;
    @SerializedName("next_station")
    private int nextStation;
    @SerializedName("email")
    private String email;

    public Patient(String id, String honorific, String firstName, String middleName, String lastName, int phoneCountryId, String phoneNumber, String address, Object dateOfBirth, String gender, Object photo, String slumId, String bloodType, Object createTimestamp, Object lastSeen, int nextStation, String email) {

        this.id = id;
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
        this.SlumId = slumId;
        this.bloodType = bloodType;
        this.createTimestamp = createTimestamp;
        this.lastSeen = lastSeen;
        this.nextStation = nextStation;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id='" + id + '\'' +
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
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Object getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(Object lastSeen) {
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
}
