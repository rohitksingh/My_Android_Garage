package com.asudsc.retrofit2demo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PlaceHolderClient {

    @GET("/posts")
    Call<List<Post>> getAllPosts();

}
