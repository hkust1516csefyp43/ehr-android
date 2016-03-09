package io.github.hkust1516csefyp43.ehr.view.viewholder;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import io.github.hkust1516csefyp43.ehr.R;
import io.github.hkust1516csefyp43.ehr.pojo.PreviousMedicalHistory;

/**
 * Created by kalongip on 7/3/16.
 */
public class PMHCardViewHolder extends RecyclerView.ViewHolder {
    public TextView medicineName;
    public EditText medicalDescription;
    PreviousMedicalHistory pmh;

    public PMHCardViewHolder(View view, final Context context) {
        super(view);
        medicineName = (TextView) itemView.findViewById(R.id.medicineName);
        medicalDescription = (EditText) itemView.findViewById(R.id.medicalDescription);
    }

    public void setPmh(PreviousMedicalHistory pmh) {
        this.pmh = pmh;
    }
}
