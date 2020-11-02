package com.asudsc.retrofit2demo;

import com.google.gson.annotations.SerializedName;

public class Post {

    @SerializedName("userId")
    public int user_id;
    public int id;
    public String title;
    public String body;

    public String toString(){
        return user_id+" "+id+" "+title+" "+body;
    }
}
