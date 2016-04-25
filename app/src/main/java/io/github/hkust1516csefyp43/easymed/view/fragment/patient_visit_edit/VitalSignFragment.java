package io.github.hkust1516csefyp43.easymed.view.fragment.patient_visit_edit;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import io.github.hkust1516csefyp43.easymed.R;
import io.github.hkust1516csefyp43.easymed.listener.OnFragmentInteractionListener;
import io.github.hkust1516csefyp43.easymed.listener.OnSendData;
import io.github.hkust1516csefyp43.easymed.pojo.patient_visit_edit.VitalSigns;

public class VitalSignFragment extends Fragment implements OnSendData{
  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";
  private static final String TAG = VitalSignFragment.class.getSimpleName();


  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;

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
  private int[] lddDate = new int[3];

  private OnFragmentInteractionListener mListener;

  public VitalSignFragment() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @param param1 Parameter 1.
   * @param param2 Parameter 2.
   * @return A new instance of fragment VitalSignFragment.
   */
  // TODO: Rename and change types and number of parameters
  public static VitalSignFragment newInstance(String param1, String param2) {
    VitalSignFragment fragment = new VitalSignFragment();
    Bundle args = new Bundle();
    args.putString(ARG_PARAM1, param1);
    args.putString(ARG_PARAM2, param2);
    fragment.setArguments(args);
    return fragment;
  }

  public static VitalSignFragment newInstance(){
    return new VitalSignFragment();
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
    View view = inflater.inflate(R.layout.fragment_vital_sign, container, false);
    tvLDD = (TextView) view.findViewById(R.id.ldd);
    etSystolic = (EditText) view.findViewById(R.id.etSystolic);
    etDiastolic = (EditText) view.findViewById(R.id.etDiastolic);
    etPulseRate = (EditText) view.findViewById(R.id.etPulseRate);
    etRespiratoryRate = (EditText) view.findViewById(R.id.etRespiratoryRate);
    etTemperature = (EditText) view.findViewById(R.id.etTemperature);
    etSpo2 = (EditText) view.findViewById(R.id.etSpo2);
    etWeight = (EditText) view.findViewById(R.id.etWeight);
    etHeight = (EditText) view.findViewById(R.id.etHeight);
    tvBMI = (TextView) view.findViewById(R.id.tvBMI);

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
              tvBMI.setTextColor(Color.GREEN);
            else if (bmi < 25)
              tvBMI.setTextColor(Color.BLUE);
            else if (bmi < 30)
              tvBMI.setTextColor(Color.YELLOW);
            else
              tvBMI.setTextColor(Color.RED);
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
        Log.d(TAG, s.toString());
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
              lddDate[0] = year;
              lddDate[1] = monthOfYear;
              lddDate[2] = dayOfMonth;
              tvLDD.setText(date);
            }
          }
        }, gc.get(Calendar.YEAR), gc.get(Calendar.MONTH), gc.get(Calendar.DAY_OF_MONTH));
        dpd.show(getActivity().getFragmentManager(), TAG);
      }
    });

    Button removeButton = (Button) view.findViewById(R.id.removeButton2);
    removeButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        lddDate[0] = 0;
        lddDate[1] = 0;
        lddDate[2] = 0;
        tvLDD.setText("Click to select date");
      }
    });
    return view;
  }

  private String BMICalculator(double w, double h) {
    if (w <= 0 || h <= 0)
      return "?";
    double h2 = h / 100;
    h2 = h2 * h2;
    h2 = w / h2;
    BigDecimal bd = new BigDecimal(h2);
    bd = bd.round(new MathContext(3));
    h2 = bd.doubleValue();
    return roundNumber(h2, 2);
  }
  private String roundNumber(double num, int length) {
    String format = "#";
    if (length > 0)
      format += ".";
    for (int i = 0; i < length; i++) {
      format += "#";
    }
    DecimalFormat df = new DecimalFormat(format);
    df.setRoundingMode(RoundingMode.CEILING);
    return df.format(num);
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

  @Override
  public Serializable onSendData() {

    VitalSigns vs = new VitalSigns();
    if (etSystolic != null) {
      try {
        vs.setSystolic(Integer.parseInt(etSystolic.getText().toString()));
      } catch (NumberFormatException e) {
        //TODO systolic is not a number
      }
    }
    if (etDiastolic != null) {
      try {
        vs.setDiastolic(Integer.parseInt(etDiastolic.getText().toString()));
      } catch (NumberFormatException e) {
        //TODO diastolic is not a number
      }
    }
    if (etPulseRate != null) {
      try {
        vs.setPulseRate(Integer.parseInt(etPulseRate.getText().toString()));
      } catch (NumberFormatException e) {
        //TODO pulse rate is not a number
      }
    }
    if (etRespiratoryRate != null) {
      try {
        vs.setRespiratoryRate(Integer.parseInt(etRespiratoryRate.getText().toString()));
      } catch (NumberFormatException e) {
        //TODO respiratory rate is not a number
      }
    }
    if (etSpo2 != null) {
      try {
        vs.setSpo2(Integer.parseInt(etSpo2.getText().toString()));
      } catch (NumberFormatException e) {
        //TODO spo2 is not a number
      }
    }
    if (etTemperature != null) {
      try {
        //TODO if in F, change back to C first (Utils.fahrenheitToCelsius)
        vs.setTemperature(Double.parseDouble(etTemperature.getText().toString()));
      } catch (NumberFormatException e) {
        //TODO temperature is not a number
      }
    }
    if (etWeight != null) {
      try {
        vs.setWeight(Double.parseDouble(etWeight.getText().toString()));
      } catch (NumberFormatException e) {
        //TODO weight is not a number
      }
    }
    if (etHeight != null) {
      try {
        vs.setHeight(Double.parseDouble(etHeight.getText().toString()));
      } catch (NumberFormatException e) {
        //TODO height is not a number
      }
    }
    return vs;
  }
}
