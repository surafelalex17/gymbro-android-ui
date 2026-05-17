package com.gymbro.app.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.gymbro.app.data.local.dao.ExerciseDao;
import com.gymbro.app.data.local.dao.WorkoutDao;
import com.gymbro.app.data.local.entities.ExerciseEntity;
import com.gymbro.app.data.local.entities.WorkoutEntity;

@Database(
        entities = { ExerciseEntity.class, WorkoutEntity.class },
        version = 1,
        exportSchema = false
)
@TypeConverters(Converters.class)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    // DAOs
    public abstract ExerciseDao exerciseDao();
    public abstract WorkoutDao workoutDao();

    // Singleton
    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "gymbro_database"
                    )
                    .fallbackToDestructiveMigration() // during development only
                    .build();
        }
        return instance;
    }
}