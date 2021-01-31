package com.rohitksingh.rxjavademo.flatmap.requests;

import com.rohitksingh.rxjavademo.flatmap.models.Comment;
import com.rohitksingh.rxjavademo.flatmap.models.Post;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RequestAPI {

    @GET("posts")
    Observable<List<Post>> getAllPost();

    @GET("post/{id}/comments")
    Observable<List<Comment>> getAllComments(@Path("id") int id);
}
