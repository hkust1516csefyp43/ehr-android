package io.github.hkust1516csefyp43.easymed;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NotificationActivity extends AppCompatActivity {
  public final static String TAG = NotificationActivity.class.getSimpleName();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_notification);

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    if (toolbar != null) {
      setSupportActionBar(toolbar);
      ActionBar actionBar = getSupportActionBar();
      if (actionBar != null) {
        actionBar.setTitle("Notification");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

      }
    }

    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    if (recyclerView != null) {

    }
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

  private class NotificationRecyclerViewViewHolder extends RecyclerView.ViewHolder {
    TextView tvNotification;

    public NotificationRecyclerViewViewHolder(View itemView) {
      super(itemView);
      tvNotification = (TextView) itemView.findViewById(R.id.tv_notification);
    }
  }

  private class NotificationRecyclerViewAdapter extends RecyclerView.Adapter<NotificationRecyclerViewViewHolder> {

    public NotificationRecyclerViewAdapter() {

    }

    @Override
    public NotificationRecyclerViewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      return null;
    }

    @Override
    public void onBindViewHolder(NotificationRecyclerViewViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
      return 0;
    }
  }
}
