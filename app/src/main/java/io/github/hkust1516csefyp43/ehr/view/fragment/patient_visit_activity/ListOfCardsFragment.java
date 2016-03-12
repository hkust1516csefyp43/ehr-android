package io.github.hkust1516csefyp43.ehr.view.fragment.patient_visit_activity;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
    private FragRecyclerViewAdapter adapter;
    private FloatingActionButton fab;
    private String title;
    private ArrayList<String> preFillItems;

    public ListOfCardsFragment() {
        // Required empty public constructor
    }

    /**
     * Create a new instance of this fragment
     * Useful in allergy, diagnosis, etc.
     * There won't be any cards by default
     *
     * @param title of the fragment, e.g. PMH, PE, Allergy, etc.
     * @return an instance of ListOfCardsFragment
     */
    public static ListOfCardsFragment newInstance(String title) {
        ListOfCardsFragment fragment = new ListOfCardsFragment();
        Bundle args = new Bundle();
        args.putString(Const.KEY_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Create a new instance of this fragment with pre-fill cards
     * Useful in RoS, PE, etc.
     * TODO It will activate the null switch
     *
     * @param title        of the fragment, e.g. PMH, PE, Allergy, etc.
     * @param preFillItems an ArrayList<String> of title to be prefill
     * @return an instance of ListOfCardsFragment
     */
    public static ListOfCardsFragment newInstance(String title, ArrayList<String> preFillItems) {
        ListOfCardsFragment fragment = new ListOfCardsFragment();
        Bundle args = new Bundle();
        args.putString(Const.KEY_TITLE, title);
        args.putStringArrayList(Const.KEY_PRE_FILL_ITEMS, preFillItems);
        fragment.setArguments(args);
        return fragment;
    }

    public static ListOfCardsFragment newInstance(String title, String[] preFillItems) {
        ArrayList<String> sList = new ArrayList<>();
        for (String s : preFillItems) {
            sList.add(s);
        }
        return newInstance(title, sList);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("qqq172", "rotating");
        Bundle b = getArguments();
        if (b != null) {
            title = b.getString(Const.KEY_TITLE);
            preFillItems = b.getStringArrayList(Const.KEY_PRE_FILL_ITEMS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("qqq171", "rotating");
        final Context contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.AppTheme2);
        LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);
        View v = localInflater.inflate(R.layout.fragment_previous_medical_history, container, false);
        rv = (RecyclerView) v.findViewById(R.id.recycler_view);
        rv.setHasFixedSize(true);

        rv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        fab = (FloatingActionButton) v.findViewById(R.id.floatingactionbutton);
        fab.setImageDrawable(new IconicsDrawable(getContext(), GoogleMaterial.Icon.gmd_add).color(Color.WHITE).paddingDp(3).sizeDp(16));
        //TODO call api and get list of keywords (& cache them)
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
                new MaterialDialog.Builder(getContext())
                        .title("Add")
                        .customView(tetdcv, true)
                        .positiveText("Confirm")
                        .negativeText("Cancel")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                ArrayList<String> data = tetdcv.getData();
                                tetdcv.clearData();
                                Log.d("qqq141", data.toString());
                                adapter.addCard(new Card(data.get(0), data.get(1)));
                            }
                        })
                        .onNegative(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                tetdcv.clearData();
                                dialog.dismiss();
                            }
                        })
                        .show();
            }
        });
        if (preFillItems != null) {
            fab.setVisibility(View.GONE);
            ArrayList<Card> preFillCards = new ArrayList<>();
            for (String s : preFillItems) {
                preFillCards.add(new Card(s, "Fill this or flip the switch"));
            }
            adapter = new FragRecyclerViewAdapter(preFillCards, getContext(), true, a, title);
        } else {
            adapter = new FragRecyclerViewAdapter(null, getContext(), false, a, title);
        }
        rv.setAdapter(adapter);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("qqq173", "rotating");
    }

    /**
     * save everything to make sure rotate wont
     *
     * @param outState
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.d("qqq179", "rotating");
        super.onSaveInstanceState(outState);
        outState.putString(Const.KEY_TITLE, title);
        outState.putParcelableArrayList(Const.KEY_LOCF + title, adapter.getData());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("qqq174", "rotating");
        if (savedInstanceState != null) {
            title = savedInstanceState.getString(Const.KEY_TITLE);
            ArrayList<Card> alc = savedInstanceState.getParcelableArrayList(Const.KEY_LOCF + title);
            adapter.deleteAllCards();
            adapter.addCards(alc);
        }
    }

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
}
