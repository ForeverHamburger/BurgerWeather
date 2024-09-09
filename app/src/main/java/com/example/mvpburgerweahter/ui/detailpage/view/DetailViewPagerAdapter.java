package com.example.mvpburgerweahter.ui.detailpage.view;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class DetailViewPagerAdapter extends FragmentStateAdapter {

    private List<DetailPageFragment> fragmentList;

    public DetailViewPagerAdapter(@NonNull FragmentActivity fragmentActivity,
                                  List<DetailPageFragment> fragmentList) {
        super(fragmentActivity);
        this.fragmentList = fragmentList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentList == null ? null : fragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return fragmentList == null ? 0:fragmentList.size();
    }

    public Fragment getFragment(int position) {
        if (position >= 0 && position < fragmentList.size()) {
            return fragmentList.get(position);
        }
        return null;
    }
}
