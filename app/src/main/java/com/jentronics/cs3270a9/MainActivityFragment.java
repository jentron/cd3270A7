package com.jentronics.cs3270a9;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jentronics.cs3270a9.db.Course;

import java.util.ArrayList;
import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private RecyclerView recyclerView;
    private CourseRecyclerViewAdapter adapter;
    private int columnCount = 1;
    private View root;
    private CourseRecyclerInterface mCallback;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_main, container, false);

        recyclerView = (RecyclerView)root.findViewById(R.id.rvCourseList);
        mCallback = (CourseRecyclerInterface) getContext();
        return root;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Log.d("Test", "Called onCreateOptionsMenu");
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onResume() {
        super.onResume();

        Context context = getContext();
        adapter = new CourseRecyclerViewAdapter(new ArrayList<Course>(), context);
        if(columnCount <= 1)
        {
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(context, columnCount));
        }

        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(false);

        ViewModelProviders.of(this)
                .get(AllCoursesViewModel.class)
                .getCourseList(context)
                .observe(this, new Observer<List<Course>>() {
                    @Override
                    public void onChanged(@Nullable List<Course> courses) {
                        if(courses!= null){
                            adapter.addItems(courses);
                        }
                    }
                });

    }

}
