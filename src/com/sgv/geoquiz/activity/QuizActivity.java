
package com.sgv.geoquiz.activity;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.sgv.geoquiz.R;

public class QuizActivity extends Activity {

    private Button falseBtn, trueBtn, cheatBtn;
    private ImageButton nextBtn, prevBtn;
    private static final String INDEX = "index";
    public static final String ANSWER = "answer";
    private int currentIndex = 0;
    private boolean cheated = false;

    private TextView tvQuestion;
    // add more questions
    // make it customizable
    private TrueFalse[] questionBank = new TrueFalse[] {
            new TrueFalse(R.string.question_africa, false),
            new TrueFalse(R.string.question_asia, true),
            new TrueFalse(R.string.question_americas, true),
            new TrueFalse(R.string.question_mideast, false),
            new TrueFalse(R.string.question_oceans, true)
    };

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt(INDEX);
        }
        setContentView(R.layout.activity_quiz);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            ActionBar ab = getActionBar();
            ab.setSubtitle("Bodies of Water");
        }

        falseBtn = (Button) findViewById(R.id.falseBtn);
        trueBtn = (Button) findViewById(R.id.trueBtn);
        trueBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });
        falseBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });
        tvQuestion = (TextView) findViewById(R.id.question_text_view);
        updateQuestion();
        tvQuestion.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getNextQuestion();
            }
        });
        nextBtn = (ImageButton) findViewById(R.id.nextBtn);
        nextBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getNextQuestion();
            }

        });
        prevBtn = (ImageButton) findViewById(R.id.prevBtn);
        prevBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getPrevQuestion();
            }

        });

        cheatBtn = (Button) findViewById(R.id.cheatBtn);
        cheatBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizActivity.this, CheatActivity.class);
                intent.putExtra(ANSWER, questionBank[currentIndex].isTrueQuestion());
                startActivityForResult(intent, 0);

            }
        });

        TextView footer = (TextView) findViewById(R.id.footer);
        footer.setText("API Version" + Build.VERSION.SDK_INT);
        
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        cheated = data.getBooleanExtra(CheatActivity.ANSWER_SHOWN, false);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(INDEX, currentIndex);
    }

    private void getNextQuestion() {

        currentIndex = (currentIndex + 1) % questionBank.length;
        updateQuestion();
    }

    private void getPrevQuestion() {
        // (a % b + b) % b % is remainder in java
        currentIndex = ((currentIndex - 1) % questionBank.length + questionBank.length)
                % questionBank.length;
        updateQuestion();
    }

    private void checkAnswer(boolean b) {
        if (cheated) {
            Toast.makeText(this, R.string.juddgement_toast, Toast.LENGTH_LONG).show();
            cheated = false;
        } else {
            boolean answerIsTrue = questionBank[currentIndex].isTrueQuestion();
            if (b == answerIsTrue) {
                Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void updateQuestion() {
        int question = questionBank[currentIndex].getQuestion();
        tvQuestion.setText(question);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.quiz, menu);
        return true;
    }

}
