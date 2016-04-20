package io.github.hkust1516csefyp43.easymed;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.github.hkust1516csefyp43.easymed.listener.OnFragmentInteractionListener;

public class PharmacyFragment extends Fragment {

  private OnFragmentInteractionListener mListener;
  private TabLayout tabLayout;

//  public static ConsultationFragment newInstance(String param1, String param2) {
//    ConsultationFragment fragment = new ConsultationFragment();
//    return fragment;
//  }

  public PharmacyFragment() {
    // Required empty public constructor
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_toolbar_tablayout_viewpager_fab, container, false);
    Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
    toolbar.setTitle("Consultation");
    DrawerLayout drawer = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
    ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);


    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(getActivity(), drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    if (drawer != null) {
      drawer.setDrawerListener(toggle);
      toggle.syncState();
    }

    tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
    tabLayout.addTab(tabLayout.newTab().setText("Waiting"));
    tabLayout.addTab(tabLayout.newTab().setText("Finished"));

    ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPager);
    viewPager.setAdapter(new TwoPagesAdapter(getFragmentManager()));

    return view;
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
//    if (context instanceof OnFragmentInteractionListener) {
//      mListener = (OnFragmentInteractionListener) context;
//    } else {
//      throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
//    }
  }

  @Override
  public void onDetach() {
    super.onDetach();
    mListener = null;
  }

  private class TwoPagesAdapter extends FragmentStatePagerAdapter {

    public TwoPagesAdapter(FragmentManager fm) {
      super(fm);
    }

    @Override
    public Fragment getItem(int position) {
      switch (position) {
        case 0:
          return PatientListFragment.newInstance(Const.PatientListPageId.PRE_PHARMACY);
        case 1:
          return PatientListFragment.newInstance(Const.PatientListPageId.POST_PHARMACY);
        default:
          return PatientListFragment.newInstance(Const.PatientListPageId.NOT_YET);    //TODO idk?

      }
    }

    @Override
    public int getCount() {
      return 2;
    }
  }
}