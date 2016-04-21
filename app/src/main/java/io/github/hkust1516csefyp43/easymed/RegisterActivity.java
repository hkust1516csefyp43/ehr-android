package io.github.hkust1516csefyp43.easymed;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class RegisterActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_register);
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
    finish();
    startActivity(intent);
  }
}
