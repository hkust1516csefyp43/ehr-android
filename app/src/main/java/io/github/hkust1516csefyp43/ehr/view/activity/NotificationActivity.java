package io.github.hkust1516csefyp43.ehr.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import io.github.hkust1516csefyp43.ehr.R;

public class NotificationActivity extends AppCompatActivity {
    LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        ll = (LinearLayout) findViewById(R.id.llNotificationList);
        //TODO get notification from cache
        //Add them in a for loop
    }
}
