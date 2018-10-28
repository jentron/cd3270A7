package com.jentronics.cs3270a8;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.jentronics.cs3270a8.db.Course;

import java.util.List;

public class CourseRecyclerViewAdapter extends RecyclerView.Adapter<CourseRecyclerViewAdapter.ViewHolder> {

    private final List<Course> courses;
    private CourseRecyclerInterface mCallback;

    public CourseRecyclerViewAdapter(List<Course> courses, CourseRecyclerInterface mCallback) {
        this.courses = courses;
        this.mCallback = mCallback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Course course = courses.get(position);
        if (course != null){
            holder.tvLine1.setText(course.getName());
            holder.tvLine2.setText(course.getCourse_code());

            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO: userClicked set up an interface
                    mCallback.viewCourse(course);
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public void addItems(List<Course> courses) {
        this.courses.clear();
        this.courses.addAll(courses);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tvLine1, tvLine2;
        public Course course;
        public View view;


        public ViewHolder(View itemView){
            super(itemView);

            view = itemView;
            tvLine1 = (TextView) view.findViewById(R.id.line1);
            tvLine2 = (TextView) view.findViewById(R.id.line2);
        }
    }
}
