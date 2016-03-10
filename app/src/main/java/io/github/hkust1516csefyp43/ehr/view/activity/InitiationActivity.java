package io.github.hkust1516csefyp43.ehr.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;

import io.github.hkust1516csefyp43.ehr.R;

public class InitiationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initiation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView ivLocalHost = (ImageView) findViewById(R.id.ivLocalHost);
        ivLocalHost.setImageDrawable(new IconicsDrawable(this, GoogleMaterial.Icon.gmd_cloud_off).color(getResources().getColor(R.color.primary_text_color)).actionBar());
        ImageView ivCloudHost = (ImageView) findViewById(R.id.ivCloudHost);
        ivCloudHost.setImageDrawable(new IconicsDrawable(this, GoogleMaterial.Icon.gmd_cloud).color(getResources().getColor(R.color.primary_text_color)).actionBar());
        ImageView ivSSID = (ImageView) findViewById(R.id.ivSSID);
        ivSSID.setImageDrawable(new IconicsDrawable(this, GoogleMaterial.Icon.gmd_router).color(getResources().getColor(R.color.primary_text_color)).actionBar());

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

}
