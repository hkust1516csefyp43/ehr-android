package io.github.hkust1516csefyp43.easymed.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.crashlytics.android.Crashlytics;
import com.optimizely.Optimizely;

import net.danlew.android.joda.JodaTimeAndroid;

import io.fabric.sdk.android.Fabric;
import io.github.hkust1516csefyp43.easymed.R;
import io.github.hkust1516csefyp43.easymed.pojo.server_response.User;
import io.github.hkust1516csefyp43.easymed.utility.Cache;
import io.github.hkust1516csefyp43.easymed.utility.Connectivity;
import io.github.hkust1516csefyp43.easymed.utility.Const;

public class SplashActivity extends AppCompatActivity {

  private static final String TAG = SplashActivity.class.getSimpleName();
  private String error;
  private User user;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Fabric.with(this, new Crashlytics());
    Optimizely.startOptimizelyWithAPIToken(getString(R.string.com_optimizely_api_key), getApplication());
    JodaTimeAndroid.init(this);

    setContentView(R.layout.activity_splash);

    /**
     * TODO find the right server
     * 1. see if any internet exists
     * 2. see if wifi exists
     * 3. if wifi, check if rpi server exists
     * 4. if exists, done
     * 5. else check if heroku is accessible
     * 6. if not, set error >> true (on post execute will display sth)
     */
    final CheckIfServerIsAvailable task2 = new CheckIfServerIsAvailable(this, "ehr-api.herokuapp.com", 443, new SplashActivity.AsyncResponse() {
      @Override
      public void processFinish(String output) {
        //TODO there is nothing else you can do
        error = "Even heroku is not available";
      }
    });
    CheckIfServerIsAvailable task1 = new CheckIfServerIsAvailable(this, "192.168.0.194", 3000, new SplashActivity.AsyncResponse() {
      @Override
      public void processFinish(String output) {
        task2.execute();
      }
    });
    if (Connectivity.isConnected(getApplicationContext())) {
      if (Connectivity.isConnectedWifi(getApplicationContext())) {
        task1.execute();
      } else {
        task2.execute();
      }
    } else {
      error = "Internet is not available";
    }
  }

  public void showLogo(){
    ImageView iv = (ImageView) findViewById(R.id.logo);
    if (iv != null) {
      int logo = R.drawable.easymed;
      Glide.with(this).load(logo).diskCacheStrategy(DiskCacheStrategy.ALL).into(iv);
    }
  }

  private class CheckIfServerIsAvailable extends AsyncTask<Void, Boolean, Boolean> {
    Context context;
    String host;
    int port;
    AsyncResponse delegate;

    public CheckIfServerIsAvailable(Context context, String host, int port, AsyncResponse delegate) {
      this.context = context;
      this.host = host;
      this.port = port;
      this.delegate = delegate;
    }

    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      showLogo();
    }

    @Override
    protected Boolean doInBackground(Void... params) {
      return Connectivity.isReachableByTcp(host, port, 10000);
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
      super.onPostExecute(aBoolean);
      if (aBoolean) {
        Log.d(TAG, "successful: " + host);
        //TODO display some dialog to tell user which server they are connected to
        final Class target;
        User user = Cache.CurrentUser.getUser(context);
        if (user == null){
          target = LoginActivity.class;
        }else{
          target = DrawerActivity.class;
        }
        new Handler().postDelayed(new Runnable() {
          @Override
          public void run() {
            Intent mainIntent = new Intent(SplashActivity.this, target);
            startActivity(mainIntent);
            finish();
          }
        }, Const.SPLASH_DISPLAY_LENGTH);
      } else {
        Log.d(TAG, host + " is not accessible");
        delegate.processFinish("failed");
      }
    }
  }

  public interface AsyncResponse {
    void processFinish(String output);
  }

}
