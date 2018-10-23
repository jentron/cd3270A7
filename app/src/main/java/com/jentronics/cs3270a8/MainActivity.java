package com.jentronics.cs3270a8;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.jentronics.cs3270a8.db.AppDatabase;
import com.jentronics.cs3270a8.db.Course;

public class MainActivity extends AppCompatActivity {

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
         //       Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
           //             .setAction("Action", null).show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Course c = new Course("123456", "Math Modeling",
                                "MATH 3550", "11:30", "12:20");
                        AppDatabase.getInstance(getApplicationContext())
                                .courseDAO()
                                .insert(c);
                    }
                }).start();
            }
        });
    }

}
