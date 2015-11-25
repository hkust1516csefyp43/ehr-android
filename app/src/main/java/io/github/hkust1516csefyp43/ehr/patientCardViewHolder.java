package io.github.hkust1516csefyp43.ehr;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Louis on 25/11/15.
 */
public final class patientCardViewHolder extends RecyclerView.ViewHolder {
    TextView patientName;
    TextView subtitle;

    public patientCardViewHolder(View view) {
        super(view);
        patientName = (TextView) itemView.findViewById(R.id.tvPatientName);
        subtitle = (TextView) itemView.findViewById(R.id.tvSubtitle);
    }
}
