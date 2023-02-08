package com.example.mvvmpattern.adpter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdpter extends FragmentPagerAdapter {

    private final List<Fragment> fragmentList=new ArrayList<> ();
    private final List<String>titleList=new ArrayList<> ();
    public ViewPagerAdpter(@NonNull FragmentManager fm) {
        super (fm);
    }

    public void Add(Fragment fragment,String title){
        fragmentList.add (fragment);
        titleList.add (title);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get (position);
    }

    @Override
    public int getCount() {
        return fragmentList.size ();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get (position);
    }
}
