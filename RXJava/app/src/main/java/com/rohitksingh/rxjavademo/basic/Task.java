package com.rohitksingh.rxjavademo.basic;

public class Task {

    int priority;
    boolean isComplete;
    String description;

    public Task() { }

    public Task(String description, boolean isComplete, int priority) {
        super();
        this.priority = priority;
        this.isComplete = isComplete;
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
