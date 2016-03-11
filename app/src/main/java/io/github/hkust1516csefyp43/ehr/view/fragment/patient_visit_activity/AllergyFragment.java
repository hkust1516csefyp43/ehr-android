package io.github.hkust1516csefyp43.ehr.view.fragment.patient_visit_activity;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;

import java.util.ArrayList;
import java.util.List;

import io.github.hkust1516csefyp43.ehr.R;
import io.github.hkust1516csefyp43.ehr.adapter.FragRecyclerViewAdapter;
import io.github.hkust1516csefyp43.ehr.listener.OnFragmentInteractionListener;
import io.github.hkust1516csefyp43.ehr.pojo.patient_visit.CardFrag;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AllergyFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AllergyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AllergyFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private RecyclerView rv;
    private List<CardFrag> allergy;

    private FloatingActionButton fab;

    public AllergyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AllergyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AllergyFragment newInstance(String param1, String param2) {
        AllergyFragment fragment = new AllergyFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_allergy, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();

        allergy = new ArrayList<>();
        allergy.add(0, new CardFrag("milk", "serious"));
        allergy.add(1, new CardFrag("beef", "little"));
        allergy.add(2, new CardFrag("fish", "deadly"));
        allergy.add(3, new CardFrag("egg", "none"));
        allergy.add(4, new CardFrag("cat", "some"));
        allergy.add(5, new CardFrag("water", "many"));
        allergy.add(6, new CardFrag("unknown", "few"));

        rv = (RecyclerView) getView().findViewById(R.id.rv_fab);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rv.setAdapter(new FragRecyclerViewAdapter(allergy, getContext()));
        fab = (FloatingActionButton) getActivity().findViewById(R.id.floatingactionbutton);
        fab.setImageDrawable(new IconicsDrawable(getContext(), GoogleMaterial.Icon.gmd_add).color(Color.WHITE).paddingDp(3).sizeDp(16));
        // TODO: setOnClickListener
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
