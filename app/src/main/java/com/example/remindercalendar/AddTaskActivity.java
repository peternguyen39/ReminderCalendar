package com.example.remindercalendar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddTaskActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.remindercalendar.REPLY";
    private int yy, mm, dd, hourOfDay, minute;
    public Task task;
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
        hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);

        dateTextView.setText(new SimpleDateFormat("dd-MM-yyyy").format(calendar.getTime()));
        calendar.add(Calendar.HOUR_OF_DAY, 1);
        timeTextView.setText(new SimpleDateFormat("KK:mm aa").format(calendar.getTime()));
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
        calendar.set(year, month, day);
        dateTextView.setText(new SimpleDateFormat("dd-MM-yyyy").format(calendar.getTime()));
    }

    public void processTimePickerResult(int hourOfDay, int minute) {
        this.hourOfDay = hourOfDay;
        this.minute = minute;
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        timeTextView.setText(new SimpleDateFormat("KK:mm aa").format(calendar.getTime()));
    }

    @SuppressLint("ResourceType")
    public void saveButton(View view) {

        Intent intent = new Intent();
        if (!TextUtils.isEmpty(titleEditText.getText().toString())) {

            task = new Task();
            task.title = titleEditText.getText().toString();
            task.due_time = Calendar.getInstance();
            Log.d("Time", String.valueOf(Calendar.getInstance()));
            task.due_time.set(yy, mm, dd, hourOfDay, minute);

            if ((hourOfDay == calendar.get(Calendar.HOUR)) && (minute == calendar.get(Calendar.MINUTE)) && (dd == calendar.get(Calendar.DATE)) && (mm == calendar.get(Calendar.MONTH)) && (yy == calendar.get(Calendar.YEAR))) {
                task.due_time.add(Calendar.HOUR, 1);
            }

            task.task_description = descEditText.getText().toString();
            task.starred = addTask_isStarred.isChecked();
            task.reminder_time = task.due_time;


            intent.putExtra(EXTRA_REPLY, task);
            setResult(RESULT_OK, intent);

        } else {
            setResult(RESULT_CANCELED, intent);
        }
        finish();
    }

    public void cancelButton(View view) {
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
    }

}