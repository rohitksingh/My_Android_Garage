package com.rohksin.firebaserealtimedb;

/**
 * Created by Illuminati on 10/21/2017.
 */

public class Movie {

    private String name;
    private String image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString()
    {
        return name+" \n"+image;
    }

}
