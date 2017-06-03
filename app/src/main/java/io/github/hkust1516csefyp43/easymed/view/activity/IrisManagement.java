package io.github.hkust1516csefyp43.easymed.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import io.github.hkust1516csefyp43.easymed.R;
import io.github.hkust1516csefyp43.easymed.utility.PatientIdentifier;

public class IrisManagement extends AppCompatActivity {

    Button saveButton;
    PatientIdentifier pi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iris_management);

        saveButton = (Button) findViewById(R.id.save_iris_to_phone);
        pi = PatientIdentifier.getPatientIdentifier(this, IrisManagement.this);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pi.saveIrisToPhone()){
                    Toast.makeText(IrisManagement.this, "Irises saved successfully", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(IrisManagement.this, "Unable to save irises", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
