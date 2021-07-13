package com.example.remindercalendar;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TaskDAO {
    @Insert
    void insert(Task task);

    @Query("DELETE FROM task_table")
    void deleteAll();

    @Query("SELECT * from task_table ORDER BY due_time ASC")
    LiveData<List<Task>> getAllTasks();

}
