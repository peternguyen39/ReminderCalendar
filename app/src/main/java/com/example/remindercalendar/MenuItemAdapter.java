package com.example.remindercalendar;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.example.remindercalendar.R.layout.menu_item;

public class MenuItemAdapter extends RecyclerView.Adapter<MenuItemAdapter.ViewHolder> {
    private List<MenuItem> localDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final Button item;

        public ViewHolder(@NonNull @NotNull View view) {
            super(view);

            item = (Button) view.findViewById(R.id.item);
        }
    }

    public MenuItemAdapter(List<MenuItem> localDataset) {
        this.localDataset = localDataset;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(
                        menu_item,
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        MenuItem menuItem = localDataset.get(position);

        holder.item.setText(menuItem.getItemText());
    }

    @Override
    public int getItemCount() {
        return localDataset.size();
    }


}
