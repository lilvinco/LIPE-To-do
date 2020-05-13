package com.example.todoapp;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Task.class},version = 1,exportSchema = false)
public abstract class TaskRoomDB extends RoomDatabase {

    public abstract TaskDAO taskDAO();
    private static volatile TaskRoomDB INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static TaskRoomDB getINSTANCE(final Context context) {
        if(INSTANCE == null){
            synchronized (TaskRoomDB.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),TaskRoomDB.class,"tasks_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                   // new PopulateDbAsync(INSTANCE).execute();

                    // If you want to keep data through app restarts,
                    // comment out the following block
                    databaseWriteExecutor.execute(() -> {
                        // Populate the database in the background.
                        // If you want to start with more tasks, just add them.
                        TaskDAO dao = INSTANCE.taskDAO();
                        dao.deleteAll();

                        Task task = new Task( "Buy fish", "12/2/2020", "5/13/2020");
                        dao.insertTask(task);
                        task = new Task( "Buy chick", "12/2/2020", "5/13/2020");
                        dao.insertTask(task);
                    });
                }
            };
    /**
     * Populate the database in the background.
     */
    /*private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final TaskDAO mDao;
        String[] tasks = {"Buy chin chin", "Fry fidj", "Read  book"};

        PopulateDbAsync(TaskRoomDB db) {
            mDao = db.taskDAO();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate the database
            // when it is first created
           mDao.deleteAll();

            for (int i = 0; i <= tasks.length - 1; i++) {
                Task task = new Task(tasks[i]);
                mDao.insertTask(task);
            }
            return null;
        }
    }*/
}
