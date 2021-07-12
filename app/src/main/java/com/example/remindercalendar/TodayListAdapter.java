package com.example.remindercalendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TodayListAdapter extends RecyclerView.Adapter<TodayListAdapter.TodayViewHolder> {
    private ArrayList<Reminder> reminderList;

    public TodayListAdapter(Context context, ArrayList<Reminder> list) {
        reminderList = list;
    }


    @Override
    public TodayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reminder_item, parent, false);
        return new TodayViewHolder(this, view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodayListAdapter.TodayViewHolder holder, int position) {
        //TODO: Read data from storage\
        //TODO: Implement behavior of starred
        //TODO: Implement Activity for ReminderItem
    }

    @Override
    public int getItemCount() {
        return reminderList.size();
    }

    public class TodayViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView duedate;
        private CheckBox starred;
        private TodayListAdapter todayListAdapter;

        public TodayViewHolder(TodayListAdapter listAdapter, @NonNull View itemView) {
            super(itemView);
            todayListAdapter = listAdapter;
            title = itemView.findViewById(R.id.reminder_title);
            duedate = itemView.findViewById(R.id.due_date);
            starred = itemView.findViewById(R.id.star_important);
        }
    }
}
