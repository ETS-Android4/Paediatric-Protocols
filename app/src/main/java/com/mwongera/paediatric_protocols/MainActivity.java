package com.mwongera.paediatric_protocols;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;
import com.heinrichreimersoftware.materialdrawer.DrawerActivity;
import com.heinrichreimersoftware.materialdrawer.structure.DrawerFragmentItem;
import com.heinrichreimersoftware.materialdrawer.structure.DrawerItem;
import com.heinrichreimersoftware.materialdrawer.structure.DrawerProfile;
import com.heinrichreimersoftware.materialdrawer.theme.DrawerTheme;
import com.mwongera.paediatric_protocols.fragment.Drugsfragment;
import com.mwongera.paediatric_protocols.fragment.MajorConditionsFragment;
import com.mwongera.paediatric_protocols.fragment.NewbornCareFragment;
import com.mwongera.paediatric_protocols.fragment.NewsFragment;
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

        setTitle("");

        mViewPager = (MaterialViewPager) findViewById(R.id.materialViewPager);
        toolbar = mViewPager.getToolbar();

        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        setDrawerTheme(
                new DrawerTheme(this)
                        .setBackgroundColorRes(R.color.text_color_primary_3)
                        .setTextColorPrimaryRes(R.color.color_accent)
        );
        addItems(new DrawerFragmentItem()
                        .setFragment(new Drugsfragment())
                        .setTextPrimary(getString(R.string.drugs_fragment))
                        .setImage(ContextCompat.getDrawable(this, R.drawable.pills)),
                new DrawerFragmentItem()
                        .setFragment(new TriageFragment())
                        .setTextPrimary(getString(R.string.triage_fragment))
                        .setImage(ContextCompat.getDrawable(this, R.drawable.hospital)),
                new DrawerFragmentItem()
                        .setFragment(new MajorConditionsFragment())
                        .setTextPrimary(getString(R.string.major_conditions_fragment))
                        .setImage(ContextCompat.getDrawable(this, R.drawable.heart)),
                new DrawerFragmentItem()
                        .setFragment(new NewbornCareFragment())
                        .setTextPrimary(getString(R.string.newborn_care_fragment))
                        .setImage(ContextCompat.getDrawable(this, R.drawable.medicine)),
                new DrawerFragmentItem()
                        .setFragment(new NewsFragment())
                        .setImage(ContextCompat.getDrawable(this, R.drawable.text_lines))
                        .setTextPrimary(getString(R.string.news_fragment))

        );
        setOnItemClickListener(new DrawerItem.OnItemClickListener() {
            @Override
            public void onClick(DrawerItem item, long id, int position) {
                selectItem(position);
            }
        });



        mViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position % 5) {
                    case 0:
                        return Drugsfragment.newInstance();
                    case 1:
                        return TriageFragment.newInstance();
                    case 2:
                        return MajorConditionsFragment.newInstance();
                    case 3:
                        return NewbornCareFragment.newInstance();
                    case 4:
                        return NewsFragment.newInstance();
                    default:
                        return Drugsfragment.newInstance();
                }

            }

            @Override
            public int getCount() {
                return 5;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position % 5) {
                    case 0:
                        return "DRUGS/FLUIDS";
                    case 1:
                        return "TRIAGE/EMERGENCY CARE";
                    case 2:
                        return "MAJOR CONDITIONS";
                    case 3:
                        return "NEWBORN CARE";
                    case 4:
                        return "NEWS";
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
                                R.color.green,
                                "http://cdn1.tnwcdn.com/wp-content/blogs.dir/1/files/2014/06/wallpaper_51.jpg");
                    case 1:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.blue,
                                "http://cdn1.tnwcdn.com/wp-content/blogs.dir/1/files/2014/06/wallpaper_51.jpg");
                    case 2:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.cyan,
                                "http://www.droid-life.com/wp-content/uploads/2014/10/lollipop-wallpapers10.jpg");
                    case 3:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.red,
                                "http://www.tothemobile.com/wp-content/uploads/2014/07/original.jpg");
                    case 4:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.red,
                                "http://www.tothemobile.com/wp-content/uploads/2014/07/original.jpg");
                }
                return null;
            }
        });

        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());

        View logo = findViewById(R.id.logo_white);
        if (logo != null) {
            logo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewPager.notifyHeaderChanged();
                    Toast.makeText(getApplicationContext(), "Yes, the title is clickable", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
