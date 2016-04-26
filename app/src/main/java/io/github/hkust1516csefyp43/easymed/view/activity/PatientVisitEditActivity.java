package io.github.hkust1516csefyp43.easymed.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
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
import java.util.concurrent.TimeUnit;

import io.github.hkust1516csefyp43.easymed.R;
import io.github.hkust1516csefyp43.easymed.listener.OnFragmentInteractionListener;
import io.github.hkust1516csefyp43.easymed.pojo.patient_visit_edit.ListOfCards;
import io.github.hkust1516csefyp43.easymed.pojo.patient_visit_edit.PersonalData;
import io.github.hkust1516csefyp43.easymed.pojo.patient_visit_edit.Pregnancy;
import io.github.hkust1516csefyp43.easymed.pojo.patient_visit_edit.VitalSigns;
import io.github.hkust1516csefyp43.easymed.pojo.server_response.Clinic;
import io.github.hkust1516csefyp43.easymed.pojo.server_response.Consultation;
import io.github.hkust1516csefyp43.easymed.pojo.server_response.Patient;
import io.github.hkust1516csefyp43.easymed.pojo.server_response.Triage;
import io.github.hkust1516csefyp43.easymed.pojo.server_response.Visit;
import io.github.hkust1516csefyp43.easymed.utility.Const;
import io.github.hkust1516csefyp43.easymed.utility.v2API;
import io.github.hkust1516csefyp43.easymed.view.fragment.patient_visit_edit.ChiefComplaintFragment;
import io.github.hkust1516csefyp43.easymed.view.fragment.patient_visit_edit.DocumentFragment;
import io.github.hkust1516csefyp43.easymed.view.fragment.patient_visit_edit.ListOfCardsFragment;
import io.github.hkust1516csefyp43.easymed.view.fragment.patient_visit_edit.PersonalDataFragment;
import io.github.hkust1516csefyp43.easymed.view.fragment.patient_visit_edit.PregnancyFragment;
import io.github.hkust1516csefyp43.easymed.view.fragment.patient_visit_edit.RemarkFragment;
import io.github.hkust1516csefyp43.easymed.view.fragment.patient_visit_edit.VitalSignFragment;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PatientVisitEditActivity extends AppCompatActivity implements OnFragmentInteractionListener, NavigationView.OnNavigationItemSelectedListener{
  private static final String TAG = PatientVisitEditActivity.class.getSimpleName();
  public static final String[] DEFAULT_PHYSICAL_EXAMINATION = {"General Appearance", "Respiratory", "Cardiovascular", "Gastrointestinal", "Genital/Urinary", "ENT", "Skin", "Other"};
  public static final String[] DEFAULT_REVICE_OF_SYSTEM = {"EENT", "Respiratory", "Cardiovascular", "Gastrointestinal", "Genital/Urinary", "ENT", "Skin", "Locomotor", "Neurology"};

  private PersonalDataFragment personalDataFragment;
  private VitalSignFragment vitalSignFragment;
  private ChiefComplaintFragment chiefComplaintFragment;
  private RemarkFragment triageRemarkFragment;
  private DocumentFragment hpiFragment;
  private ListOfCardsFragment pmhFragment;
  private DocumentFragment fhFragment;
  private DocumentFragment shFragment;
  private ListOfCardsFragment dhFragment;
  private ListOfCardsFragment screeningFragment;
  private ListOfCardsFragment allergyFragment;
  private PregnancyFragment pregnancyFragment;
  private ListOfCardsFragment rosFragment;
  private ListOfCardsFragment rfFragment;
  private ListOfCardsFragment peFragment;
  private ListOfCardsFragment diagnosisFragment;
  private ListOfCardsFragment investigationFragment;
  private ListOfCardsFragment medicationFragment;
  private ListOfCardsFragment adviceFragment;
  private ListOfCardsFragment followupFragment;
  private RemarkFragment consultationRemarkFragment;

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
    final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    tabLayout = (TabLayout) findViewById(R.id.tabLayout);
    viewPager = (ViewPager) findViewById(R.id.viewPager);
    drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);


    //get extra
    Intent intent = getIntent();
    if (intent != null) {
      Serializable serializable;
      //isTriage
      isTriage = intent.getBooleanExtra(Const.BundleKey.IS_TRIAGE, true);
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

    if (thisPatient != null && thisPatient.getClinicId() != null) {
      OkHttpClient.Builder ohc1 = new OkHttpClient.Builder();
      ohc1.readTimeout(1, TimeUnit.MINUTES);
      ohc1.connectTimeout(1, TimeUnit.MINUTES);
      Retrofit retrofit = new Retrofit
          .Builder()
          .baseUrl(Const.Database.CLOUD_API_BASE_URL_121_dev)
          .addConverterFactory(GsonConverterFactory.create(Const.GsonParserThatWorksWithPGTimestamp))
          .client(ohc1.build())
          .build();
      v2API.clinics clinicService = retrofit.create(v2API.clinics.class);
      Call<Clinic> clinicCall = clinicService.getClinic("1", thisPatient.getClinicId());
      clinicCall.enqueue(new Callback<Clinic>() {
        @Override
        public void onResponse(Call<Clinic> call, Response<Clinic> response) {
          if (response != null && response.body() != null && response.body().getEnglishName() != null) {
            if (toolbar != null) {
              toolbar.setSubtitle(response.body().getEnglishName());
            }
          }
        }

        @Override
        public void onFailure(Call<Clinic> call, Throwable t) {

        }
      });
    }
//
//    Clinic clinic = Cache.CurrentUser.getClinic(this);
//    if (isTriage && clinic != null && toolbar != null) {
//      if (clinic.getEnglishName() != null) {
//        toolbar.setSubtitle(clinic.getEnglishName());
//      }
//    }

    if (isTriage && drawerLayout != null) {
      drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
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
      viewPager.setOffscreenPageLimit(20);
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
    MaterialDialog.SingleButtonCallback yes = new MaterialDialog.SingleButtonCallback() {
      @Override
      public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
        dialog.dismiss();
        PatientVisitEditActivity.this.finish();
      }
    };
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
        onBackPressed();
        return true;
      case R.id.chief_complaint:
        if (thisTriage != null && thisTriage.getChiefComplaints() != null) {
          new MaterialDialog.Builder(this)
              .title("Chief Complain")
              .content(thisTriage.getChiefComplaints())
              .autoDismiss(true)
              .onPositive(new MaterialDialog.SingleButtonCallback() {
                @Override
                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                  dialog.dismiss();
                }
              })
              .theme(Theme.LIGHT)
              .positiveText("Dismiss")
              .show();
        } else {
          if (viewPager != null) {
            Snackbar.make(viewPager, "No Chief Complain", Snackbar.LENGTH_LONG).show();
          } else {
            new MaterialDialog.Builder(this)
                .content("No Chief Complain")
                .autoDismiss(true)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                  @Override
                  public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                    dialog.dismiss();
                  }
                })
                .theme(Theme.LIGHT)
                .positiveText("Dismiss")
                .show();
          }
        }
        return false;
      case R.id.history:
        Intent intent = new Intent(getBaseContext(), PatientVisitViewActivity.class);
        intent.putExtra(Const.BundleKey.ON_OR_OFF, false);
        if (thisPatient != null) {
          intent.putExtra(Const.BundleKey.READ_ONLY_PATIENT, thisPatient);
        }
        startActivity(intent);
        return false;
      case R.id.confirm:
        Serializable serializable;
        PersonalData personalData;
        VitalSigns vitalSigns;
        String chiefComplaints;
        String triageRemark;
        if (personalDataFragment != null) {
          serializable = personalDataFragment.onSendData();
          if (serializable instanceof PersonalData) {
            personalData = (PersonalData) serializable;
          }
        }
        if (vitalSignFragment != null) {
          serializable = vitalSignFragment.onSendData();
          if (serializable instanceof VitalSigns) {
            vitalSigns = (VitalSigns) serializable;
          }
        }
        if (chiefComplaintFragment != null) {
          serializable = chiefComplaintFragment.onSendData();
          if (serializable instanceof String) {
            chiefComplaints = (String) serializable;
          }
        }
        if (triageRemarkFragment != null) {
          serializable = triageRemarkFragment.onSendData();
          if (serializable instanceof String) {
            triageRemark = (String) serializable;
          }
        }
        if (!isTriage) {
          String hpi;
          ListOfCards pmh;
          String fh;
          String sh;
          ListOfCards dh;
          ListOfCards screening;
          ListOfCards allergy;
          Pregnancy pregnancy;
          ListOfCards ros;
          ListOfCards rf;
          ListOfCards pe;
          ListOfCards diagnosis;
          ListOfCards investigation;
          ListOfCards medication;
          ListOfCards advice;
          ListOfCards followup;
          String consultationRemark;
          if (hpiFragment != null) {
            serializable = hpiFragment.onSendData();
            if (serializable instanceof String) {
              hpi = (String) serializable;
            }
          }
          if (pmhFragment != null) {
            serializable = pmhFragment.onSendData();
            if (serializable instanceof ListOfCards) {
              pmh = (ListOfCards) serializable;
            }
          }
          if (fhFragment != null) {
            serializable = fhFragment.onSendData();
            if (serializable instanceof String) {
              fh = (String) serializable;
            }
          }
          if (shFragment != null) {
            serializable = shFragment.onSendData();
            if (serializable instanceof String) {
              sh = (String) serializable;
            }
          }
          if (dhFragment != null) {
            serializable = dhFragment.onSendData();
            if (serializable instanceof ListOfCards) {
              dh = (ListOfCards) serializable;
            }
          }
          if (screeningFragment != null) {
            serializable = screeningFragment.onSendData();
            if (serializable instanceof ListOfCards) {
              screening = (ListOfCards) serializable;
            }
          }
          if (allergyFragment != null) {
            serializable = allergyFragment.onSendData();
            if (serializable instanceof ListOfCards) {
              allergy = (ListOfCards) serializable;
            }
          }
          if (pregnancyFragment != null) {
            serializable = pregnancyFragment.onSendData();
            if (serializable instanceof Pregnancy) {
              pregnancy = (Pregnancy) serializable;
            }
          }
          if (rosFragment != null) {
            serializable = rosFragment.onSendData();
            if (serializable instanceof ListOfCards) {
              ros = (ListOfCards) serializable;
            }
          }
          if (rfFragment != null) {
            serializable = rfFragment.onSendData();
            if (serializable instanceof ListOfCards) {
              rf = (ListOfCards) serializable;
            }
          }
          if (peFragment != null) {
            serializable = peFragment.onSendData();
            if (serializable instanceof ListOfCards) {
              pe = (ListOfCards) serializable;
            }
          }
          if (diagnosisFragment != null) {
            serializable = diagnosisFragment.onSendData();
            if (serializable instanceof ListOfCards) {
              diagnosis = (ListOfCards) serializable;
            }
          }
          if (investigationFragment != null) {
            serializable = investigationFragment.onSendData();
            if (serializable instanceof ListOfCards) {
              investigation = (ListOfCards) serializable;
            }
          }
          if (medicationFragment != null) {
            serializable = medicationFragment.onSendData();
            if (serializable instanceof ListOfCards) {
              medication = (ListOfCards) serializable;
            }
          }
          if (adviceFragment != null) {
            serializable = adviceFragment.onSendData();
            if (serializable instanceof ListOfCards) {
              advice = (ListOfCards) serializable;
            }
          }
          if (followupFragment != null) {
            serializable = followupFragment.onSendData();
            if (serializable instanceof ListOfCards) {
              followup = (ListOfCards) serializable;
            }
          }
          if (consultationRemarkFragment != null) {
            serializable = consultationRemarkFragment.onSendData();
            if (serializable instanceof String) {
              consultationRemark = (String) serializable;
            } else if (serializable instanceof Throwable) {

            }
          }
        }

        OkHttpClient.Builder ohc1 = new OkHttpClient.Builder();
        ohc1.readTimeout(1, TimeUnit.MINUTES);
        ohc1.connectTimeout(1, TimeUnit.MINUTES);
        Retrofit retrofit = new Retrofit
            .Builder()
            .baseUrl(Const.Database.CLOUD_API_BASE_URL_121_dev)
            .addConverterFactory(GsonConverterFactory.create(Const.GsonParserThatWorksWithPGTimestamp))
            .client(ohc1.build())
            .build();


        

        if (thisPatient != null) {
          if (isTriage) {
            if (thisTriage != null) {                                                               //existing patient edit triage
              //PUT patient
              v2API.patients patientService = retrofit.create(v2API.patients.class);
              //PUT visit (iff tag number have been modified?)
              //PUT triage
              v2API.triages triageService = retrofit.create(v2API.triages.class);
            } else {                                                                                //existing patient new triage
              //PUT patient
              //POST triage
            }
          } else {
            if (thisConsultation != null) {                                                         //existing patient edit consultation (and triage)
              //PUT patient
              //PUT visit (iff tag number have been modified?)
              //PUT triage
              //PUT consultation
              //PUT document
              //PUT investigations (how?)
              //Update related_data (how?)
            } else {
              if (thisTriage != null) {                                                             //existing patient new consultation edit triage
                //PUT patient
                //PUT visit (iff tag number have been modified?)
                //PUT triage
                //POST consultation
                //POST related_data
                //POST investigation
                //POST prescription
              } else {                                                                              //existing patient new consultation new triage
                //PUT patient
                //POST visit
                //POST triage
                //POST consultation
                //POST related_data
                //POST investigation
                //POST prescription
              }
            }
          }
        } else {
          if (isTriage) {                                                                           //new patient new triage
            //POST patient
            //POST visit
            //POST triage
          } else {                                                                                  //new patient new consultation (and triage)
            //POST patient
            //POST visit
            //POST triage
            //POST document
            //POST consultation
            //POST related_data
            //POST investigation
            //POST prescription
          }
        }
        return false;
      default:
        return false;
    }
  }

  private Patient generatePatient(PersonalData personalData) {
    Patient patient = new Patient();
    //TODO
    return patient;
  }

  private Triage generateTriage(VitalSigns vitalSigns, String chiefComplaint, String remark) {
    Triage triage = new Triage();
    //TODO
    return triage;
  }

  private Consultation generateConsultation() {
    Consultation consultation = new Consultation();
    //TODO
    return consultation;
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
    menuItem1.setIcon(new IconicsDrawable(this).icon(GoogleMaterial.Icon.gmd_check).color(Color.WHITE).actionBar());
    menuItem2.setIcon(new IconicsDrawable(this).icon(GoogleMaterial.Icon.gmd_history).color(Color.WHITE).actionBar());
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
          if (triageRemarkFragment == null){
            triageRemarkFragment = RemarkFragment.newInstance();
          }
          return triageRemarkFragment;
        case 4:
          if (hpiFragment == null){
            hpiFragment = DocumentFragment.newInstance(null, 0);
          }
          return hpiFragment;
        case 5:
          if (pmhFragment == null) {
            pmhFragment = ListOfCardsFragment.newInstance("Previous Medical History");
          }
          return pmhFragment;
        case 6:
          if (fhFragment == null) {
            fhFragment = DocumentFragment.newInstance(null, 1);
          }
          return fhFragment;
        case 7:
          if (shFragment == null) {
            shFragment = DocumentFragment.newInstance(null, 2);
          }
          return shFragment;
        case 8:
          if (dhFragment == null) {
            dhFragment = ListOfCardsFragment.newInstance("Drug History");
          }
          return dhFragment;
        case 9:
          if (screeningFragment == null) {
            screeningFragment = ListOfCardsFragment.newInstance("Screening");
          }
          return screeningFragment;
        case 10:
          if (allergyFragment == null) {
            allergyFragment = ListOfCardsFragment.newInstance("Allergy");
          }
          return allergyFragment;
        case 11:
          if (pregnancyFragment == null) {
            pregnancyFragment = PregnancyFragment.newInstance("","");
          }
          return pregnancyFragment;
        case 12:
          if (rosFragment == null) {
            rosFragment = ListOfCardsFragment.newInstance("Review of System", DEFAULT_REVICE_OF_SYSTEM);
          }
          return rosFragment;
        case 13:
          if (rfFragment == null) {
            rfFragment = ListOfCardsFragment.newInstance("Red Flags");
          }
          return rfFragment;
        case 14:
          if (peFragment == null) {
            peFragment = ListOfCardsFragment.newInstance("Physical Examination", DEFAULT_PHYSICAL_EXAMINATION);
          }
          return peFragment;
        case 15:
          if (diagnosisFragment == null) {
            diagnosisFragment = ListOfCardsFragment.newInstance("Clinical Diagnosis");
          }
          return diagnosisFragment;
        case 16:
          if (investigationFragment == null) {
            investigationFragment = ListOfCardsFragment.newInstance("Investigation");
          }
          return investigationFragment;
        case 17:
          if (medicationFragment == null) {
            medicationFragment = ListOfCardsFragment.newInstance("Medication");
          }
          return medicationFragment;
        case 18:
          if (adviceFragment == null) {
            adviceFragment = ListOfCardsFragment.newInstance("Advice");
          }
          return adviceFragment;
        case 19:
          if (followupFragment == null) {
            followupFragment = ListOfCardsFragment.newInstance("Follow-up");
          }
          return followupFragment;
        case 20:
          if (consultationRemarkFragment == null) {
            consultationRemarkFragment = RemarkFragment.newInstance();
          }
          return consultationRemarkFragment;
        default:
          return PersonalDataFragment.newInstance(thisPatient);
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
