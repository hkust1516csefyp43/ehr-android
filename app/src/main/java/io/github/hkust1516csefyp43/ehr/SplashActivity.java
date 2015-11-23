package io.github.hkust1516csefyp43.ehr;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import io.github.hkust1516csefyp43.ehr.pojo.Chief_complain;
import io.github.hkust1516csefyp43.ehr.value.Const;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import roboguice.inject.ContentView;

/**
 * TODO Check User disk cache
 */
@ContentView(R.layout.activity_splash)
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Const.API_HEROKU_TEST).addConverterFactory(GsonConverterFactory.create(gson)).build();

        MyApiEndpointInterface apiService = retrofit.create(MyApiEndpointInterface.class);

        Call<List<Chief_complain>> call = apiService.getChiefComplains("hihi", null, null, null);
        call.enqueue(new Callback<List<Chief_complain>>() {
            @Override
            public void onResponse(Response<List<Chief_complain>> response, Retrofit retrofit) {
                Log.d("qqq: ", response.toString());
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(mainIntent);
                finish();

                overridePendingTransition(R.anim.activityfadein, R.anim.splashfadeout);
            }
        }, Const.SPLASH_DISPLAY_LENGTH);
    }
}
