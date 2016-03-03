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

import io.github.hkust1516csefyp43.ehr.R;
import io.github.hkust1516csefyp43.ehr.listener.OnCameraRespond;
import io.github.hkust1516csefyp43.ehr.listener.OnFragmentInteractionListener;
import io.github.hkust1516csefyp43.ehr.pojo.Patient;
import io.github.hkust1516csefyp43.ehr.value.Const;
import io.github.hkust1516csefyp43.ehr.view.fragment.patient_visit_activity.AdviceFragment;
import io.github.hkust1516csefyp43.ehr.view.fragment.patient_visit_activity.AllergyFragment;
import io.github.hkust1516csefyp43.ehr.view.fragment.patient_visit_activity.ChiefComplainFragment;
import io.github.hkust1516csefyp43.ehr.view.fragment.patient_visit_activity.ClinicalDiagnosisFragment;
import io.github.hkust1516csefyp43.ehr.view.fragment.patient_visit_activity.DrugHistoryFragment;
import io.github.hkust1516csefyp43.ehr.view.fragment.patient_visit_activity.FamilyHistoryFragment;
import io.github.hkust1516csefyp43.ehr.view.fragment.patient_visit_activity.FollowUpFragment;
import io.github.hkust1516csefyp43.ehr.view.fragment.patient_visit_activity.HPIFragment;
import io.github.hkust1516csefyp43.ehr.view.fragment.patient_visit_activity.InvestigationFragment;
import io.github.hkust1516csefyp43.ehr.view.fragment.patient_visit_activity.MedicationFragment;
import io.github.hkust1516csefyp43.ehr.view.fragment.patient_visit_activity.PEFragment;
import io.github.hkust1516csefyp43.ehr.view.fragment.patient_visit_activity.PMHFragment;
import io.github.hkust1516csefyp43.ehr.view.fragment.patient_visit_activity.PersonalDataFragment;
import io.github.hkust1516csefyp43.ehr.view.fragment.patient_visit_activity.PregnancyFragment;
import io.github.hkust1516csefyp43.ehr.view.fragment.patient_visit_activity.ROSFragment;
import io.github.hkust1516csefyp43.ehr.view.fragment.patient_visit_activity.RemarkFragment;
import io.github.hkust1516csefyp43.ehr.view.fragment.patient_visit_activity.ScreeningFragment;
import io.github.hkust1516csefyp43.ehr.view.fragment.patient_visit_activity.SocialHistoryFragment;
import io.github.hkust1516csefyp43.ehr.view.fragment.patient_visit_activity.VitalSignsFragment;

public class PatientVisitActivity extends AppCompatActivity implements OnFragmentInteractionListener {

    ViewPager viewPager;
    String[] tabs = {
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
    Patient patient = null;
    Context mContext;
    String mCC;
    private PersonalDataFragment pdf;
    private OnCameraRespond ocrPDF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_visit);
        mContext = this;

        //setup toolbar
        Toolbar tb = (Toolbar) findViewById(R.id.tbPatientVisit);
        setSupportActionBar(tb);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setDisplayShowHomeEnabled(true);
            patient = (Patient) getIntent().getSerializableExtra("patient");
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

        //Setup tabs
        TabLayout tl = (TabLayout) findViewById(R.id.tlPatientVisit);
        tl.setTabGravity(TabLayout.MODE_SCROLLABLE);
        for (String tab : tabs) {
            tl.addTab(tl.newTab().setText(tab));
        }

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        if (viewPager != null) {
            viewPager.setAdapter(new viewPagerAdapter(getSupportFragmentManager()));
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
                return false;
            }
        });
        menu.findItem(R.id.action_save).setIcon(new IconicsDrawable(getApplicationContext(), GoogleMaterial.Icon.gmd_save).color(Color.WHITE).actionBar().paddingDp(2)).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });
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
                PatientVisitActivity.this.finish();
            }
        };
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

        public viewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        //TODO save sth to call the fragment from activity
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    if (pdf == null) {
                        pdf = new PersonalDataFragment().newInstance(patient);
                        ocrPDF = pdf;
                    }
                    return pdf;
                case 1:
                    return VitalSignsFragment.newInstance();
                case 2:
                    return ChiefComplainFragment.newInstance("", "");
                case 4:
                    return PMHFragment.newInstance("", "");
                case 8:
                    return ScreeningFragment.newInstance("", "");
                case 7:
                    return DrugHistoryFragment.newInstance("", "");
                case 9:
                    return AllergyFragment.newInstance("", "");
                case 10:
                    return PregnancyFragment.newInstance("", "");
                case 3:
                    return HPIFragment.newInstance("", "");
                case 5:
                    return FamilyHistoryFragment.newInstance("", "");
                case 6:
                    return SocialHistoryFragment.newInstance("", "");
                case 11:
                    return ROSFragment.newInstance("", "");
                case 12:
                    return PEFragment.newInstance("", "");
                case 13:
                    return ClinicalDiagnosisFragment.newInstance("", "");
                case 14:
                    return InvestigationFragment.newInstance("", "");
                case 15:
                    return MedicationFragment.newInstance("", "");
                case 16:
                    return AdviceFragment.newInstance("", "");
                case 17:
                    return FollowUpFragment.newInstance("", "");
                case 18:
                    return RemarkFragment.newInstance("", "");
                default:
                    return PersonalDataFragment.newInstance();
            }
        }

        @Override
        public int getCount() {
            return tabs.length;
        }
    }
}
