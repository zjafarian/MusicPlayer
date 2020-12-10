package com.example.musicplayer.data.repository;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.example.musicplayer.data.entities.Music;

import java.util.ArrayList;
import java.util.List;

public class MusicRepository {

    private static MusicRepository sInstance;
    private Context mContext;
    private List<Music> mMusicList;
    private List<String> mArtistsNames = new ArrayList<>();
    private List<String> mAlbumsNames = new ArrayList<>();
    private List<Music> mFindMusicsArtist = new ArrayList<>();
    private List<Music> mFindMusicsAlbums = new ArrayList<>();


    private MusicRepository(Context context) {
        mContext = context.getApplicationContext();
        MusicUtils musicUtils = new MusicUtils(mContext);
        mMusicList = musicUtils.getMusicList();
    }

    public static synchronized MusicRepository getInstance(Context context) {
        if (sInstance == null)
            sInstance = new MusicRepository(context);

        return sInstance;
    }

    public List<Music> getMusicList() {
        return mMusicList;
    }

    public List<String> findArtists() {
        for (int i = 0; i < mMusicList.size(); i++) {
            String name = mMusicList.get(i).getMusicArtist();
            boolean check = false;
            if (mArtistsNames == null || mArtistsNames.size() == 0) {
                mArtistsNames.add(name);
            } else {
                for (int j = 0; j < mArtistsNames.size(); j++) {
                    if (mArtistsNames.get(j).equals(name)) {
                        check = true;
                        continue;
                    }
                }
                if (!check)
                    mArtistsNames.add(name);
            }
        }
        return mArtistsNames;
    }

    public List<String> findAlbums() {
        for (int i = 0; i < mMusicList.size(); i++) {
            String name = mMusicList.get(i).getMusicAlbum();
            boolean check = false;
            if (mAlbumsNames == null || mAlbumsNames.size() == 0) {
                mAlbumsNames.add(name);

            } else {
                for (int j = 0; j < mAlbumsNames.size(); j++) {
                    if (mAlbumsNames.get(j).equals(name)) {
                        check = true;
                        continue;
                    }
                }
                if (!check)
                    mAlbumsNames.add(name);
            }
        }
        return mAlbumsNames;
    }

    public List<Music> findMusicByArtistName(String nameArtist) {
        mFindMusicsArtist = new ArrayList<>();
        for (int i = 0; i < mMusicList.size(); i++) {
            if (mMusicList.get(i).getMusicArtist().equals(nameArtist))
                mFindMusicsArtist.add(mMusicList.get(i));
        }

        return mFindMusicsArtist;
    }

    public List<Music> findMusicByAlbumName(String nameAlbum) {
        mFindMusicsAlbums = new ArrayList<>();
        for (int i = 0; i < mMusicList.size(); i++) {
            if (mMusicList.get(i).getMusicAlbum().equals(nameAlbum))
                mFindMusicsAlbums.add(mMusicList.get(i));
        }
        return mFindMusicsAlbums;
    }

    public List<Music> findMusics() {
        return mMusicList;
    }

}
