package io.github.hkust1516csefyp43.easymed.view.activity;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.iconics.IconicsDrawable;

import org.json.JSONException;
import org.json.JSONObject;

import io.github.hkust1516csefyp43.easymed.R;
import io.github.hkust1516csefyp43.easymed.utility.Const;

public class SignUpActivity extends AppCompatActivity {
  public static final String TAG = SignUpActivity.class.getSimpleName();

  //view
  private TextInputEditText tietUsername;
  private TextInputEditText tietPassword1;
  private TextInputEditText tietPassword2;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sign_up);
    final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    if (toolbar != null) {
      setSupportActionBar(toolbar);
      ActionBar actionBar = getSupportActionBar();
      if (actionBar != null) {
        actionBar.setTitle("Sigh up");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
      }
    }

    findViewsById();

    final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    if (fab != null) {
      fab.setImageDrawable(new IconicsDrawable(this).actionBar().color(Color.WHITE).icon(CommunityMaterial.Icon.cmd_check));
      fab.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          //TODO check
          if (isEveryBoxValid()) {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            try {
              BitMatrix bitMatrix = qrCodeWriter.encode(generateUserAccountData().toString(), BarcodeFormat.QR_CODE, 1024, 1024);  //TODO replace with the user input
              int height = bitMatrix.getHeight();
              int width = bitMatrix.getWidth();
              Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
              for (int x = 0; x < width; x++){
                for (int y = 0; y < height; y++){
                  bmp.setPixel(x, y, bitMatrix.get(x,y) ? Color.BLACK : Color.WHITE);
                }
              }
              Dialog builder = new Dialog(SignUpActivity.this);
              builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
              builder.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
              builder.setCanceledOnTouchOutside(true);
              builder.setCancelable(true);
              ImageView imageView = new ImageView(SignUpActivity.this);
              imageView.setImageBitmap(bmp);

              TextView textView = new TextView(SignUpActivity.this);
              textView.setText("Ask admin to create your account");

              LinearLayout linearLayout = new LinearLayout(SignUpActivity.this);
              linearLayout.setOrientation(LinearLayout.VERTICAL);
              linearLayout.addView(textView);
              linearLayout.addView(imageView);

              builder.addContentView(linearLayout, new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
              builder.show();
            } catch (WriterException e) {
              e.printStackTrace();
            }
          }
        }
      });
    }
  }

  private void findViewsById() {
    tietUsername = (TextInputEditText) findViewById(R.id.etUsername);
    tietPassword1 = (TextInputEditText) findViewById(R.id.etPassword1);
    tietPassword2 = (TextInputEditText) findViewById(R.id.etPassword2);
  }

  private boolean isEveryBoxValid() {
    if (tietUsername != null && tietPassword1 != null && tietPassword2 != null) {
      if (tietUsername.getText() != null && !tietUsername.getText().toString().equals("") && tietUsername.getText().length() > 0) {
        if (tietPassword1.getText() != null && !tietPassword1.getText().toString().equals("") && tietPassword1.getText().length() > 0) {
          if (tietPassword2.getText() != null && !tietPassword2.getText().toString().equals("") && tietPassword2.getText().length() > 0) {
            if (tietPassword1.getText().toString().equals(tietPassword2.getText().toString())) {
              return true;
            } else {
              Log.d(TAG, "pw1 != pw2");
              tietPassword2.setError("Not match");
              return false;
            }
          } else {
            Log.d(TAG, "empty pw2");
            tietPassword2.setError("Cannot be empty");
            return false;
          }
        } else {
          Log.d(TAG, "empty pw1");
          tietPassword1.setError("Cannot be empty");
          return false;
        }
      } else {
        Log.d(TAG, "empty username");
        tietUsername.setError("Cannot be empty");
        return false;
      }
    } else {
      Log.d(TAG, "Something wrong. Can't find the views.");
      return false;
    }
  }

  private JSONObject generateUserAccountData() {
    JSONObject jsonObject = new JSONObject();
    try {
      jsonObject.put(Const.SignUp.USERNAME, tietUsername.getText().toString());
      jsonObject.put(Const.SignUp.PASSWORD, tietPassword1.getText().toString());
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return jsonObject;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        // app icon in action bar clicked; goto parent activity.
        this.finish();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }
}
