package com.example.todoapp;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@SuppressWarnings("ALL")
@Dao
public interface TaskDao {

    @Insert
    void insert(Task task);

    @Update
    void update(Task task);

    @Delete
    void delete(Task task);

    @Query("DELETE FROM task_table WHERE listNumber = :listNumber")
    void deleteAllTasks(int listNumber);

    @Query("SELECT * FROM task_table WHERE (done = 0 OR done = :isDone) AND listNumber = :listNumber ORDER BY priority DESC")
    LiveData<List<Task>> getAllTasks(int isDone, int listNumber);

    //usuwanie skonczonych taskow

    @Query("DELETE FROM task_table WHERE done = 1 AND listNumber = :listNumber")
    void deleteAllDoneTasks(int listNumber);

    //sortowanie według nazwy

    @Query("SELECT * FROM task_table WHERE (done = 0 OR done = :isDone) AND listNumber = :listNumber ORDER BY title ASC")
    LiveData<List<Task>> getAllTasksByASCName(int isDone, int listNumber);

    @Query("SELECT * FROM task_table WHERE (done = 0 OR done = :isDone) AND listNumber = :listNumber ORDER BY title DESC")
    LiveData<List<Task>> getAllTasksByDESCName(int isDone, int listNumber);

    @Query("SELECT * FROM task_table WHERE (done = 0 OR done = :isDone) AND listNumber = :listNumber  ORDER BY title ASC")
    LiveData<List<Task>> getAllNotDoneTasksByASCName(int isDone, int listNumber);

    @Query("SELECT * FROM task_table WHERE (done = 0 OR done = :isDone) AND listNumber = :listNumber  ORDER BY title DESC")
    LiveData<List<Task>> getAllNotDoneTasksByDESCName(int isDone, int listNumber);

    //sortowanie według priorytetu

    @Query("SELECT * FROM task_table WHERE (done = 0 OR done = :isDone) AND listNumber = :listNumber ORDER BY priority ASC")
    LiveData<List<Task>> getAllTasksByASCPriority(int isDone, int listNumber);

    @Query("SELECT * FROM task_table WHERE (done = 0 OR done = :isDone) AND listNumber = :listNumber ORDER BY priority DESC")
    LiveData<List<Task>> getAllTasksByDESCPriority(int isDone, int listNumber);

    @Query("SELECT * FROM task_table WHERE (done = 0 OR done = :isDone) AND listNumber = :listNumber ORDER BY priority ASC")
    LiveData<List<Task>> getAllNotDoneTasksByASCPriority(int isDone, int listNumber);

    @Query("SELECT * FROM task_table WHERE (done = 0 OR done = :isDone) AND listNumber = :listNumber ORDER BY priority DESC")
    LiveData<List<Task>> getAllNotDoneTasksByDESCPriority(int isDone, int listNumber);

    //sortowanie według daty

    @Query("SELECT * FROM task_table WHERE (done = 0 OR done = :isDone) AND listNumber = :listNumber ORDER BY date ASC")
    LiveData<List<Task>> getAllTasksByASCDate(int isDone, int listNumber);

    @Query("SELECT * FROM task_table WHERE (done = 0 OR done = :isDone) AND listNumber = :listNumber ORDER BY date DESC")
    LiveData<List<Task>> getAllTasksByDESCDate(int isDone, int listNumber);

    @Query("SELECT * FROM task_table WHERE (done = 0 OR done = :isDone) AND listNumber = :listNumber ORDER BY date ASC")
    LiveData<List<Task>> getAllNotDoneTasksByASCDate(int isDone, int listNumber);

    @Query("SELECT * FROM task_table WHERE (done = 0 OR done = :isDone) AND listNumber = :listNumber ORDER BY date DESC")
    LiveData<List<Task>> getAllNotDoneTasksByDESCDate(int isDone, int listNumber);

    // udostępnianie
    @Query("SELECT * FROM task_table WHERE (done = 0 OR done = :isDone) AND listNumber = :listNumber ORDER BY title DESC")
    List<Task> getAllTasksToList(int isDone, int listNumber);
}
