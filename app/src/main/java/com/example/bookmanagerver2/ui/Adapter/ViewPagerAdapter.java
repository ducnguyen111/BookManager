package com.example.bookmanagerver2.ui.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.bookmanagerver2.ui.Fragment.LibrarianFragment;
import com.example.bookmanagerver2.ui.Fragment.MemberFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch(position){
            case 0:
                return  new MemberFragment();
            case 1 :
                return new LibrarianFragment();
            default :
                return new MemberFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch(position) {
            case 0:title = "Member";
                break;
            case 1:title = "Librarian";
                break;
        }
        return  title;
    }
}
