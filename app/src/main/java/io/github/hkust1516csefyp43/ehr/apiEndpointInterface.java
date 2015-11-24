package io.github.hkust1516csefyp43.ehr;

import java.util.List;

import io.github.hkust1516csefyp43.ehr.pojo.Chief_complain;
import io.github.hkust1516csefyp43.ehr.pojo.Patient;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface apiEndpointInterface {
    // Request method and URL specified in the annotation
    // Callback for the parsed response is the last parameter

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

//    @GET("/user/{username}")
//    Call<User> getUser(@Path("username") String username);
//
//    @GET("/group/{id}/users")
//    Call<List<User>> groupList(@Path("id") int groupId, @Query("sort") String sort);
//
//    @POST("/users/new")
//    Call<User> createUser(@Body User user);
}