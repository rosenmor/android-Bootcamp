package com.morosenf.simpleui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;


import jarden.quiz.ArithmeticQuiz;
import jarden.quiz.EndOfQuestionsException;
import jarden.quiz.PresetQuiz;
import jarden.quiz.Quiz;

import static com.morosenf.simpleui.R.string.level;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText answerEditTex;
    public TextView resultTextView;
    public TextView questionTextView;
    private Button goButton;
    private Quiz quiz;
    private PresetQuiz presetQuiz;
    private String levelStr;
    public static int level;
    private String question = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionTextView = (TextView) findViewById(R.id.questionTextView);
        goButton = (Button)findViewById(R.id.goButton);
        answerEditTex = (EditText)findViewById(R.id.answerEditText);
        resultTextView = (TextView)findViewById(R.id.resultTextView);
        levelStr =  ((EditText)findViewById(R.id.levelEditText)).getText().toString();
        level=Integer.parseInt(levelStr);

        //questionTextView.setText("7 * 6 ?");
        quiz = new ArithmeticQuiz();

        // Here, thisActivity is the current activity
        /*if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.INTERNET},
                        1);
            }*/

        String serverUrlStr = "https://sites.google.com/site/amazequiz/home/problems/spanish.txt?attredirects=0&d=1";

        new ReadFromURL().execute(serverUrlStr);


       /* try {
            presetQuiz = new PresetQuiz(spanishProps);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            question = presetQuiz.getNextQuestion(level);
        } catch (EndOfQuestionsException e) {
            Log.e("my first app", e.toString());
            questionTextView.setText(e.toString());
        }*/


        //InputStream inputStream = getResources().openRawResource(R.raw.capitals);

        /*Properties capitalProps = new Properties();
        try {
            capitalProps.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            presetQuiz = new PresetQuiz(capitalProps);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            question = presetQuiz.getNextQuestion(level);
        } catch (EndOfQuestionsException e) {
            Log.e("my first app", e.toString());
            questionTextView.setText(e.toString());
        }*/

        /*try {
            question = quiz.getNextQuestion(level);
        } catch (EndOfQuestionsException e) {
            Log.e("my first app", e.toString());
            questionTextView.setText(e.toString());
        }*/



        questionTextView.setText(question);

        goButton.setOnClickListener(this);




    }

    @Override
    public void onClick(View view) {
        String answer = answerEditTex.getText().toString().trim();

        //String correctAnswer = quiz.getCorrectAnswer();
        /*if (quiz.isCorrect(answer)==1) {
            resultTextView.setText("Correct !");
        }
        else {
            resultTextView.setText( answer + " is wrong !");
        }*/

        if (presetQuiz.isCorrect(answer)==1) {
            resultTextView.setText("Correct !");
        }
        else {
            resultTextView.setText( answer + " is wrong !");
        }


        levelStr =  ((EditText)findViewById(R.id.levelEditText)).getText().toString();
        level=Integer.parseInt(levelStr);

        try {
            //question = quiz.getNextQuestion(level);
            question = presetQuiz.getNextQuestion(level);
        } catch (EndOfQuestionsException e) {
            Log.e("my first app", e.toString());
            resultTextView.setText(e.toString());
        }
        questionTextView.setText(question);
        answerEditTex.setText("");

    }
}
