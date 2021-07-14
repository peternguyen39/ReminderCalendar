package com.example.remindercalendar;

import java.io.Serializable;

public class MenuItem implements Serializable {
    private String item;

    public MenuItem(String item) {
        this.item = item;
    }

    public String getItemText() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return item;
    }
}
