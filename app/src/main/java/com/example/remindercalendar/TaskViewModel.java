package com.example.remindercalendar;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {

    private TaskRepository repository;
    private LiveData<List<Task>> allTasks;

    public TaskViewModel(@NonNull Application application) {
        super(application);
        repository = new TaskRepository(application);
        allTasks = repository.getAllTasks();
    }

    LiveData<List<Task>> getAllTasks() {
        return allTasks;
    }

    public void insert(Task task) {
        repository.insert(task);
    }

    public void deleteATask(String taskTitle) {
        repository.deleteATask(taskTitle);
    }

    public void updateTask(Task task) {
        repository.updateTask(task);
    }

    //public void getImportantTasks() {
    //    allTasks=repository.getImportantTasks();
    //}
}
