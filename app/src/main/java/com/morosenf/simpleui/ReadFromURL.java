package com.morosenf.simpleui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;



/**
 * Created by morosenf on 12/6/2016.
 */


interface ResultsListener {
    void setProperties(InputStream inputStream);
}

public class ReadFromURL extends AsyncTask <String, Void, InputStream >{

    private final ResultsListener resultsListener;

    public ReadFromURL(ResultsListener resultsListener) {
        this.resultsListener = resultsListener;
    }


    @Override
    protected InputStream doInBackground(String... strings) {
        URL spanishURL = null;

        try {
            spanishURL = new URL(strings[0]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        /*if (ContextCompat.checkSelfPermission(MainActivity.class,
                Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(MainActivity.class,
                    new String[]{Manifest.permission.INTERNET},
                    1);
        }*/

        InputStream inputStream = null;
        try {
            inputStream = spanishURL.openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return inputStream;
    }

    @Override
    protected void onPostExecute(InputStream inputStream) {
        super.onPostExecute(inputStream);
        this.resultsListener.setProperties(inputStream);

    }
}
