package com.asudsc.retrofit2demo;

public class Post {
    public int userId;
    public int id;
    public String title;
    public String body;

    public String toString(){
        return userId+" "+id+" "+title+" "+body;
    }
}
