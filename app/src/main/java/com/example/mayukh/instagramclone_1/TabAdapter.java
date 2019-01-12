package com.example.mayukh.instagramclone_1;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabAdapter extends FragmentPagerAdapter {

    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int tabPosition) {
        //Returning the required fragments(tab's contents) at required position
        switch (tabPosition){
            case 0:
                ProfileTab profileTab = new ProfileTab();
                return profileTab;
            case 1:
                return new UsersTab();
            case 2:
                return new SharePicturesTab();
            default:
                return  null;
        }
    }

    @Override
    //returning the number of fragments
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    //Setting the Titles of each Fragments
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "PROFILE";
            case 1:
                return  "USERS";
            case 2:
                return "SHARE PICTURE";
                default:
                    return null;
        }
    }
}
