package com.example.remindercalendar;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public TodayListAdapter todayListAdapter;
    private FloatingActionButton fab;
    private RecyclerView todayView;
    private TaskViewModel taskViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        todayView = findViewById(R.id.today_view);
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
        //fab.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        return;
        //    }
        //});
    }


}