package io.github.hkust1516csefyp43.ehr;

import java.util.List;

import io.github.hkust1516csefyp43.ehr.pojo.BlockedDevice;
import io.github.hkust1516csefyp43.ehr.pojo.Chief_complain;
import io.github.hkust1516csefyp43.ehr.pojo.Consultation;
import io.github.hkust1516csefyp43.ehr.pojo.Inventory;
import io.github.hkust1516csefyp43.ehr.pojo.Patient;
import io.github.hkust1516csefyp43.ehr.pojo.Pharmacy;
import io.github.hkust1516csefyp43.ehr.pojo.Role;
import io.github.hkust1516csefyp43.ehr.pojo.Status;
import io.github.hkust1516csefyp43.ehr.pojo.Token;
import io.github.hkust1516csefyp43.ehr.pojo.Triage;
import io.github.hkust1516csefyp43.ehr.pojo.User;
import io.github.hkust1516csefyp43.ehr.pojo.Visit;
import retrofit.Call;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * TODO define each api calls
 */
public interface apiEndpointInterface {
    // Request method and URL specified in the annotation
    // Callback for the parsed response is the last parameter

//------------------------------------------ users ------------------------------------------

    /**
     * @return
     */
    @GET("v1/user/")
    Call<User> login();

    @GET("v1/user/")
    Call<User> refreshAccessToken();

    @POST("v1/user/")
    Call<User> signup();

    @PUT("v1/user/{id}")
    Call<User> updateInfo();

    @DELETE("v1/users/logout/")
    Call<User> logout();

    @GET("v1/users/token/")
    Call<List<Token>> getTokens();

    @DELETE("v1/users/token/{id}")
    Call<Token> revokeToken();

    @GET("v1/users/block_device/")
    Call<List<BlockedDevice>> getBlockedDevices();

    @GET("v1/users/role/")
    Call<List<Role>> getRoles();

    @GET("v1/users/role/{id}")
    Call<Role> getRole();

//------------------------------------------ patients ------------------------------------------

    @GET("v1/patient/")
    Call<List<Patient>> getPatients(
            @Query("token") String token,
            @Query("next_station") Integer nextStation,
            @Query("age") Integer age,
            @Query("age_ot") Integer ageOlderThen,
            @Query("age_yt") Integer ageYoungerThan,
            @Query("slum_id") String slumId,
            @Query("gender") String gender,
            @Query("blood_type") String bloodType,
            @Query("country_id") String countryId,
            @Query("email") String email,
            @Query("related_to_id") String relatedToId,
            @Query("phone_country_id") String phoneCountryId,
            @Query("phone_number") String phoneNumber,
            @Query("limit") Integer limit,
            @Query("offset") Integer offset,
            @Query("sort_by") String sortBy);

    @GET("v1/patient/{id}")
    Call<Patient> getPatient();

    @POST("v1/patients/")
    Call<Patient> addPatient();

    @PUT("v1/patients/{id}")
    Call<Patient> updatePatient();

    @DELETE("v1/patients/{id}")
    Call<Patient> deletePatient();

//------------------------------------------ visits ------------------------------------------

    @GET("v1/visits/")
    Call<List<Visit>> getVisits();

    @GET("v1/visits/{id}")
    Call<Visit> getVisit();

    @POST("v1/visits/")
    Call<Visit> createVisit();

    @PUT("v1/visits/{id}")
    Call<Visit> updateVisit();

    @DELETE("v1/visits/{id}")
    Call<Visit> deleteVisit();

    @POST("v1/visits/triages/{id}")
    Call<Triage> addTriage();

    @POST("v1/visits/consultations/{id}")
    Call<Consultation> addConsultation();

    @POST("v1/visits/pharmacies/{id}")
    Call<Pharmacy> addPharmacy();

//------------------------------------------ inventories ------------------------------------------

    @GET("v1/inventories/")
    Call<List<Inventory>> getInventories();

    @GET("v1/inventories/{id}")
    Call<Inventory> getInventory();

    @POST("v1/inventories/")
    Call<Inventory> addInventory();

    @PUT("v1/inventories/{id}")
    Call<Inventory> updateInventory();

    @DELETE("v1/inventories/{id}")
    Call<Inventory> deleteInventory();

    //TODO inventory update

//------------------------------------------ diagnosis ------------------------------------------

//------------------------------------------ chief complains ------------------------------------------

    @GET("v1/chief_complain/")
    Call<List<Chief_complain>> getChiefComplains(
            @Query("token") String token,
            @Query("diagnosis_id") String diagnosisId,
            @Query("name") String name,
            @Query("sort_by") String sortBy);

    @GET("v1/chief_complain/{id}")
    Call<Chief_complain> getChiefComplain(
            @Path("id") String id,
            @Query("token") String token);


    @GET("v1/static/status")
    Call<Status> getStatus();


//------------------------------------------ stock samples ------------------------------------------


//    @GET("/user/{username}")
//    Call<User> getUser(@Path("username") String username);
//
//    @GET("/group/{id}/users")
//    Call<List<User>> groupList(@Path("id") int groupId, @Query("sort") String sort);
//
//    @POST("/users/new")
//    Call<User> createUser(@Body User user);
}