package com.example.remindercalendar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        actionToolbar();
        setListeners();
        addMenuItemsToMenuList();
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
                openAddTaskActivity();
            }
        });
    }

    private void openAddTaskActivity() {
        Intent intent = new Intent(this, AddTaskActivity.class);

        startActivity(intent);
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
        toolbar.setTitle("Remindinator");
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


}