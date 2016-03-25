package io.github.hkust1516csefyp43.ehr.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.amulyakhare.textdrawable.TextDrawable;

import java.util.List;

import io.github.hkust1516csefyp43.ehr.R;
import io.github.hkust1516csefyp43.ehr.Utils;
import io.github.hkust1516csefyp43.ehr.value.Cache;
import io.github.hkust1516csefyp43.ehr.view.viewholder.patientCardViewHolder;


/**
 * Created by Louis on 3/11/15.
 */
public class PatientCardRecyclerViewAdapter extends RecyclerView.Adapter {
    Context context;
    int whichStation;

    public PatientCardRecyclerViewAdapter(Context context, int which) {
        this.context = context;
        whichStation = which;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new patientCardViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_patient, parent, false), context, whichStation);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (context != null) {
            patientCardViewHolder ph = (patientCardViewHolder) holder;
            StringBuilder name = new StringBuilder();
            List<io.github.hkust1516csefyp43.ehr.pojo.server_response.v2.Patient> listOfPatients = Cache.getPostTriagePatients(context);
            if (listOfPatients != null && listOfPatients.size() > 0) {
                io.github.hkust1516csefyp43.ehr.pojo.server_response.v2.Patient aPatient = listOfPatients.get(position);
                ph.setPatient(aPatient);
                //TODO a setting to change first or last name in the front
                if (aPatient.getLastName() != null) {
                    name.append(aPatient.getLastName());
                    name.append(" ");
                }
                name.append(aPatient.getFirstName());
                ph.patientName.setText(name.toString());
                //TODO replace gender with symbols (save screen space)
                //TODO once the age came you can start calculate that
                ph.subtitle.setText(" age (TBC)");
                //TODO 1) Check if photo is null >> TextDrawable or load image
                //TODO 2) find a range of acceptable RGB (currently 50+)
                //TODO 3) save color combination of each patient? (if just getting from cache, just use the old color?)
                //e.g. Gmail: each letter have their own pre-defined color combination
                String t = Utils.getTextDrawableText(aPatient);
                Drawable backup = TextDrawable.builder().buildRound(t, Utils.getTextDrawableColor(t));

                ph.proPic.setImageDrawable(backup);
            } else {
                //TODO no patient list >> display sth on UI
            }
        }
    }

    @Override
    public int getItemCount() {
        if (context != null) {
            List<io.github.hkust1516csefyp43.ehr.pojo.server_response.v2.Patient> temp = Cache.getPostTriagePatients(context);
            if (temp != null) {
                return temp.size();
            } else
                return 0;
        } else
            return 0;
    }
}
