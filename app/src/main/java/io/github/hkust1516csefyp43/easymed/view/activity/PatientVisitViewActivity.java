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
import android.widget.ImageView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.github.hkust1516csefyp43.easymed.R;
import io.github.hkust1516csefyp43.easymed.listener.OnFragmentInteractionListener;
import io.github.hkust1516csefyp43.easymed.pojo.server_response.Patient;
import io.github.hkust1516csefyp43.easymed.pojo.server_response.Visit;
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

  private Boolean fabOn = false;      //should each visitdetailfragment display edit button on the corner
  private Boolean isTriage = true;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    final Dialog dialog = new Dialog(this, R.style.AppTheme);
    dialog.setContentView(R.layout.dialog_loading);
    dialog.show();

    setContentView(R.layout.activity_patient_visit_view);

    viewPager = (ViewPager) findViewById(R.id.viewpager);
    tabLayout = (TabLayout) findViewById(R.id.tabLayout);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

    setSupportActionBar(toolbar);

    ImageView ivProfilePic = (ImageView) findViewById(R.id.profile_pic);

    thisPatient = (Patient) this.getIntent().getSerializableExtra(Const.BundleKey.READ_ONLY_PATIENT);
    fabOn = getIntent().getBooleanExtra(Const.BundleKey.ON_OR_OFF, false);                          //TODO What should the default be?
    isTriage = getIntent().getBooleanExtra(Const.BundleKey.IS_TRIAGE, true);
    //TODO get extra triage
    //TODO get extra consultation

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

    if (ivProfilePic != null && thisPatient != null) {
      ivProfilePic.setImageDrawable(TextDrawable.builder().buildRect(Util.getTextDrawableText(thisPatient), ColorGenerator.MATERIAL.getColor(thisPatient.getLastNameSpaceFirstName())));
    }

    //TODO /v2/visits/ token patient_id >> populate ui accordingly
    //>> get tcp of each visits >> the visit fragment (not create yet) will handle it

    if (thisPatient != null) {
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
      Call<List<Visit>> visitsCall = visitService.getVisits("1", null, thisPatient.getPatientId(), "create_timestamp");
      visitsCall.enqueue(new Callback<List<Visit>>() {
        @Override
        public void onResponse(Call<List<Visit>> call, Response<List<Visit>> response) {
          visits = response.body();
          Collections.reverse(visits);
          if (tabLayout != null && viewPager != null) {
            for (Visit v: visits) {
              Log.d(TAG, "visit: " + v.toString());
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
          if (position-1 < visits.size()) {               //-1 because of the Bio page
            if (thisPatient != null) {
              return VisitDetailFragment.newInstance(thisPatient, visits.get(position-1), fabOn, isTriage);
            }
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


}