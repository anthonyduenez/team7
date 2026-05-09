package com.example.team7.database;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.team7.database.dao.OutfitDao;
import com.example.team7.database.dao.ClothingDao;
import com.example.team7.database.dao.UserDao;
import com.example.team7.database.entities.Outfit;
import com.example.team7.database.entities.Clothing;
import com.example.team7.database.entities.User;
import com.example.team7.database.relations.OutfitClothingCrossRef;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {User.class,
        Outfit.class,
        Clothing.class,
        OutfitClothingCrossRef.class},
        version = 1,
        exportSchema = false
)
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService DB_EXECUTOR = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public abstract UserDao userDao();

    public abstract OutfitDao outfitDao();

    public abstract ClothingDao clothingDao();

    public static AppDatabase getDatabase(final Context context) {

        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                Log.i("AppDatabase", "Creating database instance");
                if (INSTANCE == null) {
                    Log.i("AppDatabase", "Building database");
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    AppDatabase.class, "AppDatabase").allowMainThreadQueries()
                            .addCallback(addDefaultValues)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static final RoomDatabase.Callback addDefaultValues = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            Log.i("AppDatabase", "Database created");
            DB_EXECUTOR.execute(() -> {
                UserDao userDao = INSTANCE.userDao();

                User admin = new User("admin", "admin");
                admin.setAdmin(true);
                userDao.insertUser(admin);

                User user1 = new User("user1", "password");
                userDao.insertUser(user1);
            });
        }
    };
}
