package io.github.hkust1516csefyp43.ehr;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

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

import io.github.hkust1516csefyp43.ehr.value.Const;
import io.github.hkust1516csefyp43.ehr.view.RecyclerViewFragment;

public class MainActivity extends AppCompatActivity implements RecyclerViewFragment.OnFragmentInteractionListener {
    //TODO create a util to get theme color according to package

    public final static int PAGES = 2;
    public final String TAG = getClass().getSimpleName();
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Setup the toolbar (the horizontal bar on the top)
         */
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle(getResources().getString(R.string.triage));
        tb.setBackgroundColor(ContextCompat.getColor(this, R.color.primary_color));
        tb.setCollapsible(true);
        tb.setSubtitle("Cannal Side"); //TODO get it dynamically
        tb.setTitleTextColor(ContextCompat.getColor(this, R.color.text_color));
        tb.setSubtitleTextColor(ContextCompat.getColor(this, R.color.text_color));

        /**
         * Create navigation view (i.e. drawer)
         */
        //Define each drawer item + diveder
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

        PrimaryDrawerItem inventory = new PrimaryDrawerItem()
                .withName(R.string.inventory)
                .withIcon(new IconicsDrawable(getApplicationContext(), FontAwesome.Icon.faw_medkit).color(Color.GRAY).paddingDp(2))
                .withIdentifier(Const.ID_INVENTORY);
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
        new DrawerBuilder()
                .withActivity(this)
                .withToolbar(tb)
                .withActionBarDrawerToggleAnimated(true)
                .addDrawerItems(triage, consultation, pharmacy, ddi, inventory, adminDashboard, ddi, settings, about)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int i, IDrawerItem iDrawerItem) {
                        switch (iDrawerItem.getIdentifier()) {
                            case Const.ID_TRIAGE:
                                getSupportActionBar().setTitle(getResources().getString(R.string.triage));
                                Answers.getInstance().logContentView(new ContentViewEvent()
                                        .putContentName("Triage")
                                        .putContentType("Station")
                                        .putContentId("triage"));
                                break;
                            case Const.ID_CONSULTATION:
                                getSupportActionBar().setTitle(getResources().getString(R.string.consultation));
                                Answers.getInstance().logContentView(new ContentViewEvent()
                                        .putContentName("Consultation")
                                        .putContentType("Station")
                                        .putContentId("consultation"));
                                break;
                            case Const.ID_PHARMACY:
                                getSupportActionBar().setTitle(getResources().getString(R.string.pharmacy));
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
                                Answers.getInstance().logContentView(new ContentViewEvent()
                                        .putContentName("Admin")
                                        .putContentType("Admin")
                                        .putContentId("admin"));
                                break;
                            case Const.ID_SETTINGS:
                                getSupportActionBar().setTitle(getResources().getString(R.string.settings));
                                getSupportActionBar().setSubtitle(null);
                                Answers.getInstance().logContentView(new ContentViewEvent()
                                        .putContentName("Settings")
                                        .putContentType("Settings & About")
                                        .putContentId("settings"));
                                break;
                            case Const.ID_ABOUT:
                                getSupportActionBar().setTitle(getResources().getString(R.string.about));
                                getSupportActionBar().setSubtitle(null);
                                Answers.getInstance().logContentView(new ContentViewEvent()
                                        .putContentName("About")
                                        .putContentType("Settings & About")
                                        .putContentId("about"));
                                break;
                        }
                        return false;
                    }
                })
                .withAccountHeader(ah)
                .build();

        Answers.getInstance().logContentView(new ContentViewEvent()
                .putContentName("Triage")
                .putContentType("Station")
                .putContentId("triage"));


        /**
         * Initiate the tabs
         */
        TabLayout tl = (TabLayout) findViewById(R.id.tablayout);
        String queuePlusNo = getString(R.string.queue) + "(12)";
        String finishedPlusNo = getString(R.string.finished) + "(24)";
        tl.addTab(tl.newTab().setText(queuePlusNo));
        tl.addTab(tl.newTab().setText(finishedPlusNo));
        tl.setBackgroundColor(ContextCompat.getColor(this, R.color.primary_color));

        /**
         * Setup viewpager adaptor + viewpager fragments
         */
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        recyclerViewAdapter rvAdapter = new recyclerViewAdapter(getSupportFragmentManager());
        viewPager.setAdapter(rvAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tl));
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
    public void onFragmentInteraction(Uri uri) {
        //TODO ?
    }

    public class recyclerViewAdapter extends FragmentStatePagerAdapter {

        public recyclerViewAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    Log.d(TAG, "1");
                    return RecyclerViewFragment.newInstance("case", "0");
                case 1:
                    Log.d(TAG, "2");
                    return RecyclerViewFragment.newInstance("case", "1");
                default:
                    Log.d(TAG, "default");
                    return RecyclerViewFragment.newInstance("case", "default");
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

}
