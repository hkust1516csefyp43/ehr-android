package io.github.hkust1516csefyp43.ehr.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import io.github.hkust1516csefyp43.ehr.R;

public class SettingsActivity extends AppCompatActivity {
    private Toolbar tb;
    private ActionBar ab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        final Activity a = this;
        tb = (Toolbar) findViewById(R.id.toolbar);
        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO For some can't get the back button to work
                a.finish();
            }
        });
        setSupportActionBar(tb);
        ab = getSupportActionBar();

        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowHomeEnabled(true);
        ab.setTitle("Settings");
    }
}
