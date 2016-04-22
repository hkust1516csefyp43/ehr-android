package io.github.hkust1516csefyp43.easymed.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;
import io.github.hkust1516csefyp43.easymed.R;
import io.github.hkust1516csefyp43.easymed.pojo.server_response.User;
import io.github.hkust1516csefyp43.easymed.utility.Cache;
import io.github.hkust1516csefyp43.easymed.utility.Const;

public class SplashActivity extends AppCompatActivity {

  private static final String TAG = SplashActivity.class.getSimpleName();
  User user;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Fabric.with(this, new Crashlytics());
    setContentView(R.layout.activity_splash);
    SplashLogicAsyncTask task = new SplashLogicAsyncTask(this);
    task.execute();
  }

  public void showLogo(){
    ImageView iv = (ImageView) findViewById(R.id.logo);
    if (iv != null) {
      int logo = R.drawable.easymed;
      Glide.with(this).load(logo).diskCacheStrategy(DiskCacheStrategy.ALL).into(iv);
    }
  }

  private class SplashLogicAsyncTask extends AsyncTask<Void, Void, Void> {
    Context context;
    public SplashLogicAsyncTask(Context c) {
      context = c;
    }

    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      showLogo();
    }

    @Override
    protected Void doInBackground(Void... params) {
      user = Cache.CurrentUser.getUser(context);
      return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
      super.onPostExecute(aVoid);
      final Class target;
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
    }
  }

}
