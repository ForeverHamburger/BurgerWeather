package com.example.mvpburgerweahter.utils;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class TimeUtils {
    private static final String TAG = "TimeUtils";
    public static String extractTime(String dateTimeStr) {
        SimpleDateFormat fullFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mmX");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

        try {
            Date date = fullFormat.parse(dateTimeStr);
            return timeFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String[] getUpcomingWeekdays() {
        LocalDate today = LocalDate.now();
        String[] weekdays = new String[7];

        for (int i = 0; i < 7; i++) {
            LocalDate date = today.plusDays(i);
            weekdays[i] = date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        }

        return weekdays;
    }

    public static String getCurrentDateFormatted() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, dd MMM", Locale.ENGLISH);
        Calendar calendar = Calendar.getInstance();
        return dateFormat.format(calendar.getTime());
    }

    public static List<String> getNextSevenDays() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd");
        List<String> dates = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            LocalDate date = today.plusDays(i);
            String formattedDate = date.format(formatter);
            dates.add(formattedDate);
        }

        return dates;
    }

    public static float convertTimeToFloat(String time) {
        if (time == null || !time.matches("\\d\\d:\\d\\d")) {
            throw new IllegalArgumentException("Invalid time format. Correct format is HH:mm.");
        }

        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);

        return hours + (float) minutes / 60.0f;
    }
}
