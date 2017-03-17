package com.mwongera.paediatric_protocols.fragment;

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

import com.mwongera.paediatric_protocols.R;

import java.text.DecimalFormat;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by mwongera on 2/22/17.
 */
public class MaintenanceFluids extends Fragment{


    ImageView imgv;
    AlertDialog dialog;

    PhotoViewAttacher mAttacher;
    double weight;
    String weighing;
    DecimalFormat df = new DecimalFormat("###.##");
    EditText inputWeight;
    TextView txtShock,txtMaintaince,txtAlternative,txtbolus;

    Button btn1,btn2;
    String results1,results2,results3,results4;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_maintainace_fluids, container, false);
        inputWeight=(EditText) rootView.findViewById(R.id.editText1);
        txtShock=(TextView) rootView.findViewById(R.id.textShock);
        txtMaintaince=(TextView) rootView.findViewById(R.id.textMaintainance);
        txtAlternative=(TextView) rootView.findViewById(R.id.textAlternative);
        txtbolus=(TextView) rootView.findViewById(R.id.textBolus);
        btn1=(Button)rootView.findViewById(R.id.button1);
        btn2=(Button)rootView.findViewById(R.id.button2);
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
                    Toast.makeText(getActivity(), "Enter details correctly",Toast.LENGTH_SHORT).show();
                }


            }




        });
        btn2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                txtShock.setText("");
                txtAlternative.setText("");
                txtMaintaince.setText("");
                inputWeight.setText("");
                txtbolus.setText("");

            }
        });



        return rootView;
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
