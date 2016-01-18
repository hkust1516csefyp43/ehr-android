package io.github.hkust1516csefyp43.ehr.view.fragment.two_recycler_view_patients_activity;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
    private int times = 0;
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
        Log.d("qqq41", "onAttach");
        try {
            mListener = (OnFragmentInteractionListener) context;
            lListener = (ListCounterChangedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("qqq42", "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("qqq43", "onCreateView");
        View rootView = inflater.inflate(R.layout.fragment_recycler_view, container, false);
        final Context c = getContext();
        if (rootView != null) {
            if (rv == null)
                rv = (RecyclerView) rootView.findViewById(R.id.recyclerView);
            if (srl == null)
                srl = (SwipeRefreshLayout) rootView.findViewById(R.id.swiperefreshlayout);
            if (fail == null)
                fail = (TextView) rootView.findViewById(R.id.fail);
            fail.setVisibility(View.GONE);
            rv.setLayoutManager(new LinearLayoutManager(c, LinearLayoutManager.VERTICAL, false));
            rv.setAdapter(new PatientCardRecyclerViewAdapter(c));
        }

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Const.API_HEROKU).addConverterFactory(GsonConverterFactory.create(Const.gson1)).build();
        apiEndpointInterface apiService = retrofit.create(apiEndpointInterface.class);
        //TODO only get patients where next_station = consultation
        //TODO different Call depending on the station variable >>1/2/3
        final Call<List<Patient>> call2 = apiService.getPatients("hihi", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
        final Callback<List<Patient>> cb = new Callback<List<Patient>>() {
            @Override
            public void onResponse(Response<List<Patient>> response, Retrofit retrofit) {
                Log.d("qqq27", "receiving: " + response.code() + " " + response.message());
                if (response.code() >= 500 && response.code() < 600)
                    onFailure(new Throwable(response.code() + "/" + response.message()));
                else
                    receiving(c, response);
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("qqq27", "receives nothing");
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
                if (!srl.isRefreshing()) {
                    Log.d("qqq28", "set refresh to true: " + times);
                    times++;
                    srl.setRefreshing(true);
                    refreshUI(call2, cb);
                }
            }
        });
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("qqq44", "onResume");
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
        if (response != null && response.body() != null && response.body().size() > 0) {
            Cache.setPostTriagePatients(c, response.body());
            changeTabCounter(response.body().size());

            if (rv != null && c != null && srl != null) {
                srl.clearAnimation();
                srl.setRefreshing(false);
                rv.getAdapter().notifyDataSetChanged();
                fail.setVisibility(View.GONE);
                rv.setVisibility(View.VISIBLE);
                srl.setVisibility(View.VISIBLE);
                Log.d("qqq25", "" + response.body().toString());
                Log.d("qqq23", "rv/srl not showing occationally");
            } else {
                Log.d("qqq23", "sth wrong");
            }
        } else {
            //TODO receives nothing
            Log.d("qqq", "error");
            failing(new Throwable("Fetched nothing"));
        }
    }

    private void failing(Throwable t) {
        Log.d("qqq19", "failing");
        changeTabCounter(0);
        fail.setText(t.getMessage());
        fail.setVisibility(View.VISIBLE);
        srl.clearAnimation();
        srl.setRefreshing(false);
        srl.setVisibility(View.GONE);
        rv.setVisibility(View.GONE);
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

    public void scrollToTop() {
        rv.getLayoutManager().scrollToPosition(0);
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
