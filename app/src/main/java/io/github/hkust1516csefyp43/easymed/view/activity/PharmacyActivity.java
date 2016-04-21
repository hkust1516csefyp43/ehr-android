package io.github.hkust1516csefyp43.easymed.view.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;

import io.github.hkust1516csefyp43.easymed.R;

public class PharmacyActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_pharmacy);

    //get extra
    //use visit id to get consultation
    //use consultation id to get prescriptions
    //add prescription programmatically

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setTitle("last name first name");
    }

    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    if (fab != null) {
      fab.setImageDrawable(new IconicsDrawable(this).icon(GoogleMaterial.Icon.gmd_check).color(Color.WHITE).actionBar());
      //TODO click to confirm >> retrofit
      fab.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
              .setAction("Action", null).show();
        }
      });
    }
  }
}
