package io.github.hkust1516csefyp43.ehr.view.custom_view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

import io.github.hkust1516csefyp43.ehr.R;

/**
 * Created by Louis on 11/3/16.
 */
public class TwoEditTextDialogCustomView extends LinearLayout {
    AutoCompleteTextView actv;
    EditText et;

    public TwoEditTextDialogCustomView(Context context, @Nullable ArrayList<String> suggestions, @Nullable String title) {
        super(context, null, R.style.AppTheme2);
        this.setOrientation(VERTICAL);

        actv = new AutoCompleteTextView(context);
        actv.setDropDownBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.md_divider_black)));
        actv.setHint(title);
        if (suggestions != null) {
//            actv.setHintTextColor(getResources().getColor(R.color.primary_text_color));
            String[] list = suggestions.toArray(new String[0]);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, list);
            actv.setAdapter(adapter);
        }

        TextInputLayout til = new TextInputLayout(context);

        et = new EditText(context);
        et.setLines(6);
        et.setHint("Description");

        til.addView(et);

        this.addView(actv);
        this.addView(til);
    }

    public ArrayList<String> getData() {
        ArrayList<String> output = new ArrayList<>();
        output.add(actv.getText().toString());
        output.add(et.getText().toString());
        return output;
    }
}
