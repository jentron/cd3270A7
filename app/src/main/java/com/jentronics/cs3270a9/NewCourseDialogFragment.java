package com.jentronics.cs3270a9;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.Toolbar;
import android.view.Window;

import com.jentronics.cs3270a9.db.AppDatabase;
import com.jentronics.cs3270a9.db.Course;

public class NewCourseDialogFragment extends DialogFragment {
    private TextInputEditText edt_id, edt_name, edt_course_code, edt_start_at, edt_end_at;
    private View root;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.dialog_new_course, container, false);

        Toolbar toolbar = (Toolbar) root.findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.title_new_course);

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();

        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setHomeAsUpIndicator(android.R.drawable.ic_menu_close_clear_cancel);
        }

        setHasOptionsMenu(true);

        edt_id          = (TextInputEditText) root.findViewById(R.id.edt_id);
        edt_course_code = (TextInputEditText) root.findViewById(R.id.edt_course_code);
        edt_name        = (TextInputEditText) root.findViewById(R.id.edt_name);
        edt_start_at    = (TextInputEditText) root.findViewById(R.id.edt_start_at);
        edt_end_at      = (TextInputEditText) root.findViewById(R.id.edt_end_at);

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
        getActivity().getMenuInflater().inflate(R.menu.menu_create_dialog, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_save:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Course course = new Course(
                                edt_id.getText().toString(),
                                edt_name.getText().toString(),
                                edt_course_code.getText().toString(),
                                edt_start_at.getText().toString(),
                                edt_end_at.getText().toString()
                        );
                        AppDatabase.getInstance(getContext())
                                .courseDAO()
                                .insert(course);

                    }
                }).start();
                dismiss();
                return true;
            case android.R.id.home:
                dismiss();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
