package com.example.remindercalendar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

public class AddTaskActivity extends AppCompatActivity {

    public Task task;
    private int yy, mm, dd, hourOfDay, minute;
    public TextView dateTextView;
    public TextView timeTextView;
    public CheckBox addTask_isStarred;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        dateTextView = findViewById(R.id.addTask_date_textview);
        timeTextView = findViewById(R.id.addTask_time_textview);
        addTask_isStarred = findViewById(R.id.addTask_isStarred_checkbox);
    }

    public void showDatePicker(View view) {
        DialogFragment newFragment = new com.example.remindercalendar.DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void showTimePicker(View view) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public void processDatePickerResult(int year, int month, int day) {
        yy = year;
        mm = month;
        dd = day;
        String month_string = Integer.toString(month+1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);
        String dateMessage = (month_string + "/" + day_string + "/" + year_string);
        dateTextView.setText(dateMessage);
    }

    public void processTimePickerResult(int hourOfDay, int minute) {
        String hour_string = Integer.toString(hourOfDay);
        String minute_string = Integer.toString(minute);
        String timeMessage = (hour_string + ":" + minute_string);
        timeTextView.setText(timeMessage);
    }

    @SuppressLint("ResourceType")
    public void saveButton(View view) {
        task.title = getString(R.id.addTask_title_edittext);
        task.due_time.set(yy, mm, dd, hourOfDay, minute);
        task.task_description = getString(R.id.addTask_description_edittext);
        task.starred = addTask_isStarred.isChecked();
        finish();
    }

    public void cancelButton(View view) {
        finish();
    }
}