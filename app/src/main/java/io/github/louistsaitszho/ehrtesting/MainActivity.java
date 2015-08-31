package io.github.louistsaitszho.ehrtesting;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.MaterialIcons;
import com.joanzapata.iconify.fonts.MaterialModule;
import com.melnykov.fab.FloatingActionButton;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);
//        tb.setTitle(R.string.triage);
        getSupportActionBar().setTitle(getResources().getString(R.string.triage));
        tb.setBackgroundColor(getResources().getColor(R.color.primary_color));
        tb.setCollapsible(true);
        tb.setSubtitle("Village name"); //TODO get it dynamically

        TabLayout tl = (TabLayout) findViewById(R.id.tablayout);
        tl.addTab(tl.newTab().setText(R.string.queue));
        tl.addTab(tl.newTab().setText(R.string.finished));
        tl.setBackgroundColor(getResources().getColor(R.color.primary_color));

        //Attach FAB to Recycler View to enable auto hide
        RecyclerView rv = (RecyclerView) findViewById(R.id.recyclerview);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingactionbutton);
        fab.attachToRecyclerView(rv);
        fab.setImageDrawable(new IconicsDrawable(getApplicationContext(), GoogleMaterial.Icon.gmd_add).color(Color.WHITE).sizeDp(16));

        ImageView ivProfilePic = (ImageView) findViewById(R.id.iv_profile_pic);
        //TODO get and set the actual image. If image does not exist, load drawable
        if (ivProfilePic != null) {
            TextDrawable drawable = TextDrawable.builder().beginConfig().width(60).height(60).endConfig().buildRound("A", getResources().getColor(R.color.accent_color));
            ivProfilePic.setImageDrawable(drawable);    //You can't use Glide with set drawable image
        }

        //TODO transparent status bar padding
        //http://blog.raffaeu.com/archive/2015/04/11/android-and-the-transparent-status-bar.aspx
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        Iconify.with(new MaterialModule());
        menu.findItem(R.id.action_search).setIcon(new IconDrawable(this, MaterialIcons.md_search).actionBarSize());
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
