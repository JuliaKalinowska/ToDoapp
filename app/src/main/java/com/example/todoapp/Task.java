package com.example.todoapp;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "task_table")
public class Task {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;

    private String description;

    private int priority;

    private int done;

    private String date;

    public Task(String title, String description, int priority, int done, String date) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.done = done;
        this.date = date;
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
}
