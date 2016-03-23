package io.github.hkust1516csefyp43.ehr.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.turingtechnologies.materialscrollbar.INameableAdapter;

import java.util.List;

import io.github.hkust1516csefyp43.ehr.R;
import io.github.hkust1516csefyp43.ehr.pojo.server_response.v2.BloodType;
import io.github.hkust1516csefyp43.ehr.pojo.server_response.v2.Clinic;
import io.github.hkust1516csefyp43.ehr.pojo.server_response.v2.Keyword;
import io.github.hkust1516csefyp43.ehr.pojo.server_response.v2.Suitcase;
import io.github.hkust1516csefyp43.ehr.value.Const;
import io.github.hkust1516csefyp43.ehr.view.viewholder.StaticItemViewHolder;

/**
 * Created by Louis on 22/3/16.
 */
public class StaticItemAdapter extends RecyclerView.Adapter<StaticItemViewHolder> implements INameableAdapter {
    Context context;
    List<Clinic> listOfClinics;
    List<Keyword> listOfKeywords;
    List<Suitcase> listOfSuitcases;
    List<BloodType> listOfBloodTypes;
    int whichOne;

    public StaticItemAdapter(Context c, int w) {
        context = c;
        whichOne = w;
    }

    public static StaticItemAdapter newInstance(Context c, List l, int w) {
        StaticItemAdapter sia = new StaticItemAdapter(c, w);
        switch (w) {
            case Const.STATIC_CLINIC:
                sia.listOfClinics = l;
                break;
            case Const.STATIC_KEYWORD:
                sia.listOfKeywords = l;
                break;
            case Const.STATIC_SUITCASE:
                sia.listOfSuitcases = l;
                break;
            case Const.STATIC_BLOOD_TYPE:
                sia.listOfBloodTypes = l;
                break;
        }
        return sia;
    }

    @Override
    public StaticItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new StaticItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_static, parent, false), context);
    }

    @Override
    public void onBindViewHolder(StaticItemViewHolder holder, int position) {
        switch (whichOne) {
            case Const.STATIC_CLINIC:
                if (listOfClinics != null)
                    holder.tvTitle.setText(listOfClinics.get(position).getTitle());
                break;
            case Const.STATIC_KEYWORD:
//                if (listOfKeywords != null)
//                    holder.tvTitle.setText(listOfKeywords.get(position).getTitle());
                break;
            case Const.STATIC_SUITCASE:
                break;
            case Const.STATIC_BLOOD_TYPE:
                if (listOfBloodTypes != null)
                    holder.tvTitle.setText(listOfBloodTypes.get(position).getTitle());
                break;
        }
    }

    @Override
    public int getItemCount() {
        switch (whichOne) {
            case Const.STATIC_CLINIC:
                if (listOfClinics != null)
                    return listOfClinics.size();
                else
                    return 0;
            case Const.STATIC_KEYWORD:
                break;
            case Const.STATIC_SUITCASE:
                break;
            case Const.STATIC_BLOOD_TYPE:
                if (listOfBloodTypes != null)
                    return listOfBloodTypes.size();
                else
                    return 0;
            default:
                return 0;
        }
        return 0;
    }

    @Override
    public Character getCharacterForElement(int element) {
        Character c = '0';
        switch (whichOne) {
            case Const.STATIC_CLINIC:
                if (listOfClinics != null)
                    c = listOfClinics.get(element).getTitle().charAt(0);
            case Const.STATIC_KEYWORD:
                break;
            case Const.STATIC_SUITCASE:
                break;
            case Const.STATIC_BLOOD_TYPE:
                if (listOfBloodTypes != null)
                    c = listOfBloodTypes.get(element).getTitle().charAt(0);
                break;
            default:
                return 0;
        }
        if (Character.isDigit(c)) {
            c = '#';
        }
        return c;
    }
}
