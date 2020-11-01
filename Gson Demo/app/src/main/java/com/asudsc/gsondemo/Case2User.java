package com.asudsc.gsondemo;

public class Case2User {

    private String name;
    private int age;
    private String email;
    private boolean isDeveloper;
    private Case2UserAddress userAddress;

    public String toString(){
        return name+" "+age+" "+email+" "+isDeveloper+" "+userAddress.toString();
    }
}

class Case2UserAddress {
    private String city;
    private String country;
    private String houseNumber;
    private String street;

    public String toString(){
        return "Address: "+city+" "+country+" "+houseNumber+" "+street;
    }
}
