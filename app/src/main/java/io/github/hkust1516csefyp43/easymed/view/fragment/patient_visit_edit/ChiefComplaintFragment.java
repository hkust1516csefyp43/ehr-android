package io.github.hkust1516csefyp43.easymed.view.fragment.patient_visit_edit;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatMultiAutoCompleteTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;

import io.github.hkust1516csefyp43.easymed.R;
import io.github.hkust1516csefyp43.easymed.listener.OnFragmentInteractionListener;
import io.github.hkust1516csefyp43.easymed.listener.OnSendData;

public class ChiefComplaintFragment extends Fragment implements OnSendData{
  private OnFragmentInteractionListener mListener;
  private AppCompatMultiAutoCompleteTextView acmactv;

  public ChiefComplaintFragment() {
    // Required empty public constructor
  }

  public static ChiefComplaintFragment newInstance(String param1, String param2) {
    ChiefComplaintFragment fragment = new ChiefComplaintFragment();
    return fragment;
  }

  public static ChiefComplaintFragment newInstance(){
    return new ChiefComplaintFragment();
  }
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_chief_complaint, container, false);
    acmactv = (AppCompatMultiAutoCompleteTextView) view.findViewById(R.id.chief_complaint);
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

  @Override
  public Serializable onSendData() {
    return null;
  }
}
