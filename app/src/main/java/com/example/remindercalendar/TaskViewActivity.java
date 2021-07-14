package com.example.remindercalendar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TaskViewActivity extends AppCompatActivity {
    private TextView taskview_title;
    private TextView taskview_duetime;
    private TextView taskview_duedate;
    private EditText taskview_description;
    private Button taskview_editButton;
    private Button taskview_dateButton;
    private Button taskview_timeButton;
    private Button taskview_saveButton;
    private Button taskview_cancelButton;
    private CheckBox star;
    private Task currentTask;
    private Calendar calendar;

    static boolean active = false;

    @Override
    public void onStart() {
        super.onStart();
        active = true;
    }

    @Override
    public void onStop() {
        super.onStop();
        active = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_view);

        Intent intent = getIntent();
        currentTask = (Task) intent.getSerializableExtra("TaskView");
        if (currentTask == null) return;
        taskview_title = findViewById(R.id.taskview_title_text);
        taskview_duedate = findViewById(R.id.taskview_duedate_text);
        taskview_duetime = findViewById(R.id.taskview_duetime_text);
        taskview_description = findViewById(R.id.taskview_description_text);
        taskview_editButton = findViewById(R.id.taskview_edit_button);
        taskview_dateButton = findViewById(R.id.taskview_date_button);
        taskview_timeButton = findViewById(R.id.taskview_time_button);
        taskview_saveButton = findViewById(R.id.taskview_save_button);
        taskview_cancelButton = findViewById(R.id.taskview_cancel_button);
        star=findViewById(R.id.taskview_isStarred_checkbox);

        Log.d("TaskView", String.valueOf(currentTask));
        Log.d("TaskView", String.valueOf(currentTask.title));
        Log.d("TaskView", String.valueOf(currentTask.due_time));
        Log.d("TaskView", String.valueOf(currentTask.reminder_time));

        initialState();
    }

    public void initialState() {
        taskview_title.setText(currentTask.title);
        taskview_description.setText(currentTask.task_description);
        taskview_duedate.setText(new SimpleDateFormat("dd-MM-yyyy").format(currentTask.due_time.getTime()));
        taskview_duetime.setText(new SimpleDateFormat("KK:mm aa").format(currentTask.due_time.getTime()));
        star.setChecked(currentTask.starred);
        calendar = currentTask.due_time;

        taskview_title.setEnabled(false);
        taskview_description.setEnabled(false);
        taskview_editButton.setEnabled(true);
        taskview_editButton.setVisibility(View.VISIBLE);
        taskview_dateButton.setEnabled(false);
        taskview_dateButton.setVisibility(View.INVISIBLE);
        taskview_timeButton.setEnabled(false);
        taskview_timeButton.setVisibility(View.INVISIBLE);
        taskview_saveButton.setEnabled(false);
        taskview_saveButton.setVisibility(View.INVISIBLE);
        taskview_cancelButton.setEnabled(false);
        taskview_cancelButton.setVisibility(View.INVISIBLE);
    }

    public void showDatePicker(View view) {
        DialogFragment newFragment = new com.example.remindercalendar.DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void processDatePickerResult(int year, int month, int day) {
        calendar.set(year, month, day);
        taskview_duedate.setText(new SimpleDateFormat("dd-MM-yyyy").format(calendar.getTime()));
    }

    public void showTimePicker(View view) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public void processTimePickerResult(int hourOfDay, int minute) {
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        taskview_duedate.setText(new SimpleDateFormat("KK:mm aa").format(calendar.getTime()));
    }


    public void editTask(View view) {
        taskview_description.setEnabled(true);
        taskview_editButton.setEnabled(false);
        taskview_editButton.setVisibility(View.INVISIBLE);
        taskview_dateButton.setEnabled(true);
        taskview_dateButton.setVisibility(View.VISIBLE);
        taskview_timeButton.setEnabled(true);
        taskview_timeButton.setVisibility(View.VISIBLE);
        taskview_saveButton.setEnabled(true);
        taskview_saveButton.setVisibility(View.VISIBLE);
        taskview_cancelButton.setEnabled(true);
        taskview_cancelButton.setVisibility(View.VISIBLE);
    }

    public void saveTask(View view) {
        currentTask.title = taskview_title.getText().toString();
        currentTask.due_time = calendar;
        currentTask.task_description = taskview_description.getText().toString();
        Intent intent = new Intent();
        intent.putExtra("EditedTask", currentTask);
        setResult(RESULT_OK, intent);
        initialState();
        //finish();
    }

    public void cancelEdit(View view) {
        initialState();
        Intent intent = new Intent();
        setResult(RESULT_CANCELED);
        //finish();
    }

    public void tickStarred(View view) {
        currentTask.starred = star.isChecked();
    }

    public void back(View view) {
        Intent intent = new Intent();
        intent.putExtra("EditedTask", currentTask);
        setResult(RESULT_OK, intent);
        finish();
    }
}