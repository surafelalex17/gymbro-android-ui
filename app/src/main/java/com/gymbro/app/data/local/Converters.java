package com.gymbro.app.data.local;

import androidx.room.TypeConverter;
import java.util.Arrays;
import java.util.List;

public class Converters {

    @TypeConverter
    public static String fromList(List<String> list) {
        if (list == null) return "";
        return String.join(",", list);
    }

    @TypeConverter
    public static List<String> toList(String value) {
        if (value == null || value.isEmpty()) return List.of();
        return Arrays.asList(value.split(","));
    }
}