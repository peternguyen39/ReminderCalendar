package com.example.remindercalendar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;

public class TaskViewActivity extends AppCompatActivity {
    private TextView taskview_title;
    private TextView taskview_duetime;
    private TextView taskview_description;
    private CheckBox star;
    private Task currentTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_view);

        Intent intent = getIntent();
        currentTask = (Task) intent.getSerializableExtra("TaskView");
        if (currentTask == null) return;

        taskview_title = findViewById(R.id.taskview_title_text);
        taskview_duetime = findViewById(R.id.taskview_duetime_text);
        taskview_description = findViewById(R.id.taskview_description_text);
        //star=findViewById(R.id.st);

        Log.d("TaskView", String.valueOf(currentTask));
        Log.d("TaskView", String.valueOf(currentTask.title));
        Log.d("TaskView", String.valueOf(currentTask.due_time));
        Log.d("TaskView", String.valueOf(currentTask.reminder_time));

        taskview_title.setText(currentTask.title);
        taskview_description.setText(currentTask.task_description);
        taskview_duetime.setText(new SimpleDateFormat("EEE, dd-MM-yyyy hh:mm").format(currentTask.due_time.getTime()));
        //star.setChecked(currentTask.starred);

        finish();
    }

}