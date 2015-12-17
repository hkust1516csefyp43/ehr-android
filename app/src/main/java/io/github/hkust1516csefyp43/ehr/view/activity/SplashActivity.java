package io.github.hkust1516csefyp43.ehr.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import io.github.hkust1516csefyp43.ehr.R;
import io.github.hkust1516csefyp43.ehr.value.Const;

/**
 * TODO Check User disk cache
 */
//@ContentView(R.layout.activity_splash)
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
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
