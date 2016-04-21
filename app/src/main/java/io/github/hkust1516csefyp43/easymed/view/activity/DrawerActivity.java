package io.github.hkust1516csefyp43.easymed.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;
import com.mikepenz.aboutlibraries.Libs;
import com.mikepenz.aboutlibraries.LibsBuilder;
import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.context.IconicsLayoutInflater;
import com.squareup.okhttp.OkHttpClient;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.github.hkust1516csefyp43.easymed.utility.Cache;
import io.github.hkust1516csefyp43.easymed.utility.Const;
import io.github.hkust1516csefyp43.easymed.POJO.Notification;
import io.github.hkust1516csefyp43.easymed.POJO.User;
import io.github.hkust1516csefyp43.easymed.R;
import io.github.hkust1516csefyp43.easymed.listener.OnFragmentInteractionListener;
import io.github.hkust1516csefyp43.easymed.listener.OnPatientsFetchedListener;
import io.github.hkust1516csefyp43.easymed.utility.v2API;
import io.github.hkust1516csefyp43.easymed.view.fragment.station.ConsultationFragment;
import io.github.hkust1516csefyp43.easymed.view.fragment.station.PharmacyFragment;
import io.github.hkust1516csefyp43.easymed.view.fragment.station.TriageFragment;
import retrofit.GsonConverterFactory;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


public class DrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, OnFragmentInteractionListener, OnPatientsFetchedListener, MaterialDialog.SingleButtonCallback {
  public final static String TAG = DrawerActivity.class.getSimpleName();
  private User currentUser;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    LayoutInflaterCompat.setFactory(getLayoutInflater(), new IconicsLayoutInflater(getDelegate()));
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_drawer);

    Log.d(TAG, "before");
    new ThingsToDoInBackground().execute();
    Log.d(TAG, "after");

    currentUser = Cache.CurrentUser.getUser(getApplicationContext());

    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    if (navigationView != null) {
      TextView uEmail = (TextView) navigationView.getHeaderView(0).findViewById(R.id.textView);
      uEmail.setText(currentUser.getEmail());
    }
    navigationView.getHeaderView(0).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(getBaseContext(), ProfileActivity.class);
        startActivity(intent);
      }
    });


    navigationView.setNavigationItemSelectedListener(this);
    Menu menu = navigationView.getMenu();
    MenuItem menuItem = menu.findItem(R.id.nav_triage);
    if (menuItem != null) {
      menuItem.setIcon(new IconicsDrawable(this).icon(CommunityMaterial.Icon.cmd_thermometer).color(Color.GRAY).actionBar().paddingDp(2));
    }
    menuItem = menu.findItem(R.id.nav_consultation);
    if (menuItem != null) {
      menuItem.setIcon(new IconicsDrawable(this).icon(CommunityMaterial.Icon.cmd_hospital).color(Color.GRAY).actionBar().paddingDp(2));
    }
    menuItem = menu.findItem(R.id.nav_pharmacy);
    if (menuItem != null) {
      menuItem.setIcon(new IconicsDrawable(this).icon(CommunityMaterial.Icon.cmd_pharmacy).color(Color.GRAY).actionBar().paddingDp(2));
    }
    menuItem = menu.findItem(R.id.nav_inventory);
    if (menuItem != null) {
      menuItem.setIcon(new IconicsDrawable(this).icon(FontAwesome.Icon.faw_medkit).color(Color.GRAY).actionBar().paddingDp(2));
    }
    menuItem = menu.findItem(R.id.nav_statistics);
    if (menuItem != null) {
      menuItem.setIcon(new IconicsDrawable(this).icon(CommunityMaterial.Icon.cmd_file_chart).color(Color.GRAY).actionBar().paddingDp(2));
    }
    menuItem = menu.findItem(R.id.nav_admin);
    if (menuItem != null) {
      menuItem.setIcon(new IconicsDrawable(this).icon(FontAwesome.Icon.faw_male).color(Color.GRAY).actionBar().paddingDp(2));
    }
    menuItem = menu.findItem(R.id.nav_settings);
    if (menuItem != null) {
      menuItem.setIcon(new IconicsDrawable(this).icon(GoogleMaterial.Icon.gmd_settings).color(Color.GRAY).actionBar().paddingDp(2));
    }
    menuItem = menu.findItem(R.id.nav_about);
    if (menuItem != null) {
      menuItem.setIcon(new IconicsDrawable(this).icon(GoogleMaterial.Icon.gmd_info).color(Color.GRAY).actionBar().paddingDp(2));
    }
    menuItem = menu.findItem(R.id.nav_logout);
    if (menuItem != null) {
      menuItem.setIcon(new IconicsDrawable(this).icon(GoogleMaterial.Icon.gmd_exit_to_app).color(Color.GRAY).actionBar().paddingDp(2));
    }

    FrameLayout frameLayout = (FrameLayout) findViewById(R.id.fragment_container);
    if (frameLayout != null) {
      TriageFragment triageFragment = new TriageFragment();
      getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, triageFragment).commit();
    }
  }

  private void setIcon(int id, Drawable drawable) {

  }

  @Override
  public void onBackPressed() {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    if (drawer != null) {
      if (drawer.isDrawerOpen(GravityCompat.START)) {
        drawer.closeDrawer(GravityCompat.START);
      } else {
        super.onBackPressed();
      }
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.drawer, menu);
    MenuItem mi = menu.findItem(R.id.notification);
    if (mi != null) {
      Log.d(TAG, "setting notification icon");
      mi.setIcon(new IconicsDrawable(this).icon(GoogleMaterial.Icon.gmd_notifications_none).color(Color.WHITE).actionBar());
    } else {
      Log.d(TAG, "cannot set notification icon");
    }
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will automatically handle clicks on the Home/Up button, so long as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();
    if (id == R.id.notification) {
      Intent i = new Intent(this, NotificationActivity.class);
      startActivity(i);
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @SuppressWarnings("StatementWithEmptyBody")
  @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    // Handle navigation view item clicks here.
    int id = item.getItemId();

    if (id == R.id.nav_triage) {
      TriageFragment triageFragment = new TriageFragment();
      FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
      fragmentTransaction.replace(R.id.fragment_container, triageFragment);
      fragmentTransaction.addToBackStack(null);
      fragmentTransaction.commit();
    } else if (id == R.id.nav_consultation) {
      ConsultationFragment consultationFragment = new ConsultationFragment();
      FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
      fragmentTransaction.replace(R.id.fragment_container, consultationFragment);
      fragmentTransaction.addToBackStack(null);
      fragmentTransaction.commit();

    } else if (id == R.id.nav_pharmacy) {
      PharmacyFragment pharmacyFragment = new PharmacyFragment();
      FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
      fragmentTransaction.replace(R.id.fragment_container, pharmacyFragment);
      fragmentTransaction.addToBackStack(null);
      fragmentTransaction.commit();
    } else if (id == R.id.nav_inventory) {
      //change toolbar name
      ActionBar actionBar = getSupportActionBar();
      if (actionBar != null) {
        actionBar.setTitle("Inventory");
      }
      //swap fragment to InventoryFragment
    } else if (id == R.id.nav_statistics) {
      //change toolbar name
      ActionBar actionBar = getSupportActionBar();
      if (actionBar != null) {
        actionBar.setTitle("Statistics");
      }
      //?
    } else if (id == R.id.nav_admin) {
      //change toolbar name
      ActionBar actionBar = getSupportActionBar();
      if (actionBar != null) {
        actionBar.setTitle("Admin");
      }
      //swap fragment to AdminFragment
    } else if (id == R.id.nav_settings) {
      //change toolbar name
      ActionBar actionBar = getSupportActionBar();
      if (actionBar != null) {
        actionBar.setTitle("Settings");
      }
      //swap fragment to SettingsFragment
    } else if (id == R.id.nav_about) {
      //Trigger AboutLibrary
      new LibsBuilder()
          .withActivityTitle("About")
          .withAboutIconShown(true)
          .withAboutVersionShown(true)
          .withVersionShown(true)
          .withActivityStyle(Libs.ActivityStyle.LIGHT_DARK_TOOLBAR)
          .start(DrawerActivity.this);
    } else if (id == R.id.nav_logout) {
      new MaterialDialog.Builder(this)
          .theme(Theme.LIGHT)
          .autoDismiss(true)
          .content("Are you sure you want to logout?")
          .positiveText("Logout")
          //TODO icon?
          //TODO different color for +ve and -ve text
          .onPositive(this)
          .negativeText("Dismiss")
          .onNegative(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
              dialog.dismiss();
            }
          })
          .show();

    }

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    if (drawer != null) {
      drawer.closeDrawer(GravityCompat.START);
    }
    return true;
  }

  @Override
  public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
    Cache.CurrentUser.logout(this);
    Intent intent = new Intent(this, LoginActivity.class);
    startActivity(intent);
    finish();
  }

  @Override
  public void onFragmentInteraction(Uri uri) {

  }

  @Override
  public void updateTabTitleCounter(int whichPage, int howMuch) {
    Log.d(TAG, "wp = " + whichPage + "; hm = " + howMuch);
  }

  private class ThingsToDoInBackground extends AsyncTask<Void, Void, Void> {

    public ThingsToDoInBackground() {
      Log.d(TAG, "ast constructor");
    }

    @Override
    protected Void doInBackground(Void... params) {
      //fetch notifications
      Log.d(TAG, "do in background");

      OkHttpClient ohc1 = new OkHttpClient();
      ohc1.setReadTimeout(1, TimeUnit.MINUTES);
      ohc1.setConnectTimeout(1, TimeUnit.MINUTES);

      Retrofit retrofit = new Retrofit
          .Builder()
          .baseUrl(Const.Database.CLOUD_API_BASE_URL_121_dev)
          .addConverterFactory(GsonConverterFactory.create(Const.GsonParserThatWorksWithPGTimestamp))
          .client(ohc1)
          .build();
      v2API.notifications notificationService = retrofit.create(v2API.notifications.class);
      Call<List<Notification>> notificationList = notificationService.getMyNotifications("1");
      notificationList.enqueue(new Callback<List<Notification>>() {
        @Override
        public void onResponse(Response<List<Notification>> response, Retrofit retrofit) {
          Log.d(TAG, response.toString());
          Log.d(TAG, response.body().toString());
          Cache.CurrentUser.setNotifications(getBaseContext(), response.body());
        }

        @Override
        public void onFailure(Throwable t) {

        }
      });
      return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
      super.onPostExecute(aVoid);
      Log.d(TAG, "ope");
    }
  }
}
