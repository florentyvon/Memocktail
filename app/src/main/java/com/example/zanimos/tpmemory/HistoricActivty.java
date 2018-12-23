package com.example.zanimos.tpmemory;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;


public class HistoricActivty extends AppCompatActivity {

    private SectionsPageAdapter mSectionPageAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historic);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        Button btn_validate;

        mSectionPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        setupViewPager(mViewPager);
        mViewPager.setOffscreenPageLimit(2);
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new TabFragment(), "mojito");
        adapter.addFragment(new TabFragment(), "daiquiri");
        adapter.addFragment(new TabFragment(), "pina colada");
        viewPager.setAdapter(adapter);
    }
}
