package io.github.hkust1516csefyp43.easymed.view.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;

import io.github.hkust1516csefyp43.easymed.POJO.Patient;
import io.github.hkust1516csefyp43.easymed.R;
import io.github.hkust1516csefyp43.easymed.listener.OnFragmentInteractionListener;
import io.github.hkust1516csefyp43.easymed.utility.Const;

public class BioFragment extends Fragment {
  private Patient patient;
  private static String key = Const.BundleKey.READ_ONLY_PATIENT;

  private OnFragmentInteractionListener mListener;

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
