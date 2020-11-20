package com.example.musicplayer.data.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.musicplayer.data.room.entities.Music;
import com.example.musicplayer.data.room.entities.User;

import java.util.List;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertUsers(User... users);

    @Update
    void updateUser(User user);

    @Delete
    void deleteUser (User user);

    @Query("select * from userTable")
    LiveData<List<User>> getUserList();

    @Query("select * from userTable where userId= :idUser")
    LiveData<User> getUser (int idUser);
}
