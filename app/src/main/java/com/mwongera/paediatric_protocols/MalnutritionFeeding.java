package com.mwongera.paediatric_protocols;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

/**
 * Created by mwongera on 3/5/17.
 */

public class MalnutritionFeeding extends Activity {

    double weight;
    String Oedema,weighing;
    DecimalFormat df = new DecimalFormat("###.##");
    EditText inputWeight;
    TextView tresults1,tresults2, tresults3;
    WebView webview;
    Spinner oedemacheck;
    Button btn1,btn2;
    String results1,results2,results3,results4,results5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_malnutrition_feeding);

        //getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#f9a614")));

        inputWeight=(EditText)findViewById(R.id.editText1);
        tresults1=(TextView)findViewById(R.id.textacute);
        tresults2=(TextView)findViewById(R.id.texttransition);
        tresults3=(TextView)findViewById(R.id.textReharb);
        oedemacheck=(Spinner)findViewById(R.id.spinner1);
        btn1=(Button)findViewById(R.id.button1);
        btn2=(Button)findViewById(R.id.button2);


        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                weighing=inputWeight.getText().toString();

                //method to calculate dosage once a button is clicked
                if (weighing.length()!=0 ){

                    weight=Double.parseDouble(weighing);
                    //results.setText("");

                    //add a keyboard remover function
                    calculateFeeding(weight);
                }else{
                    Toast.makeText(getApplicationContext(), "Enter details correctly",Toast.LENGTH_SHORT).show();
                }


            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                tresults1.setText("");
                tresults2.setText("");
                tresults3.setText("");
                inputWeight.setText("");

            }
        });





    }
    private void calculateFeeding(double weight2) {

        if (String.valueOf(oedemacheck.getSelectedItem()).equalsIgnoreCase("No or moderate Oedema")){
            //no oedema fn acute feeding
            results1= df.format(weight2*130)+" mls/day of F75 as "+df.format((weight2*130)/8) +"mls 3 Hrly";

        }else if (String.valueOf(oedemacheck.getSelectedItem()).equalsIgnoreCase("Severe or even-face Oedema")){
            // oedema fn acute feeding
            results1= df.format(weight2*100)+" mls/day of F75 as "+df.format((weight2*100)/8) +"mls 3 Hrly";
        }
        ////transition phase--->with or without oedema
        results2= df.format(weight2*130)+" mls/day of F100 as "+df.format((weight2*130)/8) +"mls 3 Hrly";

        ///////reharbilitation phase
        results4= df.format(weight2*150)+" mls/day of F100 as "+df.format((weight2*150)/8) +"mls 3 Hrly";
        //if functions from the table Rutf tables
        if (weight2<=4.5){
            //trans->result3
            //rehab->result5
            results3="1.5 packets per 24 hrs";
            results5="2 packets per 24 hrs";

        }else if (weight2>4.5 && weight2<=6.5){
            results3="2.1 packets per 24 hrs";
            results5="2.5 packets per 24 hrs";
        }else if (weight2>6.5 && weight2<=8){
            results3="2.5 packets per 24 hrs";
            results5="3.0 packets per 24 hrs";
        }else if (weight2>8 && weight2<=9){
            results3="2.8 packets per 24 hrs";
            results5="3.5 packets per 24 hrs";
        }else if (weight2>9 && weight2<=10.0){
            results3="3.1 packets per 24 hrs";
            results5="4.0 packets per 24 hrs";
        }else if (weight2>10.0 && weight2<=11.0){
            results3="3.6 packets per 24 hrs";
            results5="4.0 packets per 24 hrs";
        }else if (weight2>11.0 && weight2<=12.0){
            results3="4.0 packets per 24 hrs";
            results5="5.0 packets per 24 hrs";
        }

        //display results
        //results.setText("Output\n F75-Acute Feeding\n"+results1+"\nTransition phase\n"+results2+" or "+results3+" (RUTF)"+"\nRehabilitation phase\n"+results4+" or "+results5+" (RUTF)");
        tresults1.setText("F75 Acute feeding\n"+results1);
        tresults2.setText("Transition phase\n"+results2+"\n or \n"+results3);
        tresults3.setText("Rehabilitation phase\n"+results4+" \nor \n"+results5+" (RUTF)");



    }

}
