package io.github.hkust1516csefyp43.easymed.view.fragment.patient_visit_view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.Serializable;

import io.github.hkust1516csefyp43.easymed.R;
import io.github.hkust1516csefyp43.easymed.listener.OnFragmentInteractionListener;
import io.github.hkust1516csefyp43.easymed.pojo.server_response.Patient;
import io.github.hkust1516csefyp43.easymed.utility.Const;

public class BioFragment extends Fragment {
  private Patient patient;
  private static String key = Const.BundleKey.READ_ONLY_PATIENT;

  private OnFragmentInteractionListener mListener;

  private LinearLayout llPatientInfo;

  public static BioFragment newInstance(Patient patient) {
    BioFragment fragment = new BioFragment();
    Bundle args = new Bundle();
    args.putSerializable(key, patient);
    fragment.setArguments(args);
    return fragment;
  }

  public BioFragment() {
    // Required empty public constructor
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      Serializable o = getArguments().getSerializable(key);
      if (o instanceof Patient) {
        patient = (Patient) o;
      }
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_bio, container, false);
    llPatientInfo = (LinearLayout) view.findViewById(R.id.llPatientInfo);
    if (llPatientInfo != null) {
      if (patient != null) {
        Context context = getContext();
        if (context != null) {
          TextView tvBioTitle = new TextView(context);
          tvBioTitle.setText("Basic Information");
          tvBioTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
          tvBioTitle.setTextColor(Color.BLACK);
          tvBioTitle.setTypeface(null, Typeface.BOLD);
          llPatientInfo.addView(tvBioTitle);

          if (patient.getPatientId() != null){
            TextView tvBioPatientId = new TextView(context);
            tvBioPatientId.setText("Patient ID: " + patient.getPatientId());
            llPatientInfo.addView(tvBioPatientId);
          }

          if (patient.getGenderId() != null){
            TextView tvBioGender = new TextView(context);
            tvBioGender.setText("Gender: " + patient.getGenderId());
            llPatientInfo.addView(tvBioGender);
          }

          if (patient.getBirthDate() != null) {
            TextView tvBioBirthDate = new TextView(context);
            tvBioBirthDate.setText("Birthday: " + patient.getBirthYear() + "/" + patient.getBirthMonth() + "/" + patient.getBirthDate());
            llPatientInfo.addView(tvBioBirthDate);
          }

          if (patient.getBloodTypeId() != null) {
            TextView tvBioBloodType = new TextView(context);
            tvBioBloodType.setText("Blood type: " + patient.getBloodTypeId());
            llPatientInfo.addView(tvBioBloodType);
          }

          if (patient.getPhoneNumberCountryCode() != null) {
            TextView tvBioPhoneNumberCountryCode = new TextView(context);
            tvBioPhoneNumberCountryCode.setText("Phone number country code: " + patient.getPhoneNumberCountryCode());
            llPatientInfo.addView(tvBioPhoneNumberCountryCode);
          }

          if (patient.getPhoneNumber() != null) {
            TextView tvPhoneNumber = new TextView(context);
            tvPhoneNumber.setText("Phone number: " + patient.getPhoneNumber());
            llPatientInfo.addView(tvPhoneNumber);
          }

          if (patient.getAddress() != null) {
            TextView tvBioAddress = new TextView(context);
            tvBioAddress.setText("Address: " + patient.getAddress());
            llPatientInfo.addView(tvBioAddress);
          }

          if (patient.getEmail() != null) {
            TextView tvBioEmail = new TextView(context);
            tvBioEmail.setText("Email: " + patient.getEmail());
            llPatientInfo.addView(tvBioEmail);
          }

//  /**
//   * Inflate the blank section of the page with content
//   * TODO make it good looking (material design stuff: spacing, sizes of everything, animation, etc)
//   */
////  private void fillTheWholePage() {
////    LinearLayout llPatientInfo = (LinearLayout) findViewById(R.id.llPatientInfo);
////    fillPersonalData(llPatientInfo);
////  }
//
//  private void fillPersonalData(LinearLayout l) {
//    if (l != null) {
//      TextView tv = new TextView(this);
//      StringBuilder sb = new StringBuilder();
//      if (thisPatient.getHonorific() != null) {
//        sb.append(thisPatient.getHonorific()).append(" ");
//      }
//      if (thisPatient.getLastName() != null) {
//        sb.append(thisPatient.getLastName()).append(" ");
//      }
//      if (thisPatient.getMiddleName() != null) {
//        sb.append(thisPatient.getMiddleName()).append(" ");
//      }
//      sb.append(thisPatient.getFirstName());
//      tv.setText(sb.toString());
//
//      TextInputLayout textInputLayout = new TextInputLayout(this);
//      textInputLayout.addView(tv);
//
//      l.addView(textInputLayout);
//      if (thisPatient.getNativeName() != null) {
//        TextView tv2 = new TextView(this);
//        tv2.setText(thisPatient.getNativeName());
//        TextInputLayout textInputLayout2 = new TextInputLayout(this);
//        textInputLayout2.addView(tv2);
//        l.addView(textInputLayout2);
//      }
//    }
//    if (thisPatient.getBirthYear() != null && thisPatient.getBirthMonth() != null && thisPatient.getBirthDate() != null) {
//      //TODO user can customize display format (order and symbol)
//      String birthday = "" + thisPatient.getBirthYear() + "/" + thisPatient.getBirthMonth() + "/" + thisPatient.getBirthDate() + " (" + Util.birthdayToAgeString(thisPatient.getBirthYear(), thisPatient.getBirthMonth(), thisPatient.getBirthDate()) + ")";
//      TextView tv1 = new TextView(this);
//      tv1.setText(birthday);
//      l.addView(tv1);
//    }
//    //phone
//    //address
//    //email address
//    //gender
//    //blood type
//  }
        }
      } else {
        //TODO some error message?
      }
    }//else there is nothing you can do
    return view;
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
