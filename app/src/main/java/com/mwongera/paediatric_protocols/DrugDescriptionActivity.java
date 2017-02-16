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
            case 4:
                //Use 25mg/kg/dose for simple infections and 40-45mg/kg
                //for pneumonia
                drugDetails= (25*weight2)+ "mg dose for simple infections\n "+(40*weight2)+"-"+(45*weight2)+" mg for pnemonia";
                results.setText(drugDetails);
                break;
            case 5:
                //Ampicillin Newborn: 50mg/kg/dose 12 hourly iv or im if aged <7days and 8 hourly if aged 7 – 28 days.
                //Age 1m and over: 50mg/kg/dose 6 hourly iv / im

                if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("days")) {
                    if (agedd<7){
                        drugDetails= (50*weight2)+" mg dose 12 hourly or im";
                    }else if (agedd>=7 && agedd<=28){
                        drugDetails= (50*weight2)+" mg dose 8 hourly or im";
                    }

                }else if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("months") || String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("years")){

                    drugDetails= (50*weight2)+" mg dose 6 hourly iv / im";
                }
                results.setText(drugDetails);
                break;
            case 6:
                //Artemether -
                //Lumefantrine

                description="Given with Food \n Stat then at 8hrs then BD on day 2 and 3";

                if (weight < 5) {
                    drugDetails = "1/2 tablet";

                } else {
                    // check the ages
                    if ((String.valueOf(aged.getSelectedItem())
                            .equalsIgnoreCase("months"))) {
                        // months
                        if (agedd >= 3 && agedd < 35) {
                            drugDetails = "1 tablet";

                        }else if(agedd >= 35 && agedd < 60) {
                            drugDetails = "2 tablets";

                        }

                        else {
                            drugDetails = "Error, kindly check the inputs";
                        }

                    } else if ((String.valueOf(aged.getSelectedItem())
                            .equalsIgnoreCase("years"))) {

                        // years
                        if (agedd >= 1 && agedd < 3) {
                            drugDetails = "1 tablet";
                        } else if (agedd >= 3 && agedd <= 8) {
                            drugDetails = "2 tablets";
                        } else if (agedd > 8 && agedd <= 11) {
                            drugDetails = "3 tablets";
                        }else{
                            drugDetails = "Error, kindly check the inputs";
                        }
                    } else {
                        drugDetails = "Error in the Inputs";
                    }

                }
                results.setText(drugDetails);
                webview.loadData(description, "text/html", "UTF-8");
                break;
            case 7:
                //	Artemisinin -
                //	Piperaquine
                description="Given OD for 3 days";
                // check the ages
                if ((String.valueOf(aged.getSelectedItem())
                        .equalsIgnoreCase("months"))) {
                    // months
                    if (agedd >= 3 && agedd <= 35) {
                        drugDetails = "1 paed tab";

                    }else if(agedd > 35 && agedd < 60) {
                        drugDetails = "2 paed tabs";

                    }else{
                        drugDetails = "Error, kindly check the inputs";
                    }

                } else if ((String.valueOf(aged.getSelectedItem())
                        .equalsIgnoreCase("years"))) {

                    // years
                    if (agedd >= 1 && agedd < 3) {
                        drugDetails = "1 paed tab";
                    } else if (agedd >= 3 && agedd <= 5) {
                        drugDetails = "2 paed tab";
                    } else if (agedd > 5 && agedd <= 11) {
                        drugDetails = "1 adult tab";
                    }else{
                        drugDetails = "Error, kindly check the inputs";
                    }
                } else {
                    drugDetails = "Error in the Inputs";
                }

                results.setText(drugDetails);
                webview.loadData(description, "text/html", "UTF-8");
                break;
            case  8:
                //Artesunate Age 1m and over: 2.4mg/kg given iv/im at 0, 12 and 24
                //hours then daily –
                if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("months") || String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("years")){
                    drugDetails= df.format(2.4*weight2)+" mg given iv/im at 0, 12 and 24 hours then daily for max of 7 days\n press here for preparation procedure";
                    //description="change to full course oral ACT as soon as possible after 3 doses when infant/child drinking/breast feeding.";
                    description="As soon as the child can eat drink (after 24 hours for artesunate) then change to a  full course of artemisinin combination therapy (ACT)"
                            +"typically the 1st line oral anti-malarial Artemether Lumefantrine";
                    results.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            Intent i= new Intent (DrugDescriptionActivity.this,MaraliaTreatmentPreparation.class);
                            startActivity(i);
                        }
                    });


                }else{
                    drugDetails="Error, make sure the child is above 1 Month";
                }
                results.setText(drugDetails);
                webview.loadData(description, "text/html", "UTF-8");
                break;
            case 9:
                //Beclomethasone Age < 2yrs	50-100	micrograms	12hrly,
                //Age ≥ 2yrs 100-200 micrograms	12hrly
                if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("months") || String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("days")){
                    drugDetails="50-100	micrograms	12hrly";
                }else if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("years")){
                    if (agedd<2){
                        drugDetails="50-100	micrograms	12hrly";
                    }else if (agedd>=2){
                        drugDetails="100-200 micrograms	12hrly";
                    }
                }
                description="Can double doses to improve control but check technique and follow up carefully";
                results.setText(drugDetails);
                webview.loadData(description, "text/html", "UTF-8");
                break;
            case 10:
                description="Not recommended if jaundiced or age less than	7 days <br/> Double Penicillin doses if treating Meningitis and age greater than 1	month";
                //Benzyl Penicillin (X-pen)Newborn:  50,000 iu/kg/dose 12 hourly iv or im.
                if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("days")){
                    if (agedd<7){
                        drugDetails= (50000*weight2)+" iu/dose 12 hourly iv or im";
                    }else{
                        drugDetails= (50000*weight2)+" iu/dose 6 hourly iv / im";
                    }
                }else{
                    drugDetails= (50000*weight2)+" iu/dose 6 hourly iv / im";
                }
                results.setText(drugDetails);
                webview.loadData(description, "text/html", "UTF-8");
                break;
            case 11:
                //Newborns and up to 4 yrs: 0.25mmol/kg 6 hrly.
                if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("years"))
                {
                    if(agedd>4){
                        drugDetails="Allowed only up to 4 years";
                    }else{
                        drugDetails=(0.25*weight2)+" mmol 6 hourly";
                    }
                }else
                    drugDetails=(0.25*weight2)+" mmol 6 hourly";
                description="<html><p>Calcium Gluconate 1g tabs contain 2.23mmol calcium"
                        +"<br/>Calcium Lactate 300mg tabs contain 1mmol calcium"
                        +"<br/>Maybe required together with Vitamin	D for treating rickets in first 7 days but monitor calcium to prevent hypercalcaemia</p></html>";
                results.setText(drugDetails);
                webview.loadData(description, "text/html", "UTF-8");
                break;
            case 12:
                //Carbamazepine Age 1 m –12 yrs: initially 5 mg/kg at night, increased
                if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("months")){
                    if (agedd>=1){
                        drugDetails=(5*weight2) +" mg  at night, increased as necessary by "+ (2.5*weight2) +"-"+5*weight2+ " mg every 3–7 days "
                                +"\n usual maintenance dose "+5*weight2+" mg 2–3 times daily.";
                        description="Avoid abrupt withdrawal watch carefully for side effects and change treatment if concerns";
                    }else{
                        drugDetails="Error";
                    }
                }else if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("years")){
                    if (agedd<=12){
                        drugDetails=(5*weight2) +" mg  at night, increased as necessary by "+ (2.5*weight2) +"-"+5*weight2+ " mg every 3–7 days "
                                +"\n usual maintenance dose "+5*weight2+" mg 2–3 times daily.";
                        description="Avoid abrupt withdrawal watch carefully for side effects and change treatment if concerns";
                    }else{
                        drugDetails="Error";
                    }
                }else{
                    drugDetails="Error";
                }

                results.setText(drugDetails);
                webview.loadData(description, "text/html", "UTF-8");
                break;
            case 13:
                //Cefotaxime Preferred	to	Ceftriaxone	for	treatment	of	neonatal	meningitis	if	aged	<7	days:
                if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("days")){
                    if (agedd<=7){
                        drugDetails= "Pre-term: "+(50*weight2)+" mg 12 hourly \nTerm aged <7 days: "+ 50*weight2+ "mg 8 hourly" ;
                        results.setText(drugDetails);

                    }else{
                        drugDetails="Drug for only upto 7 days";
                    }
                }else{
                    drugDetails="Drug for only upto 7 days";
                }
                description="Preferred to Ceftriaxone for treatment	of neonatal	meningitis if aged <7 days:";
                results.setText(drugDetails);
                webview.loadData(description, "text/html", "UTF-8");
                break;
            case 14:
                //Ceftriaxone
                //
                if(String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("days")){
                    description="<html><p><ul><li>Ceftriaxone iv/im maximum 50mg/kg 24hrly for neonates but not recommended if jaundiced or &lt 7 days .</li><br/><li>If Meningitis or very severe sepsis, 50mg/kg 12hrly</li></ul></p></html>  ";
                    if(agedd<=7){
                        drugDetails=50*weight2+ " iv/im 24hrly ";

                    }else if (agedd>7){
                        drugDetails=50*weight2+ " iv/im 12hrly ";
                    }

                }else if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("months")){
                    drugDetails=50*weight2+ " iv/im 12hrly ";
                }else if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("years")){
                    //drugDetails=50*weight2+ " iv/im 12hrly ";
                    if (agedd>18){
                        drugDetails="use adult dosages ";
                    }else{
                        drugDetails=50*weight2+ " iv/im 12hrly ";
                    }

                }else{
                    drugDetails="null";
                }
                results.setText(drugDetails);
                webview.loadData(description, "text/html", "UTF-8");

                break;
            case 15:
                //Chloramphenicol
                if(String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("days")){
                    description="<html><p>Chloramphenicol should not be used in babies aged &lt 7 days<br/> 25mg/kg iv/im 6hrly for meningitis in over 7 days old</p></html>  ";
                    if(agedd<=7){
                        drugDetails="null ";

                    }else if (agedd>7){
                        drugDetails=25*weight2+ " iv/im 6hrly - Meningitis ";
                    }

                }else if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("months")){
                    drugDetails=25*weight2+ " iv/im 6hrly - Meningitis ";
                }else if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("years")){
                    if (agedd>18){
                        drugDetails="use adult dosages ";
                    }else{
                        drugDetails=25*weight2+ " iv/im 6hrly - Meningitis ";
                    }
                }else{
                    drugDetails="null";
                }
                results.setText(drugDetails);
                webview.loadData(description, "text/html", "UTF-8");
                break;
            case 16:
                //Ciprofloxacin
                if(String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("days")){
                    description="<html><p>15mg/kg per dose 12hrly for 3 days</p></html>  ";
                    if(agedd<=7){
                        drugDetails="null ";

                    }else if (agedd>7){
                        drugDetails=15*weight2+ " orally 12hrly for 3 days ";
                    }

                }else if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("months")){
                    drugDetails=15*weight2+ " orally 12hrly for 3 days ";
                }else if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("years")){
                    if (agedd>18){
                        drugDetails="use adult dosages ";
                    }else{
                        drugDetails=15*weight2+ " orally 12hrly for 3 days ";
                    }
                }else{
                    drugDetails="null";
                }

                results.setText(drugDetails);
                webview.loadData(description, "text/html", "UTF-8");

                break;
            case 17:
                //Clotrimazole 1%
                drugDetails="Use Clotrimazole paint for oral thrush and apply 2-3 times daily until cleared";
                results.setText(drugDetails);
                //webview.loadData(description, "text/html", "UTF-8");
                break;
            case 18:
                //Co-trimoxazole*
                if (weight2>=2 && weight2<=3){
                    drugDetails="2.5mls 12hrly of 240mg/5ml syrup " +
                            "\n 0.25 of 480mg tabs 12hrly";
                }else if(weight2>=4 && weight2<=10){
                    drugDetails="5mls 12hrly of 240mg/5ml syrup " +
                            "\n 0.5 of 480mg tabs 12hrly";
                }else if(weight2>=11 && weight2<=15){
                    drugDetails="7.5mls 12hrly of 240mg/5ml syrup " +
                            "\n 0.5 of 480mg tabs 12hrly";
                }else if(weight2>=16 && weight2<=20){
                    drugDetails="10mls 12hrly of 240mg/5ml syrup " +
                            "\n 1 of 480mg tabs 12hrly";
                }
                description="<html><p>Co-trimoxazole pneumonia dozing 4mg/kg trimethoprim and 20mg/kg sulphamethoxazole</p></html>  ";
                webview.loadData(description, "text/html", "UTF-8");
                results.setText(drugDetails);

                break;
            case 19:
                //Dexamethasone
                drugDetails=df.format(0.6*weight2)+" mg stat for	severe	croup";
                results.setText(drugDetails);
                break;
            case 20:
                //Dextrose/glucose
                if(String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("days")){
                    description="<html><p>To make 10% glucose 50% Glucose and water or injection:<br/>"
                            +"10 mls syringe:"
                            +"<br/> 2 mls 50% glucose"
                            +"<br/>8 mls Water "
                            +"<br/>20 mls syringe:"
                            +"<br/>4 mls 50% Glucose"
                            +"<br/> 16 mls Water"
                            +"<br/>50% Glucose and 5%"
                            +"<br/>Glucose:"
                            +"<br/>10 mls syringe:"
                            +"<br/>1 mls 50% Glucose"
                            +"<br/>9 mls 5% Glucose "
                            +"<br/>20 mls syringe:"
                            +"<br/> 2 mls 50% Glucose"
                            +"<br/>18 mls 5% Glucose </p></html>  ";
                    if(agedd<=7){
                        drugDetails=2*weight2 +"mls 10% dextrose iv over 5-10 minutes";

                    }else if (agedd>7){
                        drugDetails=(5*weight2)+" mls 10% dextrose iv over 5-10 mins";
                    }

                }else if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("months")){
                    drugDetails=(5*weight2)+" mls 10% dextrose iv over 5-10 mins";
                }else if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("years")){
                    if (agedd>18){
                        drugDetails="use adult dosages ";
                    }else{
                        drugDetails=(5*weight2)+" mls 10% dextrose iv over 5-10 mins";
                    }
                }else{
                    drugDetails="null";
                }

                results.setText(drugDetails);
                webview.loadData(description, "text/html", "UTF-8");


                description= "Note> Newborn: 2 mls/kg";
                results.setText(drugDetails);
                webview.loadData(description, "text/html", "UTF-8");
                break;
            case 21:
                //Dihydrocodeine
                description="<html><p>Age 1–4 yrs: 0.5mg / kg every 4–6 hours <br/>"
                        +"Age 4–12 yrs: 0.5–1 mg/kg (max. 30 mg) every 4–6 hrs</p></html>";
                if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("years")){
                    if (agedd>=1 && agedd<4){
                        drugDetails=0.5*weight2+ "mg every 4–6 hours";
                    }else if (agedd>=4 && agedd<=12){
                        drugDetails=0.5*weight2+" - "+1*weight2+ "mg every 4–6 hours(max. 30 mg)";
                    }

                }
                webview.loadData(description, "text/html", "UTF-8");
                results.setText(drugDetails);
                break;
            case 22:
                //Diazepam (iv)
                if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("months")){
                    if (agedd>1){
                        drugDetails=0.3*weight2+ "mg ="+ df.format((0.3*weight2*8)/10)+" mls of 10mg/2ml)";
                    }else {
                        drugDetails="Not used in neonates ";
                    }

                }else if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("years")){
                    if (agedd>18){
                        drugDetails="Use adult dosages ";
                    }else {

                        drugDetails=0.3*weight2+ "mg ="+ df.format((0.3*weight2*8)/10)+" mls of 10mg/2ml";
                    }

                }else{
                    drugDetails="null ";

                }

                description="<html><p>0.3 mg/kg iv <br/> Not used in neonates</p></html>";
                //drugDetails=0.3*weight2+ "mg (="+300*weight2+" mcg)";
                webview.loadData(description, "text/html", "UTF-8");
                results.setText(drugDetails);
                break;
            case 23:
                description="<html><p>The whole syringe  barrel of a 1ml or 2ml syringe should be inserted gently so	" +
                        "that pr DZ is given at a depth of 4-5cm<br/></p><p>0.3 mg/kg iv <br/> Not used in neonates</p></html>";
                //Diazepam (rectal)m;
                drugDetails=0.5*weight2+ "mg (="+df.format((0.3*weight2*8)/10)+" mls of 10mg/2ml";
                results.setText(drugDetails);
                webview.loadData(description, "text/html", "UTF-8");
                break;
            case 24:
                //Digoxin (oral)
                if (String.valueOf(aged.getSelectedItem())
                        .equalsIgnoreCase("years")) {
                    if (agedd >= 2 && agedd < 5) {
                        drugDetails = "initialy "+ 35
                                * weight2
                                + " micrograms in 3 divided doses for 24 hrs then \n "
                                + 10 * weight2 + " micrograms daily in 1–2 doses";
                    } else if (agedd >= 5 && agedd < 10) {
                        drugDetails = "initialy "+ 25
                                * weight2
                                + " micrograms (max.750 micrograms) in 3 divided doses for 24 hrs then \n "
                                + 6
                                * weight2
                                + " micrograms (max. 250 micrograms daily)daily in 1–2 doses";
                    } else if (agedd >= 5 && agedd < 10) {
                        drugDetails ="initialy "+ 25
                                * weight2
                                + " micrograms (max.750 micrograms) in 3 divided doses for 24 hrs then \n "
                                + 6
                                * weight2
                                + " micrograms (max. 250 micrograms daily)daily in 1–2 doses";
                    } else if (agedd >= 10 && agedd <=18) {
                        drugDetails = "initialy "+ 0.75
                                * weight2
                                +"-"
                                +1.5
                                * weight2
                                + " micrograms in 3 divided doses for 24 hrs then \n "
                                + 62.5
                                * weight2
                                +"-"
                                +250
                                * weight2
                                + " micrograms daily in 1–2 doses";
                    }

                }else{
                    drugDetails="null";
                }
                //add info
                description=getResources().getString(R.string.d24);
                results.setText(drugDetails);
                webview.loadData(description, "text/html", "UTF-8");

                break;
            case 25:
                //Flucloxacillin
                if(String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("days")){
                    description="<html><p>Neonates 25mg/kg  12hrly given as a 125mg/5ml suspension<br/>Over 1 month 50mg/kg per dose 8hrly </p></html>  ";
                    if(agedd<=7){
                        drugDetails=(weight2*25*5)/125 +" mls/ suspension 12 hrly";

                    }else if (agedd>7){
                        drugDetails=15*weight2+ "mg per dose 8 hrly or"+(weight2*25*5)/125 +" mls/ suspension 8 hrly" ;
                    }

                }else if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("months")){
                    drugDetails=15*weight2+ "mg per dose 8 hrly or"+(weight2*25*5)/125 +" mls/ suspension 8 hrly" ;
                }else if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("years")){
                    //drugDetails=50*weight2+ " iv/im 12hrly ";
                    if (agedd>18){
                        drugDetails="use adult dosages ";
                    }else{
                        drugDetails=15*weight2+ "mg per dose 8 hrly or"+(weight2*25*5)/125 +" mls/ suspension 8 hrly" ;
                    }

                }else{
                    drugDetails="null";
                }
                results.setText(drugDetails);
                webview.loadData(description, "text/html", "UTF-8");

                break;
            case 26:
                //Frusemide
                drugDetails=0.5*weight2+" mg to" + 1*weight2+" mg up to 6 hrly";
                results.setText(drugDetails);
                break;
            case 27:
                //Gentamicin
                if(String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("days")){
                    description="<html><p>Neonates &lt 7 days and &lt 2kg - 3mg/kg  24hrly <br/>Neonates &lt 7 days and &gt or = 2kg - 5mg/kg  24hrly <br/> Over 7 days -7.5mg/kg iv/im over 3-5 minutes 24hrly </p></html>  ";
                    if(agedd<=7){
                        if (weight2<2){
                            drugDetails= 3*weight2+" mg iv/im 24hrly";
                        }else if(weight2>=2){
                            drugDetails= 5*weight2+" mg iv/im 24hrly";
                        }

                    }else if (agedd>7){
                        drugDetails=7.5*weight2+" mg iv/im over 3-5 minutes 24hrly ";
                    }

                }else if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("months")){
                    drugDetails=drugDetails=7.5*weight2+" mg iv/im over 3-5 minutes 24hrly ";
                }else if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("years")){

                    if (agedd>18){
                        drugDetails="use adult dosages ";
                    }else{
                        drugDetails=drugDetails=7.5*weight2+" mg iv/im over 3-5 minutes 24hrly ";
                    }

                }else{
                    drugDetails="null";
                }
                results.setText(drugDetails);
                webview.loadData(description, "text/html", "UTF-8");
                break;
            case 28:
                //Hydroxyurea
                drugDetails=20*weight2+ "mg daily – Hb and white cells with neutrophil count must be done monthly.";
                description="<html></p>(for severe SCD only: Pain >3 episodes/yr; stroke;	transfusion	&gt;2/yr; acute chest syndrome)<br/>Stop treatment and consult specialist if neutrophils reduced</p></html>";
                results.setText(drugDetails);
                webview.loadData(description, "text/html", "UTF-8");
                break;
            case 29:
                //Ibuprofen
                drugDetails=5*weight2+"-" +10*weight2+" mg 8 hourly";
                results.setText(drugDetails);
                break;
            case 30:
                //Iron tabs / syrup *
                if (weight2>=3 && weight2<=6){
                    drugDetails="2.5mls of 140mg/5ml syrup 12hrly" ;

                }else if(weight2>=7 && weight2<=9){
                    drugDetails="5mls of 140mg/5ml syrup twice daily" +
                            "\n 0.25 of 200mg tabs twice daily";
                }else if(weight2>=10 && weight2<=14){
                    drugDetails="10mls of 140mg/5ml syrup twice daily" +
                            "\n 0.5 of 200mg tabs twice daily";
                }else if(weight2>=15 && weight2<=20){
                    drugDetails="15mls of 140mg/5ml syrup twice daily" +
                            "\n 0.5 of 200mg tabs twice daily";
                }
                description="<html><p>200mg	Ferrous	sulphate tabs 140mg/5mls Ferrous fumarate syrup</p></html> ";
                webview.loadData(description, "text/html", "UTF-8");
                results.setText(drugDetails);



                break;
            case 31:
                //Lactulose
                //info
                //description="Age 1m–12 yrs, 15-20 mg/kg at a rate not exceeding 1 mg/kg/minute as a loading dose; maintenance dose of 2.5–5 mg/kg twice daily (max. 150mg twice daily);  Similardosing	can	be	used	in	neonates."
                if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("months")){
                    if (agedd>=1 && agedd<=12){
                        drugDetails="2.5 mL twice daily, adjusted according	to response ";
                    }
                }else if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("years")){
                    if (agedd>=1 && agedd<=5){
                        drugDetails="2.5 -10mL twice daily, adjusted according	to response ";
                    }else if(agedd>5){
                        drugDetails="Use adult dosages";
                    }
                }else{
                    drugDetails="";
                }
                results.setText(drugDetails);
                break;
            case 32:
                //Mebendazole
                //(for	age	>	1	yr)		100mg bd for 3 days or 500mg stat
                if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("years")){
                    drugDetails="100mg bd for 3 days or 500mg stat";
                }else{
                    drugDetails="for age >	1 yr";
                }
                description="for age > 1 yr";
                results.setText(drugDetails);

                break;
            case 33:
                //Metronidazole
                if(String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("days")){
                    description="<html><p>Neonates &lt 7 days - 7.5mg/kg iv 12hrly <br/> Over 7 days -7.5mg/kg iv over 8hrly </p></html>  ";
                    if(agedd<=7){
                        drugDetails=7.5*weight2+ " iv 12hrly ";

                    }else if (agedd>7){
                        drugDetails=7.5*weight2+ " iv 8hrly or "+(7.5*weight2)/200 +" of 200mg tabs per dose 8 hrly";
                    }

                }else if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("months")){
                    drugDetails=7.5*weight2+ " iv 8hrly or "+(7.5*weight2)/200 +" of 200mg tabs per dose 8 hrly";
                }else if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("years")){

                    if (agedd>18){
                        drugDetails="use adult dosages ";
                    }else{
                        drugDetails=7.5*weight2+ " iv 8hrly or "+(7.5*weight2)/200 +" of 200mg tabs per dose 8 hrly";
                    }

                }else{
                    drugDetails="null";
                }
                results.setText(drugDetails);
                webview.loadData(description, "text/html", "UTF-8");

                break;
            case 34:
                //Morphine
                if(String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("days")){
                    drugDetails= df.format(0.15*weight2)+" mg";
                }else if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("months")){
                    if (agedd>=1&&agedd<=11){
                        drugDetails=  df.format(0.2*weight2)+" mg";
                    }

                }else if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("years")){
                    if (agedd>=1&&agedd<=5){
                        drugDetails=  df.format(2.5*weight2)+"-"+(5*weight2)+" mg";
                    }else if(agedd>=6&&agedd<=12){
                        drugDetails=  df.format(5*weight2)+"-"+(10*weight2)+" mg";
                    }
                }else{
                    drugDetails="";
                }
                results.setText(drugDetails);
                break;
            case 35:
                //Multivitamins
                //Age <6 m: 2.5mls daily;
                if(String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("days")){
                    drugDetails="2.5mls daily";
                }else if(String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("months")){
                    if (agedd<6){
                        drugDetails="2.5mls daily";
                    }
                }else{
                    drugDetails="5mls 12 hrly";
                }
                description="Age <6 m: 2.5mls daily;\n"+
                        "Age > 6m: 5mls 12 hrly";
                results.setText(drugDetails);
                webview.loadData(description, "text/html", "UTF-8");
                break;
            case 36:
                //Nystatin
                drugDetails="(100,000 iu/ml) 1ml 6hrly (2weeks if HIV +ve)";
                results.setText(drugDetails);

                //webview.loadData(description, "text/html", "UTF-8");
                break;
            case 37:
                //Paracetamol
                //10-15mg / kg 6 to 8 hrly
                description="10-15mg/kg 6 to 8 hrly";
                drugDetails= 10*weight2 +"-"+15*weight2+" mg 6 to 8 hrly";
                webview.loadData(description, "text/html", "UTF-8");
                results.setText(drugDetails);
                break;
            case 38:
                //Pethidine, im
                //0.5 to 1mg / kg every 4- 6 hours
                description="0.5 to 1mg/kg every 4-6 hours";
                drugDetails= 0.5*weight2 +"-"+1*weight2+" mg 4 to 6 hrly";
                results.setText(drugDetails);
                webview.loadData(description, "text/html", "UTF-8");

                break;
            case 39:
                //Phenobarbitone
                description="<html><p>High dose maintainance for Chronic Therapy <br/> Low doze mainatance used as starting doze for fits in acute febrile ilness</p></html>";
                //Loading with 15mg/kg (assuming not	on	maintenance	phenobarb)	followed by 2.5mg – 5mg/kg daily, Page	13
                if(String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("days")){
                    if (agedd<=7){
                        drugDetails="Loading with "+20*weight2+" mg (assuming not on maintenance phenobarb) \n followed by"+ 2.5*weight2+"-"+ 5*weight2+" mg daily";

                    }else{
                        drugDetails="Loading with "+15*weight2+" mg (assuming not on maintenance phenobarb) \n followed by"+ 2.5*weight2+"-"+ 5*weight2+" mg daily";
                    }
                }else{
                    drugDetails="Loading with "+15*weight2+" mg (assuming not on maintenance phenobarb) \n followed by"+ 2.5*weight2+"-"+ 5*weight2+" mg daily";
                }
                drugDetails="Loading with "+15*weight2+" mg (assuming not on maintenance phenobarb) \n followed by"+ 2.5*weight2+"-"+ 5*weight2+" mg daily";
                results.setText(drugDetails);
                webview.loadData(description, "text/html", "UTF-8");
                break;
            case 40:
                //Phenytoin
                description="<html><p>Age 1m–12 yrs, 15-20 mg/kg at a rate not exceeding"+
                        "1 mg/kg/minute as a loading dose;<br/> maintenance dose of"+
                        "2.5–5 mg/kg twice daily (max. 150mg twice daily);<br/>"+
                        "Similar dosing	can	be	used in	neonates.</html>";

                if(String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("months")){
                    drugDetails="loading dose: "+15*weight2+"-"+20*weight2+" mg at a rate not exceeding 1 mg/kg/minute\n"
                            +"maintenance dose:"+2.5*weight2+"-"+5*weight2+"mg twice daily (max. 150mg twice daily)";
                }else if(String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("years")){
                    if (agedd>=1 && agedd<=12){
                        drugDetails="loading dose: "+15*weight2+"-"+20*weight2+" mg at a rate not exceeding 1 mg/kg/minute\n"
                                +"maintenance dose:"+2.5*weight2+"-"+5*weight2+"mg twice daily (max. 150mg twice daily)";

                    }else{
                        drugDetails="";
                    }


                }else{
                    drugDetails="loading dose: "+15*weight2+"-"+20*weight2+" mg at a rate not exceeding 1 mg/kg/minute\n"
                            +"maintenance dose:"+2.5*weight2+"-"+5*weight2+"mg twice daily (max. 150mg twice daily)";
                }

                webview.loadData(description, "text/html", "UTF-8");
                results.setText(drugDetails);
                break;
            case 41:
                //Potassium - oral 1 - 4 mmol/kg/day
                drugDetails=1*weight2+"-"+4*weight2+" mmol/day";
                results.setText(drugDetails);
                break;
            case 42:
                //Prednisolone -
                drugDetails="Asthma " +1*weight2+" mg daily";
                description="usually for 3-5 days, stopped when symptoms largely resolved";
                results.setText(drugDetails);
                webview.loadData(description, "text/html", "UTF-8");
                break;
            case 43:
                //Quinine
                //Toast.makeText(getApplicationContext(), "here",Toast.LENGTH_LONG).show();
                drugDetails=df.format(weight2*20) +" mg loading dose \n Then "+df.format(weight2*10)+ " iv/im 8 hrly maintainance \n Click here for preparation procedure";
                results.setText(drugDetails);
                results.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent i= new Intent (DrugDescriptionActivity.this,MaraliaTreatmentPreparation.class);
                        startActivity(i);
                    }
                });

                break;
            case 44:
                //Salbutamol
                String iv="";
                String nebulised="";
                String inhaled="";
                String oral="0";
                if(String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("days") ){
                    iv=((weight2*5)>=250?250:(weight2*5))+"Micrograms";

                }else if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("months")){
                    iv=((weight2*5)>=250?250:(weight2*5))+"Micrograms";
                    if (agedd>=2&&agedd<=11){
                        oral="1mg /dose 6-8hrly";
                    }

                }
                else if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("years")){
                    if (agedd<2){
                        iv=((weight2*5)>=250?250:(weight2*5))+"Micrograms";
                    }else{
                        iv=((weight2*15)>=250?250:(weight2*15))+"Micrograms";
                    }
                    if (agedd>=1&&agedd<=4)
                    {
                        oral="2mg /dose 6-8hrly";
                    }
                }
                nebulised="2.5mg/dose";
                inhaled="100mcg /puff (read instructions)";
                //set results
                results.setText("Nebulised: "+nebulised+"\nIV: "+iv+"\n Inhaled: "+inhaled+"\n Oral: "+ oral);



                //info----
                description="<html><p>NEBULISED: 2.5 mg/dose as required"+
                        "<br/>- INHALED (Not for use as long term therapy): (100 mcg per puff) 2 puffs via spacer repeated as required acutely or 2 puffs up to 4-6 hrly for acute wheeze for <5 days."+
                        "<br/> - ORAL (No longer recommended unless on inhaled therapy): Age 2-11months - 1mg/dose 6-8hrly, Age 1-4yrs - 2mg/dose 6-8hrly. (1 week ONLY - Not suitable for maintenance therapy)"+
                        "<br/>- IV in hospital ONLY over 5mins: Age <2yrs - 5mcg/kg. Age >=2yrs - up to 15mcg/kg. MAX. dose 250mcg."+
                        "<br/>- NOTE: IV therapy should only be used on an HDU, ideally with a monitor and MUST be given slowlly as directed. Use inhaled steroids for persistent asthma.</p></html>";

                webview.loadData(description, "text/html", "UTF-8");

                break;
            case 45:
                //TB Treatment->See	page	35
                //add a clickable link to image
                double result1;
                drugDetails="Isoniazid(H) "+(result1=(weight2*10)>=300?300:(weight2*10))+"-"+(result1=(weight2*15)>=300?300:(weight2*15)) +"mg" +
                        "\n Rifampicin(R) "+(result1=(weight2*10)>=600?600:(weight2*10))+"-"+(result1=(weight2*20)>=600?600:(weight2*20))+"mg" +
                        "\n Pyrazinomide(Z) "+(result1=(weight2*30)>=1500?1500:(weight2*30))+"-"+(result1=(weight2*40)>=1500?1500:(weight2*40))+"mg" +
                        "\n Ethambutol(E) "+(result1=(weight2*15)>=1600?1600:(weight2*15))+"-"+(result1=(weight2*25)>=1600?1600:(weight2*25)) +"mg" ;





                results.setText(drugDetails+" click here to view Regiment Dosing");
                results.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent i= new Intent (DrugDescriptionActivity.this,Tuberculosis.class);
                        startActivity(i);
                    }
                });

                break;
            case 46:
                //Valproate sodium)
                description="Age 1m to 12yrs: initially 5-7.5mg/kg twice daily (max. 600 mg in one day);\n usual maintenance dose 12.5 to 15mg/kg twice daily - must monitor clinical chemistry and"+
                        "haematological parameters if dose exceeds 40 mg/kg daily";
                if(String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("months")){
                    drugDetails="loading dose: "+5*weight2+"-"+7.5*weight2+" mg twice daily(max. 600 mg in one day)\n"
                            +"maintenance dose:"+12.5*weight2+"-"+15*weight2+"mg twice daily";
                    //description="You must monitor clinical chemistry and haematological	parameters if dose exceeds 40 mg/kg daily";
                }else if(String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("years")){
                    if (agedd>=1 && agedd<=12){
                        drugDetails="loading dose: "+5*weight2+"-"+7.5*weight2+" mg twice daily(max. 600 mg in one day)\n"
                                +"maintenance dose:"+12.5*weight2+"-"+15*weight2+"mg twice daily";
                        //description="You must monitor clinical chemistry and haematological	parameters if dose exceeds 40 mg/kg daily";
                    }else{
                        drugDetails="";
                        //description="";
                    }


                }else{
                    drugDetails="";
                    //description="";
                }

                webview.loadData(description, "text/html", "UTF-8");
                results.setText(drugDetails);
                break;
            case 47:
                //Vitamin A
                if(String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("months")){
                    if (agedd<6){
                        drugDetails="50,000 u stat";

                        description="Once on admission, not to be repeated within 1 month. For malnutrition with eye disease repeat on day 2 and day 14";
                    }else if (agedd>=6&&agedd<=12){
                        drugDetails="100,000 u stat";

                        description="Once on admission, not to be repeated within 1 month. For malnutrition with eye disease repeat on day 2 and day 14";
                    }else if (agedd>12){
                        drugDetails="200,000 u stat";

                        description="Once on admission, not to be repeated within 1 month. For malnutrition with eye disease repeat on day 2 and day 14";
                    }
                    else{
                        drugDetails="";
                        description="";
                    }
                }else if(String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("years")){
                    if(agedd>1){
                        drugDetails="200,000 u stat";

                        description="Once on admission, not to be repeated within 1 month. For malnutrition with eye disease repeat on day 2 and day 14";
                    }else{
                        drugDetails="100,000 u stat";

                        description="Once on admission, not to be repeated within 1 month. For malnutrition with eye disease repeat on day 2 and day 14";
                    }
                }else if(String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("days")) {
                    drugDetails="50,000 u stat";

                    description="Once on admission, not to be repeated within 1 month. For malnutrition with eye disease repeat on day 2 and day 14";
                }

                webview.loadData(description, "text/html", "UTF-8");
                results.setText(drugDetails);

                break;
            case 48:
                //Vitamin D
                if(String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("days")){
                    drugDetails="Dosage: 3,000 u = 75 micrograms";

                }else if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("months")){
                    if (agedd<6){
                        drugDetails="Dosage: 3,000 u = 75 micrograms";
                        description="Chole or ergocalciferol: Rickets. Low dose	regimens daily for 8 – 12 wks or one high dose.	± Calcium for first	week of	treatment.";
                    }else if (agedd>=6){
                        drugDetails="Dosage: 6,000 u = 150 micrograms \n 6m stat im regimen: 300,000 u = 7.5 mg Stat";
                        description="Chole or ergocalciferol: Rickets. Low dose	regimens daily for 8 – 12 wks or one high dose.	± Calcium for first	week of	treatment.";
                    }

                }else if(String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("years")){
                    description="Chole or ergocalciferol: Rickets. Low dose	regimens daily for 8-12 wks or one high dose.Calcium for first	week of	treatment.";
                    drugDetails="Dosage: 6,000 u = 150 micrograms \n 6m stat im regimen: 300,000 u = 7.5 mg Stat";
                }else{
                    drugDetails="";
                    description="";
                }
                webview.loadData(description, "text/html", "UTF-8");
                results.setText(drugDetails);
                break;
            case 49:
                //Vitamin D – Maintenance After	treatment	course
                description="After	treatment	course\n"+
                        "Chole or ergocalciferol: Rickets. Low dose	regimens daily for 8 – 12 wks or one high dose.	± Calcium for first	week of	treatment.";

                if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("days")){
                    drugDetails="200 - 400 u (5 – 10 μg)";

                }else if(String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("months")){
                    if (agedd<6){
                        drugDetails="200 - 400 u (5 – 10 μg)";
                    }else if (agedd>=6 && agedd<=12){
                        drugDetails="400 - 800 u (10 – 20 μg)";
                    }
                }else if(String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("years")){
                    if (agedd>1){
                        drugDetails="400 - 800 u (10 – 20 μg)";
                    }else {
                        drugDetails="200 - 400 u (5 – 10 μg)";
                    }
                }

                webview.loadData(description, "text/html", "UTF-8");
                results.setText(drugDetails);
                break;
            case 50:
                //Vitamin K
                description="Newborns: 1mg stat im (<1500g, 0.5mg im stat)";
                drugDetails="For liver disease: "+df.format(0.3*weight2)+" mg stat, max 10mg";
                webview.loadData(description, "text/html", "UTF-8");
                results.setText(drugDetails);
                break;
            case 51:
                //zinc Sulphate
                if(String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("days")){
                    drugDetails="10mg od, 14 days";

                }else if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("months")){
                    if (agedd<=6){
                        drugDetails="10mg od, 14 days";
                    }else if (agedd>6){
                        drugDetails="20mg";

                    }

                }else if(String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("years")){
                    drugDetails="20mg";
                }else{
                    drugDetails="";
                    description="";
                }
                results.setText(drugDetails);
                break;

        }
        //end of the method
    }

    private String getExactAge(int age2) {
        String message=null;

        if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("days")){
            message=age2+" days";
        }else if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("months")){
            message=age2+" months";
        }else if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("years")){
            message=age2+" years";
        }
        return message;

    }

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.drug_description, menu);
//		return true;
//	}
}
