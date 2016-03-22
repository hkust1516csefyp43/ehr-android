package io.github.hkust1516csefyp43.ehr.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.squareup.okhttp.OkHttpClient;
import com.turingtechnologies.materialscrollbar.AlphabetIndicator;
import com.turingtechnologies.materialscrollbar.DragScrollBar;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.github.hkust1516csefyp43.ehr.R;
import io.github.hkust1516csefyp43.ehr.adapter.StaticItemAdapter;
import io.github.hkust1516csefyp43.ehr.pojo.server_response.v2.Clinic;
import io.github.hkust1516csefyp43.ehr.value.Cache;
import io.github.hkust1516csefyp43.ehr.value.Const;
import io.github.hkust1516csefyp43.ehr.view.DividerItemDecoration;
import io.github.hkust1516csefyp43.ehr.view.v2API;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * TODO get the correct data (clinic/keyword/suitcase/etc) though Bundle and Extra
 * Someone pass which one to get (Const.STATIC_XXX), and the in onCreate/onResume, it user retrofit to get the data correspondingly (switch)
 * TODO handle rotate
 */
public class StaticItemListActivity extends AppCompatActivity {
    RecyclerView rv;
    ProgressBar pb;
    RelativeLayout rl;
    DragScrollBar d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static_item_list);
        super.setTheme(R.style.AppTheme2);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        rl = (RelativeLayout) findViewById(R.id.theWholeThing);
        rv = (RecyclerView) findViewById(R.id.recyclerView);
//        d = (DragScrollBar) findViewById(R.id.dragScrollBar);
        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        rv.setItemAnimator(new DefaultItemAnimator());
        pb = (ProgressBar) findViewById(R.id.loading_wheel);

        OkHttpClient ohc1 = new OkHttpClient();
        ohc1.setReadTimeout(1, TimeUnit.MINUTES);
        ohc1.setConnectTimeout(1, TimeUnit.MINUTES);
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(Const.API_ONE2ONE_HEROKU)
                .addConverterFactory(GsonConverterFactory.create(Const.gson1))
                .client(ohc1)
                .build();
        v2API apiService = retrofit.create(v2API.class);
        Call<List<Clinic>> clinicListCall = apiService.getClinics("1", null, null, null, null, null, null, null, null, null, null, null, null, null);
        clinicListCall.enqueue(new Callback<List<Clinic>>() {
            @Override
            public void onResponse(Response<List<Clinic>> response, Retrofit retrofit) {
                Log.d("qqq27", "receiving: " + response.code() + " " + response.message() + " " + response.body());
                if (response.body() != null && response.body().size() > 0) {
                    //TODO
                    Cache.setClinics(getBaseContext(), response.body());
                    rv.setAdapter(StaticItemAdapter.newInstance(getBaseContext(), response.body(), Const.STATIC_CLINIC));
                    rv.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                    pb.setVisibility(View.GONE);
                    rv.setVisibility(View.VISIBLE);
                    d = new DragScrollBar(getBaseContext(), rv, true);
                    d.addIndicator(new AlphabetIndicator(getBaseContext()), true);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("qqq27", "receives nothing/error");
            }
        });
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);


    }
}
