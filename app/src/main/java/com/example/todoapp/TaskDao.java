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

    @Query("DELETE FROM task_table")
    void deleteAllTasks();

    @Query("SELECT * FROM task_table ORDER BY priority DESC")
    LiveData<List<Task>> getAllTasks();

    //usuwanie skonczonych taskow

    @Query("DELETE FROM task_table WHERE done = 1")
    void deleteAllDoneTasks();

    //sortowanie według nazwy

    @Query("SELECT * FROM task_table ORDER BY title ASC")
    LiveData<List<Task>> getAllTasksByASCName();

    @Query("SELECT * FROM task_table ORDER BY title DESC")
    LiveData<List<Task>> getAllTasksByDESCName();

    @Query("SELECT * FROM task_table WHERE done = 0 ORDER BY title ASC")
    LiveData<List<Task>> getAllNotDoneTasksByASCName();

    @Query("SELECT * FROM task_table WHERE done = 0 ORDER BY title DESC")
    LiveData<List<Task>> getAllNotDoneTasksByDESCName();

    //sortowanie według priorytetu

    @Query("SELECT * FROM task_table ORDER BY priority ASC")
    LiveData<List<Task>> getAllTasksByASCPriority();

    @Query("SELECT * FROM task_table ORDER BY priority DESC")
    LiveData<List<Task>> getAllTasksByDESCPriority();

    @Query("SELECT * FROM task_table WHERE done = 0 ORDER BY priority ASC")
    LiveData<List<Task>> getAllNotDoneTasksByASCPriority();

    @Query("SELECT * FROM task_table WHERE done = 0 ORDER BY priority DESC")
    LiveData<List<Task>> getAllNotDoneTasksByDESCPriority();

    //sortowanie według daty

    @Query("SELECT * FROM task_table ORDER BY date ASC")
    LiveData<List<Task>> getAllTasksByASCDate();

    @Query("SELECT * FROM task_table ORDER BY date DESC")
    LiveData<List<Task>> getAllTasksByDESCDate();

    @Query("SELECT * FROM task_table WHERE done = 0 ORDER BY date ASC")
    LiveData<List<Task>> getAllNotDoneTasksByASCDate();

    @Query("SELECT * FROM task_table WHERE done = 0 ORDER BY date DESC")
    LiveData<List<Task>> getAllNotDoneTasksByDESCDate();

}
