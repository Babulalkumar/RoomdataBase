package com.example.mvvmpattern;

import androidx.room.TypeConverter;

import com.example.mvvmpattern.model.AccessInfo;
import com.example.mvvmpattern.model.VolumeInfo;
import com.google.gson.Gson;

import java.lang.reflect.Type;

public class ConvertersSale {

    @TypeConverter
    public static String fromString(AccessInfo value) {
        return new Gson ().toJson (value);
    }

    @TypeConverter
    public static AccessInfo fromArrayList(String list) {
        return new Gson ().fromJson (list, AccessInfo.class);
    }
}
