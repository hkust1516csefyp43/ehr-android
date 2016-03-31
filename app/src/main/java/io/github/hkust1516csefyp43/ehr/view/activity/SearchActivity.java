package io.github.hkust1516csefyp43.ehr.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.quinny898.library.persistentsearch.SearchBox;
import com.quinny898.library.persistentsearch.SearchResult;
import com.squareup.okhttp.OkHttpClient;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.github.hkust1516csefyp43.ehr.R;
import io.github.hkust1516csefyp43.ehr.pojo.server_response.v2.Patient;
import io.github.hkust1516csefyp43.ehr.v2API;
import io.github.hkust1516csefyp43.ehr.value.Const;
import io.github.hkust1516csefyp43.ehr.view.custom_view.FixedRecyclerView;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class SearchActivity extends AppCompatActivity {
    SearchBox searchBox;
    FixedRecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchBox = (SearchBox) findViewById(R.id.searchbox);
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
                v2API apiService = retrofit.create(v2API.class);
                Call<List<Patient>> clinicListCall = apiService.getPatients("1", null, null, null, null, null, null, null, null, s, null);
                clinicListCall.enqueue(new Callback<List<Patient>>() {
                    @Override
                    public void onResponse(Response<List<Patient>> response, Retrofit retrofit) {
                        Log.d("qqq27", "receiving: " + response.code() + " " + response.message() + " " + response.body());
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
        //TODO adapter, viewholder, everything
    }
}
