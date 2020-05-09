package com.example.todoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.TaskViewHolder> {
    private String[] mDataset;//TODO: Replace with database connection
    private String[] mDataset2;
    private String[] mDataset3;
    private LayoutInflater mInflater;


    // Provide a suitable constructor (depends on the kind of dataset)
    public ListAdapter(Context context, String[] myDataset, String[] myDataset2, String[] myDataset3) {
        mInflater	=	LayoutInflater.from(context);
        mDataset = myDataset;
        mDataset2 = myDataset2;
        mDataset3 = myDataset3;
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
        //final MyTodoData todoData = mDataset[position];
        holder.taskTextView.setText(mDataset[position]);
        holder.dateTextView.setText(mDataset2[position]);
        holder.deadlineTextView.setText(mDataset3[position]);
    }

    // Return the size of your dataset (invoked by the layout manager)

    @Override
    public int getItemCount() {
        return mDataset.length;
    }


}
