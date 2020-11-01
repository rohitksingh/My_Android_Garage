package com.asudsc.gsondemo;

public class Case2User {

    public String name;
    public int age;
    public String email;
    public boolean isDeveloper;
    public Case2UserAddress userAddress;

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
