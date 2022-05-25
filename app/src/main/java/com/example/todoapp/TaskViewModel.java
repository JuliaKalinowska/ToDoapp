package com.example.todoapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class TaskViewModel extends AndroidViewModel {

    private final TaskRepository repository;
    private LiveData<List<Task>> allTasks;
    //
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
    private MediatorLiveData<List<Task>> data = new MediatorLiveData<>();
    private LiveData<List<Task>> sourceData;
    private int isDone = 1;
    private int listNumber = 1;
    private String column = "title";


    public TaskViewModel(@NonNull Application application) {
        super(application);
        repository = new TaskRepository(application);
        sourceData = repository.getAllTasks(isDone, listNumber);
        data.addSource(sourceData, value -> data.setValue(value));
        /*allTasks = repository.getAllTasks();

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
        allNotDoneTasksByASCDate = repository.getAllNotDoneTasksByDESCDate();*/
    }

    public void insert(Task task) {
        repository.insert(task);
    }

    public void update(Task task) {
        repository.update(task);
    }

    public void delete(Task task) {
        repository.delete(task);
    }

    public void deleteAllTasks(int listNumber) {
        repository.deleteAllTasks(listNumber);
    }

    public void deleteAllDoneTasks(int listNumber) {
        repository.deleteAllDoneTasks(listNumber);
    }

    public LiveData<List<Task>> getAllTasks(int isDone, int listNumber) {
        return repository.getAllTasks(isDone, listNumber);
    }

    public LiveData<List<Task>> getAllTasksByDESCName(int isDone, int listNumber) {
        return repository.getAllTasksByDESCName(isDone, listNumber);
    }

    public LiveData<List<Task>> getAllTasksByASCName(int isDone, int listNumber) {
        return repository.getAllTasksByASCName(isDone, listNumber);
    }

    public LiveData<List<Task>> getAllTasksByDESCPriority(int isDone, int listNumber) {
        return repository.getAllTasksByDESCPriority(isDone, listNumber);
    }

    public LiveData<List<Task>> getAllTasksByASCPriority(int isDone, int listNumber) {
        return repository.getAllTasksByASCPriority(isDone, listNumber);
    }

    public LiveData<List<Task>> getAllTasksByDESCDate(int isDone, int listNumber) {
        return repository.getAllTasksByDESCDate(isDone, listNumber);
    }

    public LiveData<List<Task>> getAllTasksByASCDate(int isDone, int listNumber) {
        return repository.getAllTasksByASCDate(isDone, listNumber);
    }

    public LiveData<List<Task>> getAllNotDoneTasksByDESCName(int isDone, int listNumber) {
        return repository.getAllNotDoneTasksByDESCName(isDone, listNumber);
    }

    public LiveData<List<Task>> getAllNotDoneTasksByASCName(int isDone, int listNumber) {
        return repository.getAllNotDoneTasksByASCName(isDone, listNumber);
    }

    public LiveData<List<Task>> getAllNotDoneTasksByDESCPriority(int isDone, int listNumber) {
        return repository.getAllNotDoneTasksByDESCPriority(isDone, listNumber);
    }

    public LiveData<List<Task>> getAllNotDoneTasksByASCPriority(int isDone, int listNumber) {
        return repository.getAllNotDoneTasksByASCPriority(isDone, listNumber);
    }

    public LiveData<List<Task>> getAllNotDoneTasksByDESCDate(int isDone, int listNumber) {
        return repository.getAllNotDoneTasksByDESCDate(isDone, listNumber);
    }

    public LiveData<List<Task>> getAllNotDoneTasksByASCDate(int isDone, int listNumber) {
        return repository.getAllNotDoneTasksByASCDate(isDone, listNumber);
    }

    public List<Task> getAllTasksToList(int isDone, int listNumber) {
        return repository.getAllTasksToList(isDone, listNumber);
    }

    public LiveData<List<Task>> getData() {
        return data;
    }

    public List<String> getList(int isDone, int listNumber) {
        return repository.getList(isDone, listNumber);
    }

    public void updateData(int isDone, int listNumber, String column) {
        if (column == "title") {
            data.removeSource(sourceData);
            sourceData = repository.getAllTasksByASCName(isDone, listNumber);
            data.addSource(sourceData, value -> data.setValue(value));
        }
        else if (column == "date") {
            data.removeSource(sourceData);
            sourceData = repository.getAllTasksByASCDate(isDone, listNumber);
            data.addSource(sourceData, value -> data.setValue(value));
        }
        else {
            data.removeSource(sourceData);
            sourceData = repository.getAllTasksByASCPriority(isDone, listNumber);
            data.addSource(sourceData, value -> data.setValue(value));
        }
    }
}
