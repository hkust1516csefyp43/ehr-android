package io.github.hkust1516csefyp43.easymed.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.turingtechnologies.materialscrollbar.AlphabetIndicator;
import com.turingtechnologies.materialscrollbar.DragScrollBar;
import com.turingtechnologies.materialscrollbar.INameableAdapter;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.github.hkust1516csefyp43.easymed.R;
import io.github.hkust1516csefyp43.easymed.listener.OnFragmentInteractionListener;
import io.github.hkust1516csefyp43.easymed.pojo.server_response.MedicationVariant;
import io.github.hkust1516csefyp43.easymed.utility.Const;
import io.github.hkust1516csefyp43.easymed.utility.v2API;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MedicationVariantListFragment extends Fragment {
  private static final String TAG = MedicationVariantListFragment.class.getSimpleName();

  private OnFragmentInteractionListener mListener;
  private static String key = Const.BundleKey.WHICH_MV_PAGE;

  private SwipeRefreshLayout swipeRefreshLayout;
  private RecyclerView recyclerView;
  private ProgressBar progressBar;
  private DragScrollBar dragScrollBar;

  private int whichPage;

  private List<MedicationVariant> medicationVariants;

  public static MedicationVariantListFragment newInstance(int whichPage) {
    MedicationVariantListFragment fragment = new MedicationVariantListFragment();
    Bundle args = new Bundle();
    args.putInt(key, whichPage);
    fragment.setArguments(args);
    return fragment;
  }

  public MedicationVariantListFragment() {
    // Required empty public constructor
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      whichPage = getArguments().getInt(key);
    }
    Log.d(TAG, "which page = " + whichPage);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    //whichPage are 0(out of stock), 1(inadequate) or 2(enough)
    //recyclerview, call api, fill the list, swipe refresh, fab >> new activity
    View view = inflater.inflate(R.layout.fragment_medication_variant_list, container, false);
    swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.srl);
    swipeRefreshLayout.setVisibility(View.GONE);
    recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
    progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
    progressBar.setVisibility(View.VISIBLE);

    if (recyclerView != null) {
      HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
        @Override public void log(String message) {
          Log.d(TAG, "mvlf: " + message);
        }
      });
      logging.setLevel(HttpLoggingInterceptor.Level.BODY);
      OkHttpClient.Builder ohc1 = new OkHttpClient.Builder();
      ohc1.readTimeout(1, TimeUnit.MINUTES);
      ohc1.connectTimeout(1, TimeUnit.MINUTES);
      ohc1.addInterceptor(logging);
      Retrofit retrofit = new Retrofit
          .Builder()
          .baseUrl(Const.Database.CLOUD_API_BASE_URL_121_dev)
          .addConverterFactory(GsonConverterFactory.create(Const.GsonParserThatWorksWithPGTimestamp))
          .client(ohc1.build())
          .build();
      v2API.medication_variants medicationVariantService = retrofit.create(v2API.medication_variants.class);
      Call<List<MedicationVariant>> medicationCall = medicationVariantService.getMedicationVariants("1", whichPage, null);
      medicationCall.enqueue(new Callback<List<MedicationVariant>>() {
        @Override
        public void onResponse(Call<List<MedicationVariant>> call, Response<List<MedicationVariant>> response) {
          if (response != null) {
            if (response.body() != null) {
              if (response.body().size() > 0) {
                Log.d(TAG, response.body().toString());
                medicationVariants = response.body();
                showUI();
              } else {
                //TODO show no item ui (not failure, just nothing)
                showEmptyUI();
              }
            } else {
              onFailure(call, new Throwable("empty response body"));
            }
          } else {
            onFailure(call, new Throwable("Empty response"));
          }
        }

        @Override
        public void onFailure(Call<List<MedicationVariant>> call, Throwable t) {
          t.printStackTrace();
          showError();
        }
      });

    }

    return view;
  }

  private void showError() {
    //TODO
  }

  private void showEmptyUI() {
    //TODO
  }

  private void showUI() {
    if (recyclerView != null) {
      recyclerView.setAdapter(new medicationVariantRecyclerViewAdapter());
      recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
      progressBar.setVisibility(View.GONE);
      swipeRefreshLayout.setVisibility(View.VISIBLE);
      dragScrollBar = new DragScrollBar(getContext(), recyclerView, true);
      dragScrollBar.addIndicator(new AlphabetIndicator(getContext()), true);
    }
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    if (context instanceof OnFragmentInteractionListener) {
      mListener = (OnFragmentInteractionListener) context;
    } else {
      throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
    }
  }

  @Override
  public void onDetach() {
    super.onDetach();
    mListener = null;
  }

  public class medicationVariantItemViewHolder extends RecyclerView.ViewHolder {

    public medicationVariantItemViewHolder(View itemView) {
      super(itemView);
      //TODO fill me up
    }

  }

  public class medicationVariantRecyclerViewAdapter extends RecyclerView.Adapter<medicationVariantItemViewHolder> implements INameableAdapter{

    public medicationVariantRecyclerViewAdapter() {
      Log.d(TAG, "creating adapter");
    }

    @Override
    public medicationVariantItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      return new medicationVariantItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_inventory, parent, false));
    }

    @Override
    public void onBindViewHolder(medicationVariantItemViewHolder holder, int position) {
      //TODO fill me up
      Log.d(TAG, "position: " + position);
    }

    @Override
    public int getItemCount() {
      if (medicationVariants != null)
        return medicationVariants.size();
      else
        return 0;
    }

    @Override
    public Character getCharacterForElement(int element) {
      return 'y';
    }
  }

}
