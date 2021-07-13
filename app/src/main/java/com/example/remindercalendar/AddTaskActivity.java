package com.example.remindercalendar;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

public class AddTaskActivity extends AppCompatActivity {

    public Task task;
    private int yy, mm, dd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
    }


    public void showDatePicker(View view) {
        DialogFragment newFragment = new com.example.remindercalendar.DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void processDatePickerResult(int year, int month, int day) {
        yy = year;
        mm = month;
        dd = day;
        task.due_time.set(year, month, day);
    }

    public void processTimePickerResult(int hourOfDay, int minute) {
        task.due_time.set(yy, mm, dd, hourOfDay, minute);
    }
}