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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

/**
 * Created by mwongera on 3/5/17.
 */

public class PlanAActivity extends Activity {

    Button btn,btn1;
    TextView results;
    EditText inputweight;
    WebView webv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_a);
       // getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#f9a614")));
        final String customHtml ="<html><heading>Plan A </heading><p> 1) Continue breast feeding and encourage feeding if > 6 months";
        webv=(WebView)findViewById(R.id.webView1);

        results=(TextView)findViewById(R.id.textResults);

        inputweight=(EditText)findViewById(R.id.weight);
        btn=(Button)findViewById(R.id.button1);
        //btn1=(Button)findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Double weight;
                Double resulting;

                if (inputweight.getText().toString().trim().length()!=0){
                    removetheKeyboard();
                    weight= Double.parseDouble(inputweight.getText().toString());
                    resulting=weight*10;
                    results.setText(resulting+ " mls ORS after each stool ");
                    //notes.loadData(Noted, "text/html", "UTF-8");
                    webv.loadData(customHtml, "text/html", "UTF-8");

                }else{

                    Toast.makeText(getApplicationContext(), "Input the Weight", Toast.LENGTH_SHORT).show();

                }


            }
        });

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-3940256099942544~3347511713");

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }
    public void removetheKeyboard(){
        //First remove the virtual keyboad.


        InputMethodManager imm = (InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(inputweight.getWindowToken(), 0);
    }

}
