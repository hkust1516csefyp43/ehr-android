package io.github.hkust1516csefyp43.ehr.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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

import com.squareup.okhttp.OkHttpClient;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.github.hkust1516csefyp43.ehr.R;
import io.github.hkust1516csefyp43.ehr.listener.OnFragmentInteractionListener;
import io.github.hkust1516csefyp43.ehr.pojo.server_response.v2.Patient;
import io.github.hkust1516csefyp43.ehr.pojo.server_response.v2.Prescription;
import io.github.hkust1516csefyp43.ehr.v2API;
import io.github.hkust1516csefyp43.ehr.value.Const;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * TODO handle no prescription patients (probably in POST consultations)
 * TODO POST pharmacy (but when?)
 */
public class PharmacyActivity extends AppCompatActivity implements OnFragmentInteractionListener {
  private Toolbar tb;
  private ActionBar ab;
  private RecyclerView rv;

  private Patient patient;
  private List<Prescription> prescriptionList;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_pharmacy);

    Intent i = getIntent();
    if (i != null) {
      Serializable s = i.getSerializableExtra(Const.KEY_PATIENT);
      if (s != null) {
        patient = (Patient) s;
      }
    }

    tb = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(tb);
    ab = getSupportActionBar();
    if (ab != null) {
      ab.setDisplayHomeAsUpEnabled(true);
      ab.setDisplayShowHomeEnabled(true);
      ab.setTitle("Pharmacy");
    }

    rv = (RecyclerView) findViewById(R.id.rvPrescriptions);

    if (patient != null) {
      Log.d("qqq381", "yes");
      if (patient.getVisitId() != null) {
        Log.d("qqq382", "yes");
        OkHttpClient ohc1 = new OkHttpClient();
        ohc1.setReadTimeout(1, TimeUnit.MINUTES);
        ohc1.setConnectTimeout(1, TimeUnit.MINUTES);
        Retrofit retrofit = new Retrofit
            .Builder()
            .baseUrl(Const.API_ONE2ONE_HEROKU)
            .addConverterFactory(GsonConverterFactory.create(Const.GsonParserThatWorksWithPGTimestamp))
            .client(ohc1)
            .build();
        v2API.prescriptions prescriptionService = retrofit.create(v2API.prescriptions.class);
        Log.d("qqq2741", patient.getVisitId());
        Call<List<Prescription>> prescriptionListCall = prescriptionService.getPrescriptions("1", patient.getVisitId(), null, null, null, null, null, null);
        prescriptionListCall.enqueue(new Callback<List<Prescription>>() {
          @Override
          public void onResponse(Response<List<Prescription>> response, Retrofit retrofit) {
            //TODO
            Log.d("qqq274", "receiving: " + response.code() + " " + response.message() + " " + response.body());
            if (response.code() > 199 && response.code() < 300) {
              prescriptionList = response.body();
              rv.setAdapter(new prescriptionRVAdapter());
              rv.setLayoutManager(new LinearLayoutManager(getBaseContext()));
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

          }
        });
      } else {
        Log.d("qqq382", "no");
        //TODO patient have no visit_id >> shouldn't be able to enter in the first place
      }
    } else {
      Log.d("qqq381", "no");
      //TODO null patient >> shouldn't be able to enter in the first place
    }
  }

  @Override
  public void onFragmentInteraction(Uri uri) {

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
      if (holder != null && prescriptionList != null) {
        if (position < prescriptionList.size()) {
          holder.tvMedication.setText(prescriptionList.get(position).getMedicationId());    //TODO get medication name
          holder.tvPrescriptionDetail.setText(prescriptionList.get(position).getDetail());
          holder.appCompatCheckBox.setChecked(prescriptionList.get(position).getPrescribed());
        }
      }
    }

    @Override
    public int getItemCount() {
      Log.d("qqq370", String.valueOf(prescriptionList.size()));
      return prescriptionList.size();
    }
  }
}
