package com.example.remindercalendar;

import java.util.ArrayList;
import java.util.Calendar;

public class Task {
    public String title;
    public Calendar due_time;
    public String task_description;
    public ArrayList<Reminder> reminder_list;
    public boolean starred;
}
