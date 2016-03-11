package io.github.hkust1516csefyp43.ehr.view.fragment.patient_visit_activity;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;

import java.util.ArrayList;
import java.util.List;

import io.github.hkust1516csefyp43.ehr.R;
import io.github.hkust1516csefyp43.ehr.adapter.FragRecyclerViewAdapter;
import io.github.hkust1516csefyp43.ehr.listener.OnFragmentInteractionListener;
import io.github.hkust1516csefyp43.ehr.pojo.patient_visit.Card;
import io.github.hkust1516csefyp43.ehr.value.Const;
import io.github.hkust1516csefyp43.ehr.view.custom_view.TwoEditTextDialogCustomView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListOfCardsFragment...OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListOfCardsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListOfCardsFragment extends Fragment {
    private OnFragmentInteractionListener mListener;
    private RecyclerView rv;
    private List<Card> disease;
    private FloatingActionButton fab;
    private String title;
    private FragRecyclerViewAdapter adapter;

    public ListOfCardsFragment() {
        // Required empty public constructor
    }

    public static ListOfCardsFragment newInstance(String title, String param2) {
        ListOfCardsFragment fragment = new ListOfCardsFragment();
        Bundle args = new Bundle();
        args.putString(Const.KEY_TITLE, title);
//        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(Const.KEY_TITLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final Context contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.AppTheme2);
        LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);
        View v = localInflater.inflate(R.layout.fragment_previous_medical_history, container, false);
        rv = (RecyclerView) v.findViewById(R.id.recycler_view);
        fab = (FloatingActionButton) v.findViewById(R.id.floatingactionbutton);
        fab.setImageDrawable(new IconicsDrawable(getContext(), GoogleMaterial.Icon.gmd_add).color(Color.WHITE).paddingDp(3).sizeDp(16));
        // TODO: setOnClickListener
        ArrayList<String> a = new ArrayList<>();
        a.add("a");
        a.add("ab");
        a.add("abc");
        a.add("abcd");
        a.add("abcde");
        a.add("abcdef");
        a.add("abcdefg");
        a.add("headache");
        a.add("head");
        a.add("leg");
        final TwoEditTextDialogCustomView tetdcv = new TwoEditTextDialogCustomView(getContext(), a, title);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean wrapInScrollView = true;
                new MaterialDialog.Builder(getContext())
                        .title("Add")
                        .customView(tetdcv, wrapInScrollView)
                        .positiveText("Confirm")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                ArrayList<String> data = tetdcv.getData();
                                Log.d("qqq141", data.toString());
                                adapter.addCard(new Card(data.get(0), data.get(1)));
                                adapter.notifyItemInserted(adapter.getCardCount() - 1);
                            }
                        })
                        .show();
            }
        });
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

        disease = new ArrayList<>();
        disease.add(0, new Card("heart disease", "very very very severe"));
        disease.add(1, new Card("diabetes", "die soon"));
        disease.add(2, new Card("insomnia", "feel unhappy"));
        disease.add(3, new Card("depression", "no comment"));
        disease.add(4, new Card("hot", "40 oC"));
        disease.add(5, new Card("cold", "30 oC"));
        disease.add(6, new Card("crazy", "silly guy"));
        disease.add(7, new Card("out of control", "pissing everywhere"));

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new FragRecyclerViewAdapter(disease, getContext());
        rv.setAdapter(adapter);
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
