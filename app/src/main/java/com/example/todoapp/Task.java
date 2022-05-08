package com.example.todoapp;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "task_table")
public class Task {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private final String title;

    private final String description;

    private final int priority;

    private final int done;

    private final String date;

    private final int listNumber;

    public Task(String title, String description, int priority, int done, String date, int listNumber) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.done = done;
        this.date = date;
        this.listNumber = listNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    public int getDone() {
        return done;
    }

    public String getDate() {
        return date;
    }

    public int getListNumber() {
        return listNumber;
    }
}
