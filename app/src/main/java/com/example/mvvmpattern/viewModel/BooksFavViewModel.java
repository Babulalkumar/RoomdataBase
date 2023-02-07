package com.example.mvvmpattern.viewModel;


import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mvvmpattern.model.Books;
import com.example.mvvmpattern.model.Item;
import com.example.mvvmpattern.model.Items;
import com.example.mvvmpattern.repository.FavRepository;
import com.example.mvvmpattern.repository.Repository;

import java.util.List;


public class BooksFavViewModel extends AndroidViewModel {



    public LiveData<List<Item>> getAllPostCode;

    public BooksFavViewModel(@NonNull Application application) {
        super (application);
        FavRepository repository = new FavRepository (application);
        this.getAllPostCode = repository.getAllpostCode ();
    }



    public LiveData<List<Item>> getAllpostCode() {
        return getAllPostCode;
    }
}
