package com.example.musicplayer.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.example.musicplayer.R;
import com.example.musicplayer.adapters.MusicPagerAdapter;
import com.example.musicplayer.data.entities.TabsMusics;
import com.example.musicplayer.databinding.ActivityMusicPagerBinding;
import com.example.musicplayer.viewmodel.MusicsPagerViewModel;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import java.util.Arrays;
import java.util.List;

public class MusicPagerActivity extends AppCompatActivity {

    private ActivityMusicPagerBinding mBinding;
    private MusicsPagerViewModel mMusicsPagerViewModel;


    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, MusicPagerActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMusicsPagerViewModel = new ViewModelProvider(this).get(MusicsPagerViewModel.class);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_music_pager);
        updateUI();
    }

    public void updateUI() {
        List<TabsMusics> tabsMusics = Arrays.asList(TabsMusics.values());
        if (mMusicsPagerViewModel.getMusicPagerAdapter() == null) {
            mMusicsPagerViewModel.setMusicPagerAdapter(
                    new MusicPagerAdapter(this));


            mBinding.musicPager.setAdapter(mMusicsPagerViewModel.getMusicPagerAdapter());


            new TabLayoutMediator(mBinding.tabsMusicPager, mBinding.musicPager,
                    new TabLayoutMediator.TabConfigurationStrategy() {
                        @Override
                        public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {

                            String nameTab = String.valueOf(tabsMusics.get(position));
                            tab.setText(nameTab);
                            mBinding.musicPager.setCurrentItem(position);

                        }
                    }).attach();


        } else {
            mMusicsPagerViewModel.updateUI();
        }
    }

}