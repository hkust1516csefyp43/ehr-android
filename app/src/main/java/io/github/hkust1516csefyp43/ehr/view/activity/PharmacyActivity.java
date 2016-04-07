package io.github.hkust1516csefyp43.ehr.view.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import io.github.hkust1516csefyp43.ehr.R;
import io.github.hkust1516csefyp43.ehr.listener.OnFragmentInteractionListener;

/**
 * TODO get Bundle (Patient, Consultation)
 */
public class PharmacyActivity extends AppCompatActivity implements OnFragmentInteractionListener {
  private Toolbar tb;
  private ActionBar ab;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_pharmacy);
    tb = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(tb);
    ab = getSupportActionBar();
    if (ab != null) {
      ab.setDisplayHomeAsUpEnabled(true);
      ab.setDisplayShowHomeEnabled(true);
      ab.setTitle("Pharmacy");
    }
  }

  @Override
  public void onFragmentInteraction(Uri uri) {

  }
}
