package com.example.mvvmpattern;

import android.util.Log;

import androidx.room.TypeConverter;

import com.example.mvvmpattern.model.VolumeInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

public class Converters {
/*    @TypeConverter
    public static String ListToJson(List<VolumeInfo> replyMessages) {
        if (replyMessages == null) return null;
        Type type = new TypeToken<List<VolumeInfo>> () {
        }.getType ();
        String json = new Gson ().toJson (replyMessages, type);
        Log.i ("JSON", "toJson: " + json);
        return replyMessages.isEmpty () ? null : json;
    }

    @TypeConverter
    public static List<VolumeInfo> JsonToList(String json) {
        Gson gson = new Gson ();
        Type type = new TypeToken<List<VolumeInfo>> () {
        }.getType ();
        List<VolumeInfo> replyMessages = gson.fromJson (json, type);
        return replyMessages;
    }*/



    @TypeConverter
    public static String fromString(VolumeInfo value) {
        //String json = new Gson ().toJson (value);
       // Log.i ("JSON", "toJson: " + json);
        return new Gson().toJson(value);

    }

    @TypeConverter
    public static VolumeInfo fromArrayList(String list) {
       /* Gson gson=new Gson ();
        Type type = new TypeToken<VolumeInfo> () {
        }.getType ();
        VolumeInfo volumeInfo=gson.fromJson (String.valueOf (gson),type);
        return volumeInfo;*/
        return new Gson().fromJson(list,VolumeInfo.class);
    }
}
