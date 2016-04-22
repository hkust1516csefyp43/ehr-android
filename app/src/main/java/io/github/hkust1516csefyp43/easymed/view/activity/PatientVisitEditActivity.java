package io.github.hkust1516csefyp43.easymed.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;

import java.io.Serializable;
import java.util.ArrayList;

import io.github.hkust1516csefyp43.easymed.R;
import io.github.hkust1516csefyp43.easymed.listener.OnFragmentInteractionListener;
import io.github.hkust1516csefyp43.easymed.pojo.server_response.Consultation;
import io.github.hkust1516csefyp43.easymed.pojo.server_response.Patient;
import io.github.hkust1516csefyp43.easymed.pojo.server_response.Triage;
import io.github.hkust1516csefyp43.easymed.pojo.server_response.Visit;
import io.github.hkust1516csefyp43.easymed.utility.Const;
import io.github.hkust1516csefyp43.easymed.view.fragment.patient_visit_edit.ChiefComplaintFragment;
import io.github.hkust1516csefyp43.easymed.view.fragment.patient_visit_edit.PersonalDataFragment;
import io.github.hkust1516csefyp43.easymed.view.fragment.patient_visit_edit.PregnancyFragment;
import io.github.hkust1516csefyp43.easymed.view.fragment.patient_visit_edit.RemarkFragment;
import io.github.hkust1516csefyp43.easymed.view.fragment.patient_visit_edit.VitalSignFragment;

public class PatientVisitEditActivity extends AppCompatActivity implements OnFragmentInteractionListener{

  private PersonalDataFragment personalDataFragment;
  private VitalSignFragment vitalSignFragment;
  private ChiefComplaintFragment chiefComplaintFragment;
  private RemarkFragment remarkFragment;
  private PregnancyFragment pregnancyFragment;

  private Patient thisPatient;
  private Visit thisVisit;
  private Triage thisTriage;
  private Consultation thisConsultation;
  private boolean isTriage = true;

  private ArrayList<String> tabs = new ArrayList<>();

  private TabLayout tabLayout;
  private ViewPager viewPager;
  private ActionBar supportActionBar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_patient_visit_edit);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    tabLayout = (TabLayout) findViewById(R.id.tabLayout);
    viewPager = (ViewPager) findViewById(R.id.viewPager);

    //get extra
    Intent intent = getIntent();
    if (intent != null) {
      Serializable serializable;
      //isTriage
      isTriage = intent.getBooleanExtra(Const.BundleKey.IS_TRIAGE, true);       //TODO what should the default be?
      //patient
      serializable = intent.getSerializableExtra(Const.BundleKey.EDIT_PATIENT);
      if (serializable instanceof Patient) {
        thisPatient = (Patient) serializable;
      }
      //visit
      serializable = intent.getSerializableExtra(Const.BundleKey.WHOLE_VISIT);
      if (serializable instanceof Visit) {
        thisVisit = (Visit) serializable;
      }
      //triage
      serializable = intent.getSerializableExtra(Const.BundleKey.WHOLE_TRIAGE);
      if (serializable instanceof Triage) {
        thisTriage = (Triage) serializable;
      }
      //consultation
      serializable = intent.getSerializableExtra(Const.BundleKey.WHOLE_CONSULTATION);
      if (serializable instanceof Consultation) {
        thisConsultation = (Consultation) serializable;
      }
    }

    if (tabLayout != null && viewPager != null) {

      //set tablayout pages
      //triage: personal data, vital signs, chief complain, triage remark
      tabs.add("Personal Data");
      tabs.add("Vital Signs");
      tabs.add("Chief Complaints");
      tabs.add("Triage Remark");
      if (!isTriage) {  //consultation: (triage pages), hpi, pmh, fs, ss, drug history, screening, allergy, preg, sr, rf, pe, diagnosis, invest, prescriptin, advice, fu, consul remark
        tabs.add("HPI");
        tabs.add("Previous Medical History");
        tabs.add("Family History");
        tabs.add("Social History");
        tabs.add("Drug History");
        tabs.add("Screening");
        tabs.add("Allergy");
        tabs.add("Pregnancy");
        tabs.add("Review of System");
        tabs.add("Red Flags");
        tabs.add("Physical Examination");
        tabs.add("Clinical Diagnosis");
        tabs.add("Investigation");
        tabs.add("Medication");
        tabs.add("Advice");
        tabs.add("Follow-up");
        tabs.add("Consultation Remark");
      }
      for (String s: tabs) {
        tabLayout.addTab(tabLayout.newTab().setText(s));
      }


      viewPager.setAdapter(new viewPagerAdapter(getSupportFragmentManager()));
      viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
      //viewpager need to set page adapter first
//    tabLayout.setupWithViewPager(viewPager);
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
          viewPager.setCurrentItem((tab.getPosition()));

        }
      });
    }

    setSupportActionBar(toolbar);
    supportActionBar = getSupportActionBar();
    if (supportActionBar != null) {
      //Set patient name as title
      supportActionBar.setDisplayHomeAsUpEnabled(true);
      supportActionBar.setDisplayShowHomeEnabled(true);
    }


    //if patient comes with visit_id >> get triage or both triage and consultation if exist


    //set toolbar title (last name first name)
    if (thisPatient != null && supportActionBar != null) {
      supportActionBar.setTitle(thisPatient.getLastNameSpaceFirstName());
    }
    if (thisPatient == null && supportActionBar != null) {
      supportActionBar.setTitle("New Patient");
    }
    //set toolbar subtitle (clinic name)
    //cc button (only in consultation)
    //confirm button >> dialog, progressbar, (some dialog if successful)
  }

  @Override
  public boolean onPrepareOptionsMenu(Menu menu) {
    if (thisPatient == null){
      MenuItem menuItem3 = menu.findItem(R.id.history);
      menuItem3.setVisible(false);
    }
    return super.onPrepareOptionsMenu(menu);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater menuInflater = getMenuInflater();
    menuInflater.inflate(R.menu.menu_patient_edit, menu);
    MenuItem menuItem1 = menu.findItem(R.id.confirm);
    menuItem1.setIcon(new IconicsDrawable(this).icon(GoogleMaterial.Icon.gmd_check).color(Color.WHITE).actionBar());
    MenuItem menuItem2 = menu.findItem(R.id.history);
    menuItem2.setIcon(new IconicsDrawable(this).icon(GoogleMaterial.Icon.gmd_history).color(Color.WHITE).actionBar());
    menuItem2.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
      @Override
      public boolean onMenuItemClick(MenuItem item) {
        Intent intent = new Intent(getBaseContext(), PatientVisitViewActivity.class);
        intent.putExtra(Const.BundleKey.ON_OR_OFF, false);
        if (thisPatient != null) {
          intent.putExtra(Const.BundleKey.READ_ONLY_PATIENT, thisPatient);
        }
        startActivity(intent);
        return false;
      }
    });
    return true;
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

  public class viewPagerAdapter extends FragmentStatePagerAdapter{
    public viewPagerAdapter(FragmentManager fm) {
      super(fm);
    }

    @Override
    public Fragment getItem(int position) {
      switch (position){
        case 0:
          if (personalDataFragment == null){
            personalDataFragment = PersonalDataFragment.newInstance(thisPatient);
          }
          return personalDataFragment;
        case 1:
          if (vitalSignFragment == null){
            vitalSignFragment = VitalSignFragment.newInstance();
          }
          return vitalSignFragment;
        case 2:
          if (chiefComplaintFragment == null){
            chiefComplaintFragment = ChiefComplaintFragment.newInstance("","");
          }
          return chiefComplaintFragment;
        case 3:
          if (remarkFragment == null){
            remarkFragment = remarkFragment.newInstance("","");
          }
          return remarkFragment;
        case 4:
          return pregnancyFragment = PregnancyFragment.newInstance("","");
        case 5:
          return pregnancyFragment = PregnancyFragment.newInstance("","");
        case 6:
          return pregnancyFragment = PregnancyFragment.newInstance("","");
        case 7:
          return pregnancyFragment = PregnancyFragment.newInstance("","");
        case 8:
          return pregnancyFragment = PregnancyFragment.newInstance("","");
        case 9:
          return pregnancyFragment = PregnancyFragment.newInstance("","");
        case 10:
          return pregnancyFragment = PregnancyFragment.newInstance("","");
        case 11:
          return pregnancyFragment = PregnancyFragment.newInstance("","");
        case 12:
          return pregnancyFragment = PregnancyFragment.newInstance("","");
        case 13:
          return pregnancyFragment = PregnancyFragment.newInstance("","");
        case 14:
          return pregnancyFragment = PregnancyFragment.newInstance("","");
        case 15:
          return pregnancyFragment = PregnancyFragment.newInstance("","");
        case 16:
          return pregnancyFragment = PregnancyFragment.newInstance("","");
        case 17:
          return pregnancyFragment = PregnancyFragment.newInstance("","");
        case 18:
          return pregnancyFragment = PregnancyFragment.newInstance("","");
        case 19:
          return pregnancyFragment = PregnancyFragment.newInstance("","");
        case 20:
          return pregnancyFragment = PregnancyFragment.newInstance("","");
        default:
          return personalDataFragment.newInstance(thisPatient);
      }
    }

    @Override
    public int getCount() {
      return tabs.size();
    }
  }

}
