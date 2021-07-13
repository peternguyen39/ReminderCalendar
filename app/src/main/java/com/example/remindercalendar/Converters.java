package com.example.remindercalendar;

import androidx.room.TypeConverter;

import java.util.Calendar;
import java.util.Date;

public class Converters {
    @TypeConverter
    public static Calendar fromTimestamp(Long value) {
        if (value == null) return null;
        Date date = new Date(value);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    @TypeConverter
    public static Long fromCalendar(Calendar calendar) {
        return calendar == null ? null : calendar.getTime().getTime();
    }
}
