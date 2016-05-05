package io.github.hkust1516csefyp43.easymed.view.fragment.station;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.iconics.IconicsDrawable;

import io.github.hkust1516csefyp43.easymed.R;
import io.github.hkust1516csefyp43.easymed.listener.OnFragmentInteractionListener;
import io.github.hkust1516csefyp43.easymed.view.activity.SyncActivity;

public class AdminFragment extends Fragment {
  private OnFragmentInteractionListener mListener;
  public static AdminFragment newInstance(String param1, String param2) {
    AdminFragment fragment = new AdminFragment();
    Bundle args = new Bundle();
    fragment.setArguments(args);
    return fragment;
  }

 public AdminFragment() {
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
    View view = inflater.inflate(R.layout.fragment_admin, container, false);
    Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
    DrawerLayout drawer = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
    LinearLayout llSync = (LinearLayout) view.findViewById(R.id.synchronisation);
    LinearLayout llStatics = (LinearLayout) view.findViewById(R.id.statics);
    LinearLayout llOthers = (LinearLayout) view.findViewById(R.id.other);
    ImageView ivSync = (ImageView) view.findViewById(R.id.ivSync);
    ImageView ivStatic = (ImageView) view.findViewById(R.id.ivStatic);
    ImageView ivOther = (ImageView) view.findViewById(R.id.ivOther);

    ivSync.setImageDrawable(new IconicsDrawable(getContext()).color(Color.WHITE).actionBar().icon(CommunityMaterial.Icon.cmd_sync));
    ivStatic.setImageDrawable(new IconicsDrawable(getContext()).color(Color.WHITE).actionBar().icon(CommunityMaterial.Icon.cmd_table_edit));
    ivOther.setImageDrawable(new IconicsDrawable(getContext()).color(Color.WHITE).actionBar().icon(CommunityMaterial.Icon.cmd_settings_box));

    llSync.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(getContext(), SyncActivity.class);
        startActivity(intent);
      }
    });
    llStatics.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        //TODO
      }
    });
    llOthers.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        //TODO
      }
    });


    toolbar.setTitle("Admin");
    ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(getActivity(), drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    if (drawer != null) {
      drawer.setDrawerListener(toggle);
      toggle.syncState();
    }

    //TODO synchronize (sync)
    //TODO data management (table edit)
    //TODO other setting (setting)

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
