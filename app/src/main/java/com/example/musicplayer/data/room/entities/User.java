package com.example.musicplayer.data.room.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "userTable")
public class User {

    @PrimaryKey (autoGenerate = true)
    @NonNull
    @ColumnInfo (name = "userId")
    private int mUserId;

    @ColumnInfo (name = "userName")
    private String mUserName;

    @ColumnInfo(name = "password")
    private String mPassword;

    public User() {
        mUserName = "";
        mPassword = "";
    }

    public User(String userName, String password) {
        mUserName = userName;
        mPassword = password;
    }

    public int getUserId() {
        return mUserId;
    }

    public void setUserId(int userId) {
        mUserId = userId;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }
}
