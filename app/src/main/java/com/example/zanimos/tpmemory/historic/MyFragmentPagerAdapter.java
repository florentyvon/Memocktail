package com.example.zanimos.tpmemory.historic;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.zanimos.tpmemory.R;
import com.example.zanimos.tpmemory.historic.CocktailScoreFragment;

/***
 * Fragment pager adapter implementation
 * @author Florent Yvon, Julien Raillard, Mickael Meneux
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private Context context;

    /***
     * Constructor
     * @param context : current context
     * @param fm : fragment manager instance
     */
    public MyFragmentPagerAdapter(Context context, FragmentManager fm)
    {
        super(fm);
        this.context = context;
    }

    /***
     * Adapter view getter method
     * @param position : view position in adapter
     * @return Fragment : desired fragment
     */
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

    /***
     * Adapter view count getter method
     * @return int : view number
     */
    @Override
    public int getCount() {
        return 3;
    }

    /***
     * Adapter view title getter method
     * @param position : view position
     * @return CharSequence :  title
     */
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: return context.getString(R.string.virgin_daiquiri);
            case 1: return context.getString(R.string.virgin_mojito);
            case 2: return context.getString(R.string.virgin_pina_colada);
            default: return null;
        }
    }
}
