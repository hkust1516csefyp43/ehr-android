package io.github.hkust1516csefyp43.ehr;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import io.github.hkust1516csefyp43.ehr.view.activity.PatientVisitActivity;

/**
 * Created by Louis on 25/11/15.
 */
public final class patientCardViewHolder extends RecyclerView.ViewHolder {
    public TextView patientName;
    public TextView subtitle;

    public patientCardViewHolder(View view, final Context context) {
        super(view);
        patientName = (TextView) itemView.findViewById(R.id.tvPatientName);
        subtitle = (TextView) itemView.findViewById(R.id.tvSubtitle);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PatientVisitActivity.class);
                context.startActivity(intent);
            }
        });
    }
}
