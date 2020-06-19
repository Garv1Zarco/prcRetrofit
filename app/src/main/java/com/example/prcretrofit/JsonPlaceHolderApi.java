package com.example.prcretrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {

    @GET("posts")   // esta es la parte de la url especifica del json o lo que quiera que llamemos api
    Call<List<Post>> getPosts(); //estamos llamando a una lista que contiene los elementos descritos en Post y lo estamos metiendo dentro de un nuevo metodo llamado getPosts()

    @GET("posts/{id}/comments")   // esta es la parte de la url especifica del json o lo que quiera que llamemos api
    Call<List<Comment>> getComments();
}
