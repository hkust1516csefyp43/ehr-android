package io.github.hkust1516csefyp43.ehr.view.fragment.patient_visit_activity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Calendar;
import java.util.GregorianCalendar;

import io.github.hkust1516csefyp43.ehr.R;
import io.github.hkust1516csefyp43.ehr.Utils;
import io.github.hkust1516csefyp43.ehr.listener.OnFragmentInteractionListener;
import io.github.hkust1516csefyp43.ehr.pojo.server_response.v1.Patient;
import io.github.hkust1516csefyp43.ehr.pojo.server_response.v1.Triage;

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
    private TextView tvBMI;
    private EditText etSystolic;
    private EditText etDiastolic;
    private EditText etPulseRate;
    private EditText etRespiratoryRate;
    private EditText etTemperature;
    private EditText etSpo2;
    private EditText etWeight;
    private EditText etHeight;
    private double dWeight;
    private double dHeight;

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
        //TODO add blood sugar
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
        tvBMI = (TextView) v.findViewById(R.id.tvBMI);
        tvBMI.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (tvBMI != null) {
                    try {
                        double bmi = Double.parseDouble(s.toString());
                        if (bmi < 18.5)
                            tvBMI.setTextColor(getResources().getColor(R.color.md_blue_600));
                        else if (bmi < 25)
                            tvBMI.setTextColor(getResources().getColor(R.color.primary_text_color));
                        else if (bmi < 30)
                            tvBMI.setTextColor(getResources().getColor(R.color.md_yellow_500));
                        else
                            tvBMI.setTextColor(getResources().getColor(R.color.md_red_600));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        //e.g. "?", "Please input sth"
                        tvBMI.setTextColor(getResources().getColor(R.color.primary_text_color));
                    }
                }
            }
        });
        etWeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d("qqq150", s.toString());
                try {
                    dWeight = Double.parseDouble(s.toString());
                    if (tvBMI != null)
                        tvBMI.setText(BMICalculator(dWeight, dHeight));
                } catch (NumberFormatException e) {
                    dWeight = 0;
                    if (tvBMI != null) {
                        tvBMI.setText("?");
                    }
                    e.printStackTrace();
                }
            }
        });
        etHeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d("qqq151", s.toString());
                try {
                    dHeight = Double.parseDouble(s.toString());
                    if (tvBMI != null)
                        tvBMI.setText(BMICalculator(dWeight, dHeight));
                } catch (NumberFormatException e) {
                    dHeight = 0;
                    if (tvBMI != null) {
                        tvBMI.setText("?");
                    }
                    e.printStackTrace();
                }
            }
        });
        //TODO weight & height listener: calculate BMI
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

    /**
     * Return a string of BMI (no unit, just number
     * TODO how many significant figures?
     *
     * @param w = weight in kh
     * @param h = weight in cm
     * @return a string of the weight
     */
    private String BMICalculator(double w, double h) {
        if (w <= 0 || h <= 0)
            return "?";
        double h2 = h / 100;
        h2 = h2 * h2;
        h2 = w / h2;
        BigDecimal bd = new BigDecimal(h2);
        bd = bd.round(new MathContext(3));
        h2 = bd.doubleValue();
        return Utils.roundNumber(h2, 2);
    }
}
