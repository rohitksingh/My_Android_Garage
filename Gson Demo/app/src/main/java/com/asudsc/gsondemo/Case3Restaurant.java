package com.asudsc.gsondemo;

import java.util.List;

public class Case3Restaurant {

    public String name;
    public List<Menu> menu;

    public String toString(){
        return name+" "+menu.toString();
    }
}

class Menu{

    public String description;
    public double price;

    public String toString(){
        return description+" "+price;
    }

}
