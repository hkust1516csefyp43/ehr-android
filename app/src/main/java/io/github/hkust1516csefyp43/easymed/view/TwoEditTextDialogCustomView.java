package io.github.hkust1516csefyp43.easymed.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import io.github.hkust1516csefyp43.easymed.R;


/**
 * Created by Louis on 11/3/16.
 */
public class TwoEditTextDialogCustomView extends LinearLayout {
  AutoCompleteTextView actv;
  TextView tv;
  EditText et;

  /**
   *
   * @param context
   * @param suggestions i.e. keywords suggestions
   * @param title       of the
   */
  public TwoEditTextDialogCustomView(Context context, @Nullable ArrayList<String> suggestions, @Nullable String title) {
    super(context, null, R.style.AppTheme);
    this.setOrientation(VERTICAL);

    TextInputLayout til = new TextInputLayout(context);
    actv = new AutoCompleteTextView(context);
    actv.setDropDownBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.primary_text_color)));
    actv.setHint(title);
    Log.d("qqq10", "idk");
    if (suggestions != null) {
      Log.d("qqq7", suggestions.toString());
      String[] list = new String[suggestions.size()];
      list = suggestions.toArray(list);
      Log.d("qqq8", list.toString());
      ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_selectable_list_item, list);
      actv.setThreshold(1);
      actv.setAdapter(adapter);
    }

    TextInputLayout til2 = new TextInputLayout(context);

    et = new EditText(context);
    et.setLines(4);
    et.setHint("Description");

    til.addView(actv);
    til2.addView(et);

    this.addView(til);
    this.addView(til2);
  }

  /**
   * @param context
   * @param suggestions
   * @param title
   * @param text1
   * @param text2
   * @param displaySwitch
   */
  public TwoEditTextDialogCustomView(Context context, @Nullable ArrayList<String> suggestions, @Nullable String title, @Nullable String text1, @Nullable String text2, boolean displaySwitch) {
    super(context, null, R.style.AppTheme);
    this.setOrientation(VERTICAL);

    Log.d("qqq9", title + String.valueOf(displaySwitch));
    if (!displaySwitch) {
      Log.d("qqq9", title + "ok");
      TextInputLayout til = new TextInputLayout(context);
      actv = new AutoCompleteTextView(context);
      actv.setHint(title);
      if (suggestions != null) {
        Log.d("qqq7", suggestions.toString());
        String[] list = new String[suggestions.size()];
        list = suggestions.toArray(list);
        Log.d("qqq8", list.toString());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_selectable_list_item, list);
        actv.setThreshold(1);
        actv.setAdapter(adapter);
      }
      if (text1 != null)
        actv.setText(text1);
      til.addView(actv);
      this.addView(til);
    } else {
      Log.d("qqq9", "wtf");
      tv = new TextView(context);
      tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 22);
      tv.setGravity(Gravity.CENTER_HORIZONTAL);
      tv.setPadding(0, 0, 0, 16);
      LayoutParams llp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
      tv.setLayoutParams(llp);
      if (text1 != null){
        actv = null;
        tv.setText(text1);
      }
      this.addView(tv);
    }

    TextInputLayout til2 = new TextInputLayout(context);

    et = new EditText(context);
    et.setLines(8);
    et.setHint("Description");
    if (text2 != null)
      et.setText(text2);

    til2.addView(et);

    this.addView(til2);
  }

  public ArrayList<String> getData() {
    ArrayList<String> output = new ArrayList<>();
    if (actv != null) {
      output.add(actv.getText().toString());
    } else {
      output.add(tv.getText().toString());
    }
    output.add(et.getText().toString());
    return output;
  }

  public void clearData() {
    if (actv != null)
      actv.setText("");
    if (et != null)
      et.setText("");
  }
}
