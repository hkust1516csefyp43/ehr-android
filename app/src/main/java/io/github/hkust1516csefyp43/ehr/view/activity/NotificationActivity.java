package io.github.hkust1516csefyp43.ehr.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import io.github.hkust1516csefyp43.ehr.R;
import io.github.hkust1516csefyp43.ehr.pojo.server_response.v2.Notification;
import io.github.hkust1516csefyp43.ehr.value.Cache;

public class NotificationActivity extends AppCompatActivity {
  RecyclerView rv;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_notification);

    Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
    tb.setTitle(getString(R.string.notifications));   //TODO suffix (how many)
    this.setSupportActionBar(tb);
    ActionBar ab = getSupportActionBar();
    if (ab != null) {
      ab.setDisplayHomeAsUpEnabled(true);
      ab.setDisplayShowHomeEnabled(true);
    }

    rv = (RecyclerView) findViewById(R.id.rvNotification);
    rv.setAdapter(new NotificationRVAdapter(this));
    rv.setLayoutManager(new LinearLayoutManager(this));
  }

  private class NotificationViewHolder extends RecyclerView.ViewHolder {
    TextView tvNotification;
    TextView tvDate;
    View unreadCircle;

    public NotificationViewHolder(final View itemView) {
      super(itemView);
      itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          //TODO use getAdapterPosition() to get if and set it as read (PUT notification)
          //TODO put a progressBar on the top of this itemView
          //if PUT successful, unreadCircle.setVisibility(View.INVISIBLE);
          unreadCircle.setVisibility(View.INVISIBLE);
        }
      });
      tvNotification = (TextView) itemView.findViewById(R.id.tvNotification);
      tvDate = (TextView) itemView.findViewById(R.id.tvDate);
      unreadCircle = itemView.findViewById(R.id.vUnreadCircle);
    }
  }

  private class NotificationRVAdapter extends RecyclerView.Adapter<NotificationViewHolder> {
    List<Notification> notificationList;

    public NotificationRVAdapter(Context context) {
      notificationList = Cache.getNotifications(context);
    }

    @Override
    public NotificationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      return new NotificationViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification, parent, false));
    }

    @Override
    public void onBindViewHolder(NotificationViewHolder holder, int position) {
      holder.tvNotification.setText(notificationList.get(position).getMessage());
      if (!notificationList.get(position).getRead()) {
        holder.unreadCircle.setVisibility(View.VISIBLE);
      } else {
        holder.unreadCircle.setVisibility(View.INVISIBLE);
      }
      String dateText = (notificationList.get(position).getRemindDate().getYear() + 1900) + "/" + (notificationList.get(position).getRemindDate().getMonth() + 1) + "/" + notificationList.get(position).getRemindDate().getDate();
      holder.tvDate.setText(dateText);
    }

    @Override
    public int getItemCount() {
      if (notificationList != null) {
        return notificationList.size();
      }
      return 0;
    }
  }
}
