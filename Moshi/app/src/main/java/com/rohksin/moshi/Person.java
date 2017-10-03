package com.rohksin.moshi;

/**
 * Created by Illuminati on 10/3/2017.
 */
public class Person {

    private String name;
    private String email;
    private Phone phone;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public String toString()
    {
        return name+" \n"+email+ " \n"+ phone;
    }

}
