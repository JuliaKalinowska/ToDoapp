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

    public TaskRepository(Application application)
    {
        TaskDatabase database = TaskDatabase.getInstance(application);
        taskDao = database.taskDao();
        allTasks = taskDao.getAllTasks();

        allTasksByDESCName = taskDao.getAllTasksByDESCName();
        allTasksByASCName = taskDao.getAllTasksByASCName();
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

    public LiveData<List<Task>> getAllTasks(){
        return allTasks;
    }

    //sort
    public LiveData<List<Task>> getAllTasksByDESCName() {return allTasksByDESCName;}
    public LiveData<List<Task>> getAllTasksByASCName() {return allTasksByASCName;}

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
}
