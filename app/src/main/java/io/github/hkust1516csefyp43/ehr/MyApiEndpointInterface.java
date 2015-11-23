package io.github.hkust1516csefyp43.ehr;

import java.util.List;

import io.github.hkust1516csefyp43.ehr.pojo.Chief_complain;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface MyApiEndpointInterface {
    // Request method and URL specified in the annotation
    // Callback for the parsed response is the last parameter

    @GET("/chief_complain/")
    Call<List<Chief_complain>> getChiefComplains(@Query("token") String token, @Query("diagnosis_id") String diagnosisId, @Query("name") String name, @Query("sort_by") String sortBy);
//    Call<Chief_complain> getChiefComplains(@Query("token") String token, @Query("diagnosis_id") String diagnosisId, @Query("name") String name, @Query("sort_by") String sortBy);

    @GET("/chief_complain/{id}")
    Call<Chief_complain> getChiefComplain(@Path("id") String id, @Query("token") String token);

//    @GET("/user/{username}")
//    Call<User> getUser(@Path("username") String username);
//
//    @GET("/group/{id}/users")
//    Call<List<User>> groupList(@Path("id") int groupId, @Query("sort") String sort);
//
//    @POST("/users/new")
//    Call<User> createUser(@Body User user);
}