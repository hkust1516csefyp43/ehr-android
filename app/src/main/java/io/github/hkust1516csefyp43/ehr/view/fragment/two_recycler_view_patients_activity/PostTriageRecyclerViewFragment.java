package io.github.hkust1516csefyp43.ehr.view.fragment.two_recycler_view_patients_activity;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jpardogo.android.googleprogressbar.library.GoogleProgressBar;
import com.melnykov.fab.FloatingActionButton;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;

import java.util.List;

import io.github.hkust1516csefyp43.ehr.R;
import io.github.hkust1516csefyp43.ehr.adapter.PatientCardRecyclerViewAdapter;
import io.github.hkust1516csefyp43.ehr.apiEndpointInterface;
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
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView rv;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public PostTriageRecyclerViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PostTriageRecyclerViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PostTriageRecyclerViewFragment newInstance(String param1, String param2) {
        PostTriageRecyclerViewFragment fragment = new PostTriageRecyclerViewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recycler_view, container, false);
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
        try {
            mListener = (OnFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        //Attach FAB to Recycler View to enable auto hide
        rv = (RecyclerView) getView().findViewById(R.id.recyclerView);
        final RelativeLayout rl = (RelativeLayout) getView().findViewById(R.id.everything_else);
        final GoogleProgressBar gpb = (GoogleProgressBar) getView().findViewById(R.id.google_progress);
        final FloatingActionButton fab = (FloatingActionButton) getView().findViewById(R.id.floatingactionbutton);
        final TextView fail = (TextView) getView().findViewById(R.id.fail);
        final Context c = getContext();

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Const.API_HEROKU).addConverterFactory(GsonConverterFactory.create(gson)).build();
        apiEndpointInterface apiService = retrofit.create(apiEndpointInterface.class);
        Call<List<Patient>> call2 = apiService.getPatients("MiWTgwpjRYN0gtFixCTioZa1ll2V5CGRk6ioXIK14P51CKcdUpJVgEr2hB8MjAT4peyRCmluMn2ogVFasH7UE6Z1KPCDjCYgAIVqwJPw85TFDNxUH4majmhfMKFCLOJvwW7PY7a1YnaLlyFvmK4QJJw4fsc9bFakMmQc7Aq0aLyfPtquUXRYUl9CuXdU2mcsgyFDY2TnduSANqkLSoYZfmwKle7OCmhHS6ZXpL2pKXHYR0zpj5AkebNBINDtb6v", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
        call2.enqueue(new Callback<List<Patient>>() {
            @Override
            public void onResponse(Response<List<Patient>> response, Retrofit retrofit) {
                Cache.setPostTriagePatients(response.body());
                changeTabCounter(response.body().size());

                if (rv != null && c != null) {
                    rl.setVisibility(View.VISIBLE);
                    gpb.setVisibility(View.GONE);
                    fab.setImageDrawable(new IconicsDrawable(c, GoogleMaterial.Icon.gmd_add).color(Color.WHITE).sizeDp(16));
                    fab.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            newPatient();
                        }
                    });
                    LinearLayoutManager lm = new LinearLayoutManager(c);
                    lm.setOrientation(LinearLayoutManager.VERTICAL);
                    rv.setLayoutManager(lm);
                    rv.setAdapter(new PatientCardRecyclerViewAdapter(c));
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("qqq19", t.toString());
                fail.setVisibility(View.VISIBLE);
                gpb.setVisibility(View.GONE);
                rl.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void newPatient() {
        Intent intent = new Intent(this.getContext(), PatientVisitActivity.class);
        startActivity(intent);
    }

    public void inflateRV(Context c) {
        LinearLayoutManager lm = new LinearLayoutManager(c);
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(lm);
        rv.setAdapter(new PatientCardRecyclerViewAdapter(c));
    }

    public void refresh() {
        //clear cache
        //loading become visible
        //wait for response
    }

    public void changeTabCounter(int count) {
        Uri size = Uri.parse("0" + getString(R.string.uri_separator) + count);
        mListener.onFragmentInteraction(size);
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
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}