package com.example.todoapp;

import android.app.Application;
import android.hardware.camera2.params.LensShadingMap;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TaskRepository {

    private final TaskDao taskDao;
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

    public TaskRepository(Application application) {
        TaskDatabase database = TaskDatabase.getInstance(application);
        taskDao = database.taskDao();
        /*allTasks = taskDao.getAllTasks();

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
        allNotDoneTasksByASCDate = taskDao.getAllNotDoneTasksByDESCDate();*/
    }

    public void insert(Task task) {
        new InsertTaskAsyncTask(taskDao).execute(task);
    }

    public void update(Task task) {
        new UpdateTaskAsyncTask(taskDao).execute(task);
    }

    public void delete(Task task) {
        new DeleteTaskAsyncTask(taskDao).execute(task);
    }

    public void deleteAllTasks(int listNumber) {
        new DeleteAllTasksAsyncTask(taskDao, listNumber).execute();
    }

    public void deleteAllDoneTasks(int listNumber) {
        new DeleteAllDoneTasksAsyncTask(taskDao, listNumber).execute();
    }

    public LiveData<List<Task>> getAllTasks(int isDone, int listNumber) {
        return taskDao.getAllTasks(isDone, listNumber);
    }

    //sort
    public LiveData<List<Task>> getAllTasksByDESCName(int isDone, int listNumber) {
        return taskDao.getAllTasksByDESCName(isDone, listNumber);
    }

    public LiveData<List<Task>> getAllTasksByASCName(int isDone, int listNumber) {
        return taskDao.getAllTasksByASCName(isDone, listNumber);
    }

    public LiveData<List<Task>> getAllTasksByDESCPriority(int isDone, int listNumber) {
        return taskDao.getAllTasksByDESCPriority(isDone, listNumber);
    }

    public LiveData<List<Task>> getAllTasksByASCPriority(int isDone, int listNumber) {
        return taskDao.getAllTasksByASCPriority(isDone, listNumber);
    }

    public LiveData<List<Task>> getAllTasksByDESCDate(int isDone, int listNumber) {
        return taskDao.getAllTasksByDESCDate(isDone, listNumber);
    }

    public LiveData<List<Task>> getAllTasksByASCDate(int isDone, int listNumber) {
        return taskDao.getAllTasksByASCDate(isDone, listNumber);
    }

    public LiveData<List<Task>> getAllNotDoneTasksByDESCName(int isDone, int listNumber) {
        return taskDao.getAllNotDoneTasksByDESCName(isDone, listNumber);
    }

    public LiveData<List<Task>> getAllNotDoneTasksByASCName(int isDone, int listNumber) {
        return taskDao.getAllNotDoneTasksByASCName(isDone, listNumber);
    }

    public LiveData<List<Task>> getAllNotDoneTasksByDESCPriority(int isDone, int listNumber) {
        return taskDao.getAllNotDoneTasksByDESCPriority(isDone, listNumber);
    }

    public LiveData<List<Task>> getAllNotDoneTasksByASCPriority(int isDone, int listNumber) {
        return taskDao.getAllNotDoneTasksByASCPriority(isDone, listNumber);
    }

    public LiveData<List<Task>> getAllNotDoneTasksByDESCDate(int isDone, int listNumber) {
        return taskDao.getAllNotDoneTasksByDESCDate(isDone, listNumber);
    }

    public LiveData<List<Task>> getAllNotDoneTasksByASCDate(int isDone, int listNumber) {
        return taskDao.getAllNotDoneTasksByASCDate(isDone, listNumber);
    }

    private static class InsertTaskAsyncTask extends AsyncTask<Task, Void, Void> {
        private final TaskDao taskDao;

        private InsertTaskAsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            taskDao.insert(tasks[0]);
            return null;
        }
    }

    private static class UpdateTaskAsyncTask extends AsyncTask<Task, Void, Void> {
        private final TaskDao taskDao;

        private UpdateTaskAsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            taskDao.update(tasks[0]);
            return null;
        }
    }

    private static class DeleteTaskAsyncTask extends AsyncTask<Task, Void, Void> {
        private final TaskDao taskDao;

        private DeleteTaskAsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            taskDao.delete(tasks[0]);
            return null;
        }
    }

    private static class DeleteAllTasksAsyncTask extends AsyncTask<Void, Void, Void> {
        private final TaskDao taskDao;
        private final int listNumber;

        private DeleteAllTasksAsyncTask(TaskDao taskDao, int listNumber) {
            this.taskDao = taskDao;
            this.listNumber = listNumber;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            taskDao.deleteAllTasks(listNumber);
            return null;
        }
    }

    private static class DeleteAllDoneTasksAsyncTask extends AsyncTask<Void, Void, Void> {
        private final TaskDao taskDao;
        private final int listNumber;

        private DeleteAllDoneTasksAsyncTask(TaskDao taskDao, int listNumber) {
            this.taskDao = taskDao;
            this.listNumber = listNumber;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            taskDao.deleteAllDoneTasks(listNumber);
            return null;
        }
    }
}
