package com.example.musicplayer.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.musicplayer.data.room.entities.TabsMusics;
import com.example.musicplayer.view.activity.MusicPagerActivity;
import com.example.musicplayer.view.fragment.ListAlbumsFragment;
import com.example.musicplayer.view.fragment.ListArtistsFragment;
import com.example.musicplayer.view.fragment.ListMusicsFragment;

import java.util.Arrays;
import java.util.List;

public class MusicPagerAdapter extends FragmentStateAdapter {
    private List<TabsMusics> mTabsMusics = Arrays.asList(TabsMusics.values());

    private int mStateTabs;



    public MusicPagerAdapter(@NonNull MusicPagerActivity fragmentActivity) {
        super(fragmentActivity);

    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        mStateTabs = position;
        switch (position){
            case 0:
                ListAlbumsFragment listAlbumsFragment = ListAlbumsFragment.newInstance();
                return listAlbumsFragment;
            case 1:
                ListArtistsFragment listArtictsFragment = ListArtistsFragment.newInstance();
                return listArtictsFragment;
            case 2:
                ListMusicsFragment listMusicsFragment = ListMusicsFragment.newInstance();
                return listMusicsFragment;
            default:
                break;
        }

        return null;
    }

    @Override
    public int getItemCount() {
        return mTabsMusics.size();
    }


}
