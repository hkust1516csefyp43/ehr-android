package io.github.hkust1516csefyp43.ehr.view.fragment.patient_visit_activity;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.typeface.IIcon;

import io.github.hkust1516csefyp43.ehr.R;
import io.github.hkust1516csefyp43.ehr.listener.OnFragmentInteractionListener;
import jp.wasabeef.richeditor.RichEditor;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HPIFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HPIFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match

    // TODO: Rename and change types of parameters
    private RichEditor mEditor;
    private OnFragmentInteractionListener mListener;
    private LinearLayout hsvLL;

    public HPIFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static HPIFragment newInstance(String param1, String param2) {
        HPIFragment fragment = new HPIFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final Context contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.AppTheme2);
        LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);
        View v = localInflater.inflate(R.layout.fragment_hpi, container, false);

        mEditor = (RichEditor) v.findViewById(R.id.editor);
        hsvLL = (LinearLayout) v.findViewById(R.id.HsvLL);

        if (mEditor != null) {
            mEditor.setAlignCenter();
            mEditor.setEditorFontSize(14);
            mEditor.setEditorFontColor(Color.BLACK);
            mEditor.setPadding(8, 8, 8, 8);
            mEditor.setPlaceholder("Tap here and start typing");
            mEditor.setHtml("the html content <br>in string");    //put the content back to the rich editor (i.e. webview)
            mEditor.scrollTo(0, mEditor.getContentHeight());
        }

        //TODO add imageview as button

        addButtons(getContext(), hsvLL, CommunityMaterial.Icon.cmd_undo, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("qqq9", "yes");
                if (mEditor != null) {
                    Log.d("qqq100", mEditor.getHtml());
                }
            }
        });
        addButtons(getContext(), hsvLL, CommunityMaterial.Icon.cmd_redo, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("qqq9", "yes");
                if (mEditor != null) {
                    mEditor.setHtml(mEditor.getHtml() + "<br>" + "<img src=\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAM0AAAD\n" +
                            " NCAMAAAAsYgRbAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5c\n" +
                            " cllPAAAABJQTFRF3NSmzMewPxIG//ncJEJsldTou1jHgAAAARBJREFUeNrs2EEK\n" +
                            " gCAQBVDLuv+V20dENbMY831wKz4Y/VHb/5RGQ0NDQ0NDQ0NDQ0NDQ0NDQ\n" +
                            " 0NDQ0NDQ0NDQ0NDQ0NDQ0NDQ0PzMWtyaGhoaGhoaGhoaGhoaGhoxtb0QGho\n" +
                            " aGhoaGhoaGhoaGhoaMbRLEvv50VTQ9OTQ5OpyZ01GpM2g0bfmDQaL7S+ofFC6x\n" +
                            " v3ZpxJiywakzbvd9r3RWPS9I2+MWk0+kbf0Hih9Y17U0nTHibrDDQ0NDQ0NDQ0\n" +
                            " NDQ0NDQ0NTXbRSL/AK72o6GhoaGhoRlL8951vwsNDQ0NDQ1NDc0WyHtDTEhD\n" +
                            " Q0NDQ0NTS5MdGhoaGhoaGhoaGhoaGhoaGhoaGhoaGposzSHAAErMwwQ2HwRQ\n" +
                            " AAAAAElFTkSuQmCC\" alt=\"beastie.png\">");
                }
            }
        });
        addButtons(getContext(), hsvLL, CommunityMaterial.Icon.cmd_format_bold, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("qqq9", "yes");
                if (mEditor != null) {
                    mEditor.scrollTo(0, mEditor.getContentHeight());
                }
            }
        });
        addButtons(getContext(), hsvLL, CommunityMaterial.Icon.cmd_format_italic, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("qqq9", "yes");
            }
        });
        addButtons(getContext(), hsvLL, CommunityMaterial.Icon.cmd_format_underline, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("qqq9", "yes");
            }
        });
        addButtons(getContext(), hsvLL, CommunityMaterial.Icon.cmd_format_strikethrough, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("qqq9", "yes");
            }
        });
        addButtons(getContext(), hsvLL, CommunityMaterial.Icon.cmd_format_subscript, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("qqq9", "yes");
            }
        });
        addButtons(getContext(), hsvLL, CommunityMaterial.Icon.cmd_format_superscript, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("qqq9", "yes");
            }
        });
        addButtons(getContext(), hsvLL, CommunityMaterial.Icon.cmd_file_image_box, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("qqq9", "yes");
            }
        });
        addButtons(getContext(), hsvLL, CommunityMaterial.Icon.cmd_format_align_left, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("qqq9", "yes");
            }
        });
        addButtons(getContext(), hsvLL, CommunityMaterial.Icon.cmd_format_align_center, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("qqq9", "yes");
            }
        });
        addButtons(getContext(), hsvLL, CommunityMaterial.Icon.cmd_format_align_right, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("qqq9", "yes");
            }
        });

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

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
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void addButtons(Context context, LinearLayout hsvLL, IIcon icon, View.OnClickListener ocl) {
        ImageView ivBold = new ImageView(context);
        ivBold.setImageDrawable(new IconicsDrawable(getContext(), icon).color(getResources().getColor(R.color.primary_text_color)).sizeDp(48).paddingDp(12));
        ivBold.setOnClickListener(ocl);
        hsvLL.addView(ivBold);
    }
}
