package io.github.hkust1516csefyp43.ehr;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.amulyakhare.textdrawable.TextDrawable;
import com.melnykov.fab.FloatingActionButton;
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

public class MainActivity extends AppCompatActivity {
    //TODO create a util to get theme color according to package

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle(getResources().getString(R.string.triage));
        tb.setBackgroundColor(ContextCompat.getColor(this, R.color.primary_color));
        tb.setCollapsible(true);
        tb.setSubtitle("Cannal Side"); //TODO get it dynamically
        tb.setTitleTextColor(ContextCompat.getColor(this, R.color.text_color));
        tb.setSubtitleTextColor(ContextCompat.getColor(this, R.color.text_color));

        PrimaryDrawerItem triage = new PrimaryDrawerItem()
                .withName(R.string.triage)
                .withIcon(new IconicsDrawable(getApplicationContext(), CommunityMaterial.Icon.cmd_thermometer).color(Color.GRAY).paddingDp(2))
                .withIdentifier(Consts.ID_TRIAGE);
        PrimaryDrawerItem consultation = new PrimaryDrawerItem()
                .withName(R.string.consultation)
                .withIcon(new IconicsDrawable(getApplicationContext(), CommunityMaterial.Icon.cmd_hospital).color(Color.GRAY).paddingDp(2))
                .withIdentifier(Consts.ID_CONSULTATION);
        PrimaryDrawerItem pharmacy = new PrimaryDrawerItem()
                .withName(R.string.pharmacy)
                .withIcon(new IconicsDrawable(getApplicationContext(), CommunityMaterial.Icon.cmd_pharmacy).color(Color.GRAY).paddingDp(2))
                .withIdentifier(Consts.ID_PHARMACY);

        PrimaryDrawerItem inventory = new PrimaryDrawerItem()
                .withName(R.string.inventory)
                .withIcon(new IconicsDrawable(getApplicationContext(), FontAwesome.Icon.faw_medkit).color(Color.GRAY).paddingDp(2))
                .withIdentifier(Consts.ID_INVENTORY);
        PrimaryDrawerItem adminDashboard = new PrimaryDrawerItem()
                .withName(R.string.admin)
                .withIcon(new IconicsDrawable(getApplicationContext(), FontAwesome.Icon.faw_male).color(Color.GRAY).paddingDp(2))
                .withIdentifier(Consts.ID_ADMIN);

        SecondaryDrawerItem settings = new SecondaryDrawerItem()
                .withName(R.string.settings)
                .withIcon(new IconicsDrawable(getApplicationContext(), GoogleMaterial.Icon.gmd_settings).color(Color.GRAY).paddingDp(2))
                .withIdentifier(Consts.ID_SETTINGS);
        SecondaryDrawerItem about = new SecondaryDrawerItem()
                .withName(R.string.about)
                .withIcon(new IconicsDrawable(getApplicationContext(), GoogleMaterial.Icon.gmd_info).color(Color.GRAY).paddingDp(2))
                .withIdentifier(Consts.ID_ABOUT);

        DividerDrawerItem ddi = new DividerDrawerItem();

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
                        return false;
                    }
                })
                .build();

        new DrawerBuilder()
                .withActivity(this)
                .withToolbar(tb)
                .withActionBarDrawerToggleAnimated(true)
                .addDrawerItems(triage, consultation, pharmacy, ddi, inventory, adminDashboard, ddi, settings, about)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int i, IDrawerItem iDrawerItem) {
                        switch (iDrawerItem.getIdentifier()) {
                            case Consts.ID_TRIAGE:
                                getSupportActionBar().setTitle(getResources().getString(R.string.triage));
                                break;
                            case Consts.ID_CONSULTATION:
                                getSupportActionBar().setTitle(getResources().getString(R.string.consultation));
                                break;
                            case Consts.ID_PHARMACY:
                                getSupportActionBar().setTitle(getResources().getString(R.string.pharmacy));
                                break;
                            case Consts.ID_INVENTORY:
                                getSupportActionBar().setTitle(getResources().getString(R.string.inventory));
                                break;
                            case Consts.ID_ADMIN:
                                getSupportActionBar().setTitle(getResources().getString(R.string.admin));
                                getSupportActionBar().setSubtitle(null);
                                break;
                            case Consts.ID_SETTINGS:
                                getSupportActionBar().setTitle(getResources().getString(R.string.settings));
                                getSupportActionBar().setSubtitle(null);
                                break;
                            case Consts.ID_ABOUT:
                                getSupportActionBar().setTitle(getResources().getString(R.string.about));
                                getSupportActionBar().setSubtitle(null);
                                break;
                        }
                        return false;
                    }
                })
                .withAccountHeader(ah)
                .build();

        TabLayout tl = (TabLayout) findViewById(R.id.tablayout);
        String queuePlusNo = getString(R.string.queue) + "(12)";
        String finishedPlusNo = getString(R.string.finished) + "(24)";
        tl.addTab(tl.newTab().setText(queuePlusNo));
        tl.addTab(tl.newTab().setText(finishedPlusNo));
        tl.setBackgroundColor(ContextCompat.getColor(this, R.color.primary_color));

        //Attach FAB to Recycler View to enable auto hide
        RecyclerView rv = (RecyclerView) findViewById(R.id.recyclerview);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingactionbutton);
        fab.attachToRecyclerView(rv);
        fab.setImageDrawable(new IconicsDrawable(getApplicationContext(), GoogleMaterial.Icon.gmd_add).color(Color.WHITE).sizeDp(16));

        //Crash test for parse
        final Context context = this;   //Is there a better way to do this -_-
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                new MaterialDialog.Builder(context)
//                        .title("This is going to crash")
//                        .content("Confirm to crash this thing to test Parse crash report")
//                        .positiveText("Yes")
//                        .negativeText("No")
//                        .callback(new MaterialDialog.ButtonCallback() {
//                            @Override
//                            public void onPositive(MaterialDialog dialog) {
//                                super.onPositive(dialog);
//                                throw new RuntimeException("Test Exception!");
//                            }
//                        })
//                        .show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.findItem(R.id.action_search).setIcon(new IconicsDrawable(getApplicationContext(), GoogleMaterial.Icon.gmd_search).color(Color.WHITE).actionBar().paddingDp(2)).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }
}
