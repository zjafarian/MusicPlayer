package com.example.musicplayer.data.repository;

import android.database.Cursor;
import android.database.CursorWrapper;
import android.net.Uri;
import android.provider.MediaStore;

import com.example.musicplayer.data.room.entities.Music;

public class MusicCursorWrapper extends CursorWrapper {

    public MusicCursorWrapper(Cursor cursor) {
        super(cursor);
    }


    public Music getMusic (Uri pathMusic){
        long id =getLong(getColumnIndex(MediaStore.Audio.Media._ID));
        String  pathFile = getString(getColumnIndex(MediaStore.Audio.Media.DATA));
        String title = getString(getColumnIndex(MediaStore.Audio.Media.TITLE));
        String artist =getString(getColumnIndex(MediaStore.Audio.Media.ARTIST));
        String album = getString(getColumnIndex(MediaStore.Audio.Media.ALBUM));
        long duration = getInt(getColumnIndex(MediaStore.Audio.Media.DURATION));

        Music music = new Music(id,pathMusic);
        music.setMusicTitle(title);
        music.setAlbumArt(album);
        music.setMusicArtist(artist);
        music.setDuration(duration);
        music.setPathFile(pathFile);

        return music;
    }



}
