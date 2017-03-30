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
        item item = new item(R.drawable.two,"Cake1");
        itemList.add(item);
        item = new item(R.drawable.three,"Cake2");
        itemList.add(item);
        item = new item(R.drawable.four,"Cake3");
        itemList.add(item);
        item= new item(R.drawable.one,"Cake4");
        itemList.add(item);
        item = new item(R.drawable.three,"Cake2");
        itemList.add(item);
        item = new item(R.drawable.four,"Cake3");
        itemList.add(item);
        item= new item(R.drawable.one,"Cake4");
        itemList.add(item);
        //mAdapter.notifyDataSetChanged();
    }
    @Override
    public void itemClicked(View view, int position) {
        if(position == 2) {
            Intent intent = new Intent(MainActivity.this, Main2Activity.class);
            startActivity(intent);
        } else if (position == 4) {
            Intent intent = new Intent(MainActivity.this, Main2Activity.class);
            startActivity(intent);
        }
        else if (position==1){
            Intent intent = new Intent(MainActivity.this, Main2Activity.class);
            startActivity(intent);
        }
        else {
            System.out.println("position...."+position);
        }
    }

}
