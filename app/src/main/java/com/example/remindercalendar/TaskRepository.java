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

    public void deleteATask(String taskTitle) {
        new deleteATaskAsyncTask(taskDAO).execute(taskTitle);
    }

    public void updateTask(Task task) {
        new updateTaskAsyncTask(taskDAO).execute(task);
    }

    //public LiveData<List<Task>> getImportantTasks() {
    //new importantAsyncTask(taskDAO).execute();
    //  allTasks=taskDAO.importantTask();
    //    return allTasks;
    //}

    private static class insertAsyncTask extends AsyncTask<Task, Void, Void> {
        private TaskDAO AsyncTaskDao;

        insertAsyncTask(TaskDAO dao) {
            AsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Task... params) {
            //Log.d("TaskRep task title",params[0].title);
            AsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteATaskAsyncTask extends AsyncTask<String, Void, Void> {
        private TaskDAO mAsyncTaskDao;

        deleteATaskAsyncTask(TaskDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final String... params) {
            mAsyncTaskDao.deleteATask(params[0]);
            return null;
        }
    }

    private static class updateTaskAsyncTask extends AsyncTask<Task, Void, Void> {
        private TaskDAO mAsyncTaskDao;

        updateTaskAsyncTask(TaskDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Task... params) {
            mAsyncTaskDao.updateTask(params[0]);
            return null;
        }
    }
/*
    private static class importantAsyncTask extends AsyncTask<Void, Void, Void> {
        private TaskDAO mAsyncTaskDao;

        importantAsyncTask(TaskDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Void... voids) {
            mAsyncTaskDao.importantTask();
            return null;
        }
    }

 */
}
