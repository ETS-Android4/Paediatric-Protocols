package com.mwongera.paediatric_protocols.fragment;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.SearchView;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mwongera.paediatric_protocols.DrugDescriptionActivity;
import com.mwongera.paediatric_protocols.R;
import com.mwongera.paediatric_protocols.item;

import java.util.ArrayList;

/**
 * Created by mwongera on 3/11/17.
 */

public class DrugsActivity extends ListActivity implements SearchView.OnQueryTextListener {


    String drugs[];
    ListView lst;
    private SearchView mSearchView;

    @Override
     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_drugs);

        mSearchView = (SearchView) findViewById(R.id.search_view);

        lst=getListView();
        drugs=getResources().getStringArray(R.array.Drugs);
        setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,drugs));
        lst.setTextFilterEnabled(true);
        setupSearchView();
    }

    private void setupSearchView() {
        mSearchView.setIconifiedByDefault(false);
        mSearchView.setOnQueryTextListener(this);
        mSearchView.setSubmitButtonEnabled(false);
        mSearchView.setQueryHint("Search");
    }

    public boolean onQueryTextChange(String newText) {
        if (TextUtils.isEmpty(newText)) {
            lst.clearTextFilter();
        } else {
            lst.setFilterText(newText.toString());
        }
        return true;
    }

    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        // TODO Auto-generated method stub
        super.onListItemClick(l, v, position, id);
        openWorkplace(position);

    }



    private void openWorkplace(int ps) {
        Intent i = new Intent(DrugsActivity.this, DrugDescriptionActivity.class);
        String drugtype=drugs[ps];
        i.putExtra("drugtype", drugtype);
        i.putExtra("position", ps+"");
        //Toast.makeText(getApplicationContext(), drugtype+" "+ps , Toast.LENGTH_LONG).show();
        startActivity(i);


    }


}
