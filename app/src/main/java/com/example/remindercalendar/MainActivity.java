package com.example.remindercalendar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.Toolbar;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public TodayListAdapter todayListAdapter;
    private FloatingActionButton fab;
    private RecyclerView todayView;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private RecyclerView menuRecyclerView;
    private List<MenuItem> menuItemList;
    private TaskViewModel taskViewModel;
    public int NEW_TASK_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        actionToolbar();
        setListeners();

        addMenuItemsToMenuList();

        final TodayListAdapter adapter = new TodayListAdapter(this);
        todayView.setAdapter(adapter);
        todayView.setLayoutManager(new LinearLayoutManager(this));

        taskViewModel = ViewModelProviders.of(this).get(TaskViewModel.class);
        taskViewModel.getAllTasks().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                adapter.setTaskList(tasks);
            }
        });
        
    }

    private void addMenuItemsToMenuList() {
        MenuItemAdapter menuItemAdapter = new MenuItemAdapter(menuItemList);

        menuRecyclerView.setAdapter(menuItemAdapter);
        menuRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setListeners() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
                startActivityForResult(intent, NEW_TASK_ACTIVITY_REQUEST_CODE);
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
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        todayView = (RecyclerView) findViewById(R.id.today_view);
        menuRecyclerView = (RecyclerView) findViewById(R.id.menu_list);
        fab = (FloatingActionButton) findViewById(R.id.floating_action_button);

        menuItemList = new ArrayList<MenuItem>();
        menuItemList.add(new MenuItem("All"));
        menuItemList.add(new MenuItem("Today"));
        menuItemList.add(new MenuItem("Important"));
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_TASK_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Intent intent = this.getIntent();
            Bundle bundle = intent.getExtras();
            //TODO: CANNOT GETEXTRA DATA FROM INTENT -> INTENT EXTRAS ARE ALWAYS NULL
            Log.d("Intent received:", String.valueOf(getIntent().getExtras()));
            if (bundle != null) {
                Task task = (Task) getIntent().getSerializableExtra(AddTaskActivity.EXTRA_REPLY);
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