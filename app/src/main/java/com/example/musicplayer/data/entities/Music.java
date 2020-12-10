package com.example.musicplayer.data.entities;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;


public class Music implements Parcelable, Comparable<Music> {

    private long mIdMusic;

    private String mMusicTitle;

    private String mMusicAlbum;

    private String mMusicArtist;

    private long mDurationMusic;

    private Uri mUriMusic;

    private String mAlbumIdMusic;

    private String mPathFileMusic;

    private String mArtistIdMusic;

    private String mArtistKeyMusic;

    private String mAlbumKeyMusic;




    public Music() {
        mMusicTitle = "";
        mMusicAlbum = "";
        mMusicArtist = "";
        mDurationMusic = 0;
        mIdMusic = 0;
        mAlbumIdMusic = "";
    }

    public Music(long idMusic, Uri uriMusic) {
        mIdMusic = idMusic;
        mUriMusic = uriMusic;
    }

    protected Music(Parcel in) {
        mIdMusic = in.readLong();
        mMusicTitle = in.readString();
        mMusicAlbum = in.readString();
        mMusicArtist = in.readString();
        mDurationMusic = in.readLong();
        mUriMusic = in.readParcelable(Uri.class.getClassLoader());
        mAlbumIdMusic = in.readString();
        mPathFileMusic = in.readString();
        mArtistIdMusic = in.readString();
        mArtistKeyMusic = in.readString();
        mAlbumKeyMusic = in.readString();
    }

    public static final Creator<Music> CREATOR = new Creator<Music>() {
        @Override
        public Music createFromParcel(Parcel in) {
            return new Music(in);
        }

        @Override
        public Music[] newArray(int size) {
            return new Music[size];
        }
    };

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

    public long getDurationMusic() {
        return mDurationMusic;
    }

    public void setDurationMusic(long durationMusic) {
        mDurationMusic = durationMusic;
    }

    public Uri getUriMusic() {
        return mUriMusic;
    }

    public void setUriMusic(Uri uriMusic) {
        mUriMusic = uriMusic;
    }

    public String getAlbumIdMusic() {
        return mAlbumIdMusic;
    }

    public void setAlbumIdMusic(String albumIdMusic) {
        mAlbumIdMusic = albumIdMusic;
    }

    public String getPathFileMusic() {
        return mPathFileMusic;
    }

    public void setPathFileMusic(String pathFileMusic) {
        mPathFileMusic = pathFileMusic;
    }

    public String getArtistIdMusic() {
        return mArtistIdMusic;
    }

    public void setArtistIdMusic(String artistIdMusic) {
        mArtistIdMusic = artistIdMusic;
    }

    public String getArtistKeyMusic() {
        return mArtistKeyMusic;
    }

    public void setArtistKeyMusic(String artistKeyMusic) {
        mArtistKeyMusic = artistKeyMusic;
    }

    public String getAlbumKeyMusic() {
        return mAlbumKeyMusic;
    }

    public void setAlbumKeyMusic(String albumKeyMusic) {
        mAlbumKeyMusic = albumKeyMusic;
    }


    @Override
    public int compareTo(Music music) {
        return this.getMusicTitle().compareTo(music.getMusicTitle());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.mIdMusic);
        dest.writeString(this.mMusicTitle);
        dest.writeString(this.mMusicAlbum);
        dest.writeString(this.mAlbumIdMusic);
        dest.writeString(this.mAlbumKeyMusic);
        dest.writeString(this.mMusicArtist);
        dest.writeString(this.mArtistIdMusic);
        dest.writeString(this.mArtistKeyMusic);
        dest.writeParcelable(this.mUriMusic, flags);
        dest.writeValue(this.mDurationMusic);
    }


}
