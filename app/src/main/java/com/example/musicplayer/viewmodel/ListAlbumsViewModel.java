package com.example.musicplayer.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.musicplayer.data.repository.MusicRepository;
import com.example.musicplayer.data.room.entities.Music;

import java.util.ArrayList;
import java.util.List;

public class ListAlbumsViewModel extends AndroidViewModel {
    private MusicRepository mRepository;
    private List<Music> mMusicList = new ArrayList<>();



    public ListAlbumsViewModel(@NonNull Application application) {
        super(application);
        mRepository = MusicRepository.getInstance(application);
        fetchMusicsFromDbToLiveData();

    }





    public List<Music> getMusicList() {
        return mMusicList;
    }

    public void setMusicList(List<Music> musicList) {
        mMusicList = musicList;
    }

    public void fetchMusicsFromDbToLiveData() {
        mMusicList = mRepository.getMusicList();
    }



    public void insertNewMusic() {
        Music music = new Music();
        mRepository.insertMusic(music);

    }

    public int getNumberOfMusics() {
        return getMusicList() == null ? 0 : getMusicList().size();
    }

    public int getPosition(long musicId) {
        if (getMusicList() != null)
            for (int i = 0; i < getMusicList().size(); i++) {
                if (getMusicList().get(i).getIdMusic() == musicId)
                    return i;
            }
        return 0;
    }


    public void onClickListItem(int position) {
        Music music = mMusicList.get(position);


    }


}
