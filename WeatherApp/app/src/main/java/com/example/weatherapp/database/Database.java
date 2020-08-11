package com.example.weatherapp.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.weatherapp.dao.Dao;
import com.example.weatherapp.java.UserConfig;

@androidx.room.Database(entities = {UserConfig.class}, version = 1, exportSchema = false)
/**
 * database class which contains an instance of database and with which we can access the dao
 */
public abstract class Database extends RoomDatabase {

    private static Database instance;

    public abstract Dao getDao();

    /**
     * method to get the instance of database. if instance is null we create new
     *
     * @param context apllication context
     * @return the database instance
     */
    public static synchronized Database getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), Database.class, "weather_app")
                    .fallbackToDestructiveMigration().addCallback(roomCallback).build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private Dao dao;

        private PopulateDbAsyncTask(Database db) {
            dao = db.getDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao.insert(new UserConfig(UserConfig.CONFIG_ID, "Ruzomberok"));
            return null;
        }
    }

}
