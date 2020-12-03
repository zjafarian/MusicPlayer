package com.example.musicplayer.viewmodel;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;


import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.musicplayer.view.activity.LoginSignUpActivity;
import com.example.musicplayer.view.activity.MusicPagerActivity;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class MusicsPagerViewModel extends AndroidViewModel {

    public MusicsPagerViewModel(@NonNull Application application) {
        super(application);

    }

    public void createIntentForLoginSignUpPage (Activity activity){
        Intent intent = LoginSignUpActivity.newIntent(activity);
        activity.startActivityForResult(intent, MusicPagerActivity.REQUEST_CODE_LOGIN_SIGN_UP);
    }


    public void setListMusicsUser(int userId){

    }

    /*public void setListTask() {
        if (mAdapter == null) {
            mViewPager.setAdapter(createCardAdapter());
            new TabLayoutMediator(mTabLayout, mViewPager,
                    new TabLayoutMediator.TabConfigurationStrategy() {
                        @Override
                        public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                            String nameTab = String.valueOf(mStates.get(position));
                            tab.setText(nameTab);
                        }
                    }).attach();

        } else {
            mAdapter.notifyDataSetChanged();
        }

    }

    private void setUI(int position) {
        FragmentStateAdapter adapter =
                new CrimeViewPagerAdapter(this, mCrimeListViewModel);
        mBinding.crimeViewPager.setAdapter(adapter);

        //this method "must" be placed after setAdapter.
        mBinding.crimeViewPager.setCurrentItem(position);
    }*/
}
