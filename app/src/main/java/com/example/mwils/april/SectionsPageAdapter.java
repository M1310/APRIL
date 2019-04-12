package com.example.mwils.april;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SectionsPageAdapter extends FragmentPagerAdapter {

    //Used to keep track of the Fragments
    private final List<Fragment> mFragmentList = new ArrayList<>();
    //Used to keep track of the name of the Fragments
    private final List<String> mFragmentTitleList = new ArrayList<>();

    //Adds the actual fragment and page title
    public void addFragment(Fragment fragment, String title){
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }//end addFragment method

    public SectionsPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }//returns fragment title

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }//returns fragment itself

    @Override
    public int getCount() {
        return mFragmentList.size();
    }


}
