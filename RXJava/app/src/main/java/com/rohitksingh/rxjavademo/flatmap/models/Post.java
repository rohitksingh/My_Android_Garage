package com.rohitksingh.rxjavademo.flatmap.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Post {

    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("postId")
    private int postId;

    @Expose
    @SerializedName("title")
    private String title;

    @Expose
    @SerializedName("body")
    private String body;

    private List<Comment> comments;

    public Post(int id, int postId, String title, String body, List<Comment> comments) {
        this.id = id;
        this.postId = postId;
        this.title = title;
        this.body = body;
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
