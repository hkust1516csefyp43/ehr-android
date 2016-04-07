package io.github.hkust1516csefyp43.ehr.view.fragment.patient_visit_activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
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
import io.github.hkust1516csefyp43.ehr.listener.OnSendData;
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
public class PersonalDataFragment extends Fragment implements OnCameraRespond, OnSendData, NumberPickerDialogHandlerV2 {
    private static Patient patient;
    private OnFragmentInteractionListener mListener;
    private EditText etFirstName;
    private EditText etLastName;
    private ImageView ivProfilePic;
    private TextView tvBirthday;
    private TextView tvTagNumber;
    private Spinner sGender;
    private int[] birthday;

    public PersonalDataFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
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
        etFirstName = (EditText) v.findViewById(R.id.first_name);
        etLastName = (EditText) v.findViewById(R.id.last_name);
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
                            .setPlusMinusVisibility(View.GONE)
                            .setDecimalVisibility(View.GONE)
                            .addNumberPickerDialogHandler(new NumberPickerDialogHandlerV2() {
                                @Override
                                public void onDialogNumberSet(int reference, BigInteger number, double decimal, boolean isNegative, BigDecimal fullNumber) {
                                    Log.d("qqq330", number.toString() + " / " + decimal + " / " + isNegative + " / " + fullNumber.toString());
                                    if (tvTagNumber != null) {
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
                    }, 1992, 9, 14);            //Channat's birthday
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
        return null;
    }
}
