package com.example.todoapp;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.TaskViewHolder> {
    private final LayoutInflater mInflater;
    public List<Task> mTasks;


    // Provide a suitable constructor (depends on the kind of dataset)
    public ListAdapter(Context context) {
        mInflater	=	LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //create view and inflate
        View mItemView = mInflater.inflate(R.layout.todo_item, parent, false);
        //TaskViewHolder viewHolder = new TaskViewHolder(mItemView);
        //return viewHolder;
        return new TaskViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        if (mTasks != null) {
            Task current = mTasks.get(position);
            holder.taskTextView.setText(current.getTaskContent());
            holder.deadlineTextView.setText(current.getDeadline());
            holder.dateTextView.setText(current.getDateCreated());
        } else {
            // Covers the case of data not being ready yet.
            holder.taskTextView.setText("No Word");
        }
    }

    void setTasks(List<Task> tasks){
        mTasks = tasks;
        notifyDataSetChanged();
    }


    public class TaskViewHolder extends RecyclerView.ViewHolder {
        // Provide a reference to the views for each data item
        TextView taskTextView;
        CardView cardView;
        TextView dateTextView;
        TextView deadlineTextView;


        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            taskTextView = itemView.findViewById(R.id.taskTextView);
            cardView = itemView.findViewById(R.id.cardView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            deadlineTextView = itemView.findViewById(R.id.deadlineTextView);
        }
    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        if (mTasks != null)
            return mTasks.size();
        else return 0;
    }
}


