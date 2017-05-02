package com.mwongera.paediatric_protocols;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.mwongera.paediatric_protocols.fragment.AnthropometrySlideActivity;
import com.mwongera.paediatric_protocols.fragment.MaintenanceFluids;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mwongera on 3/30/17.
 */

public class Drugs_drawer extends AppCompatActivity implements ClickListener {


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
        item item = new item(R.drawable.pill3,"Essential Drugs");
        itemList.add(item);
        item = new item(R.drawable.signs,"Maintenance Fluids");
        itemList.add(item);
        item = new item(R.drawable.signs,"Dehydration-Shock");
        itemList.add(item);
        item= new item(R.drawable.signs,"Dehydration-Plan A");
        itemList.add(item);
        item = new item(R.drawable.signs,"Dehydration Plan B");
        itemList.add(item);
        item = new item(R.drawable.signs,"Dehydration Plan C");
        itemList.add(item);
        item= new item(R.drawable.signs,"Malnutrition-Fluid management");
        itemList.add(item);
        item= new item(R.drawable.signs,"Malnutrition-Feeding");
        itemList.add(item);
        item= new item(R.drawable.pictures,"Anthropometry");
        itemList.add(item);
        //mAdapter.notifyDataSetChanged();
    }
    @Override
    public void itemClicked(View view, int position) {
        if(position == 0) {
            Intent intent = new Intent(this, DrugActivity1.class);
            startActivity(intent);
        }else if(position == 1) {
            Intent intent = new Intent(this, MaintenanceFluids.class);
            startActivity(intent);
        }else if(position == 2) {
            Intent intent = new Intent(this, ShockActivity.class);
            startActivity(intent);
        }else if(position == 3) {
            Intent intent = new Intent(this, PlanAActivity.class);
            startActivity(intent);
        }else if(position == 4) {
            Intent intent = new Intent(this, PlanBActivity.class);
            startActivity(intent);
        }else if(position == 5) {
            Intent intent = new Intent(this, PlanCActivity.class);
            startActivity(intent);
        }else if(position == 6) {
            Intent intent = new Intent(this, FluidinDiarrhoea.class);
            startActivity(intent);
        }else if(position == 7) {
            Intent intent = new Intent(this, MalnutritionFeeding.class);
            startActivity(intent);
        }else if(position == 8) {
            Intent intent = new Intent(this, AnthropometrySlideActivity.class);
            startActivity(intent);
        }
        else {
            System.out.println("position...."+position);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
