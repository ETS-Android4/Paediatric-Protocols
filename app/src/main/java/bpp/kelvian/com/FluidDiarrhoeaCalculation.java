package bpp.kelvian.com;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.text.DecimalFormat;

/**
 * Created by kelvian on 3/4/17.
 */

public class FluidDiarrhoeaCalculation extends Activity{

    double weight;
    String Oedema,weighing;
    DecimalFormat df = new DecimalFormat("###.##");
    EditText inputWeight;
    TextView txtShock,txtMaintaince,txtAlternative;

    Button btn1;
    String results1,results2,results3;
    private FirebaseAnalytics mFirebaseAnalytics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fluidsin_diarrhoea_calculation);
        //getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#f9a614")));
        inputWeight=(EditText)findViewById(R.id.editText1);
        txtShock=(TextView)findViewById(R.id.textShock);
        txtMaintaince=(TextView)findViewById(R.id.textMaintainance);
        txtAlternative=(TextView)findViewById(R.id.textAlternative);
        btn1=(Button)findViewById(R.id.button1);
        //btn2=(Button)findViewById(R.id.button2);
        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                weighing=inputWeight.getText().toString();

                //method to calculate dosage once a button is clicked
                if (weighing.length()!=0 ){

                    weight=Double.parseDouble(weighing);

                    txtShock.setText("");
                    txtAlternative.setText("");
                    txtMaintaince.setText("");
                    inputWeight.setText("");
                    //add a keyboard remover function
                    calculateFeeding(weight);
                }else{
                    Toast.makeText(getApplicationContext(), "Enter details correctly",Toast.LENGTH_SHORT).show();
                }


            }


        });
        MobileAds.initialize(getApplicationContext(), "ca-app-pub-3940256099942544~3347511713");

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

    }
    private void calculateFeeding(double weight) {
        // TODO Auto-generated method stub
        //Shock
        results1=df.format(weight*20)+" mls in 2hrs of ringers with 5 % Dextrose";
        results2=df.format(weight*4)+" mls/hr maintainance fluids";
        results3=df.format(weight*10)+" mls/Kg Oral/NGT Volume only for 2hrs \n  Alternate Volume "+df.format(7.5*weight)+" mls/hr of F75 and Resomal for 10 hrs"+
                "\n Then Switch to Feeding Schedule at 12hrs";
        //set results
        txtShock.setText("Shock\n"+results1);
        txtMaintaince.setText("E.Maintainance\n"+results2);
        txtAlternative.setText("Oral or NGT\n"+results3);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.fluidsin_diarrhoea_calculation, menu);
        return true;
    }

}
