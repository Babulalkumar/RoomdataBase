package com.example.mvvmpattern.repository;

import static android.content.ContentValues.TAG;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.mvvmpattern.dao.BooksDao;
import com.example.mvvmpattern.database.BooksDataBase;
import com.example.mvvmpattern.model.Item;
import com.example.mvvmpattern.model.Items;

import java.util.List;


public class FavRepository {
    public BooksDao postCodeDao;
    public LiveData<List<Item>> getAllpostCode;

    public FavRepository(Application application) {
        BooksDataBase database = BooksDataBase.getInstance (application);
        postCodeDao = database.postCodeDao ();
        getAllpostCode = postCodeDao.getcats ();

    }

    public void insert(List<Item> cats) {
        new InsertAsyncTask (postCodeDao).execute (cats);

    }



    public LiveData<List<Item>> getAllpostCode() {

        return getAllpostCode;
    }



    private class InsertAsyncTask extends AsyncTask<List<Item>, Void, Void> {
        private final BooksDao postCodeDao;

        public InsertAsyncTask(BooksDao catDao) {

            this.postCodeDao = catDao;
        }

        @SafeVarargs
        @Override
        protected final Void doInBackground(List<Item>... lists) {
            // postCodeDao.deleteAll ();
            postCodeDao.insert (lists[0]);
            getAllpostCode = postCodeDao.getcats ();
            // String a=postCodeDao.getcats ().getValue ().get (0).getKind ().toString ();
            Log.d (TAG, "doInBackground: " + postCodeDao.getcats ());
            return null;
        }
    }
}
