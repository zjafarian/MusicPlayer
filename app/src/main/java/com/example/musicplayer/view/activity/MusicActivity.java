package com.example.musicplayer.view.activity;

import androidx.fragment.app.Fragment;
import android.content.Context;
import android.content.Intent;
import com.example.musicplayer.R;
import com.example.musicplayer.view.fragment.MusicFragment;

public class MusicActivity extends SingleFragmentActivity {

    public static final String EXTRA_ID_MUSIC = "com.example.musicplayer.idMusic";
    public static final String EXTRA_NAME_TAB = "om.example.musicplayer.nameTab";
    public static final String EXTRA_NAME_ARTIST_OR_ALBUM = "om.example.musicplayer.nameArtistOrAlbum";


    public static Intent newIntent(Context context, long id, String nameTab,
                                   String nameArtistOrAlbum) {
        Intent intent = new Intent(context, MusicActivity.class);
        intent.putExtra(EXTRA_ID_MUSIC, id);
        intent.putExtra(EXTRA_NAME_TAB,nameTab);
        intent.putExtra(EXTRA_NAME_ARTIST_OR_ALBUM,nameArtistOrAlbum);


        return intent;
    }


    @Override
    public Fragment createFragment() {
        long idMusic = getIntent().getLongExtra(EXTRA_ID_MUSIC, 0);
        String nameTab = getIntent().getStringExtra(EXTRA_NAME_TAB);
        String artistOrAlbum = getIntent().getStringExtra(EXTRA_NAME_ARTIST_OR_ALBUM);
        MusicFragment musicFragment = MusicFragment.newInstance(idMusic,nameTab,artistOrAlbum);
        return musicFragment;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_music;
    }
}