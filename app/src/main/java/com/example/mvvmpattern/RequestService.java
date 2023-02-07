package com.example.mvvmpattern;

import com.example.mvvmpattern.model.Books;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RequestService {
    @GET("/books/v1/volumes")
    Call<Books> getSearchResults(@Query(value = "q") String searchText, @Query("startIndex") int startIndex, @Query("orderBy") String orderBy, @Query("maxResults") int maxResults);
}
