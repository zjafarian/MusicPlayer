package com.example.musicplayer.viewmodel;

import android.app.Activity;
import android.app.Application;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;

import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;

import android.media.MediaPlayer;
import android.net.Uri;

import android.provider.MediaStore;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;
import androidx.lifecycle.AndroidViewModel;

import com.example.musicplayer.R;
import com.example.musicplayer.data.repository.MusicRepository;
import com.example.musicplayer.data.entities.Music;
import com.example.musicplayer.utilities.ID3TAG;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.jaudiotagger.tag.datatype.Artwork;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;


public class MusicViewModel extends AndroidViewModel implements MediaPlayer.OnCompletionListener {
    public static final String TAG = "MusicViewModel";
    private MusicRepository mRepository;
    private List<Music> mMusicList;
    private Music mMusic;
    private MediaPlayer mMediaPlayer;
    private Context mContext;
    private List<Music> mMusicListSubject;
    private int mCurrentIndexMusic;
    private Activity mActivity;
    private boolean mIsLoop;
    private Bitmap mBitmap;
    private int columnIndex;


    public MusicViewModel(@NonNull Application application) {
        super(application);
        mContext = getApplication().getApplicationContext();
        mRepository = MusicRepository.getInstance(application);
        mMusicList = mRepository.getMusicList();

    }

    public Music getMusic() {
        return mMusic;
    }

    public boolean isLoop() {
        return mIsLoop;
    }

    public void setLoop(boolean loop) {
        mIsLoop = loop;
        mMediaPlayer.setOnCompletionListener(this::onCompletion);
    }

    public Music findMusic(long idMusic, String nameTab, String nameArtistOrAlbum) {
        setListMusicSubject(nameTab, nameArtistOrAlbum);
        for (Music findMusic : mMusicList) {
            if (findMusic.getIdMusic() == idMusic) {
                mMusic = findMusic;
                mMediaPlayer = MediaPlayer.create(mContext, mMusic.getUriMusic());
                mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mCurrentIndexMusic = mMusicListSubject.indexOf(mMusic);
                mMediaPlayer.setOnCompletionListener(this::onCompletion);
                return findMusic;
            }
        }
        return null;
    }

    private void setListMusicSubject(String nameTab, String nameArtistOrAlbum) {
        switch (nameTab) {
            case "album":
                mMusicListSubject = mRepository.findMusicByAlbumName(nameArtistOrAlbum);
                break;
            case "artist":
                mMusicListSubject = mRepository.findMusicByArtistName(nameArtistOrAlbum);
                break;
            case "music":
                mMusicListSubject = mRepository.findMusics();
                break;
        }
    }

    public void setPlayList(boolean isShuffle) {
        if (isShuffle)
            Collections.shuffle(mMusicListSubject);
        else Collections.sort(mMusicListSubject);
    }

    public MediaPlayer getMediaPlayer() {
        return mMediaPlayer;
    }

    public String getCurrentTimeText() {
        return null;
    }

    public String getTotalTimeText() {
        return createTimeLabel(mMediaPlayer.getDuration());
    }

    private String createTimeLabel(int duration) {
        String label = "";
        int min = duration / 1000 / 60;
        int sec = duration / 1000 * 60;
        label = min + ":";
        double mil = sec / 1000.0;
        if (mil > 0) {
            sec = sec / 1000;
            mil = Math.getExponent(mil);
        }
        if (sec < 10)
            label += "0";

        label += sec;
        if (mil > 0) {
            int m = min;
            label = label + ":" + m;
        }
        return label;
    }

    public void goForward() {
        mCurrentIndexMusic = (mCurrentIndexMusic + 1) % mMusicListSubject.size();
        mMusic = mMusicListSubject.get(mCurrentIndexMusic);

        try {
            mMediaPlayer.reset();
            mMediaPlayer.setDataSource(mContext, mMusic.getUriMusic());
            mMediaPlayer.prepare();
            mMediaPlayer.start();

        } catch (IOException e) {
            Log.d("MusicViewModel", "Play: " + e.getMessage());
        }
    }

    public void goBackward() {
        mCurrentIndexMusic = (mCurrentIndexMusic - 1 + mMusicListSubject.size())
                % mMusicListSubject.size();
        mMusic = mMusicListSubject.get(mCurrentIndexMusic);
        try {
            mMediaPlayer.reset();
            mMediaPlayer.setDataSource(mContext, mMusic.getUriMusic());
            mMediaPlayer.prepare();
            mMediaPlayer.start();
        } catch (IOException e) {
            Log.d(TAG, "Play: " + e.getMessage());
        }
    }

    public String getTitleCurrentMusic() {
        return mMusic.getMusicTitle();
    }

    public Uri getBitmapImage() {
        if (mMusic == null)
            return null;

        long id = Long.parseLong(mMusic.getAlbumIdMusic());


        Uri contentUri = ContentUris.withAppendedId
                (MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, id);

        Cursor cursor = null;
        String path;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = mContext.getContentResolver().query(contentUri,
                    proj,
                    null,
                    null,
                    null);
            cursor.moveToFirst();
            columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            path = cursor.getString(columnIndex);
        } finally {
                cursor.close();
        }

        Uri uri = Uri.parse(path);

        return uri;
    }

    public void setActivity(Activity activity) {
        mActivity = activity;
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        if (mCurrentIndexMusic == mMusicListSubject.size() - 1 && !mIsLoop) {
            mediaPlayer.stop();
            mediaPlayer.reset();
            return;
        }
        if (!mIsLoop) {
            mCurrentIndexMusic++;
        } else {
            mCurrentIndexMusic = (mCurrentIndexMusic + 1) % mMusicListSubject.size();
        }
        mMusic = mMusicListSubject.get(mCurrentIndexMusic);
        mCurrentIndexMusic = mMusicListSubject.indexOf(mMusic);

        try {
            mMediaPlayer.reset();
            mMediaPlayer.setDataSource(mContext, mMusic.getUriMusic());
            mMediaPlayer.prepare();
            mMediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
