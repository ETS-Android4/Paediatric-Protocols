package com.mwongera.paediatric_protocols;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.mwongera.paediatric_protocols.fragment.MaintenanceFluids;

/**
 * Created by mwongera on 2/22/17.
 */

public class FluidsAndFeeding extends AppCompatActivity {


    Button button1, button2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fluids_and_feeding);

        addListenerOnButton();
    }

    public void addListenerOnButton() {
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FluidsAndFeeding.this, Neonatal.class);
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FluidsAndFeeding.this, MaintenanceFluids.class);
                startActivity(intent);
            }
        });


    }



}
