package io.github.hkust1516csefyp43.ehr.view.viewholder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import io.github.hkust1516csefyp43.ehr.R;
import io.github.hkust1516csefyp43.ehr.pojo.server_response.v2.Patient;
import io.github.hkust1516csefyp43.ehr.value.Cache;
import io.github.hkust1516csefyp43.ehr.value.Const;
import io.github.hkust1516csefyp43.ehr.view.activity.PatientVisitActivity;
import io.github.hkust1516csefyp43.ehr.view.activity.PharmacyActivity;

/**
 * Created by Louis on 25/11/15.
 */
public final class patientCardViewHolder extends RecyclerView.ViewHolder {
  public TextView patientName;
  public TextView subtitle;
  public TextView nativeName;
  public ImageView proPic;
  public Patient patient;
  int whichStation;

  public patientCardViewHolder(View view, final Context context, final int which) {
    super(view);
    Log.d("qqq351", String.valueOf(which));
    patientName = (TextView) itemView.findViewById(R.id.tvPatientName);
    subtitle = (TextView) itemView.findViewById(R.id.tvSubtitle);
    nativeName = (TextView) itemView.findViewById(R.id.tvPatientNativeName);
    proPic = (ImageView) itemView.findViewById(R.id.ivPatientPic);
    whichStation = which;
    view.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (patient != null) {
          Log.d("qqq120", patient.toString());
          Log.d("qqq121", "station: " + whichStation);
          whichStation = Cache.getWhichStation(context);
          Log.d("qqq122", "station: " + whichStation);
          if (whichStation == Const.ID_PHARMACY) {
            Intent intent = new Intent(context, PharmacyActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
          } else {
            Intent intent = new Intent(context, PatientVisitActivity.class);
            intent.putExtra(Const.KEY_PATIENT, patient);
            Log.d("qqq350", String.valueOf(whichStation));
            switch (whichStation) {
              case Const.ID_TRIAGE:
                intent.putExtra(Const.KEY_IS_TRIAGE, true);
                break;
              case Const.ID_CONSULTATION:
                intent.putExtra(Const.KEY_IS_TRIAGE, false);
                break;
            }
            //need_cc == true for consultation station only
            intent.putExtra(Const.KEY_SNACKBAR_TEXT, "Headache, Headache, Headache, Headache, Headache, Headache");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
          }
        } else {
          Log.d("qqq11", "no patient");
        }
      }
    });
    //TODO? Instagram style 3D touch card?
  }

  public void setPatient(Patient p) {
    patient = p;
  }
}
