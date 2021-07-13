package com.example.remindercalendar;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Calendar;

@Entity(tableName = "task_table")
public class Task implements Serializable {
    @PrimaryKey
    @NonNull
    //@ColumnInfo(name = "title")
    public String title;
    //@ColumnInfo(name = "due_time")
    public Calendar due_time;
    //@ColumnInfo(name = "description")
    public String task_description;
    //TODO:Convert single reminder into Reminder ArrayList
    //public ArrayList<Reminder> reminder_list;
    //@ColumnInfo(name = "reminder")
    //public Reminder reminder;
    public Calendar reminder_time;
    public boolean starred;

}
