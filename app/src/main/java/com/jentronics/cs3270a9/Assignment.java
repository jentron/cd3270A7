package com.jentronics.cs3270a9;

public class Assignment {
    protected String name;
    protected boolean has_submitted_submissions;

    public String toString() {
        if(has_submitted_submissions) {
            return name + " : Submitted";
        }
        else {
            return name + " : Not Submitted";
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isHas_submitted_submissions() {
        return has_submitted_submissions;
    }

    public void setHas_submitted_submissions(boolean has_submitted_submissions) {
        this.has_submitted_submissions = has_submitted_submissions;
    }




}
