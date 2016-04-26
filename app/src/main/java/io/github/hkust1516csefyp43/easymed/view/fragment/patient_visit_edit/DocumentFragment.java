package io.github.hkust1516csefyp43.easymed.view.fragment.patient_visit_edit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.typeface.IIcon;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;

import io.github.hkust1516csefyp43.easymed.R;
import io.github.hkust1516csefyp43.easymed.listener.OnSendData;
import io.github.hkust1516csefyp43.easymed.pojo.server_response.Document;
import io.github.hkust1516csefyp43.easymed.utility.Const;
import jp.wasabeef.richeditor.RichEditor;

public class DocumentFragment extends Fragment implements OnSendData{
  public static final String TAG = DocumentFragment.class.getSimpleName();
  private static final String key1 = Const.BundleKey.EDIT_PATIENT;
  private static final String key2 = Const.BundleKey.WHICH_DOCUMENT;

  private RichEditor mEditor;
  private LinearLayout hsvLL;

  private Document document;
  private int whichDocument;

  /**
   * TODO pqtient_id (to call API)
   * @param patientId
   * @param whichDocument = 0 for HPI, 1 for Family History, 2 for social history
   * @return
   */
  public static DocumentFragment newInstance(String patientId, Integer whichDocument) {
    DocumentFragment fragment = new DocumentFragment();
    Bundle args = new Bundle();
    args.putString(key1, patientId);
    args.putInt(key2, whichDocument);
    fragment.setArguments(args);
    return fragment;
  }

  public DocumentFragment() {
    // Required empty public constructor
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      String patientId = getArguments().getString(key1);
      whichDocument = getArguments().getInt(key2, -1);
      //TODO get document iff whichDocument >= 0
      switch (whichDocument) {
        case 0:
          break;
        case 1:
          break;
        case 2:
          break;
      }
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view =  inflater.inflate(R.layout.fragment_document, container, false);
    if (view != null) {
      mEditor = (RichEditor) view.findViewById(R.id.editor);
      hsvLL = (LinearLayout) view.findViewById(R.id.HsvLL);

      if (mEditor != null) {
        mEditor.setAlignCenter();
        mEditor.setEditorFontSize(14);
        mEditor.setEditorFontColor(Color.BLACK);
        mEditor.setPadding(8, 8, 8, 8);
        mEditor.setPlaceholder("Tap here and start typing");
        if (document != null && document.getDocumentInHtml() != null) {
          mEditor.setHtml(document.getDocumentInHtml());
        }
        mEditor.scrollTo(0, mEditor.getContentHeight());
        mEditor.pageDown(true);
        addButtons(getContext());
      }
    }
    return view;
  }

  private void addButtons(Context context) {
    addButton(context, CommunityMaterial.Icon.cmd_undo, new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mEditor.undo();
      }
    });

    addButton(context, CommunityMaterial.Icon.cmd_redo, new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mEditor.redo();
      }
    });

    addButton(context, CommunityMaterial.Icon.cmd_format_list_bulleted, new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mEditor.setBullets();
      }
    });

    addButton(context, CommunityMaterial.Icon.cmd_format_list_numbers, new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mEditor.setNumbers();
      }
    });

    addButton(context, CommunityMaterial.Icon.cmd_format_bold, new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mEditor.setBold();
      }
    });
    addButton(context, CommunityMaterial.Icon.cmd_format_italic, new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mEditor.setItalic();
      }
    });
    addButton(context, CommunityMaterial.Icon.cmd_format_underline, new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mEditor.setUnderline();
      }
    });
    addButton(context, CommunityMaterial.Icon.cmd_format_strikethrough, new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mEditor.setStrikeThrough();
      }
    });
    addButton(context, CommunityMaterial.Icon.cmd_format_subscript, new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mEditor.setSubscript();
      }
    });
    addButton(context, CommunityMaterial.Icon.cmd_format_superscript, new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mEditor.setSuperscript();
      }
    });
    addButton(context, CommunityMaterial.Icon.cmd_camera, new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        openCamera();
      }
    });
    addButton(context, CommunityMaterial.Icon.cmd_format_align_left, new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mEditor.setAlignLeft();
      }
    });
    addButton(context, CommunityMaterial.Icon.cmd_format_align_center, new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mEditor.setAlignCenter();
      }
    });
    addButton(context, CommunityMaterial.Icon.cmd_format_align_right, new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mEditor.setAlignRight();
      }
    });
  }

  private void openCamera() {
    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
      startActivityForResult(takePictureIntent, 12345);
    }
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == 12345) {
      if (resultCode == Activity.RESULT_OK) {
        Log.d(TAG, "step 1: " + data.toString());
        Bundle extras = data.getExtras();
        Bitmap imageBitmap = (Bitmap) extras.get("data");
        if (imageBitmap != null) {
          Log.d(TAG, "got bitmap");
          ByteArrayOutputStream bao = new ByteArrayOutputStream();
          imageBitmap.compress(Bitmap.CompressFormat.JPEG, 5, bao);
          byte [] ba = bao.toByteArray();
          String ba1 = Base64.encodeToString(ba,Base64.NO_WRAP);
          if (mEditor != null) {
            Log.d(TAG, "inserting image: " + ba1);
            String output = mEditor.getHtml();
            if (output == null) {
              output = "";
            } else if (output.compareTo("null") == 0){
              output = "";
            } else {
              output = output + "<br>";
            }
            mEditor.setHtml(output + "<img src=\"data:image/png;base64," + ba1 + "\"/>");
            Log.d(TAG, "mEditor: " + mEditor.getHtml());
          }
        }
      }
    }
  }

  private void addButton(Context context, IIcon icon, View.OnClickListener onClickListener) {
    ImageView ivBold = new ImageView(context);
    ivBold.setImageDrawable(new IconicsDrawable(getContext(), icon).color(getResources().getColor(R.color.primary_text_color)).sizeDp(48).paddingDp(12));
    ivBold.setOnClickListener(onClickListener);
    hsvLL.addView(ivBold);
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
  }

  @Override
  public Serializable onSendData() {
    if (mEditor != null){
      return mEditor.getHtml();
    } else {
      return null;
    }
  }
}
