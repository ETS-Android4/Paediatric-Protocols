package com.mwongera.paediatric_protocols;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

/**
 * Created by mwongera on 3/5/17.
 */

public class NewBornFluids extends Activity {

    Button btn1,btn2;
    Double weight;
    String weighing;
    String age;
    Spinner spinner;
    DecimalFormat df = new DecimalFormat("###.#");

    EditText inputWeight;

    TextView txtvolume,txthrly,txtrate,txtngt;
    Double results1,results2,results3,resultsiv;
    String resultsebm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_born_fluids);
        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#f9a614")));
        inputWeight=(EditText)findViewById(R.id.editText1);
        txtvolume=(TextView)findViewById(R.id.textvolume);
        spinner=(Spinner)findViewById(R.id.spinner1);
        txthrly=(TextView)findViewById(R.id.texthrly);
        txtrate=(TextView)findViewById(R.id.textRate);
        txtngt=(TextView)findViewById(R.id.textNGT);
        btn1=(Button)findViewById(R.id.button1);
        btn2=(Button)findViewById(R.id.button2);

        //String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("years")
        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                weighing=inputWeight.getText().toString();

                //method to calculate dosage once a button is clicked
                if (weighing.length()==0 || String.valueOf(spinner.getSelectedItem()).equalsIgnoreCase("age")){
                    Toast.makeText(getApplicationContext(), "Enter details correctly",Toast.LENGTH_SHORT).show();

                }else{
                    //Toast.makeText(getApplicationContext(), "Enter details correctly",Toast.LENGTH_SHORT).show();
                    weight=Double.parseDouble(weighing);

                    txtvolume.setText("");
                    txthrly.setText("");
                    txtrate.setText("");
                    //inputWeight.setText("");
                    //add a keyboard remover function
                    calculateFeeding(weight,String.valueOf(spinner.getSelectedItem()));
                }


            }




        });
        btn2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                txtvolume.setText("");
                txthrly.setText("");
                txtrate.setText("");
                inputWeight.setText("");
                txtngt.setText("");

            }
        });

    }

    private void calculateFeeding(Double weight, String age) {
        Double ebm=0.0;
        Double ivf1=0.0;
        //for weights >=1.5
        if (weight>=1.5){
            //calculate for day 1
            if (age.equalsIgnoreCase("1")){
                results1=(weight*60);//total
                results2=((weight*60)/8);//3hrly
                results3=((weight*60)/24);//rates
                resultsebm="";
                resultsiv=0.0;

            }else if (age.equalsIgnoreCase("2")){
                results1=(weight*80);//total
                results2=((weight*80)/8);//3hrly
                results3=((weight*80)/24);//rates
                //day two, introduce NGT
                if (weight>=1.5&&weight<2){
                    resultsebm="NGT feeds 7.5 mls 3hrly";

                    resultsiv=(((results2)-7.5))/3;//3hrly

                }else if(weight>=2){
                    resultsebm="NGT feeds 10 mls 3hrly";
                    resultsiv=((results2)-10)/3;//3hrly

                }


            }else if (age.equalsIgnoreCase("3")){

                results1=(weight*100);//total
                results2=((weight*100)/8);//3hrly
                results3=((weight*100)/24);//rates
                //day two, introduce NGT
                if (weight>=1.5&&weight<2){
                    resultsebm="NGT feeds -15 mls 3hrly";
                    resultsiv=((results2)-15)/3;//3hrly

                }else if(weight>=2){
                    resultsebm="NGT feeds -20 mls 3hrly";
                    resultsiv=((results2)-20)/3;//3hrly

                }

            }else if (age.equalsIgnoreCase("4")){

                results1=(weight*120);//total
                results2=((weight*120)/8);//3hrly
                results3=((weight*120)/24);//rates
                //day two, introduce NGT
                if (weight>=1.5&&weight<2){
                    resultsebm="NGT feeds -22.5 mls 3hrly";
                    resultsiv=(((results2)-22.5)/3);//3hrly
                    results2=resultsiv>=results2?0.0:results2;

                }else if(weight>=2){
                    resultsebm="NGT feeds -30 mls 3hrly";
                    resultsiv=(((results2)/8)-30)/3;
                    results2=resultsiv>=results2?0.0:results2;
                }

            }else if (age.equalsIgnoreCase("5")){

                results1=(weight*140);//total
                results2=((weight*140)/8);//3hrly
                results3=((weight*140)/24);//rates
                //day two, introduce NGT
                if (weight>=1.5&&weight<2){
                    resultsebm="NGT feeds -30 mls 3hrly";
                    resultsiv=((results2)-30)/3;
                    results2=resultsiv>=results2?0.0:results2;

                }else if(weight>=2){
                    resultsebm="NGT feeds -40 mls 3hrly";
                    resultsiv=((results2)-40)/3;
                    results2=resultsiv>=results2?0.0:results2;
                }

            }else if (age.equalsIgnoreCase("6")){

                results1=(weight*160);//total
                results2=((weight*160)/8);//3hrly
                results3=((weight*160)/24);//rates
                //day two, introduce NGT
                if (weight>=1.5&&weight<2){
                    resultsebm="NGT feeds -37.5 mls 3hrly";
                    resultsiv=((results2)-37.5)/3;
                    results2=resultsiv>=results2?0.0:results2;

                }else if(weight>=2){
                    resultsebm="NGT feeds -50 mls 3hrly";
                    resultsiv=((results2)-50)/3;
                    results2=resultsiv>=results2?0.0:results2;
                }

            }
//			else if (age.equalsIgnoreCase("7")){
//
//				results1=(weight*180);//total
//				results2=((weight*180)/8);//3hrly
//				results3=((weight*180)/24);//rates
//				//day two, introduce NGT
//				if (weight>=1.5&&weight<2){
//					resultsebm="NGT feeds -45 mls 3hrly";
//					resultsiv=((results2)-45);
//					results2=resultsiv>=results2?0.0:results2;
//
//				}else if(weight>=2){
//					resultsebm="NGT feeds -60 mls 3hrly";
//					resultsiv=((results2)-60);
//					results2=resultsiv>=results2?0.0:results2;
//				}
//
//		}
            else if (age.equalsIgnoreCase("7+")){

                results1=(weight*180);//total
                results2=((weight*180)/8);//3hrly
                results3=((weight*180)/24);//rates
                //day two, introduce NGT
                if (weight>=1.5&&weight<2){
                    resultsebm="NGT feeds -45 mls 3hrly";
                    resultsiv=((results2)-45)/3;
                    results2=resultsiv>=results2?0.0:results2;

                }else if(weight>=2){
                    resultsebm="NGT feeds -60 mls 3hrly";
                    resultsiv=((results2)-45)/3;
                    results2=resultsiv>=results2?0.0:results2;
                }

            }

            //less weight
        }else if(weight<1.5){

            //calculate for day 1
            if (age.equalsIgnoreCase("1")){
                results1=(weight*80);//total
                results2=((weight*80)/8);//3hrly
                results3=((weight*80)/24);//rates
                resultsebm="";
                resultsiv=0.0;

            }else if (age.equalsIgnoreCase("2")){
                results1=(weight*100);//total
                results2=((weight*100)/8);//3hrly
                results3=((weight*100)/24);//rates
                //day two, introduce NGT
                if (weight<1.5){
                    resultsebm="NGT feeds 5 mls 3hrly";
                    resultsiv=((results2)-5)/3;
                    results2=resultsiv>=results2?0.0:results2;
                }

            }else if (age.equalsIgnoreCase("3")){

                results1=(weight*120);//total
                results2=((weight*120)/8);//3hrly
                results3=((weight*120)/24);//rates
                //day two, introduce NGT
                if (weight<1.5){
                    resultsebm="NGT feeds 10 mls 3hrly";
                    resultsiv=(((results2)-10)/3);
                    results2=resultsiv>=results2?0.0:results2;
                }

            }else if (age.equalsIgnoreCase("4")){

                results1=(weight*140);//total
                results2=((weight*140)/8);//3hrly
                results3=((weight*140)/24);//rates
                //day two, introduce NGT
                if (weight<1.5){
                    resultsebm="NGT feeds 15 mls 3hrly";
                    resultsiv=(((results2)-15)/3);
                    results2=resultsiv>=results2?0.0:results2;
                }

            }else if (age.equalsIgnoreCase("5")){

                results1=(weight*160);//total
                results2=((weight*160)/8);//3hrly
                results3=((weight*160)/24);//rates
                //day two, introduce NGT
                if (weight<1.5){
                    resultsebm="NGT feeds 20 mls 3hrly";
                    resultsiv=(((results2)-20)/3);
                    results2=resultsiv>=results2?0.0:results2;
                }

            }else if (age.equalsIgnoreCase("6")){

                results1=(weight*180);//total
                results2=((weight*180)/8);//3hrly
                results3=((weight*180)/24);//rates
                //day two, introduce NGT
                if (weight<1.5){
                    resultsebm="NGT feeds 25 mls 3hrly";
                    resultsiv=(((results2)-25)/3);
                    results2=resultsiv>=results2?0.0:results2;
                }

            }
//			else if (age.equalsIgnoreCase("7")){
//
//				results1=(weight*180);//total
//				results2=((weight*180)/8);//3hrly
//				results3=((weight*180)/24);//rates
//				//day two, introduce NGT
//				if (weight<1.5){
//					resultsebm="NGT feeds 30 mls 3hrly";
//					resultsiv=(((results2)-30)/3);
//					results2=resultsiv>=results2?0.0:results2;
//				}
//
//			}
            else if (age.equalsIgnoreCase("7+")){

                results1=(weight*180);//total
                results2=((weight*180)/8);//3hrly
                results3=((weight*180)/24);//rates
                //day two, introduce NGT
                if (weight<1.5){
                    resultsebm="NGT feeds 35 mls 3hrly";
                    resultsiv=(((results2)-35)/3);
                    results2=resultsiv>=results2?0.0:results2;
                }

            }

        }
        //set the results here;;;;
        double numberivf;
        numberivf=(resultsiv>0)?resultsiv:0.0;
        txtvolume.setText("Total Daily Fluids \n"+df.format(results1)+" Mls");
        txthrly.setText("NG Feeds 3Hrly \n"+df.format(results2)+" Mls");
        txtrate.setText("IV Fluids rates (Mls/hr) \n"+df.format(results3)+" Mls");
        txtngt.setText("NGT Feeds after 24hrs IVF\n"+resultsebm+"\n + \n"+df.format(numberivf)+" IVF");



    }

}
