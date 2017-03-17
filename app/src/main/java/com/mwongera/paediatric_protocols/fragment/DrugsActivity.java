package com.mwongera.paediatric_protocols.fragment;


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

public class DrugsActivity extends Fragment {


    String drugs[];
    ArrayList<item> arrayList = new ArrayList<item>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_drugs, container, false);

        ListView lst= (ListView)rootView.findViewById(R.id.listView1);
        drugs=getResources().getStringArray(R.array.Drugs);
        ArrayAdapter<String> mine=	new ArrayAdapter<String> (getActivity(),android.R.layout.simple_list_item_1,drugs);
        lst.setAdapter(mine);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //do something
                openWorkplace(position);
            }
        });

        return rootView;
    }



    private void openWorkplace(int ps) {
        Intent i = new Intent(getActivity(), DrugDescriptionActivity.class);
        String drugtype=drugs[ps];
        i.putExtra("drugtype", drugtype);
        i.putExtra("position", ps+"");
        //Toast.makeText(getApplicationContext(), drugtype+" "+ps , Toast.LENGTH_LONG).show();
        startActivity(i);


    }


}
