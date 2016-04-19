package io.github.hkust1516csefyp43.easymed;

import java.util.List;

import io.github.hkust1516csefyp43.easymed.POJO.Notification;
import io.github.hkust1516csefyp43.easymed.POJO.Patient;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Header;

/**
 * Created by Louis on 20/4/16.
 */
public interface v2API {
  interface notification {
    @GET("notifications/") Call<List<Notification>> getMyNotifications(@Header("token") String token);
  }

  interface patients {
    @GET("patients/") Call<List<Patient>> getPatients(@Header("token") String token);
  }
}
