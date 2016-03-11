package io.github.hkust1516csefyp43.ehr.view.fragment.two_recycler_view_patients_activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.okhttp.OkHttpClient;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.github.hkust1516csefyp43.ehr.R;
import io.github.hkust1516csefyp43.ehr.adapter.PatientCardRecyclerViewAdapter;
import io.github.hkust1516csefyp43.ehr.apiEndpointInterface;
import io.github.hkust1516csefyp43.ehr.listener.ListCounterChangedListener;
import io.github.hkust1516csefyp43.ehr.listener.OnFragmentInteractionListener;
import io.github.hkust1516csefyp43.ehr.pojo.Patient;
import io.github.hkust1516csefyp43.ehr.value.Cache;
import io.github.hkust1516csefyp43.ehr.value.Const;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class PostTriageRecyclerViewFragment extends android.support.v4.app.Fragment {
    private static int station = 0;
    private int times = 0;
    // TODO: Rename parameter arguments, choose names that match
    private RecyclerView rv;
//    private SwipeRefreshLayout srl;
    private TextView fail;
    private OnFragmentInteractionListener mListener;
    private ListCounterChangedListener lListener;
    private int whichOne;

    public PostTriageRecyclerViewFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static PostTriageRecyclerViewFragment newInstance() {
        return new PostTriageRecyclerViewFragment();
    }

    public static PostTriageRecyclerViewFragment newInstance(int which) {
        PostTriageRecyclerViewFragment ptrvf = new PostTriageRecyclerViewFragment();
        Bundle args = new Bundle();
        args.putInt(Const.EXTRA_WHICH_ONE, which);
        ptrvf.setArguments(args);
        return ptrvf;
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
        if (getArguments() != null) {
            whichOne = getArguments().getInt(Const.EXTRA_WHICH_ONE);
            Log.d("qqq42", "which one = " + whichOne);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("qqq43", "onCreateView");
        View rootView = inflater.inflate(R.layout.fragment_recycler_view, container, false);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("qqq44", "onResume");
        final Context c = getContext();
        View rootView = getView();
        if (rootView != null) {
            if (rv == null)
                rv = (RecyclerView) rootView.findViewById(R.id.recyclerView);
//            if (srl == null)
//                srl = (SwipeRefreshLayout) rootView.findViewById(R.id.swiperefreshlayout);
            if (fail == null)
                fail = (TextView) rootView.findViewById(R.id.fail);
            fail.setVisibility(View.GONE);
            rv.setLayoutManager(new LinearLayoutManager(c, LinearLayoutManager.VERTICAL, false));
            rv.setAdapter(new PatientCardRecyclerViewAdapter(c));
            Log.d("qqq", "rv lm and a all set");
        }

        OkHttpClient ohc1 = new OkHttpClient();
        ohc1.setReadTimeout(1, TimeUnit.MINUTES);
        ohc1.setConnectTimeout(1, TimeUnit.MINUTES);

        OkHttpClient ohc15 = new OkHttpClient();
        ohc15.setReadTimeout(15, TimeUnit.MINUTES);
        ohc15.setConnectTimeout(15, TimeUnit.MINUTES);

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(Const.API_ONE2ONE_HEROKU)
                .addConverterFactory(GsonConverterFactory.create(Const.gson1))
                .client(ohc1)
                .build();
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
                Log.d("qqq27", "receives nothing/error");
                failing(t);
            }
        };

//        srl.setOnRefreshListener(new OnRefreshListener() {
//            @Override
//            public void onRefresh() {
        refreshUI(call2, cb);
//            }
//        });

//        srl.post(new Runnable() {
//            @Override
//            public void run() {
//                if (!srl.isRefreshing()) {
//                    Log.d("qqq28", "set refresh to true: " + times);
//                    times++;
//                    srl.setRefreshing(true);
//                    refreshUI(call2, cb);
//                }
//            }
//        });
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

//            if (rv != null && c != null && srl != null) {
            if (rv != null && c != null) {
                rv.getAdapter().notifyDataSetChanged();
                fail.setVisibility(View.GONE);
                rv.setVisibility(View.VISIBLE);
//                srl.setVisibility(View.VISIBLE);
                Log.d("qqq25", "" + response.body().toString());
                Log.d("qqq23", "rv/srl not showing occasionally");
            } else {
                Log.d("qqq23", "sth wrong");
            }
        } else {
            //TODO receives nothing
            Log.d("qqq", "error");
            changeTabCounter(-1);
            failing(new Throwable("Fetched nothing"));
        }
    }

    private void failing(Throwable t) {
        Log.d("qqq19", "failing");
        changeTabCounter(0);
        fail.setText(t.getMessage());
        fail.setVisibility(View.VISIBLE);
//        srl.clearAnimation();
//        srl.setRefreshing(false);
//        srl.setVisibility(View.GONE);
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
}
