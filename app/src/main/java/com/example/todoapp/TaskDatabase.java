package com.example.todoapp;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Task.class, version = 4)
public abstract class TaskDatabase extends RoomDatabase {

    private static TaskDatabase instance;

    public abstract TaskDao taskDao();

    public static synchronized TaskDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    TaskDatabase.class, "task_database").fallbackToDestructiveMigration().addCallback(roomCallback).build();

        }
        return instance;
    }

    private static final RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private final TaskDao taskDao;

        private PopulateDbAsyncTask(TaskDatabase db) {
            taskDao = db.taskDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            taskDao.insert(new Task("Nazwa 1", "Opis 1", 1, 0, "1979-09-17", 1));
            taskDao.insert(new Task("Nazwa 2", "Opis 2", 2, 0, "2018-09-17", 2));
            taskDao.insert(new Task("Nazwa 3", "Opis 3", 3, 1, "2019-09-17", 3));
            return null;
        }
    }
}
