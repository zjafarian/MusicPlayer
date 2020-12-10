package com.example.musicplayer.view.activity;

import androidx.fragment.app.Fragment;
import android.content.Context;
import android.content.Intent;
import com.example.musicplayer.R;
import com.example.musicplayer.view.fragment.ListArtistsFragment;

import java.util.UUID;

public class ListArtistsActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context context, UUID id) {
        Intent intent = new Intent(context, ListArtistsActivity.class);
        return intent;
    }


    @Override
    public Fragment createFragment() {
        ListArtistsFragment listArtictsFragment = ListArtistsFragment.newInstance();
        return listArtictsFragment;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_list_artists;
    }
}