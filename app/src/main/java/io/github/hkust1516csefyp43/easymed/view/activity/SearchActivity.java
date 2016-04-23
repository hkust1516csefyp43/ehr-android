package io.github.hkust1516csefyp43.easymed.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import io.github.hkust1516csefyp43.easymed.R;
import io.github.hkust1516csefyp43.easymed.utility.Const;
import io.github.hkust1516csefyp43.easymed.view.fragment.PatientListFragment;

public class SearchActivity extends AppCompatActivity {
  private MaterialSearchView searchView;
  private boolean isTriage;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_search);

    Intent intent = getIntent();
    if (intent != null) {
      intent.getBooleanExtra(Const.BundleKey.IS_TRIAGE, true);
    }

    searchView = (MaterialSearchView) findViewById(R.id.search_view);
    if (searchView != null) {
      searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
          //TODO PatientListFragment handles it
          PatientListFragment patientListFragment;
          if (isTriage) {
            patientListFragment = PatientListFragment.newInstance(Const.PatientListPageId.TRIAGE_SEARCH, query);
          } else {
            patientListFragment = PatientListFragment.newInstance(Const.PatientListPageId.CONSULTATION_SEARCH, query);
          }
          if (patientListFragment != null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, patientListFragment).commit();
          }
          return true;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
          //TODO some kind of auto complete?
          return false;
        }
      });
    }

    //triage or consultation
    //from extra: patient




    //bottom >> PatientListFragment


    //(search == no current visit, triage and consultation)
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_search, menu);
    MenuItem item = menu.findItem(R.id.action_search);
    if (searchView != null)
      searchView.setMenuItem(item);
    return true;
  }

  @Override
  public void onBackPressed() {
    if (searchView != null) {
      if (searchView.isSearchOpen()) {
        searchView.closeSearch();
      } else {
        super.onBackPressed();
      }
    } else {
      super.onBackPressed();
    }
  }
}
