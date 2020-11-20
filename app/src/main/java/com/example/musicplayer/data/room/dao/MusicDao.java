package com.example.musicplayer.data.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.musicplayer.data.room.entities.Music;

import java.util.List;

@Dao
public interface MusicDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMusics(Music... musics);

    @Update
    void updateMusic (Music music);

    @Delete
    void deleteMusic (Music music);

    @Query("select * from musicTable")
    LiveData<List<Music>> getMusicsList();

    @Query("select * from musicTable where id= :idMusic")
    LiveData<Music> getMusic (int idMusic);
}
