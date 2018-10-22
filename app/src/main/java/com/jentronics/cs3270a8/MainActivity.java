package com.jentronics.cs3270a8;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.jentronics.cs3270a8.db.AppDatabase;
import com.jentronics.cs3270a8.db.Course;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Course> courseList = AppDatabase.getInstance(getApplicationContext())
                .courseDAO()
                .getAll();

        for(Course c : courseList) {
            Log.d("Test", "All Courses: " + c.toString());
        }
            }
        }).start();
        setContentView(R.layout.activity_main);
    }
}
