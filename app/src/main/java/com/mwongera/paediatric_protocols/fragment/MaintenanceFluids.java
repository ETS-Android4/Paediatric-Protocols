package com.mwongera.paediatric_protocols.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.mwongera.paediatric_protocols.R;

import java.text.DecimalFormat;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by mwongera on 2/22/17.
 */
public class MaintenanceFluids extends Activity{


    ImageView imgv;
    AlertDialog dialog;

    PhotoViewAttacher mAttacher;
    double weight;
    String weighing;
    DecimalFormat df = new DecimalFormat("###.##");
    EditText inputWeight;
    TextView txtShock,txtMaintaince,txtAlternative,txtbolus;

    Button btn1;
    String results1,results2,results3,results4;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintainace_fluids);

        inputWeight=(EditText) findViewById(R.id.editText1);
        txtShock=(TextView) findViewById(R.id.textShock);
        txtMaintaince=(TextView) findViewById(R.id.textMaintainance);
        txtAlternative=(TextView) findViewById(R.id.textAlternative);
        txtbolus=(TextView) findViewById(R.id.textBolus);
        btn1=(Button) findViewById(R.id.button1);
        //btn2=(Button) findViewById(R.id.button2);
        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                weighing=inputWeight.getText().toString();

                //method to calculate dosage once a button is clicked
                if (weighing.length()!=0 ){

                    weight=Double.parseDouble(weighing);

//							txtShock.setText("");
//							txtAlternative.setText("");
//							txtMaintaince.setText("");
//							inputWeight.setText("");
//							txtbolus.setText("");
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

    }
    private void calculateFeeding(double weight) {

        if (weight<=10){
            results1=df.format(weight*100)+" mls/24 hrs";
            results2="at "+ df.format((weight*100)/24)+" mls/hr";
            results3="Drip Rates\n Adult set: " +df.format((weight*100*20)/(24*60)) +" drops per min"+
                    "\n Paediatric Set: "+df.format((weight*100*60)/(24*60)) +" drops per min";
            results4="Feed Bolus: "+df.format((weight*100)/8)+ " mls 3hrly";
        }else if (weight>10 && weight<=20){
            results1=df.format(((weight-10)*50)+1000)+" mls/24 hrs";
            results2="at "+ df.format((((weight-10)*50)+1000)/24)+" mls/hr";
            results3="Drip Rates\n Adult set: " +df.format(((((weight-10)*50)+1000)*20)/(24*60)) +" drops per min"+
                    "\n Paediatric Set: "+df.format(((((weight-10)*50)+1000)*60)/(24*60)) +" drops per min";
            results4="Feed Bolus: "+df.format(((((weight-10)*50)+1000))/8)+ " mls 3hrly";

        }else if (weight>20){
            results1=df.format(((weight-20)*25)+1500)+" mls/24 hrs";
            results2="at "+ df.format((((weight-20)*25)+1500)/24)+" mls/hr";
            results3="Drip Rates\n Adult set: " +df.format(((((weight-20)*25)+1500)*20)/(24*60)) +" drops per min"+
                    "\n Paediatric Set: "+df.format(((((weight-20)*25)+1500)*60)/(24*60)) +" drops per min";
            results4="Feed Bolus: "+df.format((((weight-20)*25)+1500)/8)+ " mls 3hrly";
        }else{

        }
//show results
        txtShock.setText("Total volume: "+results1);
        txtMaintaince.setText(results2);
        txtAlternative.setText(results3);
        txtbolus.setText(results4);


    }


}
