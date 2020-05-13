package com.example.todoapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TaskDAO{
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertTask(Task task);
    @Delete
    void deleteTask(Task... task);
    @Update
    void updateTask(Task repos);

    @Query("DELETE FROM task_table")
    void deleteAll();

    @Query("SELECT * FROM task_table")
    LiveData<List<Task>> getAllTasks();

    @Query("SELECT * FROM task_table WHERE mTaskID IN (:taskIds)")
    List<Task> loadAllByIds(int[] taskIds);

}
