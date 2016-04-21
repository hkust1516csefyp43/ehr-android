package io.github.hkust1516csefyp43.easymed.view.fragment.patient_visit_view;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.Iconics;
import com.mikepenz.iconics.IconicsDrawable;

import java.io.Serializable;

import io.github.hkust1516csefyp43.easymed.pojo.Consultation;
import io.github.hkust1516csefyp43.easymed.pojo.Triage;
import io.github.hkust1516csefyp43.easymed.pojo.Visit;
import io.github.hkust1516csefyp43.easymed.R;
import io.github.hkust1516csefyp43.easymed.listener.OnFragmentInteractionListener;
import io.github.hkust1516csefyp43.easymed.utility.Const;

public class VisitDetailFragment extends Fragment {
  private static String key1 = Const.BundleKey.VISIT_ID;
  private static String key2 = Const.BundleKey.ON_OR_OFF;
  private OnFragmentInteractionListener mListener;

  private FloatingActionButton floatingActionButton;

  /**
   * Difference between this fabOn and the fabOn in PatientVisitViewActivity (PVVA): the code starts
   * PVVA, and the PVVA will be passed into this. However, if for some reason PVVA does not pass
   * fabOn, this would still works.
   * TODO what should the default be?
   */
  private Boolean fabOn = false;

  private Visit visit;
  private Triage triage;
  private Consultation consultation;
  //TODO Pharmacy POJO

  public static VisitDetailFragment newInstance(Visit visit, Boolean fabOn) {
    VisitDetailFragment fragment = new VisitDetailFragment();
    Bundle args = new Bundle();
    args.putSerializable(key1, visit);
    args.putBoolean(key2, fabOn);
    fragment.setArguments(args);
    return fragment;
  }

  public VisitDetailFragment() {
    // Required empty public constructor
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      Serializable serializable = getArguments().getSerializable(key1);
      if (serializable instanceof Visit) {
        visit = (Visit) serializable;
      }
      fabOn = getArguments().getBoolean(key2);
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_visit_detail, container, false);
    floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab);
    if (!fabOn) {
      floatingActionButton.setVisibility(View.GONE);
    } else {
      floatingActionButton.setImageDrawable(new IconicsDrawable(getContext()).icon(GoogleMaterial.Icon.gmd_edit).actionBar().color(Color.WHITE));
    }
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
