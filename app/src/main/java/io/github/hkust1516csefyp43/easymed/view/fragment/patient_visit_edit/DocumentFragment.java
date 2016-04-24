package io.github.hkust1516csefyp43.easymed.view.fragment.patient_visit_edit;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.typeface.IIcon;

import java.io.Serializable;

import io.github.hkust1516csefyp43.easymed.R;
import io.github.hkust1516csefyp43.easymed.pojo.server_response.Document;
import io.github.hkust1516csefyp43.easymed.utility.Const;
import jp.wasabeef.richeditor.RichEditor;

public class DocumentFragment extends Fragment {
  public static final String TAG = DocumentFragment.class.getSimpleName();
  private static final String documentKey = Const.BundleKey.DOCUMENT;

  private RichEditor mEditor;
  private LinearLayout hsvLL;

  private Document document;

  public static DocumentFragment newInstance(Document document) {
    DocumentFragment fragment = new DocumentFragment();
    Bundle args = new Bundle();
    args.putSerializable(documentKey, document);
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
      Serializable serializable = getArguments().getSerializable(documentKey);
      if (serializable != null) {
        if (serializable instanceof Document) {
          document = (Document) serializable;
        }
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
        mEditor.scrollTo(0, mEditor.getContentHeight());
        mEditor.pageDown(true);
        if (document != null) {
          //TODO .setHtml
        }
        addButtons(getContext());
      }
    }
    return view;
  }

  private void addButtons(Context context) {
    addButton(context, CommunityMaterial.Icon.cmd_undo, new View.OnClickListener() {
      @Override
      public void onClick(View v) {

      }
    });

    addButton(context, CommunityMaterial.Icon.cmd_redo, new View.OnClickListener() {
      @Override
      public void onClick(View v) {

      }
    });

    addButton(context, CommunityMaterial.Icon.cmd_format_list_bulleted, new View.OnClickListener() {
      @Override
      public void onClick(View v) {

      }
    });

    addButton(context, CommunityMaterial.Icon.cmd_format_list_numbers, new View.OnClickListener() {
      @Override
      public void onClick(View v) {

      }
    });

    addButton(context, CommunityMaterial.Icon.cmd_format_bold, new View.OnClickListener() {
      @Override
      public void onClick(View v) {

      }
    });
    addButton(context, CommunityMaterial.Icon.cmd_format_italic, new View.OnClickListener() {
      @Override
      public void onClick(View v) {

      }
    });
    addButton(context, CommunityMaterial.Icon.cmd_format_underline, new View.OnClickListener() {
      @Override
      public void onClick(View v) {

      }
    });
    addButton(context, CommunityMaterial.Icon.cmd_format_strikethrough, new View.OnClickListener() {
      @Override
      public void onClick(View v) {

      }
    });
    addButton(context, CommunityMaterial.Icon.cmd_format_subscript, new View.OnClickListener() {
      @Override
      public void onClick(View v) {

      }
    });
    addButton(context, CommunityMaterial.Icon.cmd_format_superscript, new View.OnClickListener() {
      @Override
      public void onClick(View v) {

      }
    });
    addButton(context, CommunityMaterial.Icon.cmd_file_image_box, new View.OnClickListener() {
      @Override
      public void onClick(View v) {

      }
    });
    addButton(context, CommunityMaterial.Icon.cmd_format_align_left, new View.OnClickListener() {
      @Override
      public void onClick(View v) {

      }
    });
    addButton(context, CommunityMaterial.Icon.cmd_format_align_center, new View.OnClickListener() {
      @Override
      public void onClick(View v) {

      }
    });
    addButton(context, CommunityMaterial.Icon.cmd_format_align_right, new View.OnClickListener() {
      @Override
      public void onClick(View v) {

      }
    });
    //TODO bulleten
    //TODO number list
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

}
