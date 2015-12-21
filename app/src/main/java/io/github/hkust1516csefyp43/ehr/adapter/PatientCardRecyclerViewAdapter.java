package io.github.hkust1516csefyp43.ehr.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.amulyakhare.textdrawable.TextDrawable;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import io.github.hkust1516csefyp43.ehr.CircleTransform;
import io.github.hkust1516csefyp43.ehr.R;
import io.github.hkust1516csefyp43.ehr.Utils;
import io.github.hkust1516csefyp43.ehr.pojo.Patient;
import io.github.hkust1516csefyp43.ehr.value.Cache;
import io.github.hkust1516csefyp43.ehr.view.viewholder.patientCardViewHolder;


/**
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
        return new patientCardViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_patient, parent, false), context);
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
            ph.subtitle.setText(aPatient.getGender() + " / " + Utils.lastSeenToString(aPatient.getLastSeen()) + " / age (TBC)");
            //TODO 1) Check if photo is null >> TextDrawable or load image
            //TODO 2) find a range of acceptable RGB (currently 50+)
            //TODO 3) save color combination of each patient? (if just getting from cache, just use the old color?)
            //e.g. Gmail: each letter have their own pre-defined color combination
            Drawable backup = TextDrawable.builder().buildRound(Utils.getTextDrawableText(aPatient), Utils.getRandomColor());

            String[] uris = {"http://cdn-img.instyle.com/sites/default/files/styles/428xflex/public/images/2012/TRANSFORMATIONS/2005-adam-levine-400_0.jpg?itok=n_C1oYNP", "https://fbcdn-sphotos-h-a.akamaihd.net/hphotos-ak-xfl1/v/t1.0-9/11351352_10153368808349875_6517669903500714504_n.jpg?oh=f892debb6921a5004adf4719f42e6de4&oe=57112EFD&__gda__=1461375548_a4cf7741cb74652e0d733b02ec293588", "http://hkust1516csefyp43.github.io/img/team/ricky.jpg"};
            if (position < uris.length) {
                Uri imageUri = Uri.parse(uris[position]);
                Glide.with(context).load(imageUri)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .transform(new CircleTransform(context))
                        .thumbnail(0.01f)
                        .placeholder(backup)
                        .fallback(backup)
                        .into(ph.proPic);
            } else {
                ph.proPic.setImageDrawable(backup);
            }

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
