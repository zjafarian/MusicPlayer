package com.example.musicplayer.data.room.dao;

import android.net.Uri;

import androidx.room.TypeConverter;

public class Converters {

    @TypeConverter
    public static String uriToString (Uri uri){
        return uri.toString();
    }

    @TypeConverter
    public static Uri stringToUri (String uri){
        return Uri.parse(uri);

    }

}
