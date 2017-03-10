package com.mwongera.paediatric_protocols;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mwongera.paediatric_protocols.fragment.AnthropometryWeightE;
import com.mwongera.paediatric_protocols.fragment.MajorIllnessActivity;
import com.mwongera.paediatric_protocols.fragment.Procedures;
import com.mwongera.paediatric_protocols.fragment.ResusciationEmergencyCare;
import com.mwongera.paediatric_protocols.fragment.TriageOfSickChildren;

/**
 * Created by mwongera on 3/5/17.
 */

public class TableActivity extends ListActivity {

    String items[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_table);
        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#f9a614")));
        ListView lst = getListView();
        items = getResources().getStringArray(R.array.TOC);
        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, items));

    }



    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        // TODO Auto-generated method stub
        super.onListItemClick(l, v, position, id);
        openWorkplace(position);

    }

    private void openWorkplace(int ps) {

        if (ps == 4) {
            Intent i = new Intent(this, MajorIllnessActivity.class);
            startActivity(i);

        }else if(ps==0){
            Intent i = new Intent(this, DrugsActivity.class);
            startActivity(i);

        }else if(ps==5){
            Intent i = new Intent(this, NewbornCare.class);
            startActivity(i);

        }else if(ps==6){
            Intent i = new Intent(this, Procedures.class);
            startActivity(i);

        }else if(ps==3){
            Intent i = new Intent(this, ResusciationEmergencyCare.class);
            startActivity(i);

        }else if(ps==7){
            Intent i = new Intent(this, AnthropometryWeightE.class);
            startActivity(i);

        }else if(ps==2){
            Intent i = new Intent(this, TriageOfSickChildren.class);
            startActivity(i);

        }
    }


}
