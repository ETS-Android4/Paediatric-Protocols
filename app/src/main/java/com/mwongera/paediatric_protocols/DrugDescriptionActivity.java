package com.mwongera.paediatric_protocols;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.DecimalFormat;
import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by mwongera on 2/10/17.
 */

public class DrugDescriptionActivity extends Activity {
    DecimalFormat df = new DecimalFormat("###.##");
    EditText inputWeight, inputAge;
    TextView results,txtDrug;
    WebView webview;
    Spinner aged;
    int age;
    int positioned = 0;
    Double weight;
    String aging, weighing;
    String drugtype;
    String position;
    String drugDetails=null;
    String description=null;
    String [] lan;
    Button btn1, btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_description);

        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#f9a614")));
        inputAge=(EditText)findViewById(R.id.editText2);
        inputWeight=(EditText)findViewById(R.id.editText1);
        results=(TextView)findViewById(R.id.textResults);
        txtDrug=(TextView)findViewById(R.id.textDrug);
        webview=(WebView)findViewById(R.id.webView1);
        aged=(Spinner)findViewById(R.id.spinner1);
        btn1=(Button)findViewById(R.id.button1);
        btn2=(Button)findViewById(R.id.button2);

        //drug type description
        Intent i = getIntent();
        drugtype=i.getStringExtra("drugtype");
        position=i.getStringExtra("position");
        txtDrug.setText(drugtype);
        positioned= Integer.parseInt(position);

        btn1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                aging = inputAge.getText().toString();
                weighing = inputWeight.getText().toString();

                //method to calculate dosage once a button is clicked
                if (weighing.length() != 0 && aging.length() != 0) {
                    age = Integer.parseInt(aging);
                    weight = Double.parseDouble(weighing);
                    results.setText("");

                    //add a keyboard remover function
                    calculateDosage(weight,age,positioned);
                }else{
                    Toast.makeText(getApplicationContext(), "Enter details correctly", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                results.setText("");
                inputAge.setText("");
                inputWeight.setText("");
                webview.clearView();
            }
        });
    }
    private void calculateDosage(Double weight2, int agedd, int pos) {
        //Do a switch case for all the drugs
        switch (pos) {
            case 0:
                drugDetails = (""+df.format(0.1*weight2)+" ml in resuscitation");
                description="To make this strength dilute 1 ml of 1\tin 1000 adrenaline in 9\tmls\"\t\n" +
                        "\t+\"water for injection to make 10mls.";
                results.setText(drugDetails);
                webview.loadData(description, "text/html", "UTF-8");
                break;
            case 1:
                //Albendazole Age < 2yrs, 200mg stat
                //Age ≥ 2yrs, 400mg stat
                if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("days") || (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("months"))){
                    drugDetails="200mg stat";

                }else if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("years")){
                    if (agedd<2){
                        drugDetails="200mg stat";
                    }else{

                        drugDetails="400mg stat";
                    }

                }
                // add values to the txtviews
                results.setText(drugDetails);
                break;
            case 2:
                //Amikacin Age 1mo to 18 yrs, 15mg/kg once daily; same dosing can be used in newborns.
                if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("years") || (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("months"))){
                    if (agedd>=1 && agedd<=18){
                        drugDetails= (15*weight2+" mg once daily");
                        description="Ideally Amikacin trough concentration should be monitored (if available). If serious gram negative infection / resistance to gentamicin higher doses may be used with monitoring";
                        results.setText(drugDetails);
                    }else if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("days")) {
                        drugDetails="";
                        results.setText(drugDetails);
                    }

                }

                webview.loadData(description, "text/html", "UTF-8");
                break;
            case 3:
                //Aminophylline
                //(iv)
                //Newborn Loading dose 6mg/kg  iv over 1 hour or rectal,
                //Maintenance (iv or oral): Age	0-7	days - 2.5mg/kg ///////////////////////////depends more on age
                if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("days")){
                    if(agedd>=1&&agedd<=7){
                        drugDetails="Newborn Loading dose "+ 6*weight2+" mg  iv over 1 hour or rectal" +
                                "\n Maintenance (iv or oral):Age 0-7 days- "+ 2.5*weight2+" mg 12hrly " +
                                "\n Asthma:"+6*weight2+"mg iv first dose over 30 mins";


                    }else if(agedd>7&&agedd<=28){
                        drugDetails="Newborn Loading dose "+ 6*weight2+" mg  iv over 1 hour or rectal" +
                                "\n Maintenance (iv or oral):Age 7-28 days- "+ 4*weight2+" mg 12hrly " +
                                "\n Asthma:"+6*weight2+"mg iv first dose over 30 mins";


                    }

                }else{
                    drugDetails="Newborn Loading dose "+ 6*weight2+" mg  iv over 1 hour or rectal" +
                            "\n Maintenance (iv or oral):Age 7-28 days- "+ 4*weight2+" mg 12hrly " +
                            "\n Asthma:"+6*weight2+"mg iv first dose over 30 mins";

                }
                description="<html><p>Newborn Loading dose 6mg/kg  iv over 1 hour or rectal,<br/>"+
                        "Maintenance (iv or oral): Age	0-7	days - 2.5mg/kg "+
                        "12hrly, Age 7-28 days-	4mg/kg 12hrly.<br/>"+
                        "Asthma: 6mg/kg iv first dose over 30 mins</p></html>";


                results.setText(drugDetails);
                ///calculate the maintainance doses
                //description=" Age7-28 days "+4*weight2+" mg 12hrly<br/> ";
                webview.loadData(description, "text/html", "UTF-8");
                break;
        }
    }

}
