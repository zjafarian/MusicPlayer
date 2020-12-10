package com.example.musicplayer.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.musicplayer.adapters.ListAlbumsAdapter;
import com.example.musicplayer.adapters.ListArtistsAdapter;
import com.example.musicplayer.data.repository.MusicRepository;

import java.util.List;

public class ListAlbumsArtistsViewModel extends AndroidViewModel {
    private MusicRepository mRepository;
    private Context mContext;
    private ListAlbumsAdapter mAdapterAlbum;
    private ListArtistsAdapter mAdapterArtist;


    public ListAlbumsArtistsViewModel(@NonNull Application application) {
        super(application);
        mContext = application.getApplicationContext();
        mRepository = MusicRepository.getInstance(mContext);
    }

    public List<String> getArtistsNames() {
        return mRepository.findArtists();
    }

    public List<String> getAlbumsNames() {
        return mRepository.findAlbums();
    }

    public void updateUIArtist() {
        if (mAdapterArtist != null) {
            mAdapterArtist.notifyDataSetChanged();
        }

    }

    public void updateUIAlbum() {
        if (mAdapterAlbum != null) {
            mAdapterAlbum.notifyDataSetChanged();
        }

    }


}
