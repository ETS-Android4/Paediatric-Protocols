package bpp.kelvian.com;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.analytics.FirebaseAnalytics;

/**
 * Created by kelvian on 2/22/17.
 */

public class NewbornCare extends AppCompatActivity {

    Button button1, button2, button3, button4;
    private FirebaseAnalytics mFirebaseAnalytics;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.newborn_activity);

        addListenerOnButton();

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
    }

    public void addListenerOnButton() {
        button1 = (Button) findViewById(R.id.button1);
        //button2 = (Button) findViewById(R.id.button2);
       // button3 = (Button) findViewById(R.id.button3);
        //button4 = (Button) findViewById(R.id.button4);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewbornCare.this, Resuscitation.class);
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewbornCare.this, Sepsis.class);
                startActivity(intent);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewbornCare.this, Jaundice.class);
                startActivity(intent);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewbornCare.this, FeedingActivity.class);
                startActivity(intent);
            }
        });


    }

}
