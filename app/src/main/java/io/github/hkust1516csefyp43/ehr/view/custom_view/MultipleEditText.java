package io.github.hkust1516csefyp43.ehr.view.custom_view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import io.github.hkust1516csefyp43.ehr.listener.MoreOrLessEditTextListener;

/**
 * Created by Louis on 10/3/16.
 */
public class MultipleEditText extends LinearLayout implements MoreOrLessEditTextListener {
    Context c;

    public MultipleEditText(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        this.setOrientation(VERTICAL);
        c = context;

        EditTextWithButton etwb = new EditTextWithButton(context, EditTextWithButton.ICON_ADD, "SSID", this, this.getChildCount() + 1);
        this.addView(etwb);
    }

    @Override
    public void onAdd() {
        this.addView(new EditTextWithButton(c, EditTextWithButton.ICON_ADD, "SSID", this, this.getChildCount() + 1));
    }

    @Override
    public void onRemove(int position) {
        if (this.getChildCount() > 1)
            this.removeViewAt(position - 1);
    }
}
