package com.example.tarasantoshchuk.application1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    /**
     * variable for editText view from activity_main layout
     */
    private EditText editText;

    /**
     * key to save editText's text in bundle
     */
    private static final String EDIT_TEXT = "edit_text";

    /**
     * sets content view
     * initializes editText
     * sets editText's text from bundle
     */
    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.editTextString);
        /**
         * variable for btnGoTo2 button from activity_main layout
         */
        Button btnGoTo2 = (Button) findViewById(R.id.btnGoTo2);
        btnGoTo2.setOnClickListener(new View.OnClickListener() {
            /**
             * starts next task's main activity
             */
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
        /**
         * variable for btnReverse button from activity_main layout
         */
        Button btnReverse = (Button) findViewById(R.id.btnReverse);
        btnReverse.setOnClickListener(new View.OnClickListener() {
            /**
             * Handler for button click
             * checks if reverse changes text inside editText and updates text
             */
            @Override
            public void onClick(View v) {
                String oldText = editText.getText().toString();

                String newText = new StringBuilder(editText.getText().toString())
                        .reverse()
                        .toString();

                if (!oldText.equals(newText)) {
                    editText.setText(newText);
                    Toast.makeText(MainActivity.this, R.string.msg_reversed, Toast.LENGTH_SHORT)
                            .show();
                } else {
                    Toast.makeText(MainActivity.this, R.string.msg_no_effect, Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });
        if(savedState != null) {
            editText.setText(savedState.getString(EDIT_TEXT));
        }
    }

    /**
     * Saves text inside editText to bundle
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(EDIT_TEXT, editText.getText().toString());
    }
}
