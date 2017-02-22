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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;
import com.mwongera.paediatric_protocols.ClickListener;
import com.mwongera.paediatric_protocols.DrugDescriptionActivity;
import com.mwongera.paediatric_protocols.Main2Activity;
import com.mwongera.paediatric_protocols.MyAdapter;
import com.mwongera.paediatric_protocols.R;
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
        item item = new item("Albendazole", "");
        itemList.add(item);
        item = new item("Amikacin", "");
        itemList.add(item);
        item = new item("Aminophylline", "");
        itemList.add(item);
        item= new item("Amoxicillin", "");
        itemList.add(item);
        item = new item("Ampicillin", "");
        itemList.add(item);
        item = new item("Artemether - Lumefantrine", "");
        itemList.add(item);
        item= new item("Artemisinin -Piperaquine", "");
        itemList.add(item);
        item= new item("Artemether - Lumefantrine", "");
        itemList.add(item);
        item= new item("Artesunate","");
        itemList.add(item);
        item= new item("Beclomethasone", "");
        itemList.add(item);
        item= new item("Calcium", "");
        itemList.add(item);
        item= new item("Carbamazepine", "");
        itemList.add(item);
        item= new item("Cefotaxime", "");
        itemList.add(item);
        item= new item("Ceftriaxone", "");
        itemList.add(item);
        item= new item("Chloramphenicol", "");
        itemList.add(item);
        item= new item("Ciprofloxacin", "");
        itemList.add(item);
        item= new item("Clotrimazole 1%", "");
        itemList.add(item);
        item= new item("Co-trimoxazole", "");
        itemList.add(item);
        item= new item("Dexamethasone", "");
        itemList.add(item);
        item= new item("Dextrose/glucose", "");
        itemList.add(item);
        item= new item("Dihydrocodeine", "");
        itemList.add(item);
        item= new item("Diazepam (iv)", "");
        itemList.add(item);
        item= new item("Diazepam (rectal)", "");
        itemList.add(item);
        item= new item("Digoxin (oral)", "");
        itemList.add(item);
        item= new item("Flucloxacillin", "");
        itemList.add(item);
        item= new item("Frusemide", "");
        itemList.add(item);
        item= new item("Gentamicin", "");
        itemList.add(item);
        item= new item("Hydroxyurea", "");
        itemList.add(item);
        item= new item("Ibuprofen", "");
        itemList.add(item);
        item= new item("Iron tabs / syrup", "");
        itemList.add(item);
        item= new item("Lactulose", "");
        itemList.add(item);
        item= new item("Mebendazole ", "");
        itemList.add(item);
        item= new item("Metronidazole(oral)", "");
        itemList.add(item);
        item= new item("Morphine", "");
        itemList.add(item);
        item= new item("Multivitamins", "");
        itemList.add(item);
        item= new item("Nystatin", "");
        itemList.add(item);
        item= new item("Paracetamol", "");
        itemList.add(item);
        item= new item("Pethidine, im", "");
        itemList.add(item);
        item= new item("Phenobarbitone", "");
        itemList.add(item);
        item= new item("Phenytoin", "");
        itemList.add(item);
        item= new item("Potassium - oral", "");
        itemList.add(item);
        item= new item("Prednisolone - tabs", "");
        itemList.add(item);
        item= new item("Quinine", "");
        itemList.add(item);
        item= new item("Salbutamol", "");
        itemList.add(item);
        item= new item("Quinine", "");
        itemList.add(item);
        item= new item("TB Treatment", "");
        itemList.add(item);
        item= new item("Valproate(sodium)", "");
        itemList.add(item);
        item= new item("Vitamin A ", "");
        itemList.add(item);
        item= new item("Vitamin D", "");
        itemList.add(item);
        item= new item("Vitamin D – Maintenance", "");
        itemList.add(item);
        item= new item("Vitamin K", "");
        itemList.add(item);
        item= new item("Zinc Sulphate", "");
        itemList.add(item);

        //mAdapter.notifyDataSetChanged();
    }
    @Override
    public void itemClicked(View view, int position) {
       openWorkplace(position);
    }

    private void openWorkplace(int position) {
        Intent i = new Intent(getActivity(), DrugDescriptionActivity.class);
        String drugtype=drugs[position];
        i.putExtra("drugtype", drugtype);
        i.putExtra("position", position+"");
        //Toast.makeText(getApplicationContext(), drugtype+" "+ps , Toast.LENGTH_LONG).show();
        startActivity(i);
    }

    public static Drugsfragment newInstance() {
        return new Drugsfragment();
    }

}
