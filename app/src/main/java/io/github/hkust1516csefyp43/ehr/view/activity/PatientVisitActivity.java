package io.github.hkust1516csefyp43.ehr.view.activity;

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
import com.afollestad.materialdialogs.Theme;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;

import io.github.hkust1516csefyp43.ehr.R;
import io.github.hkust1516csefyp43.ehr.listener.OnFragmentInteractionListener;
import io.github.hkust1516csefyp43.ehr.pojo.Patient;
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
import io.github.hkust1516csefyp43.ehr.view.fragment.patient_visit_activity.PersonalDataFragment;
import io.github.hkust1516csefyp43.ehr.view.fragment.patient_visit_activity.PhysicalExaminationFragment;
import io.github.hkust1516csefyp43.ehr.view.fragment.patient_visit_activity.PregnancyFragment;
import io.github.hkust1516csefyp43.ehr.view.fragment.patient_visit_activity.PreviousMedicalHistoryFragment;
import io.github.hkust1516csefyp43.ehr.view.fragment.patient_visit_activity.ROSFragment;
import io.github.hkust1516csefyp43.ehr.view.fragment.patient_visit_activity.ScreeningFragment;
import io.github.hkust1516csefyp43.ehr.view.fragment.patient_visit_activity.SocialHistoryFragment;
import io.github.hkust1516csefyp43.ehr.view.fragment.patient_visit_activity.VitalSignsFragment;

public class PatientVisitActivity extends AppCompatActivity implements OnFragmentInteractionListener {

    ViewPager viewPager;
    String[] tabs = {"Personal Data", "Vital Signs", "Chief Complain", "Previous Medical History", "Screening", "Drug History", "Allergy", "Pregnancy (Female only)", "HPI", "Family History", "Social History", "Review of the System", "Physical Examination", "Clinical Diagnosis", "Investigation", "Medication", "Advice", "Follow-up"};
    Patient patient = null;
    private PersonalDataFragment pdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_visit);

        //setup toolbar
        Toolbar tb = (Toolbar) findViewById(R.id.tbPatientVisit);
        setSupportActionBar(tb);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setDisplayShowHomeEnabled(true);
            patient = (Patient) getIntent().getSerializableExtra("patient");
            String title = "";
            String subtitle = "";
            if (patient != null) {
                Log.d("qqq13", patient.toString());
                subtitle = "from Cannal Side";
                if (patient.getFirstName() != null)
                    title += patient.getFirstName() + " ";
                if (patient.getMiddleName() != null)
                    title += patient.getMiddleName() + " ";
                if (patient.getLastName() != null)
                    title += patient.getLastName();
            } else {
                title = "New patient";
                subtitle = "in Cannal Side";
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
            String snackBarText = getIntent().getStringExtra("snackBarText");
            if (snackBarText != null) {
                Snackbar.make(viewPager, snackBarText, Snackbar.LENGTH_INDEFINITE).show();
            } else {
                Snackbar.make(viewPager, "No CC", Snackbar.LENGTH_LONG).show();
            }
        }
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
        //TODO dialog confirm exit
        MaterialDialog.SingleButtonCallback yes = new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                dialog.dismiss();
                PatientVisitActivity.this.finish();
            }
        };
        MaterialDialog.SingleButtonCallback no = new MaterialDialog.SingleButtonCallback() {
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

    //TODO
    public class viewPagerAdapter extends FragmentStatePagerAdapter {

        public viewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 1:
                    if (pdf == null)
                        pdf = new PersonalDataFragment().newInstance();
                    return pdf;
                case 2:
                    return VitalSignsFragment.newInstance("", "");
                case 3:
                    return ChiefComplainFragment.newInstance("", "");
                case 4:
                    return PreviousMedicalHistoryFragment.newInstance("", "");
                case 5:
                    return ScreeningFragment.newInstance("", "");
                case 6:
                    return DrugHistoryFragment.newInstance("", "");
                case 7:
                    return AllergyFragment.newInstance("", "");
                case 8:
                    return PregnancyFragment.newInstance("", "");
                case 9:
                    return HPIFragment.newInstance("", "");
                case 10:
                    return FamilyHistoryFragment.newInstance("", "");
                case 11:
                    return SocialHistoryFragment.newInstance("", "");
                case 12:
                    return ROSFragment.newInstance("", "");
                case 13:
                    return PhysicalExaminationFragment.newInstance("", "");
                case 14:
                    return ClinicalDiagnosisFragment.newInstance("", "");
                case 15:
                    return InvestigationFragment.newInstance("", "");
                case 16:
                    return MedicationFragment.newInstance("", "");
                case 17:
                    return AdviceFragment.newInstance("", "");
                case 18:
                    return FollowUpFragment.newInstance("", "");
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
