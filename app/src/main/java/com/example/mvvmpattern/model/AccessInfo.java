package com.example.mvvmpattern.model;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@Entity(tableName = "acessInfo")
public class AccessInfo {
    @PrimaryKey(autoGenerate = true)
    private int Id;
    @SerializedName("webReaderLink")
    @Expose
    private String webReaderLink;

    public String getWebReaderLink() {
        return webReaderLink;
    }

    public int getId() {
        return Id;
    }

    public void setWebReaderLink(String webReaderLink) {
        this.webReaderLink = webReaderLink;
    }

    public void setId(int id) {
        Id = id;
    }
}