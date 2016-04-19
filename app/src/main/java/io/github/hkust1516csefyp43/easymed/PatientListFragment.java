package io.github.hkust1516csefyp43.easymed;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.okhttp.OkHttpClient;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.github.hkust1516csefyp43.easymed.POJO.Patient;
import io.github.hkust1516csefyp43.easymed.listener.OnFragmentInteractionListener;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class PatientListFragment extends Fragment {
  public static final String TAG = PatientListFragment.class.getSimpleName();
  private List<Patient> patients;
  private OnFragmentInteractionListener mListener;
  private RecyclerView recyclerView;
  private ProgressBar progressBar;

  public static PatientListFragment newInstance(String param1, String param2) {
    PatientListFragment fragment = new PatientListFragment();
    return fragment;
  }

  public PatientListFragment() {
    // Required empty public constructor
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_patient_list, container, false);
    recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
    progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
    recyclerView.setVisibility(View.GONE);
    progressBar.setVisibility(View.VISIBLE);
    return view;
  }

  @Override
  public void onResume() {
    super.onResume();

    OkHttpClient ohc1 = new OkHttpClient();
    ohc1.setReadTimeout(1, TimeUnit.MINUTES);
    ohc1.setConnectTimeout(1, TimeUnit.MINUTES);
    Retrofit retrofit = new Retrofit
        .Builder()
        .baseUrl(Const.Database.CLOUD_API_BASE_URL_121_dev)
        .addConverterFactory(GsonConverterFactory.create(Const.GsonParserThatWorksWithPGTimestamp))
        .client(ohc1)
        .build();
    v2API.patients patientService = retrofit.create(v2API.patients.class);
    Call<List<Patient>> patientList = patientService.getPatients("1");
    patientList.enqueue(new Callback<List<Patient>>() {
      @Override
      public void onResponse(Response<List<Patient>> response, Retrofit retrofit) {
        Log.d(TAG, response.toString());
        Log.d(TAG, response.body().toString());
        patients = response.body();
        recyclerView.setAdapter(new PatientRecyclerViewAdapter());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
      }

      @Override
      public void onFailure(Throwable t) {

      }
    });
  }

  // TODO: Rename method, update argument and hook method into UI event
  public void onButtonPressed(Uri uri) {
    if (mListener != null) {
      mListener.onFragmentInteraction(uri);
    }
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

  private class PatientRecyclerViewViewHolder extends RecyclerView.ViewHolder {
    public TextView patientName;
    public TextView subtitle;
    public TextView nativeName;
    public ImageView proPic;

    public PatientRecyclerViewViewHolder(View itemView) {
      super(itemView);
      patientName = (TextView) itemView.findViewById(R.id.tvPatientName);
      subtitle = (TextView) itemView.findViewById(R.id.tvSubtitle);
      nativeName = (TextView) itemView.findViewById(R.id.tvPatientNativeName);
      proPic = (ImageView) itemView.findViewById(R.id.ivPatientPic);
    }
  }

  private class PatientRecyclerViewAdapter extends RecyclerView.Adapter<PatientRecyclerViewViewHolder> {

    public PatientRecyclerViewAdapter() {

    }

    @Override
    public PatientRecyclerViewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      return new PatientRecyclerViewViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_patient, parent, false));
    }

    @Override
    public void onBindViewHolder(PatientRecyclerViewViewHolder holder, int position) {
      Patient aPatient = patients.get(position);
      StringBuilder name = new StringBuilder();
      if (aPatient.getTag() != null) {
        name.append(aPatient.getTag().toString());
        name.append(". ");
      }
      if (aPatient.getLastName() != null) {
        name.append(aPatient.getLastName());
        name.append(" ");
      }
      name.append(aPatient.getFirstName());
      holder.patientName.setText(name.toString());
      holder.nativeName.setText(aPatient.getNativeName());
    }

    @Override
    public int getItemCount() {
      if (patients != null)
        return patients.size();
      else
        return 0;
    }
  }

}
