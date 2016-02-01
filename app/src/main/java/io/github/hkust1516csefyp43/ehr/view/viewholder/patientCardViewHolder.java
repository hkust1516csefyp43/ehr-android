package io.github.hkust1516csefyp43.ehr.view.viewholder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import io.github.hkust1516csefyp43.ehr.R;
import io.github.hkust1516csefyp43.ehr.pojo.Patient;
import io.github.hkust1516csefyp43.ehr.value.Const;
import io.github.hkust1516csefyp43.ehr.view.activity.PatientVisitActivity;

/**
 * Created by Louis on 25/11/15.
 */
public final class patientCardViewHolder extends RecyclerView.ViewHolder {
    public TextView patientName;
    public TextView subtitle;
    public ImageView proPic;
    Patient patient;

    public patientCardViewHolder(View view, final Context context) {
        super(view);
        patientName = (TextView) itemView.findViewById(R.id.tvPatientName);
        subtitle = (TextView) itemView.findViewById(R.id.tvSubtitle);
        proPic = (ImageView) itemView.findViewById(R.id.ivPatientPic);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (patient != null) {
                    Log.d("qqq12", patient.toString());
                    Intent intent = new Intent(context, PatientVisitActivity.class);
                    intent.putExtra("patient", patient);
                    //need_cc == true for consultation station only
                    intent.putExtra(Const.KEY_SNACKBAR_TEXT, "Headache, Headache, Headache, Headache, Headache, Headache");
                    context.startActivity(intent);
                } else {
                    Log.d("qqq11", "no patient");
                }
            }
        });
        //TODO? Instagram style 3D touch card?
//        view.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                v.setOnTouchListener(new View.OnTouchListener() {
//                    public boolean onTouch(View view, MotionEvent event) {
//                        if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {
//                            Log.d("qqq TouchTest", "Touch down");
//                        } else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
//                            Log.d("qqq TouchTest", "Touch up");
//                            Log.d("qqq8", ""+event.getAxisValue(MotionEvent.AXIS_X));
//                            Log.d("qqq8", ""+event.getAxisValue(MotionEvent.AXIS_Y));
//                            long t = event.getEventTime()-event.getDownTime();
//                            Log.d("qqq8", ""+t);
//                        }
//                        return true;
//                    }
//                });
//                return false;
//            }
//        });
    }

    public void setPatient(Patient p) {
        patient = p;
    }
}
