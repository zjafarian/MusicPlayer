package com.example.musicplayer.data.repository;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;

import com.example.musicplayer.data.room.MusicDatabase;
import com.example.musicplayer.data.room.dao.MusicDao;
import com.example.musicplayer.data.room.dao.UserDao;
import com.example.musicplayer.data.room.entities.Music;
import com.example.musicplayer.data.room.entities.User;
import com.example.musicplayer.utilities.MusicUtils;

import java.io.File;
import java.util.List;
import java.util.UUID;

public class MusicRepository {

    private static MusicRepository sInstance;
    private Context mContext;
    private MusicDao mMusicDao;
    private UserDao mUserDao;
    private List<Music> mMusicList;


    private MusicRepository(Context context) {
        mContext = context.getApplicationContext();

        MusicDatabase musicDatabase = MusicDatabase.getDatabase(mContext);
        mMusicDao = musicDatabase.getMusicDao();
        mUserDao = musicDatabase.getUserDao();
        MusicUtils musicUtils = new MusicUtils(mContext);
        mMusicList = musicUtils.getMusicList();
        insertMusics();


    }


    public static synchronized MusicRepository getInstance(Context context) {
        if (sInstance == null)
            sInstance = new MusicRepository(context);

        return sInstance;
    }

    //get data from music table



    public Music getMusic(int id) {
        return mMusicDao.getMusic(id);
    }

    public void updateMusic(Music music) {
        MusicDatabase.dataBaseWriteExecutor.execute(()-> mMusicDao.updateMusic(music));
    }

    public void deleteMusic(Music music) {
        MusicDatabase.dataBaseWriteExecutor.execute(()-> mMusicDao.deleteMusic(music));
    }

    public List<Music> getMusicList (){
        return mMusicDao.getMusicsList();
    }

    public void insertMusic(Music music) {
        MusicDatabase.dataBaseWriteExecutor.execute(()-> mMusicDao.insertMusics(music));
    }

    public void insertMusics() {
        MusicDatabase.dataBaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mMusicDao.insertMusics(mMusicList.toArray(new Music[]{}));
            }
        });
    }


    // get data from user table


    public List<User> getUsers() {
        return mUserDao.getUserList();
    }

    public User getUser(int id) {
        return mUserDao.getUser(id);
    }

    public void updateUser(User user) {
        MusicDatabase.dataBaseWriteExecutor.execute(()-> mUserDao.updateUser(user));
    }

    public void deleteUser(User user) {
        MusicDatabase.dataBaseWriteExecutor.execute(()-> mUserDao.deleteUser(user));
    }

    public void insertUser(User user) {
        MusicDatabase.dataBaseWriteExecutor.execute(()-> mUserDao.insertUser(user));
    }

   /* public void insertUsers(List<User> list) {
        MusicDatabase.dataBaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mUserDao.insertUsers(list.toArray(new User[]{}));
            }
        });
    }*/

}
