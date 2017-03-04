package com.mwongera.paediatric_protocols;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by mwongera on 3/5/17.
 */

public class Malnutrition extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_malnutrition);
        ListView ls=getListView();
        String []mal=getResources().getStringArray(R.array.malnutrition);
        setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mal));
        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#f9a614")));
    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        // TODO Auto-generated method stub
        super.onListItemClick(l, v, position, id);
        openWorkplace(position);

    }
    private void openWorkplace(int position) {
        switch(position){
            case 0:
                Intent i= new Intent(Malnutrition.this,Anthropometry.class);
                startActivity(i);
                break;
            case 1:
                Intent ii= new Intent(Malnutrition.this,ManagementinSAM.class);
                startActivity(ii);
                break;
            case 2:
                Intent iii= new Intent(Malnutrition.this,FluidinDiarrhoea.class);
                startActivity(iii);
                break;
            case 3:
                Intent iv= new Intent(Malnutrition.this,FeedingActivity.class);
                startActivity(iv);
                break;
            default:
                break;
        }
    }

}
