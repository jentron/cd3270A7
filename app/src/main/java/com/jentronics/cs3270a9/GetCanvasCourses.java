package com.jentronics.cs3270a9;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jentronics.cs3270a9.db.Course;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class GetCanvasCourses extends AsyncTask<String, Integer, String> {

    private String rawJSON;
    private OnCourseComplete mCallback;

    public interface OnCourseComplete{
        void processCourseList(Course[] courses);
        void processAssigmentList(Assignment[] assignments);
    }

    public void setOnCourseComplete(OnCourseComplete mCallback){
        this.mCallback = mCallback;
    }

    @Override
    protected String doInBackground(String... strings) {

        try{
            URL url = new URL("https://weber.instructure.com/api/v1/courses");
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer " + Authorization.AUTH_TOKEN);

            connection.connect();

            int status = connection.getResponseCode();
            Log.d("Test", "HTTPS response code " + status);

            switch (status){
                case 200:
                case 201:
                    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    rawJSON = br.readLine();
                    Log.d("Test", "rawJSON Length: " + rawJSON.length());
                    Log.d("Test", "rawJSON first 256: " + rawJSON.substring(0, 255));
                    break;
            }


        } catch (MalformedURLException e) {
            Log.d("Test", "BAD URL: Unable to connect");
        } catch (IOException e) {
            Log.d("Test", "NO CONNECTION: Unable to connect");
        }

        return rawJSON;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        Course[] courses;
        try {
            courses = parseJson(result);
            if(courses != null ){
                if(mCallback != null && mCallback instanceof OnCourseComplete ) {
                    mCallback.processCourseList(courses);
                }
                else {
                    throw new Exception("mCallback not set to OnCourseComplete");
                }

            }
        }
        catch (Exception e){
            Log.d("Test", e.getMessage());
        }
    }

    private Course[] parseJson(String result) {
        GsonBuilder gsonb = new GsonBuilder();
        Gson gson = gsonb.create();

        Course[] courses = null;

        try{
            courses = gson.fromJson(rawJSON, Course[].class);
            Log.d("Test", "First Course " + courses[0].toString());
        }
        catch (Exception e){
            Log.d("Test", e.getMessage());
        }

        return courses;

    }
}
