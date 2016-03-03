package io.github.hkust1516csefyp43.ehr.view.fragment.patient_visit_activity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;

import io.github.hkust1516csefyp43.ehr.R;
import io.github.hkust1516csefyp43.ehr.listener.OnFragmentInteractionListener;

public class RemarkFragment extends Fragment {
    private OnFragmentInteractionListener mListener;
    private SwitchCompat scRemark;
    private EditText etRemark;
    private TextInputLayout tilRemark;

    public RemarkFragment() {
        // Required empty public constructor
    }

    public static RemarkFragment newInstance(String param1, String param2) {
        RemarkFragment fragment = new RemarkFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final Context contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.AppTheme2);
        LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);
        View v = localInflater.inflate(R.layout.fragment_remark, container, false);
        scRemark = (SwitchCompat) v.findViewById(R.id.scRemark);
        etRemark = (EditText) v.findViewById(R.id.etRemark);
        tilRemark = (TextInputLayout) v.findViewById(R.id.tilRemark);
        scRemark.setChecked(true);
        if (!scRemark.isChecked())
            tilRemark.setVisibility(View.GONE);
        else
            tilRemark.setVisibility(View.VISIBLE);
        scRemark.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (tilRemark != null) {
                    if (isChecked)
                        tilRemark.setVisibility(View.VISIBLE);
                    else
                        tilRemark.setVisibility(View.GONE);
                }

            }
        });
        return v;
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
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
