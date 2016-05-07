package io.github.hkust1516csefyp43.easymed.view.fragment.station;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.db.chart.Tools;
import com.db.chart.listener.OnEntryClickListener;
import com.db.chart.model.LineSet;
import com.db.chart.view.AxisController;
import com.db.chart.view.LineChartView;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.iconics.IconicsDrawable;

import io.github.hkust1516csefyp43.easymed.R;
import io.github.hkust1516csefyp43.easymed.listener.OnFragmentInteractionListener;
import io.github.hkust1516csefyp43.easymed.utility.ExcelGenerator;
import io.github.hkust1516csefyp43.easymed.view.activity.GenerateNewReportActivity;

public class ReportsFragment extends Fragment {
  private static final String TAG = ReportsFragment.class.getSimpleName();
  private OnFragmentInteractionListener mListener;

//  public static ReportsFragment newInstance() {
//    ReportsFragment fragment = new ReportsFragment();
//    return fragment;
//  }

  public ReportsFragment() {
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
    View view = inflater.inflate(R.layout.fragment_reports, container, false);
    Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
    toolbar.setTitle("Reports");
    DrawerLayout drawer = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
    ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);


    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(getActivity(), drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    if (drawer != null) {
      drawer.setDrawerListener(toggle);
      toggle.syncState();
    }

    FloatingActionButton fabOne = (FloatingActionButton) view.findViewById(R.id.fabOne);
    fabOne.setIconDrawable(new IconicsDrawable(getContext()).color(Color.WHITE).actionBar().icon(CommunityMaterial.Icon.cmd_calendar));
    fabOne.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        ExcelGenerator.createWorkbook("Test3.xls");
      }
    });

    FloatingActionButton fabRange = (FloatingActionButton) view.findViewById(R.id.fabRange);
    fabRange.setIconDrawable(new IconicsDrawable(getContext()).color(Color.WHITE).actionBar().icon(CommunityMaterial.Icon.cmd_calendar_multiple));

    LineChartView lineChartView = (LineChartView) view.findViewById(R.id.linechart);
    if (lineChartView != null) {
      lineChartView.setClickable(true);
      lineChartView.setClickablePointRadius(10);
//      lineChartView.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//          Log.d(TAG, "ocl");
//        }
//      });
      lineChartView.setOnEntryClickListener(new OnEntryClickListener() {
        @Override
        public void onClick(int setIndex, int entryIndex, Rect rect) {
          Log.d(TAG, "oecl");
        }
      });
      LineSet lineSet = new LineSet();
      lineSet.addPoint("Dec 15", 1);
      lineSet.addPoint("Jan 16", 3);
      lineSet.addPoint("Feb 16", 2);
      lineSet.addPoint("Mar 16", 4);
      lineSet.addPoint("Apr 16", 6);
      lineSet.setColor(Color.parseColor("#758cbb"))
          .setDotsColor(Color.parseColor("#758cbb"))
          .setThickness(1)
          .setSmooth(true)
          .beginAt(0);
      lineChartView.addData(lineSet);
      lineChartView.setBorderSpacing(Tools.fromDpToPx(15))
          .setAxisBorderValues(0, 10)
          .setYLabels(AxisController.LabelPosition.NONE)
          .setLabelsColor(Color.parseColor("#6a84c3"))
          .setXAxis(true)
          .setYAxis(true);
      lineChartView.show();
    }

    return view;
  }

  private void openGenerateNewReport() {
    Intent intent = new Intent(getContext(), GenerateNewReportActivity.class);
    startActivity(intent);
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
