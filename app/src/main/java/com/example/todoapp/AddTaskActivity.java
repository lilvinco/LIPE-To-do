package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddTaskActivity extends AppCompatActivity {

    private SharedPreferences mSharedPreferences;
    private String sharedPrefFile = "com.example.todoapp";
    private EditText editText;
    private Button addTaskBtn;
    private String task;
    private int spSize;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        mSharedPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        mSharedPreferences.getString("key_1", null);
        spSize = mSharedPreferences.getAll().size();
        tv = findViewById(R.id.textView);
        tv.setText(String.format("%s", spSize));

        editText = findViewById(R.id.editText);
        task = editText.getText().toString();
        addTaskBtn = findViewById(R.id.button);
        addTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor prefEditor = mSharedPreferences.edit();
                prefEditor.putString("key_1", task);
                prefEditor.apply();
            }
        });
    }
}
