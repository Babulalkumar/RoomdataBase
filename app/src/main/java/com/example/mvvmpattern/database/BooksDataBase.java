package com.example.mvvmpattern.database;


import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.example.mvvmpattern.dao.BooksDao;
import com.example.mvvmpattern.model.AccessInfo;
import com.example.mvvmpattern.model.ImageLinks;
import com.example.mvvmpattern.model.Item;
import com.example.mvvmpattern.model.Pdf;
import com.example.mvvmpattern.model.ReadingModes;

@Database(entities = {Item.class, AccessInfo.class, Pdf.class, ReadingModes.class, ImageLinks.class}, version = 1,exportSchema = false)
//@TypeConverters(Converters.class)
public abstract class BooksDataBase extends RoomDatabase {

    private static final String DATABASE_NAME = "book";

    public abstract BooksDao postCodeDao();

    public static volatile BooksDataBase INSTANCE = null;

    public static BooksDataBase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (BooksDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder (context, BooksDataBase.class, DATABASE_NAME)
                            .fallbackToDestructiveMigration ()
                            .addCallback (callback)
                            .build ();
                }
            }
        }
        return INSTANCE;
    }

    public static Callback callback = new Callback () {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen (db);
            new PopulateDbAsyn (INSTANCE);
        }
    };

    static class PopulateDbAsyn extends AsyncTask<Void, Void, Void> {
        private final BooksDao postCodeDao;

        public PopulateDbAsyn(BooksDataBase postCodeDataBase) {
            postCodeDao = postCodeDataBase.postCodeDao ();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            // postCodeDao.deleteAll ();
            return null;
        }
    }
}
