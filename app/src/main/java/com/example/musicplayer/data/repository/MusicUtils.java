package com.example.musicplayer.data.repository;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.example.musicplayer.data.repository.MusicCursorWrapper;
import com.example.musicplayer.data.entities.Music;

import java.util.ArrayList;
import java.util.List;

public class MusicUtils {
    public static final String TAG = "MusicUtils";
    private Context mContext;
    private final Uri albumArtUri = Uri.parse("content://media/external/audio/albumart");
    private List<Music> mMusicList = new ArrayList<>();

    public MusicUtils(Context context) {
        mContext = context.getApplicationContext();
        ContentResolver musicResolver = mContext.getContentResolver();
        Uri musicUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        MusicCursorWrapper musicCursorWrapper=null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            musicCursorWrapper = new MusicCursorWrapper(musicResolver.query
                        (musicUri, null, null, null));
        }


        if (musicCursorWrapper != null && musicCursorWrapper.moveToFirst()) {
            try {
                while (!musicCursorWrapper.isAfterLast()) {

                    long id = musicCursorWrapper.getLong
                            (musicCursorWrapper.getColumnIndex(MediaStore.Audio.Media._ID));
                    Uri contentUri = ContentUris.withAppendedId
                            (MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, id);

                    Music music = musicCursorWrapper.getMusic(contentUri);
                    mMusicList.add(music);

                    musicCursorWrapper.moveToNext();
                }

            } catch (Exception e) {
                Log.d(TAG, e.getMessage());

            } finally {
                musicCursorWrapper.close();
            }
        }


    }


    public List<Music> getMusicList() {
        return mMusicList;
    }
}
