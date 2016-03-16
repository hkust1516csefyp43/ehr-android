package io.github.hkust1516csefyp43.ehr.view;

import java.util.List;

import io.github.hkust1516csefyp43.ehr.pojo.server_response.v2.BloodType;
import io.github.hkust1516csefyp43.ehr.pojo.server_response.v2.Patient;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Louis on 15/3/16.
 */
public interface v2API {

    /**
     * TODO age, age_ot and age_yt
     */
    @GET("v2/patient/")
    Call<List<Patient>> getPatients(
            @Query("token") String token,                                       //TODO replace Query with Header later
            @Query("clinic_id") String client,
            @Query("next_station") String nextStation,
            @Query("gender_id") String gender,
            @Query("phone_number_country_code") String phoneCountryCode,        //phone number is missing?
            @Query("email") String email,
            @Query("first_name") String firstName,
            @Query("middle_name") String middleName,
            @Query("last_name") String lastName                                 //TODO add "name"
    );

    @GET("v2/blood_types/")
    Call<List<BloodType>> getBloodTypes(
            @Header("token") String token
    );

    @GET("v2/blood_types/{id}")
    Call<BloodType> getBloodType(
            @Header("token") String token,
            @Path("id") String bloodTypeId
    );

//    @PUT("v2/blood_types/{id}")       //TODO Call<?> what is the response?
//    @POST("v2/blood_types/")       //TODO Call<?> what is the response?

}
