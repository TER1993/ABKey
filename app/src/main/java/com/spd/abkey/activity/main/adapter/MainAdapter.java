package com.spd.abkey.activity.main.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.spd.abkey.AppAbKey;
import com.spd.abkey.R;

import java.util.Arrays;
import java.util.List;

/**
 * @author xuyan
 */
public class MainAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;

    public MainAdapter(FragmentManager fm, List<Fragment> mTabList) {
        super(fm);
        fragments = mTabList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return Arrays.asList(AppAbKey.getInstance().getResources().getStringArray(R.array.main_menu)).get(position);
    }
}
