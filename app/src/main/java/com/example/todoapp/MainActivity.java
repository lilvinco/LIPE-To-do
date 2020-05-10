package com.example.todoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

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

        //specify an adapter
        String[] mDataset = getResources().getStringArray(R.array.tasks);
        String[] mDataset2 = getResources().getStringArray(R.array.dates);
        String[] mDataset3 = getResources().getStringArray(R.array.deadlines);
        mAdapter = new ListAdapter(this, mDataset, mDataset2, mDataset3);
        recyclerView.setAdapter(mAdapter);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Snackbar.make(view, "You clicked fab", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                Intent intent = new Intent(getApplicationContext(), AddTaskActivity.class);
                startActivity(intent);
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
