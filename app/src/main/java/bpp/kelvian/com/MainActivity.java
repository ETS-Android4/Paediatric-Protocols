package bpp.kelvian.com;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.heinrichreimersoftware.materialdrawer.DrawerActivity;
import com.heinrichreimersoftware.materialdrawer.structure.DrawerItem;
import com.heinrichreimersoftware.materialdrawer.theme.DrawerTheme;
import bpp.kelvian.com.fragment.TriageFragment;
import bpp.kelvian.com.fragment.Drugsfragment;
import bpp.kelvian.com.fragment.MajorConditionsFragment;
import bpp.kelvian.com.fragment.NewbornCareFragment;

public class MainActivity extends DrawerActivity {

    private DrawerLayout mDrawerLayout;
    private MaterialViewPager mViewPager;
    private Toolbar toolbar;
    private ActionBarDrawerToggle mDrawerToggle;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("PAEDIATRIC PTOTOCOLS - KE");

        mViewPager = (MaterialViewPager) findViewById(R.id.materialViewPager);
        toolbar = mViewPager.getToolbar();

        setSupportActionBar(toolbar);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle=new ActionBarDrawerToggle(this,mDrawerLayout,R.string.drawer_open,R.string.drawer_close){
            public void onDrawerClosed(    View view){
                invalidateOptionsMenu();
            }
            public void onDrawerOpened(    View drawerView){
                invalidateOptionsMenu();
            }
        };

        mDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mDrawerToggle);


        setOnItemClickListener(new DrawerItem.OnItemClickListener() {
            @Override
            public void onClick(DrawerItem item, long id, int position) {
                selectItem(position);
            }
        });

        setDrawerTheme(
                new DrawerTheme(this)
                        .setBackgroundColorRes(R.color.text_color_primary_3)
                        .setTextColorPrimaryRes(R.color.navigationBarColor)
        );
        addItems(new DrawerItem()
                        .setImage(ContextCompat.getDrawable(this, R.drawable.text_lines))
                        .setTextPrimary(getString(R.string.news_fragment))
                        .setOnItemClickListener(new DrawerItem.OnItemClickListener() {
                            @Override
                            public void onClick(DrawerItem drawerItem, long id, int position) {
                                Intent i = new Intent(MainActivity.this, RssActivity.class);
                                startActivity(i);
                            }
                        })
                ,
                new DrawerItem()
                        .setTextPrimary(getString(R.string.about))
                        .setImage(ContextCompat.getDrawable(this, R.drawable.logo))
                        .setOnItemClickListener(new DrawerItem.OnItemClickListener() {
                            @Override
                            public void onClick(DrawerItem drawerItem, long id, int position) {
                                Intent i = new Intent(MainActivity.this, About.class);
                                startActivity(i);
                            }
                        })

        );

        mViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position % 4) {
                    case 0:
                        return Drugsfragment.newInstance();
                    case 1:
                        return TriageFragment.newInstance();
                    case 2:
                        return MajorConditionsFragment.newInstance();
                    case 3:
                        return NewbornCareFragment.newInstance();
                    default:
                        return Drugsfragment.newInstance();
                }

            }

            @Override
            public int getCount() {
                return 4;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position % 4) {
                    case 0:
                        return "DRUGS/FLUIDS";
                    case 1:
                        return "TRIAGE/EMERGENCY CARE";
                    case 2:
                        return "MAJOR CONDITIONS";
                    case 3:
                        return "NEWBORN CARE";
                }
                return "";
            }
        });



        mViewPager.setMaterialViewPagerListener(new MaterialViewPager.Listener() {
            @Override
            public HeaderDesign getHeaderDesign(int page) {
                switch (page) {
                    case 0:
                        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.wallone);
                        Drawable bmDrawable = new BitmapDrawable(getResources(), bm);
                        return HeaderDesign.fromColorResAndDrawable(
                                R.color.finestWhite, bmDrawable);
                    case 1:
                        Bitmap bm1 = BitmapFactory.decodeResource(getResources(), R.drawable.wallfour);
                        Drawable bmDrawable1 = new BitmapDrawable(getResources(), bm1);
                        return HeaderDesign.fromColorResAndDrawable(
                                R.color.finestWhite, bmDrawable1);
                    case 2:
                        Bitmap bm2 = BitmapFactory.decodeResource(getResources(), R.drawable.wallthree);
                        Drawable bmDrawable2 = new BitmapDrawable(getResources(), bm2);
                        return HeaderDesign.fromColorResAndDrawable(
                                R.color.finestWhite, bmDrawable2);
                    case 3:
                        Bitmap bm3 = BitmapFactory.decodeResource(getResources(), R.drawable.wallfive);
                        Drawable bmDrawable3 = new BitmapDrawable(getResources(), bm3);
                        return HeaderDesign.fromColorResAndDrawable(
                                R.color.finestWhite, bmDrawable3);
                }
                return null;
            }
        });

        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());
        mViewPager.getPagerTitleStrip().setTextColor(Color.WHITE);

        new AppEula(this).show();

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

    }

    /**
     * Opens the drawer
     */
    public void openDrawer() {
        mDrawerLayout.openDrawer(mDrawerLayout);
    }

    /**
     * Closes the drawer
     */
    public void closeDrawer() {
        mDrawerLayout.closeDrawer(mDrawerLayout);
    }


}
