package io.github.hkust1516csefyp43.easymed;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
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

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.github.hkust1516csefyp43.easymed.POJO.Patient;
import io.github.hkust1516csefyp43.easymed.listener.OnFragmentInteractionListener;
import io.github.hkust1516csefyp43.easymed.listener.OnPatientsFetchedListener;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class PatientListFragment extends Fragment{
  public static final String TAG = PatientListFragment.class.getSimpleName();

  private List<Patient> patients;

  private int whichPage;

  private OnFragmentInteractionListener mListener;
  private OnPatientsFetchedListener numberListener;

  private RecyclerView recyclerView;
  private ProgressBar progressBar;
  private SwipeRefreshLayout swipeRefreshLayout;

  public static PatientListFragment newInstance(int whichPage) {
    PatientListFragment fragment = new PatientListFragment();
    Bundle extra = new Bundle();
    extra.putInt(Const.BundleKey.WHICH_PATIENT_LIST_ID, whichPage);
    fragment.setArguments(extra);
    return fragment;
  }

  public PatientListFragment() {
    // Required empty public constructor
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Bundle bundle = this.getArguments();
    if (bundle != null) {
      whichPage = bundle.getInt(Const.BundleKey.WHICH_PATIENT_LIST_ID);
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_patient_list, container, false);
    recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
    progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
    swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.srl);
    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override
      public void onRefresh() {
        onResume();
      }
    });
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

    switch (whichPage) {
      case Const.PatientListPageId.POST_TRIAGE:   // == PRE_CONSULTATION
        Call<List<Patient>> patientList = patientService.getPatients("1", null, "2", null, null, null, null, null, null, null, null);
        patientList.enqueue(new Callback<List<Patient>>() {
          @Override
          public void onResponse(Response<List<Patient>> response, Retrofit retrofit) {
            Log.d(TAG, response.body().toString());
            patients = response.body();
            Collections.sort(patients);
            if (numberListener != null) {
              Log.d(TAG, "post triage counter update triggered: " + patients.size());
              numberListener.updateTabTitleCounter(whichPage, patients.size());
            }
            recyclerView.setAdapter(new PatientRecyclerViewAdapter());
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            if (swipeRefreshLayout != null) {
              swipeRefreshLayout.setRefreshing(false);
            }
            recyclerView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
          }

          @Override
          public void onFailure(Throwable t) {

          }
        });
        break;
      case Const.PatientListPageId.NOT_YET:
        Call<List<Patient>> patientList2 = patientService.getPatients("1", null, null, null, null, null, null, null, null, null, null);
        patientList2.enqueue(new Callback<List<Patient>>() {
          @Override
          public void onResponse(Response<List<Patient>> response, Retrofit retrofit) {
            Log.d(TAG, response.body().toString());
            patients = response.body();
            if (numberListener != null) {
              Log.d(TAG, "not yet counter update triggered: " + patients.size());
              numberListener.updateTabTitleCounter(whichPage, patients.size());
            }
            recyclerView.setAdapter(new PatientRecyclerViewAdapter());
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            if (swipeRefreshLayout != null) {
              swipeRefreshLayout.setRefreshing(false);
            }
            recyclerView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
          }

          @Override
          public void onFailure(Throwable t) {

          }
        });
        break;
      case Const.PatientListPageId.POST_CONSULTATION:   // == PRE_PHARMACY
        Call<List<Patient>> patientList3 = patientService.getPatients("1", null, "3", null, null, null, null, null, null, null, null);
        patientList3.enqueue(new Callback<List<Patient>>() {
          @Override
          public void onResponse(Response<List<Patient>> response, Retrofit retrofit) {
            Log.d(TAG, response.body().toString());
            patients = response.body();
            if (numberListener != null) {
              Log.d(TAG, "not yet counter update triggered: " + patients.size());
              numberListener.updateTabTitleCounter(whichPage, patients.size());
            }
            recyclerView.setAdapter(new PatientRecyclerViewAdapter());
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            if (swipeRefreshLayout != null) {
              swipeRefreshLayout.setRefreshing(false);
            }
            recyclerView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
          }

          @Override
          public void onFailure(Throwable t) {

          }
        });
        break;
      case Const.PatientListPageId.POST_PHARMACY:
        Call<List<Patient>> patientList4 = patientService.getPatients("1", null, "1", null, null, null, null, null, null, null, null);
        patientList4.enqueue(new Callback<List<Patient>>() {
          @Override
          public void onResponse(Response<List<Patient>> response, Retrofit retrofit) {
            Log.d(TAG, response.body().toString());
            patients = response.body();
            if (numberListener != null) {
              Log.d(TAG, "not yet counter update triggered: " + patients.size());
              numberListener.updateTabTitleCounter(whichPage, patients.size());
            }
            recyclerView.setAdapter(new PatientRecyclerViewAdapter());
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            if (swipeRefreshLayout != null) {
              swipeRefreshLayout.setRefreshing(false);
            }
            recyclerView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
          }

          @Override
          public void onFailure(Throwable t) {

          }
        });
        break;
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
    if (context instanceof OnPatientsFetchedListener) {
      Log.d(TAG, "OPFL attached");
      numberListener = (OnPatientsFetchedListener) context;
    } //TODO else idk
  }

  @Override
  public void onDetach() {
    super.onDetach();
    mListener = null;
  }

  private class PatientRecyclerViewViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public CardView theWholeCard;
    public TextView patientName;
    public TextView subtitle;
    public TextView nativeName;
    public ImageView proPic;

    public PatientRecyclerViewViewHolder(View itemView) {
      super(itemView);
      theWholeCard = (CardView) itemView.findViewById(R.id.cardPatient);
      theWholeCard.setOnClickListener(this);
      patientName = (TextView) itemView.findViewById(R.id.tvPatientName);
      subtitle = (TextView) itemView.findViewById(R.id.tvSubtitle);
      nativeName = (TextView) itemView.findViewById(R.id.tvPatientNativeName);
      proPic = (ImageView) itemView.findViewById(R.id.ivPatientPic);
    }

    @Override
    public void onClick(View v) {
      //open patient visit
    }
  }

  private class PatientRecyclerViewAdapter extends RecyclerView.Adapter<PatientRecyclerViewViewHolder> {
    View.OnClickListener onClickListener;

    public PatientRecyclerViewAdapter() {

    }

    public PatientRecyclerViewAdapter(View.OnClickListener click) {
      onClickListener = click;
    }

    @Override
    public PatientRecyclerViewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      return new PatientRecyclerViewViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_patient, parent, false));
    }

    @Override
    public void onBindViewHolder(PatientRecyclerViewViewHolder holder, int position) {
      final Patient aPatient = patients.get(position);
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

      holder.theWholeCard.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          switch (whichPage) {
            case Const.PatientListPageId.POST_TRIAGE:
              //TODO edit patient
              Log.d(TAG, "going to edit patient " + aPatient.getFirstName());
              Intent intent = new Intent(getContext(), PatientVisitReadOnlyActivity.class);
              intent.putExtra(Const.BundleKey.READ_ONLY_PATIENT, aPatient);
              startActivity(intent);
              break;
            case Const.PatientListPageId.NOT_YET:
              Log.d(TAG, "going to ? patient " + aPatient.getFirstName());
              //TODO ?
              break;
            default:

          }
        }
      });

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
