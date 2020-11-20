package com.example.musicplayer.data.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.musicplayer.data.room.MusicDatabase;
import com.example.musicplayer.data.room.dao.MusicDao;
import com.example.musicplayer.data.room.dao.UserDao;
import com.example.musicplayer.data.room.entities.Music;
import com.example.musicplayer.data.room.entities.User;

import java.io.File;
import java.util.List;
import java.util.UUID;

public class MusicRepository {

    private static MusicRepository sInstance;
    private Context mContext;
    private MusicDao mMusicDao;
    private UserDao mUserDao;

    private MusicRepository(Context context) {
        mContext = context;

        MusicDatabase musicDatabase = MusicDatabase.getDatabase(mContext);
        mMusicDao = musicDatabase.getMusicDao();
        mUserDao = musicDatabase.getUserDao();

    }

    public static synchronized MusicRepository getInstance(Context context) {
        if (sInstance == null)
            sInstance = new MusicRepository(context);

        return sInstance;
    }

    //get data from music table

    public LiveData<List<Music>> getMusicsLiveData() {
        return mMusicDao.getMusicsList();
    }

    public LiveData<Music> getMusicLiveData(int id) {
        return mMusicDao.getMusic(id);
    }

    public void updateMusic(Music music) {
        MusicDatabase.dataBaseWriteExecutor.execute(()-> mMusicDao.updateMusic(music));
    }

    public void deleteMusic(Music music) {
        MusicDatabase.dataBaseWriteExecutor.execute(()-> mMusicDao.deleteMusic(music));
    }

    public void insertMusic(Music music) {
        MusicDatabase.dataBaseWriteExecutor.execute(()-> mMusicDao.insertMusics(music));
    }

    public void insertMusics(List<Music> list) {
        MusicDatabase.dataBaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mMusicDao.insertMusics(list.toArray(new Music[]{}));
            }
        });
    }


    // get data from user table


    public LiveData<List<User>> getUsersLiveData() {
        return mUserDao.getUserList();
    }

    public LiveData<User> getUserLiveData(int id) {
        return mUserDao.getUser(id);
    }

    public void updateUser(User user) {
        MusicDatabase.dataBaseWriteExecutor.execute(()-> mUserDao.updateUser(user));
    }

    public void deleteUser(User user) {
        MusicDatabase.dataBaseWriteExecutor.execute(()-> mUserDao.deleteUser(user));
    }

    public void insertUser(User user) {
        MusicDatabase.dataBaseWriteExecutor.execute(()-> mUserDao.insertUsers(user));
    }

    public void insertUsers(List<User> list) {
        MusicDatabase.dataBaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mUserDao.insertUsers(list.toArray(new User[]{}));
            }
        });
    }

}
