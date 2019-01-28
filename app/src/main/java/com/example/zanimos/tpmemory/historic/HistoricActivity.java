package com.example.zanimos.tpmemory.historic;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.example.zanimos.tpmemory.R;
import com.example.zanimos.tpmemory.infrastructure.BaseActivity;

/***
 * Historic activity
 * @author Florent Yvon, Julien Raillard, Mickael Meneux
 */
public class HistoricActivity extends BaseActivity {

    private ViewPager viewPager = null;
    private TabLayout tabLayout = null;

    /***
     * onCreate activity event
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historic);
        initComponents();
    }

    /***
     * onStart activity event
     */
    @Override
    protected void onStart() {
        super.onStart();

        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(this, getSupportFragmentManager());
        viewPager.setOffscreenPageLimit(adapter.getCount() -1);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    /***
     * Activity components init
     */
    private void initComponents()
    {
        viewPager = (ViewPager) findViewById(R.id.viewerpager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
    }
}