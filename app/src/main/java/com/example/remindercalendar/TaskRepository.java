package com.example.remindercalendar;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TaskRepository {
    private TaskDAO taskDAO;
    private LiveData<List<Task>> allTasks;

    TaskRepository(Application application) {
        TaskRoomDatabase database = TaskRoomDatabase.getDatabase(application);
        taskDAO = database.taskDAO();
        allTasks = taskDAO.getAllTasks();
    }

    LiveData<List<Task>> getAllTasks() {
        return allTasks;
    }

    public void insert(Task task) {
        new insertAsyncTask(taskDAO).execute(task);
    }

    private static class insertAsyncTask extends AsyncTask<Task, Void, Void> {
        private TaskDAO AsyncTaskDao;

        insertAsyncTask(TaskDAO dao) {
            AsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Task... params) {
            AsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}