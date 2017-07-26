package com.example.user.mahindraapp.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by user on 30/6/17.
 */

public class ViewPageAdapters extends FragmentPagerAdapter
{
    ArrayList<Fragment> fragments=new ArrayList<>();
    ArrayList<String>tabTitles=new ArrayList<>();

    public ViewPageAdapters(FragmentManager fm)
    {
        super(fm);
    }
    /**
     * Function for adding the diferent fragments along with its
     * name of the tabs that has been added in the activity
     */
    public void addFragments( Fragment fragments,String tabTitles)
    {
        this.fragments.add(fragments);
        this.tabTitles.add(tabTitles);
    }
/**
 * This function returns  the Fragment associated with a specified position.
 * @return position
 *
 *
 */
@Override
public Fragment getItem(int position)
{
    return fragments.get(position);
}

    @Override
    public int getCount()
    {
        return fragments.size();
    }
    /**
     * This function returns  the tabtitle associated with a specified position.
     * @return tabTitles at givenposition
     *
     *
     */
    public CharSequence getPageTitle(int position)
    {
        return tabTitles.get(position);
    }
}
