package io.github.hkust1516csefyp43.easymed.view.fragment;

import android.content.Context;
import android.content.Intent;
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

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.github.hkust1516csefyp43.easymed.R;
import io.github.hkust1516csefyp43.easymed.listener.OnFragmentInteractionListener;
import io.github.hkust1516csefyp43.easymed.listener.OnPatientsFetchedListener;
import io.github.hkust1516csefyp43.easymed.pojo.server_response.Gender;
import io.github.hkust1516csefyp43.easymed.pojo.server_response.Patient;
import io.github.hkust1516csefyp43.easymed.utility.Const;
import io.github.hkust1516csefyp43.easymed.utility.Util;
import io.github.hkust1516csefyp43.easymed.utility.v2API;
import io.github.hkust1516csefyp43.easymed.view.activity.PatientVisitEditActivity;
import io.github.hkust1516csefyp43.easymed.view.activity.PatientVisitViewActivity;
import io.github.hkust1516csefyp43.easymed.view.activity.PharmacyActivity;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PatientListFragment extends Fragment{
  public static final String TAG = PatientListFragment.class.getSimpleName();

  private List<Patient> patients;

  private int whichPage;
  private boolean displayGender = true;
  private String nameSearchName;
  private HashMap<String, String> gendersHashMap = new HashMap<>();

  private OnFragmentInteractionListener mListener;
  private OnPatientsFetchedListener numberListener;

  private RecyclerView recyclerView;
  private ProgressBar progressBar;
  private SwipeRefreshLayout swipeRefreshLayout;

  public static PatientListFragment newInstance(int whichPage, String nameSearchString) {
    PatientListFragment fragment = new PatientListFragment();
    Bundle extra = new Bundle();
    extra.putInt(Const.BundleKey.WHICH_PATIENT_LIST_ID, whichPage);
    extra.putString(Const.BundleKey.NAME_SEARCH_NAME, nameSearchString);
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
      nameSearchName = bundle.getString(Const.BundleKey.NAME_SEARCH_NAME);
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

    OkHttpClient.Builder ohc1 = new OkHttpClient.Builder();
    ohc1.readTimeout(1, TimeUnit.MINUTES);
    ohc1.connectTimeout(1, TimeUnit.MINUTES);
    Retrofit retrofit = new Retrofit
        .Builder()
        .baseUrl(Const.Database.CLOUD_API_BASE_URL_121_dev)
        .addConverterFactory(GsonConverterFactory.create(Const.GsonParserThatWorksWithPGTimestamp))
        .client(ohc1.build())
        .build();

    v2API.genders genderService = retrofit.create(v2API.genders.class);
    Call<List<Gender>> gendersCall = genderService.getGenders("1");
    gendersCall.enqueue(new Callback<List<Gender>>() {
      @Override
      public void onResponse(Call<List<Gender>> call, Response<List<Gender>> response) {
        if (response != null) {
          if (response.body() != null) {
            if (response.body().size() > 0) {
              for (Gender g: response.body()) {
                gendersHashMap.put(g.getId(), g.getGender());
                proceed();
              }
            } else {
              //size is 0
              onFailure(null, new Throwable("size is 0"));
            }
          } else {
            //empty body
            onFailure(null, new Throwable("empty body"));
          }
        } else {
          //null response
          onFailure(null, new Throwable("null body"));
        }
      }

      @Override
      public void onFailure(Call<List<Gender>> call, Throwable t) {
        t.printStackTrace();
        displayGender = false;
        proceed();
      }
    });
  }

  private void proceed() {
    OkHttpClient.Builder ohc1 = new OkHttpClient.Builder();
    ohc1.readTimeout(1, TimeUnit.MINUTES);
    ohc1.connectTimeout(1, TimeUnit.MINUTES);
    Retrofit retrofit = new Retrofit
        .Builder()
        .baseUrl(Const.Database.CLOUD_API_BASE_URL_121_dev)
        .addConverterFactory(GsonConverterFactory.create(Const.GsonParserThatWorksWithPGTimestamp))
        .client(ohc1.build())
        .build();
    v2API.patients patientService = retrofit.create(v2API.patients.class);

    switch (whichPage) {
      case Const.PatientListPageId.POST_TRIAGE:
      case Const.PatientListPageId.PRE_CONSULTATION:
//        Call<List<Patient>> patientList = patientService.getPatients("1", null, "2", null, null, null, null, null, null, null, Util.todayString());         //set to today == nothing to see -_-
        Call<List<Patient>> patientList = patientService.getPatients("1", null, "2", null, null, null, null, null, null, null, null);
        patientList.enqueue(new Callback<List<Patient>>() {
          @Override
          public void onResponse(Call<List<Patient>> call, Response<List<Patient>> response) {

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
          public void onFailure(Call<List<Patient>> call, Throwable t) {

          }
        });
        break;
      case Const.PatientListPageId.NOT_YET:
        Call<List<Patient>> patientList2 = patientService.getPatients("1", null, null, null, null, null, null, null, null, null, null);
        patientList2.enqueue(new Callback<List<Patient>>() {
          @Override
          public void onResponse(Call<List<Patient>> call, Response<List<Patient>> response) {
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
          public void onFailure(Call<List<Patient>> call, Throwable t) {

          }
        });
        break;
      case Const.PatientListPageId.POST_CONSULTATION:
      case Const.PatientListPageId.PRE_PHARMACY:
//        Call<List<Patient>> patientList3 = patientService.getPatients("1", null, "3", null, null, null, null, null, null, null, Util.todayString());          //set to today == nothing to see -_-
        Call<List<Patient>> patientList3 = patientService.getPatients("1", null, "3", null, null, null, null, null, null, null, null);
        patientList3.enqueue(new Callback<List<Patient>>() {
          @Override
          public void onResponse(Call<List<Patient>> call, Response<List<Patient>> response) {
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
          public void onFailure(Call<List<Patient>> call, Throwable t) {
          }
        });
        break;
      case Const.PatientListPageId.POST_PHARMACY:
//        Call<List<Patient>> patientList4 = patientService.getPatients("1", null, "1", null, null, null, null, null, null, null, Util.todayString());          //set to today == nothing to see -_-
        Call<List<Patient>> patientList4 = patientService.getPatients("1", null, "1", null, null, null, null, null, null, null, null);
        patientList4.enqueue(new Callback<List<Patient>>() {
          @Override
          public void onResponse(Call<List<Patient>> call, Response<List<Patient>> response) {
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
          public void onFailure(Call<List<Patient>> call, Throwable t) {

          }
        });
        break;
      case Const.PatientListPageId.TRIAGE_SEARCH:
        if (nameSearchName != null) {
          Call<List<Patient>> patientCall = patientService.getPatients("1", null, null, null, null, null, null, null, null, nameSearchName, null);
          patientCall.enqueue(new Callback<List<Patient>>() {
            @Override
            public void onResponse(Call<List<Patient>> call, Response<List<Patient>> response) {
              if (response != null) {
                Log.d(TAG, response.body().toString());
                patients = response.body();
                recyclerView.setAdapter(new PatientRecyclerViewAdapter());
                recyclerView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
              } else {
                onFailure(call, new Throwable("empty response wtf"));
              }
            }

            @Override
            public void onFailure(Call<List<Patient>> call, Throwable t) {
              t.printStackTrace();
            }
          });
        } else {
          //TODO display nothing
        }
        break;
      case Const.PatientListPageId.CONSULTATION_SEARCH:
        if (nameSearchName != null) {
          Call<List<Patient>> patientCall = patientService.getPatients("1", null, null, null, null, null, null, null, null, nameSearchName, null);
          patientCall.enqueue(new Callback<List<Patient>>() {
            @Override
            public void onResponse(Call<List<Patient>> call, Response<List<Patient>> response) {
              if (response != null) {
                Log.d(TAG, response.body().toString());
                patients = response.body();
                recyclerView.setAdapter(new PatientRecyclerViewAdapter());
                recyclerView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
              } else {
                onFailure(call, new Throwable("empty response wtf"));
              }
            }

            @Override
            public void onFailure(Call<List<Patient>> call, Throwable t) {
              t.printStackTrace();
            }
          });
        } else {
          //TODO display nothing
        }
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
    public PatientRecyclerViewAdapter() {

    }

    @Override
    public PatientRecyclerViewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      return new PatientRecyclerViewViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_patient, parent, false));
    }

    @Override
    public void onBindViewHolder(PatientRecyclerViewViewHolder holder, int position) {
      if (position < patients.size()) {
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
        holder.proPic.setImageDrawable(TextDrawable.builder().buildRound(Util.getTextDrawableText(aPatient), ColorGenerator.MATERIAL.getColor(aPatient.getLastNameSpaceFirstName())));

        StringBuilder subtitle = new StringBuilder();
        if (aPatient.getGenderId() != null) {
          subtitle.append(gendersHashMap.get(aPatient.getGenderId()));
        }
        if (aPatient.getBirthYear() != null && aPatient.getBirthYear() != null && aPatient.getBirthYear() != null) {
//          Log.d(TAG, "here:" + subtitle.toString() + ":end; length: " + subtitle.toString().length());
          if (subtitle.toString().length() > 0) {
            subtitle.append(" / ");
          }
          subtitle.append(Util.birthdayToAgeString(aPatient.getBirthYear(), aPatient.getBirthMonth(), aPatient.getBirthDate()));
        }
        holder.subtitle.setText(subtitle.toString());

        holder.theWholeCard.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            switch (whichPage) {
              case Const.PatientListPageId.POST_TRIAGE:
                Log.d(TAG, "going to edit patient " + aPatient.getFirstName());
                Intent intent = new Intent(getContext(), PatientVisitViewActivity.class);
                intent.putExtra(Const.BundleKey.READ_ONLY_PATIENT, aPatient);
                intent.putExtra(Const.BundleKey.ON_OR_OFF, true);
                startActivity(intent);
                break;
              case Const.PatientListPageId.NOT_YET:
                Log.d(TAG, "going to edit patient " + aPatient.getFirstName());
                Intent intent5 = new Intent(getContext(), PatientVisitViewActivity.class);
                intent5.putExtra(Const.BundleKey.READ_ONLY_PATIENT, aPatient);
                intent5.putExtra(Const.BundleKey.ON_OR_OFF, false);
                startActivity(intent5);
                break;
              case Const.PatientListPageId.PRE_CONSULTATION:
                Intent intent1 = new Intent(getContext(), PatientVisitEditActivity.class);
                intent1.putExtra(Const.BundleKey.EDIT_PATIENT, aPatient);
                intent1.putExtra(Const.BundleKey.ON_OR_OFF, true);
                intent1.putExtra(Const.BundleKey.IS_TRIAGE, false);
                startActivity(intent1);
                break;
              case Const.PatientListPageId.POST_CONSULTATION:
                Intent intent2 = new Intent(getContext(), PatientVisitViewActivity.class);
                intent2.putExtra(Const.BundleKey.READ_ONLY_PATIENT, aPatient);
                intent2.putExtra(Const.BundleKey.ON_OR_OFF, true);
                startActivity(intent2);
                break;
              case Const.PatientListPageId.PRE_PHARMACY:
                Intent intent4 = new Intent(getContext(), PharmacyActivity.class);
                intent4.putExtra(Const.BundleKey.READ_ONLY_PATIENT, aPatient);
                startActivity(intent4);
                break;
              case Const.PatientListPageId.POST_PHARMACY:
              default:
                Intent intent3 = new Intent(getContext(), PatientVisitViewActivity.class);
                intent3.putExtra(Const.BundleKey.READ_ONLY_PATIENT, aPatient);
                startActivity(intent3);

            }
          }
        });
      }


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
