package io.github.hkust1516csefyp43.easymed.view.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.squareup.okhttp.OkHttpClient;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.github.hkust1516csefyp43.easymed.R;
import io.github.hkust1516csefyp43.easymed.pojo.Medication;
import io.github.hkust1516csefyp43.easymed.pojo.Patient;
import io.github.hkust1516csefyp43.easymed.pojo.Prescription;
import io.github.hkust1516csefyp43.easymed.utility.Const;
import io.github.hkust1516csefyp43.easymed.utility.v2API;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class PharmacyActivity extends AppCompatActivity {
  public static final String TAG = PharmacyActivity.class.getSimpleName();

  private Dialog dialog;
  private RecyclerView rv;

  private Patient patient;

  private List<Prescription> prescriptions;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_pharmacy);

    dialog = new Dialog(this, R.style.AppTheme);
    dialog.setContentView(R.layout.dialog_loading);
    dialog.show();

    rv = (RecyclerView) findViewById(R.id.recycler_view);

    //get extra patient (w/ visit_id)
    Intent intent = getIntent();
    if (intent != null) {
      Serializable serializable = intent.getSerializableExtra(Const.BundleKey.READ_ONLY_PATIENT);
      if (serializable instanceof Patient) {
        Log.d(TAG, "patient saved");
        patient = (Patient) serializable;
      }
    }

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    ActionBar actionBar = getSupportActionBar();

    if (patient != null) {
      Log.d(TAG, "patient is not null: " + patient.toString());
      if (patient.getVisitId() != null) {
        Log.d(TAG, "vid exist: " + patient.getVisitId());
        OkHttpClient ohc1 = new OkHttpClient();
        ohc1.setReadTimeout(1, TimeUnit.MINUTES);
        ohc1.setConnectTimeout(1, TimeUnit.MINUTES);

        Retrofit retrofit = new Retrofit
            .Builder()
            .baseUrl(Const.Database.CLOUD_API_BASE_URL_121_dev)
            .addConverterFactory(GsonConverterFactory.create(Const.GsonParserThatWorksWithPGTimestamp))
            .client(ohc1)
            .build();
        v2API.prescriptions prescriptionService = retrofit.create(v2API.prescriptions.class);
        Call<List<Prescription>> prescriptionsCall = prescriptionService.getPrescriptions("1", patient.getVisitId(), null, null, null, null, null, null);
        prescriptionsCall.enqueue(new Callback<List<Prescription>>() {
          @Override
          public void onResponse(Response<List<Prescription>> response, Retrofit retrofit) {
            //TODO get name for each one
            if (response != null) {
              if (response.body() != null) {
                if (response.body().size() >=1 ) {
                  prescriptions = response.body();
                  Log.d(TAG, prescriptions.toString());
                  for (int i = 0; i < prescriptions.size(); i++) {
                    Prescription p = prescriptions.get(i);
                    v2API.medication medicationService = retrofit.create(v2API.medication.class);
                    Call<Medication> medicationCall = medicationService.getMedication("1", p.getMedicationId());
                    final int j = i;
                    medicationCall.enqueue(new Callback<Medication>() {
                      @Override
                      public void onResponse(Response<Medication> response, Retrofit retrofit) {
                        if (response != null) {
                          if (response.body() != null) {
                            if (response.body().getMedication() != null) {
                              prescriptions.get(j).setMedicationName(response.body().getMedication());
                              Log.d(TAG, "new name: " + response.body().getMedication());
                              if (theWholeListOfPrescriptionsGotName()) {
                                showUI();
                              }
                            } else {
                              onFailure(new Throwable("Medication have no name -_-"));
                            }
                          } else {
                            onFailure(new Throwable("Empty body"));
                          }
                        } else {
                          onFailure(new Throwable("No response"));
                        }
                      }

                      @Override
                      public void onFailure(Throwable t) {
                        //How to handle if some medication cannot get medication (no name)
                        //>>solution: set the medication name to Const.EMPTY_STRING then check again
                        t.printStackTrace();
                        prescriptions.get(j).setMedicationName(Const.EMPTY_STRING);
                        if (theWholeListOfPrescriptionsGotName()) {
                          showUI();
                        }
                      }
                    });
                  }
                } else {    //the list id empty
                  onFailure(new Throwable("No prescriptions"));
                  //TODO set next station back to 1 + some dialog explain what happened.
                }
              } else {  //the list is null
                onFailure(new Throwable("No prescriptions"));
                //TODO set next station back to 1 + some dialog explain what happened.
              }
            } else {
              onFailure(new Throwable("No response"));
            }
          }

          @Override
          public void onFailure(Throwable t) {
            t.printStackTrace();
            showUI();
          }
        });
      }

      if (actionBar != null) {
        actionBar.setTitle(patient.getLastNameSpaceFirstName());
      }

    }

    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    if (fab != null) {
      fab.setImageDrawable(new IconicsDrawable(this).icon(GoogleMaterial.Icon.gmd_check).color(Color.WHITE).actionBar());
      fab.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          //TODO click to confirm >> retrofit
        }
      });
    }
  }

  private void showUI() {
    if (prescriptions != null) {
      Log.d(TAG, "before cleanup" + prescriptions.size());
    }
    //clean up invalid prescriptions
    List<Prescription> newPrescriptions = new ArrayList<>();
    for (Prescription p: prescriptions) {
      if (p.getMedicationName() != null) {
        if (p.getMedicationName() != Const.EMPTY_STRING) {
          newPrescriptions.add(p);
        }
      }
    }
    prescriptions = newPrescriptions;
    Log.d(TAG, "after cleanup" + prescriptions.size());
    if (dialog != null) {
      //TODO set adapters and stuff
      if (rv != null) {
        rv.setAdapter(new prescriptionRVAdapter());
        rv.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        new Handler().postDelayed(new Runnable() {      //Dismiss dialog 1s later (avoid the dialog flashing >> weird)
          @Override
          public void run() {
            dialog.dismiss();
            //TODO dismiss animation
          }
        }, 1000);
      }
    }
  }

  /**
   * How to handle if some medication cannot get medication (no name)
   * >>solution: set the medication name to Const.EMPTY_STRING then check again
   * (Because remove it mess up the for loop -_-)
   * @return true if the whole list have have
   */
  private boolean theWholeListOfPrescriptionsGotName () {
    if (prescriptions != null) {
      Log.d(TAG, "Checking if the list have been processed: " + prescriptions.toString());
      for (Prescription p: prescriptions) {
        if (p.getMedicationName() == null) {
          return false;
        }
      }
    }
    return true;
  }

  private class prescriptionsRVViewHolder extends RecyclerView.ViewHolder {
    AppCompatCheckBox appCompatCheckBox;
    TextView tvMedication;
    TextView tvPrescriptionDetail;

    public prescriptionsRVViewHolder(View itemView) {
      super(itemView);
      appCompatCheckBox = (AppCompatCheckBox) itemView.findViewById(R.id.accbPrescription);
      tvMedication = (TextView) itemView.findViewById(R.id.tvMedication);
      tvPrescriptionDetail = (TextView) itemView.findViewById(R.id.tvPrescriptionDetail);

      appCompatCheckBox.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          //TODO PUT prescription (set to prescribed = true)
        }
      });
    }

  }

  private class prescriptionRVAdapter extends RecyclerView.Adapter<prescriptionsRVViewHolder> {

    public prescriptionRVAdapter() {

    }

    @Override
    public prescriptionsRVViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      return new prescriptionsRVViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_prescription, parent, false));
    }

    @Override
    public void onBindViewHolder(prescriptionsRVViewHolder holder, int position) {
      if (holder != null && prescriptions != null) {
        if (position < prescriptions.size()) {
          if (prescriptions.get(position).getMedicationName() != Const.EMPTY_STRING) {
            holder.tvMedication.setText(prescriptions.get(position).getMedicationName());       //TODO check for Const.EMPTY_STRING
            holder.tvPrescriptionDetail.setText(prescriptions.get(position).getDetail());
            holder.appCompatCheckBox.setChecked(prescriptions.get(position).getPrescribed());
          }
        }
      }
    }

    @Override
    public int getItemCount() {
      Log.d("qqq370", String.valueOf(prescriptions.size()));
      return prescriptions.size();
    }
  }

}
