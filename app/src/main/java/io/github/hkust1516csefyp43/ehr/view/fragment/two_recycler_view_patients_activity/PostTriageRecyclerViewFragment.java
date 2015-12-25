package io.github.hkust1516csefyp43.ehr.view.fragment.two_recycler_view_patients_activity;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import io.github.hkust1516csefyp43.ehr.R;
import io.github.hkust1516csefyp43.ehr.adapter.PatientCardRecyclerViewAdapter;
import io.github.hkust1516csefyp43.ehr.apiEndpointInterface;
import io.github.hkust1516csefyp43.ehr.listener.ListCounterChangedListener;
import io.github.hkust1516csefyp43.ehr.pojo.Patient;
import io.github.hkust1516csefyp43.ehr.value.Cache;
import io.github.hkust1516csefyp43.ehr.value.Const;
import io.github.hkust1516csefyp43.ehr.view.activity.PatientVisitActivity;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PostTriageRecyclerViewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PostTriageRecyclerViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PostTriageRecyclerViewFragment extends android.support.v4.app.Fragment {
    private static int station = 0;
    // TODO: Rename parameter arguments, choose names that match
    private RecyclerView rv;
    private SwipeRefreshLayout srl;
    private TextView fail;
    private OnFragmentInteractionListener mListener;
    private ListCounterChangedListener lListener;

    public PostTriageRecyclerViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment PostTriageRecyclerViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PostTriageRecyclerViewFragment newInstance() {
        return new PostTriageRecyclerViewFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnFragmentInteractionListener) context;
            lListener = (ListCounterChangedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recycler_view, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getView() != null) {
            if (rv == null)
                rv = (RecyclerView) getView().findViewById(R.id.recyclerView);
            if (srl == null)
                srl = (SwipeRefreshLayout) getView().findViewById(R.id.swiperefreshlayout);
            if (fail == null)
                fail = (TextView) getView().findViewById(R.id.fail);
        }
        final Context c = getContext();
        fail.setVisibility(View.GONE);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Const.API_HEROKU).addConverterFactory(GsonConverterFactory.create(Const.gson1)).build();
        apiEndpointInterface apiService = retrofit.create(apiEndpointInterface.class);
        //TODO only get patients where next_station = consultation
        //TODO different Call depending on the station variable >>1/2/3
        final Call<List<Patient>> call2 = apiService.getPatients("MiWTgwpjRYN0gtFixCTioZa1ll2V5CGRk6ioXIK14P51CKcdUpJVgEr2hB8MjAT4peyRCmluMn2ogVFasH7UE6Z1KPCDjCYgAIVqwJPw85TFDNxUH4majmhfMKFCLOJvwW7PY7a1YnaLlyFvmK4QJJw4fsc9bFakMmQc7Aq0aLyfPtquUXRYUl9CuXdU2mcsgyFDY2TnduSANqkLSoYZfmwKle7OCmhHS6ZXpL2pKXHYR0zpj5AkebNBINDtb6v", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
        final Callback<List<Patient>> cb = new Callback<List<Patient>>() {
            @Override
            public void onResponse(Response<List<Patient>> response, Retrofit retrofit) {
                Log.d("qqq27", "receiving sth");
                receiving(c, response);
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("qqq27", "receiving nothing");
                failing(t);
            }
        };

        srl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshUI(call2, cb);
            }
        });

        srl.post(new Runnable() {
            @Override
            public void run() {
                Log.d("qqq28", "set refresh to true");
                srl.setRefreshing(true);
                refreshUI(call2, cb);
            }
        });
    }

    private void refreshUI(Call<List<Patient>> call2, Callback<List<Patient>> cb) {
        Log.d("qqq29", "refreshing");
        Call<List<Patient>> call = call2.clone();
        call.enqueue(cb);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        lListener = null;
    }

    private void receiving(Context c, Response<List<Patient>> response) {
        fail.setVisibility(View.GONE);
        Cache.setPostTriagePatients(c, response.body());
        changeTabCounter(response.body().size());

        if (rv != null && c != null) {
            srl.clearAnimation();
            srl.setRefreshing(false);
            LinearLayoutManager lm = new LinearLayoutManager(c);
            lm.setOrientation(LinearLayoutManager.VERTICAL);
            rv.setLayoutManager(lm);
            RecyclerView.Adapter rva = new PatientCardRecyclerViewAdapter(c);
            rv.setAdapter(rva);
            rv.getAdapter().notifyDataSetChanged();
            srl.setVisibility(View.VISIBLE);
            Log.d("qqq25", "" + response.body().toString());
            Log.d("qqq23", "not exactly nothing wrong");
        } else {
            Log.d("qqq23", "sth wrong");
        }
    }

    private void failing(Throwable t) {
        Log.d("qqq19", t.toString());
        changeTabCounter(0);
        fail.setVisibility(View.VISIBLE);
        srl.clearAnimation();
        srl.setRefreshing(false);
        srl.setVisibility(View.GONE);
    }

    /**
     * open new activity of patient. p == null >> new patient; else put it into extra
     *
     * @param p
     */
    public void openPatientVisit(Patient p) {
        Intent intent = new Intent(this.getContext(), PatientVisitActivity.class);
        if (p != null) {
            intent.putExtra("patient", p);
        }
        startActivity(intent);
    }

    /**
     * Signal TwoRecyclerViewPatientsActivity to change the number of patients on the tab
     * @param count = how many patients
     */
    public void changeTabCounter(int count) {
        if (lListener != null) {
            lListener.onCounterChangedListener(0, count);
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

}
