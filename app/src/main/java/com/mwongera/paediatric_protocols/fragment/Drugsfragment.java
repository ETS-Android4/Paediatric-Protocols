package com.mwongera.paediatric_protocols.fragment;

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
import android.widget.LinearLayout;

import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;
import com.mwongera.paediatric_protocols.ClickListener;
import com.mwongera.paediatric_protocols.DrugDescriptionActivity;
import com.mwongera.paediatric_protocols.MyAdapter;
import com.mwongera.paediatric_protocols.R;
import com.mwongera.paediatric_protocols.Resuscitation;
import com.mwongera.paediatric_protocols.item;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by mwongera on 2/10/17.
 */

public class Drugsfragment extends Fragment implements ClickListener {

    static final boolean GRID_LAYOUT = false;
    private List<item> itemList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    private LinearLayout main;
    String drugs[];

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        drugs=getResources().getStringArray(R.array.Drugs);
        return inflater.inflate(R.layout.fragment_recyclerview, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager;

        prepareItem();

        if (GRID_LAYOUT) {
            layoutManager = new GridLayoutManager(getActivity(), 2);
        } else {
            layoutManager = new LinearLayoutManager(getActivity());
        }
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        //Use this now
        recyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());

        mAdapter = new MyAdapter(itemList);
        mAdapter.setClickListener(this);

        //mAdapter = new RecyclerViewMaterialAdapter();
        recyclerView.setAdapter(mAdapter);
    }

    private void prepareItem() {
        item item = new item("Essential Drugs", "");
        itemList.add(item);
        item = new item("Maintenance Fluids", "");
        itemList.add(item);
        item = new item("Dehydration-Shock management", "");
        itemList.add(item);
        item= new item("Dehydration-Plan A", "");
        itemList.add(item);
        item = new item("Dehydration-Plan B", "");
        itemList.add(item);
        item = new item("Dehydration-Plan C", "");
        itemList.add(item);
        item= new item("Malnutrition-Fluid management", "");
        itemList.add(item);
        item= new item("Malnutrition-Feeding", "");
        itemList.add(item);
        item= new item("Anthropometry","");
        itemList.add(item);

        //mAdapter.notifyDataSetChanged();
    }

    @Override
    public void itemClicked(View view, int position) {
        if(position == 2) {
            Intent intent = new Intent(getActivity(), Resuscitation.class);
            getActivity().startActivity(intent);
        } else {
            System.out.println("position...."+position);
        }
    }


    public static Drugsfragment newInstance() {
        return new Drugsfragment();
    }

}
