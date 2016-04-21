package io.github.hkust1516csefyp43.easymed.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import io.github.hkust1516csefyp43.easymed.R;

public class PatientVisitEditActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_patient_visit_edit);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    //get extra: triage or consultation
    //set tablayout pages
    //triage: personal data, vital signs, chief complain, triage remark
    //consultation: (triage pages), hpi, pmh, fs, ss, drug history, screening, allergy, preg, sr, rf, pe, diagnosis, invest, prescriptin, advice, fu, consul remark

    //get extra: patient
    //if patient comes with visit_id >> get triage or both triage and consultation

    //set toolbar title (last name first name)
    //set toolbar subtitle (clinic name)
    //cc button (only in consultation)
    //confirm button >> dialog, progressbar, (some dialog if successful)


  }

}
