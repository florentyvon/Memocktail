package com.example.zanimos.tpmemory;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;


public class HistoricActivity extends AppCompatActivity {

    private ViewPager viewPager = null;
    private TabLayout tabLayout = null;
    private MyFragmentPagerAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Mémocktail - Historique");
        setContentView(R.layout.activity_historic);
        initComponents();
    }

    @Override
    protected void onStart() {
        super.onStart();

        adapter = new MyFragmentPagerAdapter(this, getSupportFragmentManager());
        viewPager.setOffscreenPageLimit(adapter.getCount() -1);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initComponents()
    {
        viewPager = (ViewPager) findViewById(R.id.viewerpager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
    }
}
