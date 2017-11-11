package bpp.kelvian.com;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kelvian on 5/5/17.
 */

public class Instructions extends AppCompatActivity{
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instuctions);


        ListView lvItems = (ListView) findViewById(R.id.lv_items);
        ExpandableAdapter adapter = getAdapter();

        lvItems.setAdapter(adapter);
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ExpandableAdapter adapter = (ExpandableAdapter) parent.getAdapter();

                item1 item = (item1) adapter.getItem(position);
                if(item != null){
                    if(item.isExpanded){
                        item.isExpanded = false;

                    }else{
                        item.isExpanded = true;
                    }
                }

                adapter.notifyDataSetChanged();
            }
        });
        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
    }


    private ExpandableAdapter getAdapter(){

        List<item1> items = new ArrayList<>();

        for(int i = 0; i < 50; i++){
            item1 item = new item1();
            item.title = "Title Item " + i;
            item.description = "Description for Title Item "+ i;
            item.isExpanded = false;

            items.add(item);
        }

        return new ExpandableAdapter(this, items);
    }

}
