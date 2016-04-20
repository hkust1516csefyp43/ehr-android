package io.github.hkust1516csefyp43.easymed;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.squareup.okhttp.OkHttpClient;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.github.hkust1516csefyp43.easymed.POJO.Patient;
import io.github.hkust1516csefyp43.easymed.POJO.Visit;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class PatientVisitReadOnlyActivity extends AppCompatActivity {
  public final static String TAG = PatientVisitReadOnlyActivity.class.getSimpleName();

  private Patient thisPatient;
  private List<Visit> visits;

  private ViewPager viewPager;
  private TabLayout tabLayout;

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
    tabLayout = (TabLayout) findViewById(R.id.tabLayout);
    tabLayout.addTab(tabLayout.newTab().setText("Bio"));

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

    viewPager = (ViewPager) findViewById(R.id.viewpager);

    //TODO /v2/visits/ token patient_id >> populate ui accordingly
    //>> get tcp of each visits >> the visit fragment (not create yet) will handle it

    OkHttpClient ohc1 = new OkHttpClient();
    ohc1.setReadTimeout(1, TimeUnit.MINUTES);
    ohc1.setConnectTimeout(1, TimeUnit.MINUTES);
    Retrofit retrofit = new Retrofit
        .Builder()
        .baseUrl(Const.Database.CLOUD_API_BASE_URL_121_dev)
        .addConverterFactory(GsonConverterFactory.create(Const.GsonParserThatWorksWithPGTimestamp))
        .client(ohc1)
        .build();
    v2API.visits visitService = retrofit.create(v2API.visits.class);
    Call<List<Visit>> visitsCall = visitService.getVisits("1", null, thisPatient.getPatientId());
    visitsCall.enqueue(new Callback<List<Visit>>() {
      @Override
      public void onResponse(Response<List<Visit>> response, Retrofit retrofit) {
        visits = response.body();
        if (tabLayout != null && viewPager != null) {
          for (Visit v: visits) {
            tabLayout.addTab(tabLayout.newTab().setText(Util.dateInString(v.getCreateTimestamp())));
          }

        }
      }

      @Override
      public void onFailure(Throwable t) {

      }
    });
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

  private class patientHistory extends FragmentStatePagerAdapter {

    public patientHistory(FragmentManager fm) {
      super(fm);
    }

    @Override
    public Fragment getItem(int position) {
      return null;
    }

    @Override
    public int getCount() {
      return 0;
    }
  }

//  /**
//   * Inflate the blank section of the page with content
//   * TODO make it good looking (material design stuff: spacing, sizes of everything, animation, etc)
//   */
////  private void fillTheWholePage() {
////    LinearLayout llPatientInfo = (LinearLayout) findViewById(R.id.llPatientInfo);
////    fillPersonalData(llPatientInfo);
////  }
//
//  private void fillPersonalData(LinearLayout l) {
//    if (l != null) {
//      TextView tv = new TextView(this);
//      StringBuilder sb = new StringBuilder();
//      if (thisPatient.getHonorific() != null) {
//        sb.append(thisPatient.getHonorific()).append(" ");
//      }
//      if (thisPatient.getLastName() != null) {
//        sb.append(thisPatient.getLastName()).append(" ");
//      }
//      if (thisPatient.getMiddleName() != null) {
//        sb.append(thisPatient.getMiddleName()).append(" ");
//      }
//      sb.append(thisPatient.getFirstName());
//      tv.setText(sb.toString());
//
//      TextInputLayout textInputLayout = new TextInputLayout(this);
//      textInputLayout.addView(tv);
//
//      l.addView(textInputLayout);
//      if (thisPatient.getNativeName() != null) {
//        TextView tv2 = new TextView(this);
//        tv2.setText(thisPatient.getNativeName());
//        TextInputLayout textInputLayout2 = new TextInputLayout(this);
//        textInputLayout2.addView(tv2);
//        l.addView(textInputLayout2);
//      }
//    }
//    if (thisPatient.getBirthYear() != null && thisPatient.getBirthMonth() != null && thisPatient.getBirthDate() != null) {
//      //TODO user can customize display format (order and symbol)
//      String birthday = "" + thisPatient.getBirthYear() + "/" + thisPatient.getBirthMonth() + "/" + thisPatient.getBirthDate() + " (" + Util.birthdayToAgeString(thisPatient.getBirthYear(), thisPatient.getBirthMonth(), thisPatient.getBirthDate()) + ")";
//      TextView tv1 = new TextView(this);
//      tv1.setText(birthday);
//      l.addView(tv1);
//    }
//    //phone
//    //address
//    //email address
//    //gender
//    //blood type
//  }

}
