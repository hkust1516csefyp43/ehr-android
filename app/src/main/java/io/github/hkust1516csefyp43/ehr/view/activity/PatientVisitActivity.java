package io.github.hkust1516csefyp43.ehr.view.activity;

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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;

import io.github.hkust1516csefyp43.ehr.R;
import io.github.hkust1516csefyp43.ehr.pojo.Patient;
import io.github.hkust1516csefyp43.ehr.view.fragment.patient_visit_activity.PersonalDataFragment;

public class PatientVisitActivity extends AppCompatActivity implements PersonalDataFragment.OnFragmentInteractionListener {

    ViewPager viewPager;
    String[] tabs = {"Personal Data", "Vital Signs", "Chief Complain", "Previous Medical History", "Screening", "Drug History", "Allergy", "Pregnancy (Female only)", "HPI", "Family History", "Social History", "Review of the System", "Physical Examination", "Clinical Diagnosis", "Investigation", "Medication", "Advice", "Follow-up"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_visit);

        Patient p = (Patient) getIntent().getSerializableExtra("patient");
        if (p != null) {
            Log.d("qqq13", p.toString());
        }

        //setup toolbar
        Toolbar tb = (Toolbar) findViewById(R.id.tbPatientVisit);
        setSupportActionBar(tb);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setDisplayShowHomeEnabled(true);
            ab.setTitle("New patient");
            ab.setSubtitle("in Slum XXX");
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
                    return PersonalDataFragment.newInstance("random", "text");
                case 2:
                    return PersonalDataFragment.newInstance("random", "text");
                default:
                    return PersonalDataFragment.newInstance("random", "text");
            }
        }

        @Override
        public int getCount() {
            return tabs.length;
        }
    }
}
