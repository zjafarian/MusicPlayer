package com.example.musicplayer.viewmodel;

import android.app.Application;
import android.content.Intent;
import android.os.Build;
import android.provider.SyncStateContract;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.musicplayer.data.repository.MusicRepository;
import com.example.musicplayer.data.room.entities.Music;
import com.example.musicplayer.view.activity.MusicPagerActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ListMusicsViewModel extends AndroidViewModel {
    private MusicRepository mRepository;
    private List<Music> mMusicsSubject = new ArrayList<>();
    private LiveData<List<Music>> mMusicsLiveData;
    private MutableLiveData<Music> mMusicSelectedLiveData;


    @RequiresApi(api = Build.VERSION_CODES.O)
    public ListMusicsViewModel(@NonNull Application application) {
        super(application);
        mRepository = MusicRepository.getInstance(application);
        fetchMusicsFromDbToLiveData();

    }

    public void fetchMusicsFromDbToLiveData() {
        mMusicsLiveData = mRepository.getMusicsLiveData();
    }

    public void onMusicSelectedLiveData(Music music) {
        mMusicSelectedLiveData.setValue(music);
    }

    public void insertNewMusic() {

        Music music = new Music();
        mRepository.insertMusic(music);

        //select the music after creating new one to add details to it.
        onMusicSelectedLiveData(music);

    }

    public int getNumberOfMusics() {
        return getMusicsSubject() == null ? 0 : getMusicsSubject().size();
    }

    public int getPosition(int musicId) {
        if (mMusicsSubject!= null)
            for (int i = 0; i < mMusicsSubject.size(); i++) {
                if (mMusicsSubject.get(i).getIdMusic()== musicId)
                    return i;
            }

        return 0;
    }

    public void navigateToDetail(FragmentActivity activity, Music music) {

            Intent intent = MusicPagerActivity.newIntent(activity);

            activity.startActivity(intent);

    }

    public void onClickListItem(int position) {
        Music music = mMusicsLiveData.getValue().get(position);
        onMusicSelectedLiveData(music);
    }


    public MusicRepository getRepository() {
        return mRepository;
    }

    public void setRepository(MusicRepository repository) {
        mRepository = repository;
    }

    public List<Music> getMusicsSubject() {
        return mMusicsSubject;
    }

    public void setMusicsSubject(List<Music> musicsSubject) {
        mMusicsSubject = musicsSubject;
    }

    public LiveData<List<Music>> getMusicsLiveData() {
        return mMusicsLiveData;
    }

    public void setMusicsLiveData(LiveData<List<Music>> musicsLiveData) {
        mMusicsLiveData = musicsLiveData;
    }

    public MutableLiveData<Music> getMusicSelectedLiveData() {
        return mMusicSelectedLiveData;
    }

    public void setMusicSelectedLiveData(MutableLiveData<Music> musicSelectedLiveData) {
        mMusicSelectedLiveData = musicSelectedLiveData;
    }


}
