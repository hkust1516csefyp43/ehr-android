package io.github.hkust1516csefyp43.ehr.view.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import io.github.hkust1516csefyp43.ehr.R;

/**
 * Created by kalongip on 7/3/16.
 */
public class FragCardViewHolder extends RecyclerView.ViewHolder {
    public TextView cardTitle;
    public TextView cardDescription;

    public FragCardViewHolder(View view, final Context context) {
        super(view);
        cardTitle = (TextView) itemView.findViewById(R.id.medicineName);
        cardDescription = (TextView) itemView.findViewById(R.id.medicalDescription);
    }

}
