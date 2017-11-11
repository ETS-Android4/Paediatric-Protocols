package bpp.kelvian.com.fragment;

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
import bpp.kelvian.com.Asthma;
import bpp.kelvian.com.ClickListener;
import bpp.kelvian.com.Convulsions;
import bpp.kelvian.com.MajorIllnessDescriptionActivity;
import bpp.kelvian.com.Malaria;
import bpp.kelvian.com.Meningitis;
import bpp.kelvian.com.MyAdapter;
import bpp.kelvian.com.Pneumonia;
import bpp.kelvian.com.Tuberculosis;
import bpp.kelvian.com.item;
import bpp.kelvian.com.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kelvian on 3/4/17.
 */

public class MajorConditionsFragment extends Fragment implements ClickListener {

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
            Intent intent = new Intent(getActivity(), Asthma.class);
            getActivity().startActivity(intent);
        }else if(position == 1) {
            Intent intent = new Intent(getActivity(), Convulsions.class);
            getActivity().startActivity(intent);
        } else if(position == 2) {
            Intent intent = new Intent(getActivity(), MajorIllnessDescriptionActivity.class);
            getActivity().startActivity(intent);
        } else if(position == 3) {
            Intent intent = new Intent(getActivity(), HivSlideActivity.class);
            getActivity().startActivity(intent);
        } else if(position == 4) {
            Intent intent = new Intent(getActivity(), Malaria.class);
            getActivity().startActivity(intent);
        }else if(position == 5) {
            Intent intent = new Intent(getActivity(), managementSamSlideActivity.class);
            getActivity().startActivity(intent);
        }else if(position == 6) {
            Intent intent = new Intent(getActivity(), Meningitis.class);
            getActivity().startActivity(intent);
        }else if(position == 7) {
            Intent intent = new Intent(getActivity(), Pneumonia.class);
            getActivity().startActivity(intent);
        }else if(position == 8) {
            Intent intent = new Intent(getActivity(), Tuberculosis.class);
            getActivity().startActivity(intent);
        }
        else {
            System.out.println("position...."+position);
        }
    }

    public static MajorConditionsFragment newInstance() {
        return new MajorConditionsFragment();
    }


}
