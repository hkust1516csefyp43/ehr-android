package io.github.hkust1516csefyp43.easymed;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;

import io.github.hkust1516csefyp43.easymed.POJO.Patient;

public class PatientVisitReadOnlyActivity extends AppCompatActivity {
  Patient thisPatient;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_patient_visit_read_only);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    thisPatient = (Patient) this.getIntent().getSerializableExtra(Const.BundleKey.READ_ONLY_PATIENT);
    //TODO get extra triage
    //TODO get extra consultation

    //TODO experiment: tablayout to show history
    TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
    tabLayout.addTab(tabLayout.newTab().setText("1"));
    tabLayout.addTab(tabLayout.newTab().setText("2"));
    tabLayout.addTab(tabLayout.newTab().setText("3"));

    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
      actionBar.setDisplayShowHomeEnabled(true);
      if (thisPatient != null) {
        StringBuilder s = new StringBuilder();
        if (thisPatient.getTag() != null) {
          s.append(thisPatient.getTag().toString() + ". ");
        }
        s.append(thisPatient.getLastNameSpaceFirstName());
        actionBar.setTitle(s.toString());
      }
    }

    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    if (fab != null) {
      fab.setImageDrawable(new IconicsDrawable(this).icon(GoogleMaterial.Icon.gmd_edit).actionBar().color(Color.WHITE));
      fab.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          Intent intent = new Intent(getBaseContext(), PatientVisitEditActivity.class);
          intent.putExtra(Const.BundleKey.EDIT_PATIENT, thisPatient);
          startActivity(intent);
        }
      });
    }

    if (thisPatient != null) {
      fillTheWholePage();
    }
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        // app icon in action bar clicked; goto parent activity.
        this.finish();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  /**
   * Inflate the blank section of the page with content
   * TODO make it good looking (material design stuff: spacing, sizes of everything, animation, etc)
   */
  private void fillTheWholePage() {
    LinearLayout llPatientInfo = (LinearLayout) findViewById(R.id.llPatientInfo);
    fillPersonalData(llPatientInfo);
  }

  private void fillPersonalData(LinearLayout l) {
    if (l != null) {
      TextView tv = new TextView(this);
      StringBuilder sb = new StringBuilder();
      if (thisPatient.getHonorific() != null) {
        sb.append(thisPatient.getHonorific()).append(" ");
      }
      if (thisPatient.getLastName() != null) {
        sb.append(thisPatient.getLastName()).append(" ");
      }
      if (thisPatient.getMiddleName() != null) {
        sb.append(thisPatient.getMiddleName()).append(" ");
      }
      sb.append(thisPatient.getFirstName());
      tv.setText(sb.toString());

      TextInputLayout textInputLayout = new TextInputLayout(this);
      textInputLayout.addView(tv);

      l.addView(textInputLayout);
      if (thisPatient.getNativeName() != null) {
        TextView tv2 = new TextView(this);
        tv2.setText(thisPatient.getNativeName());
        TextInputLayout textInputLayout2 = new TextInputLayout(this);
        textInputLayout2.addView(tv2);
        l.addView(textInputLayout2);
      }
    }
    if (thisPatient.getBirthYear() != null && thisPatient.getBirthMonth() != null && thisPatient.getBirthDate() != null) {
      //TODO user can customize display format (order and symbol)
      String birthday = "" + thisPatient.getBirthYear() + "/" + thisPatient.getBirthMonth() + "/" + thisPatient.getBirthDate() + " (" + Util.birthdayToAgeString(thisPatient.getBirthYear(), thisPatient.getBirthMonth(), thisPatient.getBirthDate()) + ")";
      TextView tv1 = new TextView(this);
      tv1.setText(birthday);
      l.addView(tv1);
    }
    //phone
    //address
    //email address
    //gender
    //blood type
  }

}
