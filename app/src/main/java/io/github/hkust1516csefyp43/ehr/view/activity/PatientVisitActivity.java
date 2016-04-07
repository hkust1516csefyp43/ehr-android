package io.github.hkust1516csefyp43.ehr.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.MaterialDialog.SingleButtonCallback;
import com.afollestad.materialdialogs.Theme;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.squareup.okhttp.OkHttpClient;

import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.github.hkust1516csefyp43.ehr.R;
import io.github.hkust1516csefyp43.ehr.listener.OnCameraRespond;
import io.github.hkust1516csefyp43.ehr.listener.OnFragmentInteractionListener;
import io.github.hkust1516csefyp43.ehr.listener.onSendData;
import io.github.hkust1516csefyp43.ehr.pojo.patient_visit.ChiefComplain;
import io.github.hkust1516csefyp43.ehr.pojo.patient_visit.PersonalData;
import io.github.hkust1516csefyp43.ehr.pojo.patient_visit.Remark;
import io.github.hkust1516csefyp43.ehr.pojo.patient_visit.VitalSigns;
import io.github.hkust1516csefyp43.ehr.pojo.server_response.v2.Clinic;
import io.github.hkust1516csefyp43.ehr.pojo.server_response.v2.Consultation;
import io.github.hkust1516csefyp43.ehr.pojo.server_response.v2.Keyword;
import io.github.hkust1516csefyp43.ehr.pojo.server_response.v2.Patient;
import io.github.hkust1516csefyp43.ehr.pojo.server_response.v2.Triage;
import io.github.hkust1516csefyp43.ehr.pojo.server_response.v2.Visit;
import io.github.hkust1516csefyp43.ehr.v2API;
import io.github.hkust1516csefyp43.ehr.value.Cache;
import io.github.hkust1516csefyp43.ehr.value.Const;
import io.github.hkust1516csefyp43.ehr.view.fragment.patient_visit_activity.ChiefComplainFragment;
import io.github.hkust1516csefyp43.ehr.view.fragment.patient_visit_activity.DocumentFragment;
import io.github.hkust1516csefyp43.ehr.view.fragment.patient_visit_activity.InvestigationFragment;
import io.github.hkust1516csefyp43.ehr.view.fragment.patient_visit_activity.ListOfCardsFragment;
import io.github.hkust1516csefyp43.ehr.view.fragment.patient_visit_activity.PersonalDataFragment;
import io.github.hkust1516csefyp43.ehr.view.fragment.patient_visit_activity.PregnancyFragment;
import io.github.hkust1516csefyp43.ehr.view.fragment.patient_visit_activity.RemarkFragment;
import io.github.hkust1516csefyp43.ehr.view.fragment.patient_visit_activity.VitalSignsFragment;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class PatientVisitActivity extends AppCompatActivity implements OnFragmentInteractionListener {

  String[] consultationTabs = {
      "Personal Data",
      "Vital Signs",
      "Chief Complain",
      "HPI",
      "Previous Medical History",
      "Family History",
      "Social History",
      "Drug History",
      "Screening",
      "Allergy",
      "Pregnancy (Female only)",
      "Review of the System",
      "Physical Examination",
      "Clinical Diagnosis",
      "Investigation",
      "Medication",
      "Advice",
      "Follow-up",
      "Remark"
  };
  String[] triageTabs = {
      "Personal Data",
      "Vital Signs",
      "Chief Complain",
      "Remark"
  };
  private Patient patient = null;
  private String mCC;
  private boolean isTriage;

  private PersonalDataFragment pdf;
  private VitalSignsFragment vsf;
  private ChiefComplainFragment ccf;
  private DocumentFragment dfHPI;
  private ListOfCardsFragment locfPMH;
  private DocumentFragment dfFamily;
  private DocumentFragment dfSocial;
  private ListOfCardsFragment locfDrugHistory;
  private ListOfCardsFragment locfScreening;
  private ListOfCardsFragment locfAllergy;
  private PregnancyFragment pf;
  private ListOfCardsFragment locfRoS;
  private ListOfCardsFragment locfPE;
  private ListOfCardsFragment locfDiagnosis;
  private InvestigationFragment invf;
  private ListOfCardsFragment locfMedication;
  private ListOfCardsFragment locfAdvice;
  private ListOfCardsFragment locfFollowUp;
  private RemarkFragment rf;

  private OnCameraRespond ocrPDF;
  private Triage triage;
  private Consultation consultation;

  private onSendData osdPersonalData;
  private onSendData osdVitalSigns;
  private onSendData osdChiefComplain;
  private onSendData osdHPI;
  private onSendData osdPMH;
  private onSendData osdFamilyHistory;
  private onSendData osdSocialHistory;
  private onSendData osdDrugHistory;
  private onSendData osdScreening;
  private onSendData osdAllergy;
  private onSendData osdPregnancy;
  private onSendData osdRoS;
  private onSendData osdPE;
  private onSendData osdDiagnosis;
  private onSendData osdInvestigation;
  private onSendData osdMedication;
  private onSendData osdAdvice;
  private onSendData osdFollowUp;
  private onSendData osdRemark;

  private Date startTime;

  private ProgressBar pb;
  private ViewPager viewPager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_patient_visit);

    viewPager = (ViewPager) findViewById(R.id.viewpager);
    pb = (ProgressBar) findViewById(R.id.loading_wheel);

    Intent i = getIntent();
    if (i != null) {
      isTriage = i.getBooleanExtra(Const.KEY_IS_TRIAGE, true);
      patient = (Patient) i.getSerializableExtra(Const.KEY_PATIENT);
      triage = (Triage) i.getSerializableExtra(Const.KEY_TRIAGE);
      consultation = (Consultation) i.getSerializableExtra(Const.KEY_CONSULTATION);
    }

    GregorianCalendar gc = new GregorianCalendar();
    startTime = gc.getTime();

    //setup toolbar
    Toolbar tb = (Toolbar) findViewById(R.id.tbPatientVisit);
    setSupportActionBar(tb);
    ActionBar ab = getSupportActionBar();
    if (ab != null) {
      ab.setDisplayHomeAsUpEnabled(true);
      ab.setDisplayShowHomeEnabled(true);

      String title = "";
      if (patient != null) {
        Log.d("qqq13", patient.toString());
        if (patient.getFirstName() != null)
          title += patient.getFirstName() + " ";
        if (patient.getMiddleName() != null)
          title += patient.getMiddleName() + " ";
        if (patient.getLastName() != null)
          title += patient.getLastName();
      } else {
        title = "New patient";
      }
      ab.setTitle(title);

      Clinic clinic = Cache.getCurrentClinic(this);
      if (clinic != null) {
        String subtitle = clinic.getEnglishName();
        ab.setSubtitle(subtitle);
      }
    }

    OkHttpClient ohc1 = new OkHttpClient();
    ohc1.setReadTimeout(1, TimeUnit.MINUTES);
    ohc1.setConnectTimeout(1, TimeUnit.MINUTES);
    Retrofit retrofit = new Retrofit
        .Builder()
        .baseUrl(Const.API_ONE2ONE_HEROKU)
        .addConverterFactory(GsonConverterFactory.create(Const.GsonParserThatWorksWithPGTimestamp))
        .client(ohc1)
        .build();
    v2API.keywords apiService = retrofit.create(v2API.keywords.class);
    Call<List<Keyword>> ck = apiService.getKeywords("1", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
    ck.enqueue(new Callback<List<Keyword>>() {
      @Override
      public void onResponse(Response<List<Keyword>> response, Retrofit retrofit) {
        Cache.setKeywords(getBaseContext(), response.body());
      }

      @Override
      public void onFailure(Throwable t) {

      }
    });

    //Setup tabs
    TabLayout tl = (TabLayout) findViewById(R.id.tlPatientVisit);
    tl.setTabGravity(TabLayout.MODE_SCROLLABLE);
    if (isTriage) {
      for (String tab : triageTabs) {
        tl.addTab(tl.newTab().setText(tab));
      }
    } else {
      for (String tab : consultationTabs) {
        tl.addTab(tl.newTab().setText(tab));
      }
    }


    if (viewPager != null) {
      viewPager.setAdapter(new viewPagerAdapter(getSupportFragmentManager(), isTriage));         //TODO the false >> consultation, true >> triage
      viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tl));
      mCC = getIntent().getStringExtra(Const.KEY_SNACKBAR_TEXT);
      if (mCC != null) {
        Snackbar.make(viewPager, mCC, Snackbar.LENGTH_LONG).show();
      }
      tl.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    Log.d("qqq81", "" + requestCode + "/" + resultCode);
    //TODO I don't think this calculation can handle not just camera call
    //for some reason the request code is accumulative, so a bit of calculation is needed
    if ((requestCode - 1) % 65536 == 0) {
      if (resultCode == RESULT_OK) {
        if (ocrPDF != null) {
          ocrPDF.OnCameraRespond(data);
        } else {
          //TODO can't find the fragment -_-
        }
      } else if (resultCode == RESULT_CANCELED) {
        // User cancelled the image capture
      } else {
        Log.d("qqq83", "cant find fragment?");
      }
    } else
      super.onActivityResult(requestCode, resultCode, data);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_patient_activity, menu);
    menu.findItem(R.id.action_confirm).setIcon(new IconicsDrawable(getApplicationContext(), GoogleMaterial.Icon.gmd_check).color(Color.WHITE).actionBar().paddingDp(2)).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
      @Override
      public boolean onMenuItemClick(MenuItem item) {
        pb.setVisibility(View.VISIBLE);
        OkHttpClient ohc1 = new OkHttpClient();
        ohc1.setReadTimeout(1, TimeUnit.MINUTES);
        ohc1.setConnectTimeout(1, TimeUnit.MINUTES);
        Retrofit retrofit = new Retrofit
            .Builder()
            .baseUrl(Const.API_ONE2ONE_HEROKU)
            .addConverterFactory(GsonConverterFactory.create(Const.GsonParserThatWorksWithPGTimestamp))
            .client(ohc1)
            .build();
        if (isTriage) {
          if (patient == null) {
            if (triage == null) {   //TODO new patient new triage
              Log.d("qqq333a", "new patient new triage");
              //1. POST patient
              Object o = osdPersonalData.onSendData();
              if (o != null) {
                final PersonalData pd = (PersonalData) o;
                Log.d("qqq331", pd.toString());
                v2API.patients patientService = retrofit.create(v2API.patients.class);
                Patient p = inflatePatient(pd);
                p.setClinicId(Cache.getCurrentClinicId(getBaseContext()));
                Log.d("qqq3312", p.toString());
                Call<Patient> patientCall = patientService.addPatient("1", p);
                patientCall.enqueue(new Callback<Patient>() {
                  @Override
                  public void onResponse(Response<Patient> response, Retrofit retrofit) {
                    Log.d("qqq331a", "receiving: " + response.code() + " " + response.message() + " " + response.body());
                    //TODO use dialog to display 400
                    if (response.code() < 300 && response.code() > 199) {       //yes
                      //2. POST visit
                      Visit v = inflateVisit(pd, response.body());
                      Log.d("qqq3321", v.toString());
                      v2API.visits visitService = retrofit.create(v2API.visits.class);
                      Call<Visit> visitCall = visitService.addVisit("1", v);
                      visitCall.enqueue(new Callback<Visit>() {
                        @Override
                        public void onResponse(Response<Visit> response, Retrofit retrofit) {
                          Log.d("qqq331b", "receiving: " + response.code() + " " + response.message() + " " + response.body());
                          if (response.code() < 300 && response.code() > 199) {
                            //3. POST triage
                            Object o2 = null;
                            VitalSigns vs = null;
                            ChiefComplain cc = null;
                            Remark r = null;
                            if (osdVitalSigns != null) {
                              o2 = osdVitalSigns.onSendData();
                              if (o2 != null) {
                                vs = (VitalSigns) o2;
                                Log.d("qqq332", vs.toString());
                              } else {
                                pb.setVisibility(View.GONE);
                                viewPager.setCurrentItem(1);
                              }
                            }
                            if (osdChiefComplain != null) {
                              o2 = osdChiefComplain.onSendData();
                              if (o2 != null) {
                                cc = (ChiefComplain) o2;
                                Log.d("qqq333", cc.toString());
                              } else {
                                pb.setVisibility(View.GONE);
                                viewPager.setCurrentItem(2);
                              }
                            }
                            if (osdRemark != null) {
                              o2 = osdRemark.onSendData();
                              if (o2 != null) {
                                r = (Remark) o2;
                                Log.d("qqq334", r.toString());
                              } else {
                                pb.setVisibility(View.GONE);
                                viewPager.setCurrentItem(3);
                              }
                            }
                            Triage t = inflateTriage(pd, vs, cc, r, response.body());
                            Log.d("qqq335", t.toString());

                            v2API.triages triageService = retrofit.create(v2API.triages.class);
                            Call<Triage> triageCall = triageService.addTriage("1", t);
                            triageCall.enqueue(new Callback<Triage>() {
                              @Override
                              public void onResponse(Response<Triage> response, Retrofit retrofit) {
                                Log.d("qqq331c", "receiving: " + response.code() + " " + response.message() + " " + response.body());
                                if (response.code() < 300 && response.code() > 199) {
                                  //TODO make it a tick
                                  pb.setVisibility(View.GONE);
                                  finish();
                                } else {
                                  try {
                                    Log.d("qqq331c1", response.errorBody().string());
                                  } catch (IOException e) {
                                    e.printStackTrace();
                                  }
                                }
                              }

                              @Override
                              public void onFailure(Throwable t) {
                                pb.setVisibility(View.GONE);
                                Log.d("qqq332c", "no");
                              }
                            });

                          } else {
                            //oh no >> send to onFailure
                          }
                        }

                        @Override
                        public void onFailure(Throwable t) {
                          pb.setVisibility(View.GONE);
                          Log.d("qqq332b", "no");
                        }
                      });
                    } else {
                      try {
                        onFailure(new Throwable(response.errorBody().string()));
                      } catch (IOException e) {
                        e.printStackTrace();
                      }
                    }
                  }

                  @Override
                  public void onFailure(Throwable t) {
                    t.printStackTrace();
                    pb.setVisibility(View.GONE);
                    Log.d("qqq332a2", "no" + t.toString());
                  }
                });

              } else {
                pb.setVisibility(View.GONE);
                viewPager.setCurrentItem(0);
              }
            }
            //else makes not sense (new patient edit triage!?)
          } else {
            if (triage == null) {       //TODO existing patient new triage
              Log.d("qqq333b", "existing patient new triage");
              //1. PUT patient
              Object o = osdPersonalData.onSendData();
              if (o != null) {
                final PersonalData pd = (PersonalData) o;
                Log.d("qqq331", pd.toString());
                v2API.patients patientService = retrofit.create(v2API.patients.class);
                Patient p = inflatePatient(pd);
                p.setClinicId(Cache.getCurrentClinicId(getBaseContext()));
                Log.d("qqq3312", p.toString());
                Call<Patient> patientCall = patientService.editPatient("1", patient.getPatientId(), p);
                patientCall.enqueue(new Callback<Patient>() {
                  @Override
                  public void onResponse(Response<Patient> response, Retrofit retrofit) {
                    Log.d("qqq331a", "receiving: " + response.code() + " " + response.message() + " " + response.body());
                    //TODO use dialog to display 400
                    if (response.code() < 300 && response.code() > 199) {       //yes
                      //2. POST visit
                      Visit v = inflateVisit(pd, response.body());
                      Log.d("qqq3321", v.toString());
                      v2API.visits visitService = retrofit.create(v2API.visits.class);
                      Call<Visit> visitCall = visitService.addVisit("1", v);
                      visitCall.enqueue(new Callback<Visit>() {
                        @Override
                        public void onResponse(Response<Visit> response, Retrofit retrofit) {
                          Log.d("qqq331b", "receiving: " + response.code() + " " + response.message() + " " + response.body());
                          if (response.code() < 300 && response.code() > 199) {
                            //3. POST triage
                            Object o2 = null;
                            VitalSigns vs = null;
                            ChiefComplain cc = null;
                            Remark r = null;
                            if (osdVitalSigns != null) {
                              o2 = osdVitalSigns.onSendData();
                              if (o2 != null) {
                                vs = (VitalSigns) o2;
                                Log.d("qqq332", vs.toString());
                              } else {
                                viewPager.setCurrentItem(1);
                              }
                            }
                            if (osdChiefComplain != null) {
                              o2 = osdChiefComplain.onSendData();
                              if (o2 != null) {
                                cc = (ChiefComplain) o2;
                                Log.d("qqq333", cc.toString());
                              } else {
                                viewPager.setCurrentItem(2);
                              }
                            }
                            if (osdRemark != null) {
                              o2 = osdRemark.onSendData();
                              if (o2 != null) {
                                r = (Remark) o2;
                                Log.d("qqq334", r.toString());
                              } else {
                                viewPager.setCurrentItem(3);
                              }
                            }
                            Triage t = inflateTriage(pd, vs, cc, r, response.body());
                            Log.d("qqq335", t.toString());

                            v2API.triages triageService = retrofit.create(v2API.triages.class);
                            Call<Triage> triageCall = triageService.addTriage("1", t);
                            triageCall.enqueue(new Callback<Triage>() {
                              @Override
                              public void onResponse(Response<Triage> response, Retrofit retrofit) {
                                Log.d("qqq331c", "receiving: " + response.code() + " " + response.message() + " " + response.body());
                                if (response.code() < 300 && response.code() > 199) {
                                  finish();
                                } else {
                                  try {
                                    Log.d("qqq331c1", response.errorBody().string());
                                  } catch (IOException e) {
                                    e.printStackTrace();
                                  }
                                }
                              }

                              @Override
                              public void onFailure(Throwable t) {
                                Log.d("qqq332c", "no");
                              }
                            });

                          } else {
                            //oh no >> send to onFailure
                          }
                        }

                        @Override
                        public void onFailure(Throwable t) {
                          Log.d("qqq332b", "no");
                        }
                      });
                    } else {
                      try {
                        onFailure(new Throwable(response.errorBody().string()));
                      } catch (IOException e) {
                        e.printStackTrace();
                      }
                    }
                  }

                  @Override
                  public void onFailure(Throwable t) {
                    t.printStackTrace();
                    Log.d("qqq332a1", "no" + t.toString());
                  }
                });

              } else {
                viewPager.setCurrentItem(0);
              }
            } else {                    //TODO existing patient edit triage
              //1. PUT patient
              //2. PUT visit (tag number)
              //1. PUT triage
            }
          }
        } else {
          if (patient == null) {
            if (consultation == null) { //TODO new patient new consultation
              //1. POST patient
              PersonalData pd = (PersonalData) osdPersonalData.onSendData();
              //2. POST visit
              //3. POST triage
              VitalSigns vs = (VitalSigns) osdVitalSigns.onSendData();
              ChiefComplain cc = (ChiefComplain) osdChiefComplain.onSendData();
              Remark r = (Remark) osdRemark.onSendData();
              //4. POST consultation
            }
            //else makes no sense (new patient edit consultation!?)
          } else {
            if (consultation == null) {
              if (triage == null) {   //TODO existing patient new consultation (skipped triage)
                //1. POST triage
                VitalSigns vs = (VitalSigns) osdVitalSigns.onSendData();
                ChiefComplain cc = (ChiefComplain) osdChiefComplain.onSendData();
                Remark r = (Remark) osdRemark.onSendData();
                //2. POST consultation
              } else {                //TODO existing patient new consultation
                //1. PUT triage
                VitalSigns vs = (VitalSigns) osdVitalSigns.onSendData();
                ChiefComplain cc = (ChiefComplain) osdChiefComplain.onSendData();
                Remark r = (Remark) osdRemark.onSendData();
                //2. POST consultation
              }
            } else {
              if (triage != null) {   //TODO existing patient edit consultation
                //1. PUT triage
                VitalSigns vs = (VitalSigns) osdVitalSigns.onSendData();
                ChiefComplain cc = (ChiefComplain) osdChiefComplain.onSendData();
                Remark r = (Remark) osdRemark.onSendData();
                //2. PUT consultation
              }
              //else makes no sense (how can you edit consultation without a triage record?
            }
          }
        }
        return false;
      }
    });
    final Context mContext = this;
    menu.findItem(R.id.cc).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
      @Override
      public boolean onMenuItemClick(MenuItem item) {
        if (mCC != null) {
          new MaterialDialog.Builder(mContext)
              .title("Chief Complain")
              .content(mCC)
              .autoDismiss(true)
              .onPositive(new SingleButtonCallback() {
                @Override
                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                  dialog.dismiss();
                }
              })
              .theme(Theme.LIGHT)
              .positiveText("Dismiss")
              .show();
        } else {
          Snackbar.make(viewPager, "No CC", Snackbar.LENGTH_LONG).show();
        }
        return false;
      }
    });
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      onBackPressed();
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public void onBackPressed() {
    SingleButtonCallback yes = new SingleButtonCallback() {
      @Override
      public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
        dialog.dismiss();
        //TODO destroy current patient cache
        PatientVisitActivity.this.finish();
      }
    };
    //TODO save data button
    SingleButtonCallback no = new SingleButtonCallback() {
      @Override
      public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
        dialog.dismiss();
      }
    };
    new MaterialDialog.Builder(this)
        .title(R.string.dismiss_dialog_title)
        .content(R.string.dismiss_dialog_content)
        .positiveText(R.string.dismiss_dialog_positive_text)
        .negativeText(R.string.dismiss_dialog_negative_text)
        .onPositive(yes)
        .onNegative(no)
        .autoDismiss(false)
        .theme(Theme.LIGHT)
        .show();
  }

  @Override
  public void onFragmentInteraction(Uri uri) {
    //TODO?
  }

  public class viewPagerAdapter extends FragmentStatePagerAdapter {
    boolean triage;

    public viewPagerAdapter(FragmentManager fm, boolean t) {
      super(fm);
      triage = t;
    }

    //TODO save sth to call the fragment from activity
    @Override
    public Fragment getItem(int position) {
      switch (position) {
        case 0:
          if (pdf == null) {
            pdf = PersonalDataFragment.newInstance(patient);
            ocrPDF = pdf;
            osdPersonalData = pdf;
          }
          return pdf;
        case 1:
          if (vsf == null) {
            vsf = VitalSignsFragment.newInstance();
            osdVitalSigns = vsf;
          }
          return vsf;
        case 2:
          if (ccf == null) {
            ccf = ChiefComplainFragment.newInstance("", "");
            osdChiefComplain = ccf;
          }
          return ccf;
        case 3:
          if (triage) {
            if (rf == null) {
              rf = RemarkFragment.newInstance();
              osdRemark = rf;
            }
            return rf;
          } else {
            if (dfHPI == null) {
              dfHPI = DocumentFragment.newInstance(Const.KEY_HPI, null);
              osdHPI = dfHPI;
            }
            return dfHPI;
          }
        case 4:
          if (locfPMH == null) {
            locfPMH = ListOfCardsFragment.newInstance("PMH");
            osdPMH = locfPMH;
          }
          return locfPMH;
        case 5:
          if (dfFamily == null) {
            dfFamily = DocumentFragment.newInstance(Const.KEY_FAMILY_HISTORY, null);
            osdFamilyHistory = dfFamily;
          }
          return dfFamily;
        case 6:
          if (dfSocial == null) {
            dfSocial = DocumentFragment.newInstance(Const.KEY_SOCIAL_HISTORY, null);
            osdSocialHistory = dfSocial;
          }
          return dfSocial;
        case 7:
          if (locfDrugHistory == null) {
            locfDrugHistory = ListOfCardsFragment.newInstance("Drug History");
            osdDrugHistory = locfDrugHistory;
          }
          return locfDrugHistory;
        case 8:
          if (locfScreening == null) {
            locfScreening = ListOfCardsFragment.newInstance("Screening");
            osdScreening = locfScreening;
          }
          return locfScreening;
        case 9:
          if (locfAllergy == null) {
            locfAllergy = ListOfCardsFragment.newInstance("Allergy");
            osdAllergy = locfAllergy;
          }
          return locfAllergy;
        case 10:
          if (pf == null) {
            pf = PregnancyFragment.newInstance();
            osdPregnancy = pf;
          }
          return pf;
        case 11:
          if (locfRoS == null) {
            locfRoS = ListOfCardsFragment.newInstance("RoS", Const.DEFAULT_REVICE_OF_SYSTEM);
            osdRoS = locfRoS;
          }
          return locfRoS;
        case 12:
          if (locfPE == null) {
            locfPE = ListOfCardsFragment.newInstance("PE", Const.DEFAULT_PHYSICAL_EXAMINATION);
            osdPE = locfPE;
          }
          return locfPE;
        case 13:
          if (locfDiagnosis == null) {
            locfDiagnosis = ListOfCardsFragment.newInstance("Diagnosis");
            osdDiagnosis = locfDiagnosis;
          }
          return locfDiagnosis;
        case 14:
          //TODO figure out what it should look like first
          return InvestigationFragment.newInstance("", "");
        case 15:
          if (locfMedication == null) {
            locfMedication = ListOfCardsFragment.newInstance("Medication");
            osdMedication = locfMedication;
          }
          return locfMedication;
        case 16:
          if (locfAdvice == null) {
            locfAdvice = ListOfCardsFragment.newInstance("Advice");
            osdAdvice = locfAdvice;
          }
          return locfAdvice;
        case 17:
          if (locfFollowUp == null) {
            locfFollowUp = ListOfCardsFragment.newInstance("Follow-up");
            osdFollowUp = locfFollowUp;
          }
          return locfFollowUp;
        case 18:
          if (rf == null) {
            rf = RemarkFragment.newInstance();
            osdRemark = rf;
          }
          return rf;
        default:
          return PersonalDataFragment.newInstance();
      }
    }

    @Override
    public int getCount() {
      if (triage)
        return triageTabs.length;
      else
        return consultationTabs.length;
    }
  }

  private Patient inflatePatient(PersonalData pd) {
    Patient p = new Patient();
    p.setAddress(pd.getAddress());
    p.setBirthDate(pd.getBirthDate());
    p.setBirthMonth(pd.getBirthMonth());
    p.setBirthYear(pd.getBirthYear());
    p.setFirstName(pd.getFirstName());
    p.setMiddleName(pd.getMiddleName());
    p.setLastName(pd.getLastName());
    p.setNativeName(pd.getNativeName());
    p.setPhoneNumber(pd.getPhoneNumber());
    return p;
  }

  private Visit inflateVisit(PersonalData pd, Patient p) {
    Visit v = new Visit();
    try {
      v.setTag(Integer.parseInt(String.valueOf(pd.getTagNumber())));
    } catch (NumberFormatException e) {
      e.printStackTrace();
    }
    v.setPatientId(p.getPatientId());
    v.setNextStation(Const.ID_CONSULTATION);
    return v;
  }

  private Triage inflateTriage(PersonalData pd, VitalSigns vs, ChiefComplain cc, Remark r, Visit v) {
    Triage t = new Triage();
    t.setStartTime(startTime);
    GregorianCalendar gc = new GregorianCalendar();
    t.setEndTime(gc.getTime());
    if (!isTriage) {
      t.setEditedInConsultation(true);
    }
    if (vs != null) {
      t.setSystolic(vs.getSystolic());
      t.setDiastolic(vs.getDiastolic());
      t.setHeartRate(vs.getPulseRate());
      t.setRespiratoryRate(vs.getRespiratoryRate());
      t.setWeight(vs.getWeight());
      t.setHeight(vs.getHeight());
      t.setTemperature(vs.getTemperature());
      t.setSpo2(vs.getSpo2());
      //TODO LDD
    }
    if (cc != null) {
      t.setChiefComplains(cc.getChiefComplain());
    }
    if (r != null) {
      t.setRemark(r.getRemark());
    }
    if (v != null) {
      t.setVisitId(v.getId());
    }
    return t;
  }
}
