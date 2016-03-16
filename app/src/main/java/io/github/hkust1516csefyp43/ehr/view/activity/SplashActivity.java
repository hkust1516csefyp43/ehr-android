package io.github.hkust1516csefyp43.ehr.view.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.json.JSONException;
import org.json.JSONObject;

import io.github.hkust1516csefyp43.ehr.BuildConfig;
import io.github.hkust1516csefyp43.ehr.Connectivity;
import io.github.hkust1516csefyp43.ehr.R;
import io.github.hkust1516csefyp43.ehr.pojo.server_response.v1.User;
import io.github.hkust1516csefyp43.ehr.value.Cache;
import io.github.hkust1516csefyp43.ehr.value.Const;

import static android.provider.Settings.ACTION_WIFI_SETTINGS;

/**
 * TODO Check User disk cache
 * TODO check variant >> free >> no host cache >> need to input
 * TODO move things to background thread
 */
public class SplashActivity extends AppCompatActivity {
    JSONObject serverConfig;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        behindSplashIconAsyncTask bsiat = new behindSplashIconAsyncTask(this);
        bsiat.execute();                                                        //Run non-UI stuff on background thread
    }

    private void emergencyFix() {
        for (int i = Cache.getEmergencyFix(this) + 1; i <= Const.NUMBER_EMERGENCY_FIX; i++) {
            switch (i) {
                case 1:                                         //Emergency fix for v1.0.9: remove config to make sure there is a SSID in the config
                    Cache.clearConfig(this);
                    Log.d("qqq0", "Emergency fix 1");
                    break;
            }
        }
        Cache.setEmergencyFix(this, Const.NUMBER_EMERGENCY_FIX);
    }

    /**
     * Load and display the correct logo to the splash screen
     */
    public void loadLogo() {
        ImageView iv = (ImageView) findViewById(R.id.logo);
        int logo;
        if (BuildConfig.FLAVOR.compareTo(Const.FLAVOR_ONE_2_ONE_CAMBODIA) == 0)
            logo = R.drawable.easymed;
        else
            logo = R.drawable.ehr_free_logo;
        Glide.with(this).load(logo).diskCacheStrategy(DiskCacheStrategy.ALL).fallback(R.drawable.ehr_logo).into(iv);
    }

    public void showLoadingSpinner() {
        //no need to dismiss, just destory the whole activity
        ProgressBar pb = (ProgressBar) findViewById(R.id.loading_wheel);
        pb.setVisibility(View.VISIBLE);
    }

    public void hideLoadingSpinner() {
        ProgressBar pb = (ProgressBar) findViewById(R.id.loading_wheel);
        pb.setVisibility(View.GONE);
    }

    public String noHostInitiation() throws JSONException {
        JSONObject host = new JSONObject();
        if (BuildConfig.FLAVOR.compareTo(Const.FLAVOR_ONE_2_ONE_CAMBODIA) == 0) {
            host.put(Const.CONFIG_CLOUD_HOST, Const.API_ONE2ONE_HEROKU);
            host.put(Const.CONFIG_LOCAL_HOST, Const.API_ONE2ONE_RPi);
            host.put(Const.CONFIG_SSID_LIST, Const.SSID_LIST_ONE2ONE);
            Cache.setConfig(this, host);
            return host.toString();
        } else {
            // TODO ask user for input and save to SP
            return null;
        }
    }

    /**
     * P.S.: Each field can be null but not missing
     * i.e. "cloud_api_host"=null is ok, but not the whole "cloud_api_host" missing
     *
     * @param serverConfigString
     */
    private void getHosts(String serverConfigString) {
        Log.d("qqq112", serverConfigString);
        try {
            serverConfig = new JSONObject(serverConfigString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            Const.setCloudAPI(serverConfig.getString(Const.CONFIG_CLOUD_HOST));
        } catch (JSONException e) {
            //TODO missing cloud api
            e.printStackTrace();
        }
        try {
            Const.setLocalAPI(serverConfig.getString(Const.CONFIG_LOCAL_HOST));
        } catch (JSONException e) {
            //TODO missing local api
            e.printStackTrace();
        }
        try {
            Const.setListSSID(serverConfig.getJSONArray(Const.CONFIG_SSID_LIST));
        } catch (JSONException e) {
            //TODO missing ssid
            e.printStackTrace();
        }
    }

    public void makeSureSomeFormOfNetworkAccessIsAvailable() {
        if (Connectivity.isConnected(getApplicationContext())) {
            if (Connectivity.isConnectedWifi(getApplicationContext())) {
                String thisSSID = Connectivity.ConnectedWifiSSID(getApplicationContext());
                if (thisSSID != null) {
                    boolean found = false;
                    int i = 0;
                    String aSSID;
                    if (Const.LIST_SSID != null && Const.LIST_SSID.length() > 0) {
                        do {
                            try {
                                aSSID = "\"" + Const.LIST_SSID.getString(i) + "\"";                 //the stupid getSSID method pre and suffix '"' to the ssid -_-
                                Log.d("qqq113", aSSID + " vs " + thisSSID);
                                if (aSSID.compareTo(thisSSID) == 0)
                                    found = true;
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            i++;
                        } while (!found && i <= Const.LIST_SSID.length());
                    }
                    if (found) {
                        //i.e. found local router
                        Log.d("qqq110", "found it");
                    } else {
                        //i.e. connected through wifi but not local router
                        Log.d("qqq111", "not local");
                    }
                }
                //TODO check if local host is available
            } else if (Connectivity.isConnectedMobile(getApplicationContext())) {
                Log.d("qqq52", "not wifi");
                //TODO dialog: not accessing local host
                //TODO check if cloud host is available
            } else {

            }
        } else {
            //TODO not connected >> go to setting
            //TODO dialog: wifi, data or else
            final View v = findViewById(R.id.rootView);
            final Snackbar sb = Snackbar.make(v, "No network access", Snackbar.LENGTH_INDEFINITE);
            final MaterialDialog.Builder b = new MaterialDialog.Builder(this);
            b.theme(Theme.LIGHT).title("No network access")
                    .content("Please enable Wi-Fi or data").positiveText("Wi-Fi")
                    .negativeText("Data")
                    .neutralText("Dismiss")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            startActivity(new Intent(ACTION_WIFI_SETTINGS));
                        }
                    })
                    .onNegative(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            Intent intent = new Intent();
                            intent.setComponent(new ComponentName(
                                    "com.android.settings",
                                    "com.android.settings.Settings$DataUsageSummaryActivity"));
                            startActivity(intent);
                        }
                    })
                    .onNeutral(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            sb.show();
                            dialog.dismiss();
                        }
                    });
            sb.setAction("Fix it", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    b.show();
                }
            });
            Looper.prepare();
            b.show();
        }
    }


    private class behindSplashIconAsyncTask extends AsyncTask<Void, Void, Void> {
        Context context;

        private behindSplashIconAsyncTask(Context c) {
            context = c;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loadLogo();
            showLoadingSpinner();
        }

        @Override
        protected Void doInBackground(Void... params) {
            emergencyFix();
            serverConfig = null;
            String serverConfigString = null;
            try {
                serverConfigString = Cache.getConfig(context);
                if (serverConfigString == null) {
                    serverConfigString = noHostInitiation();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            getHosts(serverConfigString);
            makeSureSomeFormOfNetworkAccessIsAvailable();
            user = Cache.getUser(context);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //TODO start the correct activity
            //TODO check if user is still valid (1. compare with device time 2. try with server(s))
            final Class c;
            if (user == null) {
                //go to login screen
                c = LoginActivity.class;
            } else {
                c = TwoRecyclerViewPatientsActivity.class;
            }
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    hideLoadingSpinner();
                    Intent mainIntent = new Intent(SplashActivity.this, c);
                    startActivity(mainIntent);
                    finish();
                    overridePendingTransition(R.anim.activityfadein, R.anim.splashfadeout);
                }
            }, Const.SPLASH_DISPLAY_LENGTH);
        }
    }
}
