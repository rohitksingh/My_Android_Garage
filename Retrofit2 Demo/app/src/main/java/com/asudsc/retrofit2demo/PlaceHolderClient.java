package com.asudsc.retrofit2demo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PlaceHolderClient {

    @GET("/posts")
    Call<List<Post>> getAllPosts();

    @GET("/posts/{post_num}")
    Call<Post> getPost(@Path("post_num") int postNum);

}
