package io.github.hkust1516csefyp43.easymed.view.fragment.patient_visit_view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.squareup.okhttp.OkHttpClient;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.github.hkust1516csefyp43.easymed.R;
import io.github.hkust1516csefyp43.easymed.listener.OnFragmentInteractionListener;
import io.github.hkust1516csefyp43.easymed.pojo.Consultation;
import io.github.hkust1516csefyp43.easymed.pojo.Triage;
import io.github.hkust1516csefyp43.easymed.pojo.Visit;
import io.github.hkust1516csefyp43.easymed.utility.Const;
import io.github.hkust1516csefyp43.easymed.utility.v2API;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class VisitDetailFragment extends Fragment {
  public final static String TAG = VisitDetailFragment.class.getSimpleName();

  private static String key1 = Const.BundleKey.VISIT_ID;
  private static String key2 = Const.BundleKey.ON_OR_OFF;
  private OnFragmentInteractionListener mListener;

  private FloatingActionButton floatingActionButton;

  /**
   * Difference between this fabOn and the fabOn in PatientVisitViewActivity (PVVA): the code starts
   * PVVA, and the PVVA will be passed into this. However, if for some reason PVVA does not pass
   * fabOn, this would still works.
   * TODO what should the default be?
   */
  private Boolean fabOn = false;

  private Visit visit;
  private Triage triage;
  private Consultation consultation;
  //TODO Pharmacy POJO
  private LinearLayout linearLayout;


  public static VisitDetailFragment newInstance(Visit visit, Boolean fabOn) {
    VisitDetailFragment fragment = new VisitDetailFragment();
    Bundle args = new Bundle();
    args.putSerializable(key1, visit);
    args.putBoolean(key2, fabOn);
    fragment.setArguments(args);
    return fragment;
  }

  public VisitDetailFragment() {
    // Required empty public constructor
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      Serializable serializable = getArguments().getSerializable(key1);
      if (serializable instanceof Visit) {
        visit = (Visit) serializable;
      }
      fabOn = getArguments().getBoolean(key2);
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_visit_detail, container, false);
    floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab);

    if (!fabOn) {
      floatingActionButton.setVisibility(View.GONE);
    } else {
      floatingActionButton.setImageDrawable(new IconicsDrawable(getContext()).icon(GoogleMaterial.Icon.gmd_edit).actionBar().color(Color.WHITE));
    }
    linearLayout = (LinearLayout) view.findViewById(R.id.ll_visit_info);
    if (linearLayout != null){

      //Triage
      OkHttpClient ohc1 = new OkHttpClient();
      ohc1.setReadTimeout(1, TimeUnit.MINUTES);
      ohc1.setConnectTimeout(1, TimeUnit.MINUTES);
      Retrofit retrofit = new Retrofit
          .Builder()
          .baseUrl(Const.Database.CLOUD_API_BASE_URL_121_dev)
          .addConverterFactory(GsonConverterFactory.create(Const.GsonParserThatWorksWithPGTimestamp))
          .client(ohc1)
          .build();
      v2API.triages triages = retrofit.create(v2API.triages.class);
      Call<List<Triage>> triageCall = triages.getTriages("1", visit.getId());
      triageCall.enqueue(new Callback<List<Triage>>() {
        @Override
        public void onResponse(Response<List<Triage>> response, Retrofit retrofit) {
          Log.d(TAG, visit.getId());
          Log.d(TAG, response.body().toString());
          if (response.body() != null && response.body().size() != 0){
            triage = response.body().get(0);
            if (triage != null){
              TextView tvTriageTitle = new TextView(getContext());
              tvTriageTitle.setText("Triage");
              tvTriageTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
              tvTriageTitle.setTextColor(Color.BLACK);
              tvTriageTitle.setTypeface(null, Typeface.BOLD);
              linearLayout.addView(tvTriageTitle);

              if (triage.getUserId() != null){
                TextView tvTriageUserId = new TextView(getContext());
                tvTriageUserId.setText("User ID: " + triage.getUserId());
                linearLayout.addView(tvTriageUserId);
              }
              if (triage.getId() != null){
                TextView tvTriageId = new TextView(getContext());
                tvTriageId.setText("Triage ID: " + triage.getId());
                linearLayout.addView(tvTriageId);
              }
              if (triage.getVisitId() != null){
                TextView tvTriageVisitId = new TextView(getContext());
                tvTriageVisitId.setText("Visit ID: " + triage.getVisitId());
                linearLayout.addView(tvTriageVisitId);
              }
              if (triage.getChiefComplains() != null){
                TextView tvTriageCC = new TextView(getContext());
                tvTriageCC.setText("Chief complaint: " + triage.getChiefComplains());
                linearLayout.addView(tvTriageCC);
              }
              if (triage.getHeartRate() != null){
                TextView tvTriageHeartRate = new TextView(getContext());
                tvTriageHeartRate.setText("Heart rate: " + triage.getHeartRate());
                linearLayout.addView(tvTriageHeartRate);
              }
              if (triage.getHeight() != null){
                TextView tvTriageHeight = new TextView(getContext());
                tvTriageHeight.setText("Height: " + String.valueOf(triage.getHeight()));
                linearLayout.addView(tvTriageHeight);
              }
              if (triage.getWeight() != null){
                TextView tvTriageWeight = new TextView(getContext());
                tvTriageWeight.setText("Weight: " + String.valueOf(triage.getWeight()));
                linearLayout.addView(tvTriageWeight);
              }
              if (triage.getRemark() != null){
                TextView tvTriageRemark = new TextView(getContext());
                tvTriageRemark.setText("Remark: " + triage.getRemark());
                linearLayout.addView(tvTriageRemark);
              }
            }
          }else onFailure(null);

        }

        @Override
        public void onFailure(Throwable t) {

        }
      });

      //Consultation
      OkHttpClient ohc2 = new OkHttpClient();
      ohc1.setReadTimeout(1, TimeUnit.MINUTES);
      ohc1.setConnectTimeout(1, TimeUnit.MINUTES);
      Retrofit retrofit2 = new Retrofit
          .Builder()
          .baseUrl(Const.Database.CLOUD_API_BASE_URL_121_dev)
          .addConverterFactory(GsonConverterFactory.create(Const.GsonParserThatWorksWithPGTimestamp))
          .client(ohc2)
          .build();
      final v2API.consultations consultations = retrofit2.create(v2API.consultations.class);
      final Call<List<Consultation>> consultationCall = consultations.getConsultations("1", visit.getId());
      consultationCall.enqueue(new Callback<List<Consultation>>() {
        @Override
        public void onResponse(Response<List<Consultation>> response, Retrofit retrofit) {
          Log.d(TAG, visit.getId());
          Log.d(TAG, response.body().toString());
          if (response.body() != null && response.body().size() != 0){
            consultation = response.body().get(0);
            if (consultation != null){
              TextView tvConsultationTitle = new TextView(getContext());
              tvConsultationTitle.setText("Consultation");
              tvConsultationTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
              tvConsultationTitle.setTextColor(Color.BLACK);
              tvConsultationTitle.setTypeface(null, Typeface.BOLD);
              linearLayout.addView(tvConsultationTitle);

              if (consultation.getUserId() != null){
                TextView tvConsultationUserId = new TextView(getContext());
                tvConsultationUserId.setText("User ID: " + consultation.getUserId());
                linearLayout.addView(tvConsultationUserId);
              }
              if (consultation.getId() != null){
                TextView tvConsultationId = new TextView(getContext());
                tvConsultationId.setText("Consultation ID: " + consultation.getId());
                linearLayout.addView(tvConsultationId);
              }
              if (consultation.getVisitId() != null){
                TextView tvConsultationVisitId = new TextView(getContext());
                tvConsultationVisitId.setText("Visit ID: " + consultation.getVisitId());
                linearLayout.addView(tvConsultationVisitId);
              }
//              if (triage.getHeight() != null){
//                TextView textView4 = new TextView(getContext());
//                textView4.setText(String.valueOf(triage.getHeight()));
//                linearLayout.addView(textView4);
//              }
//              if (triage.getRemark() != null){
//                TextView textView5 = new TextView(getContext());
//                textView5.setText(triage.getRemark());
//                linearLayout.addView(textView5);
//              }
//              if (triage.getChiefComplains() != null){
//                TextView textView7 = new TextView(getContext());
//                textView7.setText(triage.getChiefComplains());
//                linearLayout.addView(textView7);
//              }
            }
          }else onFailure(null);

        }

        @Override
        public void onFailure(Throwable t) {

        }
      });
    }


    return view;
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    if (context instanceof OnFragmentInteractionListener) {
      mListener = (OnFragmentInteractionListener) context;
    } else {
      throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
    }
  }

  @Override
  public void onDetach() {
    super.onDetach();
    mListener = null;
  }
}
