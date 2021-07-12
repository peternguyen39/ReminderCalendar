package com.example.remindercalendar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TaskViewActivity extends AppCompatActivity {
    private TextView taskview_title;
    private TextView taskview_duedate;
    private TextView taskview_duetime;
    private TextView taskview_description;
    private int listPosition;
    private Task currentTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_view);

        Intent intent = getIntent();
        listPosition = intent.getIntExtra("ListPosition", 0);

        taskview_title = findViewById(R.id.taskview_title_text);
        taskview_duedate = findViewById(R.id.taskview_duedate_text);
        taskview_duetime = findViewById(R.id.taskview_duetime_text);
        taskview_description = findViewById(R.id.taskview_description_text);

        //TODO: Retrieve current task from list and display onscreen
        // currentTask=
    }

}