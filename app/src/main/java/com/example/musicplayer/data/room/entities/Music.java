package com.example.musicplayer.data.room.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "musicTable")
public class Music {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int mIdMusic;
    
    @ForeignKey(entity = User.class, parentColumns ="userId" , childColumns = "userId")
    @ColumnInfo(name = "idUser")
    private int mIdUser;

    @ColumnInfo(name = "title")
    private String mMusicTitle;

    @ColumnInfo(name = "album")
    private String mMusicAlbum;

    @ColumnInfo(name = "artist")
    private String mMusicArtist;

    @ColumnInfo(name = "duration")
    private long mDuration;

    public Music() {
        mMusicTitle = "";
        mMusicAlbum = "";
        mMusicArtist = "";
        mDuration = 0;
    }

    public Music(int idMusic, String musicTitle, String musicAlbum, String musicArtist,
                 long duration, int userId) {
        mIdMusic = idMusic;
        mMusicTitle = musicTitle;
        mMusicAlbum = musicAlbum;
        mMusicArtist = musicArtist;
        mDuration = duration;
        mIdUser = userId;
    }

    public int getIdMusic() {
        return mIdMusic;
    }

    public void setIdMusic(int idMusic) {
        mIdMusic = idMusic;
    }

    public String getMusicTitle() {
        return mMusicTitle;
    }

    public void setMusicTitle(String musicTitle) {
        mMusicTitle = musicTitle;
    }

    public String getMusicAlbum() {
        return mMusicAlbum;
    }

    public void setMusicAlbum(String musicAlbum) {
        mMusicAlbum = musicAlbum;
    }

    public String getMusicArtist() {
        return mMusicArtist;
    }

    public void setMusicArtist(String musicArtist) {
        mMusicArtist = musicArtist;
    }

    public long getDuration() {
        return mDuration;
    }

    public void setDuration(long duration) {
        mDuration = duration;
    }

    public int getIdUser() {
        return mIdUser;
    }

    public void setIdUser(int idUser) {
        mIdUser = idUser;
    }
}
