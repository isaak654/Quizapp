package com.example.android.quizapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        //Method calls
        scoreLabel();
        finalScore();
        restartAgainVisible();

        }

    //Score message
    public void scoreLabel () {
        TextView score = (TextView) findViewById(R.id.score_label);
        //If score is higher than 3, display a congratulations message, otherwise the opposite.
        if (getSum() > 3) {
        score.setText(String.format("%s", getString(R.string.score_success) + "\t" + getName() + "\n\n" + getString(R.string.score_label)));
        } else {
        score.setText(String.format("%s", getString(R.string.score_failure) + "\n\n" + getString(R.string.score_label)));
        }
        }

    //Total score
    public void finalScore () {
        TextView score = (TextView) findViewById(R.id.final_score);
        //If score is higher than 3, display it in blue color, otherwise in red color.
        if (getSum() > 3) {
            score.setText(String.format("%s", getSum() + getString(R.string.numberOfQuestions)));
            score.setTextColor(Color.BLUE);
        } else {
            score.setText(String.format("%s", getSum() + getString(R.string.numberOfQuestions)));
            score.setTextColor(Color.RED);
        }

    }

    // Restart again button visibility state
    public void restartAgainVisible() {
        if (getSum() < 6){
            Button btn= (Button) findViewById(R.id.restart_bt);
            btn.setVisibility(View.VISIBLE);
        }
    }

    // Restart again button
    public void restartAgain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        }

    // Exit button (not a real exit, the app will be moved in background)
    public void exitApp (View view) {
        this.finish();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    //Gets sum from activity_quiz.xml
    public int getSum() {
        Bundle extras = getIntent().getExtras();
        return extras.getInt("id_sum");
    }

    //Gets name from activity_main.xml
    public String getName() {
        Bundle extras = getIntent().getExtras();
        return extras.getString("id_name");
    }

}