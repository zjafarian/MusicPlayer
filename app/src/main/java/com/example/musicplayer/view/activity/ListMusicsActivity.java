package com.example.musicplayer.view.activity;


import androidx.fragment.app.Fragment;
import android.content.Context;
import android.content.Intent;
import com.example.musicplayer.R;
import com.example.musicplayer.view.fragment.ListMusicsFragment;


public class ListMusicsActivity extends SingleFragmentActivity{

    public static final String EXTRA_NAME_TAB = "com.example.musicplayer.nameTab";
    public static final String EXTRA_NAME_ARTIST_OR_ALBUM = "com.example.musicplayer.nameArtistOrAlbum";

    public static Intent newIntent
            (Context context, String nameTab, String nameArtistOrAlbum) {
        Intent intent = new Intent(context, ListMusicsActivity.class);
        intent.putExtra(EXTRA_NAME_TAB,nameTab);
        intent.putExtra(EXTRA_NAME_ARTIST_OR_ALBUM,nameArtistOrAlbum);
        return intent;
    }


    @Override
    public Fragment createFragment() {
        String nameTab = getIntent().getStringExtra(EXTRA_NAME_TAB);
        String nameArtistOrAlbum = getIntent().getStringExtra(EXTRA_NAME_ARTIST_OR_ALBUM);

        ListMusicsFragment listMusicsFragment =
                ListMusicsFragment.newInstance(nameTab,nameArtistOrAlbum);
        return listMusicsFragment;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_list_musics;
    }
}