
package com.sgv.geoquiz.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sgv.geoquiz.R;
import com.sgv.geoquiz.R.id;
import com.sgv.geoquiz.R.layout;
import com.sgv.geoquiz.R.menu;

public class CheatActivity extends Activity {
    public static final String ANSWER_SHOWN = "answer_shown";
    Button answerBtn;
    TextView tvAnswer;
    boolean answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        answer = getIntent().getBooleanExtra(QuizActivity.ANSWER, false);
        answerBtn = (Button) findViewById(R.id.answerBtn);
        tvAnswer = (TextView) findViewById(R.id.tvAnswer);
        answerBtn.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                tvAnswer.setText(String.valueOf(answer));
                setAnswerShown(true);
            }
        });
        setAnswerShown(false);
    }
    
    private void setAnswerShown(boolean isShown)  {
        Intent i = new Intent(this, QuizActivity.class);
        i.putExtra(ANSWER_SHOWN, isShown);
        setResult(RESULT_OK, i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.cheat, menu);
        return true;
    }

    
    
}
