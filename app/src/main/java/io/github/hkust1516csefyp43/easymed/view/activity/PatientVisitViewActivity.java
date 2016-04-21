package io.github.hkust1516csefyp43.easymed.view.activity;

import android.app.Dialog;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.github.hkust1516csefyp43.easymed.R;
import io.github.hkust1516csefyp43.easymed.listener.OnFragmentInteractionListener;
import io.github.hkust1516csefyp43.easymed.pojo.Patient;
import io.github.hkust1516csefyp43.easymed.pojo.Visit;
import io.github.hkust1516csefyp43.easymed.utility.Const;
import io.github.hkust1516csefyp43.easymed.utility.Util;
import io.github.hkust1516csefyp43.easymed.utility.v2API;
import io.github.hkust1516csefyp43.easymed.view.fragment.patient_visit_view.BioFragment;
import io.github.hkust1516csefyp43.easymed.view.fragment.patient_visit_view.VisitDetailFragment;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PatientVisitViewActivity extends AppCompatActivity implements OnFragmentInteractionListener{
  public final static String TAG = PatientVisitViewActivity.class.getSimpleName();

  private Patient thisPatient;
  private List<Visit> visits;

  private ViewPager viewPager;
  private TabLayout tabLayout;

  private Boolean fabOn = false;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_patient_visit_read_only);

    final Dialog dialog = new Dialog(this, R.style.AppTheme);
    dialog.setContentView(R.layout.dialog_loading);
    dialog.show();

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    thisPatient = (Patient) this.getIntent().getSerializableExtra(Const.BundleKey.READ_ONLY_PATIENT);
    fabOn = getIntent().getBooleanExtra(Const.BundleKey.ON_OR_OFF, false);                          //TODO What should the default be?
    //TODO get extra triage
    //TODO get extra consultation

    tabLayout = (TabLayout) findViewById(R.id.tabLayout);
    if (tabLayout != null) {
      tabLayout.addTab(tabLayout.newTab().setText("Bio"));
    }

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

    viewPager = (ViewPager) findViewById(R.id.viewpager);

    //TODO /v2/visits/ token patient_id >> populate ui accordingly
    //>> get tcp of each visits >> the visit fragment (not create yet) will handle it

    OkHttpClient.Builder ohc1 = new OkHttpClient.Builder();
    ohc1.readTimeout(1, TimeUnit.MINUTES);
    ohc1.connectTimeout(1, TimeUnit.MINUTES);
    Retrofit retrofit = new Retrofit
        .Builder()
        .baseUrl(Const.Database.CLOUD_API_BASE_URL_121_dev)
        .addConverterFactory(GsonConverterFactory.create(Const.GsonParserThatWorksWithPGTimestamp))
        .client(ohc1.build())
        .build();
    v2API.visits visitService = retrofit.create(v2API.visits.class);
    Call<List<Visit>> visitsCall = visitService.getVisits("1", null, thisPatient.getPatientId());
    visitsCall.enqueue(new Callback<List<Visit>>() {
      @Override
      public void onResponse(Call<List<Visit>> call, Response<List<Visit>> response) {

        visits = response.body();
        if (tabLayout != null && viewPager != null) {
          for (Visit v: visits) {
            tabLayout.addTab(tabLayout.newTab().setText(Util.dateInStringOrToday(v.getCreateTimestamp())));
          }
          viewPager.setAdapter(new patientHistory(getSupportFragmentManager()));
          viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
          tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
              viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
              viewPager.setCurrentItem(tab.getPosition());
            }
          });
        }

        new Handler().postDelayed(new Runnable() {      //Dismiss dialog 1s later (avoid the dialog flashing >> weird)
          @Override
          public void run() {
            dialog.dismiss();
            //TODO dismiss animation
          }
        }, 1000);
      }

      @Override
      public void onFailure(Call<List<Visit>> call, Throwable t) {

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

  @Override
  public void onFragmentInteraction(Uri uri) {

  }

  private class patientHistory extends FragmentStatePagerAdapter {

    public patientHistory(FragmentManager fm) {
      super(fm);
    }

    @Override
    public Fragment getItem(int position) {
      if (position == 0) {
        return BioFragment.newInstance(thisPatient);
      } else {
        if (visits != null) {
          if (position-1 < visits.size()) {                                         //-1 because of the Bio page
            return VisitDetailFragment.newInstance(visits.get(position-1), fabOn);
          }
        }
      }
      if (visits != null) {
        Log.d(TAG, "Something is wrong with this: visits.size(): " + visits.size() + "; position: " + position + "; the whole visits: " + visits.toString());
      }
      return new VisitDetailFragment();
    }

    @Override
    public int getCount() {
      return visits.size()+1;
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

//{
//    "consultation_id": "MSzaXkZFhT5G6B58",
//    "end_timestamp": "2016-03-07T15:02:25.755Z",
//    "pe_cardio": null,
//    "pe_ent": null,
//    "pe_gastro": null,
//    "pe_general": null,
//    "pe_genital": null,
//    "pe_other": null,
//    "pe_respiratory": null,
//    "pe_skin": null,
//    "preg_breast_feeding": null,
//    "preg_contraceptive": null,
//    "preg_curr_preg": null,
//    "preg_gestration": null,
//    "preg_lmp": null,
//    "preg_num_abortion": null,
//    "preg_num_live_birth": null,
//    "preg_num_miscarriage": null,
//    "preg_num_preg": null,
//    "preg_num_still_birth": null,
//    "preg_remark": null,
//    "remark": null,
//    "rf_alertness": null,
//    "rf_breathing": null,
//    "rf_circulation": null,
//    "rf_defg": null,
//    "rf_dehydration": null,
//    "ros_cardio": null,
//    "ros_ent": null,
//    "ros_ga": null,
//    "ros_gastro": null,
//    "ros_genital": null,
//    "ros_other": null,
//    "ros_respi": null,
//    "ros_skin": null,
//    "start_timestamp": "2016-03-07T15:02:25.755Z",
//    "user_id": "acwaeoiwlin",
//    "visit_id": "41"
//    }
