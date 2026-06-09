package com.gymbro.app.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.gymbro.app.data.local.dao.ExerciseDao;
import com.gymbro.app.data.local.dao.RoutineDao;
import com.gymbro.app.data.local.dao.RoutineExerciseDao;
import com.gymbro.app.data.local.dao.UserDao;
import com.gymbro.app.data.local.dao.UserSettingsDao;
import com.gymbro.app.data.local.dao.WorkoutDao;
import com.gymbro.app.data.local.dao.WorkoutExerciseDao;
import com.gymbro.app.data.local.entities.ExerciseEntity;
import com.gymbro.app.data.local.entities.RoutineEntity;
import com.gymbro.app.data.local.entities.RoutineExerciseEntity;
import com.gymbro.app.data.local.entities.UserEntity;
import com.gymbro.app.data.local.entities.UserSettingsEntity;
import com.gymbro.app.data.local.entities.WorkoutEntity;
import com.gymbro.app.data.local.entities.WorkoutExerciseEntity;

@Database(
        entities = {
                ExerciseEntity.class,
                WorkoutEntity.class,
                RoutineEntity.class,
                RoutineExerciseEntity.class,
                UserEntity.class,
                UserSettingsEntity.class,
                WorkoutExerciseEntity.class
        },
        version = 2,
        exportSchema = false
)
@TypeConverters(Converters.class)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    // All 7 DAOs
    public abstract ExerciseDao exerciseDao();
    public abstract WorkoutDao workoutDao();
    public abstract RoutineDao routineDao();
    public abstract RoutineExerciseDao routineExerciseDao();
    public abstract UserDao userDao();
    public abstract UserSettingsDao userSettingsDao();
    public abstract WorkoutExerciseDao workoutExerciseDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "gymbro_database"
                    )
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}