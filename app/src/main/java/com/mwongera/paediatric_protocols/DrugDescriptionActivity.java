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
    Double ageddd;
    int positioned = 0;
    Double weight;
    String aging, weighing;
    String drugtype;
    String position;
    String drugDetails = null;
    String description = null;
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
                } else {
                    Toast.makeText(getApplicationContext(), "Enter details correctly", Toast.LENGTH_SHORT).show();
                }

                if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("days")) {
                    ageddd = Double.valueOf(df.format(age / 28));
                }else if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("months")) {
                    ageddd = Double.valueOf(df.format(age == age));
                }else if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("years")) {
                   ageddd = Double.valueOf(df.format(age * 12));
                }else {
                    Toast.makeText(getApplicationContext(), "Enter details correctly", Toast.LENGTH_SHORT).show();
                }
                //add a keyboard remover function
                calculateDosage(weight, ageddd, positioned);
            }
        });

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-3940256099942544~3347511713");

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }

    //come up with an algorithm for each drug.
    private void calculateDosage(Double weight2, Double ageddd, int pos) {


        //DO a switch case for all the drugs.
        switch (pos) {
            //Adrenaline   Give 0.1ml/kg in resuscitation.
            case 0:
                drugDetails = "Give" + df.format(0.1 * weight2) + " ml in resuscitation \n" +
                        df.format(0.5 * weight2) + " ml of 1:1000 solution (max 5ml) for severe viral group";

                results.setText(drugDetails);
                //webview.loadData(description, "text/html", "UTF-8");
                // get our html content
                String htmlAsString0 = getString(R.string.Adrenaline);
                webview1.loadDataWithBaseURL(null, htmlAsString0, "text/html", "utf-8", null);


                break;
            case 1:
                //Albendazole Age < 2yrs, 200mg stat
                //Age ≥ 2yrs, 400mg stat
                if (ageddd < 24) {
                    drugDetails = "Give 200mg stat";

                }
                else {
                        drugDetails = "Give 400mg stat";
                    }
                // add values to the txtviews
                results.setText(drugDetails);
                // get our html content
                String htmlAsString1 = getString(R.string.Albendazole);
                webview1.loadDataWithBaseURL(null, htmlAsString1, "text/html", "utf-8", null);
                break;
            case 2:
                //Amikacin Age 1mo to 18 yrs, 15mg/kg once daily; same dosing can be used in newborns.
                        drugDetails = (15 * weight2 + " mg once daily");
                        results.setText(drugDetails);
                //webview.loadData(description, "text/html", "UTF-8");
                // get our html content
                String htmlAsString2 = getString(R.string.Amikacin);
                webview1.loadDataWithBaseURL(null, htmlAsString2, "text/html", "utf-8", null);
                break;
            case 3:
                //Aminophylline
                //(iv)
                //Newborn Loading dose 6mg/kg  iv over 1 hour or rectal,
                //Maintenance (iv or oral): Age	0-7	days - 2.5mg/kg ///////////////////////////depends more on age
                    if (ageddd < 1 ) {
                        drugDetails = "Loading dose " + (6 * weight2) + " mg  iv over 1 hour or rectal";
                        if (ageddd < 0.25) {
                           drugDetails = "Maintenance (iv or oral):" + (2.5 * weight2) + " mg 12hrly ";
                        }else if (ageddd >= 0.25 ){
                            drugDetails = "Maintenance (iv or oral):" + (4 * weight2) + " mg 12hrly ";
                        }

                } else {
                    drugDetails = "Asthma:" + 5 * weight2 + "mg iv first dose over 30 mins max 500mg";

                }


                results.setText(drugDetails);
                ///calculate the maintainance doses
                //description=" Age7-28 days "+4*weight2+" mg 12hrly<br/> ";
                //webview.loadData(description, "text/html", "UTF-8");
                // get our html content
                String htmlAsString3 = getString(R.string.Aminophylline);
                webview1.loadDataWithBaseURL(null, htmlAsString3, "text/html", "utf-8", null);
                break;

            case 4:
                //Use 25mg/kg/dose for simple infections and 40-45mg/kg
                //for pneumonia
                drugDetails = (25 * weight2) + "mg dose for simple infections\n " + (40 * weight2) + "-" + (45 * weight2) + " mg for pneumonia";
                results.setText(drugDetails);

                // get our html content
                String htmlAsString4 = getString(R.string.Amoxicillin);
                webview1.loadDataWithBaseURL(null, htmlAsString4, "text/html", "utf-8", null);
                break;
            case 5:
                //Neonate: 50mg/kg/dose 12 hourly iv or  if aged < 7 days and 8 hourly if aged 8 - 28 days.
                //Age 1m and over: 50mg/kg/dose (Max 500mg) 8 hourly
                    if (ageddd <= 0.25) {
                        drugDetails = (50 * weight2) + " mg per dose 12 hourly or im";
                    } else if (ageddd > 0.25 && ageddd <= 1) {
                        drugDetails = (50 * weight2) + " mg per dose 8 hourly or im";
                    } else {
                    drugDetails = (50 * weight2) + " mg (max 500mg) per dose 6 hourly iv / im";
                }
                results.setText(drugDetails);
                // get our html content
                String htmlAsString5 = getString(R.string.Ampicillin);
                webview1.loadDataWithBaseURL(null, htmlAsString5, "text/html", "utf-8", null);
                break;

            case 6:
                //Artemether -
                //Lumefantrine

                if (weight2 < 5) {
                    drugDetails = "1/2 tablet Given with Food Stat then at 8hrs then BD on day 2 and 3";

                } else if (weight2 >= 5 && weight2 < 15 ) {
                    drugDetails = "1 tablet Given with Food Stat then at 8hrs then BD on day 2 and 3";

                } else if (weight2 >= 15 && weight2 < 24 ) {
                    drugDetails = "2 tablets Given with Food Stat then at 8hrs then BD on day 2 and 3";

                } else if (weight2 >= 24 && weight2 < 34 ) {
                    drugDetails = "3 tablets Given with Food Stat then at 8hrs then BD on day 2 and 3";
                } else {
                    drugDetails = "Kindly check your drug index for adult dosages";
                }
                results.setText(drugDetails);
                break;
            case 7:
                //	Artemisinin -
                //	Piperaquine
                if (ageddd >= 3 && ageddd < 35 ) {
                    drugDetails = "Give 1 paediatric tablet OD for 3 days";

                } else if (ageddd >= 35 && ageddd < 60 ) {
                    drugDetails = "Give 2 paediatric tablets OD for 3 days";

                } else if (ageddd >= 60 && ageddd < 142 ) {
                    drugDetails = "Give 1 adult tablet OD for 3 days";
                } else {
                    drugDetails = "Kindly check your drug index for adult dosages";
                }

                results.setText(drugDetails);
                break;
            case 8:
                //Artesunate Age 1m and over: 2.4mg/kg given iv/im at 0, 12 and 24
                //hours then daily –
                    if (weight2 <= 20) {
                        drugDetails = df.format(3 * weight2) + " mg At 0,12, and 24h then continue daily until oral administration is feasible" +
                                "\n press here for preparation procedure";
                    } else {
                        drugDetails = df.format(2.4 * weight2) + " mg At 0,12, and 24h then continue daily until oral administration is feasible" +
                                "\n press here for preparation procedure";
                    }
                    results.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            Intent i = new Intent(DrugDescriptionActivity.this, MalariaTreatmentPreparation.class);
                            startActivity(i);
                        }
                    });

                results.setText(drugDetails);
                //webview.loadData(description, "text/html", "UTF-8");

                // get our html content
                String htmlAsString6 = getString(R.string.Artesunate);
                webview1.loadDataWithBaseURL(null, htmlAsString6, "text/html", "utf-8", null);
                break;
            case 9:
                //Arzithromycin
                    drugDetails = df.format(10 * weight2) + " mg (Max = 500mg PO for 3 days)";

                results.setText(drugDetails);
                //webview.loadData(description, "text/html", "UTF-8");

                // get our html content
                String htmlAsString7 = getString(R.string.Azithromycin);
                webview1.loadDataWithBaseURL(null, htmlAsString7, "text/html", "utf-8", null);
                break;

            case 10:
                //Budesonide
                drugDetails = " pMDI with spacer 200 mgs daily (low dose)";

                results.setText(drugDetails);
                //webview.loadData(description, "text/html", "UTF-8");

                // get our html content
                String htmlAsString8 = getString(R.string.Budesonide);
                webview1.loadDataWithBaseURL(null, htmlAsString8, "text/html", "utf-8", null);
                break;

            case 11:
                //Beclomethasone Age < 2yrs	50-100	micrograms	12hrly,
                //Age ≥ 2yrs 100-200 micrograms	12hrly
                if (ageddd < 24) {
                    drugDetails = "50-100	micrograms	12hrly";
                } else {
                    drugDetails = "100-200	micrograms	12hrly";
                }
                results.setText(drugDetails);
                //webview.loadData(description, "text/html", "UTF-8");

                // get our html content
                String htmlAsString9 = getString(R.string.Beclomethasone);
                webview1.loadDataWithBaseURL(null, htmlAsString9, "text/html", "utf-8", null);
                break;
            case 12:
                //Benzyl Penicillin (X-pen)Newborn:  50,000 iu/kg/dose 12 hourly iv or im.
                    if (ageddd <= 0.25) {
                        drugDetails =df.format(weight2 * 50000) + " iu/dose 12 hourly iv or im ";
                    } else {
                        drugDetails = df.format(weight2 * 50000) +" iu/dose 6 hourly iv / im " +
                                "\n Double Penicillin doses when treating meningitis and age > 1 month";
                    }
                results.setText(drugDetails);
                //webview.loadData(description, "text/html", "UTF-8");

                // get our html content
                String htmlAsString10 = getString(R.string.Benzyl);
                webview1.loadDataWithBaseURL(null, htmlAsString10, "text/html", "utf-8", null);
                break;

            case 13:
                //Caffeine Citrate

                drugDetails = "Loading dose : Oral " + (weight2 * 20) + " mg (or iv over 30 minutes)" +
                        "\n Maintenace dose : Oral " + (weight2 * 5) + " mg (or iv over 30 minutes)";

                // get our html content
                String htmlAsString11 = getString(R.string.Caffeine);
                webview1.loadDataWithBaseURL(null, htmlAsString11, "text/html", "utf-8", null);
                break;

            case 14:
                //Newborns and up to 4 yrs: 0.25mmol/kg 6 hrly.
                drugDetails = "Severe hypocalcemia " +
                        "\n V bolus of 10% calcium gluconate" + 0.5*weight2 + " ml or" +  (0.11 * weight2) + "mmol to a maximum of" + 20 *weight2  + " ml over 5 – 10 min then continuous IV infusion over 24 hours of" + weight2*1 + "mmol (Max 8.8 mmol) " +
                        "\n Mild hypocalcemia " +
                        "\n Oral calcium" +  (0.2*weight2) + "mmol to a maximum of 10 mmol 6 hourly" ;

                results.setText(drugDetails);
                String htmlAsString12 = getString(R.string.Calcium);
                webview1.loadDataWithBaseURL(null, htmlAsString12, "text/html", "utf-8", null);
                break;
            case 15:
                //Carbamazepine Age 1 m –12 yrs: initially 5 mg/kg at night, increased
                    if (ageddd >= 1) {
                        drugDetails = df.format(5 * weight2) + " mg  at night, increased as necessary by " + df.format(2.5 * weight2) + "-" + df.format(5 * weight2) + " mg every 3–7 days "
                                + "\n usual maintenance dose " + df.format(5 * weight2) + " mg 2–3 times daily.";
                } else {
                    drugDetails = "Not recommended for neonates";
                }

                results.setText(drugDetails);
                //webview.loadData(description, "text/html", "UTF-8");

                // get our html content
                String htmlAsString13 = getString(R.string.Carbamazepine);
                webview1.loadDataWithBaseURL(null, htmlAsString13, "text/html", "utf-8", null);
                break;
            case 16:
                //Cefotaxime Preferred	to	Ceftriaxone	for	treatment	of	neonatal	meningitis	if	aged	<7	days:
                if (ageddd <= 0.25) {
                        drugDetails = "Pre-term : " + (50 * weight2) + " mg 12 hourly " +
                                "\nTerm: aged < 7 days: " + 50 * weight2 + "mg 8 hourly";
                } else {
                        drugDetails = "Provided dosages are for neonates aged 7 days or less";
                    }
                results.setText(drugDetails);
                //webview.loadData(description, "text/html", "UTF-8");

                // get our html content
                String htmlAsString14 = getString(R.string.Cefotaxime);
                webview1.loadDataWithBaseURL(null, htmlAsString14, "text/html", "utf-8", null);
                break;

            case 17:
                //Ceftazidime
                if (ageddd <= 0.25) {
                    drugDetails = 50 * weight2 + "mg iv/im 12hrly ";

                } else if ((ageddd > 0.25 && ageddd < 1) || (weight2 > 1.2)){
                    drugDetails = (50 * weight2) + "mg iv/im 8hrly ";
                } else {
                    drugDetails = (30 * weight2) + "mg - " + (weight2*50) + "mg iv/im 8hrly (Max 6 grams per day) ";
                }
                results.setText(drugDetails);
                //webview.loadData(description, "text/html", "UTF-8");

                // get our html content
                String htmlAsString15 = getString(R.string.Ceftazidime);
                webview1.loadDataWithBaseURL(null, htmlAsString15, "text/html", "utf-8", null);

                break;

            case 18:
                //Ceftriaxone
                if (ageddd <= 0.25) {
                    drugDetails = 50 * weight2 + "mg iv/im 24hrly ";

                } else if (ageddd > 0.25 && ageddd < 1)  {
                    drugDetails = (50 * weight2) + "mg iv/im 24hrly " +
                            "\n for meningitis and very severe sepsis: " + (50 * weight2) + "mg iv/im 12 hourly";
                } else {
                    drugDetails = "max 4gms iv/im 24 hourly";
                }
                results.setText(drugDetails);
                //webview.loadData(description, "text/html", "UTF-8");
                // get our html content
                String htmlAsString16 = getString(R.string.Ceftriaxone);
                webview1.loadDataWithBaseURL(null, htmlAsString16, "text/html", "utf-8", null);

                break;


            case 19:
                //Chloramphenicol

                    if (ageddd <= 0.25) {
                        drugDetails = "Are you sure you want to issue Chloramphenicol to a neonate";

                    } else  {
                        drugDetails = (25 * weight2) + "mg iv/im 6hrly - Meningitis " +
                                "\n oral : " + (25 * weight2) + "mg 6 hourly";
                    }
                results.setText(drugDetails);
                //webview.loadData(description, "text/html", "UTF-8");

                // get our html content
                String htmlAsString17 = getString(R.string.Chloramphenicol);
                webview1.loadDataWithBaseURL(null, htmlAsString17, "text/html", "utf-8", null);
                break;

            case 20:
                //Chlorhexidine
                drugDetails = " (4% Chlorhexidine) apply once daily until the cord separates";

                results.setText(drugDetails);
                //webview.loadData(description, "text/html", "UTF-8");

                // get our html content
                String htmlAsString18 = getString(R.string.Chlorhexidine);
                webview1.loadDataWithBaseURL(null, htmlAsString18, "text/html", "utf-8", null);
                break;

            case 21:
                //Ciprofloxacin

                    if (ageddd <= 1) {
                        drugDetails = "Not recommended for neonates ";

                    } else if (ageddd >1) {
                        drugDetails = "Dysentery dosing" + df.format(15 * weight2) + " mg/dose 12hrly for 3 days ";
                    }
                results.setText(drugDetails);
                //webview.loadData(description, "text/html", "UTF-8");

                // get our html content
                String htmlAsString19 = getString(R.string.Ciprofloxacin);
                webview1.loadDataWithBaseURL(null, htmlAsString19, "text/html", "utf-8", null);

                break;
            case 22:
                //Clotrimazole 1%
                drugDetails = "Apply 2-3 times daily until cleared";
                results.setText(drugDetails);
                //webview.loadData(description, "text/html", "UTF-8");

                // get our html content
                String htmlAsString20 = getString(R.string.Clotrimazole);
                webview1.loadDataWithBaseURL(null, htmlAsString20, "text/html", "utf-8", null);
                break;
            case 23:
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
                String htmlAsString21 = getString(R.string.Cotrimoxazole);
                webview1.loadDataWithBaseURL(null, htmlAsString21, "text/html", "utf-8", null);
                break;
            case 24:
                //Dexamethasone
                drugDetails = df.format(0.6 * weight2) + " mg stat for severe croup iv/im";
                results.setText(drugDetails);

                // get our html content
                String htmlAsString22 = getString(R.string.Dexamethasone);
                webview1.loadDataWithBaseURL(null, htmlAsString22, "text/html", "utf-8", null);
                break;
            case 25:
                //Dextrose/glucose
                    if (ageddd < 1) {
                        drugDetails = df.format(2 * weight2) + "mls 10% dextrose iv over 5-10 mins ";

                    } else {
                        drugDetails = df.format(5 * weight2) + " mls 10% dextrose iv over 5-10 mins ";
                    }
                results.setText(drugDetails);
                //webview.loadData(description, "text/html", "UTF-8");

                // get our html content
                String htmlAsString23 = getString(R.string.Dextrose);
                webview1.loadDataWithBaseURL(null, htmlAsString23, "text/html", "utf-8", null);
                break;
            case 26:
                //Dihydrocodeine
                    if (ageddd >= 12 && ageddd <= 24) {
                        drugDetails = df.format(0.5 * weight2) + "mg every 4–6 hours";
                    } else if (ageddd > 24 && weight2 < 60) {
                        drugDetails = df.format(0.5 * weight2) + " - " + df.format(30 * weight2) + "mg every 4–6 hours";
                    } else if (ageddd > 24 && weight2 > 60) {
                        drugDetails = df.format(30 * weight2) + "mg every 4–6 hours";
                    } else {
                        drugDetails = "Dihydrocodeine is not recommended for infants";
                    }

                //webview.loadData(description, "text/html", "UTF-8");
                results.setText(drugDetails);

                // get our html content
                String htmlAsString24 = getString(R.string.Dihydrocodeine);
                webview1.loadDataWithBaseURL(null, htmlAsString24, "text/html", "utf-8", null);
                break;
            case 27:
                //Diazepam (iv)
                    if (ageddd > 1) {
                        drugDetails = df.format(0.3 * weight2) + "mg =" + df.format((0.3 * weight2) / 5) + " mls of 10mg/2ml solution";
                    } else {
                        drugDetails = "Not recommended in neonates ";
                    }
                //drugDetails=0.3*weight2+ "mg (="+300*weight2+" mcg)";
                //webview.loadData(description, "text/html", "UTF-8");
                results.setText(drugDetails);

                // get our html content
                String htmlAsString25 = getString(R.string.DiazepamV);
                webview1.loadDataWithBaseURL(null, htmlAsString25, "text/html", "utf-8", null);
                break;
            case 28:
                //Diazepam (rectal)m;
                if (ageddd > 1) {
                    drugDetails = df.format(0.5 * weight2) + "mg =" + df.format((0.5 * weight2) / 5) + " mls of 10mg/2ml solution";
                } else {
                    drugDetails = "Not recommended in neonates ";
                }

                results.setText(drugDetails);
                //webview.loadData(description, "text/html", "UTF-8");
                // get our html content
                String htmlAsString26 = getString(R.string.DiazepamR);
                webview1.loadDataWithBaseURL(null, htmlAsString26, "text/html", "utf-8", null);
                break;
            case 29:
                //Digoxin (oral)
                    if (ageddd >= 24 && ageddd < 60) {
                        drugDetails = "initialy " + (35 * weight2)
                                + " micrograms in 3 divided doses for 24 hrs then \n "
                                + 10 * weight2 + " micrograms daily in 1–2 doses";
                    } else if (ageddd >= 60 && ageddd < 120) {
                        drugDetails = "initialy " + 25
                                * weight2
                                + " micrograms (max.750 micrograms) in 3 divided doses for 24 hrs then \n "
                                + 6 * weight2
                                + " micrograms (max. 250 micrograms daily)daily in 1–2 doses";
                    } else if (ageddd > 120) {
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
                    } else {
                    drugDetails = "Consult a paeditrician for use in under 2 year olds";
                }

                //add info
                description = getResources().getString(R.string.d24);
                results.setText(drugDetails);
                //webview.loadData(description, "text/html", "UTF-8");

                // get our html content
                String htmlAsString27 = getString(R.string.Digoxin);
                webview1.loadDataWithBaseURL(null, htmlAsString27, "text/html", "utf-8", null);

                break;

            case 30:
                //Erythromycin
                drugDetails = df.format(30 * weight2) + "-" + weight2*50 +" mg per day in 3-4 divided doses (Max 2 grams per day))";

                results.setText(drugDetails);
                //webview.loadData(description, "text/html", "UTF-8");

                // get our html content
                String htmlAsString28 = getString(R.string.Erythromycin);
                webview1.loadDataWithBaseURL(null, htmlAsString28, "text/html", "utf-8", null);
                break;

            case 31:
                //Flucloxacillin
                if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("days")) {
                    description = "<html><p>Neonates 25mg/kg  12hrly given as a 125mg/5ml suspension<br/>Over 1 month 50mg/kg per dose 8hrly </p></html>  ";
                    if (ageddd > 1) {
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
                //webview.loadData(description, "text/html", "UTF-8");

                // get our html content
                String htmlAsString29 = getString(R.string.Flucloxacillin);
                webview1.loadDataWithBaseURL(null, htmlAsString29, "text/html", "utf-8", null);

                break;
            case 32:
                //Furosemide
                drugDetails = 0.5 * weight2 + " mg -" + 1 * weight2 + " mg up to 6 hrly";
                results.setText(drugDetails);
                break;
            case 33:
                //Gentamicin
                    if (ageddd <= 0.25 ) {
                        if (weight2 < 2) {
                            drugDetails = df.format(3 * weight2) + " mg iv/im 24hrly";
                        } else {
                            drugDetails = df.format(5 * weight2) + " mg iv/im 24hrly";
                        }

                    } else {
                        drugDetails = df.format(7.5 * weight2) + " mg iv/im over 3-5 minutes 24hrly ";
                    }
                results.setText(drugDetails);
                //webview.loadData(description, "text/html", "UTF-8");

                // get our html content
                String htmlAsString30 = getString(R.string.Gentamicin);
                webview1.loadDataWithBaseURL(null, htmlAsString30, "text/html", "utf-8", null);
                break;
            case 34:

                //(For severe SCD only: Pain >3 episodes/ yr; stroke; transfusion ≥ 2/ yr; acute chest syndrome)
                //Child 2-12 years initially 10-15mg/kg once daily, increased every 12 weeks in steps of 2.5 - 5 mg/kg daily according to response; usual dose 15 - 30 mg/kg daily (max. 35 mg/kg)
                //Hydroxyurea
                    if (ageddd > 24) {
                        drugDetails = "initially" + df.format(10 * weight2) + "-" + df.format(15 * weight2) + "once daily" + " \n " +
                                "increased every 12 weeks in steps of" + df.format(2.5 * weight2) + "-" + df.format(5 * weight2) + "daily according to response; \n" +
                                "usual dose" + df.format(15 * weight2) + "-" + df.format(30 * weight2) + "daily (max. 35 mg/kg)";
                    } else {
                        drugDetails = "Consult a paeditrician for children under 2 years";
                    }
                }else {
                    drugDetails = "null";
                }
                results.setText(drugDetails);
                //webview.loadData(description, "text/html", "UTF-8");

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
                if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("months")) {
                    if (agedd < 12) {
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
                //webview.loadData(description, "text/html", "UTF-8");
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
                    } else if (agedd > 12) {
                        drugDetails = "Hepatic Encephoropathy 25-60g/day (40-90ml) orally divided in 3-4 doses ";
                    } else if (weight2 > 40 || (weight2 * 2) > 40 || weight2 > 60 || (weight2 * 3) > 60) {
                        drugDetails = "Chronic constipation 40g/day (60ml/day) orally in divided doses";
                    } else {
                        drugDetails = "Chronic constipation:" + (weight2 * 7) + "-" + (weight2 * 2) + "g/day" + weight2 * 1 + "-" + weight2 * 3 + "ml/day orally divided doses";
                    }
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
        results.setText(drugDetails);

        // get our html content
        String htmlAsString28 = getString(R.string.Mebendazole);
        webview1.loadDataWithBaseURL(null, htmlAsString28, "text/html", "utf-8", null);

        break;
        case 33:
        //Metronidazole
        if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("days")) {
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
        //webview.loadData(description, "text/html", "UTF-8");

        // get our html content
        String htmlAsString29 = getString(R.string.Metronidazole);
        webview1.loadDataWithBaseURL(null, htmlAsString29, "text/html", "utf-8", null);

        break;
        case 34:
        //Morphine
        if (String.valueOf(aged.getSelectedItem()).equalsIgnoreCase("days")) {
            if (agedd < 28) {
                drugDetails = df.format(0.05 * weight2) + "-" + df.format(0.2*weight2) + " mg/dose im/sc/slow IV every 4 hours";
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
        drugDetails = "Pre-terms : 0.5ml(50,000 u) to each side of the mouth 6 hourly (2weeks if HIV +ve) \n" +
                "Terms : 1ml(100,000 u) to each side of the mouth 6 hourly (2weeks if HIV +ve) ";
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
        //webview.loadData(description, "text/html", "UTF-8");
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
        //webview.loadData(description, "text/html", "UTF-8");

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
                drugDetails = "Loading with " + df.format(20 * weight2) + " mg im/oral \n Maintenance dose" + df.format(2.5 * weight2) + "-" + df.format(5 * weight2) + " mg daily im/oral \n " +
                        df.format(weight2*5) + "mg im/oral as high dose maintenance in chronic therapy";

            } else {
                drugDetails = "Loading with " + 15 * weight2 + " mg im/oral \n maintrenace dose" + df.format(2.5 * weight2) + "-" + df.format(5 * weight2) + " mg daily im/oral \n" +
                        weight2*2.5 + "mg im/oral as a starting dose for fits in febrile illness \n" +
                        weight2*2 + "mg im/oral as high dose maintenance in chronic therapy" ;
            }
        } else {
            drugDetails = "";
        }
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

                //webview.loadData(description, "text/html", "UTF-8");
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

                //webview.loadData(description, "text/html", "UTF-8");
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
                                "for liver disease" + df.format(weight2 * 0.3) + "mg stat(max 10mg)";
                    } else if (agedd < 28 && weight2 >= 1.5) {
                        drugDetails = "1mg stat im \n " +
                                "for liver disease" + df.format(weight2 * 0.3) + "mg stat (max 10mg)";

                    } else {
                        drugDetails = "For liver disease" + df.format(weight2 * 0.3) + "mg stat (max 10mg)";
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
