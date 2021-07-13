package com.example.remindercalendar;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TodayListAdapter extends RecyclerView.Adapter<TodayListAdapter.TodayViewHolder> {
    private List<Task> taskList;
    private LayoutInflater inflater;

    public TodayListAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }


    @Override
    public TodayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        return new TodayViewHolder(this, view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodayListAdapter.TodayViewHolder holder, int position) {
        //TODO: Read data from storage\
        //TODO: Implement behavior of starred
        //TODO: Implement Activity for ReminderItem
        //TODO: Set onclickListener for each Task item

        if (taskList != null) {
            Task current = taskList.get(position);
            holder.title.setText(current.title);
            //holder.duedate.setText(current.due_time);
            holder.star.setChecked(current.starred);
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

    public class TodayViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView title;
        private TextView duedate;
        private CheckBox star;
        private TodayListAdapter todayListAdapter;

        public TodayViewHolder(TodayListAdapter listAdapter, @NonNull View itemView) {
            super(itemView);
            todayListAdapter = listAdapter;
            title = itemView.findViewById(R.id.reminder_title);
            duedate = itemView.findViewById(R.id.due_date);
            star = itemView.findViewById(R.id.star_important);
            star.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    todayListAdapter.taskList.get(getAdapterPosition()).starred = star.isChecked();
                }
            });
        }


        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), TaskViewActivity.class);
            intent.putExtra("ListPosition", getAdapterPosition());
            v.getContext().startActivity(intent);
        }
    }
}
