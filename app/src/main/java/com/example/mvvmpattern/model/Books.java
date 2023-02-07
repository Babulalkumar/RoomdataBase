package com.example.mvvmpattern.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Relation;
import androidx.room.TypeConverters;

import com.example.mvvmpattern.Converters;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

//@Entity(tableName = "books")
public class Books {
//    @PrimaryKey(autoGenerate = true)
//    @NonNull
//    private int id;

    @SerializedName("kind")
    @Expose
    private String kind;

    @SerializedName("totalItems")
    @Expose
    private Integer totalItems;
    /**
     *
     */
    @SerializedName("items")
    @Expose
   // @TypeConverters(Converters.class)
  //  @Embedded
    private List<Item> items;

    public Integer getTotalItems() {
        return totalItems;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }


}
