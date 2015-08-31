package io.github.louistsaitszho.ehrtesting;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.melnykov.fab.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        tb.setTitle(R.string.triage);
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

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
