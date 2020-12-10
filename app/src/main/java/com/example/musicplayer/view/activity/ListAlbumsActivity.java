package com.example.musicplayer.view.activity;

import androidx.fragment.app.Fragment;
import android.content.Context;
import android.content.Intent;
import com.example.musicplayer.R;
import com.example.musicplayer.view.fragment.ListAlbumsFragment;

import java.util.UUID;

public class ListAlbumsActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context context, UUID id) {
        Intent intent = new Intent(context, ListAlbumsActivity.class);
        return intent;
    }


    @Override
    public Fragment createFragment() {
        ListAlbumsFragment listAlbumsFragment = ListAlbumsFragment.newInstance();
        return listAlbumsFragment;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_list_albums;
    }
}