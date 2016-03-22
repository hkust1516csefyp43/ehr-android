package io.github.hkust1516csefyp43.ehr.view;

import java.util.List;

import io.github.hkust1516csefyp43.ehr.pojo.server_response.v2.BloodType;
import io.github.hkust1516csefyp43.ehr.pojo.server_response.v2.Clinic;
import io.github.hkust1516csefyp43.ehr.pojo.server_response.v2.Gender;
import io.github.hkust1516csefyp43.ehr.pojo.server_response.v2.Patient;
import io.github.hkust1516csefyp43.ehr.pojo.server_response.v2.Suitcase;
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
     * Get list of clinics without token >> clinic_id & english_name only
     * No param/header/query/body needed
     *
     * @return
     */
    @GET("v2/clinics")
    Call<List<Clinic>> getSimplifiedClinics();                              //

    /**
     * Get list of clinics
     *
     * @param token >> access token
     * @return
     */
    @GET("v2/clinics")
    Call<List<Clinic>> getClinics(
            @Header("token") String token,
            @Query("country_id") String countryId,
            @Query("is_active") Boolean isActive,
            @Query("english_name") String englishName,
            @Query("native_name") String nativeName,
            @Query("latitude") double latitude,
            @Query("longitude") double longitude,
            @Query("create_timestamp") String createTimestamp,
            @Query("remark") String remark,
            @Query("is_global") Boolean isGlobal,
            @Query("suitcase_id") String suitcaseId,
            @Query("offset") int offset,
            @Query("sort_by") String sortBy,
            @Query("limit") int limit
    );

    @GET("v2/clinics/{id}")
    Call<Clinic> getClinic(
            @Path("id") String clinicId
    );

    @GET("v2/genders")
    Call<List<Gender>> getGenders(
            @Header("token") String token
    );

    @GET("v2/suitcases")
    Call<List<Suitcase>> getSuitcases(
            @Header("token") String token
    );

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
