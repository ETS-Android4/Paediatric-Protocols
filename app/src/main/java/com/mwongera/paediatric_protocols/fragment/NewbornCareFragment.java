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
import com.mwongera.paediatric_protocols.MyAdapter;
import com.mwongera.paediatric_protocols.NeonatalJaundice;
import com.mwongera.paediatric_protocols.NeonatalSepsis;
import com.mwongera.paediatric_protocols.NewBornFluids;
import com.mwongera.paediatric_protocols.NewbornCare;
import com.mwongera.paediatric_protocols.R;
import com.mwongera.paediatric_protocols.Resuscitation;
import com.mwongera.paediatric_protocols.item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mwongera on 2/10/17.
 */

public class NewbornCareFragment extends Fragment implements ClickListener {

    static final boolean GRID_LAYOUT = false;
    private List<item> itemList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    private LinearLayout main;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
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
        item item = new item(R.drawable.mother,"Resuscitation");
        itemList.add(item);
        item = new item(R.drawable.mother,"Fluids & Feeding");
        itemList.add(item);
        item = new item(R.drawable.signs,"Sepsis");
        itemList.add(item);
        item= new item(R.drawable.signs,"Jaundice");
        itemList.add(item);
        item = new item(R.drawable.signs,"CPAP");
        itemList.add(item);

        //mAdapter.notifyDataSetChanged();
    }
    @Override
    public void itemClicked(View view, int position) {
        if(position == 0) {
            Intent intent = new Intent(getActivity(), Resuscitation.class);
            getActivity().startActivity(intent);
        }else if(position == 1) {
            Intent intent = new Intent(getActivity(), NewBornFluids.class);
            getActivity().startActivity(intent);
        }else if(position == 2) {
            Intent intent = new Intent(getActivity(), NeonatalSepsis.class);
            getActivity().startActivity(intent);
        }else if(position == 3) {
            Intent intent = new Intent(getActivity(), NeonatalJaundice.class);
            getActivity().startActivity(intent);
        }else {
            System.out.println("position...."+position);
        }
    }

    public static NewbornCareFragment newInstance() {return new NewbornCareFragment();
    }

}
