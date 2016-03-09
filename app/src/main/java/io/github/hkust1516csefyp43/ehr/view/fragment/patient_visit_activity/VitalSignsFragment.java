package io.github.hkust1516csefyp43.ehr.view.fragment.patient_visit_activity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;
import java.util.GregorianCalendar;

import io.github.hkust1516csefyp43.ehr.R;
import io.github.hkust1516csefyp43.ehr.listener.OnFragmentInteractionListener;
import io.github.hkust1516csefyp43.ehr.pojo.Patient;
import io.github.hkust1516csefyp43.ehr.pojo.Triage;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link VitalSignsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VitalSignsFragment extends Fragment {
    private OnFragmentInteractionListener mListener;
    private TextView tvLDD;
    private EditText etSystolic;
    private EditText etDiastolic;
    private EditText etPulseRate;
    private EditText etRespiratoryRate;
    private EditText etTemperature;
    private EditText etSpo2;
    private EditText etWeight;
    private EditText etHeight;
    public VitalSignsFragment() {
        // Required empty public constructor
    }

    public static VitalSignsFragment newInstance() {
        return new VitalSignsFragment();
    }

    public static VitalSignsFragment newInstance(Patient p, Triage t) {
        //TODO
        return new VitalSignsFragment();
    }

    public static VitalSignsFragment newInstance(String param1, String param2) {
        VitalSignsFragment fragment = new VitalSignsFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //TODO get extra
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final Context contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.AppTheme2);
        LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);
        return localInflater.inflate(R.layout.fragment_vital_signs, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        View v = getView();
        tvLDD = (TextView) v.findViewById(R.id.ldd);
        etSystolic = (EditText) v.findViewById(R.id.etSystolic);
        etDiastolic = (EditText) v.findViewById(R.id.etDiastolic);
        etRespiratoryRate = (EditText) v.findViewById(R.id.etRespiratoryRate);
        etTemperature = (EditText) v.findViewById(R.id.etTemperature);
        etSpo2 = (EditText) v.findViewById(R.id.etSpo2);
        etWeight = (EditText) v.findViewById(R.id.etWeight);
        etHeight = (EditText) v.findViewById(R.id.etHeight);
        //TODO weight & height listener: calculate BMI

        v.findViewById(R.id.tvBMI).setSelected(true);
        tvLDD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GregorianCalendar gc = new GregorianCalendar();
                DatePickerDialog dpd = DatePickerDialog.newInstance(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                        if (tvLDD != null) {
                            //TODO before today check
                            String date = "" + year + "/" + (monthOfYear + 1) + "/" + dayOfMonth;
                            tvLDD.setText(date);
                        }
                    }
                }, gc.get(Calendar.YEAR), gc.get(Calendar.MONTH), gc.get(Calendar.DAY_OF_MONTH));
                dpd.show(getActivity().getFragmentManager(), "qqq");
            }
        });
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
