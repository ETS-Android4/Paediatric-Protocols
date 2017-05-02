package com.mwongera.paediatric_protocols;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.mwongera.paediatric_protocols.fragment.HivSlideActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mwongera on 3/30/17.
 */

public class MajorConditions_drawer extends AppCompatActivity implements ClickListener {

    private List<item> itemList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    private LinearLayout main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drugs_drawer);
        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);

        prepareItem();

        mAdapter = new MyAdapter(itemList);
        mAdapter.setClickListener(this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
    }

    private void prepareItem() {
        item item = new item(R.drawable.heart2,"Asthma");
        itemList.add(item);
        item = new item(R.drawable.heart2,"Convulsions");
        itemList.add(item);
        item = new item(R.drawable.heart2,"Diarrhoea/Dehydration");
        itemList.add(item);
        item= new item(R.drawable.heart2,"HIV");
        itemList.add(item);
        item = new item(R.drawable.heart2,"Malaria");
        itemList.add(item);
        item = new item(R.drawable.heart2,"Malnutrition");
        itemList.add(item);
        item= new item(R.drawable.heart2,"Meningitis");
        itemList.add(item);
        item= new item(R.drawable.heart2,"Pneumonia");
        itemList.add(item);
        item= new item(R.drawable.heart2,"Pulmonary Tuberculosis");
        itemList.add(item);
        //mAdapter.notifyDataSetChanged();
    }
    @Override
    public void itemClicked(View view, int position) {
        if(position == 0) {
            Intent intent = new Intent(this, Asthma.class);
            startActivity(intent);
        }else if(position == 1) {
            Intent intent = new Intent(this, Convulsions.class);
            startActivity(intent);
        } else if(position == 2) {
            Intent intent = new Intent(this, MajorIllnessDescriptionActivity.class);
            startActivity(intent);
        } else if(position == 3) {
            Intent intent = new Intent(this, HivSlideActivity.class);
            startActivity(intent);
        } else if(position == 4) {
            Intent intent = new Intent(this, Malaria.class);
            startActivity(intent);
        }else if(position == 5) {
            Intent intent = new Intent(this, ManagementinSAM.class);
            startActivity(intent);
        }else if(position == 6) {
            Intent intent = new Intent(this, Meningitis.class);
            startActivity(intent);
        }else if(position == 7) {
            Intent intent = new Intent(this, Pneumonia.class);
            startActivity(intent);
        }else if(position == 8) {
            Intent intent = new Intent(this, Tuberculosis.class);
            startActivity(intent);
        }
        else {
            System.out.println("position...."+position);
        }
    }

}
