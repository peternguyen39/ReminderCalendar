package com.example.remindercalendar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class AddTaskActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.remindercalendar.REPLY";

    public Task task;
    private int yy, mm, dd, hourOfDay, minute;
    public TextView dateTextView;
    public TextView timeTextView;
    public CheckBox addTask_isStarred;
    private EditText titleEditText;
    private EditText descEditText;

    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        dateTextView = findViewById(R.id.addTask_date_textview);
        timeTextView = findViewById(R.id.addTask_time_textview);
        addTask_isStarred = findViewById(R.id.addTask_isStarred_checkbox);
        titleEditText = findViewById(R.id.addTask_title_edittext);
        descEditText = findViewById(R.id.addTask_description_edittext);


        //set default date for Calendar
        calendar = Calendar.getInstance();
        dd = calendar.get(Calendar.DAY_OF_MONTH);
        mm = calendar.get(Calendar.MONTH);
        yy = calendar.get(Calendar.YEAR);
        hourOfDay = calendar.get(Calendar.HOUR);
        minute = calendar.get(Calendar.MINUTE);
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
        this.hourOfDay = hourOfDay;
        this.minute = minute;
        timeTextView.setText(timeMessage);
    }

    @SuppressLint("ResourceType")
    public void saveButton(View view) {

        Intent intent = new Intent();
        Log.d("saveButton", "CLICKED!!!");
        if (!TextUtils.isEmpty(titleEditText.getText().toString())) {
            Log.d("Title", titleEditText.getText().toString());
            task = new Task();
            task.title = titleEditText.getText().toString();
            task.due_time = Calendar.getInstance();
            Log.d("Calendar", dd + "/" + mm + "/" + yy + " " + hourOfDay + ":" + minute);
            task.due_time.set(yy, mm, dd, hourOfDay, minute);
            task.task_description = descEditText.getText().toString();
            task.starred = addTask_isStarred.isChecked();
            task.reminder_time = task.due_time;

            Log.d("TASK title", task.title);
            Log.d("TASK due time", String.valueOf(task.due_time));
            Log.d("TASK starred", String.valueOf(task.starred));
            Log.d("TASK description", task.task_description);

            intent.putExtra(EXTRA_REPLY, task);
            setResult(RESULT_OK, intent);
            Log.d("TASK SENT", "TASK IS SENT IN INTENT!!!");
        } else {
            setResult(RESULT_CANCELED, intent);
        }
        finish();
    }

    public void cancelButton(View view) {
        finish();
    }

}