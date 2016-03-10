package io.github.hkust1516csefyp43.ehr.view.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import io.github.hkust1516csefyp43.ehr.R;
import io.github.hkust1516csefyp43.ehr.pojo.patient_visit.PMHFrag;

/**
 * Created by kalongip on 7/3/16.
 */
public class FragCardViewHolder extends RecyclerView.ViewHolder {
    public TextView medicineName;
    public TextView medicalDescription;
    PMHFrag pmh;

    public FragCardViewHolder(View view, final Context context) {
        super(view);
        medicineName = (TextView) itemView.findViewById(R.id.medicineName);
        medicalDescription = (TextView) itemView.findViewById(R.id.medicalDescription);
    }

    public void setPmh(PMHFrag pmh) {
        this.pmh = pmh;
    }
}
