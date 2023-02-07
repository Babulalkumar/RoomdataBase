package com.example.mvvmpattern.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvmpattern.ApiClient;
import com.example.mvvmpattern.RequestService;
import com.example.mvvmpattern.model.Books;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class Repository {
    private MutableLiveData<Books> listOfBooks = new MutableLiveData<> ();

    public Repository() {
        listOfBooks = new MutableLiveData<> ();
    }

    public void getListOfBooksOutputs(String query, int index, String category, int page) {

        RequestService api = ApiClient.getClient ().create (RequestService.class);
        Call<Books> listOfMovieOut = api.getSearchResults (query, index, category, page);
        listOfMovieOut.enqueue (new Callback<Books> () {
            @Override
            public void onResponse(@NonNull Call<Books> call, @NonNull Response<Books> response) {
                listOfBooks.setValue (response.body ());
            }

            @Override
            public void onFailure(@NonNull Call<Books> call, @NonNull Throwable t) {
                listOfBooks.postValue (null);
            }
        });
    }

    public LiveData<Books> getallBooks() {
        return listOfBooks;
    }
}