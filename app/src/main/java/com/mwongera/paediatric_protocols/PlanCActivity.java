package com.mwongera.paediatric_protocols;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by mwongera on 3/5/17.
 */

public class PlanCActivity extends Activity {

    Double age= null;
    String dosage=null;
    String drops=null;
    String step1;
    String step2;
    String notes="<html><p>i)Step1 30mls/kg Ringers over 30min if age>=12m or 60min if age <12m<br/>ii)Step 2 70mls/kg Ringers over 205hrs if age>=12m or 5hrs if age <12m<br/>ngt rehydration - 100 mls/kg ORS over 6 hrs</p></html>";
    Button btn,btn1;
    Spinner spn;
    TextView txtstep1,txtstep2,txtstep3;
    WebView webview;
    EditText weight;
    String lan[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_c);
        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#f9a614")));
        btn=(Button)findViewById(R.id.button1);
        btn1=(Button)findViewById(R.id.button2);
        txtstep1=(TextView)findViewById(R.id.textstep1);
        txtstep2=(TextView)findViewById(R.id.textstep2);
        txtstep3=(TextView)findViewById(R.id.textngt);
        weight=(EditText)findViewById(R.id.weight);
        //webview=(WebView)findViewById(R.id.webView1);
        spn=(Spinner)findViewById(R.id.spinner1);
        lan=getResources().getStringArray(R.array.age);
        //ArrayAdapter<String> entriesed= new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line,lan);
        //entriesed.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spn.setAdapter(entriesed);
        //btn click
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                if (weight.getText().toString().trim().length()!=0){
                    // TODO Auto-generated method stub
                    age=Double.parseDouble(weight.getText().toString());

                    String ngt=(age*100)+" mls ORS over 6hrs";
                    txtstep3.setText(ngt);
                    //select age----> <12m
                    if  ((String.valueOf(spn.getSelectedItem()).equalsIgnoreCase("Age<12m"))){
                        //A function to return the ringer volume--Step1 & step2

                        txtstep1.setText(calculateRingerStep1(age)+" Over 1hr ");
                        //webview.loadData(notes, "text/html", "UTF-8");
                        txtstep2.setText(calculateRingerStep2(age,"Age<12m")+" Over 5hrs ");

                    }else if ((String.valueOf(spn.getSelectedItem()).equalsIgnoreCase("Age>12m"))){


                        txtstep1.setText(calculateRingerStep1(age)+" Over 30 minutes ");
                        txtstep2.setText(calculateRingerStep2(age,"Age>12m")+" Over 2.5hrs");
                        //webview.loadData(notes, "text/html", "UTF-8");
                    }
                }else{

                    Toast.makeText(getApplicationContext(), "Input the Weight and Age correctly", Toast.LENGTH_SHORT).show();

                }
                removetheKeyboard();
            }

            //add notes.



        });
        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                weight.setText("");
                txtstep1.setText("");
                txtstep2.setText("");
                //webview.clearView();
                removetheKeyboard();

            }
        });



    }
    public void removetheKeyboard(){
        //First remove the virtual keyboad.


        InputMethodManager imm = (InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(weight.getWindowToken(), 0);
    }
    private String calculateRingerStep1(double aged) {
        //aged--->weight,not age
        if (aged<=2.0){

            dosage="50";
        }else if(aged>2.0 && aged<=2.5){
            dosage="75";
        }else if(aged>2.5 && aged<=4.0){
            dosage="100";
        }else if(aged>4.0 && aged<=6.0){
            dosage="150";
        }else if(aged>6.0 && aged<=7.0){
            dosage="200";
        }else if(aged>7.0 && aged<=9.0){
            dosage="250";
        }else if(aged>9.0 && aged<=11.0){
            dosage="300";
        }else if(aged>11 && aged<=12.0){
            dosage="350";
        }else if(aged>12.0 && aged<=14.0){
            dosage="400";
        }else if(aged>14.0 && aged<=15.0){
            dosage="450";
        }else if(aged>15.0 && aged<=17.0){
            dosage="500";
        }else if(aged>17.0 && aged<=19.0){
            dosage="550";
        }else if(aged>19.0 && aged<=20.0){
            dosage="600";
        }else{
            dosage="N/A";
        }
        step1=("Step 1: "+dosage+" mls Ringer�s");
        return step1;
    }
    ///plan c step 2
    private String  calculateRingerStep2(Double weight,String aged) {

        if  (aged.equalsIgnoreCase("Age<12m")){
            if (weight<=2.0){
                drops="10";
                dosage="150";
            }else if(weight>2.0 && weight<=3.0){
                drops="13";
                dosage="200";
            }else if(weight>3.0 && weight<=4.0){
                drops="20";
                dosage="300";
            }else if(weight>4.0 && weight<=6.0){
                drops="27";
                dosage="400";
            }else if(weight>6.0 && weight<=8.0){
                drops="33";
                dosage="500";
            }else if(weight>8.0 && weight<=9.0){
                drops="40";
                dosage="600";
            }else if(weight>9.0 && weight<=10.0){
                drops="50";
                dosage="700";
            }else if(weight>10.0 && weight<=12.0){
                drops="55";
                dosage="800";
            }else if(weight>12.0 && weight<=13.0){
                drops="60";
                dosage="900";
            }else if(weight>13.0 && weight<=15.0){
                drops="66";
                dosage="1000";
            }else if(weight>15.0 && weight<=16.0){
                drops="75";
                dosage="1100";
            }else if(weight>16.0 && weight<=18.0){
                drops="80";
                dosage="1200";
            }else if(weight>18.0 && weight<=19.0){
                drops="90";
                dosage="1300";
            }else if(weight>19.0 && weight<=20.0){
                drops="95";
                dosage="1400";
            }else{
                drops="N/A";
                dosage="N/A";
            }
            step2= "Step 2: "+drops+" drops/min** "+ "\n"+dosage+" mls Ringer�s or ng ORS";
            return step2;

        }else if (aged.equalsIgnoreCase("Age>12m")){

            if (weight<=2.0){
                drops="20";
                dosage="150";
            }else if(weight>2.0 && weight<=3.0){
                drops="20";
                dosage="200";
            }else if(weight>3.0 && weight<=4.0){
                drops="20";
                dosage="300";
            }else if(weight>4.0 && weight<=6.0){
                drops="55";
                dosage="400";
            }else if(weight>6.0 && weight<=8.0){
                drops="66";
                dosage="500";
            }else if(weight>8.0 && weight<=9.0){
                drops="80";
                dosage="600";
            }else if(weight>9.0 && weight<=10.0){
                drops="100";
                dosage="700";
            }else if(weight>10.0 && weight<=12.0){
                drops="110";
                dosage="800";
            }else if(weight>12.0 && weight<=13.0){
                drops="120";
                dosage="900";
            }else if(weight>13.0 && weight<=15.0){
                drops="135";
                dosage="1000";
            }else if(weight>15.0 && weight<=16.0){
                drops="150";
                dosage="1100";
            }else if(weight>16.0 && weight<=18.0){
                drops="160";
                dosage="1200";
            }else if(weight>18.0 && weight<=19.0){
                drops="180";
                dosage="1300";
            }else if(weight>19.0 && weight<=20.0){
                drops="190";
                dosage="1400";
            }else{
                drops="N/A";
                dosage="N/A";
            }

        }

        //step2 ="Step 2: "+drops+" drops/min** "+ "\n"+dosage+" mls Ringer�s or ng ORS";

        step2 ="Step 2:" +dosage+" mls Ringer�s \n "+drops+" drops/min** ";
        return step2;

    }


}
