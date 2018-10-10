package com.jentronics.cs3270a7.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Course {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="_id")
    private int uid;

    private String id;
    private String name;
    private String course_code;
    private String start_at;
    private String end_at;

    public Course(int uid, String id, String name, String course_code, String start_at, String end_at) {
        this.uid = uid;
        this.id = id;
        this.name = name;
        this.course_code = course_code;
        this.start_at = start_at;
        this.end_at = end_at;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCourse_code() {
        return course_code;
    }

    public String getStart_at() {
        return start_at;
    }

    public String getEnd_at() {
        return end_at;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCourse_code(String course_code) {
        this.course_code = course_code;
    }

    public void setStart_at(String start_at) {
        this.start_at = start_at;
    }

    public void setEnd_at(String end_at) {
        this.end_at = end_at;
    }



    /*
    _id: integer primary key auto-increment
    id: text
    name: text
    course_code: text
    start_at: text
    end_at: text
    */
}
