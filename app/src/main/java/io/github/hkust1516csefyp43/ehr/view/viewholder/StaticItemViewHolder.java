package io.github.hkust1516csefyp43.ehr.view.viewholder;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;

import io.github.hkust1516csefyp43.ehr.R;

/**
 * Created by Louis on 22/3/16.
 */
public class StaticItemViewHolder extends RecyclerView.ViewHolder {
    public TextView tvTitle;
    public ImageView iconEdit;

    public StaticItemViewHolder(View itemView, Context context) {
        super(itemView);
        tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
        iconEdit = (ImageView) itemView.findViewById(R.id.ivEditIcon);
        iconEdit.setImageDrawable(new IconicsDrawable(context, GoogleMaterial.Icon.gmd_edit).color(Color.GRAY).paddingDp(3).sizeDp(22));
        //TODO onclick
    }

}
