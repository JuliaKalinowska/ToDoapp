package com.example.todoapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {

    private TaskRepository repository;
    private LiveData<List<Task>> allTasks;

    private LiveData<List<Task>> allTasksByDESCName;
    private LiveData<List<Task>> allTasksByASCName;


    public TaskViewModel(@NonNull Application application) {
        super(application);
        repository = new TaskRepository(application);
        allTasks = repository.getAllTasks();

        allTasksByDESCName = repository.getAllTasksByDESCName();
        allTasksByASCName = repository.getAllTasksByASCName();
    }

    public void insert(Task task)
    {
        repository.insert(task);
    }

    public void update(Task task)
    {
        repository.update(task);
    }

    public void delete(Task task)
    {
        repository.delete(task);
    }

    public void deleteAllTasks ()
    {
        repository.deleteAllTasks();
    }

    public LiveData<List<Task>> getAllTasks()
    {
        return allTasks;
    }

    public LiveData<List<Task>> getAllTasksByDESCName() {return allTasksByDESCName;}

    public LiveData<List<Task>> getAllTasksByASCName() {return allTasksByASCName;}
}
