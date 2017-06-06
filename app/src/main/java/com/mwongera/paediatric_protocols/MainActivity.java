package com.mwongera.paediatric_protocols;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.heinrichreimersoftware.materialdrawer.DrawerActivity;
import com.heinrichreimersoftware.materialdrawer.structure.DrawerFragmentItem;
import com.heinrichreimersoftware.materialdrawer.structure.DrawerItem;
import com.heinrichreimersoftware.materialdrawer.structure.DrawerProfile;
import com.heinrichreimersoftware.materialdrawer.theme.DrawerTheme;
import com.mwongera.paediatric_protocols.fragment.Drugsfragment;
import com.mwongera.paediatric_protocols.fragment.MajorConditionsFragment;
import com.mwongera.paediatric_protocols.fragment.NewbornCareFragment;
import com.mwongera.paediatric_protocols.fragment.NewsFragment;
import com.mwongera.paediatric_protocols.fragment.RssFragment;
import com.mwongera.paediatric_protocols.fragment.TriageFragment;

public class MainActivity extends DrawerActivity {

    private DrawerLayout mDrawerLayout;
    private MaterialViewPager mViewPager;
    private Toolbar toolbar;
    private ActionBarDrawerToggle mDrawerToggle;

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
                        .setTextPrimary(getString(R.string.drugs_fragment))
                        .setImage(ContextCompat.getDrawable(this, R.drawable.pills2))
                        .setOnItemClickListener(new DrawerItem.OnItemClickListener() {
                            @Override
                            public void onClick(DrawerItem drawerItem, long id, int position) {
                                Intent i = new Intent(MainActivity.this, Drugs_drawer.class);
                                startActivity(i);
                            }
                        })
                ,
                new DrawerItem()
                        .setTextPrimary(getString(R.string.triage_fragment))
                        .setImage(ContextCompat.getDrawable(this, R.drawable.medicalsignal))
                        .setOnItemClickListener(new DrawerItem.OnItemClickListener() {
                            @Override
                            public void onClick(DrawerItem drawerItem, long id, int position) {
                                Intent i = new Intent(MainActivity.this, Triage_Drawer.class);
                                startActivity(i);
                            }
                        })
                ,
                new DrawerItem()
                        .setTextPrimary(getString(R.string.major_conditions_fragment))
                        .setImage(ContextCompat.getDrawable(this, R.drawable.heart2))
                        .setOnItemClickListener(new DrawerItem.OnItemClickListener() {
                            @Override
                            public void onClick(DrawerItem drawerItem, long id, int position) {
                                Intent i = new Intent(MainActivity.this, MajorConditions_drawer.class);
                                startActivity(i);
                            }
                        })
                ,
                new DrawerItem()
                        .setTextPrimary(getString(R.string.newborn_care_fragment))
                        .setImage(ContextCompat.getDrawable(this, R.drawable.mother))
                        .setOnItemClickListener(new DrawerItem.OnItemClickListener() {
                            @Override
                            public void onClick(DrawerItem drawerItem, long id, int position) {
                                Intent i = new Intent(MainActivity.this, Newborn_Drawer.class);
                                startActivity(i);
                            }
                        })
                ,
                new DrawerItem()
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
                        .setImage(ContextCompat.getDrawable(this, R.drawable.ic_action_info))
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
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.finestWhite,
                                "https://wallpaperscraft.com/image/medicine_pharmacy_pills_fake_law_98419_1920x1080.jpg");
                    case 1:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.finestWhite,
                                "http://cdn.playbuzz.com/cdn/e654eda5-1512-49b8-a4a9-19fffc103bfa/008ea10b-81cc-4167-8944-7031900e19d8.png");
                    case 2:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.finestWhite,
                                "https://www.premiumswitzerland.com/img/image_db/dermatology_main-1920.jpg");
                    case 3:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.finestWhite,
                                "http://cdn.wall-pix.net/albums/art-photography/00005471.jpg");
                }
                return null;
            }
        });

        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());
        mViewPager.getPagerTitleStrip().setTextColor(Color.WHITE);

        new AppEula(this).show();

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
