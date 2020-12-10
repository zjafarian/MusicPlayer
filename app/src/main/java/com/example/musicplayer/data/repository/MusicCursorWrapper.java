package com.example.musicplayer.data.repository;

import android.database.Cursor;
import android.database.CursorWrapper;
import android.net.Uri;
import android.provider.MediaStore;

import com.example.musicplayer.data.entities.Music;

public class MusicCursorWrapper extends CursorWrapper {

    public MusicCursorWrapper(Cursor cursor) {
        super(cursor);
    }


    public Music getMusic (Uri pathMusic){
        long id =getLong(getColumnIndex(MediaStore.Audio.Media._ID));
        String  pathFile = getString(getColumnIndex(MediaStore.Audio.Media.DATA));
        String title = getString(getColumnIndex(MediaStore.Audio.Media.TITLE));
        String artist =getString(getColumnIndex(MediaStore.Audio.Media.ARTIST));
        String artistId = getString(getColumnIndex(MediaStore.Audio.Media.ARTIST_ID));
        String artistKey = getString(getColumnIndex(MediaStore.Audio.Media.ARTIST_KEY));
        String album = getString(getColumnIndex(MediaStore.Audio.Media.ALBUM));
        String albumId = getString(getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));
        String albumKey = getString(getColumnIndex(MediaStore.Audio.Media.ALBUM_KEY));
        long duration = getInt(getColumnIndex(MediaStore.Audio.Media.DURATION));

        Music music = new Music(id,pathMusic);
        music.setMusicTitle(title);
        music.setMusicAlbum(album);
        music.setAlbumIdMusic(albumId);
        music.setAlbumKeyMusic(albumKey);
        music.setMusicArtist(artist);
        music.setArtistIdMusic(artistId);
        music.setArtistKeyMusic(artistKey);
        music.setDurationMusic(duration);
        music.setPathFileMusic(pathFile);

        return music;
    }



}
