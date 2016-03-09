package io.github.hkust1516csefyp43.ehr.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import io.github.hkust1516csefyp43.ehr.R;
import io.github.hkust1516csefyp43.ehr.pojo.PreviousMedicalHistory;
import io.github.hkust1516csefyp43.ehr.view.viewholder.PMHCardViewHolder;

import static android.support.v7.widget.RecyclerView.Adapter;
import static android.support.v7.widget.RecyclerView.ViewHolder;

/**
 * Created by kalongip on 4/3/16.
 */
public class PMHCardRecyclerViewAdapter extends Adapter {

    Context context;
    List<PreviousMedicalHistory> data;

    public PMHCardRecyclerViewAdapter(List<PreviousMedicalHistory> source) {
        data = source;
    }

    public PMHCardRecyclerViewAdapter(Context context){context = this.context;}

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PMHCardViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_pmh, parent, false), context);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (context != null){
            PMHCardViewHolder ph = (PMHCardViewHolder) holder;
            ph.medicineName.setText(data.get(position).getMedicineName());
            ph.startDate.setText((CharSequence) data.get(position).getStartDate());
            ph.endDate.setText((CharSequence) data.get(position).getEndDate());
            if (data.get(position).isUnderTreatment() == true){
                ph.underTreatment.setChecked(true);
            }else
                ph.underTreatment.setChecked(false);
        }

    }

    @Override
    public int getItemCount() {
        if (context != null){
            return data.size();
        } else
        return 0;
    }
}
