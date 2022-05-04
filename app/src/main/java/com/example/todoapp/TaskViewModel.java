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
    private LiveData<List<Task>> allTasksByDESCPriority;
    private LiveData<List<Task>> allTasksByASCPriority;
    private LiveData<List<Task>> allTasksByDESCDate;
    private LiveData<List<Task>> allTasksByASCDate;

    private LiveData<List<Task>> allNotDoneTasksByDESCName;
    private LiveData<List<Task>> allNotDoneTasksByASCName;
    private LiveData<List<Task>> allNotDoneTasksByDESCPriority;
    private LiveData<List<Task>> allNotDoneTasksByASCPriority;
    private LiveData<List<Task>> allNotDoneTasksByDESCDate;
    private LiveData<List<Task>> allNotDoneTasksByASCDate;


    public TaskViewModel(@NonNull Application application) {
        super(application);
        repository = new TaskRepository(application);
        allTasks = repository.getAllTasks();

        allTasksByDESCName = repository.getAllTasksByDESCName();
        allTasksByASCName = repository.getAllTasksByASCName();
        allTasksByDESCPriority = repository.getAllTasksByDESCPriority();
        allTasksByASCPriority = repository.getAllTasksByASCPriority();
        allTasksByDESCDate = repository.getAllTasksByASCDate();
        allTasksByASCDate = repository.getAllTasksByDESCDate();

        allNotDoneTasksByDESCName = repository.getAllNotDoneTasksByDESCName();
        allNotDoneTasksByASCName = repository.getAllNotDoneTasksByASCName();
        allNotDoneTasksByDESCPriority = repository.getAllNotDoneTasksByDESCPriority();
        allNotDoneTasksByASCPriority = repository.getAllNotDoneTasksByASCPriority();
        allNotDoneTasksByDESCDate = repository.getAllNotDoneTasksByASCDate();
        allNotDoneTasksByASCDate = repository.getAllNotDoneTasksByDESCDate();
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

    public void deleteAllDoneTasks() { repository.deleteAllDoneTasks(); }

    public LiveData<List<Task>> getAllTasks()
    {
        return allTasks;
    }

    public LiveData<List<Task>> getAllTasksByDESCName() {return allTasksByDESCName;}

    public LiveData<List<Task>> getAllTasksByASCName() {return allTasksByASCName;}

    public LiveData<List<Task>> getAllTasksByDESCPriority() {return allTasksByDESCPriority;}

    public LiveData<List<Task>> getAllTasksByASCPriority() {return allTasksByASCPriority;}

    public LiveData<List<Task>> getAllTasksByDESCDate() {return allTasksByDESCDate;}

    public LiveData<List<Task>> getAllTasksByASCDate() {return allTasksByASCDate;}

    public LiveData<List<Task>> getAllNotDoneTasksByDESCName() {return allNotDoneTasksByDESCName;}

    public LiveData<List<Task>> getAllNotDoneTasksByASCName() {return allNotDoneTasksByASCName;}

    public LiveData<List<Task>> getAllNotDoneTasksByDESCPriority() {return allNotDoneTasksByDESCPriority;}

    public LiveData<List<Task>> getAllNotDoneTasksByASCPriority() {return allNotDoneTasksByASCPriority;}

    public LiveData<List<Task>> getAllNotDoneTasksByDESCDate() {return allNotDoneTasksByDESCDate;}

    public LiveData<List<Task>> getAllNotDoneTasksByASCDate() {return allNotDoneTasksByASCDate;}
}
