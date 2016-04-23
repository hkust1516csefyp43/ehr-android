package io.github.hkust1516csefyp43.easymed.view.fragment.patient_visit_edit;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;

import java.util.ArrayList;

import io.github.hkust1516csefyp43.easymed.R;
import io.github.hkust1516csefyp43.easymed.listener.OnFragmentInteractionListener;
import io.github.hkust1516csefyp43.easymed.pojo.patient_visit_edit.Card;
import io.github.hkust1516csefyp43.easymed.utility.Const;
import io.github.hkust1516csefyp43.easymed.view.TwoEditTextDialogCustomView;


public class ListOfCardsFragment extends Fragment implements OnFragmentInteractionListener{
  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String TAG = ListOfCardsFragment.class.getSimpleName();

  private OnFragmentInteractionListener mListener;
  private RecyclerView recyclerView;
  private FragRecyclerViewAdapter adapter;
  private FloatingActionButton fab;
  private String title;
  private ArrayList<String> preFillItems;
  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;

  public static ListOfCardsFragment newInstance(String title) {
    ListOfCardsFragment fragment = new ListOfCardsFragment();
    Bundle args = new Bundle();
    args.putString(Const.KEY_TITLE, title);
    fragment.setArguments(args);
    return fragment;
  }

  public static ListOfCardsFragment newInstance(String title, ArrayList<String> preFillItems) {
    ListOfCardsFragment fragment = new ListOfCardsFragment();
    Bundle args = new Bundle();
    args.putString(Const.KEY_TITLE, title);
    args.putStringArrayList(Const.KEY_PRE_FILL_ITEMS, preFillItems);
    fragment.setArguments(args);
    return fragment;
  }

  public static ListOfCardsFragment newInstance(String title, String[] preFillItems) {
    ArrayList<String> sList = new ArrayList<>();
    for (String s : preFillItems) {
      sList.add(s);
    }
    return newInstance(title, sList);
  }

  public ListOfCardsFragment() {
    // Required empty public constructor
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Bundle bundle = getArguments();
    if (bundle != null) {
      title = bundle.getString(Const.KEY_TITLE);
      preFillItems = bundle.getStringArrayList(Const.KEY_PRE_FILL_ITEMS);
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_list_of_cards, container, false);
    recyclerView = (RecyclerView) view.findViewById(R.id.rv_patient_edit);
    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    adapter = new FragRecyclerViewAdapter(null, getContext(), false);

    fab = (FloatingActionButton) view.findViewById(R.id.fab_patient_edit);
    fab.setImageDrawable(new IconicsDrawable(getContext(), GoogleMaterial.Icon.gmd_add).color(Color.WHITE).paddingDp(3).sizeDp(16));

//    List<Keyword> keywords = Cache.getString(getContext(), Const.KEY_KEYWORDS);
//    ArrayList<String> a = new ArrayList<>();
//    //TODO call api and get list of keywords (& cache them)
//    for (Keyword k : keywords) {
//      a.add(k.getKeyword());
//    }
//
//    if (preFillItems != null) {
//      fab.setVisibility(View.GONE);
//      ArrayList<Card> preFillCards = new ArrayList<>();
//      for (String s : preFillItems) {
//        preFillCards.add(new Card(s, "Fill this or flip the switch"));
//      }
//      adapter = new FragRecyclerViewAdapter(preFillCards, getContext(), true, a, title);
//    } else {
//      adapter = new FragRecyclerViewAdapter(null, getContext(), false, a, title);
//    }

    final TwoEditTextDialogCustomView tetdcv = new TwoEditTextDialogCustomView(getContext(), null, title);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        new MaterialDialog.Builder(getContext())
            .title("Add")
            .customView(tetdcv, true)
            .positiveText("Confirm")
            .negativeText("Cancel")
            .onPositive(new MaterialDialog.SingleButtonCallback() {
              @Override
              public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                ArrayList<String> data = tetdcv.getData();
                tetdcv.clearData();
                Log.d(TAG, data.toString());
                if (data != null) {
                  adapter.addCard(new Card(data.get(0), data.get(1)));
                }
              }
            })
            .onNegative(new MaterialDialog.SingleButtonCallback() {
              @Override
              public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                tetdcv.clearData();
                dialog.dismiss();
              }
            })
            .show();
      }
    });
    recyclerView.setAdapter(adapter);

    return view;
  }

  // TODO: Rename method, update argument and hook method into UI event
  public void onButtonPressed(Uri uri) {
    if (mListener != null) {
      mListener.onFragmentInteraction(uri);
    }
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    if (context instanceof OnFragmentInteractionListener) {
      mListener = (OnFragmentInteractionListener) context;
    } else {
      throw new RuntimeException(context.toString()
          + " must implement OnFragmentInteractionListener");
    }
  }

  @Override
  public void onDetach() {
    super.onDetach();
    mListener = null;
  }

  @Override
  public void onFragmentInteraction(Uri uri) {

  }

  /**
   * This interface must be implemented by activities that contain this
   * fragment to allow an interaction in this fragment to be communicated
   * to the activity and potentially other fragments contained in that
   * activity.
   * <p/>
   * See the Android Training lesson <a href=
   * "http://developer.android.com/training/basics/fragments/communicating.html"
   * >Communicating with Other Fragments</a> for more information.
   */



  private class FragRecyclerViewAdapter extends RecyclerView.Adapter<FragRecyclerViewHolder> {
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
     * @param d      is true if you want to display switches on each individual card. It will also A) disable fab and B)disable edit on title
     * @param sugg   i.e. a list of suggestions for auto complete
     * @param t      i.e. the title for the dialog
     */
    public FragRecyclerViewAdapter(@Nullable ArrayList<Card> source, Context c, boolean d, @Nullable ArrayList<String> sugg, @Nullable String t) {
      this(source, c, d);
      suggestions = sugg;
      title = t;
    }


    @Override
    public FragRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      return new FragRecyclerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_visit, parent, false), context, suggestions, title, this, displaySwitch);
    }

    @Override
    public void onBindViewHolder(final FragRecyclerViewHolder holder, int position) {
      if (data != null){
        if (data.size() > 0) {
          final FragRecyclerViewHolder viewHolder = holder;
          viewHolder.cardTitle.setText(data.get(position).getCardTitle());
          viewHolder.cardDescription.setText(data.get(position).getCardDescription());
          if (displaySwitch) {
            viewHolder.setEditableTitle(false);
            viewHolder.cardSwitch.setVisibility(View.VISIBLE);
            viewHolder.cardSwitch.setChecked(data.get(position).isChecked());
            viewHolder.cardSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
              @Override
              public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                  viewHolder.cardDescription.setVisibility(View.GONE);
                  data.get(holder.getAdapterPosition()).setChecked(false);
                } else {
                  viewHolder.cardDescription.setVisibility(View.VISIBLE);
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

  private class FragRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public CardView theWholeCard;
    public TextView cardTitle;
    public TextView cardDescription;
    public SwitchCompat cardSwitch;
    public boolean editableTitle = true;


    public FragRecyclerViewHolder(View itemView, Context context, ArrayList<String> suggestions, String title, FragRecyclerViewAdapter fragRecyclerViewAdapter, boolean displaySwitch) {
      super(itemView);
      cardTitle = (TextView) itemView.findViewById(R.id.item_name);
      cardDescription = (TextView) itemView.findViewById(R.id.item_description);
      cardSwitch = (SwitchCompat) itemView.findViewById(R.id.scNull);
    }

    @Override
    public void onClick(View v) {

    }

    public void setEditableTitle(boolean editableTitle) {
      this.editableTitle = editableTitle;
    }
  }
}
