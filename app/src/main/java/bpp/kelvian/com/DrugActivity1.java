package bpp.kelvian.com;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.analytics.FirebaseAnalytics;

/**
 * Created by kelvian on 3/4/17.
 */

public class DrugActivity1 extends ListActivity {

    String drugs[];
    ListView lst;
    private FirebaseAnalytics mFirebaseAnalytics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drugs);
        //getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#f9a614")));
        lst= getListView();
        drugs=getResources().getStringArray(R.array.Drugs);

        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, drugs));

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
    }

    //	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.drugs, menu);
//		return true;
//	}
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        // TODO Auto-generated method stub
        super.onListItemClick(l, v, position, id);
        openWorkplace(position);

    }

    private void openWorkplace(int ps) {
        Intent i = new Intent(this, DrugDescriptionActivity.class);
        String drugtype=drugs[ps];
        i.putExtra("drugtype", drugtype);
        i.putExtra("position", ps+"");
        //Toast.makeText(getApplicationContext(), drugtype+" "+ps , Toast.LENGTH_LONG).show();
        startActivity(i);


    }

}
