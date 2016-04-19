package io.github.hkust1516csefyp43.easymed;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Created by Louis on 20/4/16.
 */
public interface v2API {
  interface Notification {
    @GET("notifications/") Call<List<Notification>> getMyNotifications(@Header("token") String token);
  }
}
