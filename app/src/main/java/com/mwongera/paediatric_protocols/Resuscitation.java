package com.mwongera.paediatric_protocols;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


/**
 * Created by mwongera on 2/22/17.
 */

public class Resuscitation extends AppCompatActivity {

    Button button;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_resuscitation);

        addListenerOnButton();
    }

    public void addListenerOnButton() {
        button = (Button) findViewById(R.id.button1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(this, BasicSupport.class);
                startActivity(intent);
            }
        });
    }


}
