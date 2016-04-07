package io.github.hkust1516csefyp43.ehr.view.fragment.patient_visit_activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;
import java.util.GregorianCalendar;

import io.github.hkust1516csefyp43.ehr.R;
import io.github.hkust1516csefyp43.ehr.listener.OnFragmentInteractionListener;
import io.github.hkust1516csefyp43.ehr.listener.onSendData;
import io.github.hkust1516csefyp43.ehr.pojo.patient_visit.Pregnancy;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PregnancyFragment OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PregnancyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PregnancyFragment extends Fragment implements onSendData {

  private OnFragmentInteractionListener mListener;

  private TextView LMPDate;
  private CheckBox isPregnant;
  private TextView gestation;
  private CheckBox isBreastFeeding;
  private CheckBox isContraceptive;
  private EditText noPregnancy;
  private EditText noLiveBirth;
  private EditText noMiscarriage;
  private EditText noAbortion;
  private EditText noStillBirth;
  private EditText otherInfo;

  private int[] pregDate = new int[3];


  public PregnancyFragment() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @return A new instance of fragment PregnancyFragment.
   */
  public static PregnancyFragment newInstance() {
    PregnancyFragment fragment = new PregnancyFragment();
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
    View view = localInflater.inflate(R.layout.fragment_pregnancy, container, false);
    if (view != null) {
      LMPDate = (TextView) view.findViewById(R.id.pgdate);
      isPregnant = (CheckBox) view.findViewById(R.id.ispregnant);
      //TODO llIfPregnant
      gestation = (TextView) view.findViewById(R.id.gestation);
      isBreastFeeding = (CheckBox) view.findViewById(R.id.breastfeeding);
      isContraceptive = (CheckBox) view.findViewById(R.id.contraceptive);
      noPregnancy = (EditText) view.findViewById(R.id.no_preg);
      noLiveBirth = (EditText) view.findViewById(R.id.no_livebirth);
      noMiscarriage = (EditText) view.findViewById(R.id.no_miscarriage);
      noAbortion = (EditText) view.findViewById(R.id.no_abortion);
      noStillBirth = (EditText) view.findViewById(R.id.no_stillbirth);
      otherInfo = (EditText) view.findViewById(R.id.other_info);
    }
    if (LMPDate != null) {
      LMPDate.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          GregorianCalendar calendar = new GregorianCalendar();
          DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
              String date = "" + year + "/" + (monthOfYear + 1) + "/" + dayOfMonth;
              pregDate[0] = year;
              pregDate[1] = monthOfYear;
              pregDate[2] = dayOfMonth;
              LMPDate.setText(date);
            }
          }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
          datePickerDialog.showYearPickerFirst(true);
          datePickerDialog.show(getActivity().getFragmentManager(), "qqq");
        }
      });
    }
    return view;
  }

  @Override
  public void onResume() {
    super.onResume();
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
  public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putInt("pgDate", pregDate[0]);
    outState.putInt("pgMonth", pregDate[1]);
    outState.putInt("pgYear", pregDate[2]);
    if (isPregnant.isChecked()) {
      outState.putBoolean("isPreg", true);
      outState.putString("gestation", gestation.getText().toString());
    } else
      outState.putBoolean("isPreg", false);

    if (isBreastFeeding.isChecked())
      outState.putBoolean("isBreastFeed", true);
    else
      outState.putBoolean("isBreastFeed", false);

    if (isContraceptive.isChecked())
      outState.putBoolean("isContra", true);
    else
      outState.putBoolean("isContra", false);

    outState.putString("noPreg", noPregnancy.getText().toString());
    outState.putString("noLiveBirth", noLiveBirth.getText().toString());
    outState.putString("noMiscarriage", noMiscarriage.getText().toString());
    outState.putString("noAbortion", noAbortion.getText().toString());
    outState.putString("noStillBirth", noStillBirth.getText().toString());
    outState.putString("otherInfo", otherInfo.getText().toString());
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    if (savedInstanceState != null) {
      if (LMPDate != null) {
        pregDate[0] = savedInstanceState.getInt("pgDate");
        pregDate[1] = savedInstanceState.getInt("pgMonth");
        pregDate[2] = savedInstanceState.getInt("pgYear");
        String date = "" + pregDate[0] + "/" + (pregDate[1] + 1) + "/" + pregDate[2];
        LMPDate.setText(date);
      }
      if (isPregnant != null)
        isPregnant.setChecked(savedInstanceState.getBoolean("isPreg"));
      if (isBreastFeeding != null)
        isBreastFeeding.setChecked(savedInstanceState.getBoolean("isBreastFeed"));
      if (isContraceptive != null)
        isContraceptive.setChecked(savedInstanceState.getBoolean("isContra"));
      if (noPregnancy != null)
        noPregnancy.setText(savedInstanceState.getString("noPreg"));
      if (noLiveBirth != null)
        noLiveBirth.setText(savedInstanceState.getString("noLiveBirth"));
      if (noMiscarriage != null)
        noMiscarriage.setText(savedInstanceState.getString("noMiscarriage"));
      if (noAbortion != null)
        noAbortion.setText(savedInstanceState.getString("noAbortion"));
      if (noStillBirth != null)
        noStillBirth.setText(savedInstanceState.getString("noStillBirth"));
      if (otherInfo != null)
        otherInfo.setText(savedInstanceState.getString("otherInfo"));
    }
  }


  @Override
  public Object onSendData() {
    Pregnancy p = new Pregnancy();
    if (LMPDate != null) {
      p.setLmdDate(new GregorianCalendar(pregDate[2], pregDate[1], pregDate[0]).getTime());
    }
    if (isBreastFeeding != null) {
      p.setBreastFeeding(isBreastFeeding.isChecked());
    }
    if (isContraceptive != null) {
      p.setContraceptiveUse(isContraceptive.isChecked());
    }
    if (noPregnancy != null) {
      try {
        p.setNoOfPregnancy(Integer.parseInt(noPregnancy.getText().toString()));
      } catch (NumberFormatException e) {
        e.printStackTrace();
      }
    }
    if (noLiveBirth != null) {
      try {
        p.setNoOfLiveBirth(Integer.parseInt(noLiveBirth.getText().toString()));
      } catch (NumberFormatException e) {
        e.printStackTrace();
      }
    }
    if (noMiscarriage != null) {
      try {
        p.setNoOfMiscarriage(Integer.parseInt(noMiscarriage.getText().toString()));
      } catch (NumberFormatException e) {
        e.printStackTrace();
      }
    }
    if (noAbortion != null) {
      try {
        p.setNoOfAbortion(Integer.parseInt(noAbortion.getText().toString()));
      } catch (NumberFormatException e) {
        e.printStackTrace();
      }
    }
    if (noStillBirth != null) {
      try {
        p.setNoOfStillBirth(Integer.parseInt(noStillBirth.getText().toString()));
      } catch (NumberFormatException e) {
        e.printStackTrace();
      }
    }
    if (otherInfo != null) {
      p.setOtherInformation(otherInfo.getText().toString());
    }
    return p;
  }
}
