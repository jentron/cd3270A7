package com.jentronics.cs3270a8;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.jentronics.cs3270a8.db.AppDatabase;
import com.jentronics.cs3270a8.db.Course;

public class MainActivity extends AppCompatActivity   implements CourseRecyclerInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getSupportFragmentManager();
                NewCourseDialogFragment dialog = new NewCourseDialogFragment();
                fm.beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .add(android.R.id.content, dialog)
                        .addToBackStack(null)
                        .commit();

            }
        });
    }

    @Override
    public void viewCourse(Course course) {
        int uid = course.getUid();
        FragmentManager fm = getSupportFragmentManager();
        ViewCourseDialogFragment dialog = new ViewCourseDialogFragment();
        dialog.setCourse(course);
        fm.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .add(android.R.id.content, dialog)
                .addToBackStack(null)
                .commit();
      //  Log.d("RonDebug", "Do something cool");
    }

    @Override
    public void editCourse(Course course) {
        int uid = course.getUid();
        FragmentManager fm = getSupportFragmentManager();
        CourseEditFragment dialog = new CourseEditFragment();
        dialog.setCourse(course);
        fm.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .add(android.R.id.content, dialog)
                .addToBackStack(null)
                .commit();
        //  Log.d("RonDebug", "Do something cool");
    }
}
