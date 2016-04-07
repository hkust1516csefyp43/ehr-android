package io.github.hkust1516csefyp43.ehr.view.fragment.patient_visit_activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;
import com.codetroopers.betterpickers.numberpicker.NumberPickerBuilder;
import com.codetroopers.betterpickers.numberpicker.NumberPickerDialogFragment.NumberPickerDialogHandlerV2;
import com.squareup.okhttp.OkHttpClient;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

import io.github.hkust1516csefyp43.ehr.R;
import io.github.hkust1516csefyp43.ehr.listener.OnCameraRespond;
import io.github.hkust1516csefyp43.ehr.listener.OnFragmentInteractionListener;
import io.github.hkust1516csefyp43.ehr.listener.onSendData;
import io.github.hkust1516csefyp43.ehr.pojo.patient_visit.PersonalData;
import io.github.hkust1516csefyp43.ehr.pojo.server_response.v2.Attachment;
import io.github.hkust1516csefyp43.ehr.pojo.server_response.v2.Patient;
import io.github.hkust1516csefyp43.ehr.v2API;
import io.github.hkust1516csefyp43.ehr.value.Const;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PersonalDataFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonalDataFragment extends Fragment implements OnCameraRespond, onSendData {
  private static Patient patient;
  private OnFragmentInteractionListener mListener;
  private EditText etFirstName;
  private EditText etMiddleName;
  private EditText etLastName;
  private EditText etNativeName;
  private EditText etAddress;
  //TODO phone country code spinner
  private EditText etPhoneNumber;
  private ImageView ivProfilePic;
  private TextView tvBirthday;
  private TextView tvTagNumber;
  private Spinner sGender;
  private int[] birthday = new int[3];
  private boolean error = false;

  public PersonalDataFragment() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @return A new instance of fragment PersonalDataFragment.
   */
  // TODO: Rename and change types and number of parameters
  public static PersonalDataFragment newInstance() {
    return new PersonalDataFragment();
  }

  public static PersonalDataFragment newInstance(Patient p) {
    if (p != null) {
      patient = p;
    }
    return new PersonalDataFragment();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    final Context contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.AppTheme2);
    LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);
    View v = localInflater.inflate(R.layout.fragment_personal_data, container, false);
    //TODO type year and get approximate birthday
    etFirstName = (EditText) v.findViewById(R.id.first_name);
    etFirstName.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {

      }

      @Override
      public void afterTextChanged(Editable s) {
        if (s.toString().length() < 1) {
          error = true;
          etFirstName.setError("Patient must have a first name");
        } else {
          error = false;
        }
      }
    });
    etMiddleName = (EditText) v.findViewById(R.id.middle_name);
    etLastName = (EditText) v.findViewById(R.id.last_name);
    etNativeName = (EditText) v.findViewById(R.id.native_name);
    etAddress = (EditText) v.findViewById(R.id.etAddress);
    //TODO phone country code
    etPhoneNumber = (EditText) v.findViewById(R.id.etPhoneNumber);
    ivProfilePic = (ImageView) v.findViewById(R.id.iv_profile_pic);
    tvBirthday = (TextView) v.findViewById(R.id.tvBirthday);
    tvTagNumber = (TextView) v.findViewById(R.id.tvTagNumber);
    if (tvTagNumber != null) {
      tvTagNumber.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          //TODO better dialog (number)
          NumberPickerBuilder npb = new NumberPickerBuilder()
              .setFragmentManager(getFragmentManager())
              .setStyleResId(R.style.BetterPickersDialogFragment)
              .setMinNumber(new BigDecimal(1))
              .setMaxNumber(new BigDecimal(32767))                                                  //Max of smallint in Postgres
              .setPlusMinusVisibility(View.GONE)
              .setDecimalVisibility(View.GONE)
              .addNumberPickerDialogHandler(new NumberPickerDialogHandlerV2() {
                @Override
                public void onDialogNumberSet(int reference, BigInteger number, double decimal, boolean isNegative, BigDecimal fullNumber) {
                  Log.d("qqq330", number.toString() + " / " + decimal + " / " + isNegative + " / " + fullNumber.toString());
                  if (tvTagNumber != null) {
                    error = false;
                    tvTagNumber.setText(number.toString());
                  }
                }
              });
          npb.show();
        }

      });
    }
    sGender = (Spinner) v.findViewById(R.id.sGender);
    sGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

      }

      @Override
      public void onNothingSelected(AdapterView<?> parent) {

      }
    });
    ivProfilePic.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        //TODO add new image dialog
        new MaterialDialog.Builder(getContext())
            .title("Patient picture")
            .items(R.array.image_array)
            .itemsCallback(new MaterialDialog.ListCallback() {
              @Override
              public void onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                switch (which) {
                  case Const.ACTION_TAKE_PICTURE:
                    openCamera();
                    break;
                  case Const.ACTION_SELECT_PICTURE:
                    break;
                  case Const.ACTION_REMOVE_PICTURE:
                    //save as default
                  default:
                    //TODO remove picture & put a Text Drawable
                }
              }
            })
            .theme(Theme.LIGHT)
            .show();
      }
    });
    if (tvBirthday != null) {
      tvBirthday.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          GregorianCalendar gc = new GregorianCalendar();
          DatePickerDialog dpd = DatePickerDialog.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
              if (tvBirthday != null) {
                String date = "" + year + "/" + (monthOfYear + 1) + "/" + dayOfMonth;
                birthday[0] = year;
                birthday[1] = monthOfYear;
                birthday[2] = dayOfMonth;
                tvBirthday.setText(date);
              }
            }
          }, 1992, 8, 14);            //Channat's birthday (14th September 1992)
          dpd.showYearPickerFirst(true);
          dpd.show(getActivity().getFragmentManager(), "qqq");
        }
      });
    }
    //TODO fill ui with patient data
    if (patient != null) {
      if (etFirstName != null && patient.getFirstName() != null)
        etFirstName.setText(patient.getFirstName());
      if (etLastName != null && patient.getLastName() != null)
        etLastName.setText(patient.getLastName());
      //TODO get url of image
      OkHttpClient ohc1 = new OkHttpClient();
      ohc1.setReadTimeout(1, TimeUnit.MINUTES);
      ohc1.setConnectTimeout(1, TimeUnit.MINUTES);
      //TODO don't just use Const.API_ONE2ONE_HEROKU anymore
      Retrofit retrofit = new Retrofit
          .Builder()
          .baseUrl(Const.API_ONE2ONE_HEROKU)
          .addConverterFactory(GsonConverterFactory.create(Const.GsonParserThatWorksWithPGTimestamp))
          .client(ohc1)
          .build();
      v2API.attachments attachmentService = retrofit.create(v2API.attachments.class);
      Call<Attachment> attachmentCall = attachmentService.getAttachment("1", patient.getImageId());
      attachmentCall.enqueue(new Callback<Attachment>() {
        @Override
        public void onResponse(Response<Attachment> response, Retrofit retrofit) {
          //TODO if local >> file_name; else >> cloudinary_url
        }

        @Override
        public void onFailure(Throwable t) {
          //No need to load image
        }
      });

//            if (ivProfilePic != null && patient.getProfilePictureUrl() != null) {
//                String t = Utils.getTextDrawableText(patient);
//                Drawable backup = TextDrawable.builder().buildRound(t, Utils.getTextDrawableColor(t));
//                Glide.with(this).load(patient.getProfilePictureUrl())
//                        .diskCacheStrategy(DiskCacheStrategy.ALL)
//                        .thumbnail(0.01f)
//                        .placeholder(backup)
//                        .fallback(backup)
//                        .into(ivProfilePic);
//            }
    }
    return v;
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
  public void onResume() {
    //TODO restore cache if exists
    super.onResume();
  }

  @Override
  public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putString("TagNum", tvTagNumber.getText().toString());
    outState.putString("LastName", etLastName.getText().toString());
    outState.putString("MiddleName", etMiddleName.getText().toString());
    outState.putString("FirstName", etFirstName.getText().toString());
    outState.putString("NativeName", etNativeName.getText().toString());
    outState.putInt("BDayYear", birthday[0]);
    outState.putInt("BDayMonth", birthday[1]);
    outState.putInt("BDayDate", birthday[2]);
    outState.putString("Address", etAddress.getText().toString());
    outState.putString("PhoneNum", etPhoneNumber.getText().toString());

  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    if (savedInstanceState != null){
      if (tvTagNumber != null)
        tvTagNumber.setText(savedInstanceState.getString("TagNum"));
      if (etLastName != null)
        etLastName.setText(savedInstanceState.getString("LastName"));
      if (etMiddleName != null)
        etMiddleName.setText(savedInstanceState.getString("Middlename"));
      if (etFirstName != null)
        etFirstName.setText(savedInstanceState.getString("FirstName"));
      if (etNativeName != null)
        etNativeName.setText(savedInstanceState.getString("NativeName"));
      if (etAddress != null)
        etAddress.setText(savedInstanceState.getString("Address"));
      if (tvBirthday != null){
        birthday[0] = savedInstanceState.getInt("BDayYear");
        birthday[1] = savedInstanceState.getInt("BDayMonth");
        birthday[2] = savedInstanceState.getInt("BDayDate");
        String date = "" + birthday[0] + "/" + (birthday[1] + 1) + "/" + birthday[2];
        tvBirthday.setText(date);
      }
      if (etPhoneNumber != null)
        etPhoneNumber.setText(savedInstanceState.getString("PhoneNum"));
    }
  }

  @Override
  public void onPause() {
    super.onPause();
    //TODO save data
    //Why catch each separately: even if 1 failed, others can go on (save as much data as possible)
    JSONObject personalData = new JSONObject();
    if (etFirstName != null)
      try {
        personalData.put(Const.KEY_CURRENT_PATIENT_FIRST_NAME, etFirstName.getText().toString());
      } catch (JSONException e) {
        e.printStackTrace();
      }
    if (etLastName != null)
      try {
        personalData.put(Const.KEY_CURRENT_PATIENT_LAST_NAME, etLastName.getText().toString());
      } catch (JSONException e) {
        e.printStackTrace();
      }
    if (tvBirthday != null && birthday != null) {
      try {
        personalData.put(Const.KEY_CURRENT_PATIENT_BIRTH_YEAR, birthday[0]);
        personalData.put(Const.KEY_CURRENT_PATIENT_BIRTH_MONTH, birthday[1]);
        personalData.put(Const.KEY_CURRENT_PATIENT_BIRTH_DAY, birthday[2]);
      } catch (JSONException e) {
        e.printStackTrace();
      }
    }
//        Cache.saveFragmentData(getContext(), Const.KEY_PERSONAL_DATA, personalData);
  }

  @Override
  public void onDetach() {
    super.onDetach();
    mListener = null;
  }

  private void openCamera() {
    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
      startActivityForResult(takePictureIntent, 1);
    }
  }

  @Override
  public void OnCameraRespond(Intent t) {
    if (t != null && ivProfilePic != null) {
      Bundle extras = t.getExtras();
      Bitmap imageBitmap = (Bitmap) extras.get("data");
      //TODO imageBitmap is just a thumbnail >> low resolution >> ugly
      //http://developer.android.com/training/camera/photobasics.html
      if (imageBitmap != null) {
        ivProfilePic.setImageBitmap(imageBitmap);
        ivProfilePic.setScaleType(ImageView.ScaleType.CENTER_CROP);
        ivProfilePic.setMaxHeight(ivProfilePic.getWidth());
      } else {
        Log.d("qqq811", "umm the image is null");
      }
    }
  }

  @Override
  public Object onSendData() {
    if (error)
      return null;
    else {
      PersonalData pd = new PersonalData();
      if (etFirstName != null) {
        if (etFirstName.getText().toString().length() < 1) {
          error = true;
          etFirstName.setError("Patient must have a first name");
          return null;
        } else {
          error = false;
          pd.setFirstName(etFirstName.getText().toString());
        }
      }
      if (etMiddleName != null) {
        pd.setMiddleName(etMiddleName.getText().toString());
      }
      if (etLastName != null) {
        pd.setLastName(etLastName.getText().toString());
      }
      if (etNativeName != null) {
        pd.setNativeName(etNativeName.getText().toString());
      }
      if (tvTagNumber != null) {
        try {
          pd.setTagNumber(Integer.parseInt(tvTagNumber.getText().toString()));
        } catch (NumberFormatException e) {
          //i.e. the text is not number (e.g. "Click here")
          tvTagNumber.setError("You must set a tag number");
          error = true;
          return null;
        }
      }
      if (tvBirthday != null) {
        pd.setBirthYear(birthday[0]);
        pd.setBirthMonth(birthday[1]);
        pd.setBirthDate(birthday[2]);
      }
      if (etAddress != null) {
        pd.setAddress(etAddress.getText().toString());
      }
      if (etPhoneNumber != null) {
        pd.setPhoneNumber(etPhoneNumber.getText().toString());
      }
      return pd;
    }
  }
}
