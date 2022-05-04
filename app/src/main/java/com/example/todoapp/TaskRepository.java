package com.example.todoapp;

import android.app.Application;
import android.hardware.camera2.params.LensShadingMap;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TaskRepository {

    private TaskDao taskDao;
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

    public TaskRepository(Application application)
    {
        TaskDatabase database = TaskDatabase.getInstance(application);
        taskDao = database.taskDao();
        allTasks = taskDao.getAllTasks();

        allTasksByDESCName = taskDao.getAllTasksByDESCName();
        allTasksByASCName = taskDao.getAllTasksByASCName();
        allTasksByDESCPriority = taskDao.getAllTasksByDESCPriority();
        allTasksByASCPriority = taskDao.getAllTasksByASCPriority();
        allTasksByDESCDate = taskDao.getAllTasksByASCDate();
        allTasksByASCDate = taskDao.getAllTasksByDESCDate();

        allNotDoneTasksByDESCName = taskDao.getAllNotDoneTasksByDESCName();
        allNotDoneTasksByASCName = taskDao.getAllNotDoneTasksByASCName();
        allNotDoneTasksByDESCPriority = taskDao.getAllNotDoneTasksByDESCPriority();
        allNotDoneTasksByASCPriority = taskDao.getAllNotDoneTasksByASCPriority();
        allNotDoneTasksByDESCDate = taskDao.getAllNotDoneTasksByASCDate();
        allNotDoneTasksByASCDate = taskDao.getAllNotDoneTasksByDESCDate();
    }

    public void insert(Task task)
    {
        new InsertTaskAsyncTask(taskDao).execute(task);
    }

    public void update(Task task)
    {
        new UpdateTaskAsyncTask(taskDao).execute(task);
    }

    public void delete(Task task)
    {
        new DeleteTaskAsyncTask(taskDao).execute(task);
    }

    public void deleteAllTasks()
    {
        new DeleteAllTasksAsyncTask(taskDao).execute();
    }

    public void deleteAllDoneTasks()
    {
        new DeleteAllDoneTasksAsyncTask(taskDao).execute();
    }

    public LiveData<List<Task>> getAllTasks(){
        return allTasks;
    }

    //sort
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

    private static class InsertTaskAsyncTask extends AsyncTask<Task, Void, Void>
    {
        private TaskDao taskDao;

        private InsertTaskAsyncTask(TaskDao taskDao)
        {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            taskDao.insert(tasks[0]);
            return null;
        }
    }

    private static class UpdateTaskAsyncTask extends AsyncTask<Task, Void, Void>
    {
        private TaskDao taskDao;

        private UpdateTaskAsyncTask(TaskDao taskDao)
        {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            taskDao.update(tasks[0]);
            return null;
        }
    }

    private static class DeleteTaskAsyncTask extends AsyncTask<Task, Void, Void>
    {
        private TaskDao taskDao;

        private DeleteTaskAsyncTask(TaskDao taskDao)
        {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            taskDao.delete(tasks[0]);
            return null;
        }
    }

    private static class DeleteAllTasksAsyncTask extends AsyncTask<Void, Void, Void>
    {
        private TaskDao taskDao;

        private DeleteAllTasksAsyncTask(TaskDao taskDao)
        {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            taskDao.deleteAllTasks();
            return null;
        }
    }

    private static class DeleteAllDoneTasksAsyncTask extends AsyncTask<Void, Void, Void>
    {
        private TaskDao taskDao;

        private DeleteAllDoneTasksAsyncTask(TaskDao taskDao)
        {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            taskDao.deleteAllDoneTasks();
            return null;
        }
    }
}
