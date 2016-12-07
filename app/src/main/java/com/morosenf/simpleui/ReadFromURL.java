package com.morosenf.simpleui;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import jarden.quiz.EndOfQuestionsException;
import jarden.quiz.PresetQuiz;


/**
 * Created by morosenf on 12/6/2016.
 */

public class ReadFromURL extends AsyncTask <String, Void, Properties >{

    PresetQuiz presetQuiz;
    TextView questionTextView;

    @Override
    protected Properties doInBackground(String... strings) {
        URL spanishURL = null;
        try {
            spanishURL = new URL(strings[0]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        InputStream inputStream = null;
        try {
            inputStream = spanishURL.openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Properties spanishProps = new Properties();
        try {
            spanishProps.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return spanishProps;
    }

    @Override
    protected void onPostExecute(Properties properties) {
        super.onPostExecute(properties);

               try {
            presetQuiz = new PresetQuiz(properties);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String question = null;

                try {
            question = presetQuiz.getNextQuestion(MainActivity.level);
        } catch (EndOfQuestionsException e) {
            Log.e("my first app", e.toString());
            questionTextView.setText(e.toString());
        }
        questionTextView.setText(question);
    }
}
