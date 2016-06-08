package com.example.hieunt.retrofitjsonreade;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by HieuNT on 6/8/2016.
 */
public interface BookAPI {
    @GET("/api/json/get/bSebvpSyPS?indent=2")
    public void getBooks(Callback<List<Book>> response);
}
