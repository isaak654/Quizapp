package com.example.android.quizapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    //Variable definition
    EditText userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Implicit “Submit” after hitting Done on the keyboard
        userName = (EditText) findViewById(R.id.name_field);
        userName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    changeActivity(v);
                    handled = true;
                }
                return handled;
            }
        });

        //Set up OnFocus listener for non-text box views to hide keyboard.
        userName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });


    }
    /** Called to hide keyboard on android after clicking outside EditText  */
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /** Called when the user taps the Start button to switch Activity */
    public void changeActivity(View view) {
        Intent intent = new Intent(this, QuizActivity.class);
        String nameField = userName.getText().toString();
        intent.putExtra("welcome", getString(R.string.welcome_user) + " ");
        intent.putExtra("id_name", nameField + "!");
        if (nameField.equals("")) {
            Toast.makeText(this, getString(R.string.name_warning), Toast.LENGTH_LONG).show();
            return;
        }
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
            finish();
        }

    }




}