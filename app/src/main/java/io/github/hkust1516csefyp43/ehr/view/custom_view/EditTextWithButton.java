package io.github.hkust1516csefyp43.ehr.view.custom_view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;

import io.github.hkust1516csefyp43.ehr.R;
import io.github.hkust1516csefyp43.ehr.listener.MoreOrLessEditTextListener;

/**
 * A custom view
 * A edit text with a button on the RHS (either cross or add)
 * What it looks like:
 * |--------------------------------------------|
 * | Hint                                  \  / |
 * |                                        X   |
 * | ------------------------------------- / \  |
 * |--------------------------------------------|
 * <p/>
 * Created by Louis on 10/3/16.
 */
public class EditTextWithButton extends RelativeLayout {
    public static final int ICON_ADD = 0;
    public static final int ICON_DELETE = 1;
    private int position;
    private int icon;

    public EditTextWithButton(final Context context, final int Icon, @Nullable String Hint, final MoreOrLessEditTextListener moletl, int p) {
        super(context);
        //TODO implement TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.EditTextWithButton, 0, 0);
        position = p;
        icon = Icon;
        final ImageView button = new ImageView(context);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        params.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        params.setMargins(4, 4, 4, 4);
        button.setLayoutParams(params);
        button.setId(123321);
        IconicsDrawable id;
        switch (Icon) {
            case 0:     //ADD
                id = new IconicsDrawable(context, GoogleMaterial.Icon.gmd_add).color(getResources().getColor(R.color.secondary_text_color)).sizeDp(18);
                break;
            case 1:     //delete
                id = new IconicsDrawable(context, GoogleMaterial.Icon.gmd_clear).color(getResources().getColor(R.color.secondary_text_color)).sizeDp(18);
                break;
            default:
                id = new IconicsDrawable(context, GoogleMaterial.Icon.gmd_add).color(getResources().getColor(R.color.secondary_text_color)).sizeDp(18);

        }
        button.setImageDrawable(id);
        if (moletl != null) {
            button.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("qqq120", "clicking");
                    if (icon == ICON_ADD) {
                        button.setImageDrawable(new IconicsDrawable(context, GoogleMaterial.Icon.gmd_clear).color(getResources().getColor(R.color.secondary_text_color)).sizeDp(18));
                        icon = ICON_DELETE;
                        moletl.onAdd();
                    } else {
                        moletl.onRemove(position);
                    }
                }
            });
        }

        TextInputEditText tiet = new TextInputEditText(context);
        if (Hint != null)
            tiet.setHint(Hint);
        params = new LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        tiet.setLayoutParams(params);

        TextInputLayout til = new TextInputLayout(context);
        params = new LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.LEFT_OF, button.getId());
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
        til.setLayoutParams(params);
        til.addView(tiet);

        this.addView(button);
        this.addView(til);
    }

}
