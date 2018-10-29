package com.jentronics.cs3270a8;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.jentronics.cs3270a8.db.AppDatabase;
import com.jentronics.cs3270a8.db.Course;

public class ViewCourseDialogFragment extends DialogFragment {
    private TextView tv_view_id, tv_view_name, tv_view_code, tv_view_start_at, tv_view_end_at;
    private Course course;
    private View root;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.dialog_view_course, container, false);

        Toolbar toolbar = (Toolbar) root.findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.title_view_course);

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();

        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setHomeAsUpIndicator(android.R.drawable.ic_menu_close_clear_cancel);
        }

        setHasOptionsMenu(true);

        tv_view_id        = (TextView) root.findViewById(R.id.tv_view_id);
        tv_view_code      = (TextView) root.findViewById(R.id.tv_view_code);
        tv_view_name      = (TextView) root.findViewById(R.id.tv_view_name);
        tv_view_start_at  = (TextView) root.findViewById(R.id.tv_view_start_at);
        tv_view_end_at    = (TextView) root.findViewById(R.id.tv_view_end_at);
        tv_view_id.setText(course.getId());
        tv_view_code.setText(course.getCourse_code());
        tv_view_name.setText(course.getName());
        tv_view_start_at.setText(course.getStart_at());
        tv_view_end_at.setText(course.getEnd_at());
        return root;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        getActivity().getMenuInflater().inflate(R.menu.menu_view_dialog, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_edit:
                return true;

            case R.id.action_delete:
                return true;

            case android.R.id.home:
                dismiss();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setCourse(Course course){
        this.course = course;
    }
}