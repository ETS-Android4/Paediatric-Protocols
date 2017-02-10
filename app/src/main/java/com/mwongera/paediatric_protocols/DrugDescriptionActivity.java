package com.mwongera.paediatric_protocols;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.DecimalFormat;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

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
    }

}
