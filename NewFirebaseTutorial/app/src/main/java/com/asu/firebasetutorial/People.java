package com.asu.firebasetutorial;

import java.util.UUID;

public class People {

    private String name;
    private String address;

    private UUID uuid = UUID.randomUUID();    //<-- GET UNIQUE ID SO THAT EACH TIME A NEW PROFILE IS CREATED
    private String uid = uuid.toString();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUID(){
        return uid;
    }

}
