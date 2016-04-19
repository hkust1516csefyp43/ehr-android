package io.github.hkust1516csefyp43.easymed;

import java.util.List;

import io.github.hkust1516csefyp43.easymed.POJO.Notification;
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
}
