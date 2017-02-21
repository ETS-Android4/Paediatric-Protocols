package com.mwongera.paediatric_protocols.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;
import com.mwongera.paediatric_protocols.ClickListener;
import com.mwongera.paediatric_protocols.Main2Activity;
import com.mwongera.paediatric_protocols.MyAdapter;
import com.mwongera.paediatric_protocols.R;
import com.mwongera.paediatric_protocols.item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mwongera on 2/10/17.
 */

public class Drugsfragment extends Fragment{

    String drugs[];
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
