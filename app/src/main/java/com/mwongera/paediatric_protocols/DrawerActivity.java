package com.mwongera.paediatric_protocols;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.heinrichreimersoftware.materialdrawer.DrawerFrameLayout;

import static com.mwongera.paediatric_protocols.R.layout.drawer;

/**
 * Created by mwongera on 3/20/17.
 */

public class DrawerActivity extends AppCompatActivity {

    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onStart() {
        super.onStart();

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle=new ActionBarDrawerToggle(this,mDrawer,R.string.drawer_open,R.string.drawer_close){
            public void onDrawerClosed(    View view){
                invalidateOptionsMenu();
            }
            public void onDrawerOpened(    View drawerView){
                invalidateOptionsMenu();
            }
        };
        //setSupportActionBar(toolbar);
        mDrawer.setDrawerListener(mDrawerToggle);
        mDrawer.closeDrawer(mDrawer);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setDisplayUseLogoEnabled(false);
            actionBar.setHomeButtonEnabled(true);
        }

    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mDrawerToggle.onOptionsItemSelected(item) ||
                super.onOptionsItemSelected(item);
    }
    private void switchFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();

        if(fragmentManager.findFragmentById(R.id.mdFrame) == fragment) return;

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_NONE);
        if (fragmentManager.findFragmentById(R.id.mdFrame) != null) {
            fragmentTransaction.replace(R.id.mdFrame, fragment);
        }
        else {
            ((ViewGroup)findViewById(R.id.mdFrame)).removeAllViews();
            fragmentTransaction.add(R.id.mdFrame, fragment);
        }
        fragmentTransaction.commit();
    }
    /**
     * Opens the drawer
     */
    public void openDrawer() {
        mDrawer.openDrawer(mDrawer);
    }

    /**
     * Closes the drawer
     */
    public void closeDrawer() {
        mDrawer.closeDrawer(mDrawer);
    }


}
