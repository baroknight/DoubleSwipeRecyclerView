package com.example.tododemo.pojo;

import java.io.Serializable;

public class TodoData implements Serializable {
    private String taskID;
    private String title;
    private String subTitle;
    private boolean completed;

    public TodoData(String taskID, String title, String subTitle, boolean completed) {
        this.taskID = taskID;
        this.title = title;
        this.subTitle = subTitle;
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "TodoData{" +
                "taskID='" + taskID + '\'' +
                ", title='" + title + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", completed=" + completed +
                '}';
    }

    public String getTaskID() {
        return taskID;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
