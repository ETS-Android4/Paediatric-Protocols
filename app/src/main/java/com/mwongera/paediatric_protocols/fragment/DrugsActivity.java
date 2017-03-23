package com.mwongera.paediatric_protocols.fragment;


import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mwongera.paediatric_protocols.DrugDescriptionActivity;
import com.mwongera.paediatric_protocols.R;
import com.mwongera.paediatric_protocols.item;

import java.util.ArrayList;

/**
 * Created by mwongera on 3/11/17.
 */

public class DrugsActivity extends ListActivity {


    String drugs[];
    ArrayList<item> arrayList = new ArrayList<item>();
    ListView lst;
    @Override
     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_drugs);

        lst=getListView();
        drugs=getResources().getStringArray(R.array.Drugs);
        setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,drugs));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        // TODO Auto-generated method stub
        super.onListItemClick(l, v, position, id);
        openWorkplace(position);

    }



    private void openWorkplace(int ps) {
        Intent i = new Intent(DrugsActivity.this, DrugDescriptionActivity.class);
        String drugtype=drugs[ps];
        i.putExtra("drugtype", drugtype);
        i.putExtra("position", ps+"");
        //Toast.makeText(getApplicationContext(), drugtype+" "+ps , Toast.LENGTH_LONG).show();
        startActivity(i);


    }


}
