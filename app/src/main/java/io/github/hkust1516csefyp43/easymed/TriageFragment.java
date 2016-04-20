package io.github.hkust1516csefyp43.easymed;

import android.content.Context;
import android.net.Uri;
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

public class TriageFragment extends Fragment implements OnFragmentInteractionListener {

  private OnFragmentInteractionListener mListener;
  private TabLayout tabLayout;
  private ViewPager viewPager;

  public static TriageFragment newInstance(String param1, String param2) {
    TriageFragment fragment = new TriageFragment();
    return fragment;
  }

  public TriageFragment() {
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
    toolbar.setTitle("Triage");
    DrawerLayout drawer = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
    ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);


    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(getActivity(), drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    if (drawer != null) {
      drawer.setDrawerListener(toggle);
      toggle.syncState();
    }

    tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
    viewPager = (ViewPager) view.findViewById(R.id.viewPager);

    tabLayout.addTab(tabLayout.newTab().setText("After"));
    tabLayout.addTab(tabLayout.newTab().setText("Everyone else"));
    tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
      @Override
      public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
      }

      @Override
      public void onTabUnselected(TabLayout.Tab tab) {

      }

      @Override
      public void onTabReselected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
      }
    });

    viewPager.setAdapter(new TwoPagesAdapter(getFragmentManager()));
    viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

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
  public void onFragmentInteraction(Uri uri) {

  }
//
//  public void updateTabTitleCounter(int whichPage, int howMuch) {
//    if (tabLayout != null) {
//      switch (whichPage) {
//        case Const.PatientListPageId.POST_TRIAGE:
//            tabLayout.getTabAt(0).setText("AFTER (" + howMuch + ")");
//          break;
//        case Const.PatientListPageId.NOT_YET:
//        default:        //TODO idk
//          tabLayout.getTabAt(1).setText("AFTER (" + howMuch + ")");
//          break;
//
//      }
//    }
//  }

  private class TwoPagesAdapter extends FragmentStatePagerAdapter {

    public TwoPagesAdapter(FragmentManager fm) {
      super(fm);
    }

    @Override
    public Fragment getItem(int position) {
      switch (position) {
        case 0:
          return PatientListFragment.newInstance(Const.PatientListPageId.POST_TRIAGE);
        case 1:
          return PatientListFragment.newInstance(Const.PatientListPageId.NOT_YET);
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