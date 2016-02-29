package io.github.hkust1516csefyp43.ehr.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import io.github.hkust1516csefyp43.ehr.BuildConfig;
import io.github.hkust1516csefyp43.ehr.Connectivity;
import io.github.hkust1516csefyp43.ehr.R;
import io.github.hkust1516csefyp43.ehr.pojo.User;
import io.github.hkust1516csefyp43.ehr.value.Cache;
import io.github.hkust1516csefyp43.ehr.value.Const;

/**
 * TODO Check User disk cache
 * TODO check variant >> free >> no host cache >> need to input
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SharedPreferences p = getSharedPreferences(Const.KEY_SHARE_PREFERENCES, MODE_PRIVATE);
        String configString = p.getString(Const.KEY_VIRGIN, null);
        if (configString == null) {                                                                 //first instance
            if (BuildConfig.FLAVOR.compareTo(Const.FLAVOR_ONE_2_ONE_CAMBODIA) == 0) {
                //TODO write those pre-define stuff
            } else {
                // TODO ask user for input and save to SP
            }
        } else {
            //TODO start working

        }







        //TODO check variant
        if (BuildConfig.FLAVOR == Const.FLAVOR_FREE) {

        } else if (BuildConfig.FLAVOR == Const.FLAVOR_ONE_2_ONE_CAMBODIA) {

        } else {
            //TODO error
        }

        //check network status
        if (Connectivity.isConnected(getApplicationContext())) {
            //TODO check ssid
            if (Connectivity.isConnectedWifi(getApplicationContext())) {
                Log.d("qqq51", Connectivity.ConnectedWifiSSID(getApplicationContext()));
            } else if (Connectivity.isConnectedMobile(getApplicationContext())){
                Log.d("qqq52", "not wifi");
                //TODO check if
            } else {

            }
        } else {
            //try cloud
        }





        User u = Cache.getUser(getApplicationContext());
        if (u == null) {
            //go to login screen
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent mainIntent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(mainIntent);
                    finish();
                    overridePendingTransition(R.anim.activityfadein, R.anim.splashfadeout);
                }
            }, Const.SPLASH_DISPLAY_LENGTH);
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent mainIntent = new Intent(SplashActivity.this, TwoRecyclerViewPatientsActivity.class);
                    startActivity(mainIntent);
                    finish();
                    overridePendingTransition(R.anim.activityfadein, R.anim.splashfadeout);
                }
            }, Const.SPLASH_DISPLAY_LENGTH);
        }
    }
}
