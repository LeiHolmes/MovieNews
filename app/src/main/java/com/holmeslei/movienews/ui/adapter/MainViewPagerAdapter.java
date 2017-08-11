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
    private String function;
    private String[] params;
    private String[] titles;

    public MainViewPagerAdapter(FragmentManager fm, String function, String[] params, String[] titles) {
        super(fm);
        this.function = function;
        this.params = params;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return GeneralMovieFragment.newInstance(function, params[position]);
    }

    @Override
    public int getCount() {
        return params.length;
    }

    /**
     * 设置TabLayout标题
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
