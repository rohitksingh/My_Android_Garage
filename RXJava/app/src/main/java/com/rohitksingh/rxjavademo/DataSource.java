package com.rohitksingh.rxjavademo;

import java.util.ArrayList;
import java.util.List;

public class DataSource {

    public static List<Task> getAllTaskList(){
        List<Task> taskList= new ArrayList();
        taskList.add(new Task("Take the trash", false, 1 ));
        taskList.add(new Task("Practice Leetcode", false, 2 ));
        taskList.add(new Task("Eat lunch", true, 4 ));
        return taskList;
    }
}
