package com.jentronics.cs3270a9;


import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jentronics.cs3270a9.db.AppDatabase;
import com.jentronics.cs3270a9.db.Course;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CourseEditFragment extends Fragment {

    private View root;
    private TextInputEditText  tv_action_id;
    private TextInputEditText  tv_action_name;
    private TextInputEditText  tv_action_code;
    private TextInputEditText  tv_action_start_at;
    private TextInputEditText  tv_action_end_at;
    private Button btn_save;
    private Course course;

    public CourseEditFragment() {
        // Required empty public constructor
    }

    public void setCourse(Course course){
        this.course = course;
        Log.d("Test", "UID: "+this.course.getUid());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return root = inflater.inflate(R.layout.fragment_course_edit, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();

        tv_action_id       = (TextInputEditText) root.findViewById(R.id.tv_action_id);
        tv_action_name     = (TextInputEditText) root.findViewById(R.id.tv_action_name);
        tv_action_code     = (TextInputEditText) root.findViewById(R.id.tv_action_code);
        tv_action_start_at = (TextInputEditText) root.findViewById(R.id.tv_action_start);
        tv_action_end_at   = (TextInputEditText) root.findViewById(R.id.tv_action_end);
//        btn_save = (FloatingActionButton) root.findViewById(R.id.btn_save);
        btn_save = (Button) root.findViewById(R.id.btn_save);


        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String tv_id   = tv_action_id.getText().toString();
                final String name = tv_action_name.getText().toString();
                final String code = tv_action_code.getText().toString();
                final String start = tv_action_start_at.getText().toString();
                final String end   = tv_action_end_at.getText().toString();
              //  Log.d("test", "In the button code");
                final Course course_update = new Course(tv_id, name, code, start, end);
                course_update.setUid(course.getUid());

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                    AppDatabase.getInstance(getContext())
                            .courseDAO()
                            .update(course_update);
                    List<Course> courseList = AppDatabase.getInstance(getContext())
                            .courseDAO()
                            .loadByID(tv_id);
                    for(Course c : courseList) {
                        Log.d("Test", "Course with ID: " + c.toString());
                    }
                }
            }).start();
            }
        });

        tv_action_code.setText(course.getCourse_code());
        tv_action_id.setText(course.getId());
        tv_action_name.setText(course.getName());
        tv_action_start_at.setText(course.getStart_at());
        tv_action_end_at.setText(course.getEnd_at());
    }
}
