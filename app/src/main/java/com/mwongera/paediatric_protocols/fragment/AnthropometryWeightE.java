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

import com.mwongera.paediatric_protocols.AnthropometryWeightImages;
import com.mwongera.paediatric_protocols.R;

/**
 * Created by mwongera on 3/4/17.
 */

public class AnthropometryWeightE extends Fragment {

    String drugs[];
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_anthropometry_weight_e, container, false);

        ListView lst = (ListView)rootView.findViewById(R.id.listView1);
        drugs=getResources().getStringArray(R.array.weights);
        ArrayAdapter<String> mine = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,drugs);
        lst.setAdapter(mine);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openWorkplace(position);
            }
        });
        return rootView;
    }
    private void openWorkplace(int ps) {
        Intent i = new Intent(getActivity(), AnthropometryWeightImages.class);

        i.putExtra("image", ps+"");
        startActivity(i);
    }


}
