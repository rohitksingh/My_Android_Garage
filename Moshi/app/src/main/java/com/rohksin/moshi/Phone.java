package com.rohksin.moshi;

/**
 * Created by Illuminati on 10/3/2017.
 */
public class Phone {

    private String home;
    private String mobile;

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String toString()
    {
        return home+" \n"+mobile;
    }

}
