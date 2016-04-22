package io.github.hkust1516csefyp43.easymed.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.github.hkust1516csefyp43.easymed.R;
import io.github.hkust1516csefyp43.easymed.listener.OnFragmentInteractionListener;
import io.github.hkust1516csefyp43.easymed.utility.Const;

public class MedicationVariantListFragment extends Fragment {
  private OnFragmentInteractionListener mListener;
  private static String key = Const.BundleKey.WHICH_MV_PAGE;

  private int whichPage;

  public static MedicationVariantListFragment newInstance(int whichPage) {
    MedicationVariantListFragment fragment = new MedicationVariantListFragment();
    Bundle args = new Bundle();
    args.putInt(key, whichPage);
    fragment.setArguments(args);
    return fragment;
  }

  public MedicationVariantListFragment() {
    // Required empty public constructor
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      whichPage = getArguments().getInt(key);
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    //whichPage are 0(out of stock), 1(inadequate) or 2(enough)
    //recyclerview, call api, fill the list, add swipe refresh, fab >> new activity
    return inflater.inflate(R.layout.fragment_medication_variant_list, container, false);
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
