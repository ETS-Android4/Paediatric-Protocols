package com.mwongera.paediatric_protocols;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.text.DecimalFormat;

/**
 * Created by mwongera on 2/21/17.
 */

public class  DrugDescriptionActivity extends Activity {

    DecimalFormat df = new DecimalFormat("###.#");
    EditText inputWeight, inputAge;
    TextView results, txtDrug;
    WebView webview, webview1;
    Spinner aged;
    int age;
    int positioned = 0;
    Double weight;
    String aging, weighing;
    String drugtype;
    String position;
    String drugDetails = null;
    String description = null;
    String[] lan;
    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_description);

        // getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#f9a614")));
        inputAge = (EditText) findViewById(R.id.editText2);
        inputWeight = (EditText) findViewById(R.id.editText1);
        results = (TextView) findViewById(R.id.textResults);
        txtDrug = (TextView) findViewById(R.id.textDrug);
        webview = (WebView) findViewById(R.id.webView1);
        webview1 = (WebView) findViewById(R.id.webView);
        aged = (Spinner) findViewById(R.id.spinner1);
        btn1 = (Button) findViewById(R.id.button1);
        //btn2=(Button)findViewById(R.id.button2);

        //drug type description
        Intent i = getIntent();
        drugtype = i.getStringExtra("drugtype");
        position = i.getStringExtra("position");
        txtDrug.setText(drugtype);
        positioned = Integer.parseInt(position);
        //Toast.makeText(getApplicationContext(),position,Toast.LENGTH_SHORT).show();
        //on btn1 click event
        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //aging=getExactAge(age);
                aging = inputAge.getText().toString();
                weighing = inputWeight.getText().toString();

                //method to calculate dosage once a button is clicked
                if (weighing.length() != 0 && aging.length() != 0) {
                    age = Integer.parseInt(aging);
                    weight = Double.parseDouble(weighing);
                    results.setText("");

                    //add a keyboard remover function
                    calculateDosage(weight, age, positioned);
                } else {
                    Toast.makeText(getApplicationContext(), "Enter details correctly", Toast.LENGTH_SHORT).show();
                }


            }
        });

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-3940256099942544~3347511713");

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }

    //come up with an algorithm for each drug.
    private void calculateDosage(Double weight2, int agedd, int pos) {


        //DO a switch case for all the drugs.
        switch (pos) {
            //Adrenaline   Give 0.1ml/kg in resuscitation.
            case 0:
                drugDetails = ("" + df.format(0.1 * weight2) + " ml in resuscitation ");
                drugDetails = ("" + df.format(0.5 * weight2) + " ml of 1:1000 solution (max 5ml) for severe viral group");

                results.setText(drugDetails);
                webview.loadData(description, "text/html", "UTF-8");
                // get our html content
                String htmlAsString0 = getString(R.string.Adrenaline);
                webview1.loadDataWithBaseURL(null, htmlAsString0, "text/html", "utf-8", null);


                break;
            case 1:
                //Albendazole Age < 2yrs, 200mg stat
                //Age ≥ 2yrs, 400mg stat
                if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("days") || (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("months"))) {
                    drugDetails = "200mg stat";

                } else if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("years")) {
                    if (agedd < 2) {
                        drugDetails = "200mg stat";
                    } else {

                        drugDetails = "400mg stat";
                    }

                }
                // add values to the txtviews
                results.setText(drugDetails);
                // get our html content
                String htmlAsString1 = getString(R.string.Albendazole);
                webview1.loadDataWithBaseURL(null, htmlAsString1, "text/html", "utf-8", null);
                break;
            case 2:
                //Amikacin Age 1mo to 18 yrs, 15mg/kg once daily; same dosing can be used in newborns.
                if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("years") || (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("months"))) {
                    if (agedd >= 1 && agedd <= 12) {
                        drugDetails = (15 * weight2 + " mg once daily");
                        description = "Same dosing can be used in newborns. Slow iv over 3-5 min\n" +
                                "Amikacin trough concentration should be monitored ( if available)\n" +
                                "If serious gram - ve infection / resistance to gentamicin, higher doses may be used with monitoring";
                        results.setText(drugDetails);
                    } else if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("days")) {
                        drugDetails = "";
                        results.setText(drugDetails);
                    }

                }

                webview.loadData(description, "text/html", "UTF-8");
                // get our html content
                String htmlAsString2 = getString(R.string.Amikacin);
                webview1.loadDataWithBaseURL(null, htmlAsString2, "text/html", "utf-8", null);
                break;
            case 3:
                //Aminophylline
                //(iv)
                //Newborn Loading dose 6mg/kg  iv over 1 hour or rectal,
                //Maintenance (iv or oral): Age	0-7	days - 2.5mg/kg ///////////////////////////depends more on age
                if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("days")) {
                    if (agedd >= 1 && agedd <= 28) {
                        drugDetails = "Newborn Loading dose " + 6 * weight2 + " mg  iv over 1 hour or rectal" +
                                "\n Maintenance (iv or oral):Age 0-6 days- " + 2.5 * weight2 + " mg 12hrly " +
                                "\n Maintenance (iv or oral):Age above 6 days- " + 4 * weight2 + " mg 12hrly " +
                                "\n Asthma:" + 5 * weight2 + "mg iv first dose over 30 mins max 500mg";


                    } else if (agedd >= 7 && agedd <= 28) {
                        drugDetails = "Newborn Loading dose " + 6 * weight2 + " mg  iv over 1 hour or rectal" +
                                "\n Maintenance (iv or oral):Age 7-28 days- " + 4 * weight2 + " mg 12hrly " +
                                "\n Asthma:" + 6 * weight2 + "mg iv first dose over 30 mins";


                    }

                } else {
                    drugDetails = "Newborn Loading dose " + 6 * weight2 + " mg  iv over 1 hour or rectal" +
                            "\n Maintenance (iv or oral):Age 7-28 days- " + 4 * weight2 + " mg 12hrly " +
                            "\n Asthma:" + 6 * weight2 + "mg iv first dose over 30 mins";

                }
                description = "<html><p>Newborn Loading dose 6mg/kg  iv over 1 hour or rectal,<br/>" +
                        "Maintenance (iv or oral): Age	0-7	days - 2.5mg/kg " +
                        "12hrly, Age 7-28 days-	4mg/kg 12hrly.<br/>" +
                        "Asthma: 6mg/kg iv first dose over 30 mins max 500mg</p></html>";


                results.setText(drugDetails);
                ///calculate the maintainance doses
                //description=" Age7-28 days "+4*weight2+" mg 12hrly<br/> ";
                webview.loadData(description, "text/html", "UTF-8");
                // get our html content
                String htmlAsString3 = getString(R.string.Aminophylline);
                webview1.loadDataWithBaseURL(null, htmlAsString3, "text/html", "utf-8", null);
                break;

            case 4:
                //Use 25mg/kg/dose for simple infections and 40-45mg/kg
                //for pneumonia
                drugDetails = (25 * weight2) + "mg dose for simple infections\n " + (40 * weight2) + "-" + (45 * weight2) + " mg for pnemonia";
                results.setText(drugDetails);

                // get our html content
                String htmlAsString4 = getString(R.string.Amoxicillin);
                webview1.loadDataWithBaseURL(null, htmlAsString4, "text/html", "utf-8", null);
                break;
            case 5:
                //Neonate: 50mg/kg/dose 12 hourly iv or  if aged < 7 days and 8 hourly if aged 8 - 28 days.
                //Age 1m and over: 50mg/kg/dose (Max 500mg) 8 hourly

                if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("days")) {
                    if (agedd <= 7) {
                        drugDetails = (50 * weight2) + " mg dose 12 hourly or im";
                    } else if (agedd > 7 && agedd <= 28) {
                        drugDetails = (50 * weight2) + " mg dose 8 hourly or im";
                    }

                } else if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("months") || String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("years")) {

                    drugDetails = (50 * weight2) + " mg (max 500mg) dose 6 hourly iv / im";
                }
                results.setText(drugDetails);
                // get our html content
                String htmlAsString5 = getString(R.string.Ampicillin);
                webview1.loadDataWithBaseURL(null, htmlAsString5, "text/html", "utf-8", null);
                break;

            case 6:
                //Artemether -
                //Lumefantrine

                description = "Given with Food \n Stat then at 8hrs then BD on day 2 and 3";

                if (weight < 5) {
                    drugDetails = "1/2 tablet";

                } else {
                    // check the ages
                    if ((String.valueOf(aged.getSelectedItem())
                            .equalsIgnoreCase("months"))) {
                        // months
                        if (agedd >= 3 && agedd < 35) {
                            drugDetails = "1 tablet";

                        } else if (agedd >= 35 && agedd < 60) {
                            drugDetails = "2 tablets";

                        } else {
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
                        } else {
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
                description = "Given OD for 3 days";
                // check the ages
                if ((String.valueOf(aged.getSelectedItem())
                        .equalsIgnoreCase("months"))) {
                    // months
                    if (agedd >= 3 && agedd <= 35) {
                        drugDetails = "1 paed tab";

                    } else if (agedd > 35 && agedd < 60) {
                        drugDetails = "2 paed tabs";

                    } else {
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
                    } else {
                        drugDetails = "Error, kindly check the inputs";
                    }
                } else {
                    drugDetails = "Error in the Inputs";
                }

                results.setText(drugDetails);
                webview.loadData(description, "text/html", "UTF-8");
                break;
            case 8:
                //Artesunate Age 1m and over: 2.4mg/kg given iv/im at 0, 12 and 24
                //hours then daily –
                if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("months") || String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("years")) {
                    if (weight2 <= 20) {
                        drugDetails = df.format(3 * weight2) + " mg At 0,12, and 24h then continue daily until oral administration is feasible\n press here for preparation procedure";
                    } else {
                        drugDetails = df.format(2.4 * weight2) + " mg At 0,12, and 24h then continue daily until oral administration is feasible\n press here for preparation procedure";
                    }
                    //description="change to full course oral ACT as soon as possible after 3 doses when infant/child drinking/breast feeding.";
                    description = "Given iv / im for a minimum of 24 hours\n" +
                            "As soon as the child can eat drink (after 24 hours for artesunate) then change to a full course of artemisinin combination therapy (ACT) typically the 1st line oral anti-malarial, Artemether Lumefantrine.";
                    results.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            Intent i = new Intent(DrugDescriptionActivity.this, MalariaTreatmentPreparation.class);
                            startActivity(i);
                        }
                    });


                } else {
                    drugDetails = "Error, make sure the child is above 1 Month";
                }
                results.setText(drugDetails);
                webview.loadData(description, "text/html", "UTF-8");

                // get our html content
                String htmlAsString6 = getString(R.string.Artesunate);
                webview1.loadDataWithBaseURL(null, htmlAsString6, "text/html", "utf-8", null);
                break;
            case 9:
                //Beclomethasone Age < 2yrs	50-100	micrograms	12hrly,
                //Age ≥ 2yrs 100-200 micrograms	12hrly
                if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("months") || String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("days")) {
                    drugDetails = "50-100	micrograms	12hrly";
                } else if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("years")) {
                    if (agedd < 2) {
                        drugDetails = "50-100	micrograms	12hrly";
                    } else if (agedd >= 2) {
                        drugDetails = "100-200 micrograms	12hrly";
                    }
                }
                description = "Can double doses to improve control but check technique and follow up carefully";
                results.setText(drugDetails);
                webview.loadData(description, "text/html", "UTF-8");

                // get our html content
                String htmlAsString7 = getString(R.string.Beclomethasone);
                webview1.loadDataWithBaseURL(null, htmlAsString7, "text/html", "utf-8", null);
                break;
            case 10:
                description = "Not recommended if jaundiced or age less than	7 days <br/> Double Penicillin doses if treating Meningitis and age greater than 1	month";
                //Benzyl Penicillin (X-pen)Newborn:  50,000 iu/kg/dose 12 hourly iv or im.
                if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("days")) {
                    if (agedd <= 7) {
                        drugDetails = (50000 * weight2) + " iu/dose 12 hourly iv or im";
                    } else {
                        drugDetails = (50000 * weight2) + " iu/dose 6 hourly iv / im";
                    }
                } else {
                    drugDetails = "Double penicillin doses when treating meningitis and age is more than a month";
                }
                results.setText(drugDetails);
                webview.loadData(description, "text/html", "UTF-8");

                // get our html content
                String htmlAsString8 = getString(R.string.Benzyl);
                webview1.loadDataWithBaseURL(null, htmlAsString8, "text/html", "utf-8", null);
                break;
            case 11:
                //Newborns and up to 4 yrs: 0.25mmol/kg 6 hrly.
                if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("years")) {
                    if (agedd > 4) {
                        drugDetails = "Allowed only up to 4 years";
                    } else {
                        drugDetails = (0.25 * weight2) + " mmol 6 hourly";
                    }
                } else
                    drugDetails = (0.25 * weight2) + " mmol 6 hourly";
                description = "<html><p>Calcium Gluconate 1g tabs contain 2.23mmol calcium"
                        + "<br/>Calcium Lactate 300mg tabs contain 1mmol calcium"
                        + "<br/>Maybe required together with Vitamin	D for treating rickets in first 7 days but monitor calcium to prevent hypercalcaemia</p></html>";
                results.setText(drugDetails);
                webview.loadData(description, "text/html", "UTF-8");
                break;
            case 12:
                //Carbamazepine Age 1 m –12 yrs: initially 5 mg/kg at night, increased
                if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("days")) {
                    if (agedd >= 28) {
                        drugDetails = (5 * weight2) + " mg  at night, increased as necessary by " + (2.5 * weight2) + "-" + 5 * weight2 + " mg every 3–7 days "
                                + "\n usual maintenance dose " + 5 * weight2 + " mg 2–3 times daily.";
                    } else {
                        drugDetails = "";
                    }
                } else {
                    drugDetails = "Not recommended for neonates";
                }

                results.setText(drugDetails);
                webview.loadData(description, "text/html", "UTF-8");

                // get our html content
                String htmlAsString9 = getString(R.string.Carbamazepine);
                webview1.loadDataWithBaseURL(null, htmlAsString9, "text/html", "utf-8", null);
                break;
            case 13:
                //Cefotaxime Preferred	to	Ceftriaxone	for	treatment	of	neonatal	meningitis	if	aged	<7	days:
                if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("days")) {
                    if (agedd <= 7) {
                        drugDetails = "Pre-term: " + (50 * weight2) + " mg 12 hourly \nTerm aged <7 days: " + 50 * weight2 + "mg 8 hourly";
                        results.setText(drugDetails);

                    } else {
                        drugDetails = "Drug for only upto 7 days";
                    }
                } else {
                    drugDetails = "Provided dosages are for neonates aged 7 days or less";
                }
                description = "Preferred to Ceftriaxone for treatment	of neonatal	meningitis if aged <7 days:";
                results.setText(drugDetails);
                webview.loadData(description, "text/html", "UTF-8");

                // get our html content
                String htmlAsString10 = getString(R.string.Cefotaxime);
                webview1.loadDataWithBaseURL(null, htmlAsString10, "text/html", "utf-8", null);
                break;
            case 14:
                //Ceftriaxone
                //
                if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("days")) {
                    description = "<html><p><ul><li>Ceftriaxone iv/im maximum 50mg/kg 24hrly for neonates but not recommended if jaundiced or &lt 7 days .</li><br/><li>If Meningitis or very severe sepsis, 50mg/kg 12hrly</li></ul></p></html>  ";
                    if (agedd <= 7) {
                        drugDetails = 50 * weight2 + " iv/im 24hrly ";

                    } else if (agedd > 7 && weight2 < 80) {
                        drugDetails = (50 * weight2) + " iv/im 24hrly " +
                                "\n for meningitis and very severe sepsis: " + (50 * weight2) + "iv/im 12 hourly";
                    } else {
                        drugDetails = "max 4gms iv/im 24 hourly";
                    }
                }
                results.setText(drugDetails);
                webview.loadData(description, "text/html", "UTF-8");

                // get our html content
                String htmlAsString11 = getString(R.string.Ceftazidime);
                webview1.loadDataWithBaseURL(null, htmlAsString11, "text/html", "utf-8", null);

                break;
            case 15:
                //Chloramphenicol
                if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("days")) {
                    description = "<html><p>Chloramphenicol should not be used in babies aged &lt 7 days<br/> 25mg/kg iv/im 6hrly for meningitis in over 7 days old</p></html>  ";
                    if (agedd <= 7) {
                        drugDetails = "Are you sure you want to issue Chloramphenicol to a neonate";

                    } else if (agedd > 7) {
                        drugDetails = (25 * weight2) + " iv/im 6hrly - Meningitis " +
                                "\n oral : " + (25 * weight2) + "mg 6 hourly";
                    } else {
                        drugDetails = "Are you sure you want to issue Chloramphenicol to a neonate";

                    }
                }
                results.setText(drugDetails);
                webview.loadData(description, "text/html", "UTF-8");

                // get our html content
                String htmlAsString12 = getString(R.string.Chloramphenicol);
                webview1.loadDataWithBaseURL(null, htmlAsString12, "text/html", "utf-8", null);
                break;
            case 16:
                //Ciprofloxacin
                if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("days")) {
                    description = "<html><p>15mg/kg per dose 12hrly for 3 days</p></html>  ";
                    if (agedd <= 7) {
                        drugDetails = "Not recommended for neonates ";

                    } else if (agedd > 28) {
                        drugDetails = "Dysentery dosing" + 15 * weight2 + " mg/dose 12hrly for 3 days ";
                    }

                }
                results.setText(drugDetails);
                webview.loadData(description, "text/html", "UTF-8");

                // get our html content
                String htmlAsString13 = getString(R.string.Ciprofloxacin);
                webview1.loadDataWithBaseURL(null, htmlAsString13, "text/html", "utf-8", null);

                break;
            case 17:
                //Clotrimazole 1%
                drugDetails = "Apply 2-3 times daily until cleared";
                results.setText(drugDetails);
                //webview.loadData(description, "text/html", "UTF-8");

                // get our html content
                String htmlAsString14 = getString(R.string.Clotrimazole);
                webview1.loadDataWithBaseURL(null, htmlAsString14, "text/html", "utf-8", null);
                break;
            case 18:
                //Co-trimoxazole*
                if (weight2 >= 2 && weight2 <= 3) {
                    drugDetails = "Pneumonia dosing" + (4 * weight2) + " mg Trimethropim" +
                            "\n " + (20 * weight2) + "Sulphamethoezole" +
                            "\n as 2.5mls 12hrly of 240mg/5ml syrup " +
                            "\n 1/4 of 480mg tabs 12hrly";
                } else if (weight2 > 3 && weight2 <= 10) {
                    drugDetails = "Pneumonia dosing" + (4 * weight2) + " mg Trimethropim" +
                            "\n " + (20 * weight2) + "Sulphamethoezole as 5mls" +
                            "\n of 240mg/5ml 12hrly of 240mg/5ml syrup or" +
                            "\n 1/2 of 480mg tabs 12hrly";
                } else if (weight2 > 10 && weight2 <= 15) {
                    drugDetails = "Pneumonia dosing" + (4 * weight2) + " mg Trimethropim" +
                            "\n " + (20 * weight2) + "Sulphamethoezole as 7.5mls" +
                            "\n of 240mg/5ml 12hrly of 240mg/5ml syrup or" +
                            "\n 1/2 of 480mg tabs 12hrly";
                } else if (weight2 > 15 && weight2 <= 20) {
                    drugDetails = "Pneumonia dosing" + (4 * weight2) + " mg Trimethropim" +
                            "\n " + (20 * weight2) + "Sulphamethoezole as 10mls" +
                            "\n of 240mg/5ml 12hrly of 240mg/5ml syrup or" +
                            "\n 1 of 480mg tabs 12hrly";
                } else {
                    drugDetails = "Pneumonia dosing" + (4 * weight2) + " mg Trimethropim" +
                            "\n " + (20 * weight2) + "Sulphamethoezole";
                }
                results.setText(drugDetails);
                // get our html content
                String htmlAsString15 = getString(R.string.Cotrimoxazole);
                webview1.loadDataWithBaseURL(null, htmlAsString15, "text/html", "utf-8", null);
                break;
            case 19:
                //Dexamethasone
                drugDetails = df.format(0.6 * weight2) + " mg stat for severe	croup";
                results.setText(drugDetails);

                // get our html content
                String htmlAsString16 = getString(R.string.Dexamethasone);
                webview.loadDataWithBaseURL(null, htmlAsString16, "text/html", "utf-8", null);
                break;
            case 20:
                //Dextrose/glucose
                if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("days")) {
                    if (agedd < 28) {
                        drugDetails = 2 * weight2 + "mls 10% dextrose iv over 5-10 mins ";

                    } else {
                        drugDetails = (5 * weight2) + " mls 10% dextrose iv over 5-10 mins ";
                    }

                }
                results.setText(drugDetails);
                //webview.loadData(description, "text/html", "UTF-8");

                // get our html content
                String htmlAsString17 = getString(R.string.Dextrose);
                webview1.loadDataWithBaseURL(null, htmlAsString17, "text/html", "utf-8", null);


                description = "Note> Newborn: 2 mls/kg";
                results.setText(drugDetails);
                webview.loadData(description, "text/html", "UTF-8");
                break;
            case 21:
                //Dihydrocodeine
                description = "<html><p>Age 1–4 yrs: 0.5mg / kg every 4–6 hours <br/>"
                        + "Age 4–12 yrs: 0.5–1 mg/kg (max. 30 mg) every 4–6 hrs</p></html>";
                if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("years")) {
                    if (agedd >= 1 && agedd <= 2) {
                        drugDetails = 0.5 * weight2 + "mg every 4–6 hours";
                    } else if (agedd > 4 && weight2 < 60) {
                        drugDetails = 0.5 * weight2 + " - " + 30 * weight2 + "mg every 4–6 hours(max. 30 mg)";
                    } else if (agedd > 4 && weight2 > 60) {
                        drugDetails = 30 * weight2 + "mg every 4–6 hours(max. 30 mg)";
                    } else {
                        drugDetails = "Dihydrocodeine is not recommended for infaants";
                    }

                }
                webview.loadData(description, "text/html", "UTF-8");
                results.setText(drugDetails);

                // get our html content
                String htmlAsString18 = getString(R.string.Dihydrocodeine);
                webview1.loadDataWithBaseURL(null, htmlAsString18, "text/html", "utf-8", null);
                break;
            case 22:
                //Diazepam (iv)
                if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("days")) {
                    if (agedd > 28) {
                        drugDetails = 0.3 * weight2 + "mg =" + df.format((0.3 * weight2 * 8) / 5) + " mls of 10mg/2ml solution)";
                    } else {
                        drugDetails = "Not used in neonates ";
                    }
                } else {
                    drugDetails = "null ";

                }

                description = "<html><p>0.3 mg/kg iv <br/> Not used in neonates</p></html>";
                //drugDetails=0.3*weight2+ "mg (="+300*weight2+" mcg)";
                webview.loadData(description, "text/html", "UTF-8");
                results.setText(drugDetails);

                // get our html content
                String htmlAsString19 = getString(R.string.DiazepamV);
                webview1.loadDataWithBaseURL(null, htmlAsString19, "text/html", "utf-8", null);
                break;
            case 23:
                description = "<html><p>The whole syringe  barrel of a 1ml or 2ml syringe should be inserted gently so	" +
                        "that pr DZ is given at a depth of 4-5cm<br/></p><p>0.3 mg/kg iv <br/> Not used in neonates</p></html>";
                //Diazepam (rectal)m;
                if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("days")) {
                    if (agedd > 28) {
                        drugDetails = 0.3 * weight2 + "mg =" + df.format((0.3 * weight2 * 8) / 5) + " mls of 10mg/2ml solution)";
                    } else {
                        drugDetails = "Not used in neonates ";
                    }
                } else {
                    drugDetails = "null ";

                }
                results.setText(drugDetails);
                webview.loadData(description, "text/html", "UTF-8");
                // get our html content
                String htmlAsString20 = getString(R.string.DiazepamR);
                webview1.loadDataWithBaseURL(null, htmlAsString20, "text/html", "utf-8", null);
                break;
            case 24:
                //Digoxin (oral)
                if (String.valueOf(aged.getSelectedItem())
                        .equalsIgnoreCase("years")) {
                    if (agedd >= 2 && agedd < 5) {
                        drugDetails = "initialy " + (35 * weight2)
                                + " micrograms in 3 divided doses for 24 hrs then \n "
                                + 10 * weight2 + " micrograms daily in 1–2 doses";
                    } else if (agedd >= 5 && agedd < 10) {
                        drugDetails = "initialy " + 25
                                * weight2
                                + " micrograms (max.750 micrograms) in 3 divided doses for 24 hrs then \n "
                                + 6 * weight2
                                + " micrograms (max. 250 micrograms daily)daily in 1–2 doses";
                    } else if (agedd > 10) {
                        drugDetails = "initialy " + 0.75 * weight2
                                + "-"
                                + 1.5 * weight2
                                + " micrograms in 3 divided doses for 24 hrs then \n "
                                + 62.5
                                * weight2
                                + "-"
                                + 250
                                * weight2
                                + " micrograms (max 250mcg) daily in 1–2 doses";
                    }

                } else {
                    drugDetails = "Consult a paeditrician for use under 2 years";
                }
                //add info
                description = getResources().getString(R.string.d24);
                results.setText(drugDetails);
                webview.loadData(description, "text/html", "UTF-8");

                // get our html content
                String htmlAsString21 = getString(R.string.Digoxin);
                webview1.loadDataWithBaseURL(null, htmlAsString21, "text/html", "utf-8", null);

                break;
            case 25:
                //Flucloxacillin
                if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("days")) {
                    description = "<html><p>Neonates 25mg/kg  12hrly given as a 125mg/5ml suspension<br/>Over 1 month 50mg/kg per dose 8hrly </p></html>  ";
                    if (agedd > 28) {
                        drugDetails = "Oral: " + (weight2 * 15) + "mg/dose or as" + (weight2 * 25 * 5) / 125 + " mls/ suspension 12 hrly \n"
                                + "iv/im: " + (weight2 * 50) + "mg 8 hourly"
                        ;

                    } else {
                        drugDetails = "Consult a paeditrician for neonatal doses";
                    }
                } else {
                    drugDetails = "null";
                }
                results.setText(drugDetails);
                webview.loadData(description, "text/html", "UTF-8");

                // get our html content
                String htmlAsString22 = getString(R.string.Flucloxacillin);
                webview1.loadDataWithBaseURL(null, htmlAsString22, "text/html", "utf-8", null);

                break;
            case 26:
                //Frusemide
                drugDetails = 0.5 * weight2 + " mg to" + 1 * weight2 + " mg up to 6 hrly";
                results.setText(drugDetails);
                break;
            case 27:
                //Gentamicin
                if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("days")) {
                    description = "<html><p>Neonates &lt 7 days and &lt 2kg - 3mg/kg  24hrly <br/>Neonates &lt 7 days and &gt or = 2kg - 5mg/kg  24hrly <br/> Over 7 days -7.5mg/kg iv/im over 3-5 minutes 24hrly </p></html>  ";
                    if (agedd <= 7) {
                        if (weight2 < 2) {
                            drugDetails = 3 * weight2 + " mg iv/im 24hrly";
                        } else if (weight2 >= 2) {
                            drugDetails = 5 * weight2 + " mg iv/im 24hrly";
                        }

                    } else if (agedd > 7) {
                        drugDetails = 7.5 * weight2 + " mg iv/im over 3-5 minutes 24hrly ";
                    }
                } else {
                    drugDetails = "null";
                }
                results.setText(drugDetails);
                webview.loadData(description, "text/html", "UTF-8");

                // get our html content
                String htmlAsString23 = getString(R.string.Gentamicin);
                webview1.loadDataWithBaseURL(null, htmlAsString23, "text/html", "utf-8", null);
                break;
            case 28:

                //(For severe SCD only: Pain >3 episodes/ yr; stroke; transfusion ≥ 2/ yr; acute chest syndrome)
                //Child 2-12 years initially 10-15mg/kg once daily, increased every 12 weeks in steps of 2.5 - 5 mg/kg daily according to response; usual dose 15 - 30 mg/kg daily (max. 35 mg/kg)
                //Hydroxyurea
                drugDetails = "Child above 2 years initially" + (10 * weight2) + "-" + 15 * weight2 + "once daily" + " \n " +
                        "increased every 12 weeks in steps of" + (2.5 * weight2) + "-" + (5 * weight2) + "daily according to response; \n" +
                        "usual dose" + (15 * weight2) + "-" + (30 * weight2) + "daily (max. 35 mg/kg)" + "\n" +
                        "Consult a paeditrician for children under 2 years";
                description = "<html></p>(for severe SCD only: Pain >3 episodes/yr; stroke;	transfusion	&gt;2/yr; acute chest syndrome)<br/>Stop treatment and consult specialist if neutrophils reduced</p></html>";
                results.setText(drugDetails);
                webview.loadData(description, "text/html", "UTF-8");

                // get our html content
                String htmlAsString24 = getString(R.string.Hydroxyurea);
                webview1.loadDataWithBaseURL(null, htmlAsString24, "text/html", "utf-8", null);
                break;
            case 29:
                //Ibuprofen
                drugDetails = 5 * weight2 + "-" + 10 * weight2 + " mg 8 hourly";
                results.setText(drugDetails);
                // get our html content
                String htmlAsString25 = getString(R.string.Ibuprofen);
                webview1.loadDataWithBaseURL(null, htmlAsString25, "text/html", "utf-8", null);
                break;
            case 30:
                //Iron tabs / syrup *
                //2 - 4 mg elemental Fe/kg/24 hr max dose: 15 mg elemental Fe/day
                if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("years")) {
                    if (agedd < 1) {
                        if (weight2 < 3.75) {
                            drugDetails = "Iron deficiency, Anemia in a pre-term infant: " + (15 * weight2) + "mg elemental iron 24hrly(max 15mg elemental fe/days)" + " \n " +
                                    "Prophylaxis for a preterm-infant" + (15 * weight2) + "mg elemental iron 24hrly(max 15mg elemental fe/days) \n" +
                                    "Prophylaxis for a term-infant" + (1 * weight2) + "-" + (2 * weight2) + "Prophylaxis for a preterm-infant ";
                        } else if (weight2 >= 3.75 && weight2 < 7.5) {
                            drugDetails = "Iron deficiency, Anemia in a pre-term infant: " + (2 * weight2) + "-" + 15 * weight2 + "mg elemental iron 24hrly(max 15mg elemental fe/days)" + " \n " +
                                    "Prophylaxis for a preterm-infant" + (2 * weight2) + "-" + (15 * weight2) + "mg elemental iron 24hrly(max 15mg elemental fe/days) \n" +
                                    "Prophylaxis for a term-infant" + (1 * weight2) + "-" + (2 * weight2) + "Prophylaxis for a preterm-infant ";
                        } else if (weight2 >= 7.5 && weight2 < 15) {
                            drugDetails = "Iron deficiency, Anemia in a pre-term infant: " + (2 * weight2) + "-" + 15 * weight2 + "mg elemental iron 24hourly(max 15mg elemental fe/days)" + " \n " +
                                    "Prophylaxis for a preterm-infant" + (2 * weight2) + "-" + (15 * weight2) + "mg elemental iron 24hrly(max 15mg elemental fe/days) \n" +
                                    "Prophylaxis for a term-infant" + (1 * weight2) + "-" + (2 * weight2) + "Prophylaxis for a preterm-infant ";
                        } else if (weight2 >= 15) {
                            drugDetails = "Iron deficiency, Anemia in a pre-term infant: " + 15 * weight2 + "mg elemental iron 24hrly(max 15mg elemental fe/days)" + " \n " +
                                    "Prophylaxis for a preterm-infant" + (15 * weight2) + "mg elemental iron 24hrly(max 15mg elemental fe/days) \n" +
                                    "Prophylaxis for a term-infant" + (15 * weight2) + "Prophylaxis for a preterm-infant ";
                        } else {
                            drugDetails = "Iron deficiency, Anemia in a pre-term infant: " + (3 * weight2) + "-" + 6 * weight2 + "mg elemental iron 24hourly";
                        }
                    }
                } else {
                    drugDetails = "null";
                }
                description = "";
                webview.loadData(description, "text/html", "UTF-8");
                results.setText(drugDetails);
                // get our html content
                String htmlAsString26 = getString(R.string.Iron);
                webview1.loadDataWithBaseURL(null, htmlAsString26, "text/html", "utf-8", null);
                break;
            case 31:
                //Lactulose
                //info
                //description="Age 1m–12 yrs, 15-20 mg/kg at a rate not exceeding 1 mg/kg/minute as a loading dose; maintenance dose of 2.5–5 mg/kg twice daily (max. 150mg twice daily);  Similardosing	can	be	used	in	neonates."
                if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("months")) {
                    if (agedd < 12) {
                        drugDetails = "Hepatic encephalopathy 1.7 - 6.7g/day (2.5 - 10ml) orally divided in 3-4 doses";
                    } else {
                        drugDetails = "Hepatic Encephoropathy 25-60g/day (40-90ml) orally divided in 3-4 doses ";
                    }
                } else if (weight2 > 40 || (weight2*2) > 40 || weight2 > 60 || (weight2*3)>60) {
                    drugDetails = "Chronic constipation 40g/day (60ml/day) orally in divided doses";
                }else {
                    drugDetails = "Chronic constipation:" + (weight2*7) + "-" + (weight2*2) + "g/day" + weight2*1 + "-" + weight2*3 + "ml/day orally divided doses";
        }
        results.setText(drugDetails);
        // get our html content
        String htmlAsString27 = getString(R.string.Lactulose);
        webview1.loadDataWithBaseURL(null, htmlAsString27, "text/html", "utf-8", null);
        break;
        case 32:
        //Mebendazole
        //(for	age	>	1	yr)		100mg bd for 3 days or 500mg stat
        if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("years")) {
            if (agedd > 1) {
                drugDetails = "100mg bd for 3 days or 500mg stat";
            } else {
                drugDetails = "Not recommended for infants";
            }
        }
        description = "Not recommended for infants";
        results.setText(drugDetails);

        // get our html content
        String htmlAsString28 = getString(R.string.Mebendazole);
        webview1.loadDataWithBaseURL(null, htmlAsString28, "text/html", "utf-8", null);

        break;
        case 33:
        //Metronidazole
        if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("days")) {
            description = "<html><p>Neonates &lt 7 days - 7.5mg/kg iv 12hrly <br/> Over 7 days -7.5mg/kg iv over 8hrly </p></html>  ";
            if (agedd < 28 && (weight2*15) < 4000) {
                drugDetails = 7.5 * weight2 + " iv 12hourly ";

            } else if (agedd < 28 && (weight2*15) >= 4000) {
                drugDetails = 7.5 * weight2 + " iv 12hourly";
            }else if (agedd >= 28 && (weight2*22.5)<4000) {
                drugDetails = 7.5 * weight2 + "iv 8 hourly \n" +
                        weight2*7.5 + "oral dose 8 hourly";
            }else if (agedd>=28 && weight2*22.5>=4000){
                drugDetails = "iv: 1.3g 8 hourly (Not exceeding 4g/day) \n" +
                        "oral" + weight2*7.5 + "mg/dose 8 hourly";
            }

        } else {
            drugDetails = "Error";
        }
        results.setText(drugDetails);
        webview.loadData(description, "text/html", "UTF-8");

        // get our html content
        String htmlAsString29 = getString(R.string.Metronidazole);
        webview1.loadDataWithBaseURL(null, htmlAsString29, "text/html", "utf-8", null);

        break;
        case 34:
        //Morphine
        if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("days")) {
            if (agedd < 28) {
                drugDetails = df.format(0.05 * weight2) + " mg/dose im/sc/slow IV every 4 hours";
            } else {
                drugDetails = df.format(0.1 * weight2) + "-" + weight2*0.2 + " mg/dose im/sc/slow IV every 2-4 hours as needed. Max 15mg/dose \n" +
                        weight2*0.2 + "-" + weight2*0.5 + "mg/dose PO every 4-6 hours as needed";
            }
        } else {
            drugDetails = "";
        }
        results.setText(drugDetails);

        // get our html content
        String htmlAsString30 = getString(R.string.Morphine);
        webview1.loadDataWithBaseURL(null, htmlAsString30, "text/html", "utf-8", null);
        break;
        case 35:
        //Multivitamins
        //Age <6 m: 2.5mls daily;
        if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("months")) {
            if (agedd < 6) {
                drugDetails = "2.5mls daily";
            }
        } else {
            drugDetails = "5mls daily";
        }
        description = "Age <6 m: 2.5mls daily;\n" +
                "Age > 6m: 5mls daily";
        results.setText(drugDetails);
        webview.loadData(description, "text/html", "UTF-8");
        break;
        case 36:
        //Nystatin
        drugDetails = "(100,000 iu/ml) 1ml 6hrly (2weeks if HIV +ve)";
        results.setText(drugDetails);

        //webview.loadData(description, "text/html", "UTF-8");
        // get our html content
        String htmlAsString31 = getString(R.string.Nystatin);
        webview1.loadDataWithBaseURL(null, htmlAsString31, "text/html", "utf-8", null);
        break;
        case 37:
        //Paracetamol
        //10-15mg / kg 6 to 8 hrly
        description = "10-15mg/kg 6 to 8 hrly";
        drugDetails = 10 * weight2 + "-" + 15 * weight2 + " mg 6 to 8 hrly";
        webview.loadData(description, "text/html", "UTF-8");
        results.setText(drugDetails);
        // get our html content
        String htmlAsString32 = getString(R.string.Paracetamol);
        webview1.loadDataWithBaseURL(null, htmlAsString32, "text/html", "utf-8", null);
        break;
        case 38:
        //Pethidine, im
        //0.5 to 1mg / kg every 4- 6 hours
        description = "0.5 to 1mg/kg every 4-6 hours";
        drugDetails = 0.5 * weight2 + "-" + 1 * weight2 + " mg im  4 to 6 hrly";
        results.setText(drugDetails);
        webview.loadData(description, "text/html", "UTF-8");

        // get our html content
        String htmlAsString33 = getString(R.string.Pethidine);
        webview1.loadDataWithBaseURL(null, htmlAsString33, "text/html", "utf-8", null);

        break;
        case 39:
        //Phenobarbitone
        //description = "<html><p>High dose maintainance for Chronic Therapy <br/> Low doze mainatance used as starting doze for fits in acute febrile ilness</p></html>";
        //Loading with 15mg/kg (assuming not	on	maintenance	phenobarb)	followed by 2.5mg – 5mg/kg daily, Page	13
        if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("days")) {
            if (agedd <= 28) {
                drugDetails = "Loading with " + 20 * weight2 + " mg (assuming not on maintenance phenobarb) \n Maintenance dose" + 2.5 * weight2 + "-" + 5 * weight2 + " mg daily im/oral \n " +
                        weight2*5 + "mg im/oral as high dose maintenance in chronic therapy";

            } else {
                drugDetails = "Loading with " + 15 * weight2 + " mg im/oral \n maintrenace dose" + 2.5 * weight2 + "-" + 5 * weight2 + " mg daily im/oral \n" +
                        weight2*2.5 + "mg im/oral as a starting dose for fits in febrile illness \n" +
                        weight2*2 + "mg im/oral as high dose maintenance in chronic therapy" ;
            }
        } else {
            drugDetails = "";
        }
        drugDetails = "";
        results.setText(drugDetails);
        //webview.loadData(description, "text/html", "UTF-8");
        // get our html content
        String htmlAsString34 = getString(R.string.Phenobarbitone);
        webview1.loadDataWithBaseURL(null, htmlAsString34, "text/html", "utf-8", null);
        break;
        case 40:
        //Phenytoin
        description = "<html><p>Age 1m–12 yrs, 15-20 mg/kg at a rate not exceeding" +
                "1 mg/kg/minute as a loading dose;<br/> maintenance dose of" +
                "2.5–5 mg/kg twice daily (max. 150mg twice daily);<br/>" +
                "Similar dosing	can	be	used in	neonates.</html>";

        if ((weight2*2.5) > 10 || (weight2*5) > 150) {
            drugDetails = "loading dose: " + 15 * weight2 + "-" + 20 * weight2 + " mg iv/oral at a rate not exceeding" + weight2*1 +  "mg/minute\n"
                    + "maintenance dose: 150mg 12 hourly iv/oral" ;
        } else {
            drugDetails = "loading dose: " + 15 * weight2 + "-" + 20 * weight2 + " mg at a rate not exceeding 1 mg/minute\n"
                    + "maintenance dose:" + 2.5 * weight2 + "-" + 5 * weight2 + "mg twice daily (max. 150mg 12 hourly)";
        }
        webview.loadData(description, "text/html", "UTF-8");
        results.setText(drugDetails);

        // get our html content
        String htmlAsString35 = getString(R.string.Phenytoin);
        webview1.loadDataWithBaseURL(null, htmlAsString35, "text/html", "utf-8", null);
        break;
        case 41:
        //Potassium - oral 1 - 4 mmol/kg/day
        drugDetails = 1 * weight2 + "-" + 4 * weight2 + " mmol/day orally";
        results.setText(drugDetails);
        // get our html content
        String htmlAsString36 = getString(R.string.Potassium);
        webview1.loadDataWithBaseURL(null, htmlAsString36, "text/html", "utf-8", null);
        break;
        case 42:
        //Prednisolone -
        drugDetails = "Asthma " + 2 * weight2 + " mg daily orally usually for 3-5 days";
        //description = "usually for 3-5 days, stopped when symptoms largely resolved";
        results.setText(drugDetails);
        //webview.loadData(description, "text/html", "UTF-8");
        // get our html content
        String htmlAsString37 = getString(R.string.Prednisolone);
        webview1.loadDataWithBaseURL(null, htmlAsString37, "text/html", "utf-8", null);
        break;
        case 43:
        //Quinine
        //Toast.makeText(getApplicationContext(), "here",Toast.LENGTH_LONG).show();
        drugDetails = df.format(weight2 * 20) + " mg loading dose \n Maintenance dose" + df.format(weight2 * 10) + " iv/im 8 hrly maintainance \n Click here for preparation procedure";
        results.setText(drugDetails);
        results.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(DrugDescriptionActivity.this, MalariaTreatmentPreparation.class);
                startActivity(i);
            }
        });

        // get our html content
        String htmlAsString38 = getString(R.string.Quinine);
        webview1.loadDataWithBaseURL(null, htmlAsString38, "text/html", "utf-8", null);

        break;
        case 44:
        //Salbutamol
        String iv = "";
        String nebulised = "";
        String inhaled = "";
        String oral = "0";
        if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("days")) {
            iv = ((weight2 * 5) >= 250 ? 250 : (weight2 * 5)) + "Micrograms over 5 minutes";

        } else if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("months")) {
            iv = ((weight2 * 5) >= 250 ? 250 : (weight2 * 5)) + "Micrograms over 5 minutes";
            if (agedd >= 2 && agedd <= 11) {
                oral = "1mg /dose 6-8hrly";
            }

        } else if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("years")) {
            if (agedd < 2) {
                iv = ((weight2 * 5) >= 250 ? 250 : (weight2 * 5)) + "Micrograms over 5 minutes";
            } else {
                iv = ((weight2 * 15) >= 250 ? 250 : (weight2 * 15)) + "Micrograms over 5 minutes";
            }
            if (agedd >= 1 && agedd <= 4) {
                oral = "2mg /dose 6-8hrly";
            }
        }
        nebulised = "2.5mg/dose as required";
        inhaled = "(100mcg /puff) 2 puffs as required acutely or upto 4-6 hourly for acute wheeze for 5 days";
        //set results
        results.setText("Nebulised: " + nebulised + "\nIV: " + iv + "\n Inhaled: " + inhaled + "\n Oral: " + oral);


        //info----
        //description = "<html><p>NEBULISED: 2.5 mg/dose as required" +
         //       "<br/>- INHALED (Not for use as long term therapy): (100 mcg per puff) 2 puffs via spacer repeated as required acutely or 2 puffs up to 4-6 hrly for acute wheeze for <5 days." +
         //       "<br/> - ORAL (No longer recommended unless on inhaled therapy): Age 2-11months - 1mg/dose 6-8hrly, Age 1-4yrs - 2mg/dose 6-8hrly. (1 week ONLY - Not suitable for maintenance therapy)" +
         //       "<br/>- IV in hospital ONLY over 5mins: Age <2yrs - 5mcg/kg. Age >=2yrs - up to 15mcg/kg. MAX. dose 250mcg." +
         //       "<br/>- NOTE: IV therapy should only be used on an HDU, ideally with a monitor and MUST be given slowlly as directed. Use inhaled steroids for persistent asthma.</p></html>";

        //webview.loadData(description, "text/html", "UTF-8");
        // get our html content
        String htmlAsString39 = getString(R.string.Salbutamol);
        webview1.loadDataWithBaseURL(null, htmlAsString39, "text/html", "utf-8", null);

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
                if(String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("days")){
                    if (agedd < 28) {
                        drugDetails="loading dose: "+20*weight2+" mg once daily\n"
                                +"maintenance dose:"+10*weight2+"mg twice daily";
                    //description="You must monitor clinical chemistry and haematological	parameters if dose exceeds 40 mg/kg daily";
                     }else if(agedd>=28) {
                        if (weight2 * 15 < 600 || weight2 * 10 < 600) {
                            drugDetails = "loading dose: " + 10 * weight2 + "-" + 15 * weight2 + " mg twice daily\n"
                                    + "maintenance dose:" + 12.5 * weight2 + "-" + 15 * weight2 + "mg twice daily";
                            //description="You must monitor clinical chemistry and haematological	parameters if dose exceeds 40 mg/kg daily";
                        } else {
                            drugDetails = "Initially 600mg daily in 1-2 divided doses \n" +
                                    "maintenance" + weight2 * 25 + "-" + weight2 * 30 + "mg daily in 2 divided doses PO";
                            //description="";
                        }
                    }

                }else{
                    drugDetails="";
                    //description="";
                }

                webview.loadData(description, "text/html", "UTF-8");
                results.setText(drugDetails);
                // get our html content
                String htmlAsString40 = getString(R.string.Valproate);
                webview1.loadDataWithBaseURL(null, htmlAsString40, "text/html", "utf-8", null);
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

                // get our html content
                String htmlAsString41 = getString(R.string.VitaminA);
                webview1.loadDataWithBaseURL(null, htmlAsString41, "text/html", "utf-8", null);

                break;
            case 48:
                //Vitamin D
               if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("months")){
                    if (agedd<6){
                        drugDetails="Dosage: 3,000 u = 75 micrograms";
                        description="Chole or ergocalciferol: Rickets. Low dose	regimens daily for 8 – 12 wks or one high dose.	± Calcium for first	week of	treatment.";
                    }else if (agedd>=6){
                        drugDetails="Dosage: 6,000 u = 150 micrograms \n 6m stat im regimen: 300,000 u (7.5 mg Stat)";
                        description="Chole or ergocalciferol: Rickets. Low dose	regimens daily for 8 – 12 wks or one high dose.	± Calcium for first	week of	treatment.";

                        // get our html content
                        String htmlAsString42 = getString(R.string.VitaminD);
                        webview1.loadDataWithBaseURL(null, htmlAsString42, "text/html", "utf-8", null);
                    }

                }else{
                    drugDetails="";
                    description="";
                }
                webview.loadData(description, "text/html", "UTF-8");
                results.setText(drugDetails);
                break;
            case 49:
                //Vitamin D – Maintenance After	treatment	course
                if(String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("months")){
                    if (agedd<6){
                        drugDetails="200 - 400 u (5 – 10 μg)";
                    }else if (agedd>=6 && agedd<=12){
                        drugDetails="400 - 800 u (10 – 20 μg)";
                    }
                }else {
                    drugDetails = "";
                }

                //webview.loadData(description, "text/html", "UTF-8");
                results.setText(drugDetails);

                // get our html content
                String htmlAsString43 = getString(R.string.VitaminDD);
                webview1.loadDataWithBaseURL(null, htmlAsString43, "text/html", "utf-8", null);
                break;
            case 50:
                //Vitamin K
                if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("days")) {
                    if (agedd < 28 && weight2 < 1.5) {
                        drugDetails = "0.5mg stat im \n " +
                                "for liver disease" + weight2 * 0.3 + "mg stat(max 10mg)";
                    } else if (agedd < 28 && weight2 >= 1.5) {
                        drugDetails = "1mg stat im \n " +
                                "for liver disease" + weight2 * 0.3 + "mg stat (max 10mg)";

                    } else {
                        drugDetails = "For liver disease" + weight2 * 0.3 + "mg stat (max 10mg)";
                        // description="";
                    }
                }
                //webview.loadData(description, "text/html", "UTF-8");
                results.setText(drugDetails);

                // get our html content
                String htmlAsString44 = getString(R.string.VitaminK);
                webview1.loadDataWithBaseURL(null, htmlAsString44, "text/html", "utf-8", null);
                break;
            case 51:
                //zinc Sulphate
              if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("months")){
                    if (agedd<=6){
                        drugDetails="10mg daily po for 14 days";
                    }else if (agedd>6){
                        drugDetails="20mg daily po for 14 days";

                    }

              }else{
                    drugDetails="";
                   // description="";
                }
                results.setText(drugDetails);
                // get our html content
                String htmlAsString45 = getString(R.string.ZincSulphate);
                webview1.loadDataWithBaseURL(null, htmlAsString45, "text/html", "utf-8", null);
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




}
