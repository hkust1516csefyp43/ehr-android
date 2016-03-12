package io.github.hkust1516csefyp43.ehr.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import io.github.hkust1516csefyp43.ehr.R;
import io.github.hkust1516csefyp43.ehr.pojo.patient_visit.Card;
import io.github.hkust1516csefyp43.ehr.view.viewholder.FragCardViewHolder;

import static android.support.v7.widget.RecyclerView.Adapter;

/**
 * TODO card onclick >> display dialog again (edit)
 * Created by kalongip on 4/3/16.
 */
public class FragRecyclerViewAdapter extends Adapter<FragCardViewHolder> {

    Context context;
    ArrayList<Card> data;

    public FragRecyclerViewAdapter(@Nullable ArrayList<Card> source, Context c) {
        data = source;
        this.context = c;
    }

    @Override
    public FragCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FragCardViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_frag, parent, false), context);
    }

    @Override
    public void onBindViewHolder(FragCardViewHolder holder, int position) {
        if (data != null){
            if (data.size() > 0) {
                FragCardViewHolder ph = holder;
                ph.cardTitle.setText(data.get(position).getCardTitle());
                ph.cardDescription.setText(data.get(position).getCardDescription());
            }

        }
    }

    public ArrayList<Card> getData() {
        return data;
    }

    public void addCard(Card c) {
        if (data == null)
            data = new ArrayList<>();
        data.add(c);
        this.notifyItemInserted(data.size() - 1);
    }

    public void addCards(ArrayList<Card> c) {
        if (data == null)
            data = new ArrayList<>();
        for (Card card : c) {
            data.add(card);
        }
        this.notifyDataSetChanged();
    }

    public int getCardCount() {
        return data.size();
    }

    public void deleteCard(int position) {
        data.remove(position);
        this.notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        if (data != null){
            return data.size();
        } else
            return 0;
    }
}
