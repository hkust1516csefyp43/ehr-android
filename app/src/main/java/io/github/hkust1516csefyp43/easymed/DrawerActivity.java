package io.github.hkust1516csefyp43.easymed;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.context.IconicsLayoutInflater;

public class DrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, TriageFragment.OnFragmentInteractionListener {
  public final static String TAG = DrawerActivity.class.getSimpleName();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    LayoutInflaterCompat.setFactory(getLayoutInflater(), new IconicsLayoutInflater(getDelegate()));
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_drawer);
//    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//    setSupportActionBar(toolbar);
//
//    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//    fab.setOnClickListener(new View.OnClickListener() {
//      @Override
//      public void onClick(View view) {
//        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//            .setAction("Action", null).show();
//      }
//    });
//
//    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//    if (drawer != null) {
//      drawer.setDrawerListener(toggle);
//      toggle.syncState();
//    }

    if (navigationView != null) {
      navigationView.setNavigationItemSelectedListener(this);
      Menu menu = navigationView.getMenu();
      MenuItem menuItem = menu.findItem(R.id.nav_triage);
      if (menuItem != null) {
//        menuItem.setIcon(new )
      }
    }

    FrameLayout frameLayout = (FrameLayout) findViewById(R.id.fragment_container);
    if (frameLayout != null) {
      ConsultationFragment consultationFragment = new ConsultationFragment();
      getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, consultationFragment).commit();
    }
  }

  private void setIcon(int id, Drawable drawable) {

  }

  @Override
  public void onBackPressed() {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
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
      //change toolbar name
      ActionBar actionBar = getSupportActionBar();
      if (actionBar != null) {
        actionBar.setTitle("Triage");
      }
      //swap fragment to TriageFragment
    } else if (id == R.id.nav_consultation) {
      //change toolbar name
//      ActionBar actionBar = getSupportActionBar();
//      if (actionBar != null) {
//        actionBar.setTitle("Consultation");
//      }
      //swap fragment to ConsultationFragment
    } else if (id == R.id.nav_pharmacy) {
      //change toolbar name
      ActionBar actionBar = getSupportActionBar();
      if (actionBar != null) {
        actionBar.setTitle("Pharmacy");
      }
      //swap fragment to TriageFragment
    } else if (id == R.id.nav_inventory) {
      //change toolbar name
      ActionBar actionBar = getSupportActionBar();
      if (actionBar != null) {
        actionBar.setTitle("Inventory");
      }
      //swap fragment to InventoryFragment
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
    } else if (id == R.id.nav_logout) {
      //Confirmation dialog
    }

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    if (drawer != null) {
      drawer.closeDrawer(GravityCompat.START);
    }
    return true;
  }

  @Override
  public void onFragmentInteraction(Uri uri) {

  }

  private class ThingsToDoInBackground extends AsyncTask<Void, Void, Void> {

    public ThingsToDoInBackground() {

    }

    @Override
    protected Void doInBackground(Void... params) {
      //fetch notifications
      return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
      super.onPostExecute(aVoid);
    }
  }
}
