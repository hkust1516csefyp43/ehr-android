package io.github.hkust1516csefyp43.ehr.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.github.hkust1516csefyp43.ehr.R;

public class NotificationActivity extends AppCompatActivity {
  RecyclerView rv;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_notification);

    Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
    tb.setTitle(getString(R.string.notifications));   //TODO suffix (how many)
    this.setSupportActionBar(tb);

    rv = (RecyclerView) findViewById(R.id.rvNotification);
    //TODO get notification from cache
    //Add them in a for loop
  }

  private class NotificationViewHolder extends RecyclerView.ViewHolder {
    TextView tvNotification;
    TextView tvDate;
    View unreadCircle;

    public NotificationViewHolder(View itemView) {
      super(itemView);
      tvNotification = (TextView) itemView.findViewById(R.id.tvNotification);
      tvDate = (TextView) itemView.findViewById(R.id.tvDate);
      unreadCircle = itemView.findViewById(R.id.vUnreadCircle);
    }
  }

  private class NotificationRVAdapter extends RecyclerView.Adapter<NotificationViewHolder> {

    public NotificationRVAdapter() {

    }

    @Override
    public NotificationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      return null;
    }

    @Override
    public void onBindViewHolder(NotificationViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
      return 0;
    }
  }
}
