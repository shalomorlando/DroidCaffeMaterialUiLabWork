package com.shalom.materialuilabwork;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {

    int myNumberOfTabs;
    public PagerAdapter(@NonNull FragmentManager fm, int numOfTabs) {
        super(fm, numOfTabs);
        this.myNumberOfTabs = numOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new DessertRecipeFragment();
            case 1: return new PatriesRecipeFragment();
            case 2: return new StoresFragment();
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return myNumberOfTabs;
    }
}
