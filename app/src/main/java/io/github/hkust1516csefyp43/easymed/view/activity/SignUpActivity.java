package io.github.hkust1516csefyp43.easymed.view.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
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

import java.util.concurrent.TimeUnit;

import io.github.hkust1516csefyp43.easymed.R;
import io.github.hkust1516csefyp43.easymed.utility.Const;
import io.github.hkust1516csefyp43.easymed.utility.v2API;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpActivity extends AppCompatActivity {
  public static final String TAG = SignUpActivity.class.getSimpleName();

  //view
  private TextInputEditText tietUsername;
  private TextInputEditText tietPassword1;
  private TextInputEditText tietPassword2;
  private AppCompatSpinner acsRole;

  private boolean isQr;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sign_up);

    Intent intent = getIntent();
    if (intent != null) {
      isQr = intent.getBooleanExtra(Const.BundleKey.IS_QR, true);
    }

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

    if (acsRole != null && !isQr) {
      acsRole.setVisibility(View.VISIBLE);
      //TODO call API, get list of roles, display name of each role
    }

    final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    if (fab != null) {
      if (isQr) {
        fab.setImageDrawable(new IconicsDrawable(this).actionBar().color(Color.WHITE).icon(CommunityMaterial.Icon.cmd_qrcode));
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
      } else {
        if (isEveryBoxValid()) {
          fab.setImageDrawable(new IconicsDrawable(this).actionBar().color(Color.WHITE).icon(CommunityMaterial.Icon.cmd_check));
          fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              OkHttpClient.Builder ohc1 = new OkHttpClient.Builder();
              ohc1.readTimeout(1, TimeUnit.MINUTES);
              ohc1.connectTimeout(1, TimeUnit.MINUTES);
              Retrofit retrofit = new Retrofit
                  .Builder()
                  .baseUrl(Const.Database.getCurrentAPI())
                  .addConverterFactory(GsonConverterFactory.create(Const.GsonParserThatWorksWithPGTimestamp))
                  .client(ohc1.build())
                  .build();
              v2API.users userService = retrofit.create(v2API.users.class);
//          userService.addUser("1")
            }
          });
        }
      }
    }
  }

  private void findViewsById() {
    tietUsername = (TextInputEditText) findViewById(R.id.etUsername);
    tietPassword1 = (TextInputEditText) findViewById(R.id.etPassword1);
    tietPassword2 = (TextInputEditText) findViewById(R.id.etPassword2);
    acsRole = (AppCompatSpinner) findViewById(R.id.sRole);
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
