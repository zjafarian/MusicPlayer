package com.example.musicplayer.data.room.entities;

import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "musicTable")
public class Music {

    @PrimaryKey
    @ColumnInfo(name = "id")
    private long mIdMusic;

    @ForeignKey(entity = User.class, parentColumns = "userId", childColumns = "userId")
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

    @ColumnInfo(name = "uri")
    private Uri mUriMusic;

    @ColumnInfo(name = "albumArt")
    private String mAlbumArt;

    @ColumnInfo(name = "filePath")
    private String mPathFile;



    public Music() {
        mMusicTitle = "";
        mMusicAlbum = "";
        mMusicArtist = "";
        mDuration = 0;
        mIdMusic = 0;
        mAlbumArt = "";
    }

    @Ignore
    public Music(long idMusic, Uri uriMusic) {
        mIdMusic = idMusic;
        mUriMusic = uriMusic;
    }

    public long getIdMusic() {
        return mIdMusic;
    }

    public void setIdMusic(long idMusic) {
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

    public Uri getUriMusic() {
        return mUriMusic;
    }

    public void setUriMusic(Uri uriMusic) {
        mUriMusic = uriMusic;
    }

    public String getAlbumArt() {
        return mAlbumArt;
    }

    public void setAlbumArt(String albumArt) {
        mAlbumArt = albumArt;
    }

    public String getPathFile() {
        return mPathFile;
    }

    public void setPathFile(String pathFile) {
        mPathFile = pathFile;
    }
}
