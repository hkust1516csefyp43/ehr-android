package io.github.hkust1516csefyp43.ehr.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;


/**
 * TODO The whole adapter
 * http://frank-zhu.github.io/android/2015/01/16/android-recyclerview-part-1/
 * http://frank-zhu.github.io/android/2015/02/25/android-recyclerview-part-2/
 * <p/>
 * Created by Louis on 3/11/15.
 */
public class PatientCardRecyclerViewAdapter extends RecyclerView.Adapter {
    LayoutInflater mLayoutInflater;

    public PatientCardRecyclerViewAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }
}
