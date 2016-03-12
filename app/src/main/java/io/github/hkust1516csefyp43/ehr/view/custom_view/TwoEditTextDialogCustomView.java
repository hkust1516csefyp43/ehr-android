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

        TextInputLayout til = new TextInputLayout(context);
        actv = new AutoCompleteTextView(context);
        actv.setDropDownBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.primary_text_color)));
        actv.setHint(title);
        if (suggestions != null) {
            String[] list = suggestions.toArray(new String[0]);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_selectable_list_item, list);
            actv.setAdapter(adapter);
        }

        TextInputLayout til2 = new TextInputLayout(context);

        et = new EditText(context);
        et.setLines(8);
        et.setHint("Description");

        til.addView(actv);
        til2.addView(et);

        this.addView(til);
        this.addView(til2);
    }

    public static TwoEditTextDialogCustomView newInstance(Context context, @Nullable ArrayList<String> suggestions, @Nullable String title, String text1, @Nullable String text2) {
        TwoEditTextDialogCustomView tetdcv = new TwoEditTextDialogCustomView(context, suggestions, title);
        tetdcv.setTexts(text1, text2);
        return tetdcv;
    }

    public ArrayList<String> getData() {
        ArrayList<String> output = new ArrayList<>();
        output.add(actv.getText().toString());
        output.add(et.getText().toString());
        return output;
    }

    public void clearData() {
        actv.setText("");
        et.setText("");
    }

    public void setText1(String t) {
        actv.setText(t);
    }

    public void setText2(String t) {
        et.setText(t);
    }

    public void setTexts(String t1, String t2) {
        setText1(t1);
        setText2(t2);
    }
}
