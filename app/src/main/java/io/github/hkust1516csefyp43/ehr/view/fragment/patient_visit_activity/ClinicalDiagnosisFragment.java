package io.github.hkust1516csefyp43.ehr.view.fragment.patient_visit_activity;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;

import java.util.ArrayList;

import io.github.hkust1516csefyp43.ehr.R;
import io.github.hkust1516csefyp43.ehr.adapter.FragRecyclerViewAdapter;
import io.github.hkust1516csefyp43.ehr.listener.OnFragmentInteractionListener;
import io.github.hkust1516csefyp43.ehr.pojo.patient_visit.Card;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ClinicalDiagnosisFragment...OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ClinicalDiagnosisFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClinicalDiagnosisFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private RecyclerView rv;
    private ArrayList<Card> diagnosis;

    private FloatingActionButton fab;

    public ClinicalDiagnosisFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PregnancyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ClinicalDiagnosisFragment newInstance(String param1, String param2) {
        ClinicalDiagnosisFragment fragment = new ClinicalDiagnosisFragment();
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
        final Context contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.AppTheme2);
        LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);
        View v = localInflater.inflate(R.layout.fragment_clinical_diagnosis, container, false);
        rv = (RecyclerView) v.findViewById(R.id.recycler_view);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        fab = (FloatingActionButton) v.findViewById(R.id.floatingactionbutton);
        fab.setImageDrawable(new IconicsDrawable(getContext(), GoogleMaterial.Icon.gmd_add).color(Color.WHITE).paddingDp(3).sizeDp(16));
        // TODO: setOnClickListener
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

        diagnosis = new ArrayList<>();
        diagnosis.add(0, new Card("diagnosis 1", "great"));
        diagnosis.add(1, new Card("diagnosis 2", "poor"));
        diagnosis.add(2, new Card("diagnosis 3", "quite good"));
        diagnosis.add(3, new Card("diagnosis 4", "no comment"));
        diagnosis.add(4, new Card("diagnosis 5", "healthy"));
        diagnosis.add(5, new Card("diagnosis 6", "die soon"));
        diagnosis.add(6, new Card("diagnosis 7", "unhealthy"));

        rv.setAdapter(new FragRecyclerViewAdapter(diagnosis, getContext()));
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
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
