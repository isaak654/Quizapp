package com.example.android.quizapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;


public class QuizActivity extends AppCompatActivity {

    //Variables definition
    EditText canadaCapital;
    EditText googleBrowser;
    String nameField;
    String welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        welcome = intent.getStringExtra("welcome");
        nameField = intent.getStringExtra("id_name");

        // Capture the layout's TextView and set the string as its text
        TextView userName = (TextView) findViewById(R.id.name_field);
        userName.setText(String.format("%s", welcome + nameField));

        //Variable initialization
        canadaCapital = (EditText) findViewById(R.id.canadaCapital);

        //Set up OnFocus listener for non-text box views to hide keyboard.
        canadaCapital.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        //Variable initialization
        googleBrowser = (EditText) findViewById(R.id.googleBrowser);
        //Set up OnFocus listener for non-text box views to hide keyboard.
        googleBrowser.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

    }

    // Sum of corrects answers
    public int sumPoints() {

        //Initialize sum value to 0
        int sum = 0;

        RadioButton answerQ1 = (RadioButton) findViewById(R.id.RadioButton2A);
        RadioButton answerQ2 = (RadioButton) findViewById(R.id.RadioButton1B);
        RadioButton answerQ3 = (RadioButton) findViewById(R.id.RadioButton2C);
        EditText answerQ4 = (EditText) findViewById(R.id.canadaCapital);
        String answer4 = answerQ4.getText().toString().trim();
        CheckBox answerQ5_1 = (CheckBox) findViewById(R.id.checkboxAnteater);
        CheckBox answerQ5_2 = (CheckBox) findViewById(R.id.checkboxWhale);
        CheckBox answerQ5_3 = (CheckBox) findViewById(R.id.checkboxChicken);
        CheckBox answerQ5_4 = (CheckBox) findViewById(R.id.checkboxPigeon);
        EditText answerQ6 = (EditText) findViewById(R.id.googleBrowser);
        String answer6 = answerQ6.getText().toString().trim();


        //If answers are correct, then increment score by 1 point
        if (answerQ1.isChecked()) {
            sum++;
        }
        if (answerQ2.isChecked()) {
            sum++;
        }
        if (answerQ3.isChecked()) {
            sum++;
        }
        if (answer4.equalsIgnoreCase("Ottawa")) {
            sum++;
        }
        if (answerQ5_1.isChecked() && answerQ5_2.isChecked() && !answerQ5_3.isChecked() && !answerQ5_4.isChecked()) {
            sum++;
        }
        if (answer6.equalsIgnoreCase("Chrome")) {
            sum++;
        }

        //Return sum value
        return sum;

    }

    /**
     * Called to hide keyboard on android after clicking outside EditText
     */
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * Called when the user taps the Send button to switch Activity
     */
    public void changeActivity2(View view) {
        Intent passValue = new Intent(this, ScoreActivity.class);
        passValue.putExtra("id_sum", sumPoints());
        passValue.putExtra("id_name", nameField);
        if (passValue.resolveActivity(getPackageManager()) != null) {
            startActivity(passValue);
            finish();
        }
    }

}