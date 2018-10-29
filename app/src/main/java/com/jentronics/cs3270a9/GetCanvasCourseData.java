package com.jentronics.cs3270a9;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class GetCanvasCourseData  extends AsyncTask<String, Integer, String> {
    private String rawJSON;

    @Override
    protected String doInBackground(String... strings) {
        if(strings[0] == null) return null;
        try{
            URL url = new URL("https://weber.instructure.com/api/v1/courses/" + strings[0]+"/assignments");
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
    }
}
