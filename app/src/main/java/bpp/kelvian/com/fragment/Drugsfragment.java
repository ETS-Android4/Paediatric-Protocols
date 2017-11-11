package bpp.kelvian.com.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;
import bpp.kelvian.com.ClickListener;
import bpp.kelvian.com.DrugActivity1;
import bpp.kelvian.com.FluidinDiarrhoea;
import bpp.kelvian.com.MalnutritionFeeding;
import bpp.kelvian.com.MyAdapter;
import bpp.kelvian.com.PlanAActivity;
import bpp.kelvian.com.PlanCActivity;
import bpp.kelvian.com.ShockActivity;
import bpp.kelvian.com.item;
import bpp.kelvian.com.PlanBActivity;
import bpp.kelvian.com.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by kelvian on 2/10/17.
 */

public class Drugsfragment extends Fragment implements ClickListener {

    static final boolean GRID_LAYOUT = false;
    private List<item> itemList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    private LinearLayout main;
    String drugs[];
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    public Drugsfragment(){}


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        drugs=getResources().getStringArray(R.array.Drugs);
        View view = inflater.inflate(R.layout.fragment_recyclerview, container, false);

        //TextView tv = (TextView) view.findViewById(R.id.title);
        //Typeface myTypeface = Typeface.createFromAsset(getActivity().getAssets(), "ClassicGrotesquePro_Regular.ttf");
        //tv.setTypeface(myTypeface);
        //TextView mTxtTitle = (TextView) view.findViewById(R.id.txtTitle);
        //mTxtTitle.setText(getArguments().getString(TEXT_FRAGMENT));

       // view.setLayoutParams(new ActionBarOverlayLayout.LayoutParams(ActionBarOverlayLayout.LayoutParams.MATCH_PARENT, ActionBarOverlayLayout.LayoutParams.MATCH_PARENT ));
        return view;

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
            Intent intent = new Intent(getActivity(), DrugActivity1.class);
            getActivity().startActivity(intent);
        }else if(position == 1) {
            Intent intent = new Intent(getActivity(), MaintenanceFluids.class);
            getActivity().startActivity(intent);
        }else if(position == 2) {
            Intent intent = new Intent(getActivity(), ShockActivity.class);
            getActivity().startActivity(intent);
        }else if(position == 3) {
            Intent intent = new Intent(getActivity(), PlanAActivity.class);
            getActivity().startActivity(intent);
        }else if(position == 4) {
            Intent intent = new Intent(getActivity(), PlanBActivity.class);
            getActivity().startActivity(intent);
        }else if(position == 5) {
            Intent intent = new Intent(getActivity(), PlanCActivity.class);
            getActivity().startActivity(intent);
        }else if(position == 6) {
            Intent intent = new Intent(getActivity(), FluidinDiarrhoea.class);
            getActivity().startActivity(intent);
        }else if(position == 7) {
            Intent intent = new Intent(getActivity(), MalnutritionFeeding.class);
            getActivity().startActivity(intent);
        }else if(position == 8) {
            Intent intent = new Intent(getActivity(), AnthropometrySlideActivity.class);
            getActivity().startActivity(intent);
        }
        else {
            System.out.println("position...."+position);
        }
    }

    

    public static Drugsfragment newInstance() {
        return new Drugsfragment();
    }

}
