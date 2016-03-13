package io.github.hkust1516csefyp43.ehr.view.viewholder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;

import io.github.hkust1516csefyp43.ehr.R;
import io.github.hkust1516csefyp43.ehr.adapter.FragRecyclerViewAdapter;
import io.github.hkust1516csefyp43.ehr.view.custom_view.TwoEditTextDialogCustomView;

/**
 * Created by kalongip on 7/3/16.
 */
public class FragCardViewHolder extends RecyclerView.ViewHolder {
    public TextView cardTitle;
    public TextView cardDescription;
    public SwitchCompat cardSwitch;
    public boolean editableTitle = true;

    public FragCardViewHolder(View view, final Context context) {
        super(view);
        cardTitle = (TextView) view.findViewById(R.id.medicineName);
        cardDescription = (TextView) view.findViewById(R.id.medicalDescription);
        cardSwitch = (SwitchCompat) view.findViewById(R.id.scNull);
    }

    public FragCardViewHolder(View view, final Context c, final ArrayList<String> suggestions, final String title, final FragRecyclerViewAdapter adapter, final boolean d) {
        this(view, c);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cardSwitch.isChecked()) {
                    final TwoEditTextDialogCustomView tetdcv = new TwoEditTextDialogCustomView(c, suggestions, title, cardTitle.getText().toString(), cardDescription.getText().toString(), d);
                    new MaterialDialog.Builder(c)
                            .title("Add")
                            .customView(tetdcv, true)
                            .positiveText("Confirm")
                            .negativeText("Cancel")
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    ArrayList<String> data = tetdcv.getData();
                                    tetdcv.clearData();
                                    Log.d("qqq141", data.toString());
//                                    adapter.addCard(new Card(data.get(0), data.get(1)));
                                    //TODO not add card, change card
                                    //TODO maybe just move this whole block back out to adapter (use listener?)
                                }
                            })
                            .onNegative(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    tetdcv.clearData();
                                    dialog.dismiss();
                                }
                            })
                            .show();
                }
            }
        });
    }

    public void setEditableTitle(boolean editableTitle) {
        this.editableTitle = editableTitle;
    }
}
