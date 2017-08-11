package com.holmeslei.movienews.ui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.holmeslei.movienews.ui.fragment.GeneralMovieFragment;

/**
 * Description:
 * author         xulei
 * Date           2017/8/11
 */

public class MainViewPagerAdapter extends FragmentPagerAdapter {
    private Context context;
    private String[] titles;

    public MainViewPagerAdapter(FragmentManager fm, Context context,String[] titles) {
        super(fm);
        this.context = context;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return GeneralMovieFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
