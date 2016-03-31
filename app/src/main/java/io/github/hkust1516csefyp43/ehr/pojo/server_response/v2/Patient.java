package io.github.hkust1516csefyp43.ehr.pojo.server_response.v2;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Louis on 15/3/16.
 */
public class Patient implements Serializable {

    @SerializedName("address")
    String address;
    @SerializedName("birth_date")
    Integer birthDate;
    @SerializedName("birth_month")
    Integer birthMonth;
    @SerializedName("birth_year")
    Integer birthYear;
    @SerializedName("blood_type_id")
    String bloodTypeId;
    @SerializedName("clinic_id")
    String clinicId;
    @SerializedName("create_timestamp")
    Date createTimeStamp;
    @SerializedName("email")
    String email;
    @SerializedName("first_name")
    String firstName;
    @SerializedName("gender_id")
    String genderId;
    @SerializedName("honorific")
    String honorific;
    @SerializedName("image_id")
    String imageId;
    @SerializedName("last_name")
    String lastName;
    @SerializedName("middle_name")
    String middleName;
    @SerializedName("native_name")
    String nativeName;
    @SerializedName("patient_id")
    String patientId;
    @SerializedName("phone_number")
    String phoneNumber;
    @SerializedName("phone_number_country_code")
    String phoneNumberCountryCode;
    //extra stuff from v2.visits
    @SerializedName("visit_id")
    String visitId;
    @SerializedName("tag")
    Integer tag;
    @SerializedName("next_station")
    Integer nextStation;

    public Patient(String address, Integer birthDate, Integer birthMonth, Integer birthYear, String bloodTypeId, String clinicId, Date createTimeStamp, String email, String firstName, String genderId, String honorific, String imageId, String lastName, String middleName, String nativeName, String patientId, String phoneNumber, String phoneNumberCountryCode) {
        this.address = address;
        this.birthDate = birthDate;
        this.birthMonth = birthMonth;
        this.birthYear = birthYear;
        this.bloodTypeId = bloodTypeId;
        this.clinicId = clinicId;
        this.createTimeStamp = createTimeStamp;
        this.email = email;
        this.firstName = firstName;
        this.genderId = genderId;
        this.honorific = honorific;
        this.imageId = imageId;
        this.lastName = lastName;
        this.middleName = middleName;
        this.nativeName = nativeName;
        this.patientId = patientId;
        this.phoneNumber = phoneNumber;
        this.phoneNumberCountryCode = phoneNumberCountryCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getBirthDate() {
        return birthDate;
    }

    public Integer getBirthMonth() {
        return birthMonth;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthDate(Integer birthDate) {
        this.birthDate = birthDate;
    }

    public void setBirthMonth(Integer birthMonth) {
        this.birthMonth = birthMonth;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public String getBloodTypeId() {
        return bloodTypeId;
    }

    public void setBloodTypeId(String bloodTypeId) {
        this.bloodTypeId = bloodTypeId;
    }

    public String getClinicId() {
        return clinicId;
    }

    public void setClinicId(String clinicId) {
        this.clinicId = clinicId;
    }

    public Date getCreateTimeStamp() {
        return createTimeStamp;
    }

    public void setCreateTimeStamp(Date createTimeStamp) {
        this.createTimeStamp = createTimeStamp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getGenderId() {
        return genderId;
    }

    public void setGenderId(String genderId) {
        this.genderId = genderId;
    }

    public String getHonorific() {
        return honorific;
    }

    public void setHonorific(String honorific) {
        this.honorific = honorific;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }

    public Integer getNextStation() {
        return nextStation;
    }

    public void setNextStation(Integer nextStation) {
        this.nextStation = nextStation;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumberCountryCode() {
        return phoneNumberCountryCode;
    }

    public void setPhoneNumberCountryCode(String phoneNumberCountryCode) {
        this.phoneNumberCountryCode = phoneNumberCountryCode;
    }

    public Integer getTag() {
        return tag;
    }

    public void setTag(Integer tag) {
        this.tag = tag;
    }

    public String getVisitId() {
        return visitId;
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "address='" + address + '\'' +
                ", birthDate=" + birthDate +
                ", birthMonth=" + birthMonth +
                ", birthYear=" + birthYear +
                ", bloodTypeId='" + bloodTypeId + '\'' +
                ", clinicId='" + clinicId + '\'' +
                ", createTimeStamp=" + createTimeStamp +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", genderId='" + genderId + '\'' +
                ", honorific='" + honorific + '\'' +
                ", imageId='" + imageId + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", nativeName='" + nativeName + '\'' +
                ", patientId='" + patientId + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", phoneNumberCountryCode='" + phoneNumberCountryCode + '\'' +
                ", visitId='" + visitId + '\'' +
                ", tag=" + tag +
                ", nextStation=" + nextStation +
                '}';
    }
}
