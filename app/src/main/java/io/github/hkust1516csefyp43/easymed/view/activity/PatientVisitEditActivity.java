package io.github.hkust1516csefyp43.easymed.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;

import java.io.Serializable;
import java.util.ArrayList;

import io.github.hkust1516csefyp43.easymed.R;
import io.github.hkust1516csefyp43.easymed.listener.OnFragmentInteractionListener;
import io.github.hkust1516csefyp43.easymed.pojo.server_response.Clinic;
import io.github.hkust1516csefyp43.easymed.pojo.server_response.Consultation;
import io.github.hkust1516csefyp43.easymed.pojo.server_response.Patient;
import io.github.hkust1516csefyp43.easymed.pojo.server_response.Triage;
import io.github.hkust1516csefyp43.easymed.pojo.server_response.Visit;
import io.github.hkust1516csefyp43.easymed.utility.Cache;
import io.github.hkust1516csefyp43.easymed.utility.Const;
import io.github.hkust1516csefyp43.easymed.view.fragment.patient_visit_edit.ChiefComplaintFragment;
import io.github.hkust1516csefyp43.easymed.view.fragment.patient_visit_edit.DocumentFragment;
import io.github.hkust1516csefyp43.easymed.view.fragment.patient_visit_edit.ListOfCardsFragment;
import io.github.hkust1516csefyp43.easymed.view.fragment.patient_visit_edit.PersonalDataFragment;
import io.github.hkust1516csefyp43.easymed.view.fragment.patient_visit_edit.PregnancyFragment;
import io.github.hkust1516csefyp43.easymed.view.fragment.patient_visit_edit.RemarkFragment;
import io.github.hkust1516csefyp43.easymed.view.fragment.patient_visit_edit.VitalSignFragment;

public class PatientVisitEditActivity extends AppCompatActivity implements OnFragmentInteractionListener, NavigationView.OnNavigationItemSelectedListener{
  private static final String TAG = PatientVisitEditActivity.class.getSimpleName();

  private PersonalDataFragment personalDataFragment;
  private VitalSignFragment vitalSignFragment;
  private ChiefComplaintFragment chiefComplaintFragment;
  private RemarkFragment remarkFragment;
  private PregnancyFragment pregnancyFragment;
  private ListOfCardsFragment listOfCardsFragment;

  private Patient thisPatient;
  private Visit thisVisit;
  private Triage thisTriage;
  private Consultation thisConsultation;

  private boolean isTriage = true;
  private boolean showHistoryButton = true;

  private ArrayList<String> tabs = new ArrayList<>();

  private TabLayout tabLayout;
  private ViewPager viewPager;
  private ActionBar supportActionBar;
  private DrawerLayout drawerLayout;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_patient_visit_edit);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    tabLayout = (TabLayout) findViewById(R.id.tabLayout);
    viewPager = (ViewPager) findViewById(R.id.viewPager);
    drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);


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

    //TODO get clinic from API (by the clinic_id in patient), not from cache (because Global clinics)
    Clinic clinic = Cache.CurrentUser.getClinic(this);
    if (isTriage && clinic != null && toolbar != null) {
      if (clinic.getEnglishName() != null) {
        toolbar.setSubtitle(clinic.getEnglishName());
      }
    }

    if (isTriage && drawerLayout != null) {
      drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    } else {
      //TODO change back button to hamburger button
      //TODO onclick listeners?
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
      viewPager.addOnPageChangeListener(new customViewPagerOnPageChangeListener(tabLayout, navigationView));
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
    if (isTriage) {
      supportActionBar.setDisplayHomeAsUpEnabled(true);
      supportActionBar.setDisplayShowHomeEnabled(true);
    } else {
      if (navigationView != null) {
        navigationView.setNavigationItemSelectedListener(this);
      }
      ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
      if (drawerLayout != null) {
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
      }
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
    //cc button (only in consultation w/ triage)
    //confirm button >> dialog, progressbar, (some dialog if successful)
  }

  @Override
  public void onBackPressed() {
    if (drawerLayout != null) {
      if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
        drawerLayout.closeDrawer(Gravity.LEFT);
        Log.d(TAG, "byeeeeeeeeeeee");
        return;
      }
    }
    //TODO display dialog
    MaterialDialog.SingleButtonCallback yes = new MaterialDialog.SingleButtonCallback() {
      @Override
      public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
        dialog.dismiss();
        //TODO destroy current patient cache
        PatientVisitEditActivity.this.finish();
      }
    };
    //TODO save data button
    MaterialDialog.SingleButtonCallback no = new MaterialDialog.SingleButtonCallback() {
      @Override
      public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
        dialog.dismiss();
      }
    };
    new MaterialDialog.Builder(this)
        .title("Are you sure")
        .content("Please don't leave")
        .positiveText("Fuck off")
        .negativeText("okey dokey")
        .onPositive(yes)
        .onNegative(no)
        .autoDismiss(false)
        .theme(Theme.LIGHT)
        .show();
  }

  @Override
  public boolean onPrepareOptionsMenu(Menu menu) {
    MenuItem menuItem;
    if (thisPatient == null){
      menuItem = menu.findItem(R.id.history);
      menuItem.setVisible(false);
      menuItem = menu.findItem(R.id.chief_complaint);
      menuItem.setVisible(false);
    }
    if (thisTriage == null) {
      menuItem = menu.findItem(R.id.chief_complaint);
      menuItem.setVisible(false);
    }
    return super.onPrepareOptionsMenu(menu);
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
  public boolean onNavigationItemSelected(MenuItem item) {
    // Handle navigation view item clicks here.
    if (viewPager != null) {
      int id = item.getItemId();
      switch (id) {
        case R.id.nav_personal_data:
          viewPager.setCurrentItem(0);
          break;
        case R.id.nav_vital_signs:
          viewPager.setCurrentItem(1);
          break;
        case R.id.nav_cc:
          viewPager.setCurrentItem(2);
          break;
        case R.id.nav_triage_remark:
          viewPager.setCurrentItem(3);
          break;
        case R.id.nav_hpi:
          viewPager.setCurrentItem(4);
          break;
        case R.id.nav_pmh:
          viewPager.setCurrentItem(5);
          break;
        case R.id.nav_fh:
          viewPager.setCurrentItem(6);
          break;
        case R.id.nav_sh:
          viewPager.setCurrentItem(7);
          break;
        case R.id.nav_dh:
          viewPager.setCurrentItem(8);
          break;
        case R.id.nav_screening:
          viewPager.setCurrentItem(9);
          break;
        case R.id.nav_allergy:
          viewPager.setCurrentItem(10);
          break;
        case R.id.nav_pregnancy:
          viewPager.setCurrentItem(11);
          break;
        case R.id.nav_ros:
          viewPager.setCurrentItem(12);
          break;
        case R.id.nav_rf:
          viewPager.setCurrentItem(13);
          break;
        case R.id.nav_pe:
          viewPager.setCurrentItem(14);
          break;
        case R.id.nav_cd:
          viewPager.setCurrentItem(15);
          break;
        case R.id.nav_investigation:
          viewPager.setCurrentItem(16);
          break;
        case R.id.nav_medication:
          viewPager.setCurrentItem(17);
          break;
        case R.id.nav_advice:
          viewPager.setCurrentItem(18);
          break;
        case R.id.nav_fu:
          viewPager.setCurrentItem(19);
          break;
        case R.id.nav_consultation_remark:
          viewPager.setCurrentItem(20);
          break;
      }
    }

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    if (drawer != null) {
      drawer.closeDrawer(GravityCompat.START);
    }
    return true;
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater menuInflater = getMenuInflater();
    menuInflater.inflate(R.menu.menu_patient_edit, menu);
    MenuItem menuItem1 = menu.findItem(R.id.confirm);
    MenuItem menuItem2 = menu.findItem(R.id.history);
    MenuItem menuItem3 = menu.findItem(R.id.chief_complaint);

    menuItem1.setIcon(new IconicsDrawable(this).icon(GoogleMaterial.Icon.gmd_check).color(Color.WHITE).actionBar());
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
            chiefComplaintFragment = ChiefComplaintFragment.newInstance();
          }
          return chiefComplaintFragment;
        case 3:
          if (remarkFragment == null){
            remarkFragment = remarkFragment.newInstance();
          }
          return remarkFragment;
        case 4:
          return DocumentFragment.newInstance(null, 0);
        case 5:
          return listOfCardsFragment = ListOfCardsFragment.newInstance("Previous Medical History");
        case 6:
          return DocumentFragment.newInstance(null, 1);
        case 7:
          return DocumentFragment.newInstance(null, 2);
        case 8:
          return listOfCardsFragment = ListOfCardsFragment.newInstance("Drug History");
        case 9:
          return listOfCardsFragment = ListOfCardsFragment.newInstance("Screening");
        case 10:
          return listOfCardsFragment = ListOfCardsFragment.newInstance("Allergy");
        case 11:
          return pregnancyFragment = PregnancyFragment.newInstance("","");
        case 12:
          return listOfCardsFragment = ListOfCardsFragment.newInstance("Review of System");
        case 13:
          return listOfCardsFragment = ListOfCardsFragment.newInstance("Red Flags");
        case 14:
          return listOfCardsFragment = ListOfCardsFragment.newInstance("Physical Examination");
        case 15:
          return listOfCardsFragment = ListOfCardsFragment.newInstance("Clinical Diagnosis");
        case 16:
          return listOfCardsFragment = ListOfCardsFragment.newInstance("Investigation");
        case 17:
          return listOfCardsFragment = ListOfCardsFragment.newInstance("Medication");
        case 18:
          return listOfCardsFragment = ListOfCardsFragment.newInstance("Advice");
        case 19:
          return listOfCardsFragment = ListOfCardsFragment.newInstance("Follow-up");
        case 20:
          return remarkFragment = RemarkFragment.newInstance();
        default:
          return personalDataFragment.newInstance(thisPatient);
      }
    }

    @Override
    public int getCount() {
      return tabs.size();
    }
  }

  private class customViewPagerOnPageChangeListener extends TabLayout.TabLayoutOnPageChangeListener {
    private NavigationView navigationView;

    public customViewPagerOnPageChangeListener(TabLayout tabLayout, NavigationView navigationView) {
      super(tabLayout);
      this.navigationView = navigationView;
    }

    @Override
    public void onPageSelected(int position) {
      super.onPageSelected(position);
      if (navigationView != null) {
        switch (position) {
          case 0:
            navigationView.setCheckedItem(R.id.nav_personal_data);
            break;
          case 1:
            navigationView.setCheckedItem(R.id.nav_vital_signs);
            break;
          case 2:
            navigationView.setCheckedItem(R.id.nav_cc);
            break;
          case 3:
            navigationView.setCheckedItem(R.id.nav_triage_remark);
            break;
          case 4:
            navigationView.setCheckedItem(R.id.nav_hpi);
            break;
          case 5:
            navigationView.setCheckedItem(R.id.nav_pmh);
            break;
          case 6:
            navigationView.setCheckedItem(R.id.nav_fh);
            break;
          case 7:
            navigationView.setCheckedItem(R.id.nav_sh);
            break;
          case 8:
            navigationView.setCheckedItem(R.id.nav_dh);
            break;
          case 9:
            navigationView.setCheckedItem(R.id.nav_screening);
            break;
          case 10:
            navigationView.setCheckedItem(R.id.nav_allergy);
            break;
          case 11:
            navigationView.setCheckedItem(R.id.nav_pregnancy);
            break;
          case 12:
            navigationView.setCheckedItem(R.id.nav_ros);
            break;
          case 13:
            navigationView.setCheckedItem(R.id.nav_rf);
            break;
          case 14:
            navigationView.setCheckedItem(R.id.nav_pe);
            break;
          case 15:
            navigationView.setCheckedItem(R.id.nav_cd);
            break;
          case 16:
            navigationView.setCheckedItem(R.id.nav_investigation);
            break;
          case 17:
            navigationView.setCheckedItem(R.id.nav_medication);
            break;
          case 18:
            navigationView.setCheckedItem(R.id.nav_advice);
            break;
          case 19:
            navigationView.setCheckedItem(R.id.nav_fu);
            break;
          case 20:
            navigationView.setCheckedItem(R.id.nav_consultation_remark);
            break;
        }
      }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
      super.onPageScrolled(position, positionOffset, positionOffsetPixels);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
      super.onPageScrollStateChanged(state);
    }
  }

}
