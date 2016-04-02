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

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.MaterialDialog.SingleButtonCallback;
import com.afollestad.materialdialogs.Theme;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.squareup.okhttp.OkHttpClient;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.github.hkust1516csefyp43.ehr.R;
import io.github.hkust1516csefyp43.ehr.listener.OnCameraRespond;
import io.github.hkust1516csefyp43.ehr.listener.OnFragmentInteractionListener;
import io.github.hkust1516csefyp43.ehr.pojo.server_response.v2.Keyword;
import io.github.hkust1516csefyp43.ehr.v2API;
import io.github.hkust1516csefyp43.ehr.value.Cache;
import io.github.hkust1516csefyp43.ehr.value.Const;
import io.github.hkust1516csefyp43.ehr.view.fragment.patient_visit_activity.ChiefComplainFragment;
import io.github.hkust1516csefyp43.ehr.view.fragment.patient_visit_activity.DocumentFragment;
import io.github.hkust1516csefyp43.ehr.view.fragment.patient_visit_activity.InvestigationFragment;
import io.github.hkust1516csefyp43.ehr.view.fragment.patient_visit_activity.ListOfCardsFragment;
import io.github.hkust1516csefyp43.ehr.view.fragment.patient_visit_activity.MedicationFragment;
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

    ViewPager viewPager;
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
    io.github.hkust1516csefyp43.ehr.pojo.server_response.v2.Patient patient = null;
    String mCC;
    boolean isTriage;
    private PersonalDataFragment pdf;
    private OnCameraRespond ocrPDF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_visit);

        Intent i = getIntent();
        if (i != null)
            isTriage = i.getBooleanExtra(Const.KEY_IS_TRIAGE, true);

        //setup toolbar
        Toolbar tb = (Toolbar) findViewById(R.id.tbPatientVisit);
        setSupportActionBar(tb);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setDisplayShowHomeEnabled(true);
            patient = (io.github.hkust1516csefyp43.ehr.pojo.server_response.v2.Patient) getIntent().getSerializableExtra("patient");
            //TODO get another extra: edit/new/view, triage/consultation
            String title = "";
            String subtitle = "from Cannal Side";
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
            ab.setSubtitle(subtitle);
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
//        TODO post patient testADD
//        v2API.patients patientService = retrofit.create(v2API.patients.class);
//        String clinicId = Cache.getCurrentClinicId(this);
//        Log.d("qqq310", "id = " + clinicId);
//        Patient p = new Patient("address", 16, 9, 1994, null, clinicId, null, "email.com", "louis", "caw23232", null, null, "Tsai", null, null, null, null, null);
//        Log.d("qqq310", p.toString());
//        Call<Patient> patientCall = patientService.addPatient("1", p);
//        patientCall.enqueue(new Callback<Patient>() {
//            @Override
//            public void onResponse(Response<Patient> response, Retrofit retrofit) {
//                if (response != null) {
//                    Log.d("qqq310", "yes: " + response.code() + " / " + response.message());
//                    if (response.errorBody() != null) {
//                        try {
//                            Log.d("qqq310", "not really: " + response.errorBody().string());
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//                Log.d("qqq311", "no");
//            }
//        });

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

        viewPager = (ViewPager) findViewById(R.id.viewpager);
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
                //TODO POST/PUT triage/consultation

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
                    }
                    return pdf;
                case 1:
                    return VitalSignsFragment.newInstance();
                case 2:
                    return ChiefComplainFragment.newInstance("", "");
                case 3:
                    if (triage)
                        return RemarkFragment.newInstance("", "");
                    else
                        return DocumentFragment.newInstance(Const.KEY_HPI, null);
                case 4:
                    return ListOfCardsFragment.newInstance("PMH");
                case 5:
                    return DocumentFragment.newInstance(Const.KEY_FAMILY_HISTORY, null);
                case 6:
                    return DocumentFragment.newInstance(Const.KEY_SOCIAL_HISTORY, null);
                case 7:
                    return ListOfCardsFragment.newInstance("Drug History");
                case 8:
                    return ListOfCardsFragment.newInstance("Screening");
                case 9:
                    return ListOfCardsFragment.newInstance("Allergy");
                case 10:
                    return PregnancyFragment.newInstance("", "");
                case 11:
                    return ListOfCardsFragment.newInstance("RoS", Const.DEFAULT_REVICE_OF_SYSTEM);
                case 12:
                    return ListOfCardsFragment.newInstance("PE", Const.DEFAULT_PHYSICAL_EXAMINATION);
                case 13:
                    return ListOfCardsFragment.newInstance("Diagnosis");
                case 14:
                    return InvestigationFragment.newInstance("", "");
                case 15:
                    return MedicationFragment.newInstance("", "");
                case 16:
                    return ListOfCardsFragment.newInstance("Advice");
                case 17:
                    return ListOfCardsFragment.newInstance("Follow-up");
                case 18:
                    return RemarkFragment.newInstance("", "");
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
}
