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

import com.mwongera.paediatric_protocols.fragment.AnthropometrySlideActivity;
import com.mwongera.paediatric_protocols.fragment.DrugsActivity;
import com.mwongera.paediatric_protocols.fragment.MaintenanceFluids;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mwongera on 3/30/17.
 */

public class Newborn_Drawer extends AppCompatActivity implements ClickListener {


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
        item item = new item(R.drawable.mother,"Resuscitation");
        itemList.add(item);
        item = new item(R.drawable.mother,"Fluids & Feeding");
        itemList.add(item);
        item = new item(R.drawable.mother,"Sepsis");
        itemList.add(item);
        item= new item(R.drawable.mother,"Jaundice");
        itemList.add(item);
        item = new item(R.drawable.mother,"CPAP");
        itemList.add(item);
        //mAdapter.notifyDataSetChanged();
    }
    @Override
    public void itemClicked(View view, int position) {
        if(position == 0) {
            Intent intent = new Intent(this, Resuscitation.class);
            startActivity(intent);
        }else if(position == 1) {
            Intent intent = new Intent(this, NewBornFluids.class);
            startActivity(intent);
        }else if(position == 2) {
            Intent intent = new Intent(this, NeonatalSepsis.class);
            startActivity(intent);
        }else if(position == 3) {
            Intent intent = new Intent(this, NeonatalJaundice.class);
            startActivity(intent);
        }else {
            System.out.println("position...."+position);
        }
    }


}
