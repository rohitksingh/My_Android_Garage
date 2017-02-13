package com.example.illuminati.recyclerview;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Illuminati on 2/13/2017.
 */
public class Data {

    private List<Person> list;

    public Data()
    {
        list = new ArrayList<Person>();
        for(int i=0;i<30;i++)
        {
            list.add(new Person("rohit "+i));
        }
    }

    public List<Person> getData()
    {
        return list;
    }



}
