package com.example.zanimos.tpmemory;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.ImageView;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private Context context;

    public MyFragmentPagerAdapter(Context context, FragmentManager fm)
    {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = null;
        CocktailScoreFragment cocktailFragment = null;
        switch (position)
        {
            case 0:
                bundle = new Bundle();
                bundle.putInt("ID", R.drawable.img_virgin_daiquiri);
                cocktailFragment = new CocktailScoreFragment();
                cocktailFragment.setArguments(bundle);
                return cocktailFragment;
            case 1:
                bundle = new Bundle();
                bundle.putInt("ID", R.drawable.img_virgin_mojito);
                cocktailFragment = new CocktailScoreFragment();
                cocktailFragment.setArguments(bundle);
                return cocktailFragment;
            case 2:
                bundle = new Bundle();
                bundle.putInt("ID", R.drawable.img_virgin_pina_colada);
                cocktailFragment = new CocktailScoreFragment();
                cocktailFragment.setArguments(bundle);

                return cocktailFragment;
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            // TODO : chaines à stocker dans strings.xml
            case 0: return context.getString(R.string.virgin_daiquiri);
            case 1: return context.getString(R.string.virgin_mojito);
            case 2: return context.getString(R.string.virgin_pina_colada);
            default: return null;
        }
    }
}
