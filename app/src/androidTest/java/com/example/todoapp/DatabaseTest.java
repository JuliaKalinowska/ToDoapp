package com.example.todoapp;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class DatabaseTest {
    @Test
    public void insertTest() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        TaskDatabase database = TaskDatabase.getInstance(appContext);
        TaskDao taskDao = database.taskDao();
        taskDao.deleteAllTasks(4);
        Task task = new Task("test-title", "description", 1, 1, "2018-09-16", 4);
        taskDao.insert(task);
        List<Task> result = taskDao.getListForTest(1, 4);
        Task result_task = result.get(0);
        assertEquals("test-title", result_task.getTitle());
    }

    @Test
    public void deleteAllDoneTest() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        TaskDatabase database = TaskDatabase.getInstance(appContext);
        TaskDao taskDao = database.taskDao();
        taskDao.deleteAllTasks(4);
        Task task = new Task("test-title", "description", 1, 1, "2018-09-16", 4);
        taskDao.insert(task);
        taskDao.deleteAllDoneTasks(4);
        List<Task> result = taskDao.getListForTest(1, 4);
        assertEquals(true, result.isEmpty());
    }
}