package io.github.hkust1516csefyp43.ehr.listener;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.AdapterView;

/**
 * handle what happen after clicking a patient card
 * Created by Louis on 5/11/15.
 */
public class patientCardClickListener implements RecyclerView.OnItemTouchListener {
    GestureDetector mGestureDetector;
    private AdapterView.OnItemClickListener mListener;

    public patientCardClickListener(Context context, AdapterView.OnItemClickListener listener) {
        mListener = listener;
        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                super.onLongPress(e);
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
