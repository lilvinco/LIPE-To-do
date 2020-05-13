package com.example.todoapp;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.ColumnInfo;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.PrimaryKey;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Entity (tableName = "task_table")
public class Task {


    @PrimaryKey(autoGenerate = true)
    private int mTaskID;

    @NonNull
    @ColumnInfo(name = "task_content")
    private String mTaskContent;

    @NonNull
    private String mDeadline;

    @NonNull
    private String mDateCreated;
    public Task(@NonNull String taskContent, String deadline, String dateCreated) {
        this.mTaskContent = taskContent;
        this.mDeadline = deadline;
        this.mDateCreated = dateCreated;
    }

   /* public Task(@NonNull String task) {
        this.mTask = task;
    }*/
    public String getTaskContent() {
        return this.mTaskContent;
    }

    public String getDeadline() {
        return this.mDeadline;
    }

    public String getDateCreated() {
        return this.mDateCreated;
    }

    public int getTaskID() {
        return this.mTaskID;
    }

    public void setTaskID(int mTaskID) {
        this.mTaskID = mTaskID;
    }


    public void setTaskContent(String mTask) {
        this.mTaskContent = mTask;
    }

    public void setDeadline(String mDeadline) {
        this.mDeadline = mDeadline;
    }

    public void setDateCreated(String mDateCreated) {
        this.mDateCreated = mDateCreated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;

        Task task = (Task) o;

        if (mTaskID != task.mTaskID) return false;
        return mTaskContent != null ? mTaskContent.equals(task.mTaskContent) : task.mTaskContent == null;
    }



    @Override
    public int hashCode() {
        int result = mTaskID;
        result = 31 * result + (mTaskContent != null ? mTaskContent.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Task{" +
                "mTaskID=" + mTaskID +
                ", mTaskContent='" + mTaskContent + '\'' +
                ", mDeadline='" + mDeadline + '\'' +
                ", mDateCreated='" + mDateCreated + '\'' +
                '}';
    }
}

