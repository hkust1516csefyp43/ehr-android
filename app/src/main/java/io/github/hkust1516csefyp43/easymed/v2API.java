package io.github.hkust1516csefyp43.easymed;

import java.util.List;

import io.github.hkust1516csefyp43.easymed.POJO.Notification;
import io.github.hkust1516csefyp43.easymed.POJO.Patient;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Query;

/**
 * Created by Louis on 20/4/16.
 */
public interface v2API {
  interface notification {
    @GET("notifications/") Call<List<Notification>> getMyNotifications(@Header("token") String token);
  }

  interface patients {
    @GET("patients/")
    Call<List<Patient>> getPatients(
        @Header("token") String token,
        @Query("clinic_id") String clinic,
        @Query("next_station") String nextStation,
        @Query("gender_id") String gender,
        @Query("phone_number_country_code") String phoneCountryCode,        //phone number is missing?
        @Query("email") String email,
        @Query("first_name") String firstName,
        @Query("middle_name") String middleName,
        @Query("last_name") String lastName,
        @Query("name") String name,
        @Query("visit_date") String visitDate,
        @Query("sory_by") String soryBy
    );
  }
}
