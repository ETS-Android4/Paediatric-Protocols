package com.mwongera.paediatric_protocols;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.mwongera.paediatric_protocols.fragment.RssFragment;

/**
 * Created by mwongera on 4/27/17.
 */

public class RssActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rss_main);

        if (savedInstanceState == null) {
            addRssFragment();
        }
    }

    private void addRssFragment() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        RssFragment fragment = new RssFragment();
        transaction.add(R.id.fragment_container, fragment);
        transaction.commit();
    }

}
