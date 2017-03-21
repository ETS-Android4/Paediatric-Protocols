package com.mwongera.paediatric_protocols.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.mwongera.paediatric_protocols.R;

/**
 * Created by mwongera on 3/21/17.
 */

public class HivSlideActivity extends FragmentActivity {

    private static final int NUM_PAGES = 2;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_slide);
    }

}
