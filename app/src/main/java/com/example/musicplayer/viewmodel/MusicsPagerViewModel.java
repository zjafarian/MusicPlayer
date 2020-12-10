package com.example.musicplayer.viewmodel;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;


import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.musicplayer.adapters.MusicPagerAdapter;
import com.example.musicplayer.data.repository.MusicRepository;
import com.example.musicplayer.data.entities.Music;
import com.example.musicplayer.view.activity.MusicPagerActivity;

import java.util.List;


public class MusicsPagerViewModel extends AndroidViewModel {

    private MusicPagerAdapter mMusicPagerAdapter;
    private Context mContext;
    private MusicRepository mRepository;
    private boolean mCheckLogin;

    public MusicsPagerViewModel(@NonNull Application application) {
        super(application);
        mContext = application.getApplicationContext();
        mRepository = MusicRepository.getInstance(mContext);
    }

    public MusicPagerAdapter getMusicPagerAdapter() {
        return mMusicPagerAdapter;
    }

    public void setMusicPagerAdapter(MusicPagerAdapter musicPagerAdapter) {
        mMusicPagerAdapter = musicPagerAdapter;
    }

    public void updateUI(){
        if (mMusicPagerAdapter!=null){
            mMusicPagerAdapter.notifyDataSetChanged();
        }
    }






}
