package io.github.hkust1516csefyp43.easymed.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

import io.github.hkust1516csefyp43.easymed.R;
import io.github.hkust1516csefyp43.easymed.pojo.Consultation;
import io.github.hkust1516csefyp43.easymed.pojo.Patient;
import io.github.hkust1516csefyp43.easymed.pojo.Triage;
import io.github.hkust1516csefyp43.easymed.pojo.Visit;
import io.github.hkust1516csefyp43.easymed.utility.Const;

public class PatientVisitEditActivity extends AppCompatActivity {
  private Patient thisPatient;
  private Visit thisVisit;
  private Triage thisTriage;
  private Consultation thisConsultation;
  private boolean isTriage = true;

  private ArrayList<String> tabs = new ArrayList<>();

  private TabLayout tabLayout;
  private ViewPager viewPager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_patient_visit_edit);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    tabLayout = (TabLayout) findViewById(R.id.tabLayout);
    viewPager = (ViewPager) findViewById(R.id.viewPager);


    setSupportActionBar(toolbar);

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
    if (tabLayout != null) {
      for (String s: tabs) {
        tabLayout.addTab(tabLayout.newTab().setText(s));
      }
    }

    //if patient comes with visit_id >> get triage or both triage and consultation if exis


    //set toolbar title (last name first name)
    //set toolbar subtitle (clinic name)
    //cc button (only in consultation)
    //confirm button >> dialog, progressbar, (some dialog if successful)
  }

}