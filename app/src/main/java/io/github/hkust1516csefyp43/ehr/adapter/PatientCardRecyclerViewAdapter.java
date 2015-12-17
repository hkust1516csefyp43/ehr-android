package io.github.hkust1516csefyp43.ehr.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.github.hkust1516csefyp43.ehr.R;
import io.github.hkust1516csefyp43.ehr.patientCardViewHolder;
import io.github.hkust1516csefyp43.ehr.pojo.Patient;
import io.github.hkust1516csefyp43.ehr.value.Cache;


/**
 * TODO The whole adapter
 * http://frank-zhu.github.io/android/2015/01/16/android-recyclerview-part-1/
 * http://frank-zhu.github.io/android/2015/02/25/android-recyclerview-part-2/
 * <p/>
 * Created by Louis on 3/11/15.
 */
public class PatientCardRecyclerViewAdapter extends RecyclerView.Adapter {
    LayoutInflater mLayoutInflater;
    Context context;

    public PatientCardRecyclerViewAdapter(Context context) {
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_patient, parent, false);
        return new patientCardViewHolder(itemView, context);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        patientCardViewHolder ph = (patientCardViewHolder) holder;
        StringBuilder name = new StringBuilder();
        Patient aPatient = Cache.getPatients().get(position);
        ph.setPatient(aPatient);
        name.append(aPatient.getFirstName());
        name.append(" ");
        if (aPatient.getLastName() != null) {
            name.append(aPatient.getLastName());
        }
        ph.patientName.setText(name.toString());
    }

    @Override
    public int getItemCount() {
        if (Cache.getPatients() == null) {
            return 0;
        } else
            return Cache.getPatients().size();
    }
}
