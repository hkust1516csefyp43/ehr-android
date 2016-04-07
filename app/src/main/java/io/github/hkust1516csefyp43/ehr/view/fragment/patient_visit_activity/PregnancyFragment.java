package io.github.hkust1516csefyp43.ehr.view.fragment.patient_visit_activity;

import android.content.Context;
import android.net.Uri;
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

import io.github.hkust1516csefyp43.ehr.R;
import io.github.hkust1516csefyp43.ehr.listener.OnFragmentInteractionListener;
import io.github.hkust1516csefyp43.ehr.listener.onSendData;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PregnancyFragment OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PregnancyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PregnancyFragment extends Fragment implements onSendData {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

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
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PregnancyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PregnancyFragment newInstance(String param1, String param2) {
        PregnancyFragment fragment = new PregnancyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final Context contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.AppTheme2);
        LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);
        View view = localInflater.inflate(R.layout.fragment_pregnancy, container, false);
        if (view != null){
            LMPDate = (TextView) view.findViewById(R.id.pgdate);
            isPregnant = (CheckBox) view.findViewById(R.id.ispregnant);
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
        if (LMPDate != null){
            LMPDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    GregorianCalendar calendar = new GregorianCalendar();
                    DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                            String date = "" + year + "/" + (monthOfYear + 1) + "/" + dayOfMonth;
                            pregDate[0] = year;
                            pregDate[1] = monthOfYear;
                            pregDate[2] = dayOfMonth;
                            LMPDate.setText(date);
                        }
                    }, 1992, 8, 14);
                    datePickerDialog.showYearPickerFirst(true);
                    datePickerDialog.show(getActivity().getFragmentManager(), "qqq");
                }
            });
        }
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
        if (isPregnant.isChecked()){
            outState.putBoolean("isPreg", true);
            outState.putString("gestation", gestation.getText().toString());
        }
        else
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
        if (savedInstanceState != null){
            if (LMPDate != null){
                pregDate[0] = savedInstanceState.getInt("pgDate");
                pregDate[1] = savedInstanceState.getInt("pgMonth");
                pregDate[2] = savedInstanceState.getInt("pgYear");
                String date = "" + pregDate[0] + "/" + (pregDate[1] + 1) + "/" + pregDate[2];
                LMPDate.setText(date);
            }
            if (isPregnant != null){
                if (savedInstanceState.getBoolean("isPreg"))
                    isPregnant.setChecked(true);
                else
                    isPregnant.setChecked(false);
            }
            if (isBreastFeeding != null){
                if (savedInstanceState.getBoolean("isBreastFeed"))
                    isBreastFeeding.setChecked(true);
                else
                    isBreastFeeding.setChecked(false);
            }
            if (isContraceptive != null){
                if (savedInstanceState.getBoolean("isContra"))
                    isContraceptive.setChecked(true);
                else
                    isContraceptive.setChecked(false);
            }
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

        return null;
    }
}
