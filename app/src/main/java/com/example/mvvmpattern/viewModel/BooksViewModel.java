package com.example.mvvmpattern.viewModel;


import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvmpattern.model.Books;
import com.example.mvvmpattern.repository.Repository;


public class BooksViewModel extends AndroidViewModel {

    private Repository repository;

    private LiveData<Books> listOfBooks;

    public BooksViewModel(@NonNull Application application) {
        super (application);
    }

    public void getBooks() {
        repository = new Repository ();
        listOfBooks = repository.getallBooks ();
    }

    public void getAllCategoryData(String query,int index,String orderBy,int count) {
        repository.getListOfBooksOutputs (query, index,orderBy,count);
    }

    public LiveData<Books> getCategoryBeanLiveData() {
        return listOfBooks;
    }
}
