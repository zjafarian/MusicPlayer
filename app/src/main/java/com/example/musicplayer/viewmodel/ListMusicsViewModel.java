package com.example.musicplayer.viewmodel;

import android.app.Application;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;

import com.example.musicplayer.adapters.ListMusicsAdapter;
import com.example.musicplayer.data.repository.MusicRepository;
import com.example.musicplayer.data.entities.Music;

import java.util.List;

public class ListMusicsViewModel extends AndroidViewModel {
    private MusicRepository mRepository;
    private ListMusicsAdapter mMusicsAdapter;


    @RequiresApi(api = Build.VERSION_CODES.O)
    public ListMusicsViewModel(@NonNull Application application) {
        super(application);
        mRepository = MusicRepository.getInstance(application);
    }


    public List<Music> getMusicsAlbumsByAlbumName(String nameAlbum) {
        return mRepository.findMusicByAlbumName(nameAlbum);
    }

    public List<Music> getMusicsArtistsByArtistName(String nameArtist) {
        return mRepository.findMusicByArtistName(nameArtist);
    }

    public List<Music> getMusics() {
        return mRepository.findMusics();
    }

    public void updateId(List<Music> musicList) {
        if (mMusicsAdapter != null)
            mMusicsAdapter.notifyDataSetChanged();
    }

    public void setMusicsAdapter(ListMusicsAdapter musicsAdapter) {
        mMusicsAdapter = musicsAdapter;
    }

    public ListMusicsAdapter getMusicsAdapter() {
        return mMusicsAdapter;
    }
}
