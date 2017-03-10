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

import com.mwongera.paediatric_protocols.Asthma;
import com.mwongera.paediatric_protocols.Convulsions;
import com.mwongera.paediatric_protocols.HIV;
import com.mwongera.paediatric_protocols.MajorIllnessDescriptionActivity;
import com.mwongera.paediatric_protocols.Malaria;
import com.mwongera.paediatric_protocols.Malnutrition;
import com.mwongera.paediatric_protocols.Meningitis;
import com.mwongera.paediatric_protocols.Pneumonia;
import com.mwongera.paediatric_protocols.R;
import com.mwongera.paediatric_protocols.Tuberculosis;

/**
 * Created by mwongera on 3/4/17.
 */

public class MajorIllnessActivity extends Fragment{

    String drugs[];
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_drugs, container, false);

        ListView lst= (ListView)rootView.findViewById(R.id.listView1);
        drugs=getResources().getStringArray(R.array.MajorIllness);
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
            Intent i = new Intent(getActivity(), MajorIllnessDescriptionActivity.class);
            startActivity(i);

        } else if (ps == 1) {
            Intent i = new Intent(getActivity(), Convulsions.class);
            startActivity(i);
        }else if (ps == 2) {
            Intent i = new Intent(getActivity(), Malnutrition.class);
            startActivity(i);
        }else if (ps == 3) {
            Intent i = new Intent(getActivity(), Meningitis.class);
            startActivity(i);
        }else if (ps == 4) {
            Intent i = new Intent(getActivity(), Pneumonia.class);
            startActivity(i);
        }else if (ps == 5) {
            Intent i = new Intent(getActivity(), Asthma.class);
            startActivity(i);
        }else if (ps == 6) {
            Intent i = new Intent(getActivity(), Tuberculosis.class);
            startActivity(i);
        }else if (ps == 7) {
            Intent i = new Intent(getActivity(), Malaria.class);
            startActivity(i);
        }else if (ps == 8) {
            Intent i = new Intent(getActivity(), HIV.class);
            startActivity(i);
        }
    }


}
