package io.github.hkust1516csefyp43.ehr.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amulyakhare.textdrawable.TextDrawable;

import java.util.List;
import java.util.Random;

import io.github.hkust1516csefyp43.ehr.R;
import io.github.hkust1516csefyp43.ehr.Utils;
import io.github.hkust1516csefyp43.ehr.pojo.Patient;
import io.github.hkust1516csefyp43.ehr.value.Cache;
import io.github.hkust1516csefyp43.ehr.view.viewholder.patientCardViewHolder;


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
        if (context != null) {
            patientCardViewHolder ph = (patientCardViewHolder) holder;
            StringBuilder name = new StringBuilder();
            Patient aPatient = Cache.getPostTriagePatients(context).get(position);
            ph.setPatient(aPatient);
            name.append(aPatient.getFirstName());
            name.append(" ");
            if (aPatient.getLastName() != null) {
                name.append(aPatient.getLastName());
            }
            ph.patientName.setText(name.toString());
            ph.subtitle.setText(aPatient.getGender() + " / " + Utils.lastSeenToString(aPatient.getLastSeen()));
            //TODO 1) Check if photo is null
            //TODO 2) find a range of acceptable RGB
            //TODO 3) save color combination of each patient (if no fetching, just use the old color)
            //e.g. Gmail: each letter have their own pre-defined color combination
            Random rand = new Random();
            ph.proPic.setImageDrawable(TextDrawable.builder().beginConfig().width(64).height(64).endConfig().buildRound(Utils.getTextDrawableText(aPatient), Color.rgb(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255))));
//            Uri imageUri = Uri.parse("https://avatars0.githubusercontent.com/u/3873011?v=3&s=460");
//            Glide.with(context).load(imageUri)
//                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                    .thumbnail(0.1f)
//                    .placeholder(R.drawable.ehr_logo)
//                    .fallback(R.drawable.ehr_logo)
//                    .into(ph.proPic);
        }
    }

    @Override
    public int getItemCount() {
        if (context != null) {
            List<Patient> temp = Cache.getPostTriagePatients(context);
            if (temp != null) {
                return temp.size();
            } else
                return 0;
        } else
            return 0;
    }
}
