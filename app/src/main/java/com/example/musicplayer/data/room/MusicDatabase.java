package com.example.musicplayer.data.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.musicplayer.data.room.dao.Converters;
import com.example.musicplayer.data.room.dao.MusicDao;
import com.example.musicplayer.data.room.dao.UserDao;
import com.example.musicplayer.data.room.entities.Music;
import com.example.musicplayer.data.room.entities.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Music.class, User.class},
        version = 1,
        exportSchema = false)
@TypeConverters({Converters.class})
public abstract class MusicDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "music_player.db";

    public static ExecutorService dataBaseWriteExecutor = Executors.newFixedThreadPool(8);

    public abstract MusicDao getMusicDao();

    public abstract UserDao getUserDao();


    public static MusicDatabase getDatabase(Context context) {
        return Room.databaseBuilder(
                context.getApplicationContext(),
                MusicDatabase.class,
                MusicDatabase.DATABASE_NAME
        ).allowMainThreadQueries().build();
    }


}
