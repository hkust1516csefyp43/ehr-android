package io.github.hkust1516csefyp43.ehr.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

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
    boolean displaySwitch;
    ArrayList<String> suggestions;
    String title;

    public FragRecyclerViewAdapter(@Nullable ArrayList<Card> source, Context c, boolean d) {
        data = source;
        this.context = c;
        displaySwitch = d;
    }

    /**
     * Create a new FragRecyclerViewAdapter
     *
     * @param source if there is any default cards
     * @param c      is the context, nothing special
     * @param d      is true if you want to display switches on each individual card. It will also A) disable fab and B) TODO disable edit on title
     * @param sugg   i.e. a list of suggestions for auto complete
     * @param t      i.e. the title for the dialog
     */
    public FragRecyclerViewAdapter(@Nullable ArrayList<Card> source, Context c, boolean d, @Nullable ArrayList<String> sugg, @Nullable String t) {
        this(source, c, d);
        suggestions = sugg;
        title = t;
    }

    @Override
    public FragCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FragCardViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_frag, parent, false), context, suggestions, title, this, displaySwitch);
    }

    @Override
    public void onBindViewHolder(final FragCardViewHolder holder, int position) {
        if (data != null){
            if (data.size() > 0) {
                final FragCardViewHolder ph = holder;
                ph.cardTitle.setText(data.get(position).getCardTitle());
                ph.cardDescription.setText(data.get(position).getCardDescription());
                if (displaySwitch) {
                    ph.setEditableTitle(false);
                    ph.cardSwitch.setVisibility(View.VISIBLE);
                    ph.cardSwitch.setChecked(data.get(position).isChecked());
                    ph.cardSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (!isChecked) {
                                ph.cardDescription.setVisibility(View.GONE);
                                data.get(holder.getAdapterPosition()).setChecked(false);
                            } else {
                                ph.cardDescription.setVisibility(View.VISIBLE);
                                data.get(holder.getAdapterPosition()).setChecked(true);
                            }
                        }
                    });
                }
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
        if (c != null) {
            if (data == null)
                data = new ArrayList<>();
            for (Card card : c) {
                data.add(card);
            }
            this.notifyDataSetChanged();
        }
    }

    public int getCardCount() {
        return data.size();
    }

    public void deleteCard(int position) {
        data.remove(position);
        this.notifyItemRemoved(position);
    }

    public void deleteAllCards() {
        data = new ArrayList<>();
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (data != null){
            return data.size();
        } else
            return 0;
    }
}
