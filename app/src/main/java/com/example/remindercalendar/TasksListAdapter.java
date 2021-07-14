package com.example.remindercalendar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;

public class TasksListAdapter extends RecyclerView.Adapter<TasksListAdapter.TodayViewHolder> {
    private List<Task> taskList;
    private LayoutInflater inflater;
    private TaskViewModel taskViewModel;

    public TasksListAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }


    @Override
    public TodayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        Context context = parent.getContext();
        taskViewModel = ViewModelProviders.of((FragmentActivity) context).get(TaskViewModel.class);
        return new TodayViewHolder(this, view);
    }

    @Override
    public void onBindViewHolder(@NonNull TasksListAdapter.TodayViewHolder holder, int position) {

        if (taskList != null) {
            Task current = taskList.get(position);
            holder.title.setText(current.title);
            holder.duedate.setText(new SimpleDateFormat("EEE, dd-MM-yyyy hh:mm aa").format(current.due_time.getTime()));
            holder.star.setChecked(current.starred);
            holder.delete_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    taskViewModel.deleteATask(current.title);
                }
            });
        } else {
            holder.title.setText("No Task Available");
        }
    }

    void setTaskList(List<Task> tasks) {
        taskList = tasks;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (taskList != null)
            return taskList.size();
        else return 0;
    }

    public class TodayViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView duedate;
        private CheckBox star;
        private TasksListAdapter tasksListAdapter;
        private Context context;
        private ImageButton delete_button;

        public TodayViewHolder(TasksListAdapter listAdapter, @NonNull View itemView) {
            super(itemView);
            tasksListAdapter = listAdapter;
            title = itemView.findViewById(R.id.reminder_title);
            duedate = itemView.findViewById(R.id.due_date);
            star = itemView.findViewById(R.id.star_important);
            delete_button = itemView.findViewById(R.id.delete_task_button);
            star.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tasksListAdapter.taskList.get(getAdapterPosition()).starred = star.isChecked();
                    taskViewModel.updateTask(tasksListAdapter.taskList.get(getAdapterPosition()));
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = (Context) v.getContext();
                    Intent intent = new Intent(context, TaskViewActivity.class);
                    intent.putExtra("TaskView", tasksListAdapter.taskList.get(getAdapterPosition()));
                    Log.d("Intent", String.valueOf(intent));
                    ((Activity) context).startActivityForResult(intent, MainActivity.EDIT_TASK_ACTIVITY_REQUEST_CODE);
                }
            });
        }
    }
}
