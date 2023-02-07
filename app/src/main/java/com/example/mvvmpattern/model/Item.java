package com.example.mvvmpattern.model;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Relation;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.example.mvvmpattern.Converters;
import com.example.mvvmpattern.ConvertersSale;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


@Entity(tableName = "books")
public class Item {

    @SerializedName("kind")
    @Expose
    private String kind;
    @PrimaryKey
    @SerializedName("id")
    @Expose
    @NonNull
    private String id;
    @TypeConverters(Converters.class)
    @SerializedName("volumeInfo")
    @Expose
    private VolumeInfo volumeInfo;

    @SerializedName("accessInfo")
    @Expose
    @TypeConverters(ConvertersSale.class)
    private AccessInfo accessInfo;


    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public VolumeInfo getVolumeInfo() {
        return volumeInfo;
    }

    public void setVolumeInfo(VolumeInfo volumeInfo) {
        this.volumeInfo = volumeInfo;
    }


    public AccessInfo getAccessInfo() {
        return accessInfo;
    }

    public void setAccessInfo(AccessInfo accessInfo) {
        this.accessInfo = accessInfo;
    }



}