package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.text.DateFormat;
import java.util.Date;
import java.util.TimeZone;

public class AddTaskActivity extends AppCompatActivity {

    private EditText editText;
    private Button addTaskBtn;
    private String taskContent;
    private int spSize;
    private TextView tv;
    public static final String EXTRA_REPLY =
            "com.example.todoapp.REPLY";

    public static final String EXTRA_REPLY2 =
            "com.example.todoapp.REPLY";

    public static final String EXTRA_REPLY3 =
            "com.example.todoapp.REPLY";
    private Task mTask;
    private TaskRoomDB taskRoomDB;
    private String deadline;
    private String date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        taskRoomDB = TaskRoomDB.getINSTANCE(AddTaskActivity.this);

        editText = findViewById(R.id.editText);
        deadline = findViewById(R.id.calendarView).toString();
        date = DateFormat.getDateInstance().toString();

        taskContent = editText.getText().toString();
        addTaskBtn = findViewById(R.id.button);

        addTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // create worker thread to insert data into database
             //   new InsertTask(AddTaskActivity.this,mTask).execute();
                Intent replyIntent = new Intent();
                /*if (TextUtils.isEmpty(taskContent)) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    replyIntent.putExtra(EXTRA_REPLY, taskContent);
                    replyIntent.putExtra(EXTRA_REPLY2, deadline);
                    replyIntent.putExtra(EXTRA_REPLY3, date);
                    setResult(RESULT_OK, replyIntent);
                }*/
                replyIntent.putExtra(EXTRA_REPLY, taskContent);
                replyIntent.putExtra(EXTRA_REPLY2, deadline);
                replyIntent.putExtra(EXTRA_REPLY3, date);
                setResult(RESULT_OK, replyIntent);
                finish();
            }
        });
    }

   /* private void setResult(Task task, int flag){
        setResult(flag,new Intent().putExtra("task", (CharSequence) task));
        finish();
    }

    private static class InsertTask extends AsyncTask<Void,Void,Boolean> {

        private WeakReference<AddTaskActivity> activityReference;
        private Task task;

        // only retain a weak reference to the activity
        InsertTask(AddTaskActivity context, Task task) {
            activityReference = new WeakReference<>(context);
            this.task = task;
        }

        // doInBackground methods runs on a worker thread
        @Override
        protected Boolean doInBackground(Void... objs) {
            activityReference.get().taskRoomDB.taskDAO().insertTask(task);
            return true;
        }

        // onPostExecute runs on main thread
        @Override
        protected void onPostExecute(Boolean bool) {
            if (bool){
                activityReference.get().setResult(task,1);
            }
        }

    }*/
}
