package com.example.todoapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import java.util.List;
import java.util.Objects;

public class TaskViewModel extends AndroidViewModel {

    private final TaskRepository repository;
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

    public LiveData<List<Task>> getData() {
        return data;
    }

    public List<String> getList(int isDone, int listNumber) {
        return repository.getList(isDone, listNumber);
    }

    public void updateData(int isDone, int listNumber, String column) {
        if (Objects.equals(column, "title")) {
            data.removeSource(sourceData);
            sourceData = repository.getAllTasksByASCName(isDone, listNumber);
            data.addSource(sourceData, value -> data.setValue(value));
        } else if (Objects.equals(column, "date")) {
            data.removeSource(sourceData);
            sourceData = repository.getAllTasksByASCDate(isDone, listNumber);
            data.addSource(sourceData, value -> data.setValue(value));
        } else {
            data.removeSource(sourceData);
            sourceData = repository.getAllTasksByASCPriority(isDone, listNumber);
            data.addSource(sourceData, value -> data.setValue(value));
        }
    }
}
