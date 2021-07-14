package com.example.remindercalendar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public TasksListAdapter tasksListAdapter;
    private FloatingActionButton fab;
    private RecyclerView tasksView;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private Button filterAll;
    private Button filterToday;
    private Button filterImportant;
    private TaskViewModel taskViewModel;
    public int NEW_TASK_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        actionToolbar();
        setListeners();

        tasksListAdapter = new TasksListAdapter(this);
        tasksView.setAdapter(tasksListAdapter);
        tasksView.setLayoutManager(new LinearLayoutManager(this));

        taskViewModel = ViewModelProviders.of(this).get(TaskViewModel.class);
        taskViewModel.getAllTasks().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                tasksListAdapter.setTaskList(tasks);
            }
        });
        
    }

    private void setListeners() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
                startActivityForResult(intent, NEW_TASK_ACTIVITY_REQUEST_CODE);
            }
        });
        filterAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
        filterImportant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        filterToday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void actionToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.action_icon_menu);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void init() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        filterAll = (Button) findViewById(R.id.filter_all_button);
        filterToday = (Button) findViewById(R.id.filter_today_button);
        filterImportant = (Button) findViewById(R.id.filter_important_button);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tasksView = (RecyclerView) findViewById(R.id.today_view);
        fab = (FloatingActionButton) findViewById(R.id.floating_action_button);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_TASK_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Intent intent = data;
            Bundle bundle = intent.getExtras();
            Log.d("Intent received:", String.valueOf(bundle));
            if (bundle != null) {
                Task task = (Task) data.getSerializableExtra(AddTaskActivity.EXTRA_REPLY);
                Log.d("Task status", "RECEIVED TASK!!!!");
                taskViewModel.insert(task);
            } else Log.d("Task empty", "TASK IS EMPTY AND NOT ADDED!!!");
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    "Cannot add task",
                    Toast.LENGTH_LONG).show();
        }
    }
}