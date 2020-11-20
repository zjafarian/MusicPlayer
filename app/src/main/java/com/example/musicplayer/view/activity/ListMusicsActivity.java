package com.example.musicplayer.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.musicplayer.R;
import com.example.musicplayer.view.fragment.ListMusicsFragment;

import java.util.UUID;

public class ListMusicsActivity extends SingleFragmentActivity{

    public static Intent newIntent(Context context, UUID id) {
        Intent intent = new Intent(context, ListMusicsActivity.class);
        return intent;
    }


    @Override
    public Fragment createFragment() {
        ListMusicsFragment listMusicsFragment = ListMusicsFragment.newInstance();
        return listMusicsFragment;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_list_musics;
    }
}