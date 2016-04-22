package io.github.hkust1516csefyp43.easymed.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import io.github.hkust1516csefyp43.easymed.R;
import io.github.hkust1516csefyp43.easymed.pojo.server_response.Notification;
import io.github.hkust1516csefyp43.easymed.utility.Cache;

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

    //TODO pull to refresh

    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    if (recyclerView != null) {
      recyclerView.setAdapter(new NotificationRecyclerViewAdapter(getBaseContext()));
      recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
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
    List<Notification> notifications;

    public NotificationRecyclerViewAdapter(Context context) {
      notifications = Cache.CurrentUser.getNotifications(context);
    }

    @Override
    public NotificationRecyclerViewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      return new NotificationRecyclerViewViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification, parent, false));
    }

    @Override
    public void onBindViewHolder(NotificationRecyclerViewViewHolder holder, int position) {
      holder.tvNotification.setText(notifications.get(position).getMessage());
    }

    @Override
    public int getItemCount() {
      if (notifications != null)
        return notifications.size();
      else
        return 0;
    }
  }
}
