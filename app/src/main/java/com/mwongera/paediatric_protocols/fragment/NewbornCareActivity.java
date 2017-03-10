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

import com.mwongera.paediatric_protocols.NeonatalJaundice;
import com.mwongera.paediatric_protocols.NeonatalSepsis;
import com.mwongera.paediatric_protocols.NewBornFluids;
import com.mwongera.paediatric_protocols.R;
import com.mwongera.paediatric_protocols.Resuscitation;

/**
 * Created by mwongera on 3/5/17.
 */

public class NewbornCareActivity extends Fragment {

    String drugs[];
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_new_born_care, container, false);

        ListView lst= (ListView)rootView.findViewById(R.id.listView1);
        drugs=getResources().getStringArray(R.array.newborncare);
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

        if (ps == 0) {
            Intent i = new Intent(getActivity(), Resuscitation.class);
            startActivity(i);

        } else if (ps == 1) {
            Intent i = new Intent(getActivity(), NeonatalSepsis.class);
            startActivity(i);
        }else if (ps == 2) {
            Intent i = new Intent(getActivity(), NeonatalJaundice.class);
            startActivity(i);
        }else if (ps == 3) {
            Intent i = new Intent(getActivity(), NewBornFluids.class);
            startActivity(i);;
        }
    }

}
