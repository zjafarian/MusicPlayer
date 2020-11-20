package com.example.musicplayer.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.musicplayer.R;
import com.example.musicplayer.databinding.ActivityMusicPagerBinding;
import com.example.musicplayer.viewmodel.ListAlbumsViewModel;
import com.example.musicplayer.viewmodel.ListArtistsViewModel;
import com.example.musicplayer.viewmodel.ListMusicsViewModel;

import java.util.UUID;

public class MusicPagerActivity extends AppCompatActivity {

    private ActivityMusicPagerBinding mBinding;
    private ListAlbumsViewModel mListAlbumsViewModel;
    private ListArtistsViewModel mListArtistsViewModel;
    private ListMusicsViewModel mListMusicsViewModel;


    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, MusicPagerActivity.class);
        return intent;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_music_pager);

    }
}