package com.example.mvvmpattern.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.TypeConverters;

import com.example.mvvmpattern.Converters;
import com.example.mvvmpattern.model.Item;
import com.example.mvvmpattern.model.VolumeInfo;

import java.util.List;

@Dao
public interface BooksDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Item> cats);

    @Query("SELECT  * FROM books")
    LiveData<List<Item>> getcats();

    @Query("DELETE FROM books WHERE id = :Id")
    void deleteAll(String Id);

    @TypeConverters(Converters.class)
    @Query("Update  books SET volumeInfo=:volumeInfo WHERE id =:Id")
    void udate(VolumeInfo volumeInfo, String Id);


}
