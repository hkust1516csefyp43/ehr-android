package io.github.hkust1516csefyp43.ehr.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by kalongip on 4/3/16.
 */
public class PMHCardRecyclerViewAdapter extends RecyclerView.Adapter {

    Context context;

    public PMHCardRecyclerViewAdapter(Context context){context = this.context;}

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
