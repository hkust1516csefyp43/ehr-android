package io.github.hkust1516csefyp43.easymed.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import io.github.hkust1516csefyp43.easymed.R;

/**
 * Created by Tejas_K on 6/4/2017.
 */

public class LandingPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landingpage);
        ImageButton triageButton = (ImageButton) findViewById(R.id.triageButton);
        ImageButton pharmacyButton = (ImageButton) findViewById(R.id.pharmacyButton);
        ImageButton faqButton = (ImageButton) findViewById(R.id.faqButton);
        ImageButton consultationButton = (ImageButton) findViewById(R.id.consultationButton);
        Button settingsButton = (Button) findViewById(R.id.button3);
        Button logoutButton = (Button) findViewById(R.id.button5);

        if (triageButton != null) {
            triageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(LandingPageActivity.this, DrawerActivity.class);
                    intent.putExtra("Triage", R.id.nav_triage);
                    startActivity(intent);
                }
            });
        }

        if (pharmacyButton != null) {
            pharmacyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(LandingPageActivity.this, DrawerActivity.class);
                    intent.putExtra("Pharmacy", R.id.nav_pharmacy);

                    startActivity(intent);
                }
            });
        }

//        if (faqButton != null) {
//            pharmacyButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(LandingPageActivity.this, .class);
//                    startActivity(intent);
//                }
//            });
//        }

        if (consultationButton != null) {
            consultationButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(LandingPageActivity.this, DrawerActivity.class);
                    intent.putExtra("Consultation", R.id.nav_consultation);
                    startActivity(intent);
                }
            });
        }

        if (settingsButton != null) {
            settingsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(LandingPageActivity.this, DrawerActivity.class);
                    intent.putExtra("Settings", R.id.nav_admin);
                    startActivity(intent);
                }
            });
        }

        if (logoutButton != null) {
            logoutButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(LandingPageActivity.this, DrawerActivity.class);
                    intent.putExtra("Logout", R.id.nav_logout);
                    startActivity(intent);
                }
            });
        }
    }

}