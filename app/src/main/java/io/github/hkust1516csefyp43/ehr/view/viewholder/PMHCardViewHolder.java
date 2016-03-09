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
    public EditText startDate;
    public EditText endDate;
    public CheckBox underTreatment;
    public CardView cv;
    PreviousMedicalHistory pmh;

    public PMHCardViewHolder(View view, final Context context) {
        super(view);
        medicineName = (TextView) itemView.findViewById(R.id.medicineName);
        startDate = (EditText) itemView.findViewById(R.id.startDate);
        endDate = (EditText) itemView.findViewById(R.id.endDate);
        underTreatment = (CheckBox) itemView.findViewById(R.id.underTreatment);
        cv = (CardView) itemView.findViewById(R.id.cardPMH);
    }

    public void setPmh(PreviousMedicalHistory pmh) {
        this.pmh = pmh;
    }
}
