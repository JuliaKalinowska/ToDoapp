package com.example.todoapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TaskRepository {

    private final TaskDao taskDao;

    public TaskRepository(Application application) {
        TaskDatabase database = TaskDatabase.getInstance(application);
        taskDao = database.taskDao();
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

    public LiveData<List<Task>> getAllTasksByASCName(int isDone, int listNumber) {
        return taskDao.getAllTasksByASCName(isDone, listNumber);
    }

    public LiveData<List<Task>> getAllTasksByASCPriority(int isDone, int listNumber) {
        return taskDao.getAllTasksByASCPriority(isDone, listNumber);
    }

    public LiveData<List<Task>> getAllTasksByASCDate(int isDone, int listNumber) {
        return taskDao.getAllTasksByASCDate(isDone, listNumber);
    }

    public List<String> getList(int isDone, int listNumber) {
        return taskDao.getList(isDone, listNumber);
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
