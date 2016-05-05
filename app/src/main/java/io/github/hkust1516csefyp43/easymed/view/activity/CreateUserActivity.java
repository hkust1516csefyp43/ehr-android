package io.github.hkust1516csefyp43.easymed.view.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.iconics.IconicsDrawable;

import io.github.hkust1516csefyp43.easymed.R;

public class CreateUserActivity extends AppCompatActivity {
  public static final String TAG = CreateUserActivity.class.getSimpleName();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_create_user);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setTitle("Users");
      actionBar.setDisplayHomeAsUpEnabled(true);
      actionBar.setDisplayShowHomeEnabled(true);
    }

    FloatingActionButton fabScan = (FloatingActionButton) findViewById(R.id.fabScan);
    if (fabScan != null) {
      fabScan.setIconDrawable(new IconicsDrawable(getBaseContext()).icon(CommunityMaterial.Icon.cmd_qrcode).actionBar().color(Color.WHITE));
      fabScan.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          Log.d(TAG, "scan qr code");
        }
      });
    }

    FloatingActionButton fabNew = (FloatingActionButton) findViewById(R.id.fabScratch);
    if (fabNew != null) {
      fabNew.setIconDrawable(new IconicsDrawable(getBaseContext()).icon(CommunityMaterial.Icon.cmd_account_plus).actionBar().color(Color.WHITE));
      fabNew.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          Log.d(TAG, "start from scratch");
        }
      });
    }

//    FloatingActionsMenu fab = (FloatingActionsMenu) findViewById(R.id.fab);
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
