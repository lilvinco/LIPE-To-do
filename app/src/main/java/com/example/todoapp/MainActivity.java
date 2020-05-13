package com.example.todoapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import static androidx.recyclerview.widget.ItemTouchHelper.ACTION_STATE_SWIPE;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private TaskViewModel mTaskViewModel;
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    private List<Task> mTasks;
    SwipeController swipeController = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_home);
        recyclerView = findViewById(R.id.recyclerView);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);
        //Set layout manager for recycler view
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        swipeController = new SwipeController(new SwipeControllerActions(){
            @Override
            public void onRightClicked(int position) {
                mAdapter.mTasks.remove(position);
                //mTaskViewModel.delete(position);
                mAdapter.notifyItemRemoved(position);
                mAdapter.notifyItemRangeChanged(position, mAdapter.getItemCount());
            }

           /* @Override
            public void onLeftClicked(int position) {
                if ( (note = (Note) getIntent().getSerializableExtra("note"))!=null ){
                    getSupportActionBar().setTitle("Update Note");
                    update = true;
                    button.setText("Update");
                    et_title.setText(note.getTitle());
                    et_content.setText(note.getContent());
                }
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        note.setContent(et_content.getText().toString());
                        note.setTitle(et_title.getText().toString());
                        noteDatabase.getNoteDao().updateNote(note);
                    }
                });
            }*/
        });

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
        itemTouchhelper.attachToRecyclerView(recyclerView);

        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                swipeController.onDraw(c);
            }
        });


        //specify an adapter
        mAdapter = new ListAdapter(this);
        recyclerView.setAdapter(mAdapter);

        // Setup the TaskViewModel
        mTaskViewModel = ViewModelProviders.of(this).get(TaskViewModel.class);
        // Get all the words from the database
        // and associate them to the adapter
        mTaskViewModel.getAllTasks().observe(this, tasks -> {
            // Update the cached copy of the words in the adapter.
            mAdapter.setTasks(tasks);
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Snackbar.make(view, "You clicked fab", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
            }
        });
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        if (drawer != null) {
            drawer.addDrawerListener(toggle);
        }
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Task task = new Task(data.getStringExtra(AddTaskActivity.EXTRA_REPLY), data.getStringExtra(AddTaskActivity.EXTRA_REPLY2),data.getStringExtra(AddTaskActivity.EXTRA_REPLY3));
            mTaskViewModel.insert(task);
           // mTaskViewModel.getAllTasks();
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //	Handle	navigation	view	item	clicks	here.
        switch (menuItem.getItemId()) {
            case R.id.nav_camera:
                //	Handle	the	camera	import	action	(for	now	display	a	toast).
                drawer.closeDrawer(GravityCompat.START);
                //displayToast(getString(R.string.chose_camera));
                return true;

            case R.id.nav_gallery:
                //	Handle	the	gallery	action	(for	now	display	a	toast).
                drawer.closeDrawer(GravityCompat.START);
                //displayToast(getString(R.string.chose_gallery));
                return true;
            case R.id.nav_slideshow:
                //	Handle	the	slideshow	action	(for	now	display	a	toast).
                drawer.closeDrawer(GravityCompat.START);
                //displayToast(getString(R.string.chose_slideshow));
                return true;
            case R.id.nav_manage:
                //	Handle	the	tools	action	(for	now	display	a	toast).
                drawer.closeDrawer(GravityCompat.START);
                //displayToast(getString(R.string.chose_tools));
                return true;
            case R.id.nav_share:
                //	Handle	the	share	action	(for	now	display	a	toast).
                drawer.closeDrawer(GravityCompat.START);
                //displayToast(getString(R.string.chose_share));
                return true;
            case R.id.nav_send:
                //	Handle	the	send	action	(for	now	display	a	toast).
                drawer.closeDrawer(GravityCompat.START);
                //displayToast(getString(R.string.chose_send));
                return true;
            default:
                return false;}
        }
}
