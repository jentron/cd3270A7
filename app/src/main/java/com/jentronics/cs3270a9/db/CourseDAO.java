package com.jentronics.cs3270a9.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface CourseDAO {

    @Query("select * from course")
    LiveData<List<Course>> getAll();

    @Query("select * from course where id = :id")
    List<Course> loadByID(String id);

    @Query("select * from course where uid = :uid LIMIT 1")
    Course getByUID(int uid);

    @Delete
    void delete(Course course);

    @Update
    void update(Course course);

    @Insert
    void insert(Course... courses);
}
    /*
    _id: integer primary key auto-increment
    id: text
    name: text
    course_code: text
    start_at: text
    end_at: text
    */