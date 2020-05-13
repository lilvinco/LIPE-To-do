package com.example.todoapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TaskRepository{

    private TaskDAO mTaskDAO;
    private LiveData<List<Task>> mAllTasks;

    public TaskRepository(Application application){
        TaskRoomDB taskRoomDB = TaskRoomDB.getINSTANCE(application);
        mTaskDAO = taskRoomDB.taskDAO();
        mAllTasks = mTaskDAO.getAllTasks();
    }

    LiveData<List<Task>> getAllTasks(){
        return mAllTasks;
    }

    void insert(final Task task){
        //new insertAsyncTask(mTaskDAO).execute();
        TaskRoomDB.databaseWriteExecutor.execute(() -> {
            mTaskDAO.insertTask(task);
        });
    }

    void delete(final Task task){
        //new insertAsyncTask(mTaskDAO).execute();
        TaskRoomDB.databaseWriteExecutor.execute(() -> {
            mTaskDAO.deleteTask(task);
        });
    }

    /*private static class insertAsyncTask extends AsyncTask<Task, Void, Void> {

        private TaskDAO mAsyncTaskDao;

        insertAsyncTask(TaskDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Task... params) {
                mAsyncTaskDao.insertTask(params[0]);
            return null;
        }
    }*/
}
