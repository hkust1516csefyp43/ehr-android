package io.github.hkust1516csefyp43.ehr.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import io.github.hkust1516csefyp43.ehr.R;
import io.github.hkust1516csefyp43.ehr.pojo.patient_visit.Card;
import io.github.hkust1516csefyp43.ehr.view.viewholder.FragCardViewHolder;

import static android.support.v7.widget.RecyclerView.Adapter;
import static android.support.v7.widget.RecyclerView.ViewHolder;

/**
 * Created by kalongip on 4/3/16.
 */
public class FragRecyclerViewAdapter extends Adapter {

    Context context;
    List<Card> data;

    public FragRecyclerViewAdapter(List<Card> source, Context context) {
        data = source;
        context = this.context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FragCardViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_frag, parent, false), context);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (data != null){
            FragCardViewHolder ph = (FragCardViewHolder) holder;
            ph.cardTitle.setText(data.get(position).getCardTitle());
            ph.cardDescription.setText(data.get(position).getCardDescription());

        }
    }

    public void addCard(Card c) {
        data.add(c);
    }

    public int getCardCount() {
        return data.size();
    }

    public void deleteCard(int position) {

    }

    @Override
    public int getItemCount() {
        if (data != null){
            return data.size();
        } else
        return 0;
    }
}
