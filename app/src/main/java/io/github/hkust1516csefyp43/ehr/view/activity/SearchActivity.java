package io.github.hkust1516csefyp43.ehr.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.quinny898.library.persistentsearch.SearchBox;
import com.quinny898.library.persistentsearch.SearchResult;
import com.squareup.okhttp.OkHttpClient;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.github.hkust1516csefyp43.ehr.R;
import io.github.hkust1516csefyp43.ehr.adapter.PatientCardRecyclerViewAdapter;
import io.github.hkust1516csefyp43.ehr.pojo.server_response.v2.Patient;
import io.github.hkust1516csefyp43.ehr.v2API;
import io.github.hkust1516csefyp43.ehr.value.Cache;
import io.github.hkust1516csefyp43.ehr.value.Const;
import io.github.hkust1516csefyp43.ehr.view.custom_view.FixedRecyclerView;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

//TODO dismiss this activity when someone click a card
public class SearchActivity extends AppCompatActivity {
  SearchBox searchBox;
  FixedRecyclerView recyclerView;
  int whichStation;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_search);

    Cache.setWhichStation(this, whichStation);

    searchBox = (SearchBox) findViewById(R.id.searchbox);
    searchBox.addSearchable(new SearchResult("{search history}", new IconicsDrawable(this, GoogleMaterial.Icon.gmd_history).color(getResources().getColor(R.color.secondary_text_color)).actionBar().paddingDp(2)));
    searchBox.setSearchListener(new SearchBox.SearchListener() {
      @Override
      public void onSearchOpened() {
        Log.d("qqq302", "open");
      }

      @Override
      public void onSearchCleared() {
        Log.d("qqq303", "clear");
      }

      @Override
      public void onSearchClosed() {
        Log.d("qqq303", "closed");
      }

      @Override
      public void onSearchTermChanged(String s) {
        Log.d("qqq301", s);
      }

      @Override
      public void onSearch(String s) {    //TODO call server
        Log.d("qqq300", s);
        OkHttpClient ohc1 = new OkHttpClient();
        ohc1.setReadTimeout(1, TimeUnit.MINUTES);
        ohc1.setConnectTimeout(1, TimeUnit.MINUTES);
        Retrofit retrofit = new Retrofit
            .Builder()
            .baseUrl(Const.API_ONE2ONE_HEROKU)
            .addConverterFactory(GsonConverterFactory.create(Const.GsonParserThatWorksWithPGTimestamp))
            .client(ohc1)
            .build();
        v2API.patients apiService = retrofit.create(v2API.patients.class);
        Call<List<Patient>> clinicListCall = apiService.getPatients("1", null, null, null, null, null, null, null, null, s, null);
        clinicListCall.enqueue(new Callback<List<Patient>>() {
          @Override
          public void onResponse(Response<List<Patient>> response, Retrofit retrofit) {
            Log.d("qqq27", "receiving: " + response.code() + " " + response.message() + " " + response.body());
            if (response.body() != null && response.body().size() > 0) {
              Cache.setPostTriagePatients2(getBaseContext(), response.body());
              Log.d("qqq354", String.valueOf(whichStation));
              recyclerView.swapAdapter(new PatientCardRecyclerViewAdapter(getBaseContext(), whichStation), false);
            }
          }

          @Override
          public void onFailure(Throwable t) {
            Log.d("qqq27", "receives nothing/error");
          }
        });
      }

      @Override
      public void onResultClick(SearchResult searchResult) {
        Log.d("qqq304", "result click");
      }
    });

    recyclerView = (FixedRecyclerView) findViewById(R.id.recyclerView);
    whichStation = getIntent().getIntExtra(Const.KEY_WHICH_STATION, Const.ID_TRIAGE);
    recyclerView.setAdapter(new PatientCardRecyclerViewAdapter(this, whichStation));
    Log.d("qqq353", String.valueOf(whichStation));
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
  }
}
