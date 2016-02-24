package io.github.hkust1516csefyp43.ehr.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;
import com.amulyakhare.textdrawable.TextDrawable;
import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.ContentViewEvent;
import com.crashlytics.android.answers.SearchEvent;
import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import io.github.hkust1516csefyp43.ehr.R;
import io.github.hkust1516csefyp43.ehr.listener.ListCounterChangedListener;
import io.github.hkust1516csefyp43.ehr.listener.OnFragmentInteractionListener;
import io.github.hkust1516csefyp43.ehr.pojo.Patient;
import io.github.hkust1516csefyp43.ehr.value.Cache;
import io.github.hkust1516csefyp43.ehr.value.Const;
import io.github.hkust1516csefyp43.ehr.view.fragment.two_recycler_view_patients_activity.PostPharmacyRecyclerViewFragment;
import io.github.hkust1516csefyp43.ehr.view.fragment.two_recycler_view_patients_activity.PostTriageRecyclerViewFragment;

public class TwoRecyclerViewPatientsActivity extends AppCompatActivity implements ListCounterChangedListener, OnFragmentInteractionListener {
    //TODO create a util to get theme color according to package

    public final static int PAGES = 2;
    public final String TAG = getClass().getSimpleName();
    private Toolbar tb;
    private TabLayout tl;
    private ViewPager viewPager;
    private FloatingActionButton fab;
    private ListView lv;
    private PostTriageRecyclerViewFragment ptrvf;
    private PostPharmacyRecyclerViewFragment pprvf;
    private Drawer drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] test = {"Slums", "Countries", "Chief complains", "Diagnosis", "Fingers", "Medicines", "Status", "Users", "Synchronization"};

        ArrayAdapter<String> aas = new ArrayAdapter<String>(this, R.layout.list_item, R.id.label, test);
        lv = (ListView) findViewById(R.id.listview);
        lv.setAdapter(aas);
        lv.setVisibility(View.GONE);

        /**
         * Setup the toolbar (the horizontal bar on the top)
         */
        tb = (Toolbar) findViewById(R.id.toolbar);
        if (tb != null) {
            setSupportActionBar(tb);
            tb.setBackgroundColor(ContextCompat.getColor(this, R.color.primary_color));
            tb.setCollapsible(true);
            tb.setSubtitle("Cannal Side"); //TODO get it dynamically
            tb.setTitleTextColor(ContextCompat.getColor(this, R.color.text_color));
            tb.setSubtitleTextColor(ContextCompat.getColor(this, R.color.text_color));
        }
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(getResources().getString(R.string.triage));
        }

        fab = (FloatingActionButton) findViewById(R.id.floatingactionbutton);
        fab.setImageDrawable(new IconicsDrawable(this, GoogleMaterial.Icon.gmd_add).color(Color.WHITE).paddingDp(3).sizeDp(16));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPatientVisit(null);
            }
        });

        /**
         * Create navigation view (i.e. drawer)
         */
        //Define each drawer item + divider
        PrimaryDrawerItem triage = new PrimaryDrawerItem()
                .withName(R.string.triage)
                .withIcon(new IconicsDrawable(getApplicationContext(), CommunityMaterial.Icon.cmd_thermometer).color(Color.GRAY).paddingDp(2))
                .withIdentifier(Const.ID_TRIAGE);
        PrimaryDrawerItem consultation = new PrimaryDrawerItem()
                .withName(R.string.consultation)
                .withIcon(new IconicsDrawable(getApplicationContext(), CommunityMaterial.Icon.cmd_hospital).color(Color.GRAY).paddingDp(2))
                .withIdentifier(Const.ID_CONSULTATION);
        PrimaryDrawerItem pharmacy = new PrimaryDrawerItem()
                .withName(R.string.pharmacy)
                .withIcon(new IconicsDrawable(getApplicationContext(), CommunityMaterial.Icon.cmd_pharmacy).color(Color.GRAY).paddingDp(2))
                .withIdentifier(Const.ID_PHARMACY);

        PrimaryDrawerItem adminDashboard = new PrimaryDrawerItem()
                .withName(R.string.admin)
                .withIcon(new IconicsDrawable(getApplicationContext(), FontAwesome.Icon.faw_male).color(Color.GRAY).paddingDp(2))
                .withIdentifier(Const.ID_ADMIN);

        SecondaryDrawerItem settings = new SecondaryDrawerItem()
                .withName(R.string.settings)
                .withIcon(new IconicsDrawable(getApplicationContext(), GoogleMaterial.Icon.gmd_settings).color(Color.GRAY).paddingDp(2))
                .withIdentifier(Const.ID_SETTINGS);
        SecondaryDrawerItem about = new SecondaryDrawerItem()
                .withName(R.string.about)
                .withIcon(new IconicsDrawable(getApplicationContext(), GoogleMaterial.Icon.gmd_info).color(Color.GRAY).paddingDp(2))
                .withIdentifier(Const.ID_ABOUT);
        SecondaryDrawerItem logout = new SecondaryDrawerItem()
                .withName(R.string.logout)
                .withIcon(new IconicsDrawable(getApplicationContext(), GoogleMaterial.Icon.gmd_local_phone).color(Color.GRAY).paddingDp(2))
                .withIdentifier(Const.ID_LOGOUT);

        DividerDrawerItem ddi = new DividerDrawerItem();

        //define account header (who is using the app)
        AccountHeader ah = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header_background)
                .addProfiles(new ProfileDrawerItem()
                        //TODO get and set the actual image. If image does not exist, load drawable
                        .withIcon(TextDrawable.builder().beginConfig().width(60).height(60).endConfig().buildRound("LT", ContextCompat.getColor(this, R.color.accent_color)))
                        .withName("Louis Tsai M.D.")
                        .withEmail("louis@email.com"))
                .withSelectionListEnabledForSingleProfile(false)
                .withProfileImagesClickable(false)
                .withOnAccountHeaderSelectionViewClickListener(new AccountHeader.OnAccountHeaderSelectionViewClickListener() {
                    @Override
                    public boolean onClick(View view, IProfile iProfile) {
                        //TODO ProfileActivity
                        Answers.getInstance().logContentView(new ContentViewEvent()
                                .putContentName("Profile account header")
                                .putContentType("Profile")
                                .putContentId("profile_header"));
                        return false;
                    }
                })
                .build();

        //Build the drawer
        drawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(tb)
                .withActionBarDrawerToggleAnimated(true)
                .withCloseOnClick(true)
                .addDrawerItems(triage, consultation, pharmacy, ddi, adminDashboard, ddi, settings, about, logout)
                .withAccountHeader(ah)
                .build();

//        final Context c = this;
        final Activity c = this;
        drawer.setOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
            @Override
            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                switch (drawerItem.getIdentifier()) {
                    case Const.ID_TRIAGE:
                        getSupportActionBar().setTitle(getResources().getString(R.string.triage));
                        getSupportActionBar().setSubtitle("Cannal Side");
                        hideAdmin();
                        ptrvfRecyclerViewScrollToTop();
                        Answers.getInstance().logContentView(new ContentViewEvent()
                                .putContentName("Triage")
                                .putContentType("Station")
                                .putContentId("triage"));
                        break;
                    case Const.ID_CONSULTATION:
                        getSupportActionBar().setTitle(getResources().getString(R.string.consultation));
                        getSupportActionBar().setSubtitle("Cannal Side");
                        hideAdmin();
                        Answers.getInstance().logContentView(new ContentViewEvent()
                                .putContentName("Consultation")
                                .putContentType("Station")
                                .putContentId("consultation"));
                        break;
                    case Const.ID_PHARMACY:
                        getSupportActionBar().setTitle(getResources().getString(R.string.pharmacy));
                        getSupportActionBar().setSubtitle("Cannal Side");
                        hideAdmin();
                        Answers.getInstance().logContentView(new ContentViewEvent()
                                .putContentName("Pharmacy")
                                .putContentType("Station")
                                .putContentId("pharmacy"));
                        break;
                    case Const.ID_INVENTORY:
                        getSupportActionBar().setTitle(getResources().getString(R.string.inventory));
                        Answers.getInstance().logContentView(new ContentViewEvent()
                                .putContentName("Inventory")
                                .putContentType("Admin")
                                .putContentId("inventory"));
                        break;
                    case Const.ID_ADMIN:
                        getSupportActionBar().setTitle(getResources().getString(R.string.admin));
                        getSupportActionBar().setSubtitle(null);
                        shoeAdmin();
                        Answers.getInstance().logContentView(new ContentViewEvent()
                                .putContentName("Admin")
                                .putContentType("Admin")
                                .putContentId("admin"));
                        break;
                    case Const.ID_SETTINGS:
                        openSettings();
//                        getSupportActionBar().setTitle(getResources().getString(R.string.settings));
//                        getSupportActionBar().setSubtitle(null);
                        Answers.getInstance().logContentView(new ContentViewEvent()
                                .putContentName("Settings")
                                .putContentType("Settings & About")
                                .putContentId("settings"));
                        break;
                    case Const.ID_ABOUT:
                        openAbout();
//                                getSupportActionBar().setTitle(getResources().getString(R.string.about));
//                                getSupportActionBar().setSubtitle(null);
                        getSupportActionBar().setTitle(getResources().getString(R.string.about));
                        getSupportActionBar().setSubtitle(null);
                        Answers.getInstance().logContentView(new ContentViewEvent()
                                .putContentName("About")
                                .putContentType("Settings & About")
                                .putContentId("about"));
                        break;
                    case Const.ID_LOGOUT:
                        new MaterialDialog.Builder(c)
                                .theme(Theme.LIGHT)
                                .autoDismiss(true)
                                .content("Are you sure you want to logout?")
                                .positiveText("Logout")
                                //TODO icon?
                                //TODO different color for +ve and -ve text
                                .onPositive(new MaterialDialog.SingleButtonCallback() {
                                    @Override
                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                        dialog.dismiss();
                                        Cache.clearUser(getApplicationContext());
                                        openLogin();
                                        c.finish();
                                    }
                                })
                                .negativeText("Dismiss")
                                .onNegative(new MaterialDialog.SingleButtonCallback() {
                                    @Override
                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                        dialog.dismiss();
                                    }
                                })
                                .show();
                        getSupportActionBar().setTitle(getResources().getString(R.string.settings));
                        getSupportActionBar().setSubtitle(null);
                        Answers.getInstance().logContentView(new ContentViewEvent()
                                .putContentName("Logout")
                                .putContentType("Logout")
                                .putContentId("logout"));
                        break;
                }
                return false;
            }
        });

        Answers.getInstance().logContentView(new ContentViewEvent()
                .putContentName("Triage")
                .putContentType("Station")
                .putContentId("triage"));


        /**
         * Initiate the tabs
         */
        tl = (TabLayout) findViewById(R.id.tablayout);
        String queuePlusNo = getString(R.string.queue);
        String finishedPlusNo = getString(R.string.finished);
        tl.addTab(tl.newTab().setText(queuePlusNo));
        tl.addTab(tl.newTab().setText(finishedPlusNo));
        tl.setBackgroundColor(ContextCompat.getColor(this, R.color.primary_color));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.findItem(R.id.action_search).setIcon(new IconicsDrawable(getApplicationContext(), GoogleMaterial.Icon.gmd_search).color(Color.WHITE).actionBar().paddingDp(2)).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Answers.getInstance().logSearch(new SearchEvent());
                Answers.getInstance().logContentView(new ContentViewEvent()
                        .putContentName("Search")
                        .putContentType("Search")
                        .putContentId("search"));
                return false;
            }
        });
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        recyclerViewAdapter rvAdapter = new recyclerViewAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        if (viewPager != null && tl != null) {
            viewPager.setAdapter(rvAdapter);
            viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tl));
            tl.setOnTabSelectedListener(
                    new TabLayout.OnTabSelectedListener() {
                        @Override
                        public void onTabSelected(TabLayout.Tab tab) {
                            viewPager.setCurrentItem(tab.getPosition());
                        }

                        @Override
                        public void onTabUnselected(TabLayout.Tab tab) {
                        }

                        @Override
                        public void onTabReselected(TabLayout.Tab tab) {
                            viewPager.setCurrentItem(tab.getPosition());
                        }
                    }
            );
        } else {
            Log.d("qqq", "somethings wrong");
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }

    private void hideAdmin() {
        tl.setVisibility(View.VISIBLE);
        viewPager.setVisibility(View.VISIBLE);
        fab.setVisibility(View.VISIBLE);
        lv.setVisibility(View.GONE);
    }

    private void shoeAdmin() {
        tl.setVisibility(View.GONE);
        viewPager.setVisibility(View.GONE);
        fab.setVisibility(View.GONE);
        lv.setVisibility(View.VISIBLE);
    }

    public void openAbout() {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

    public void openLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void openSettings() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void openPatientVisit(Patient p) {
        Intent intent = new Intent(this, PatientVisitActivity.class);
        if (p != null) {
            intent.putExtra("patient", p);
        }
        startActivity(intent);
    }

    private void ptrvfRecyclerViewScrollToTop() {
        ptrvf.scrollToTop();
    }

    /**
     * TODO "Finished"
     *
     * @param position of tab, 0 >> Left, 1 >> right
     * @param size     i.e. the number
     */
    @Override
    public void onCounterChangedListener(int position, int size) {
        Log.d("qqq26", "tab position/size: " + position + "/" + size);
        String message = "";
        switch (position) {
            case 0:
                if (size > 0)
                    message = getString(R.string.queue) + "(" + size + ")";
                else
                    message = getString(R.string.queue);
                break;
            case 1:
                message = getString(R.string.finished);
        }
        try {
            tl.getTabAt(position).setText(message);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public class recyclerViewAdapter extends FragmentStatePagerAdapter {

        public recyclerViewAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    Log.d(TAG, "0");
                    if (ptrvf == null)
                        ptrvf = PostTriageRecyclerViewFragment.newInstance();
                    return ptrvf;
                case 1:
                    Log.d(TAG, "1");
                    if (pprvf == null)
                        pprvf = PostPharmacyRecyclerViewFragment.newInstance();
                    return pprvf;
                default:
                    Log.d(TAG, "default");
                    if (ptrvf == null)
                        ptrvf = PostTriageRecyclerViewFragment.newInstance();
                    return ptrvf;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
